var products = require("../models/products"),
    users = require("../models/users");

var  viewAllProducts = exports.viewAllProducts = {
    menuName : "Products",
    handle : function(req, res){
        res.render("viewAllProducts", { title: "Products", products : products.products })
    }
};

exports.addProduct = {
    handle : function(req, res) {
        var productId = parseInt(req.param("id"));
        users.addProduct(req.session.userId, productId, function(err, newShoppingCart) {
            req.session.shoppingCart = newShoppingCart;
            viewAllProducts.handle(req, res);
        });
    }
}