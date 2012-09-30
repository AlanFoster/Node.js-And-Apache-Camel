var subRoutes = [
    {name : "main index routes", value : require("./main.js")},
    {name : "order routes", value : require("./products.js")},
    {name : "login", value : require("./users.js")}
];

subRoutes.forEach(function(subRoute) {
    for(var i in subRoute.value) {
        if(i in exports) {
            throw new Error("Couldn't add route. Duplicate key : " + i);
        }
        exports[i] = subRoute.value[i];
    }
});