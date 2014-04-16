var cordova = require('cordova'),
    exec = require('cordova/exec');

var flashlight = flashlight || {};

/**
 * 打开闪光灯
 * @param successCallback
 * @param errorCallback
 */
function torch(successCallback, errorCallback) {
  	exec(successCallback, errorCallback, 'Flashlight', 'torch', []);
};

/**
 * 关闭闪光灯
 * @param successCallback
 * @param errorCallback
 */
function off(successCallback, errorCallback) {
	exec(successCallback, errorCallback, 'Flashlight', 'off', []);
};

/**
 * 判断闪光灯是否打开
 * @param successCallback
 * @param errorCallback
 */
function isTorch(successCallback, errorCallback) {
  	exec(successCallback, errorCallback, 'Flashlight', 'isTorch', []);
};

/**
 * 开关切换
 * @param successCallback
 * @param errorCallback
 */
function toggle(successCallback, errorCallback) {
	isTorch(function(data) {
		if (data.result) {
			off(successCallback, errorCallback);
		} else {
			torch(successCallback, errorCallback);
		}
	}, errorCallback);
};

flashlight.torch = torch;
flashlight.off = off;
flashlight.isTorch = isTorch;
flashlight.toggle = toggle;

module.exports = flashlight;