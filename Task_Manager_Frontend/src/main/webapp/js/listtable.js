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
            url: "http://localhost:8080/Task_Manager_Frontend/jsp/static/demo",
           // url: "http://localhost:8080/ws_shop_skel/rs/products/getRange?first=1&nItems=3",
            data: "{}",
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
<<<<<<< HEAD
                tr = $('<tr id=' + data[i].id + '/>');
                tr.append("<td>" + data[i].name + "</td>");
=======
                tr = $('<tr id="tr' + data[i].id + '" />');
                tr.append("<td>" + "<button class='menubutton'>" + data[i].name + "</button>" + "</td>");
>>>>>>> branch 'master' of https://github.com/madakralc/DAT076.git
                $('#products').append(tr);
            }
            
            $("#products tbody").on("click", "tr", function () {
                id = $(this).closest('tr').attr('id');
                alert(id); 
                window.open("main.jspx");
            });
            
            },
            error: function (result) {
                alert("Error");
            }
        });
        
   };
     

function getItems (){
           return $.ajax({

            type: "GET",
            contentType: "application/json; charset=utf-8",
            url: "http://localhost:8080/Task_Manager_Frontend/jsp/static/demo2",
           // url: "http://localhost:8080/ws_shop_skel/rs/products/getRange?first=1&nItems=3",
            data: "{}",
            dataType: "json",
            success: function (data) {
                 
                console.log(data);
                console.log("Data length 23:" + data.length);
        // Use JQuery and HTML
            var tr;
            $('#products').empty();
            
            tr = $('<tr class="ui-widget-header" />');
            tr.append("<font size='60'><th font-size=20px><u>Name</u></th></font><br /><br />");
            $('#products').append(tr);
            
            for (var i = 0; i < data.length; i++) {
                tr = $('<tr id=' + i + '/>');
                tr.append("<td>" + data[i].text + "</td>");
                $('#products').append(tr);
            }
            
            $("#products tbody").on("click", "tr", function () {
                id = $(this).closest('tr').attr('id');
                alert(id); 
                window.open("main.jspx");
            });
            
            },
            error: function (result) {
                alert("Error");
            }
        });
        
   };
