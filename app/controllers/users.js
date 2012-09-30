var products = require("../models/products"),
    users = require("../models/users");


exports.viewLogin = {
    menuName : "Login",
    handle : function(req, res){
        res.render("login", { title: "Login" })
    }
};

exports.validateLogin = {
    menuName : "User",
    handle : function(req, res) {
        users.getUserByEmailAndPassword(req.param("email"), req.param("password"), function(err, user) {
            var loginSuccess = !!user;
            if(loginSuccess) {
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
            res.render("shoppingCart", {
                title : "Shopping Cart",
                user : user,
                shoppingCart : getFullShoppingCart(user.shoppingCartDetails)
            });
        });
    }
};

function getFullShoppingCart(shoppingCartDetails) {
  //  return products.getFullShoppingCart(shoppingCartDetails);
    return [];
}