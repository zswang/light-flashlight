var cordova = require('cordova'),
    exec = require('cordova/exec');

var flashlight = flashlight || {};

flashlight.torch = function(successCallback, errorCallback) {
  exec(successCallback, errorCallback, 'Flashlight', 'torch', []);
};

flashlight.off = function(successCallback, errorCallback) {
  exec(successCallback, errorCallback, 'Flashlight', 'off', []);
};

flashlight.getStatus = function(successCallback, errorCallback) {
  exec(successCallback, errorCallback, 'Flashlight', 'off', []);
};

module.exports = flashlight;