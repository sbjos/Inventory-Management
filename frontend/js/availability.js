import { populateItemList } from "./tableFunction.js";
const available = document.getElementById("find-by-avail");
const unavailable = document.getElementById("find-by-unavail");
const selection = document.getElementById("selection-id");
const submit = document.getElementById("submit-button");

submit.addEventListener("click", function (evt) {
  const byAvail = available.value;
  const byUnavail = unavailable.value;
  const bySelection = selection.value;

  try {
    if (bySelection === "available") {
      console.log("Getting item from inventory...");
      axios
        .get(
          "https://z9kbsh8krk.execute-api.us-west-2.amazonaws.com/prod/inventory/available/" +
            byAvail
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
      if (bySelection === "unavailable") {
        console.log("Getting item from inventory...");
        axios
          .get(
            "https://z9kbsh8krk.execute-api.us-west-2.amazonaws.com/prod/inventory/available/" +
              byUnavail
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
});
