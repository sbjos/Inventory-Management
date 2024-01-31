import {populateItemList} from "./tableFunction.js";
const itemAvailabilityForm = document.querySelector("#availability-form");
const itemTable = document.querySelector("#item-table");
const urlParams = new URLSearchParams(window.location.search);
const available = document.getElementById("selection-id");
const submit = document.getElementById("submit-button");

submit.addEventListener('click', function(evt) {
  const itAvail = document.getElementById("find-by-avail").value;
  const itUnavail = document.getElementById("find-by-unavail").value;
  const select = available.value;

  if (select == "available") {
    console.log("Getting item from inventory...");
    axios.get('https://z9kbsh8krk.execute-api.us-west-2.amazonaws.com/prod/inventory/available/'+itAvail+'')
    .then(res => {
      console.log(res);
      populateItemList(res.data.itemList.itemList);
    })
  } else { if (select == "unavailable") {
      console.log("Getting item from inventory...");
      axios.get('https://z9kbsh8krk.execute-api.us-west-2.amazonaws.com/prod/inventory/available/'+itUnavail+'')
      .then(res => {
        console.log(res);
        populateItemList(res.data.itemList.itemList);
      })
    }
  }
})
