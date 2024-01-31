import {populateItemList} from "./tableFunction.js";
const itemLocationForm = document.querySelector("#location-form");
const itemTable = document.querySelector("#item-table");
const urlParams = new URLSearchParams(window.location.search);
const category = document.getElementById("find-by-category");
const submit = document.getElementById("submit-button");

submit.addEventListener('click', function(evt) {
  const itLoc = document.getElementById("find-by-location").value;
  const itCat = document.getElementById("find-by-category").value;

  if(category !== " ") {
    console.log("Getting item from inventory...");
    axios.get('https://z9kbsh8krk.execute-api.us-west-2.amazonaws.com/prod/inventory/location/'+itLoc+'/'+itCat+'')
    .then(res => {
      console.log(res);
      populateItemList(res.data.itemList.itemList);
    })
  } else {
    console.log("Getting item from inventory...");
    axios.get('https://z9kbsh8krk.execute-api.us-west-2.amazonaws.com/prod/inventory/location/'+itLoc+'')
    .then(res => {
      console.log(res);
      populateItemList(res.data.itemList.itemList);
    })
  }
})
