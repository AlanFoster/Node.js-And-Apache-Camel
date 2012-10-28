var steps = function() {
    this.World = require("../support/world.js").World;
    var assert = require("./../../helpers/assert");
    var _ = require("underscore");

    this.When("I click the products search bar and input '$searchTerm'", function(searchTerm, callback) {
        var browser = this.browser;

        browser.fill("#productSearch", searchTerm);
        callback();
    });
};

module.exports = steps;