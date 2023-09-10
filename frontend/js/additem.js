const addItemForm = document.querySelector("#add-item-form");
const additemTable = document.querySelector("#item-table");
const urlParams = new URLSearchParams(window.location.search);
const submit = document.getElementById("submit-button");

submit.onclick = function(evt) {
  evt.preventDefault();
  const name = document.querySelector("#item-name").value;
  const category = document.querySelector("#Item-category").value;
  const quantity = document.querySelector("#Item-quantity").value;
  const location = document.querySelector("#Item-location").value;

  const newItem = {
    "name": name,
    "category": category,
    "quantity": quantity,
    "location": location
  }

  axios.post('https://z9kbsh8krk.execute-api.us-west-2.amazonaws.com/prod/inventory/add/?a=a', newItem)
  .then(function (response) {
    console.log(response);
    window.location.reload();
  })
  .catch(function (error) {
    console.log(error);
    populateitem(res.data.item)
  });
}

function populateitem(item) {
  let thead = additemTable.createTHead();
  let tbody = additemTable.createTBody();
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
