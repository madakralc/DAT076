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
                tr = $('<tr id="tr' + data[i].id + '" />');
                tr.append("<td>" + "<button class='menubutton'>" + data[i].name + "</button>" + "</td>");
                $('#products').append(tr);
            }
            
            $("#products tbody").on("click", "tr", function () {
                var product = {};
                product.id = $(this).children().eq(0).text();
                product.name = $(this).children().eq(1).text();
                product.price = $(this).children().eq(2).text();
                alert(product.id); 
            });
            
            },
            error: function (result) {
                alert("Error");
            }
        });
        
   };
     
     
    getList();






});
