# Appodeal Cordova Plugin

This is official Appodeal Cordova plugin, created to support Appodeal SDK with Apache Cordova and PhoneGap.

## SDK
[![](https://img.shields.io/badge/docs-android-green.svg)](http://www.appodeal.com/sdk/documentation?framework=9&full=1&platform=1)
[![](https://img.shields.io/badge/docs-ios-green.svg)](http://www.appodeal.com/sdk/documentation?framework=9&full=1&platform=2)

Appodeal Android SDK and Appodeal iOS SDK already included to the plugin.

## Install

Simply go to the project folder over console/terminal and run there following command:

    cordova plugin add https://github.com/appodeal/cordova-plugin.git

In PhoneGap you have several ways to add the plugin to your project.

To add the plugin with PhoneGap Build you need to add one of the following lines to your config.xml:
```
<plugin name="com.appodeal.plugin" source="npm" /> <!-- install from npm -->
<plugin spec="https://github.com/appodeal/cordova-plugin.git" source="git" /> <!-- install from git -->
```
To add the plugin with PhoneGap CLI, use one of the following commands:
```
phonegap plugin add com.appodeal.plugin #to install from npm
phonegap plugin add https://github.com/appodeal/cordova-plugin.git #to install from git
```

For Android You don't need to make something additional.

iOS SDK binnary is very heavy and we can't upload it to git or npm registry. We found a solution via cocoa pods.
Thats why iOS platform requires cocoa pods installed on your mac. And that's why it's impossible to build an iOS project with PhoneGap Build.

###Requirements:

####For iOS:

+ Mac OS based workstation
+ Cocoa pods installed on your Mac (follow [this](https://guides.cocoapods.org/using/getting-started.html) guide to install it)
+ XCode environment
 
####For Android:

+ Android SDK installed

After installation run:

    cordova platform update ios
    
this will update and install all required frameworks and dependencies required for Appodeal iOS SDK support.

Note that it is better to run project via newly created `.xcworkspace` in ios platform folder.

## AndroidManifest.xml
All required changes to AndroidManifest.xml are already included in the plugin

## Integration
### Ad types
Appodeal.INTERSTITIAL

Appodeal.SKIPPABLE_VIDEO

Appodeal.BANNER

Appodeal.REWARDED_VIDEO

Appodeal.NON_SKIPPABLE_VIDEO

Ad types can be combined using "|" operator. For example

Note that it is better to use NON_SKIPPABLE_VIDEO or REWARDED_VIDEO, but if you are sure you want to use SKIPPABLE_VIDEO you must confirm usage by calling Appodeal.confirm(Appodeal.SKIPPABLE_VIDEO) before SDK initialization

### SDK initialization

To initialize SDK, call the following code:
```
var appKey = "fee50c333ff3825fd6ad6d38cff78154de3025546d47a84f";
Appodeal.initialize(appKey, Appodeal.INTERSTITIAL | Appodeal.BANNER);
```
To initialize only interstitials use Appodeal.initialize(appKey, Appodeal.INTERSTITIAL)

To initialize only skippable videos use Appodeal.initialize(appKey, Appodeal.SKIPPABLE_VIDEO)

To initialize only rewarded video use Appodeal.initialize(appKey, Appodeal.REWARDED_VIDEO)

To initialize only non-skippable video use Appodeal.initialize(appKey, Appodeal.NON_SKIPPABLE_VIDEO)

To initialize interstitials and videos use Appodeal.initialize(appKey, Appodeal.INTERSTITIAL | Appodeal.SKIPPABLE_VIDEO)

To initialize only banners use Appodeal.initialize(appKey, Appodeal.BANNER)
### Check if Appodeal SDK was initialized
We recommend to initialize our SDK in the start screen of your application. It's required to initialize Appodeal SDK only once, so in this case, you no longer need to worry about it. 

But, if you want to implement a more complex algorithm, you can use the following method to check if Appodeal SDK was initialized:
```
Appodeal.isInitialized(function(result){});
```
result is a boolean value, that is indicates current initialization state.
### Display ad
```
Appodeal.show(adTypes);
```
It's possible to specify placement:
```
Appodeal.showWithPlacement(adType, (String)placementName);
```
To display interstitial use Appodeal.show(Appodeal.INTERSTITIAL)

To display skippable video use Appodeal.show(Appodeal.SKIPPABLE_VIDEO)

To display rewarded video use Appodeal.show(Appodeal.REWARDED_VIDEO)

To display non-skippable video use Appodeal.show(Appodeal.NON_SKIPPABLE_VIDEO)

To display interstitial or video use Appodeal.show(Appodeal.INTERSTITIAL | Appodeal.SKIPPABLE_VIDEO)

To display banner at the bottom of the screen use Appodeal.show(Appodeal.BANNER_BOTTOM)

To display banner at the top of the screen use Appodeal.show(Appodeal.BANNER_TOP)

Appodeal.show(adTypes, function(result){ // result is a boolean value, that is indicates whether show call was passed to appropriate SDK });

### Hiding banner
To hide banner you need to call the following code in activity:
```
Appodeal.hide(Appodeal.BANNER);
```
### Samples
#### Display interstitial after it was loaded
```
Appodeal.setAutoCache(Appodeal.INTERSTITIAL, false);
Appodeal.initialize(appKey, Appodeal.INTERSTITIAL);
Appodeal.cache(Appodeal.INTERSTITIAL);
Appodeal.enableInterstitialCallbacks(true);
document.addEventListener('onInterstitialLoaded', function(){
  Appodeal.show(Appodeal.INTERSTITIAL);
});
```

#### Display interstitial after app launch
```
Appodeal.initialize(appKey, Appodeal.INTERSTITIAL);
Appodeal.show(Appodeal.INTERSTITIAL);
```
### Advanced features
#### Enabling test mode
```
Appodeal.setTesting(true);
```
In test mode test ads will be shown and debug data will be written to logcat
#### Enabling logging
```
Appodeal.setLogging(true);
```
Enable debug logging to logcat using tag "Appodeal"
#### Checking if ad is loaded
```
Appodeal.isLoaded(adTypes, function(result){
  // result is a boolean value, that equals true if ad is loaded
});
```

To check if interstitial is loaded use Appodeal.isLoaded(Appodeal.INTERSTITIAL, function(result){})

To check if skippable video is loaded use Appodeal.isLoaded(Appodeal.SKIPPABLE_VIDEO, function(result){})

To check if rewarded video is loaded use Appodeal.isLoaded(Appodeal.REWARDED_VIDEO, function(result){})

To check if non-skippable video is loaded use Appodeal.isLoaded(Appodeal.NON_SKIPPABLE_VIDEO, function(result){})

To check if banner is loaded use Appodeal.isLoaded(Appodeal.BANNER, function(result){})

#### Checking if loaded ad is precache
```
Appodeal.isPrecache(adTypes, function(result){
  // result is a boolean value, that equals true if ad is precache
})
```
Currently supported only for interstitials and banners

To check if loaded interstitial is precache use Appodeal.isPrecache(Appodeal.INTERSTITIAL, function(result){});

To check if loaded banner is precache use Appodeal.isPrecache(Appodeal.BANNER, function(result){});

#### Setting Interstitial callbacks
```
Appodeal.enableInterstitialCallbacks(true);

document.addEventListener('onInterstitialLoaded', function(){});
document.addEventListener('onInterstitialFailedToLoad', function(){});
document.addEventListener('onInterstitialShown', function(){});
document.addEventListener('onInterstitialClicked', function(){});
document.addEventListener('onInterstitialClosed', function(){});
```
#### Setting skippable video callbacks
```
Appodeal.enableSkippableVideoCallbacks(true);

document.addEventListener('onSkippableVideoLoaded', function(){});
document.addEventListener('onSkippableVideoFailedToLoad', function(){});
document.addEventListener('onSkippableVideoShown', function(){});
document.addEventListener('onSkippableVideoFinished', function(){});
document.addEventListener('onSkippableVideoClosed', function(){});
```
#### Setting rewarded video callbacks
```
Appodeal.enableRewardedVideoCallbacks(true);

document.addEventListener('onRewardedVideoLoaded', function(){});
document.addEventListener('onRewardedVideoFailedToLoad', function(){});
document.addEventListener('onRewardedVideoShown', function(){});
document.addEventListener('onRewardedVideoFinished', function(data){
  console.log('Reward:' + data.amount + ' ' + data.name);  //data.amount  - amount of reward, data.name - reward name
});
document.addEventListener('onRewardedVideoClosed', function(){});
```
#### Setting non-skippable video callbacks
```
Appodeal.enableNonSkippableVideoCallbacks(true);

document.addEventListener('onNonSkippableVideoLoaded', function(){});
document.addEventListener('onNonSkippableVideoFailedToLoad', function(){});
document.addEventListener('onNonSkippableVideoShown', function(){});
document.addEventListener('onNonSkippableVideoFinished', function(){});
document.addEventListener('onNonSkippableVideoClosed', function(){});
```
#### Setting banner callbacks
```
Appodeal.enableBannerCallbacks(true);

document.addEventListener('onBannerClicked', function(){});
document.addEventListener('onBannerFailedToLoad', function(){});
document.addEventListener('onBannerLoaded', function(){});
document.addEventListener('onBannerShown', function(){});
```
#### Manual ad caching
```
Appodeal.cache(adTypes);
```

You should disable automatic caching before SDK initialization using setAutoCache(adTypes, false).

To cache interstitial use Appodeal.cache(Appodeal.INTERSTITIAL)

To cache skippable video use Appodeal.cache(Appodeal.SKIPPABLE_VIDEO)

To cache rewarded video use Appodeal.cache(Appodeal.REWARDED_VIDEO)

To cache interstitial and video use Appodeal.cache(Appodeal.INTERSTITIAL | Appodeal.SKIPPABLE_VIDEO)

To cache banner use Appodeal.cache(Appodeal.BANNER)

#### Enabling or disabling automatic caching
```
Appodeal.setAutoCache(adTypes, false);
```

Should be used before SDK initialization

To disable automatic caching for interstitials use Appodeal.setAutoCache(Appodeal.INTERSTITIAL, false)

To disable automatic caching for skippable videos use Appodeal.setAutoCache(Appodeal.SKIPPABLE_VIDEO, false)

To disable automatic caching for rewarded videos use Appodeal.setAutoCache(Appodeal.REWARDED_VIDEO, false)

To disable automatic caching for banners use Appodeal.setAutoCache(Appodeal.BANNER, false)

#### Triggering onLoaded callback twice
```
Appodeal.setOnLoadedTriggerBoth(adTypes, true);
```
Currently supported only for interstitials and banners

setOnLoadedTriggerBoth(Appodeal.INTERSTITIAL | Appodeal.BANNER, false); - onInterstitialLoaded and onBannerLoaded will trigger only when normal ad was loaded (default).

setOnLoadedTriggerBoth(Appodeal.INTERSTITIAL | Appodeal.BANNER, true); - onInterstitialLoaded and onBannerLoaded will trigger twice, both when precache and normal ad were loaded.

Should be used before SDK initialization

#### Disabling networks
```
Appodeal.disableNetwork((String)network);
```
```
Appodeal.disableNetworkType((String)name, adTypes);
```
Appodeal.disableNetwork("startapp") - completely disable startapp network.
Appodeal.disableNetworkType("startapp", Appodeal.BANNER|Appodeal.INTESTITIAL) - disable startapp only for BANNER and INTERSTITIAL ad types.

Available parameters: "amazon_ads", "applovin", "chartboost", "mopub", "unity_ads", "mailru", "facebook", "adcolony", "vungle", "yandex", "startapp", "avocarrot", "flurry", "pubnative", "cheetah", "inner-active", "revmob".

Should be used before SDK initialization

#### Disabling location permission check

To disable toast messages ACCESS_COARSE_LOCATION permission is missing use the following method:
```
Appodeal.disableLocationPermissionCheck();
```
Should be used before SDK initialization. It may have a significant impact on your income.

#### Disabling write external storage permission check.

To disable toast messages WRITE_EXTERNAL_STORAGE permission is missing use the following method:
```
Appodeal.disableWriteExternalStoragePermissionCheck();
```
Should be used before SDK initialization. It may have a significant impact on your income.

#### Disabling smart banners
```
Appodeal.setSmartBanners(false);
```
Should be used before SDK initialization.
#### Disabling 728x90 banners
```
Appodeal.set728x90Banners(false);
```
Should be used before SDK initialization.
#### Disable banner animation (smooth transitions)
```
Appodeal.setBannerAnimation(false);
```
Should be used before SDK initialization.

#### Show banner background
If you see some problems with banners displaying (transparent background or similar problem) you can enable banner background.
```
Appodeal.setBannerBackground(true);
```
It works only on iOS devices.

#### Set custom rule
Following methods avaiable to specify custom rule for using in segments:
```
Appodeal.setCustomBooleanRule((String)name, (boolean)value);
Appodeal.setCustomIntegerRule((String)name, (int)value);
Appodeal.setCustomDoubleRule((String)name, (double)value);
Appodeal.setCustomStringRule((String)name, (String)value);
```
#### S2S Reward Callbacks
To secure you apps economy we offer S2S reward callbaks. To validate each reward you need to setup callback URL on your server that will receive reward information. We will pass user data to your callback URL that you will need to validate and adjust user balance accordingly

1. Create reward callback URL on your server that will receive reward information
2. Fill created URL and encryption key in the app settings in your dashboard
3. Reward callback will be sent to your URL using GET request with two parameters:
   ```
   {http://example.com/reward}?data1={data1}&data2={data2}
   ```
4. Your URL should decrypt the data and validate it
5. Check impression_id for uniqueness and store it in your system to prevent duplicate transactions
6. We offer sample script in PHP, Ruby, Java and Python 3 to decrypt the data. If you need samples in other languages please contact our support and we will provide it for you.

Sample in PHP: [reward.php](https://s3-us-west-1.amazonaws.com/appodeal-android/reward/reward.php)

Sample in Ruby: [reward.rb](https://s3-us-west-1.amazonaws.com/appodeal-android/reward/reward.rb)

Sample in Java: [Reward.java](https://s3-us-west-1.amazonaws.com/appodeal-android/reward/Reward.java)

Sample in Python 3: [reward.py](https://s3-us-west-1.amazonaws.com/appodeal-android/reward/reward.py)

### Setting user data.
Our SDK provides the transfer of user data for better ad targeting and higher eCPM. All parameters are optional and can be defined partially.
#### Set the age of the user
Positive integer value.
```
Appodeal.setAge(25);
```
#### Set birth date
```
Appodeal.setBirthday("17/06/1990");
```
birthday in format "DD/MM/YYYY" or "MM/YYYY" or "YYYY"
#### Set user email
```
Appodeal.setEmail("hi@appodeal.com");
```
#### Specify gender of the user
```
Appodeal.setGender("female");
```
Possible values: "female", "male", "other".
#### Set interests of the user
```
Appodeal.setInterests("reading, games, movies, snowboarding");
```
#### Specify occupation of the user
```
Appodeal.setOccupation("work");
```
Possible values: "school", "university", "work", "other".

#### Specify marital status of the user
```
Appodeal.setRelation("dating");
```
Possible values: "single", "searching", "dating", "engaged", "married", "other".

#### Set drinking habits of the user
```
Appodeal.setAlcohol("negative");
```
Possible values: "negative", "neitral", "positive".

#### User attitude to smoking
```
Appodeal.setSmoking("negative");
```
Possible values: "negative", "neitral", "positive".

#### User ID
In order to use in S2S Reward callbacks.
```
Appodeal.setUserId("your custom user id");
```

## Troubleshooting

Sometimes cordova can throw error like this `ERROR: Plugin 'CDVOP' not found, or is not a CDVPlugin.` with ios plugin.
You can solve this by running following commands:

    cordova platform remove ios
    cordova plugin remove com.appodeal.plugin
    cordova platform add ios
    cordova plugin add https://github.com/appodeal/cordova-plugin.git
    cordova build ios
    
## Changelog

2.0.0 (05.09.2016)

+ iOS Support via cocoa pods
+ Appodeal Android SDK updated to 1.15.2
+ Android libraries and frameworks updated
+ Small bugfixes
