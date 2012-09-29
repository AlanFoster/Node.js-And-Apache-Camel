exports.index = {
    menuName : "Home",
    handle : function(req, res){
        res.render("index", { title: "Home" })
    }
};