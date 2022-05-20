package bixchat.xyz.services;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import bixchat.xyz.Splash;

import org.json.JSONException;
import org.json.JSONObject;

public class HTTPResponseHandler {

    public void analyzeResponse(Context context, Activity activity, String endpoint, String jsonString) throws JSONException {
        if(endpoint == "/"){
            Log.v("Server_Health", jsonString);
            showResponse(context, activity, "info", "Server Health", jsonString);
        } else if(endpoint.contains("/api/login")){
            if(jsonString != ""){
                SharedPreference sp = new SharedPreference(context);
                try {
                    JSONObject jsonObject = new JSONObject(jsonString);
                    String x_access_token = jsonObject.getString("x_access_token");
                    String user_id = jsonObject.getString("user_id");
                    String public_id = jsonObject.getString("public_id");
                    String email = jsonObject.getString("email");
                    Log.v("http_api_login", "success");
                    sp.setPreference("x_access_token", x_access_token);
                    sp.setPreference("user_id", user_id);
                    sp.setPreference("public_id", public_id);
                    sp.setPreference("email", email);
                    sp.setPreference("isLoggedIn", "true");
                    //showResponse(context, activity, "error", "Login Successful!", "Thank you for being a loyal user...");
                    context.startActivity(new Intent(context, Splash.class));
                } catch (Exception ex){
                    Log.e("http_api_login", ex.toString());
                    showResponse(context, activity, "error", "Login Failed!", ex.toString());
                    sp.setPreference("isLoggedIn", "false");
                }
            } else {
                Log.e("http_api_login", "empty");
                showResponse(context, activity, "error", "Login Failed!", "Empty string return");
            }
        }
    }

    private void showResponse(Context context, Activity activity, String type, String topic, String message){
        activity.runOnUiThread(new Runnable() {
            public void run() {
                BixChat nenasa = new BixChat();
                nenasa.showDialogBox(context, type, topic, message);
            }
        });
    }
}
