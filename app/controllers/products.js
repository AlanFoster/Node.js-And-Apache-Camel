var products = [
    {id : 1, name : "Cheese", description : "The finest cheese ever", price : "£1.50"},
    {id : 2, name : "Beer", description : "Lovely Beer", price : "£2.30"}
];

exports.viewAllProducts = {
    menuName : "Products",
    handle : function(req, res){
        res.render("viewAllProducts", { title: "Products", products : products })
    }
};