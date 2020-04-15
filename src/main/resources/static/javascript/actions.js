var counter = 2;

   function addDonationItem() {
      var row = document.getElementById("item"); // find row to copy
      var table = document.getElementById("itemsTable"); // find table to append to
      var clone = row.cloneNode(true); // copy children too
      clone.childNodes[1].textContent = counter;
      table.appendChild(clone); // add new row to end of table

      counter++;
    }

   function changeRequestStatus() {
        window.alert("button was pressed");
   }

   function changeDonationStatus() {
        window.alert("button was pressed");
   }

$(document).ready(function(){

    $('.student-reveal').click(function(){
        $('.student-info').hide();
        $(this).nextUntil('.student-reveal').slideToggle('fast');
    });

    $('.donor-reveal').click(function(){
        $('.donor-info').hide();
        $(this).nextUntil('.donor-reveal').slideToggle('fast');
    });

    $('.request-status').click(function(event) {
    	// Prevent the form from submitting via the browser.
    	event.preventDefault();
    	searchViaAjax();
    });

    $('.donation-status').click(function(event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        searchViaAjax();
    });

});

	function changeItemStatusAjax(theElement) {
		var status = {};

		status['itemId'] = theElement.id;
        status['itemStatus'] = theElement.textContent;

		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/volunteer/requests/postStatus",
			data : JSON.stringify(status),
			/* dataType : 'json', */
			timeout : 100000,
			success : function(status) {
				console.log("SUCCESS: ", status);
				theElement.textContent = status;
			},
			error : function(e) {
				console.log("ERROR: ", e);
			},
			done : function(e) {
				console.log("DONE");
			}
		});
	}

		function changeDonationStatusAjax(theElement) {
    		var status = {};
    		status['donationId'] = theElement.id;
            status['donationStatus'] = theElement.textContent;

    		$.ajax({
    			type : "POST",
    			contentType : "application/json",
    			url : "/volunteer/requests/postDonationStatus",
    			data : JSON.stringify(status),
    			/* dataType : 'json', */
    			timeout : 100000,
    			success : function(status) {
    				console.log("SUCCESS: ", status);
    				theElement.textContent = status;
    			},
    			error : function(e) {
    				console.log("ERROR: ", e);
    			},
    			done : function(e) {
    				console.log("DONE");
    			}
    		});
    	}

		function display(data) {
    		var json = "<h4>Ajax Response</h4>&lt;pre&gt;"
    				+ JSON.stringify(data, null, 4) + "&lt;/pre&gt;";
    		$('#feedback').html(json);
    	}

