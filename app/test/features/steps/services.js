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
                    return { ProductId : "123", price: 1.00 };
                }
            }
        }
    }

    this.Given("there is a mocked soap service running on port '15099'", function(callback) {
        var wsdl = fs.readFileSync(wsdlLocation, 'utf8'),
            server = http.createServer(function(req, res) {
                res.statusCode = 404;
                res.end();
            });
        server.listen(port);
        soap.listen(server, '/testService', TestService, wsdl);

        // Assert it is running
        request("http://localhost:" + port, function(err, res, body) {
            assert.assertTrue(!err);
            callback();
        })
    });

    this.Given("there is a valid generated wsdl", function(callback) {
        request("http://localhost:" + port + "/testService?wsdl", function(err, res, body) {
            assert.assertTrue(!err);
            assert.assertEquals(res.statusCode, 200);
            assert.assertTrue(body.length > 0);

            callback();
        })
    });

    this.When("I call the 'GetProduct' soap service with the product id '123'", function(callback) {
        var self = this;
        soap.createClient("http://localhost:" + port + "/testService?wsdl", function(err, client) {
            assert.assertTrue(!err);
            //ProductId : "123"
            client.GetProduct({ }, function(err, result) {
                //assert.assertTrue(!err);
                console.log(result);
                self.productDetailsResponse = result;
                callback();
            });
        });
    });

    this.Then("I get valid product details back", function(callback) {
        var result = this.productDetailsResponse;
        assert.assertEquals("123", result.price);
        assert.assertEquals(1.00, parseFloat(result.price));
        callback();
    });
};

module.exports = steps;