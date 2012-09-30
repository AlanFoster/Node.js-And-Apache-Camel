exports.viewLogin = {
    menuName : "Login",
    handle : function(req, res){
        res.render("login", { title: "Login" })
    }
};

exports.validateLogin = {
    menuName : "User",
    handle : function(req, res) {
        console.log("Login succesful : " + req.param("email") === "foo" && req.param("password") === "bar");
        res.json({"loginSuccess" : req.param("email") === "foo" && req.param("password") === "bar"});
    }
};

exports.shoppingCart  = {
    menuName : "shopping cart",
    handle : function(req, res) {
        res.render("shoppingCart", { title : "Shopping Cart" });
    }
};