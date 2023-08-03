# SBJOS Design Document

## Design - Inventory Management System

## 1. Problem Statement

Inventory Management System is an application that helps an organization keep track of their inventories,
purchase and sell prices, and shows an alert when a stock is running low.

## 2. Top Questions to Resolve in Review

1. Should the set limit be a percentage based on the amount of items or Should it be fully determined
   by the organization. Should it be set in percentage, number or optional?

2. Should we add vendors and customers in the application to allow a warehouse to keep track of these
   aspects of their business?

3. should it provide the average time an item spends in category before sold? Should the items purchase
   and sell price be optional or mandatory?

4. Should the ID Number a per item number with a specific prefix or should there be one item number for
   all items of the same type, and keep manufacture's Item Serial Number for per item identification?

5. Capability to filter the item list by each of the available attribute.

6. Able to change the name of an inventory if it is required for restructuring.

7. Able to create pre-defied categories for items for assignment with admin account only.

8. Provide account access. User and Admin accounts.

## 3. Use Cases

[//]: # (U1. ** As a user, I want to create an inventory to identify where the items are stored.)

[//]: # ()
[//]: # (U2. ** As a user, I want to be able to retrieve an inventory to get a list of items in that inventory.)

[//]: # ()
[//]: # (U3. ** As a user, I want to be able to delete an inventory, only if it is empty, if it is not being used.)

[//]: # ()
[//]: # (U4. ** As a user, I want to be able to add items to an inventory.)

U4. As a user, I want to be able to add items to an inventory and assign a pre-defined location to that item.

U5. As a user, I want to be able to create pre-defined categories for items for assignment.

U5. As a user, I want to be able to create pre-defined location for items for assignment.

U6. As a user, I want to be able to delete pre-defined categories for items for assignment.

U6. As a user, I want to be able to delete pre-defined location for items for assignment.

U7. As a user, I want to be able to change the values of an item's attribute in an inventory.

U8. As a user, I want to be able to retrieve items in an inventory.

U9. As a user, I want to be able to remove items to an inventory.

U10. As a user, I want to be able to set a warning level for low items in an inventory.

## 4. Project Scope

### 4.1. In Scope

[//]: # (- ** Creating an inventory)
[//]: # (- Removing an inventory)
- Creating items in an inventory
- Retrieving items in an inventory
- Updating items in an inventory
- Removing items in an inventory

### 4.2. Out of Scope

- User ID who is accessing or modifying the values.
- Provide a graph for inventories for statistical purposes.
- Creating, retrieving, updating and removing provider list.
- Creating, retrieving, updating and removing customer list.
- Use a scanner for input to scan stocks.
- Restock estimation based on shipment delivery time and item time in inventory.
- Assign a pre-defined category to that item.
- Setting warning level.

## 5. Proposed Architecture Overview

This will provide the ability to create, retrieve, update, and delete items by using the following endpoints:

- `CreateItemRequest`
- `GetItemRequest`
- `UpdateItemRequest`
- `DeleteItemRequest`

The inventory will be a DynamoDB table. The items will be stored in a list of items in the inventory  
table.

We will provide a web interface for users to manage their inventories. Thi webpage will use HTML, CSS and JavaScript
for design, JavaScript and AXIOS for RestAPI communication.

API will communicate with Lambda for `GET` `PUT` `POST` `DELETE` actions.

## 6. API

### 6.1. Public Models

````
// ItemModel

String category;
String name;
String id;
Boolean availabile;
Integer quantity;
String location;
````

[//]: # (### 6.2. GetWareHouse Endpoint)

[//]: # ()
[//]: # (- Accepts `GET` request to `/inventory/:name`)

[//]: # (- Accepts an inventory name and returns the inventory category.)

[//]: # (  - If the inventory ID or name is not found, will throw a `InventoryNotFoundException`)

[//]: # ()
[//]: # (### 6.3 CreateWareHouse Endpoint)

[//]: # ()
[//]: # (- Accepts `POST` requests to `/inventory`)

[//]: # (- Accepts data to create a new inventory with a provided name.)

[//]: # (- Returns the new inventory with a unique inventory ID assigned by the service if one was not provided.)

[//]: # (- Will verify the provided inventory name if it does not contain any invalid characters`"'` or if it exists.)

[//]: # (  - If the inventory name already exist, will throw a `NameAlreadyInUseException`.)

[//]: # (  - If the inventory name provided contains any of the invalid characters, will throw an `InvalidAttributeException`.)

[//]: # ()
[//]: # (### 6.4 DeleteInventory Endpoint)

[//]: # ()
[//]: # (- Accepts `DELETE` requests to `/inventory/:name`)

[//]: # (- Accepts name to delete an inventory.)

[//]: # (  - If the inventory name is not found, will throw a `InventoryNotFoundException`.)

[//]: # (  - If items still exist in an inventory, will throw a `InventoryNotEmptyException`.)

[//]: # (- Deletes the inventory.)

### 6.5 CreateItem Endpoint

- Accepts `POST` requests to `/inventory/add`
- Accepts all attributes to create a new item
- Verify the item name for invalid characters `[\"\'\\\\]`
- If invalid character, throw `InvalidAttributeException`
- Assigns an alphanumeric ID to the item.
- Inserts the new item to the end of the list.
- Return new item.

### 6.6 UpdateItem Endpoint

- Accepts `PUT` requests to `/inventory/item/:name`
- Accepts an item name or ID to update an item's attribute except the item's ID:

### 6.7 GetItem Endpoint

- Accepts `GET` requests to `/inventory/item/:name/id/:id`
- Accepts a name.
- If the item is not found, will throw a `ItemNotFoundException`
- If the item name is found, returns the item

### 6.7 GetItemByCategory Endpoint

- Accepts `GET` requests to `/inventory/category/:category`
- Accepts a category name.
- If the category name is found, but contains no items, will throw a `ListEmptyException`.
- If the category is not found, will throw a `CategoryNotFoundException`
- Retrieves all items from a category.
- Returns the category list in default order.

### 6.7 GetItemByAvailability Endpoint

- Accepts `GET` requests to `/inventory/available/:available`
- Accepts an availability status (ENUM - true / false).
- If the category name is found, but contains no items, the item list will be empty.

[//]: # (- If the category is not found, will throw a `CategoryNotFoundException`)
- Retrieves all items with availability true or false.
- Returns the category list in default order.

### 6.8 DeleteItem Endpoint

- Accepts `DELETE` requests to `/inventory/delete`
- Accepts name to delete an item.
- If the item name is not found, will throw a `ItemNotFoundException`.
- Deletes the item.


## 7. Tables

### 7.1 Inventory
````
name // sort key. string
id // partition key, string
category // string
available // boolean
quantity // number
location // string
````

## 8. Pages
