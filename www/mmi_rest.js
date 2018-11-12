var PLUGIN_NAME = "MMIRest";
var auto = function(params, success, error)
{
   if(params.query && params.key && success && error)
   {
      cordova.exec(success, error, PLUGIN_NAME, 'autos', [params.key, params.query.toString()]);
   }
   else
   {
      console.log("\x1b[31mError generated from MMI_REST_API_CRODOVA_PLUGIN: Paramater Missing, please put all the parameters\x1b[0m");
      cordova.exec(success, error, PLUGIN_NAME, 'error', null);
   }
};

var atlas_auto = function(params ,success, error)
{
  if(params.query && params.client_id && params.client_secret && success && error)
  {
    cordova.exec(success, error, PLUGIN_NAME, 'atlas_auto', [params.client_id ,params.client_secret ,params.query.toString()]);
  }
  else
  {
    console.log("\x1b[31mError generated from MMI_REST_API_CRODOVA_PLUGIN: Paramater Missing, please put all the parameters\x1b[0m");
    cordova.exec(success, error, PLUGIN_NAME, 'error', null);
  }
};

var atlas_nearby = function(params ,success, error)
{
  if(params.keywords && params.refLocation && params.client_id && params.client_secret && success && error)
  {
    cordova.exec(success, error, PLUGIN_NAME, 'atlas_nearby', [params.client_id , params.client_secret ,params.keywords.toString(), params.refLocation]);
  }
  else
  {
    console.log("\x1b[31mError generated from MMI_REST_API_CRODOVA_PLUGIN: Paramater Missing, please put all the parameters\x1b[0m");
    cordova.exec(success, error, PLUGIN_NAME, 'error', []);
  }
};

var geocode = function(params ,success, error)
{
  if(params.key && params.addr && success && error)
  {
    cordova.exec(success, error, PLUGIN_NAME, 'geocode', [params.key, params.addr.toString()]);
  }
  else
  {
    console.log("\x1b[31mError generated from MMI_REST_API_CRODOVA_PLUGIN: Paramater Missing, please put all the parameters\x1b[0m");
    cordova.exec(success, error, PLUGIN_NAME, 'error', []);
  }
};

var rev_geocode = function(params ,success, error)
{
  if(params.key && params.lat && params.lng && success && error)
  {
    cordova.exec(success, error, PLUGIN_NAME, 'revGeocode', [params.key, params.lat.toString(), params.lng.toString()]);
  }
  else
  {
    console.log("\x1b[31mError generated from MMI_REST_API_CRODOVA_PLUGIN: Paramater Missing, please put all the parameters\x1b[0m");
    cordova.exec(success, error, PLUGIN_NAME, 'error', []);
  }
};

var place_detail = function(params ,success, error)
{
   if(params.key && params.placeId && success && error)
   {
      cordova.exec(success, error, PLUGIN_NAME, 'placeDetail', [params.key, params.placeId.toString()]);
   }
   else
   {
     console.log("\x1b[31mError generated from MMI_REST_API_CRODOVA_PLUGIN: Paramater Missing, please put all the parameters\x1b[0m");
     cordova.exec(success, error, PLUGIN_NAME, 'error', []);
   }
};

var distance = function(params ,success, error)
{
   if(params.key && params.lat && params.lng && params.points && success && error)
   {
      cordova.exec(success, error, PLUGIN_NAME, 'getDistance', [params.key, params.lat.toString(), params.lng.toString(), params.points.toString()]);
   }
   else
   {
     console.log("\x1b[31mError generated from MMI_REST_API_CRODOVA_PLUGIN: Paramater Missing, please put all the parameters\x1b[0m");
     cordova.exec(success, error, PLUGIN_NAME, 'error', []);
   }
};

var nearby_search = function(params, success, error)
{
   if(params.key && params.lat && params.lng && params.keyword && success && error)
   {
      cordova.exec(success, error, PLUGIN_NAME, 'getNearByPlaces', [params.key.toString(), params.lat.toString(), params.lng.toString(), params.keyword.toString()]);
   }
   else
   {
     console.log("\x1b[31mError generated from MMI_REST_API_CRODOVA_PLUGIN: Paramater Missing, please put all the parameters\x1b[0m");
     cordova.exec(success, error, PLUGIN_NAME, 'error', []);
   }
};

var route = function(params ,success, error){
   if(params.key && params.start && params.destination && success && error)
   {
      params.alternatives = (params.alternatives) ? params.alternatives.toString() : 'true';
      params.advices = (params.advices) ? params.advices.toString() : '0';
      cordova.exec(success, error, PLUGIN_NAME, 'Routing', [params.key, params.start.toString(), params.destination.toString(), params.alternatives, params.advices]);
   }
   else
   {
      console.log("\x1b[31mError generated from MMI_REST_API_CRODOVA_PLUGIN: Paramater Missing, please put all the parameters\x1b[0m");
      cordova.exec(success, error, PLUGIN_NAME, 'error', []);
   }
};


var get = function(params, success, error){
    if(params.url && success && error)
    {
        cordova.exec(success, error, PLUGIN_NAME, 'get', [params.url]);
    }
    else
    {
        console.log("\x1b[31mError generated from MMI_REST_API_CRODOVA_PLUGIN: Paramater Missing, please put all the parameters\x1b[0m");
        cordova.exec(success, error, PLUGIN_NAME, 'error', []);
    }
};

module.exports = {
    auto, atlas_auto, atlas_nearby, geocode, place_detail,route, nearby_search, distance, rev_geocode
}
