var _ = require("underscore");

var configManager = require("konphyg")(__dirname + "./../config"),
    webserviceConfig = configManager("webservice"),
    soap = require("soap"),
    serviceLocation = "http://" + webserviceConfig.host + ":" + webserviceConfig.port + "/" + webserviceConfig.serviceName + "?wsdl",
    assert = require("./../test/helpers/assert");

var products = [
    {id : 1, name : "Cheese", description : "The finest cheese ever", price : "1.50"},
    {id : 2, name : "Beer", description : "Lovely Beer", price : "2.30"},
    {id : 3, name : "Ramen", description : "Awesome Ramen", price : "1.19"},
    {id : 4, name : "Pizza", description : "Pizza pizza", price : "2.99"}
];

exports.getAllProducts = function(callback) {
    soap.createClient(serviceLocation, function(err, client) {
        assert.assertTrue(!err, "there should not be a client error");
        client.GetAllProducts({}, function(err, result) {
            assert.assertTrue(!err, "Expected no error from GetProduct call");
            callback(undefined, result.Products.Product);
        });
    });
}

exports.getProductById = function(id, callback) {
    var matchingUser = _.find(products, function(product) {
        return id === user.id;
    });
    callback(undefined, matchingUser);
};

exports.getProductsByIds = function(ids, callback) {
    var items = _.filter(products, function(product) {
        return ids.indexOf(product.id) !== -1;
    });
    callback(undefined, items);
}