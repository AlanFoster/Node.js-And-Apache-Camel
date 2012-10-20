var assert = require("assert")
describe('Production Webservice Connectivity', function(){

    describe('#Connecting With soap node', function(){
        var soap = require("soap");
        var assert = require("./../helpers/assert");

        it("should start client successfully", function(callback){
            var self = this;
            soap.createClient("http://localhost:10000/ShoppingCart?wsdl", function(err, client) {
                assert.assertTrue(!err, "there should not be a client error");
                self.client = client;
                callback();
            });
        });

        it("should return products when called GetProducts is called", function(callback) {
            this.client.GetAllProducts({}, function(err, result) {
                assert.assertTrue(!err, "there should not be a client error");
                //assert.assertNot(result);
                //assert.ok(result.Products);
                assert.assertTrue(result.Products.Product.length > 0, "Expect more than one length returned");
                callback();
            })
        });


        it("should return a product when called GetProduct is called with an id of 1", function(callback) {
            var client = this.client;
            client.GetProduct({ "tns:productId" : "1" }, function(err, result) {
                assert.assertTrue(!err, "there should not be a client error");
                //assert.ok(result);
                //assert.ok(result.Products);
                assert.assertEquals("1", result.Product.productId, "Returned id should equal to 1");
                callback();
            })
        });
    })
})