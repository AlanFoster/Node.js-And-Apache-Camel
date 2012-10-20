var steps = function() {
    this.World = require("../support/world.js").World;
    var assert = require("../helpers/assert");
    var _ = require("underscore");

    var fs = require("fs");
    var http = require("http");
    var request = require("request");
    var soap = require("soap");

    var wsdlLocation = "/home/a/Downloads/Node.js-Test/app/services/wsdl/TestWsdl.wsdl";

    var port;

    var TestService = {
        TestService : {
            TestServicePort : {
                GetProduct : function(args) {
                    return { ProductId : "123", Price: 1.00 };
                }
            }
        }
    };

    this.Given("there is a mocked soap service running on port '$portNumber'", function(portNumber, callback) {
        port = portNumber;
        var server = http.createServer(function(req, res) {
            res.statusCode = 404;
            res.end();
        });

        server.listen(port);

        // Assert the server is running
        request("http://localhost:" + port, function(err, res, body) {
            assert.assertTrue(!err, "Expected no error in calling soap server");
            callback();
        })
        // Store the server in the cucumber context so that other steps can access the object
        this.server = server;
    });


    this.Given("there is a basic mock service running", function(callback) {
        var server = this.server;
        var wsdl = fs.readFileSync(wsdlLocation, 'utf8');
        soap.listen(server, '/TestService', TestService, wsdl);
        callback();
    });

    this.Given("there is a valid generated wsdl", function(callback) {
        request("http://localhost:" + port + "/TestService?wsdl", function(err, res, body) {
            assert.assertTrue(!err, "Expected no error in calling generated wsdl");
            assert.assertEquals(res.statusCode, 200, "expect 200 status response");
            assert.assertTrue(body.length > 0, "expected a wsdl body");

            callback();
        })
    });

    this.When("I call the 'GetProduct' soap service with the product id '123'", function(callback) {
        var self = this;
        soap.createClient("http://localhost:" + port + "/TestService?wsdl", function(err, client) {
            assert.assertTrue(!err, "expected to create a client succesfully");
            client.GetProduct({ ProductId : "123" }, function(err, result) {
                assert.assertTrue(!err, "Expected no error from GetProduct call");
                self.productDetailsResponse = result;
                callback();
            });
        });
    });

    this.Then("I get valid product details back", function(callback) {
        var result = this.productDetailsResponse;
        assert.assertEquals("123", result.ProductId);
        assert.assertEquals(1.00, parseFloat(result.Price));
        callback();
    });


    this.When("I test cucumber.js with the following json", function(multiline, callback) {
        this.jsonObject = JSON.parse(multiline);
        callback();
    });

    this.Then("the key foo shall equal bar", function(callback) {
        var jsonObject = this.jsonObject;
        assert.assertEquals(jsonObject["foo"], "bar", "the key foo should equal bar");
        callback();
    });
};

module.exports = steps;