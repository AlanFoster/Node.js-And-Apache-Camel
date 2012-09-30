var _ = require("underscore");

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

exports.getUserById = function getUserById(id, callback) {
    var matchingUser = _.find(users, function(user) {
        return id === user.id;
    });
    callback(undefined, matchingUser);
};