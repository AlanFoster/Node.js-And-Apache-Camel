var steps = function() {
    this.World = require("../support/world.js").World;
    var assert = require("../helpers/assert");
    var _ = require("underscore");

    this.Given("I am not logged in", function(callback) {
        var browser = this.browser;

        browser.visit("http://localhost:3000/")
            .then(function() {
                assert.assertEquals("Login", browser.text("#login a"));
                callback();
            });
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