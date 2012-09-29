
/*
 * GET home page.
 */

exports.index = function(req, res){
  res.render("index", { title: "Home" })
};

exports.viewAllProducts = function(req, res) {
  res.render("viewAllProducts", { title : "Products" });
};