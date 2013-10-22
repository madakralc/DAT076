/*
* All js for the products.html page 
 * 
 * NOTE: Last in file a comment to enable debugging
* because this is dynamically loaded (doesn't work by default to debug
* dynamic scripts in Chrome at least...)
* 
 */
// Run after DOM constructed (same as $(document).ready())
$(function() { 
   

});


function getList (){
           return $.ajax({

            type: "GET",
            contentType: "application/json; charset=utf-8",
           // url: "http://localhost:8080/Task_Manager_Frontend/jsp/static/demo",
            url: "http://localhost:8080/Task_Manager_Frontend/rs/items/lists/dag",
            data: {id: id,username: username,name: name, text: text},
            dataType: "json",
            success: function (data) {
                 
                console.log(data);
                console.log("Data length 23:" + data.length);
        // Use JQuery and HTML
            var tr;
            $('#products').empty();
            
            tr = $('<tr class="ui-widget-header" />');
            tr.append("<font size='60'><th font-size=20px><u>Todo List</u></th></font><br /><br />");
            $('#products').append(tr);
            
            for (var i = 0; i < data.length; i++) {
                tr = $('<tr id=' + data[i].id + '/>');
                tr.append("<td><button style='width:250px'>" + data[i].name + "</button></td>");
                $('#products').append(tr);
            }
            
            $("#products tbody").on("click", "tr", function () {
                id = $(this).closest('tr').attr('id');
                console.log(id); 
               window.open("itemslist.jspx?id="+id);
            });
            
            },
            error: function (result) {
                alert("Error");
            }
        });
        
   };
     

function getItems (){
    // var id = $_GET('id');
    //        console.log("idt är: "  + id);
   //         console.log("Data length 23:" + data.length);
           return $.ajax({

            type: "GET",
            contentType: "application/json; charset=utf-8",
            //url: "http://localhost:8080/Task_Manager_Frontend/jsp/static/demo2",
            url: "http://localhost:8080/Task_Manager_Frontend/rs/items/items/1",
            data: {name: item},
            dataType: "json",
            success: function (data) {
               
                console.log(data);
                 
           
        // Use JQuery and HTML
            var tr;
            $('#products').empty();
            
            tr = $('<tr class="ui-widget-header" />');
            tr.append("<font size='60'><th font-size=20px><u>Name</u></th></font><br /><br />");
            $('#products').append(tr);
            
            for (var i = 0; i < data.length; i++) {
                tr = $('<tr id=' + i + '/>');
                tr.append("<td>" + data[i].item + "</td>");
                $('#products').append(tr);
            }
            
            $("#products tbody").on("click", "tr", function () {
                id = $(this).closest('tr').attr('id');
                alert(id); 
               // window.open();
            });
            
            },
            error: function (result) {
                alert("Error");
            }
        });
        
   };
