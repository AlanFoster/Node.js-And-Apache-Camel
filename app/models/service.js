var nock = require("nock");

var products = [
    {id : 1, name : "Cheese", description : "The finest cheese ever", price : "1.50"},
    {id : 2, name : "Beer", description : "Lovely Beer", price : "2.30"},
    {id : 3, name : "Ramen", description : "Awesome Ramen", price : "1.19"},
    {id : 4, name : "Pizza", description : "Pizza pizza", price : "2.99"}
];

var service = nock("https://www.external.service.com")
    .post("/getAllProducts")
    .reply(200, products);