const getItemFormName = document.querySelector("#get-item-form-name");
const getItemFormId = document.querySelector("#get-item-form-id");
const getItemTable = document.querySelector("#item-table");
const urlParams = new URLSearchParams(window.location.search);
const byName = document.getElementById("find-by-name");
const byId = document.getElementById("find-by-id");
const submitName = document.getElementById("submit-button-name");
const submitId = document.getElementById("submit-button-id");

submitName.addEventListener('click', function(evt) {
  const itName = document.getElementById('find-by-name').value;
  console.log("Getting item from inventory...");
  axios.get('https://z9kbsh8krk.execute-api.us-west-2.amazonaws.com/prod/inventory/name/'+itName+'')
  .then(res => {
    console.log(res);
    populateItem(res.data.item);
  });
});

submitId.addEventListener('click', function(evt) {
  const itId = document.getElementById('find-by-id').value;

  console.log("Getting item from inventory...");
  axios.get('https://z9kbsh8krk.execute-api.us-west-2.amazonaws.com/prod/inventory/id/'+itId+'')
  .then(res => {
    console.log(res);
    populateItem(res.data.itemList);
  });
});

function populateItem(item) {
  console.log(item);
  let thead = getItemTable.createTHead();
  let tbody = getItemTable.createTBody();
  let row = thead.insertRow();

  for (let key in item) {
    console.log(key);
    let cell = row.insertCell();
    let text = document.createTextNode(key);
    cell.appendChild(text);
  }

  row = tbody.insertRow();

  for (let key in item) {
    let cell = row.insertCell();
    let text = document.createTextNode(item[key]);
    cell.appendChild(text);
  }
}

// function populateItem(item) {
//   // Clear previous table content
//   tableBody.innerHTML = '';
  
//   // Loop through the item properties and populate the table
//   for (const key in item) {
//     const row = tableBody.insertRow();
//     const propertyCell = row.insertCell();
//     const valueCell = row.insertCell();
//     propertyCell.textContent = key;
//     valueCell.textContent = item[key];
//   }
// }

// function openNewItemPage(item) {
//   const newPage = window.open('newpage.html', '_blank'); // Open a new tab/window

//   // Wait for the new page to load before populating data
//   newPage.onload = function() {
//     newPage.document.getElementById('item-details').textContent = JSON.stringify(item, null, 2);
//   }
// }

// function populateItemList(item) {
  // console.log(item);
  // forEach(item => {
  //   const itemElement = document.createElement('div');
  //   itemElement.textContent = `Name: ${item.itemName} -
  //                               ID: ${item.itemId}, 
  //                               Category: ${item.itemCategory},
  //                               Availability: ${item.availability},
  //                               Quantity: ${item.itemQuantity},
  //                               Location: ${item.itemLocation}`;
  //   getItemTable.appendChild(itemElement);
  // })
// }