#import "AppodealPlugin.h"
#import <UIKit/UIKit.h>

const int INTERSTITIAL        = 1;
const int VIDEO               = 2;
const int BANNER              = 4;
const int BANNER_BOTTOM       = 8;
const int BANNER_TOP          = 16;
const int REWARDED_VIDEO      = 128;
const int NON_SKIPPABLE_VIDEO = 128;

int nativeAdTypesForType(int adTypes) {
    int nativeAdTypes = 0;
    
    if ((adTypes & INTERSTITIAL) > 0) {
        nativeAdTypes |= AppodealAdTypeInterstitial;
    }
    
    if ((adTypes & VIDEO) > 0) {
        nativeAdTypes |= AppodealAdTypeSkippableVideo;
    }
    
    if ((adTypes & BANNER) > 0 ||
        (adTypes & BANNER_TOP) > 0 ||
        (adTypes & BANNER_BOTTOM) > 0) {
        
        nativeAdTypes |= AppodealAdTypeBanner;
    }
    
    if ((adTypes & REWARDED_VIDEO) > 0) {
        nativeAdTypes |= AppodealAdTypeRewardedVideo;
    }

    if ((adTypes & NON_SKIPPABLE_VIDEO) >0) {
        nativeAdTypes |= AppodealAdTypeNonSkippableVideo;
    }
    
    return nativeAdTypes;
}

int nativeShowStyleForType(int adTypes) {
    bool isInterstitial = (adTypes & INTERSTITIAL) > 0;
    bool isVideo = (adTypes & VIDEO) > 0;
    
    if (isInterstitial && isVideo) {
        return AppodealShowStyleVideoOrInterstitial;
    } else if (isVideo) {
        return AppodealShowStyleSkippableVideo;
    } else if (isInterstitial) {
        return AppodealShowStyleInterstitial;
    }
    
    if ((adTypes & BANNER_TOP) > 0) {
        return AppodealShowStyleBannerTop;
    }
    
    if ((adTypes & BANNER_BOTTOM) > 0) {
        return AppodealShowStyleBannerBottom;
    }
    
    if ((adTypes & REWARDED_VIDEO) > 0) {
        return AppodealShowStyleRewardedVideo;
    }

    if ((adTypes & NON_SKIPPABLE_VIDEO) > 0) {
        return AppodealShowStyleNonSkippableVideo;
    }
    
    return 0;
}

@implementation AppodealPlugin

//banner callbacks
- (void)bannerDidLoadAd
{
    [self.webViewEngine evaluateJavaScript:@"cordova.fireDocumentEvent('onBannerLoaded')" completionHandler:nil];
}

- (void)bannerDidFailToLoadAd
{
    [self.webViewEngine evaluateJavaScript:@"cordova.fireDocumentEvent('onBannerFailedToLoad')" completionHandler:nil];
}

- (void)bannerDidClick
{
    [self.webViewEngine evaluateJavaScript:@"cordova.fireDocumentEvent('onBannerClicked')" completionHandler:nil];
}

- (void)bannerDidShow
{
    [self.webViewEngine evaluateJavaScript:@"cordova.fireDocumentEvent('onBannerDidShow')" completionHandler:nil];
}

//interstitial callbacks
- (void)interstitialDidLoadAd
{
    [self.webViewEngine evaluateJavaScript:@"cordova.fireDocumentEvent('onInterstitialLoaded')" completionHandler:nil];
}

- (void)interstitialDidFailToLoadAd
{
    [self.webViewEngine evaluateJavaScript:@"cordova.fireDocumentEvent('onInterstitialFailedToLoad')" completionHandler:nil];
}

- (void)interstitialWillPresent
{
    [self.webViewEngine evaluateJavaScript:@"cordova.fireDocumentEvent('onInterstitialShown')" completionHandler:nil];
}

- (void)interstitialDidDismiss
{
    [self.webViewEngine evaluateJavaScript:@"cordova.fireDocumentEvent('onInterstitialClosed')" completionHandler:nil];
}

- (void)interstitialDidClick
{
    [self.webViewEngine evaluateJavaScript:@"cordova.fireDocumentEvent('onInterstitialClicked')" completionHandler:nil];
}

//video callbacks
- (void)videoDidLoadAd
{
    [self.webViewEngine evaluateJavaScript:@"cordova.fireDocumentEvent('onSkippableVideoLoaded')" completionHandler:nil];
}

- (void)videoDidFailToLoadAd
{
    [self.webViewEngine evaluateJavaScript:@"cordova.fireDocumentEvent('onSkippableVideoFailedToLoad')" completionHandler:nil];
}

- (void)videoDidPresent
{
    [self.webViewEngine evaluateJavaScript:@"cordova.fireDocumentEvent('onSkippableVideoShown')" completionHandler:nil];
}

- (void)videoWillDismiss
{
    [self.webViewEngine evaluateJavaScript:@"cordova.fireDocumentEvent('onSkippableVideoClosed')" completionHandler:nil];
}

- (void)videoDidFinish
{
    [self.webViewEngine evaluateJavaScript:@"cordova.fireDocumentEvent('onSkippableVideoFinished')" completionHandler:nil];
}

- (void)videoDidClick
{
    [self.webViewEngine evaluateJavaScript:@"cordova.fireDocumentEvent('onSkippableVideoDidClick')" completionHandler:nil];
}

//rewarded video callbacks
- (void)rewardedVideoDidLoadAd
{
    [self.webViewEngine evaluateJavaScript:@"cordova.fireDocumentEvent('onRewardedVideoLoaded')" completionHandler:nil];
    [self.webViewEngine evaluateJavaScript:@"cordova.fireDocumentEvent('onNonSkippableVideoLoaded')" completionHandler:nil];
}

- (void)rewardedVideoDidFailToLoadAd
{
    [self.webViewEngine evaluateJavaScript:@"cordova.fireDocumentEvent('onRewardedVideoFailedToLoad')" completionHandler:nil];
    [self.webViewEngine evaluateJavaScript:@"cordova.fireDocumentEvent('onNonSkippableVideoFailedToLoad')" completionHandler:nil];
}

- (void)rewardedVideoWillDismiss
{
    [self.webViewEngine evaluateJavaScript:@"cordova.fireDocumentEvent('onRewardedVideoClosed')" completionHandler:nil];
    [self.webViewEngine evaluateJavaScript:@"cordova.fireDocumentEvent('onNonSkippableVideoClosed')" completionHandler:nil];
}

- (void)rewardedVideoDidPresent
{
    [self.webViewEngine evaluateJavaScript:@"cordova.fireDocumentEvent('onRewardedVideoShown')" completionHandler:nil];
    [self.webViewEngine evaluateJavaScript:@"cordova.fireDocumentEvent('onNonSkippableVideoShown')" completionHandler:nil];
}

- (void)rewardedVideoDidFinish:(NSUInteger)rewardAmount name:(NSString *)rewardName
{
    [self.webViewEngine evaluateJavaScript:@"cordova.fireDocumentEvent('onNonSkippableVideoFinished')" completionHandler:nil];
    NSString* script = [NSString stringWithFormat:@"cordova.fireDocumentEvent('onRewardedVideoFinished', { amount: %d, name: '%@' })", rewardAmount, rewardName];
    [self.webViewEngine evaluateJavaScript:script completionHandler:nil];
}

- (void)rewardedVideoDidClick
{
    [self.webViewEngine evaluateJavaScript:@"cordova.fireDocumentEvent('onRewardedVideoClicked')" completionHandler:nil];
}

- (void) enableInterstitialCallbacks:(CDVInvokedUrlCommand*)command
{
    [Appodeal setInterstitialDelegate:self];
}

- (void) enableSkippableVideoCallbacks:(CDVInvokedUrlCommand*)command
{
    [Appodeal setVideoDelegate:self];
}

- (void) enableBannerCallbacks:(CDVInvokedUrlCommand*)command
{
    [Appodeal setBannerDelegate:self];
}

- (void) enableRewardedVideoCallbacks:(CDVInvokedUrlCommand*)command
{
    [Appodeal setRewardedVideoDelegate:self];
}

- (void) initialize:(CDVInvokedUrlCommand*)command
{
    NSString* appKey = [[command arguments] objectAtIndex:0];
    id adType = [[command arguments] objectAtIndex:1];
    [Appodeal initializeWithApiKey:appKey types:nativeAdTypesForType([adType integerValue])];
}

- (void) setTesting:(CDVInvokedUrlCommand*)command
{
    BOOL testingEnabled = [[command arguments] objectAtIndex:0];
    [Appodeal setTestingEnabled:testingEnabled];
}

- (void) setLogging:(CDVInvokedUrlCommand*)command
{
    BOOL debugEnabled = [[command arguments] objectAtIndex:0];
    [Appodeal setDebugEnabled:debugEnabled];
}

- (void) isLoaded:(CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult = nil;
    
    id adType = [[command arguments] objectAtIndex:0];
    if([Appodeal isReadyForShowWithStyle:nativeShowStyleForType([adType integerValue])])
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsInt:1];
    else
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsInt:0];
    
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void) confirm:(CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult = nil;
    pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"ok"];
    
    id adType = [[command arguments] objectAtIndex:0];
    [Appodeal confirmUsage:nativeShowStyleForType([adType integerValue])];
    
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void) show:(CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult = nil;
    id adType = [[command arguments] objectAtIndex:0];
    if([Appodeal showAd:nativeShowStyleForType([adType integerValue]) rootViewController:self.viewController])
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsInt:1];
    else
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsInt:0];

    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void) hide:(CDVInvokedUrlCommand*)command
{
    [Appodeal hideBanner];
}

- (void) cache:(CDVInvokedUrlCommand*)command
{
    id adType = [[command arguments] objectAtIndex:0];
    [Appodeal cacheAd:nativeShowStyleForType([adType integerValue])];
}

- (void) setAutoCache:(CDVInvokedUrlCommand*)command
{
    id adType = [[command arguments] objectAtIndex:0];
    id autoCache = [[command arguments] objectAtIndex:1];
    [Appodeal setAutocache:[autoCache boolValue] types:nativeAdTypesForType([adType integerValue])];
}

- (void) disableNetwork:(CDVInvokedUrlCommand*)command
{
    NSString* network = [[command arguments] objectAtIndex:0];
    [Appodeal disableNetworkForAdType:AppodealAdTypeAll name:network];
}

- (void) disableNetworkType:(CDVInvokedUrlCommand*)command
{
    NSString* network = [[command arguments] objectAtIndex:0];
    id adType = [[command arguments] objectAtIndex:1];
    [Appodeal disableNetworkForAdType:nativeAdTypesForType([adType integerValue]) name:network];
}


- (void) disableLocationCheck:(CDVInvokedUrlCommand*)command
{
    [Appodeal disableLocationPermissionCheck];
}

@end
