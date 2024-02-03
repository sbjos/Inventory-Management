import { populateItemList } from "./tableFunction.js";
const indexForm = document.querySelector("#index-form");
const itemTable = document.querySelector("#item-table");
const newPageTable = document.querySelector("#item-details");
const urlParams = new URLSearchParams(window.location.search);
const submit = document.getElementById("submit");

submit.addEventListener("click", function (evt) {
  console.log("Getting item from inventory...");
  axios
    .get(
      "https://z9kbsh8krk.execute-api.us-west-2.amazonaws.com/prod/inventory/all?all=true"
    )
    .then((res) => {
      console.log(res);
      if (!res.data.itemList.itemList) {
        alert(res.data.errorMessage);
      }
      populateItemList(res.data.itemList.itemList);
    });
});
