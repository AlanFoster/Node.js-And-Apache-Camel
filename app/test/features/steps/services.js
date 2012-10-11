var steps = function() {
    this.World = require("../support/world.js").World;
    var assert = require("../helpers/assert");
    var fs = require("fs");
    var _ = require("underscore");
    // TODO
    var wsdlLocation = "/home/a/Downloads/Node.js-Test/app/services/wsdl/TestWsdl.wsdl";
    var http = require("http");

    var request = require("request");
    var soap = require("soap");
    var port = 10000;


    var TestService = {
        TestService : {
            TestServicePort : {
                GetProduct : function(args) {
                    return { ProductId : "123", Price: 1.00 };
                }
            }
        }
    };

    this.Given("there is a mocked soap service running on port '10000'", function(callback) {
        var wsdl = fs.readFileSync(wsdlLocation, 'utf8'),
            server = http.createServer(function(req, res) {
                res.statusCode = 404;
                res.end();
            });
        server.listen(port);
        soap.listen(server, '/TestService', TestService, wsdl);

        // Assert it is running
        request("http://localhost:" + port, function(err, res, body) {
            assert.assertTrue(!err, "Expected no error in calling soap server");
            callback();
        })
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
};

module.exports = steps;