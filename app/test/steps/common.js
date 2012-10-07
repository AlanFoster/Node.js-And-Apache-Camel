var steps = function() {
    this.World = require("features/world.js").World;
    var browser = this.browser;
    var assert = require("assert");

    this.Given("I am on the home page", function(callback) {
        browser.visit("http://localhost:3000/", callback);
    });

    this.Given("the title says '$expectedTitle'", function(expectedTitle, callback) {
        var title = browser.text("h2");
        console.log("header :: ", title);
        assert.equal(title, expectedTitle);
        callback();
    });

    this.Then("I shall be happy", function(callback){
        callback();
    });
}

exports.steps = steps;