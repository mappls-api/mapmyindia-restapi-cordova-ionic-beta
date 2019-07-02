/*
 *
 * In addition, this class provides all methods 
 * of the mapmyindia rest apis
 * @author  Anuj Singh
 * @version 1.0.0, 29/06/19
 * 
 */

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

public class MMIRest extends CordovaPlugin {

 private final static String BASE_REQ_URL = "https://apis.mapmyindia.com/advancedmaps/v1";
 private final static String ATLAS_REQ_URL = "https://atlas.mapmyindia.com/api/places";
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
       String urlParams = "";
       String clientId = args.getString(0);
       String clientSecret = args.getString(1);
       String query = args.getString(2);
       String location = args.getString(3);
       String zoom = args.getString(4);
       String region = args.getString(5);
       String tokenizeAddress = args.getString(6);
       String pod = args.getString(7);
       String filter = args.getString(8);

       try {
         String url = ATLAS_REQ_URL +"/search/json?";
         urlParams = "query=" + URLEncoder.encode(query, "UTF-8");
         if(location != null && !location.isEmpty())
         {
            urlParams = urlParams + "&location="+ location;
         }
         if(zoom != null && !zoom.isEmpty())
         {
            urlParams = urlParams + "&zoom="+ zoom;
         }
         if(region != null && !region.isEmpty())
         {
            urlParams = urlParams + "&region="+ region;
         }
         if(tokenizeAddress != null && !tokenizeAddress.isEmpty())
         {
            urlParams = urlParams + "&tokenizeAddress";
         }
         if(pod != null && !pod.isEmpty())
         {
            urlParams = urlParams + "&pod="+ pod;
         }
         if(filter != null && !filter.isEmpty())
         {
            urlParams = urlParams + "&filter="+ filter;
         }
         String encodedUrl = url + urlParams;
         this.atlasRequest(callbackContext, clientId, clientSecret, encodedUrl);
       }catch (Exception e) {
         JSONObject error_res = new JSONObject();
         error_res.put("status", 400);
         error_res.put("message", "something went wrong");
         callbackContext.error(error_res);
       }
       return true;
    }

    if (action.equals("atlas_nearby")) {
       String clientId = args.getString(0);
       String clientSecret = args.getString(1);
       String keywords = args.getString(2);
       String refLocation = args.getString(3);
       try{
         String url = ATLAS_REQ_URL + "/nearby/json?";
         String urlParams = "keywords=" + keywords + "&refLocation=" + refLocation;
         String encodedUrl = url + urlParams;
         this.atlasRequest(callbackContext, clientId, clientSecret, encodedUrl);
       }catch (Exception e) {
         JSONObject error_res = new JSONObject();
         error_res.put("status", 400);
         error_res.put("message", "something went wrong");
         callbackContext.error(error_res);
       }
       return true;
    }

    if (action.equals("atlas_geocode")) {
      String urlParams = "";
      String clientId = args.getString(0);
      String clientSecret = args.getString(1);
      String addr = args.getString(2);
      String itemCount = args.getString(3);
      String bias = args.getString(4);
      String podFilter = args.getString(5);
      String bound = args.getString(6);
      String scores = args.getString(7);

      try{
        String url = ATLAS_REQ_URL + "/geocode?address=" + addr;
        if(itemCount != null && !itemCount.isEmpty())
        {
            urlParams = urlParams + "&itemCount="+ itemCount;
        }
        if(bias != null && !bias.isEmpty())
        {
            urlParams = urlParams + "&bias="+ bias;
        }
        if(podFilter != null && !podFilter.isEmpty())
        {
            urlParams = urlParams + "&podFilter="+ podFilter;
        }
        if(bound != null && !bound.isEmpty())
        {
            urlParams = urlParams + "&bound="+ bound;
        }
        if(scores != null && !scores.isEmpty())
        {
            urlParams = urlParams + "&scores";
        }
        String encodedUrl = url + urlParams;
        this.atlasRequest(callbackContext, clientId, clientSecret, encodedUrl);
      }catch (Exception e) {
        JSONObject error_res = new JSONObject();
        error_res.put("status", 400);
        error_res.put("message", "something went wrong");
        callbackContext.error(error_res);
      }
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

    if (action.equals("Routing_V1")) {
      String urlParams = "";
      String key = args.getString(0);
      String start = args.getString(1);
      String destination = args.getString(2);
      String geometries = args.getString(3);
      String steps = args.getString(4);
      String exclude = args.getString(5);
      String rtype = args.getString(6);
      String region = args.getString(7);
      String bearings = args.getString(8);
      String alternatives = args.getString(9);
      String radiuses = args.getString(10);
      String overview = args.getString(11);
      if(geometries != null && !geometries.isEmpty())
      {
         urlParams = urlParams + "&geometries="+ geometries;
      }
      if(steps != null && !steps.isEmpty())
      {
         urlParams = urlParams + "&steps="+ steps;
      }
      if(exclude != null && !exclude.isEmpty())
      {
         urlParams = urlParams + "&exclude="+ exclude;
      }
      if(rtype != null && !rtype.isEmpty())
      {
         urlParams = urlParams + "&rtype="+ rtype;
      }
      if(region != null && !region.isEmpty())
      {
         urlParams = urlParams + "&region="+ region;
      }
      if(bearings != null && !bearings.isEmpty())
      {
         urlParams = urlParams + "&bearings="+ bearings;
      }
      if(alternatives != null && !alternatives.isEmpty())
      {
         urlParams = urlParams + "&alternatives="+ alternatives;
      }
      if(radiuses != null && !radiuses.isEmpty())
      {
         urlParams = urlParams + "&radiuses="+ radiuses;
      }
      if(overview != null && !overview.isEmpty())
      {
         urlParams = urlParams + "&overview="+ overview;
      }

      //String url = BASE_REQ_URL + "/" + key + "/route?start=" + args.getString(1) + "&destination=" + args.getString(2) + "&alternatives=" + args.getString(3) + "&with_advices=" + args.getString(4);
      String url = BASE_REQ_URL + "/" + key + "/route_adv/driving/"+ start + ";" + destination+ "?" + urlParams;

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

   if (action.equals("getDistanceNew")) {
      String urlParams = "";
      String key = args.getString(0);
      String source = args.getString(1);
      String secondryLocation = args.getString(2);
      String rtype = args.getString(3);
      String region = args.getString(4);
      if(rtype != null && !rtype.isEmpty())
      {
         urlParams = urlParams + "&rtype="+ rtype;
      }
      if(region != null && !region.isEmpty())
      {
         urlParams = urlParams + "&region="+ region;
      }
      String url = BASE_REQ_URL + "/" + key + "/distance_matrix/driving/"+ source + ";" + secondryLocation + "?" + urlParams;
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
    try{
      JSONObject error_res = new JSONObject();
      error_res.put("status", 400);
      error_res.put("message", "Error in HTTP Call. please try after sometime");
      callbackContext.success(error_res);
    }catch (Exception e) {
     e.printStackTrace();
    }

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



 public void atlasRequest(CallbackContext callbackContext, String clientId, String clientSecret, String encodedUrl) {
  if (oauth2_access_token == null || oauth2_token_validity < System.currentTimeMillis() / 1000) {
   setOAuth2AccessToken("client_credentials", clientId, clientSecret);
  }
  try {
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
}
