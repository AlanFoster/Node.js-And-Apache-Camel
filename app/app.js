
/**
 * Module dependencies.
 */

var express = require("express")
  , routes = require("./routes");

var app = module.exports = express.createServer();

// Configuration

app.configure(function(){
  app.set("views", __dirname + "/views");
  app.set("view engine", "ejs");
  app.use(express.bodyParser());
  app.use(express.methodOverride());
  app.use(express.cookieParser());
  app.use(express.session({ secret: "9e515910-0a7c-11e2-892e-0800200c9a66" }));
  app.use(app.router);
  app.use(express.static(__dirname + "/public"));
});

app.configure("development", function(){
  app.use(express.errorHandler({ dumpExceptions: true, showStack: true }));
});

app.configure("production", function(){
  app.use(express.errorHandler());
});

// Routes

app.get("/", routes.index);
app.get("/viewAllProducts", routes.viewAllProducts);

app.listen(3000, function(){
  console.log("Express server listening on port %d in %s mode", app.address().port, app.settings.env);
});
