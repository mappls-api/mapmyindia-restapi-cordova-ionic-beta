var PLUGIN_NAME = "Setter";
// Act as constructor
function MMI_Rest() {}

MMI_Rest.prototype.auto = function(params, success, error)
{
   if(params.query && params.key && success && error)
   {
      cordova.exec(success, error, PLUGIN_NAME, 'auto', [params.key, params.query.toString()]);
   }
   else
   {
      console.log("\x1b[31mError generated from MMI_REST_API_CRODOVA_PLUGIN: Paramater Missing, please put all the parameters\x1b[0m");
      cordova.exec(success, error, PLUGIN_NAME, 'error', null);
   }
}

MMI_Rest.prototype.atlas_auto = function(params ,success, error)
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
}

MMI_Rest.prototype.atlas_nearby = function(params ,success, error)
{
  if(params.keywords && params.refLocation && params.client_id && params.client_secret && success && error)
  {
    cordova.exec(success, error, PLUGIN_NAME, 'atlas_nearby', [params.client_id , params.client_secret ,params.keywords.toString(), params.refLocation.toString()]);
  }
  else
  {
    console.log("\x1b[31mError generated from MMI_REST_API_CRODOVA_PLUGIN: Paramater Missing, please put all the parameters\x1b[0m");
    cordova.exec(success, error, PLUGIN_NAME, 'error', []);
  }
}

MMI_Rest.prototype.geocode = function(params ,success, error)
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
}

MMI_Rest.prototype.rev_geocode = function(params ,success, error)
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
}

MMI_Rest.prototype.place_detail = function(params ,success, error)
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
}

MMI_Rest.prototype.distance = function(params ,success, error)
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
}

MMI_Rest.prototype.nearby_search = function(params, success, error)
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
}

MMI_Rest.prototype.route = function(params ,success, error){
   if(params.key && params.start && params.destination && success && error)
   {
      var alternatives = (params.alternatives) ? params.alternatives.toString() : null;
      var advices = (params.advices) ? params.advices.toString() : null;
      cordova.exec(success, error, PLUGIN_NAME, 'Routing', [params.key, params.start.toString(), params.destination.toString(), alternatives, advices]);
   }
   else
   {
      console.log("\x1b[31mError generated from MMI_REST_API_CRODOVA_PLUGIN: Paramater Missing, please put all the parameters\x1b[0m");
      cordova.exec(success, error, PLUGIN_NAME, 'error', []);
   }
}

MMI_Rest.prototype.get = function(params, success, error){
    if(params.url && success && error)
    {
        cordova.exec(success, error, PLUGIN_NAME, 'get', [params.url]);
    }
    else
    {
        console.log("\x1b[31mError generated from MMI_REST_API_CRODOVA_PLUGIN: Paramater Missing, please put all the parameters\x1b[0m");
        cordova.exec(success, error, PLUGIN_NAME, 'error', []);
    }
}
// Installation constructor that binds MMI_Rest to window
MMI_Rest.install = function() {
  if (!window.plugins) {
    window.plugins = {};
  }
  window.plugins.mmi_rest = new MMI_Rest();
  return window.plugins.mmi_rest;
};

// Bind all plugin to window object
cordova.addConstructor(MMI_Rest.install);
