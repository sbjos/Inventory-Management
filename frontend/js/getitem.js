const getItemForm = document.querySelector("#get-item-form");
const getItemTable = document.querySelector("#get-item-table");
const urlParams = new URLSearchParams(window.location.search);
const submitName = document.getElementById("submit-button-name");
const submitId = document.getElementById("submit-button-id");

submitName.onclick = function(evt) {
  const itName = document.getElementById("find-by-name").value;
  console.log("Getting item from inventory...");
  axios.get('https://z9kbsh8krk.execute-api.us-west-2.amazonaws.com/prod/inventory/name/'+itName+'')
  .then(res => {
    console.log(res);
    populateitem(res.data.item);
})
}

submitId.onclick = function(evt) {
  const itId = document.getElementById("find-by-id").value;
  console.log("Getting item from inventory...");
  axios.get('https://z9kbsh8krk.execute-api.us-west-2.amazonaws.com/prod/inventory/id/'+itId+'')
  .then(res => {
    console.log(res);
    populateitem(res.data.itemList);
  })
}

function populateitem(items) {
  console.log(items);
  let thead = getItemTable.createTHead();
  let tbody = getItemTable.createTBody();
  let row = thead.insertRow();

  for (let key in items) {
    console.log(key);
    let cell = row.insertCell();
    let text = document.createTextNode(key);
    cell.appendChild(text);
}

  row = tbody.insertRow();

  for (let key in items) {
      let cell = row.insertCell();
      let text = document.createTextNode(items[key]);
      cell.appendChild(text);
  }
}