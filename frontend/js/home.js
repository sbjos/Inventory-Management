const indexForm = document.querySelector('#index-form');
const indexTable = document.querySelector("#item-table");
const newPageTable = document.querySelector('#item-details');
const urlParams = new URLSearchParams(window.location.search);
const submit = document.getElementById('submit');

submit.addEventListener('click', function(evt) {
  console.log("Getting item from inventory...");
  axios.get('https://z9kbsh8krk.execute-api.us-west-2.amazonaws.com/prod/inventory/all?all=true')
  .then(res => {
    console.log(res);
    populateitem(res.data.iten);
  })
});

function populateItem(item) {
  console.log(item);
  let thead = indexTable.createTHead();
  let tbody = indexTable.createTBody();
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

// function openNewItemPage(item) {
//   // Replace the current page with the new page URL
//   window.location.href = `newpage.html`;

//   // Once the new page is loaded, populate data
//   window.onload = function() {
//     const itemDetailsElement = document.getElementById('item-details');
//     if (itemDetailsElement) {
//       itemDetailsElement.textContent = JSON.stringify(item, null, 2);
//     }
//   }
// }

// function openNewItemPage(item) {
//   const newPage = window.open('newpage.html', '_blank'); // Open a new tab/window

//   // Wait for the new page to load before populating data
//   newPage.onload = function() {
//     newPage.document.getElementById('item-details').textContent = JSON.stringify(item, null, 2);
//   }
// }

// function openNewItemPage(queryType, queryValue) {
//   const newPage = `newpage.html?queryType=${queryType}&queryValue=${queryValue}`;
//   window.open(newPage, '_blank');
// }

// function openNewItemPage(queryType, queryValue) {
//   const newPage = `newpage.html?queryType=${queryType}&queryValue=${queryValue}`;
//   window.location.href = newPage; // Redirect to the new URL
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
  // });
// }