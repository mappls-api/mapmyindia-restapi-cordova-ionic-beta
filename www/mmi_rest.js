/**
 *
 * Communicate with the Java, IOS and Javascript
 * According to the platform
 * @author  Anuj Singh
 * @version 1.0.0, 29/06/19
 * 
 */

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
    params.location = (params.location) ? params.location : '';
    params.zoom = (params.zoom) ? params.zoom : '';
    params.region = (params.region) ? params.region : '';
    params.tokenizeAddress = (params.tokenizeAddress) ? params.tokenizeAddress : '';
    params.pod = (params.pod) ? params.pod : '';
    params.filter = (params.filter) ? params.filter : '';
    cordova.exec(success, error, PLUGIN_NAME, 'atlas_auto', [params.client_id ,params.client_secret ,params.query.toString(), params.location, params.zoom, params.region, params.tokenizeAddress, params.pod, params.filter]);
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

var atlas_geocode = function(params ,success, error)
{
  if(params.addr && params.client_id && params.client_secret && success && error)
  {
    params.itemCount = (params.itemCount) ? params.itemCount : '';
    params.bias = (params.bias) ? params.bias : '';
    params.podFilter = (params.podFilter) ? params.podFilter : '';
    params.bound = (params.bound) ? params.bound : '';
    params.scores = (params.scores) ? 'true' : '';
    cordova.exec(success, error, PLUGIN_NAME, 'atlas_geocode', [params.client_id , params.client_secret ,params.addr.toString(), params.itemCount, params.bias, params.podFilter, params.bound, params.scores]);
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

var distance_new = function(params ,success, error)
{
   if(params.key && params.source && params.secondryLocations && success && error)
   {
      params.rtype = (params.rtype) ? params.rtype : '';
      params.region = (params.region) ? params.region : '';
      cordova.exec(success, error, PLUGIN_NAME, 'getDistanceNew', [params.key, params.source, params.secondryLocations, params.rtype, params.region]);
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

var route_new = function(params ,success, error){
  if(params.key && params.start && params.destination && success && error)
  {
     params.geometries = (params.geometries) ? params.geometries : '';
     params.steps = (params.steps) ? params.steps : '';
     params.exclude = (params.exclude) ? params.exclude : '';
     params.rtype = (params.rtype) ? params.rtype : '';
     params.region = (params.region) ? params.region : '';
     params.bearings = (params.bearings) ? params.bearings : '';
     params.alternatives = (params.alternatives) ? params.alternatives : '';
     params.radiuses = (params.radiuses) ? params.radiuses : '';
     params.overview = (params.overview) ? params.overview : '';

     cordova.exec(success, error, PLUGIN_NAME, 'Routing_V1', [params.key, params.start.toString(), params.destination.toString(), 
                                params.geometries, params.steps, params.exclude, params.rtype, params.region, params.bearings, params.alternatives, params.radiuses, params.overview]);
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
    auto, atlas_auto, atlas_nearby, geocode, place_detail,route, nearby_search, distance, rev_geocode, atlas_geocode, route_new, distance_new
}
