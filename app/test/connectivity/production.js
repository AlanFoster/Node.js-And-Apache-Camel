var assert = require("assert")
describe('Production Webservice Connectivity', function(){

    describe('#Connecting With soap node', function(){
        var soap = require("soap");

        it("should start client successfully", function(callback){
            var self = this;
            soap.createClient("http://localhost:10000/ShoppingCart?wsdl", function(err, client) {
                assert.ok(!err);
                self.client = client;
                callback();
            });
        });

        it("should return products when called GetProducts is called", function(callback) {
            this.client.GetAllProducts({}, function(err, result) {
                assert.ok(!err);
                assert.ok(result);
                callback();
            })
        });
    })
})