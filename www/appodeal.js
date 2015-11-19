
	var Appodeal = exports;

	var exec = require('cordova/exec');
	var cordova = require('cordova');
	
	Appodeal.INTERSTITIAL = 1;
	Appodeal.VIDEO = 2;
	Appodeal.BANNER = 4;
	Appodeal.BANNER_BOTTOM = 8;
	Appodeal.BANNER_TOP    = 16;
	Appodeal.BANNER_CENTER = 32;
	Appodeal.BANNER_VIEW   = 64;
	Appodeal.REWARDED_VIDEO = 128;
	Appodeal.ANY = 255;
	Appodeal.ALL = 255;

	Appodeal.initialize = function(appKey, adType) {
		exec(null, null, "AppodealPlugin", "initialize", [appKey, adType]);
	}

	Appodeal.enableInterstitialCallbacks = function(listener) {
		exec(null, null, "AppodealPlugin", "enableIntertitialCallbacks", [listener]);
	}

	Appodeal.enableVideoCallbacks = function(listener) {
		exec(null, null, "AppodealPlugin", "enableVideoCallbacks", [listener]);
	}

	Appodeal.enableBannerCallbacks = function(listener) {
		exec(null, null, "AppodealPlugin", "enableBannerCallbacks", [listener]);
	}
	
	Appodeal.enableRewardedVideoCallbacks = function(listener) {
		exec(null, null, "AppodealPlugin", "enableRewardedVideoCallbacks", [listener]);
	}

	Appodeal.isLoaded = function(adType, callback) {
		exec(function(e){
			if(typeof callback=='function') {
				if(e==1){callback(true);
			} else {
				callback(false);}
			}
		}, null, "AppodealPlugin", "isLoaded", [adType]);
	}

	Appodeal.isPrecache = function(adType, callback) {
		exec(function(e){if(typeof callback=='function'){if(e==1){callback(true);}else{callback(false);}}}, null, "AppodealPlugin", "isPrecache", [adType]);
	}

	Appodeal.show = function(adType, callback) {
		exec(function(e){if(typeof callback=='function'){if(e==1){callback(true);}else{callback(false);}}}, null, "AppodealPlugin", "show", [adType]);
	}

	Appodeal.hide = function(adType) {
		exec(null, null, "AppodealPlugin", "hide", [adType]);
	}

	Appodeal.showWithPriceFloor = function(adType, callback) {
		exec(function(e){if(typeof callback=='function'){if(e==1){callback(true);}else{callback(false);}}}, null, "AppodealPlugin", "showWithPriceFloor", [adType]);
	}

	Appodeal.setAutoCache = function(adType, autoCache) {
		exec(null, null, "AppodealPlugin", "setAutoCache", [adType, autoCache]);
	}

	Appodeal.cache = function(adType) {
		exec(null, null, "AppodealPlugin", "cacheBanner", [adType]);
	}

	Appodeal.setOnLoadedTriggerBoth = function(adType, setTrigger) {
		exec(null, null, "AppodealPlugin", "setOnLoadedTriggerBoth", [adType, setTrigger]);
	}

	Appodeal.disableNetwork = function(network) {
		exec(null, null, "AppodealPlugin", "disableNetwork", [network]);
	}

	Appodeal.disableLocationPermissionCheck = function() {
		exec(null, null, "AppodealPlugin", "disableLocationCheck", []);
	}
