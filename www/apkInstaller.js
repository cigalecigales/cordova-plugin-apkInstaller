var exec = require('cordova/exec');

module.exports = {
    install: function(fileName, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "apkInstaller", "install", [fileName]);
    }
};
