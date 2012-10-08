var zombie = require('zombie')
    HTML5 = require("html5");
var World = function World(callback) {
    this.browser = new zombie.Browser({runScripts:true, debug:false, htmlParser: HTML5});
    
    callback();
};
exports.World = World;