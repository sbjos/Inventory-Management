const addItemForm = document.querySelector("#add-item-form");
const additemTable = document.querySelector("#add-item-table");
const urlParams = new URLSearchParams(window.location.search);

addItemForm.onsubmit = function(evt) {
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
    // window.location.reload();
  })
  .catch(function (error) {
    console.log(error);
  });

  // axios.get('https://z9kbsh8krk.execute-api.us-west-2.amazonaws.com/prod/inventory/name/'+newItem.name+'')
  // .then(function (response) {
  //   // handle success
  //   console.log(response);
  //   populateitemList(response.data);
  // })
  // .catch(function (error) {
  //   console.log(error);
  // })
}

function populateitemList(itemListData) {
  let thead = additemTable.createTHead();
  let tbody = additemTable.createTBody();
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
