package com.appodeal.plugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.os.Handler;
import android.os.Looper;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.BannerCallbacks;
import com.appodeal.ads.InterstitialCallbacks;
import com.appodeal.ads.RewardedVideoCallbacks;
import com.appodeal.ads.SkippableVideoCallbacks;
import com.appodeal.ads.NonSkippableVideoCallbacks;

public class AppodealPlugin extends CordovaPlugin {

    private static final String ACTION_INIT_APPODEAL = "initialize";
    private static final String ACTION_SET_INTERSTITIAL_CALLBACKS = "enableIntertitialCallbacks";
    private static final String ACTION_SET_SKIPPABLE_VIDEO_CALLBACKS = "enableSkippableVideoCallbacks";
    private static final String ACTION_SET_NON_SKIPPABLE_VIDEO_CALLBACKS = "enableNonSkippableVideoCallbacks";
    private static final String ACTION_SET_BANNER_CALLBACKS = "enableBannerCallbacks";
    private static final String ACTION_SET_REWARDED_VIDEO_CALLBACKS = "enableRewardedVideoCallbacks";
    private static final String ACTION_ISLOADED = "isLoaded";
    private static final String ACTION_ISPRECACHE = "isPrecache";
    private static final String ACTION_CONFIRM = "confirm";
    private static final String ACTION_SHOW = "show";
    private static final String ACTION_HIDE = "hide";
    private static final String ACTION_SET_AUTO_CACHE = "setAutoCache";
    private static final String ACTION_CACHE_BANNER = "cacheBanner";
    private static final String ACTION_SET_ON_LOADED_TRIGGER_BOTH = "setOnLoadedTriggerBoth";
    private static final String ACTION_DISABLE_NETWORK = "disableNetwork";
    private static final String ACTION_DISABLE_NETWORK_TYPE = "disableNetworkType";
    private static final String ACTION_DISABLE_LOCATION_CHECK = "disableLocationCheck";
    private static final String ACTION_SET_TESTING = "setTesting";
    private static final String ACTION_SET_LOGGING = "setLogging";
    
    private String appKey = null;
    private int adType = 0;
    private boolean autoCache = true;
    private boolean setOnTriggerBoth = true;
    private boolean setInterstitialCallbacks = false;
    private boolean setSkippableVideoCallbacks = false;
    private boolean setNonSkippableVideoCallbacks = false;
    private boolean setRewardedVideoCallbacks = false;
    private boolean setBannerCallbacks = false;
    private boolean testing = false;
    private boolean logging = false;

    @Override
    public boolean execute(String action, JSONArray args,
            final CallbackContext callback) throws JSONException {

        if (action.equals(ACTION_INIT_APPODEAL)) {
            appKey = args.getString(0);
            adType = args.getInt(1);
            cordova.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Appodeal.initialize(cordova.getActivity(), appKey, adType);
                }
            });
            return true;
        } else if (action.equals(ACTION_SET_INTERSTITIAL_CALLBACKS)) {
            setInterstitialCallbacks = args.getBoolean(0);
            cordova.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(setInterstitialCallbacks) {
                        Appodeal.setInterstitialCallbacks(interstitialListener);
                    }
                }
            });
            return true;
        } else if (action.equals(ACTION_SET_NON_SKIPPABLE_VIDEO_CALLBACKS)) {
            setNonSkippableVideoCallbacks = args.getBoolean(0);
            cordova.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(setNonSkippableVideoCallbacks) {
                        Appodeal.setNonSkippableVideoCallbacks(nonSkippableVideoListener);
                    }
                }
            });
            return true;
        } else if (action.equals(ACTION_SET_SKIPPABLE_VIDEO_CALLBACKS)) {
            setSkippableVideoCallbacks = args.getBoolean(0);
            cordova.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(setSkippableVideoCallbacks) {
                        Appodeal.setSkippableVideoCallbacks(skippableVideoListener);
                    }
                }
            });
            return true;
        } else if (action.equals(ACTION_SET_REWARDED_VIDEO_CALLBACKS)) {
            setRewardedVideoCallbacks = args.getBoolean(0);
            cordova.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(setRewardedVideoCallbacks) {
                        Appodeal.setRewardedVideoCallbacks(rewardedVideoListener);
                    }
                }
            });
            return true;
        } else if (action.equals(ACTION_SET_BANNER_CALLBACKS)) {
            setBannerCallbacks = args.getBoolean(0);
            cordova.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(setBannerCallbacks) {
                        Appodeal.setBannerCallbacks(bannerListener);
                    }
                }
            });
            return true;
        } else if (action.equals(ACTION_ISLOADED)) {
            adType = args.getInt(0);
            cordova.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    callback.success(Appodeal.isLoaded(adType) ? 1 : 0);
                }
            });
            return true;
        } else if (action.equals(ACTION_ISPRECACHE)) {
            adType = args.getInt(0);
            cordova.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    callback.success(Appodeal.isPrecache(adType) ? 1 : 0);
                }
            });
            return true;
        } else if (action.equals(ACTION_CONFIRM)) {
            adType = args.getInt(0);
            cordova.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Appodeal.confirm(adType);
                }
            });
            return true;
        } else if (action.equals(ACTION_SHOW)) {
            adType = args.getInt(0);
            cordova.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    boolean isShow = Appodeal.show(cordova.getActivity(), adType);
                    callback.success(isShow ? 1 : 0);
                }
            });
            return true;
        } else if (action.equals(ACTION_HIDE)) {
            adType = args.getInt(0);
            cordova.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Appodeal.hide(cordova.getActivity(), adType);
                }
            });
            return true;
        } else if (action.equals(ACTION_SET_AUTO_CACHE)) {
            adType = args.getInt(0);
            autoCache = args.getBoolean(1);
            cordova.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Appodeal.setAutoCache(adType, autoCache);
                }
            });
            return true;
        } else if (action.equals(ACTION_CACHE_BANNER)) {
            adType = args.getInt(0);
            cordova.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Appodeal.cache(cordova.getActivity(), adType);
                }
            });
            return true;
        } else if (action.equals(ACTION_SET_ON_LOADED_TRIGGER_BOTH)) {
            adType = args.getInt(0);
            setOnTriggerBoth = args.getBoolean(1);
            cordova.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Appodeal.setOnLoadedTriggerBoth(adType, setOnTriggerBoth);
                }
            });
            return true;
        } else if (action.equals(ACTION_DISABLE_NETWORK)) {
            final String network = args.getString(0);
            cordova.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Appodeal.disableNetwork(cordova.getActivity(), network);
                }
            });
            return true;
        } else if (action.equals(ACTION_DISABLE_NETWORK_TYPE)) {
            final String network = args.getString(0);
            adType = args.getInt(1);
            cordova.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Appodeal.disableNetwork(cordova.getActivity(), network, adType);
                }
            });
            return true;
        } else if (action.equals(ACTION_DISABLE_LOCATION_CHECK)) {
            cordova.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Appodeal.disableLocationPermissionCheck();
                }
            });
            return true;
        } else if (action.equals(ACTION_SET_LOGGING)) {
            logging = args.getBoolean(0);
            cordova.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Appodeal.setLogging(logging);
                }
            });
            return true;
        } else if (action.equals(ACTION_SET_TESTING)) {
            testing = args.getBoolean(0);
            cordova.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Appodeal.setTesting(testing);
                }
            });
            return true;
        }
        return false;
    }
    
    private InterstitialCallbacks interstitialListener = new InterstitialCallbacks() {
        
        @Override
        public void onInterstitialShown() {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl ("javascript:cordova.fireDocumentEvent('onInterstitialShown');");
                }
            });
        }
        
        @Override
        public void onInterstitialLoaded(boolean arg0) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl ("javascript:cordova.fireDocumentEvent('onInterstitialLoaded');");
                }
            });
        }
        
        @Override
        public void onInterstitialFailedToLoad() {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl ("javascript:cordova.fireDocumentEvent('onInterstitialFailedToLoad');");
                }
            });
        }
        
        @Override
        public void onInterstitialClosed() {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl ("javascript:cordova.fireDocumentEvent('onInterstitialClosed');");
                }
            });
        }
        
        @Override
        public void onInterstitialClicked() {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl ("javascript:cordova.fireDocumentEvent('onInterstitialClicked');");
                }
            });
        }
    };
    
    private SkippableVideoCallbacks skippableVideoListener = new SkippableVideoCallbacks() {

        @Override
        public void onSkippableVideoClosed() {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl ("javascript:cordova.fireDocumentEvent('onSkippableVideoClosed');");
                }
            });
        }

        @Override
        public void onSkippableVideoFailedToLoad() {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl ("javascript:cordova.fireDocumentEvent('onSkippableVideoFailedToLoad');");
                }
            });
        }

        @Override
        public void onSkippableVideoFinished() {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl ("javascript:cordova.fireDocumentEvent('onSkippableVideoFinished');");
                }
            });
        }

        @Override
        public void onSkippableVideoLoaded() {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl ("javascript:cordova.fireDocumentEvent('onSkippableVideoLoaded');");
                }
            });
        }

        @Override
        public void onSkippableVideoShown() {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl ("javascript:cordova.fireDocumentEvent('onSkippableVideoShown');");
                }
            });
        }
        
    };

    private NonSkippableVideoCallbacks nonSkippableVideoListener = new NonSkippableVideoCallbacks() {

        @Override
        public void onNonSkippableVideoClosed() {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl ("javascript:cordova.fireDocumentEvent('onNonSkippableVideoClosed');");
                }
            });
        }

        @Override
        public void onNonSkippableVideoFailedToLoad() {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl ("javascript:cordova.fireDocumentEvent('onNonSkippableVideoFailedToLoad');");
                }
            });
        }

        @Override
        public void onNonSkippableVideoFinished() {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl ("javascript:cordova.fireDocumentEvent('onNonSkippableVideoFinished');");
                }
            });
        }

        @Override
        public void onNonSkippableVideoLoaded() {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl ("javascript:cordova.fireDocumentEvent('onNonSkippableVideoLoaded');");
                }
            });
        }

        @Override
        public void onNonSkippableVideoShown() {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl ("javascript:cordova.fireDocumentEvent('onNonSkippableVideoShown');");
                }
            });
        }

    };

    private RewardedVideoCallbacks rewardedVideoListener = new RewardedVideoCallbacks() {

        @Override
        public void onRewardedVideoClosed() {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl ("javascript:cordova.fireDocumentEvent('onRewardedVideoClosed');");
                }
            });
        }

        @Override
        public void onRewardedVideoFailedToLoad() {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl ("javascript:cordova.fireDocumentEvent('onRewardedVideoFailedToLoad');");
                }
            });
        }

        @Override
        public void onRewardedVideoFinished(final int amount, final String name) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl(String.format(
                            "javascript:cordova.fireDocumentEvent('onRewardedVideoFinished', { 'amount': %d, 'name':'%s' });",
                            amount, name));
                }
            });
        }

        @Override
        public void onRewardedVideoLoaded() {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl ("javascript:cordova.fireDocumentEvent('onRewardedVideoLoaded');");
                }
            });
        }

        @Override
        public void onRewardedVideoShown() {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl ("javascript:cordova.fireDocumentEvent('onRewardedVideoShown');");
                }
            });
        }
        
    };
    
    private BannerCallbacks bannerListener = new BannerCallbacks() {

        @Override
        public void onBannerClicked() {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl ("javascript:cordova.fireDocumentEvent('onBannerClicked');");
                }
            });
        }

        @Override
        public void onBannerFailedToLoad() {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl ("javascript:cordova.fireDocumentEvent('onBannerFailedToLoad');");
                }
            });
        }

        @Override
        public void onBannerLoaded() {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl ("javascript:cordova.fireDocumentEvent('onBannerLoaded');");
                }
            });
        }

        @Override
        public void onBannerShown() {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl ("javascript:cordova.fireDocumentEvent('onBannerShown');");
                }
            });
        }
        
    };
}