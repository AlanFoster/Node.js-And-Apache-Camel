var steps = function() {
    this.World = require("../support/world.js").World;
    var assert = require("../helpers/assert");
    var _ = require("underscore");

    this.Given("I am on the home page", function(callback) {
        console.log("visiting home page");
        this.browser.visit("http://localhost:3000/", callback);
    });

    this.Given("the title says '$expectedTitle'", function(expectedTitle, callback) {
        var title = this.browser.text("h2");
        assert.assertEquals(title, expectedTitle);
        callback();
    });

    this.Then("I shall be happy", function(callback){
        callback();
    });

    this.When("I click the '$linkText' link", function(linkText, callback) {
        this.browser
            .clickLink(linkText, callback);
    });

    this.Then("I will be taken to the '$expectedPageName' page", function(expectedPageName, callback) {
        var title = this.browser.text("h2");
        assert.assertEquals(expectedPageName, title);
        callback();
    });

    this.Then("the page says '$expectedMessage'", function(expectedMessage, callback) {

      assert.assertTrue(this.browser.text().indexOf(expectedMessage) > -1);

       callback();
    });
};

module.exports = steps;