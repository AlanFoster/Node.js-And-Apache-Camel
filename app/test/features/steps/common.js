var steps = function() {
    this.World = require("../support/world.js").World;
    var assert = require("assert");

    this.Given("I am on the home page", function(callback) {
        console.log("visiting home page");
        this.browser.visit("http://localhost:3000/", callback);
    });

    this.Given("the title says '$expectedTitle'", function(expectedTitle, callback) {
        var title = this.browser.text("h2");
        assert.equal(title, expectedTitle);
        callback();
    });

    this.Then("I shall be happy", function(callback){
        callback();
    });

};

module.exports = steps;