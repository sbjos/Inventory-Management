import { populateItemList } from "./tableFunction.js";
const category = document.getElementById("find-by-category");
const available = document.getElementById("find-by-avail");
const unavailable = document.getElementById("find-by-unavail");
const selection = document.getElementById("selection-id");
const submit = document.getElementById("submit-button");

submit.addEventListener("click", function () {
  const byCategory = category.value;
  const byAvailable = available.value;
  const byUnavailable = unavailable.value;
  const bySelection = selection.value;

  if (!byCategory) {
    alert("Please enter an item category");
  } else {
    try {
      if (byCategory && bySelection === "available") {
        console.log("Getting item from inventory...");
        axios
          .get(
            "https://z9kbsh8krk.execute-api.us-west-2.amazonaws.com/prod/inventory/category/" +
              byCategory +
              "/" +
              byAvailable
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
        if (byCategory && bySelection === "unavailable") {
          console.log("Getting item from inventory...");
          axios
            .get(
              "https://z9kbsh8krk.execute-api.us-west-2.amazonaws.com/prod/inventory/category/" +
                byCategory +
                "/" +
                byUnavailable
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
              "https://z9kbsh8krk.execute-api.us-west-2.amazonaws.com/prod/inventory/category/" +
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
        }
      }
    } catch (error) {
      console.log(error);
    }
  }
});
