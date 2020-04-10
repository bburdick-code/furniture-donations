function showAlert() {
    alert("The button was clicked!");
}

/*
let btnAddDonation = document.querySelector('#addDonationItem');
let counter = document.querySelector('#counter');

btnAddDonation.addEventListener('click', () => {
    counter.value = parseInt(counter.value) + 1;
});

var counter = 0;

function addDonationItem() {
	counter++;
	var newFields = document.getElementById('readroot').cloneNode(true);
	newFields.id = '';
	newFields.style.display = 'block';
	var newField = newFields.childNodes;
	for (var i=0;i<newField.length;i++) {
		var theName = newField[i].name
		if (theName)
			newField[i].name = theName + counter;
	}
	var insertHere = document.getElementById('writeroot');
	insertHere.parentNode.insertBefore(newFields,insertHere);
}
*/
var counter = 2;

   function addDonationItem() {
      var row = document.getElementById("item"); // find row to copy
      var table = document.getElementById("itemsTable"); // find table to append to
      var clone = row.cloneNode(true); // copy children too
      clone.childNodes[1].textContent = counter;

      // var label = clone.getElementById("itemLabel");
      // label.innerHTML = 'item ' + counter;
      table.appendChild(clone); // add new row to end of table

      counter++;
    }
