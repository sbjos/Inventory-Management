import { populateItem } from "./tableFunction.js";
const name = document.getElementById("find-by-name");
const id = document.getElementById("find-by-id");
const submitName = document.getElementById("submit-button-name");
const submitId = document.getElementById("submit-button-id");

submitName.addEventListener("click", function () {
  const byName = name.value;

  if (!byName) {
    alert("Please enter an item name");
  } else {
    try {
      console.log("Getting item from inventory...");
      axios
        .get(
          "https://z9kbsh8krk.execute-api.us-west-2.amazonaws.com/prod/inventory/name/" +
            byName
        )
        .then((res) => {
          console.log(res);
          if (!res.data.item) {
            alert(res.data.errorMessage);
          } else {
            populateItem(res.data.item);
          }
        });
    } catch (error) {
      console.log(error);
    }
  }
});

submitId.addEventListener("click", function () {
  const byId = id.value;

  if (!byId) {
    alert("Please enter an item ID");
  } else {
    try {
      console.log("Getting item from inventory...");
      axios
        .get(
          "https://z9kbsh8krk.execute-api.us-west-2.amazonaws.com/prod/inventory/id/" +
            byId
        )
        .then((res) => {
          console.log(res);
          if (!res.data.itemList) {
            alert(res.data.errorMessage);
          } else {
            populateItem(res.data.itemList.itemList[0]);
          }
        });
    } catch (error) {
      console.log(error);
    }
  }
});
