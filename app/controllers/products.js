var products = require("../models/products");

exports.viewAllProducts = {
    menuName : "Products",
    handle : function(req, res){
        res.render("viewAllProducts", { title: "Products", products : products.products, user : req.session.user })
    }
};