#Appodeal Cordova Plugin

This is official Appodeal Cordova plugin, created to support Appodeal SDK with Apache Cordova.

##SDK
[![](https://img.shields.io/badge/docs-android-green.svg)](http://www.appodeal.com/sdk/documentation?framework=9&full=1&platform=1)
[![](https://img.shields.io/badge/docs-ios-green.svg)](http://www.appodeal.com/sdk/documentation?framework=9&full=1&platform=2)

Appodeal Android SDK and Appodeal iOS SDK already included to the plugin.

##Install

Simply go to the project folder over console/terminal and run there following command:

    cordova plugin add https://github.com/appodeal/cordova-plugin.git

For Android You don't need to make something additional.

iOS SDK binnary is very heavy and we can't upload it to git or npm registry. We found a solution via cocoa pods.
Thats why iOS platform requires cocoa pods installed on your mac.

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


##Troubleshooting

Sometimes cordova can throw error like this `ERROR: Plugin 'CDVOP' not found, or is not a CDVPlugin.` with ios plugin.
You can solve this by running following commands:

    cordova platform remove ios
    cordova plugin remove com.appodeal.plugin
    cordova platform add ios
    cordova plugin add https://github.com/appodeal/cordova-plugin.git
    cordova build ios
    
##Changelog

2.0.0 (05.09.2016)

+ iOS Support via cocoa pods
+ Appodeal Android SDK updated to 1.15.2
+ Android libraries and frameworks updated
+ Small bugfixes
