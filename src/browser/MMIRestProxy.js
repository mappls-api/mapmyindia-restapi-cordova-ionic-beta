const API_URL = 'https://apis.mapmyindia.com/advancedmaps/v1';
const ATLAS_URL = "https://atlas.mapmyindia.com/api/places";
const err_msg = [{"status": 400, "message": "something went wrong"}];
var access_token = null;

function atlas_auto(success, error, opts){
    var url = ATLAS_URL+'/search/json?query='+opts[2];
    get_token(opts[0], opts[1], success, error, url);
}

function atlas_nearby(success, error, opts){
    var url = ATLAS_URL+'/nearby/json?keywords='+opts[2]+'&refLocation='+opts[3];
    get_token(opts[0], opts[1], success, error, url);
}

function autos(success, error, opts) {
    var url = API_URL+'/'+opts[0]+'/autosuggest?q='+opts[1];
    load(url,function (data) {
        success(data);
    }, function (err) {
        error(err);
    });
}

function revGeocode(success, error, opts) {
    var url = API_URL+'/'+opts[0]+'/rev_geocode?lat='+opts[1]+"&lng="+opts[2];
    load(url,function (data) {
        success(data);
    }, function (err) {
        error(err);
    });
}

function geocode(success, error, opts) {
    var url = API_URL+'/'+opts[0]+'/geo_code?addr='+opts[1];
    load(url,function (data) {
        success(data);
    }, function (err) {
        error(err);
    });
}

function placeDetail(success, error, opts) {
    var url = API_URL+'/'+opts[0]+'/place_detail?place_id='+opts[1];
    load(url,function (data) {
        success(data);
    }, function (err) {
        error(err);
    });
}

function getDistance(success, error, opts) {
    var url = API_URL+'/'+opts[0]+'/distance?center='+opts[1]+ "," + opts[2] +"&pts="+opts[3];
    load(url,function (data) {
        success(data);
    }, function (err) {
        error(err);
    });
}

function getNearByPlaces(success, error, opts) {
    var url = API_URL+'/'+opts[0]+'/nearby_search?lat='+opts[1]+"&lng="+ opts[2] +"&keywords="+opts[3];
    load(url,function (data) {
        success(data);
    }, function (err) {
        error(err);
    });
}

function Routing(success, error, opts) {
    var url = API_URL+'/'+opts[0]+'/route?start='+opts[1]+"&destination="+ opts[2] +"&alternatives="+opts[3]+"&with_advices="+opts[4];
    load(url,function (data) {
        success(data);
    }, function (err) {
        error(err);
    });
}
function get(success, error, opts) {
    load(opts[0],function (data) {
        success(data);
    }, function (err) {
        error(err);
    });
}

function get_token(client_id,client_secret, success, error, url){
  var xhttp= new XMLHttpRequest();
  try{
    xhttp.onreadystatechange = function(e) {
      if (xhttp.readyState == 4 && xhttp.status == 0) {
          alert("Unknown Error Occured. Server response not received.");
      }else if(xhttp.readyState == 4 && xhttp.status == 200){
          var rest = JSON.parse(e.target.responseText);
          var new_url = url+'&access_token='+rest.access_token;
          load(new_url,function (data) {
              success(data);
          }, function (err) {
              error(err);
          });
      }
    };
    xhttp.open("POST", "https://outpost.mapmyindia.com/api/security/oauth/token", true);
    xhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhttp.send("client_id="+client_id+"&client_secret="+client_secret+"&grant_type=client_credentials");
  }catch(e){
    console.log('catch', e);
  }
}

function load(url,success_callback, error_callback) {
var script,callback_name="response";
function after() {
  setTimeout(function () {
    document.getElementsByTagName("head")[0].removeChild(script);
    }, 1);
}
  script = document.createElement('script');
    window[callback_name] = function (response) {
    after();success_callback(response);
  };
  script.type = 'text/javascript';
  script.src = url+"&callback="+callback_name
  script.async = true;
  script.addEventListener('error', function () {
    after();error_callback();
  });
  document.getElementsByTagName("head")[0].appendChild(script);
}

module.exports = {
  autos,revGeocode, geocode, placeDetail, getDistance, getNearByPlaces, Routing, get, atlas_auto, atlas_nearby
};

require('cordova/exec/proxy').add('MMIRest', module.exports);
