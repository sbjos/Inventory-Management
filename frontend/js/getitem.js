import {populateItem} from "./tableFunction.js";
const getItemFormName = document.querySelector("#get-item-form-name");
const getItemFormId = document.querySelector("#get-item-form-id");
const itemTable = document.querySelector("#item-table");
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
    populateItem(res.data.itemList.itemList[0]);
  });
});
