# Inventory Management Application

## Statement

Inventory Management System is an application that helps keep track of their inventories.

## Use Cases

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

## Project Scope

### In Scope

- Creating items in an inventory
- Retrieving items in an inventory
- Updating items in an inventory
- Removing items in an inventory
- Creating pre-defined categories
- removing pre-defined categories
- Creating pre-defined locations
- removing pre-defined locations

## Proposed Architecture Overview
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

## API

### Public Models
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

### CreateItem Endpoint

- Accepts `POST` requests to `/inventory/add:`
- Accepts all attributes to create a new item
- Auto-assigns an alphanumeric ID to the item.
- Verify the item name for valid characters `[\"\'\\\\]`
- If invalid character, throw `InvalidAttributeException`
- Inserts the new item ar random order to the list.
- Returns new item.

### UpdateItemService Endpoint

- Accepts `PUT` requests to `/inventory/update:`
- Accepts an items name to update an item's attribute except the item's name and ID.

### GetItemService Endpoint

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

### DeleteItemService Endpoint

- Accepts `DELETE` requests to `/inventory/delete`
- Accepts name to delete an item.
- If the item name is not found, will throw a `ItemNotFoundException`.
- Deletes the item.


## Tables

### IM-Item
````
itemName // partition key, string 
id // string
category // string
available // string
quantity // number
location // string
````

### IM-Category
````
category // partition key, string 
````
### IM-Location
````
location // partition key, string 
````