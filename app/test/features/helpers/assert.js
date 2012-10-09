// Java-esque asserts
// TODO add fail reason
var Assert = (function () {
    var assertDescriptions = [
        { name: "assertEquals", func: function (expected, actual) { return expected === actual; }, logMessage: "Expected <{0}> Received <{1}>" },
        { name: "assertNotSame",func: function (expected, actual) {return expected !== actual;}, logMessage: "Expected <{0}> Received <{0}>" },
        { name: "assertNull",func: function (obj) {return obj === undefined || obj === null; },logMessage: "Expected <null|undefined> Received <{0}>" },
        { name: "assertNotNull",func: function (obj) {return !(obj === undefined || obj === null); },logMessage: "Expected Not <null|undefined> Received <{0}>" },
        { name: "assertTrue",func: function (obj) {return obj === true;},logMessage: "Expected <true> Received <{0}>" },
        { name: "assertFalse",func: function (obj) {return obj === false;},logMessage: "Expected <false> Received <{0}>" }
    ];

    var assertObject = new Object();
    assertDescriptions.forEach(function (assertDescription) {
        (function (assertName, assertFunction, logMessage) {
            assertObject[assertName] = function () {
                var assertPassed = assertFunction.apply(undefined, arguments);
                var formattedLogMessage = (function (str, values) {
                    return str.replace(/\{((\d+))\}/g, function (match, digit) {
                        return values[digit];
                    });
                })(logMessage, arguments);
                if (!assertPassed) {
                    console.log("FAILED :: " + assertName + " : " + formattedLogMessage);
                    throw new Error("FAILED :: " + assertName + " : " + formattedLogMessage);
                } else {
                    console.log("PASSED :: " + assertName + " : " + formattedLogMessage);
                }
            };
        }).call(assertObject, assertDescription.name, assertDescription.func, assertDescription.logMessage);
    });

    assertDescriptions.length = 0;

    return assertObject;
})();

module.exports = Assert;