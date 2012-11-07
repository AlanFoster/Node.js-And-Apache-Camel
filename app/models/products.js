var _ = require("underscore");

var configManager = require("konphyg")(__dirname + "./../config"),
    webserviceConfig = configManager("webservice"),
    soap = require("soap"),
    serviceLocation = "http://" + webserviceConfig.host + ":" + webserviceConfig.port + "/" + webserviceConfig.serviceName + "?wsdl";

exports.getAllProducts = function(callback) {
    soap.createClient(serviceLocation, function(err, client) {
        client.GetAllProducts({}, function(err, result) {
            callback(undefined, result.Products.Product);
        });
    });
}