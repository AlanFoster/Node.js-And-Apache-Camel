var _ = require("underscore");

var products = exports.products = [
    {id : 1, name : "Cheese", description : "The finest cheese ever", price : "1.50"},
    {id : 2, name : "Beer", description : "Lovely Beer", price : "2.30"},
    {id : 3, name : "Ramen", description : "Awesome Ramen", price : "0.99"},
    {id : 4, name : "Pizza", description : "Pizza pizza", price : "2.99"}
];


exports.getProductById = function(id, callback) {
    var matchingUser = _.find(products, function(product) {
        return id === user.id;
    });
    callback(undefined, matchingUser);
};

exports.getProductsByIds = function(ids, callback) {
    console.log(ids);
    var items = _.filter(products, function(product) {
        return ids.indexOf(product.id) !== -1;
    });
    callback(undefined, items);
}