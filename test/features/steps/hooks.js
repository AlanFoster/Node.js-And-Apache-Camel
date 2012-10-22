/***
 * Used for executing code before/after scenarios
 */
module.exports = (function() {
    var soap = require("soap");

    /***
     * Used to tear down any mocked soap instances which may still be running
     */
    this.After(function(callback) {
        if(this.server) {
            this.server.close();
        }
       callback();
    })
});
