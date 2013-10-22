 
 
/*
 * All js for the products.html page
 *
 * NOTE: Last in file a comment to enable debugging
 * because this is dynamically loaded (doesn't work by default to debug
 * dynamic scripts in Chrome at least...)
 *
 */


function getList (uName){
    return $.ajax({
		type: "GET",
		contentType: "application/json; charset=utf-8",
		url: "http://localhost:8080/Task_Manager_Frontend/rs/items/lists/" + uName,
		data: {},
		dataType: "json",
		success: function (data) {
                        if(data != null){
			var list = data['ShoppingList'];
			console.log(list);
			// Use JQuery and HTML
			var tr;
			$('#products').empty();

			tr = $("<font size='60'><th font-size=20px><u>To Do list</u></th></font><br /><br />");
			tr.append('<tr class="ui-widget-header" />');
			$('#products').append(tr);

                            if(list.id != null){ 
                                 console.log("kul: !" + list.id);
                                  var count= 0;
                                    console.log("no_loop! id:" + list.id);
                                    tr = $('<tr id=' + list.id + '/>');
                                    tr.append("<td><button style='width:250px'>" + list.name + "</button></td>");
                                    $('#products').append(tr);
                            } else count = list.length;
                            for (var i = 0; i <count; i++) {
                                    console.log("loop!");
                                    tr = $('<tr id=' + list[i].id + '/>');
                                    tr.append("<td><button style='width:250px'>" + list[i].name + "</button></td>");
                                    $('#products').append(tr);
			//}
                        }

			$("#products tbody").on("click", "tr", function () {
				id = $(this).closest('tr').attr('id');
                                console.log("Kommer att hämta lista med ID: " + id);
                                alert(id);
                                //name = $(this).closest('tr').children('td:first').text();
                                //window.location.assign("itemslist.jspx?id="+id+"&listname=" + name);
                                $.ajax({
                                    url: "http://localhost:8080/Task_Manager_Frontend/rs/items/id/"+id,
                                    type: "POST",
                                    success: function(data){
                                        window.location.assign("itemslist.jspx");
                                    },
                                    error:function(jqXHR, textStatus, errorThrown) {
                                        alert("Error: " + jqXHR);
                                    }
                                });
			});

		}else {
                    alert("Du har inga listor!"); 
                }},
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
                        if(data !=null){
			var list = data['Item'];
			console.log(list);

			// Use JQuery and HTML
			var tr;
			$('#products').empty();

			tr = $("<font size='60'><th font-size=20px><u>Name</u></th></font><br /><br />");
			tr.append('<tr class="ui-widget-header" />');
			$('#products').append(tr);
                            if(list.name != null){ 
                                 console.log("kul: !" + list.id);
                                 console.log("no_loop! id:" + list.id);
                                    tr = $('<tr id=' + itemId + '/>');
                                    tr.append("<td><button style='width:250px'>" + list.name + "</button></td>");
                                    $('#products').append(tr);
                            } else {
                              for (var i = 0; i < list.length; i++) {
                            	tr = $('<tr id=' + itemId+ '/>');
                            	tr.append("<td>" + list[i].name + "</td>");
                            	$('#products').append(tr);
                              }
                            }
			$("#products tbody").on("click", "tr", function () {
				id = $(this).closest('tr').attr('id');
				alert(id);
                                listToRemove(id); 
				// window.open();
			});
                        } else {
                       
			var tr;
			$('#products').empty();

			tr = $("<font size='60'><th font-size=20px><u>Name</u></th></font><br /><br />");
			tr.append('<tr class="ui-widget-header" />');
			$('#products').append(tr);
                        tr = $('<tr id=' + itemId+ '/>');
				tr.append("<td>Tryck för att ta bort tom lista</td>");
				$('#products').append(tr);
			$("#products tbody").on("click", "tr", function () {
				id = $(this).closest('tr').attr('id');
				alert(id);
                                listToRemove(id); 
				// window.open();
			});
                        }       
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
                console.log("Kommer att ta bort lista med ID: " + listToRemove);
                window.location.assign("main.jspx");
            },
            error: function (result) {
                alert("Error");
            }
        });
        
   };
