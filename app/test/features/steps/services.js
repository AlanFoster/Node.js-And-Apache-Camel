var steps = function() {
    this.World = require("../support/world.js").World;
    var assert = require("./../../helpers/assert");
    var _ = require("underscore");

    var fs = require("fs");
    var http = require("http");
    var request = require("request");
    var soap = require("soap");

    var wsdlLocation = "/home/a/Downloads/Node.js-Test/app/services/wsdl/TestWsdl.wsdl";

    var port;

    var TestService = {
        "ShoppingCart":{
            "ShoppingCartPort":{
                "GetProduct":function () {
                            return {
                                "productId":"1",
                                "name":"Cheese",
                                "description":"Cheese Description",
                                "price":1.0
                            }
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
        soap.listen(server, '/ShoppingCart', TestService, wsdl);
        callback();
    });

    this.Given("there is a valid generated wsdl", function(callback) {
        request("http://localhost:" + port + "/ShoppingCart?wsdl", function(err, res, body) {
            assert.assertTrue(!err, "Expected no error in calling generated wsdl");
            assert.assertEquals(res.statusCode, 200, "expect 200 status response");
            assert.assertTrue(body.length > 0, "expected a wsdl body");

            callback();
        })
    });

    this.When("I call the 'GetProduct' soap service with the product id '1'", function(callback) {
        var self = this;
        soap.createClient("http://localhost:" + port + "/ShoppingCart?wsdl", function(err, client) {
            assert.assertTrue(!err, "expected to create a client succesfully");
            client.GetProduct({ "xsd1:ProductId" : "1" }, function(err, result) {
                assert.assertTrue(!err, "Expected no error from GetProduct call");
                self.productDetailsResponse = result;

                callback();
            });
        });
    });

    this.Then("I get valid product details back", function(callback) {
        var result = this.productDetailsResponse;
        assert.assertEquals("1", result.productId);
        assert.assertEquals(1.00, parseFloat(result.price));
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


    this.Given("the mocked soap service will return the following information when the '$operationName' operation is called", function(operationName, multiline, callback) {
        var server = this.server;
        var wsdl = fs.readFileSync(wsdlLocation, 'utf8');
        var json = JSON.parse(multiline);

        var mockedOperations = {};
        mockedOperations[operationName] = function() {
            return json;
        }

        var TestService = {
            "ShoppingCart":{
                "ShoppingCartPort": mockedOperations
            }
        };

        console.log("Operation name :: " + operationName);

        soap.listen(server, '/ShoppingCart', TestService, wsdl);
        callback();
    });
};

module.exports = steps;