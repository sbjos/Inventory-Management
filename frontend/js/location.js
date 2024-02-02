import { populateItemList } from "./tableFunction.js";
const location = document.getElementById("find-by-location");
const category = document.getElementById("find-by-category");
const submit = document.getElementById("submit-button");

submit.addEventListener("click", function () {
  const byLocation = location.value;
  const byCategory = category.value;

  if (!byLocation) {
    alert("Please enter a location");
  } else {
    try {
      if (!category) {
        console.log("Getting item from inventory...");
        axios
          .get(
            "https://z9kbsh8krk.execute-api.us-west-2.amazonaws.com/prod/inventory/location/" +
              byLocation +
              "/" +
              byCategory
          )
          .then((res) => {
            console.log(res);
            if (!res.data.itemList) {
              alert(res.data.errorMessage);
            } else {
              populateItemList(res.data.itemList.itemList);
            }
          });
      } else {
        console.log("Getting item from inventory...");
        axios
          .get(
            "https://z9kbsh8krk.execute-api.us-west-2.amazonaws.com/prod/inventory/location/" +
              byLocation
          )
          .then((res) => {
            console.log(res);
            if (!res.data.itemList) {
              alert(res.data.errorMessage);
            } else {
              populateItemList(res.data.itemList.itemList);
            }
          });
      }
    } catch (error) {
      console.log(error);
    }
  }
});
