var _ = require("underscore");

var products = exports.products = [
    {id : 1, name : "Cheese", description : "The finest cheese ever", price : "£1.50"},
    {id : 2, name : "Beer", description : "Lovely Beer", price : "£2.30"}
];


exports.getProductById = function(id, callback) {
    var matchingUser = _.find(products, function(product) {
        return id === user.id;
    });
    callback(undefined, matchingUser);
};

exports.getProductsByIds = function(ids, callback) {
    var items = _.filter(products, function(product) { return ids.indexOf(product.id) != -1; });
    callback(undefined, items);
}