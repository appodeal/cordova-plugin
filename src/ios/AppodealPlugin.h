#import <Cordova/CDV.h>
#import <Appodeal/Appodeal.h>

@interface AppodealPlugin : CDVPlugin <AppodealInterstitialDelegate, AppodealBannerDelegate, AppodealVideoDelegate, AppodealRewardedVideoDelegate>

- (void) initialize:(CDVInvokedUrlCommand*)command;
- (void) setTesting:(CDVInvokedUrlCommand*)command;
- (void) setLogging:(CDVInvokedUrlCommand*)command;
- (void) enableInterstitialCallbacks:(CDVInvokedUrlCommand*)command;
- (void) enableSkippableVideoCallbacks:(CDVInvokedUrlCommand*)command;
- (void) enableBannerCallbacks:(CDVInvokedUrlCommand*)command;
- (void) enableRewardedVideoCallbacks:(CDVInvokedUrlCommand*)command;
- (void) isLoaded:(CDVInvokedUrlCommand*)command;
- (void) confirm:(CDVInvokedUrlCommand*)command;
- (void) show:(CDVInvokedUrlCommand*)command;
- (void) hide:(CDVInvokedUrlCommand*)command;
- (void) cache:(CDVInvokedUrlCommand*)command;
- (void) setAutoCache:(CDVInvokedUrlCommand*)command;
- (void) disableNetwork:(CDVInvokedUrlCommand*)command;
- (void) disableNetworkType:(CDVInvokedUrlCommand*)command;
- (void) disableLocationCheck:(CDVInvokedUrlCommand*)command;

@end