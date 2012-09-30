var _ = require("underscore"),
    products = require("./products");

// Obviously we wouldn't store names/passwords like this, and we'd use a database
var users = exports.users = [
    {id : 1, email : "foo", password : "bar", shoppingCartDetails : [{id : 1, quantity : 5}]},
    {id : 1, email : "baz", password : "qux", shoppingCartDetails : [{id : 2, quantity : 3}]}
];

exports.getUserByEmailAndPassword = function getUserByEmailAndPassword(email, password, callback) {
    var matchingUser = _.find(users, function(user) {
        return  email === user.email && password === user.password
    });
    callback(undefined, matchingUser);
};

var getUserById = exports.getUserById = function getUserById(id, callback) {
    var matchingUser = _.find(users, function(user) {
        return id === user.id;
    });
    callback(undefined, matchingUser);
};

// This will be a lot simpler with a real database as it currently mimics an inefficient join function
exports.getFullShoppingCart = function(id, callback) {
    getUserById(id, function(err, user) {
        var ids = _.map(user.shoppingCartDetails, function(product) { return id; });
        products.getProductsByIds(ids, function(err, items) {
            var shoppingCart = _.map(items, function(item) {
                var matchingObject = _.find(user.shoppingCartDetails, function(product) { return product.id == item.id; });
                return _.extend(item, matchingObject);
            });
            callback(undefined, shoppingCart);
        });
    });
}