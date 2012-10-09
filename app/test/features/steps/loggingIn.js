var steps = function() {
    this.World = require("../support/world.js").World;
    var assert = require("../helpers/assert");

    this.Given("the application is running", function(callback) {
       this.browser.visit("http://localhost:3000/")
           .then(callback);
    });

    this.Given("I am not logged in", function(callback) {
        var browser = this.browser;

        browser.visit("http://localhost:3000/")
            .then(function() {
                var loginElement =  browser.query("#login");
                assert.assertNotNull(loginElement);

                var loginElementText = loginElement.querySelector("a").textContent;
                assert.assertEquals("Login", loginElement.querySelector("a").textContent);
            })
            .then(callback);
    });

    this.Then("I will be logged in", function(callback) {
        callback();
    });


    this.When("I log in with the following details", function(details, callback) {
        var browser = this.browser;
        var email = details.raw()["email"];
        var password = details.raw()["password"];

        // TODO See why 'email' doesn't work by itself
        browser
            .fill("#email", "foo")
            .fill("#password", "bar")
            .pressButton("Sign in", callback);
    })
};

module.exports = steps;