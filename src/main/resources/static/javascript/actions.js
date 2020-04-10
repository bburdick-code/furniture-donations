var counter = 2;

   function addDonationItem() {
      var row = document.getElementById("item"); // find row to copy
      var table = document.getElementById("itemsTable"); // find table to append to
      var clone = row.cloneNode(true); // copy children too
      clone.childNodes[1].textContent = counter;
      table.appendChild(clone); // add new row to end of table

      counter++;
    }
