const addItemForm = document.querySelector("#add-item-form");
const itemTable = document.querySelector("#add-item-table");
const urlParams = new URLSearchParams(window.location.search);
const id = urlParams.get('id');

addItemForm.onsubmit = function(evt) {
  evt.preventDefault();
  const itemName = document.querySelector("#item-name").value;
  const itemCategory = document.querySelector("#Item-category").value;
  const itemQuantity = document.querySelector("#Item-quantity").value;
  const itemLocation = document.querySelector("#Item-location").value;
  const newItem = {
    "itemName": itemName,
    "itemCategory": itemCategory,
    "itemQuantity": itemQuantity,
    "itemLocation": itemLocation,
  }
  axios.post(`https://svebsuap66.execute-api.us-west-2.amazonaws.com/prod/playlists/${id}/songs`, newItem, {
    authorization: {
      'x-api-key': 'K7CHRL6aqt1C6eGJ9EHyFaZCn86G0fyI2sTZKSkW'
    }
  })
  .then(res => {
    console.log(res);
    window.location.reload();
  });
}

window.onload = async function(evt) {
  evt.preventDefault();
  console.log("Getting Album Track Data...");
  axios.get("https://svebsuap66.execute-api.us-west-2.amazonaws.com/prod/playlists/"+id+"/songs", {
    authorization: {
      'x-api-key': 'K7CHRL6aqt1C6eGJ9EHyFaZCn86G0fyI2sTZKSkW'
    }
  }).then(res => {
    console.log(res);
    if (!res.data) {
      throw "No data for playlist with id:" + id;
    }

    if (res.data.itemList.length > 0) {
      populateitemList(res.data.itemList);
    }
  })
}

function populateitemList(itemListData) {
  let thead = itemListTable.createTHead();
  let tbody = itemListTable.createTBody();
  let row = thead.insertRow();

  for (let key in itemListData[0]) {
    let th = document.createElement("th");
    let text = document.createTextNode(key);
    th.appendChild(text);
    row.appendChild(th);
  }

  for (let itemList of itemListTable) {
    let row = tbody.insertRow();
    for (key in albumTrack) {
      let cell = row.insertCell();
      let text = document.createTextNode(itemList[key]);
      cell.appendChild(text);
    }
  }
}
