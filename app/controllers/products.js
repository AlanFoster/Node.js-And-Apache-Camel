exports.viewAllProducts = {
    menuName : "Products",
    handle : function(req, res){
        res.render("viewAllProducts", { title: "Products" })
    }
};