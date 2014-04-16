package com.baidu.light.flashlight;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;

public class Flashlight extends CordovaPlugin {
	public static final String TAG = "Flashlight";

	private Camera mCamera;

	/**
	 * Executes the request and returns PluginResult.
	 *
	 * @param action            The action to execute.
	 * @param args              JSONArry of arguments for the plugin.
	 * @param callbackContext   The callback id used when calling back into JavaScript.
	 * @return                  True if the action was valid, false if not.
	 */
	public boolean execute(String action, JSONArray args,
			CallbackContext callbackContext) throws JSONException {

		if (mCamera == null) {
			mCamera = Camera.open();
		}
		if ("torch".equals(action)) { // 打开手电
			Parameters parameters = mCamera.getParameters();
			parameters.setFlashMode(Parameters.FLASH_MODE_TORCH);
			mCamera.setParameters(parameters);
		} else if ("off".equals(action)) { // 关闭手电
			Parameters parameters = mCamera.getParameters();
			parameters.setFlashMode(Parameters.FLASH_MODE_OFF);
			mCamera.setParameters(parameters);
			mCamera.release();
			mCamera = null;
		} else if ("isTorch".equals(action)) { // 获取手电状态
			Parameters parameters = mCamera.getParameters();
			JSONObject json = new JSONObject();
			json.put("result", Parameters.FLASH_MODE_TORCH.equals(parameters
					.getFlashMode()));
			callbackContext.success(json);
		} else {
			return false;
		}
		return true;
	}

	/**
	 * The final call you receive before your activity is destroyed.
	 */
	public void onDestroy() {
		if (mCamera == null) {
			return;
		}
		mCamera.release();
		mCamera = null;
	}
}
