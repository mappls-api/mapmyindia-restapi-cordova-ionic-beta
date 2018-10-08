package com.mapmyindia.cordova.restapi;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import java.util.LinkedHashMap;
import java.util.Map;
import java.net.URLEncoder;

public class Setter extends CordovaPlugin {

 private final static String BASE_REQ_URL = "https://apis.mapmyindia.com/advancedmaps/v1";
 private final String token_url = "https://outpost.mapmyindia.com/api/security/oauth/token";
 private String oauth2_token_type = null;
 private String oauth2_access_token = null;
 private Long oauth2_token_validity = null;

 @Override
 public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

    if (action.equals("error")) {
      JSONObject error_res = new JSONObject();
      error_res.put("status", 400);
      error_res.put("message", "Parameter missing or wrong parameters passed");
      callbackContext.success(error_res);
      return true;
    }

    if (action.equals("get")) {
      this.getHttp(callbackContext, args.getString(0));
      return true;
    }

    if (action.equals("auto")) {
       String key = args.getString(0);
       String url = BASE_REQ_URL + "/" + key + "/autosuggest?q=" + args.getString(1);
       this.getHttp(callbackContext, url);
       return true;
    }

    if (action.equals("atlas_auto")) {
       String clientId = args.getString(0);
       String clientSecret = args.getString(1);
       String query = args.getString(2);
       this.atlasAutoSuggest(callbackContext, clientId, clientSecret, query);
       return true;
    }

    if (action.equals("atlas_nearby")) {
       String clientId = args.getString(0);
       String clientSecret = args.getString(1);
       String keywords = args.getString(2);
       String refLocation = args.getString(3);
       this.atlasNearBy(callbackContext, clientId, clientSecret, keywords, refLocation);
       return true;
    }

    if (action.equals("geocode")) {
       String key = args.getString(0);
       String url = BASE_REQ_URL + "/" + key + "/geo_code?addr=" + args.getString(1);
       this.getHttp(callbackContext, url);
       return true;
    }

    if (action.equals("revGeocode")) {
       String lat = "27.61234";
       String lng = "77.61234";
       String key = args.getString(0);
       String url = BASE_REQ_URL + "/" + key + "/rev_geocode?lat=" + args.getString(1) + "&lng=" + args.getString(2);
       this.getHttp(callbackContext, url);
       return true;
    }

    if (action.equals("placeDetail")) {
       String key = args.getString(0);
       String url = BASE_REQ_URL + "/" + key + "/place_detail?place_id=" + args.getString(1);
       this.getHttp(callbackContext, url);
       return true;
    }

    if (action.equals("getNearByPlaces")) {
       String lat = "27.61234";
       String lng = "77.61234";
       String keyword = "atm";
       String key = args.getString(0);
       String url = BASE_REQ_URL + "/" + key + "/nearby_search?lat=" + args.getString(1) + "&lng=" + args.getString(2) + "&keywords=" + args.getString(3) + "&page=1";
       this.getHttp(callbackContext, url);
       return true;
    }

    if (action.equals("Routing")) {
       String Start = "28.111,77.111";
       String Destination = "28.22,77.22";
       String Alternatives = "true";
       String WithAdvices = "0";
       String key = args.getString(0);
       String url = BASE_REQ_URL + "/" + key + "/route?start=" + args.getString(1) + "&destination=" + args.getString(2) + "&alternatives=" + args.getString(3) + "&with_advices=" + args.getString(4);
       this.getHttp(callbackContext, url);
       return true;
    }

    if (action.equals("getDistance")) {
       String centerlat = "27.61234";
       String centerlng = "77.61234";
       String points = "29,78|30,78|28,79";
       String key = args.getString(0);
       String url = BASE_REQ_URL + "/" + key + "/distance?center=" + args.getString(1) + "," + args.getString(2) + "&pts=" + args.getString(3);
       this.getHttp(callbackContext, url);
       return true;
   }
  return false;
 }

 private void getHttp(CallbackContext callbackContext, String urlStr) {

  HttpURLConnection conn = null;
  JSONObject jsonResponse = null;
  String res = "";
  try {
   URL url = new URL(urlStr);
   conn = (HttpURLConnection) url.openConnection();
   conn.setConnectTimeout(9000);
   conn.setRequestMethod("GET");
   int status = conn.getResponseCode();
   InputStream in = new BufferedInputStream(conn.getInputStream());
   BufferedReader reader = null;
   StringBuilder response = new StringBuilder();
   try {
    reader = new BufferedReader(new InputStreamReader( in , "UTF-8"));
    String line = "";
    while ((line = reader.readLine()) != null) {
     response.append(line);
    }
   } catch (Exception e) {
    e.printStackTrace();
    res = "error in inputstream reading";
   } finally {
    if (reader != null) {
     try {
      reader.close();
     } catch (Exception e) {
      e.printStackTrace();
     }
    }
   }
   res = response.toString();
   jsonResponse = new JSONObject(res);
  } catch (Exception e) {
   res = "Parameter missing";
   e.printStackTrace();
  } finally {
   try {
    if (conn != null) {
     conn.disconnect();
    }
   } catch (Exception e) {
    e.printStackTrace();
   }
  }
  if (jsonResponse != null) {
   callbackContext.success(jsonResponse);
  } else {
   callbackContext.error("Error in HTTP Call..");
  }
 }

 public void setOAuth2AccessToken(String grantType, String clientId, String clientSecret) {
  try {
   Map < String, Object > params = new LinkedHashMap < String, Object > ();
   params.put("grant_type", grantType);
   params.put("client_id", clientId);
   params.put("client_secret", clientSecret);

   StringBuilder postData = new StringBuilder();
   for (Map.Entry < String, Object > param: params.entrySet()) {
    if (postData.length() != 0) {
     postData.append('&');
    }

    postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
    postData.append('=');
    postData.append(URLEncoder.encode(
     String.valueOf(param.getValue()), "UTF-8"));
   }
   byte[] postDataBytes = postData.toString().getBytes("UTF-8");
   HttpURLConnection conn = (HttpURLConnection) new URL(token_url).openConnection();
   conn.setRequestMethod("POST");
   conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
   conn.setRequestProperty("Content-Length",
   String.valueOf(postDataBytes.length));
   conn.setDoOutput(true);
   conn.getOutputStream().write(postDataBytes);

   StringBuilder responseStrBuilder = new StringBuilder();
   String res = "";
   BufferedReader reader = null;
   try {
    InputStream in = new BufferedInputStream(conn.getInputStream());
    reader = new BufferedReader(new InputStreamReader( in , "UTF-8"));
    String line = "";

    while ((line = reader.readLine()) != null) {
     responseStrBuilder.append(line);
    }
   } catch (Exception e) {
    e.printStackTrace();
    res = "error in inputstream reading";
   } finally {
    if (reader != null) {
     try {
      reader.close();
     } catch (Exception e) {
      e.printStackTrace();
     }
    }
   }

   res = responseStrBuilder.toString();
   JSONObject response = new JSONObject(res);
   if (response != null) {
    oauth2_token_type = (String) response.get("token_type");
    oauth2_access_token = (String) response.get("access_token");
    oauth2_token_validity = System.currentTimeMillis() / 1000 + Long.valueOf(response.get("expires_in").toString());
   }
  } catch (Exception e) {
   e.printStackTrace();
  }
 }

 public void atlasAutoSuggest(CallbackContext callbackContext, String clientId, String clientSecret, String query) {
  if (oauth2_access_token == null || oauth2_token_validity < System.currentTimeMillis() / 1000) {
   setOAuth2AccessToken("client_credentials", clientId, clientSecret);
  }
  String url = "https://atlas.mapmyindia.com/api/places/search/json?";
  try {
   String urlParams = "query=" + URLEncoder.encode(query, "UTF-8");
   String encodedUrl = url + urlParams;
   HttpURLConnection conn = (HttpURLConnection) new URL(encodedUrl).openConnection();
   conn.setRequestMethod("GET");
   conn.setRequestProperty("Authorization", oauth2_token_type + " " + oauth2_access_token);
   StringBuilder responseStrBuilder = new StringBuilder();
   String res = "";
   BufferedReader reader = null;
   try {
    InputStream in = new BufferedInputStream(conn.getInputStream());
    reader = new BufferedReader(new InputStreamReader( in , "UTF-8"));
    String line = "";

    while ((line = reader.readLine()) != null) {
     responseStrBuilder.append(line);
    }
   } catch (IOException e) {
    e.printStackTrace();
    res = "error in inputstream reading";
   } finally {
    if (reader != null) {
     try {
      reader.close();
     } catch (IOException e) {
      e.printStackTrace();
     }
    }
   }

   res = responseStrBuilder.toString();
   JSONObject response = new JSONObject(res);

   if (response != null) {
    callbackContext.success(response);
   }
  } catch (Exception e) {
   callbackContext.error("Something Went wrong");
  }
 }

 public void atlasNearBy(CallbackContext callbackContext, String clientId, String clientSecret, String keywords, String refLocation) {
  if (oauth2_access_token == null || oauth2_token_validity < System.currentTimeMillis() / 1000) {
   setOAuth2AccessToken("client_credentials", clientId, clientSecret);
  }
  String url = "https://atlas.mapmyindia.com/api/places/nearby/json?";
  try {
   String urlParams = "keywords=" + keywords + "&refLocation=" + refLocation;
   String encodedUrl = url + urlParams;
   HttpURLConnection conn = (HttpURLConnection) new URL(encodedUrl).openConnection();
   conn.setRequestMethod("GET");
   conn.setRequestProperty("Authorization", oauth2_token_type + " " + oauth2_access_token);
   StringBuilder responseStrBuilder = new StringBuilder();
   String res = "";
   BufferedReader reader = null;
   try {
    InputStream in = new BufferedInputStream(conn.getInputStream());
    reader = new BufferedReader(new InputStreamReader( in , "UTF-8"));
    String line = "";

    while ((line = reader.readLine()) != null) {
     responseStrBuilder.append(line);
    }
   } catch (IOException e) {
    e.printStackTrace();
    res = "error in inputstream reading";
   } finally {
    if (reader != null) {
     try {
      reader.close();
     } catch (IOException e) {
      e.printStackTrace();
     }
    }
   }

   res = responseStrBuilder.toString();
   JSONObject response = new JSONObject(res);

   if (response != null) {
    callbackContext.success(response);
   }
  } catch (Exception e) {
   callbackContext.error("Something went wrong"+e);
  }
 }
}
