 
 
/*
 * All js for the products.html page
 *
 * NOTE: Last in file a comment to enable debugging
 * because this is dynamically loaded (doesn't work by default to debug
 * dynamic scripts in Chrome at least...)
 *
 */

// Run after DOM constructed (same as $(document).ready())
$(function() {});

function getList (uName){
    return $.ajax({
		type: "GET",
		contentType: "application/json; charset=utf-8",
		url: "http://localhost:8080/Task_Manager_Frontend/rs/items/lists/" + uName,
		data: {},
		dataType: "json",
		success: function (data) {
			var list = data['ShoppingList'];
			console.log(list);
			// Use JQuery and HTML
			var tr;
			$('#products').empty();

			tr = $('<tr class="ui-widget-header" />');
			tr.append("<font size='60'><th font-size=20px><u>Todo List</u></th></font><br /><br />");
			$('#products').append(tr);
			for (var i = 0; i < list.length; i++) {
				tr = $('<tr id=' + list[i].id + '/>');
				tr.append("<td><button style='width:250px'>" + list[i].name + "</button></td>");
				$('#products').append(tr);
			}

			$("#products tbody").on("click", "tr", function () {
				id = $(this).closest('tr').attr('id');
                                //name = $(this).closest('tr').children('td:first').text();
                                //window.location.assign("itemslist.jspx?id="+id+"&listname=" + name);
                                $.ajax({
                                    url: "http://localhost:8080/Task_Manager_Frontend/rs/items/id/"+id,
                                    type: "POST",
                                    success: function(data){
                                        alert(data);
                                        window.location.assign("itemslist.jspx");
                                    },
                                    error:function(jqXHR, textStatus, errorThrown) {
                                        alert("Error: " + jqXHR);
                                    }
                                });
			});

		},
		error: function (result) {
			alert("Error");
		}
	});
};

function getItems (itemId){
	return $.ajax({
		type: "GET",
		contentType: "application/json; charset=utf-8",
		url: "http://localhost:8080/Task_Manager_Frontend/rs/items/items/" + itemId,
		dataType: "json",
		success: function (data) {
			var list = data['Item'];
			console.log(list);

			// Use JQuery and HTML
			var tr;
			$('#products').empty();

			tr = $('<tr class="ui-widget-header" />');
			tr.append("<font size='60'><th font-size=20px><u>Name</u></th></font><br /><br />");
			$('#products').append(tr);

			for (var i = 0; i < list.length; i++) {
				tr = $('<tr id=' + itemId+ '/>');
				tr.append("<td>" + list[i].name + "</td>");
				$('#products').append(tr);
			}
			$("#products tbody").on("click", "tr", function () {
				id = $(this).closest('tr').attr('id');
				alert(id);
                                listToRemove(id); 
				// window.open();
			});
		},
		error: function (result) {
			alert("Error 2");
		}
	});

};


function listToRemove(listToRemove){
           return $.ajax({
            type: "DELETE",
            contentType: "application/text; charset=utf-8",
            url: "http://localhost:8080/Task_Manager_Frontend/rs/items/remove/" + listToRemove,
            data: {},
            dataType: "json",
            success: function (data) {
                alert("removing list with id: " + listToRemove);
                window.location.assign("main.jspx");
            },
            error: function (result) {
                alert("Error");
            }
        });
        
   };
