var zombie = require('zombie');
var World = function World(callback) {
    this.browser = new zombie.Browser();
    
    callback();
};
exports.World = World;