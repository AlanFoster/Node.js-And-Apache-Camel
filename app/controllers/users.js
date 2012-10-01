var products = require("../models/products"),
    users = require("../models/users");

exports.viewLogin = {
    menuName : "Login",
    handle : function(req, res) {
        res.render("login", { title: "Login", user : req.session.user, redir : req.param("redir", "/shoppingCart") })
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
                users.getFullShoppingCart(user.id, function(err, shoppingCart) {
                    req.session.shoppingCart = shoppingCart;
                    res.json({"loginSuccess" : true});
                });
                return;
            }

            res.json({"loginSuccess" : false});
        });
    }
};

exports.shoppingCart  = {
    menuName : "shopping cart",
    handle : function(req, res) {
        res.render("shoppingCart", { title : "Shopping Cart" });
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

exports.removeProduct = {
    handle : function(req, res) {
        var productId = parseInt(req.param("id"));
        users.removeProduct(req.session.userId, productId, function(err, newShoppingCart) {
           req.session.shoppingCart = newShoppingCart;
           res.redirect("/shoppingCart");
        });
    }
}