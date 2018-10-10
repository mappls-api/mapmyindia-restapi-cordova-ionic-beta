# MapmyIndia REST APIs for Cordova/Ionic - Beta 1

[![N|Solid](https://www.mapmyindia.com/api/img/mapmyindia-api.png)](https://www.mapmyindia.com/api/)

For full documentation contact MapmyIndia here:
[MapmyIndia: apisupport@mapmyindia.co.in](mailto:apisupport@mapmyindia.co.in).
You can get your api key to be used in this document here: [https://www.mapmyindia.com/api/signup](https://www.mapmyindia.com/api/signup)

## Version History

| Version | Last Updated | Author |
| ---- | ---- | ---- |
| 0.0.7 | October 2018 | MapmyIndia API Team ([AS](https://github.com/anujsinghwd)) |

## Technologies	Used

 1. [Java](https://www.java.com/en/download/)
 2. [Javascript](https://developer.mozilla.org/bm/docs/Web/JavaScript)
 3. [Cordova](https://cordova.apache.org/)

## Supported Technologies

 1. [Ionic](https://ionicframework.com/)
 2. [Cordova](https://cordova.apache.org/)
 3. [Phonegap](https://phonegap.com/)

## Installation

> MapmyIndia Plugin requires Cordova to	run.
> This plugin will work with only **Android** Devices

- From NPM
```js
  $ cordova plugin add @mapmyindia/mapmyindia-restapi-cordova-ionic-beta
```
- From Github
```js
  $ cordova plugin add https://github.com/mapmyindia/mapmyindia-restapi-cordova-ionic-beta.git
```
- Declare window var globally
```js
  $ declare var window;
```
> Inside `window.plugins.mmi_rest` function if you want	to access this property	you have to assign to a	variable like
> `var	thisObj	= this` outside	the `window.plugins.mmi_rest` function	body if you are	working	with `IONIC V > 1`

### Check if it’s Installed or not

```javascript
if(window.plugins)
{
    console.log('working YAY!');
}
else
{
    console.log('Not Working!');
}
```
## API	Usage


### Features:

 1. [Autosuggest](#autosuggest)
 2. [Geocoding](#geocode)
 3. [Nearby](#nearby)
 4. [Reverse Geocode](#reverse-geocode)
 5. [Place detail](#place-details--eloc)
 6. [Distance](#driving-distance-matrix)
 7. [Routing](#routing)

#### Autosuggest

##### Parameters

- `key*`
- `client_id*`
- `client_secret*`
- `query*`
- `successCallback*`
- `errorCallback*`

##### Syntax

```js
window.plugins.mmi_rest.atlas_auto(
    	{
    	  client_id: 'clientId',
    	  client_secret: 'clientSecret',
    	  query: 'agr'
    	 },SuccessCallback, Error Callback);
```
##### Example

```js
window.plugins.mmi_rest.atlas_auto({client_id: 'clientId', client_secret: 'clientSecret',query: 'agr'},
    function(result)
    {
    	console.log(result);
    },
    function(error)
    {
    	console.log(error);
});
```


#### Geocode

##### Parameters
- `key*`
- `addr*`
- `successCallback*`
- `errorCallback*`

##### Syntax

```js
	window.plugins.mmi_rest.geocode(key: 'YOUR API KEY', addr: 'address'}, Success Callback, Error Callback);
```
##### Example

```js
window.plugins.mmi_rest.geocode({key: 'YOUR API KEY', addr: 'lucknow'},
  function(result){
      console.log(JSON.stringify(result));
  },
  function(err){
     console.log(err);
});
```

#### Nearby

##### Parameters

- `key*`
- `client_id*`
- `client_secret*`
- `keywords*`
- `refLocation*`
- `successCallback*`
- `errorCallback*`

##### Syntax

```js
window.plugins.mmi_rest.atlas_nearby({client_id: 'clientId', client_secret: 'clientSecret', keywords: 'beer', refLocation: '28.631460,77.217423'}, SuccessCallback, ErrorCallback);
```
##### Example

```js
window.plugins.mmi_rest.atlas_nearby({client_id: 'clientId',client_secret: 'clientSecret',keywords: 'beer',refLocation: '28.631460,77.217423'},
  function(result)
  {
  	console.log(JSON.stringify(result));
  },
  function(error)
  {
  	console.log(error);
  });
```

#### Reverse Geocode

##### Parameters

- `key*`
- `lat*`
- `lng*`
- `successCallback*`
- `errorCallback*`

##### Syntax

```js
window.plugins.mmi_rest.rev_geocode(key: 'YOUR API KEY', lat: '27.61234', lng:	'77.61234'}, Success Callback, Error Callback);
```
##### Example

```js
window.plugins.mmi_rest.rev_geocode({key: 'YOUR	API KEY', lat: '27.61234', lng: '77.61234'},
function(result){
    console.log(JSON.stringify(result));
},
function(err){
     console.log(err);
});
```
#### Place Details / eLoc

##### Parameters

- `key*`
- `placeId*`
- `successCallback*`
- `errorCallback*`

##### Syntax

```js
window.plugins.mmi_rest.place_detail({key: 'YOUR API KEY', placeId: 'MMI000'}, Success Callback, Error Callback);
```
##### Example
```js
window.plugins.mmi_rest.place_detail({key: 'YOUR API KEY', placeId: 'MMI000'},
function(result)
{
    console.log(JSON.stringify(result));
},
function(err)
{
    console.log(err);
});
```
#### Driving Distance Matrix

##### Parameters


- `key*`
- `lat*`
- `lng*`
- `points*`
- `successCallback*`
- `errorCallback*`

##### Syntax

```js
window.plugins.mmi_rest.distance({key: 'YOUR API KEY', lat: '27.61234', lng: '77.61234', points:'29,78|30,78|28,79'}, Success Callback, ErrorCallback);
```
##### Example

```js
window.plugins.mmi_rest.distance({key: 'YOUR API KEY', lat: '27.61234', lng: '77.61234', points: '29,78|30,78|28,79'},
function(result)
{
    console.log(JSON.stringify(result));
},
function(err)
{
    console.log(err);
});
```
#### Routing

##### Parameters

Note: "alternatives" and "advices" are optional	parameters; send `null` if they are empty.

- `key*`
- `start*`
- `destination*`
- `successCallback*`
- `errorCallback*`

##### Syntax

```js
window.plugins.mmi_rest.route({key: 'YOUR API KEY', start: '28.111,77.111', destination: '28.22,77.22', alternatives: null,	advices: null},	SuccessCallback, ErrorCallback);
```
##### Example

```js
window.plugins.mmi_rest.route({key: 'YOUR API KEY', start: '28.111,77.111', destination: '28.22,77.22',	alternatives: null,	advices: null},
function(result)
{
    console.log(JSON.stringify(result));
},
function(err)
{
    console.log(JSON.stringify(err));
});
```


#### How to generate an HTTP GET Request

```js
window.plugins.mmi_rest.get(url, Success Callback, Error Callback);
```

For any queries and support, please contact:

![Email](https://www.google.com/a/cpanel/mapmyindia.co.in/images/logo.gif?service=google_gsuite)
Email us at [apisupport@mapmyindia.co.in](mailto:apisupport@mapmyindia.co.in)

![](https://www.mapmyindia.com/api/img/icons/stack-overflow.png)
[Stack Overflow](https://stackoverflow.com/questions/tagged/mapmyindia-api)
Ask a question under the mapmyindia-api

![](https://www.mapmyindia.com/api/img/icons/support.png)
[Support](https://www.mapmyindia.com/api/index.php#f_cont)
Need support? contact us!

![](https://www.mapmyindia.com/api/img/icons/blog.png)
[Blog](http://www.mapmyindia.com/blog/)
Read about the latest updates & customer stories


> © Copyright 2018. CE Info Systems Pvt. Ltd. All Rights Reserved. | [Terms & Conditions](http://www.mapmyindia.com/api/terms-&-conditions)
>  Written with [StackEdit](https://stackedit.io/) by MapmyIndia.
