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
                assert.assertEquals(1, loginElement.length);
                assert.assertEquals("login", loginElement.innerHTML);

            })
            .then(callback);
    });

    this.Then("I will be logged in", function(callback) {
        var loginElement = this.browser.query("#login");
        console.log("Not logged in");
        //  console.log("test 1" + loginELement);
        // assert.equal(loginELement.innerHTML, "Log out");
        callback();
    });


    this.When("I log in with the following details", function(details, callback) {
        callback();
    })
};

module.exports = steps;