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

    var nav = new Navigator(shop.getProductCatalogue());
    /*************************************
     * 
     * Components (from JQueryUI) and eventhandling
     */
    	$(document).ready(function(){ onLoad(); });	

    
    $("#next-button")
            .button()
            .click(function() {
        nav.next(success, fail);
        function fail() {
            clearFormDialogData(); 
            createErrorDialog("Can't list!!").dialog("open");
        }
        function success(items) {
            createTable(items);
        }
    });
    
    $("#prev-button")
            .button()
            .click(function() {
        nav.prev(success, fail);
        function success(items) {
            createTable(items);
        }
        function fail() {
            createErrorDialog("Can't list!!").dialog("open");
        }

    });

    $("#add-product")
            .button()
            .click(function() {
        createAddDialog().dialog("open");      
    });

    

  //     function page() {
  //          nav.next(success, fail);
  //                  function success(items) {
  //          createTable(items);
  //      }
   //     function fail() {
   //         createErrorDialog("Can't list!!").dialog("open");
   //     }
   //     }
    /**********************************************
     *   
     *   Functions for redering tables, dialogs and helper functions
     */
    
    
    function onLoad() {
      alert("test"); 
    };
    
        function createTable(products) {
        // Use JQuery and HTML
            var tr;
            $('#products').empty();
            
            tr = $('<tr class="ui-widget-header" />');
            tr.append("<td>Id</td>");
            tr.append("<td>Name</td>");
            tr.append("<td>Price</td>");
            $('#products').append(tr);
            
            for (var i = 0; i < products.length; i++) {
                tr = $('<tr id="tr' + products[i].id + '" />');
                tr.append("<td>" + products[i].id + "</td>");
                tr.append("<td>" + products[i].name + "</td>");
                tr.append("<td>" + products[i].price + "</td>");
                $('#products').append(tr);
            }
            $("#products tbody").on("click", "tr", function () {
                var product = {};
                product.id = $(this).children().eq(0).text();
                product.name = $(this).children().eq(1).text();
                product.price = $(this).children().eq(2).text();
                createEditDeleteDialog(product).dialog("open");
            });
    }


    

    // Possible to both edit and delet from same dialog
    function createEditDeleteDialog(product) {
        $("#dialog-form #msg").empty();
         // Using JQueryUI dialog
        $("#dialog-form #title").text("Edit or Delete product");
        $("#dialog-form").dialog({
            autoOpen: false,
            modal: true,
            stack: true,
            title: 'Edit or Delete Product',
            buttons: {
                Delete: function() {
                    var product = getFormDialogData();
                    $(this).dialog("close");
                    createConfirmDeleteDialog(product.id).dialog("open");
                },
                Save: function() {
                    var product = getFormDialogData();
                    nav.container.update(product.id, product.name, product.price);
                    $(this).dialog("close");
                },
                Cancel: function() {
                    $(this).dialog("close");
                }
            }
        });
        setFormDialogData(product);
        //$('#dialog-message').dialog('option', 'title', 'Edit or Delelte Product');
        //$("#dialog-message #msg").append("<p>All form filed are required.</p>");
        //$("#dialog-message #msg").append("<br><form><br>ID:<input type='text' name='id'><br>Name: <input type='text' name='name'><br>Price:<input type='text' name='price'></form>" ); 
    return $('#dialog-form');
        
    }



});