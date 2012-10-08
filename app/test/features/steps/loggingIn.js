var steps = function() {
    this.World = require("../support/world.js").World;
    var assert = require("assert");

    this.Given("I am not logged in", function(callback) {
        this.browser.visit("http://localhost:3000/", function(e, browser) {
          //  var loginElement = this.browser.query("#login");
            console.log("Not logged in");
          //  console.log("test 1" + loginELement);
          // assert.equal(loginELement.innerHTML, "Login");
            callback();
        });
    });

    this.Then("I will be logged in", function(callback) {
        var loginElement = this.browser.query("#login");
        console.log("Not logged in");
        //  console.log("test 1" + loginELement);
        // assert.equal(loginELement.innerHTML, "Log out");
        callback();
    });


    this.When("I log in with the following details", function(details, callback) {
        console.log(details.getContents());
        callback();

    })

};

module.exports = steps;