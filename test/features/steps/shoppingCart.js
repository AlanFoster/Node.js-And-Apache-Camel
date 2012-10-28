var steps = function() {
    this.World = require("../support/world.js").World;
    var assert = require("./../../helpers/assert");
    var _ = require("underscore");
    var util = require("util");

    this.When("I click the add to cart button for '$itemName'", function(itemName, callback) {
        this.browser
            .clickLink(util.format("a[alt*='%s']", itemName), callback);
    });

    this.Then("my shopping cart will look like the following", function(table, callback) {
        var browser = this.browser;
        var shoppingCartItems = browser.querySelectorAll("#shoppingCartShortDetail div");
        console.log(shoppingCartItems);
        _.each(shoppingCartItems, function(shoppingCartItem) {
            //var text = shoppingCartItem.innerHTML;
            console.log("a::: " + shoppingCartItem.innerHTML);
        });

       // this.browser.viewInBrowser();

        callback();
    });
};

module.exports = steps;