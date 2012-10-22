var steps = function() {
    this.World = require("../support/world.js").World;
    var assert = require("./../../helpers/assert");
    var _ = require("underscore");

    this.Then("the products page will show the following information", function(table, callback) {
        var browser = this.browser;

        // Assert Headers first
        var rowCount = 0;
        table.raw().forEach(function(row){
            rowCount++;
            var columnCount = 0;
            row.forEach(function(expectedCellText) {
                columnCount++;
                var actualCellText;
                // if Cell count is one then check for table header instead of td
                if(rowCount === 1) {
                    actualCellText = browser.text("#productsTable tr:nth-child(" + rowCount + ") th:nth-child(" + columnCount + ")");
                } else {
                    actualCellText = browser.text("#productsTable tr:nth-child(" + (rowCount - 1) + ") td:nth-child(" + columnCount + ")");
                }
                assert.assertEquals(expectedCellText, actualCellText, "cell contents should be the same");
            });
        })

        callback();
    });
};

module.exports = steps;