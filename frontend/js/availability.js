const getItemForm = document.querySelector("#get-item-form");
const getItemTable = document.querySelector("#get-item-table");
const urlParams = new URLSearchParams(window.location.search);
const submitAvail = document.getElementById("submit-available");

submitAvail.onclick = function(evt) {
  const itAvail = document.getElementById("find-by-avail").value;
  console.log("Getting item from inventory...");
  axios.get('https://z9kbsh8krk.execute-api.us-west-2.amazonaws.com/prod/inventory/available/'+itAvail+'')
  .then(res => {
    console.log(res);
    populateitem(res.data.item);
  })
}

submitAvail.onclick = function(evt) {
    const itUnavail = document.getElementById("find-by-unavail").value;
    console.log("Getting item from inventory...");
    axios.get('https://z9kbsh8krk.execute-api.us-west-2.amazonaws.com/prod/inventory/available/'+itUnavail+'')
    .then(res => {
      console.log(res);
      populateitem(res.data.item);
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
