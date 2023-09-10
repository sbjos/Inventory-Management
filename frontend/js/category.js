const itemCategoryForm = document.querySelector("#category-form");
const itemCategoryTable = document.querySelector("#item-table");
const urlParams = new URLSearchParams(window.location.search);
const available = document.getElementById("selection-id");
const category = document.getElementById("find-by-category")
const submit = document.getElementById("submit-button");


submit.addEventListener('click', function(evt) {
  const itCat = document.getElementById("find-by-category").value;
  const itUnavail = document.getElementById("find-by-unavail").value;
  const itAvail = document.getElementById("find-by-avail").value;
  const select = available.value;

  if(category !== " " && select == "available") {
    console.log("Getting item from inventory...");
    axios.get('https://z9kbsh8krk.execute-api.us-west-2.amazonaws.com/prod/inventory/category/'+itCat+'/'+itAvail+'')
    .then(res => {
      console.log(res);
      populateitem(res.data.itemList);
    })
  } else { if(category !== " " && select == "unavailable") {
    console.log("Getting item from inventory...");
    axios.get('https://z9kbsh8krk.execute-api.us-west-2.amazonaws.com/prod/inventory/category/'+itCat+'/'+itUnavail+'')
    .then(res => {
      console.log(res);
      populateitem(res.data.itemList);
    })
  } else {
    console.log("Getting item from inventory...");
    axios.get('https://z9kbsh8krk.execute-api.us-west-2.amazonaws.com/prod/inventory/category/'+itCat+'')
    .then(res => {
      console.log(res);
      populateitem(res.data.itemList);
    })
  }
}
})

function populateitem(item) {
  console.log(item);
  let thead = itemCategoryTable.createTHead();
  let tbody = itemCategoryTable.createTBody();
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
//   const newPage = window.open('newpage.html', '_blank'); // Open a new tab/window

//   // Wait for the new page to load before populating data
//   newPage.onload = function() {
//     newPage.document.getElementById('item-details').textContent = JSON.stringify(item, null, 2);
//   }
// }