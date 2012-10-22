// Java-esque asserts
var Assert = (function () {
    var assertDescriptions = [
        { name: "assertEquals", func: function (expected, actual, reason) { return expected === actual; }, logMessage: "{2} :: Expected <{0}> Received <{1}>" },
        { name: "assertNotSame",func: function (expected, actual, reason) {return expected !== actual;}, logMessage : "{2} :: Expected <{0}> Received <{1}>" },
        { name: "assertNull",func: function (obj, reason) {return obj === undefined || obj === null; }, logMessage : "{2} :: Expected <null|undefined> Received <{0}>" },
        { name: "assertNotNull",func: function (obj, reason) {return !(obj === undefined || obj === null); },logMessage: "{1} :: Expected Not <null|undefined> Received <{0}>" },
        { name: "assertTrue",func: function (obj, reason) {return obj === true;},logMessage: "{1} :: Expected <true> Received <{0}>" },
        { name: "assertFalse",func: function (obj, reason) {return obj === false;},logMessage: "{1} :: Expected <false> Received <{0}>" }
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
                    var message = "FAILED :: " + assertName + " : " + formattedLogMessage;
                    console.log(message);
                    throw new Error(message);
                }
            };
        }).call(assertObject, assertDescription.name, assertDescription.func, assertDescription.logMessage);
    });

    assertDescriptions.length = 0;

    return assertObject;
})();

module.exports = Assert;