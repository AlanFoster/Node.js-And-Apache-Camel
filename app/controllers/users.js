var products = require("../models/products"),
    users = require("../models/users");

exports.viewLogin = {
    menuName : "Login",
    handle : function(req, res){
        res.render("login", { title: "Login", user : req.session.user })
    }
};

exports.validateLogin = {
    menuName : "User",
    handle : function(req, res) {
        users.getUserByEmailAndPassword(req.param("email"), req.param("password"), function(err, user) {
            var loginSuccess = !!user;
            if(loginSuccess) {
                // Wouldn't do this in a real app
                req.session.user = user;
                req.session.userId  = user.id;
            }

            res.json({"loginSuccess" : loginSuccess});
        });
    }
};

exports.shoppingCart  = {
    menuName : "shopping cart",
    handle : function(req, res) {
        users.getUserById(req.session.userId, function(err, user) {
            if(!user) {
                res.redirect("/login");
                return;
            }
            users.getFullShoppingCart(user.id, function(err, shoppingCart) {
                res.render("shoppingCart", {
                    title : "Shopping Cart",
                    user : user,
                    shoppingCart : shoppingCart
                });
            })
        });
    }
};

exports.logout = {
    menuName : "",
    handle : function(req, res) {
        req.session.user = undefined;
        req.session.userId = undefined;
        res.redirect("/login");
    }
};