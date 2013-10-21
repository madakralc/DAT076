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
    

    createTable();
    
        function createTable() {
                alert("tb create");
        // Use JQuery and HTML
            var tr;
            $('#products').empty();
            
            tr = $('<tr class="ui-widget-header" />');
            tr.append("<td>Name</td>");
            $('#products').append(tr);
            
            for (var i = 0; i < products.length; i++) {
                tr = $('<tr id="tr' + products[i].id + '" />');
                tr.append("<td>" + products[i].id + "</td>");
                tr.append("<td>" + products[i].name + "</td>");
                tr.append("<td>" + products[i].price + "</td>");
                $('#products').append(tr);
            }

    }





});