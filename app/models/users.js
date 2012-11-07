var configManager = require("konphyg")(__dirname + "./../config"),
    webserviceConfig = configManager("webservice"),
    soap = require("soap"),
    serviceLocation = "http://" + webserviceConfig.host + ":" + webserviceConfig.port + "/" + webserviceConfig.serviceName + "?wsdl";


exports.getUserByEmailAndPassword = function getUserByEmailAndPassword(email, password, callback) {
    soap.createClient(serviceLocation, function(err, client) {
        client.GetCustomer({"tns:email" : email, "tns:password" : password}, function(err, result) {
            callback(result.err, result.Customer);
        });
    });
};

exports.addProduct = function(customerId, productId, callback) {
    soap.createClient(serviceLocation, function(err, client) {
        client.AddProductToCustomerAccount({"tns:customerId" : customerId, "tns:productId" : productId, "tns:quantity" : 1}, function(err, result) {
            callback(result.err, result.Customer);
        });
    });
};

exports.removeProduct = function(customerId, productId, callback) {
    soap.createClient(serviceLocation, function(err, client) {
        client.RemoveProductFromCustomerAccount({"tns:customerId" : customerId, "tns:productId" : productId, "tns:quantity" : 1}, function(err, result) {
            callback(result.err, result.Customer);
        });
    });
};