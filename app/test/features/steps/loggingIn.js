var steps = function() {
    this.World = require("../support/world.js").World;
    var assert = require("../helpers/assert");
    var _ = require("underscore");


    var isLoggedIn = function(browser, callback) {
        callback("Login" != browser.text("#login a"))
    }

    this.Given("I am not logged in", function(callback) {
        isLoggedIn(this.browser, function(loggedIn) {
            assert.assertFalse(loggedIn, "I should not be logged in");
            callback();
        })
    });

    this.Given("I am logged in", function(callback) {
        isLoggedIn(this.browser, function(loggedIn) {
            assert.assertTrue(loggedIn, "I should be logged in");
            callback();
        })
    });

    this.Then("I will not be logged in", function(callback) {
        isLoggedIn(this.browser, function(loggedIn) {
            assert.assertFalse(loggedIn, "I should not be logged in");
            callback();
        })
    });

    this.When("I log in with the following details", function(details, callback) {
        var email = details.hashes()[0]["email"];
        var password = details.hashes()[0]["password"];

        // TODO See why 'email' doesn't work by itself and why I need to specify the id
        this.browser
            .fill("#email", email)
            .fill("#password", password)
            .pressButton("Sign in", callback);
    })
};

module.exports = steps;