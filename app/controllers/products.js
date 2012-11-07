var products = require("../models/products"),
    users = require("../models/users");

var viewAllProducts = exports.viewAllProducts = {
    menuName : "Products",
    handle : function(req, res){
        products.getAllProducts(function(err, products) {
            res.render("viewAllProducts", { title: "Products", products : products })
        });
    }
};