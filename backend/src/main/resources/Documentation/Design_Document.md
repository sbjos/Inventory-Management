# Inventory Management Application

## 1. Problem Statement

Inventory Management System is an application that helps keep track of their inventories.

## 2. Top Questions to Resolve in Review

1. Should the ID Number a per item number with a specific prefix or should there be one item number 
   for all items of the same type?

2. Capability to filter the item list by each of the attribute.

3. Add additional item database or add other DB to create a one-to-many DB relationship where one 
   database is an item database and others could be related to location, or category.  

4. Able to create pre-defied categories for items. (With admin privilege)

5. Provide account access. User and Admin accounts.

6. Assign a pre-defined location to that item.

## 3. Use Cases

U1. Ability to retrieve a list of all items in the inventory.

U2. Ability to retrieve a list items based on availability.

U3. Ability to retrieve a list items based on category.

U4. Ability to retrieve a list items based on location.

U5. Ability to retrieve a list items based on availability in that category.

U6. Ability to retrieve a list items based on category in that location.

U7. Ability to retrieve an item from the inventory.

U8. Ability to add an item to the inventory.

U9. Ability to modify an item to the inventory except for the item name and ID.

U10. Ability to remove an item to the inventory.

U11. Ability to create pre-defined categories.

U12. Ability to create pre-defined locations.

U13. Ability to delete pre-defined categories.

U14. Ability to delete pre-defined locations.

## 4. Project Scope

### 4.1. In Scope

- Creating items in an inventory
- Retrieving items in an inventory
- Updating items in an inventory
- Removing items in an inventory
- Creating pre-defined categories
- removing pre-defined categories
- Creating pre-defined locations
- removing pre-defined locations

### 4.2. Out of Scope

- Creating user accounts.
- Delete a group of items simultaneously.

## 5. Proposed Architecture Overview

The following endpoints will allow to create, retrieve, update, and delete items:

- `DeleteCategoryService`
- `CreateCategoryService`
- `CreateLocationService`
- `DeleteLocationService`
- `CreateItemService`
- `GetItemService`
- `UpdateItemService`
- `DeleteItemService`

A web interface will be provided to allow users to interact with the application.

The app uses a DynamoDB table to store data, AWS Lambda functions to handle request, RestAPI to 
handle user request.

## 6. API

### 6.1. Public Models
````
// ItemModel

 private String name;
 private String id;
 private String category;
 private String available;
 private long quantity;
 private String location;
````
````
// categoryModel

 private String category;
````
````
// locationModel

 private String location;
````
````
// ItemListModel

 private List<Item> itemList;
````

### 6.5 CreateItem Endpoint

- Accepts `POST` requests to `/inventory/add:`
- Accepts all attributes to create a new item
- Auto-assigns an alphanumeric ID to the item.
- Verify the item name for valid characters `[\"\'\\\\]`
- If invalid character, throw `InvalidAttributeException`
- Inserts the new item ar random order to the list.
- Returns new item.

### 6.6 UpdateItemService Endpoint

- Accepts `PUT` requests to `/inventory/update:`
- Accepts an items name to update an item's attribute except the item's name and ID.

### 6.7 GetItemService Endpoint

#### Find by name
- Accepts `GET` requests to `/inventory/name/{name}:`
- Accepts a name.
- If the item is not found, will throw a `ItemNotFoundException`
- Returns the item

#### Find by ID
- Accepts `GET` requests to `/inventory/id/{id}:`
- Accepts an ID.
- If the item is not found, will throw a `ItemNotFoundException`
- Returns the item
- 
#### Find by availability

- Accepts `GET` requests to `/inventory/available/{available}`
- Accepts an availability status.
- Returns all items based on availability.

#### Find by category
- Accepts `GET` requests to `/inventory/category/{category}:`
- Accepts a category name.
- If the category name is found, but contains no items, will throw a `ItemListNotFoundException`.
- If the category is not found, will throw a `CategoryNotFoundException`
- Retrieves all items from a category.
- Returns the category list.

#### Find by category and availability
- Accepts `GET` requests to `/inventory/category/{category}/{availabe}:`
- Accepts a category and availability.
- If the category name is found, but contains no items, will throw a `ItemListNotFoundException`.
- If the category is not, will throw a `CategoryNotFoundException`
- Retrieves all items from a category.
- Returns the category list.

#### Find by Location
- Accepts `GET` requests to `/inventory/location/{location}:`
- Accepts a location name.
- If the location name is found, but contains no items, will throw a `ItemListNotFoundException`.
- If the location is not found, will throw a `LocationNotFoundException`
- Retrieves all items from a location.
- Returns the category list.

#### Find by location and category
- Accepts `GET` requests to `/inventory/location/{location}/{category}:`
- Accepts a location and category.
- If the location name is found, but contains no items, will throw a `ItemListNotFoundException`.
- If the location is not found, will throw a `LocationNotFoundException`
- Retrieves all items from a category.
- Returns the category list.

### 6.8 DeleteItemService Endpoint

- Accepts `DELETE` requests to `/inventory/delete`
- Accepts name to delete an item.
- If the item name is not found, will throw a `ItemNotFoundException`.
- Deletes the item.


## 7. Tables

### 7.1 IM-Item
````
itemName // partition key, string 
id // string
category // string
available // string
quantity // number
location // string
````

### 7.1 IM-Category
````
category // partition key, string 
````
### 7.1 IM-Location
````
location // partition key, string 
````

## 8. Pages
##