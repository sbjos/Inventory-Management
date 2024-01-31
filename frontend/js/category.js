import {populateItemList} from "./tableFunction.js";
const itemCategoryForm = document.querySelector("#category-form");
const itemTable = document.querySelector("#item-table");
const urlParams = new URLSearchParams(window.location.search);
const available = document.getElementById("selection-id");
const category = document.getElementById("find-by-category")
const submit = document.getElementById("submit-button");


submit.addEventListener('click', function(evt) {
  const itCat = document.getElementById("find-by-category").value;
  const itUnavail = document.getElementById("find-by-unavail").value;
  const itAvail = document.getElementById("find-by-avail").value;
  const select = available.value;

  if(category !== " " && select == "available") {
    console.log("Getting item from inventory...");
    axios.get('https://z9kbsh8krk.execute-api.us-west-2.amazonaws.com/prod/inventory/category/'+itCat+'/'+itAvail+'')
    .then(res => {
      console.log(res);
      populateItemList(res.data.itemList.itemList);
    })
  } else { if(category !== " " && select == "unavailable") {
    console.log("Getting item from inventory...");
    axios.get('https://z9kbsh8krk.execute-api.us-west-2.amazonaws.com/prod/inventory/category/'+itCat+'/'+itUnavail+'')
    .then(res => {
      console.log(res);
      populateItemList(res.data.itemList.itemList);
    })
  } else {
    console.log("Getting item from inventory...");
    axios.get('https://z9kbsh8krk.execute-api.us-west-2.amazonaws.com/prod/inventory/category/'+itCat+'')
    .then(res => {
      console.log(res);
      populateItemList(res.data.itemList.itemList);
    })
  }
}
})
