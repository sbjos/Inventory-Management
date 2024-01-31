const itemTable = document.querySelector("#item-table");
export {populateItem, populateItemList};

function populateItem(item) {
// Clear previous table content
itemTable.innerHTML = '';

if (item) {
    // Create table header row
    let thead = itemTable.createTHead();
    let headerRow = thead.insertRow();

    // Create table body
    let tbody = itemTable.createTBody();
    let row = tbody.insertRow();

    for (let key in item) {
    // Create table header cell
    let th = document.createElement("th");
    th.textContent = key;

    if (th.textContent === 'itemName') th.textContent = 'Name';
    if (th.textContent === 'itemId') th.textContent = 'ID';
    if (th.textContent === 'itemCategory') th.textContent = 'Category';
    if (th.textContent === 'availability') th.textContent = 'Availability';
    if (th.textContent === 'itemQuantity') th.textContent = 'Quantity';
    if (th.textContent === 'itemLocation') th.textContent = 'Location';

    headerRow.appendChild(th);

    // Create table data cell
    let cell = row.insertCell();
    let text = document.createTextNode(item[key]);
    cell.appendChild(text);
    }
}
}

function populateItemList(itemList) {
// Clear previous table content
itemTable.innerHTML = '';

if (itemList) {
    // Create table header row
    let thead = itemTable.createTHead();
    let headerRow = thead.insertRow();

    // Create table header cells based on the keys of the first item in the list
    for (let key in itemList[0]) {
    let th = document.createElement("th");
    th.textContent = key;

    if (th.textContent === 'itemName') th.textContent = 'Name';
    if (th.textContent === 'itemId') th.textContent = 'ID';
    if (th.textContent === 'itemCategory') th.textContent = 'Category';
    if (th.textContent === 'availability') th.textContent = 'Availability';
    if (th.textContent === 'itemQuantity') th.textContent = 'Quantity';
    if (th.textContent === 'itemLocation') th.textContent = 'Location';

    headerRow.appendChild(th);
    }

    // Create table body
    let tbody = itemTable.createTBody();

    // Loop through each item in the list
    itemList.forEach(item => {
    // Create a new row for each item
    let row = tbody.insertRow();

    // Populate the row with item data
    for (let key in item) {
        let cell = row.insertCell();
        let text = document.createTextNode(item[key]);
        cell.appendChild(text);
    }
    });
}
}
