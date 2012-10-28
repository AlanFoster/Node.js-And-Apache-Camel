$(function () {
    var searchBox = $("#productSearch");
    var submitButton = $("#productSearchSubmit");
    submitButton.click(function(e){
        searchBox.keyup();
    });
    searchBox.keyup(function (e) {
        var searchTerm = $("#productSearch").val();
        $("#productsTable tr").each(function (key, value) {
            console.log(searchTerm);
            var row = $(value);
            var productNameCell = row.find("td[name=productName]");
            var isValidProduct = productNameCell && productNameCell.text().indexOf(searchTerm) != -1;
            if(isValidProduct) {
                row.show();
            } else {
                row.hide();
            }
        });
    });
});