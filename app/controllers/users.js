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
        users.getUserByEmailAndPassword(req.param("email"), req.param("password"), function(err, customer) {
            if(!err) {
                req.session.user = customer;
                req.session.userId  = customer.customerId;
                req.session.shoppingCart = customer.ShoppingCart.CartItem;

                res.json({"loginSuccess" : true});
                return;
            } else {
                res.json({"loginSuccess" : false});
            }
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
        req.session.shoppingCart = undefined;

        res.redirect("/login");
    }
};

exports.removeProduct = {
    handle : function(req, res) {
        var productId = parseInt(req.param("id"));
        users.removeProduct(req.session.userId, productId, function(err, customer) {
           if(err) {
               // TODO ...
           }
           req.session.user = customer;
           req.session.userId  = customer.customerId;
           req.session.shoppingCart = customer.ShoppingCart.CartItem;

           res.redirect("/shoppingCart");
        });
    }
};

exports.addProduct = {
    handle : function(req, res) {
        var productId = parseInt(req.param("id"));
        users.addProduct(req.session.userId, productId, function(err, customer) {
            if(err) {
                // TODO ...
            }

            req.session.user = customer;
            req.session.userId  = customer.customerId;
            req.session.shoppingCart = customer.ShoppingCart.CartItem;

            res.redirect("/viewAllProducts")
        });
    }
};