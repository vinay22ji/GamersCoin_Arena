package com.developer.vinay22ji.gamerscoin_arena.Activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.GeolocationPermissions;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.developer.vinay22ji.gamerscoin_arena.R;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


import static android.view.View.VISIBLE;

public class WebActivity extends AppCompatActivity{

    WebView webView;
    ProgressBar progressBarWeb;
    public static String webUrl = "https://www.google.com/";
    public static String current_webUrl = "https://www.google.com/";
//    RelativeLayout Nointernetconnection_Layout;
//    LinearLayout dark_layout, fav_layout;
//    AppCompatButton fav_button2;
    String name, image = "https://www.bsmc.net.au/wp-content/uploads/No-image-available.jpg", subtitle = "Favorites";
//    private AdView facebookadView;
//    private final String TAG = io.makeroid.bsdeora55520.usa.Activities.WebActivity.class.getSimpleName();
//    private InterstitialAd interstitialAd;

    ConstraintLayout native_llayout,loading_layout;
//    NativeAdLayout nativeAdLayout;
//    NativeAd nativeAd;
    int MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE=11123;
//    private LinearLayout ladView;
    public WebActivity(String webUrl) {
        webUrl = webUrl;
    }

    public WebActivity() {
    }

//    FloatingActionButton flot_back_btn,flot_forword_btn,flot_home_btn,flot_refresh_btn,copy_link__btn,share_link_btn;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

//        AudienceNetworkAds.initialize(this);
//        loadAdView();
//        loadInterstial();


        webView = findViewById(R.id.myWebView);
        progressBarWeb = findViewById(R.id.progressbar);
//        native_llayout = findViewById(R.id.native_llayout);
//        nativeAdLayout = findViewById(R.id.native_ad_container);
        loading_layout = findViewById(R.id.loading_layout);

        webView.loadUrl(webUrl);


        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);
            }
        });

        if (savedInstanceState != null) {
            webView.restoreState(savedInstanceState);

        } else {

            webView.getSettings().setLoadWithOverviewMode(true);
            webView.getSettings().setUseWideViewPort(true);
            webView.getSettings().setDomStorageEnabled(true);
            webView.getSettings().setLoadsImagesAutomatically(true);
            webView.getSettings().setGeolocationEnabled(true);
            webView.getSettings().setPluginState(WebSettings.PluginState.ON);
            webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            webView.getSettings().setAllowFileAccess(true);
            webView.getSettings().setAllowContentAccess(true);
            webView.getSettings().setAllowFileAccess(true);
            webView.getSettings().setAllowFileAccessFromFileURLs(true);
            webView.getSettings().setAllowUniversalAccessFromFileURLs(true);
            webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setGeolocationEnabled(true);

            if (isConnected(WebActivity.this)) {

                webView.loadUrl(webUrl);
                webView.setVisibility(VISIBLE);

//                Nointernetconnection_Layout.setVisibility(View.GONE);
            } else {

                webView.setVisibility(View.GONE);
//                Nointernetconnection_Layout.setVisibility(VISIBLE);
            }
        }


        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {


                if (url.startsWith("http") || url.startsWith("https")) {
//                    check_fav_only();
                    System.out.println("------------------------------------------------------------ e");
                    return false;
                }
                if (url.startsWith("intent")) {

                    try {
                        Intent intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);

                        String fallbackUrl = intent.getStringExtra("browser_fallback_url");
                        if (fallbackUrl != null) {
                            webView.loadUrl(fallbackUrl);
                            current_webUrl = fallbackUrl;
                            webView.setWebChromeClient(new WebChromeClient() {
                                @Override
                                public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                                    callback.invoke(origin, true, false);
                                }
                            });
                            webView.getSettings().setJavaScriptEnabled(true);
                            webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

//                            check_fav_only();

                            return true;
                        }
                    } catch (URISyntaxException e) {
//                        check_fav_only();

                        System.out.println("------------------------------------------------------------ e");
                        //not an intent uri
                    }
//                    check_fav_only();
                    return true;//do nothing in other cases

                }
//                check_fav_only();


                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
//                swipeRefreshLayout.setRefreshing(false);
                super.onPageFinished(view, url);


//                check_fav_only();

            }

        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String sTitle) {
                super.onReceivedTitle(view, sTitle);
                if (sTitle != null && sTitle.length() > 0) {
                    try {
//                        check_fav_only();
                        name = sTitle.substring(0, 16);
                    } catch (StringIndexOutOfBoundsException w) {
//                        check_fav_only();
                        name = sTitle;
                    }

                } else {
//                    check_fav_only();
                    name = "Web Page";
                }
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {

                progressBarWeb.setVisibility(VISIBLE);
                progressBarWeb.setProgress(newProgress);
                current_webUrl = webUrl;
                if (newProgress == 100) {

                    loading_layout.setVisibility(View.GONE);

                    progressBarWeb.setVisibility(View.GONE);
                }

                super.onProgressChanged(view, newProgress);
            }
        });

    }


//    public void loadAdView() {
//        if (facebookadView != null) {
//            facebookadView.destroy();
//            facebookadView = null;
//        }
//
//        // Create a banner's ad view with a unique placement ID (generate your own on the Facebook
//        // app settings). Use different ID for each ad placement in your app.
//        facebookadView = new AdView(this, getString(R.string.facebook_banner), AdSize.BANNER_HEIGHT_50);
//
//        // Reposition the ad and add it to the view hierarchy.
//        LinearLayout adContainer = findViewById(R.id.webview_banner_container);
//        adContainer.addView(facebookadView);
//
//        // Initiate a request to load an ad.
//        facebookadView.loadAd(facebookadView.buildLoadAdConfig().withAdListener(this).build());
//    }
//    private void loadNativeAd() {
//
//        nativeAd = new NativeAd(this, getResources().getString(R.string.nativead));
//        NativeAdListener nativeAdListener = new NativeAdListener() {
//            @Override
//            public void onMediaDownloaded(Ad ad) {
//                // Native ad finished downloading all assets
//                Log.e(TAG, "Native ad finished downloading all assets.");
//            }
//            @Override
//            public void onError(Ad ad, AdError adError) {
//                // Native ad failed to load
//                Log.e(TAG, "Native ad failed to load: " + adError.getErrorMessage());
//            }
//            @Override
//            public void onAdLoaded(Ad ad) {
//                // Native ad is loaded and ready to be displayed
//                Log.d(TAG, "Native ad is loaded and ready to be displayed!");
//                if (nativeAd == null || nativeAd != ad) {
//                    return;
//                }
//                // Inflate Native Ad into Container
//                inflateAd(nativeAd);
//            }
//            @Override
//            public void onAdClicked(Ad ad) {
//                // Native ad clicked
//                Log.d(TAG, "Native ad clicked!");
//            }
//            @Override
//            public void onLoggingImpression(Ad ad) {
//                // Native ad impression
//                Log.d(TAG, "Native ad impression logged!");
//            }
//        };
//        // Request an ad
//        nativeAd.loadAd(nativeAd.buildLoadAdConfig().withAdListener(nativeAdListener).build());
//    }
//
//    private void inflateAd(NativeAd nativeAd) {
//        nativeAd.unregisterView();
//        nativeAdLayout = findViewById(R.id.native_ad_container);
//        LayoutInflater inflater = LayoutInflater.from(io.makeroid.bsdeora55520.usa.Activities.WebActivity.this);
//        ladView = (LinearLayout) inflater.inflate(R.layout.facebook_nativead_layout, nativeAdLayout, false);
//        nativeAdLayout.addView(ladView);
//        LinearLayout adChoicesContainer = findViewById(R.id.ad_choices_container);
//        AdOptionsView adOptionsView = new AdOptionsView(io.makeroid.bsdeora55520.usa.Activities.WebActivity.this, nativeAd, nativeAdLayout);
//        adChoicesContainer.removeAllViews();
//        adChoicesContainer.addView(adOptionsView, 0);
//        MediaView nativeAdIcon = ladView.findViewById(R.id.native_ad_icon);
//        TextView nativeAdTitle = ladView.findViewById(R.id.native_ad_title);
//        MediaView nativeAdMedia = ladView.findViewById(R.id.native_ad_media);
//        TextView nativeAdSocialContext = ladView.findViewById(R.id.native_ad_social_context);
//        TextView nativeAdBody = ladView.findViewById(R.id.native_ad_body);
//        TextView sponsoredLabel = ladView.findViewById(R.id.native_ad_sponsored_label);
//        Button nativeAdCallToAction = ladView.findViewById(R.id.native_ad_call_to_action);
//        nativeAdTitle.setText(nativeAd.getAdvertiserName());
//        nativeAdBody.setText(nativeAd.getAdBodyText());
//        nativeAdSocialContext.setText(nativeAd.getAdSocialContext());
//        nativeAdCallToAction.setVisibility(nativeAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
//        nativeAdCallToAction.setText(nativeAd.getAdCallToAction());
//        sponsoredLabel.setText(nativeAd.getSponsoredTranslation());
//        List<View> clickableViews = new ArrayList<>();
//        clickableViews.add(nativeAdTitle);
//        clickableViews.add(nativeAdCallToAction);
//        nativeAd.registerViewForInteraction(
//                ladView,
//                nativeAdMedia,
//                nativeAdIcon,
//                clickableViews);
//    }
//
//    private void loadInterstial() {
//
//        interstitialAd = new InterstitialAd(io.makeroid.bsdeora55520.usa.Activities.WebActivity.this, getString(R.string.facebook_interstial));
//        interstitialAd.loadAd();
//        InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
//            @Override
//            public void onInterstitialDisplayed(Ad ad) {
//            }
//
//            @Override
//            public void onInterstitialDismissed(Ad ad) {
//            }
//
//            @Override
//            public void onError(Ad ad, AdError adError) {
////                StartAppAd.showAd(WebActivity.this);
//
//       //         int secondsDelayed = 2;
//       //         new Handler().postDelayed(new Runnable() {
//      //              public void run() {
//       //             }
//       //         }, secondsDelayed * 1000);
//
//
//            }
//
//            @Override
//            public void onAdLoaded(Ad ad) {
//                try {
//                    interstitialAd.show();
//                }catch (Exception ee){}
//            }
//
//            @Override
//            public void onAdClicked(Ad ad) {
//            }
//
//            @Override
//            public void onLoggingImpression(Ad ad) {
//            }
//        };
//        interstitialAd.loadAd(
//                interstitialAd.buildLoadAdConfig()
//                        .withAdListener(interstitialAdListener)
//                        .build());
//
//        loadNativeAd();
////        loadAdView();
//    }
//
//

    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            return (mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting());
        } else
            return false;
    }


//
//    @Override
//    public void onBackPressed() {
//        if (webView.canGoBack()) {
//            webView.goBack();
//        } else {
//            try {
//               try{
//                   HomePage homePage =new HomePage();
//                   homePage.loadAdView();
//                   super.onBackPressed();
//               }catch (Exception e){}
//                super.onBackPressed();
//            } catch (Exception e) {
//
//            }
//        }
//    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        webView.saveState(outState);
        outState.clear();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        webView.restoreState(savedInstanceState);
    }

//    private AdView webview_adView;
//
//    @Override
//    protected void onDestroy() {
//        if (webview_adView != null) {
//            webview_adView.destroy();
//        }
//        super.onDestroy();
//    }
//
//
//    public void loadAdView() {
//        if (webview_adView != null) {
//            webview_adView.destroy();
//            webview_adView = null; }
//        webview_adView = new AdView(this, getResources().getString(R.string.facebook_banner), AdSize.BANNER_HEIGHT_50);
//        LinearLayout adContainer = (LinearLayout) findViewById(R.id.webview_banner_container);
//        adContainer.addView(webview_adView);
//        webview_adView.loadAd(webview_adView.buildLoadAdConfig().withAdListener(this).build());
//
//    }
//
//
//
//    @Override
//    public void onError(Ad ad, AdError adError) {
//
//        System.out.println("--------------- HOME banner failed to load "+ad +"     "+ adError.toString());
////
//        //     int secondsDelayed = 5;
//        //      new Handler().postDelayed(new Runnable() {
//        //         public void run() {
//        //             loadAdView();
//        //         }
//        //     }, secondsDelayed * 1000);
//
//    }
//
//    @Override
//    public void onAdLoaded(Ad ad) {
//
//        System.out.println("--------------- Webview banner load");
//        int secondsDelayed = 120;
//        new Handler().postDelayed(new Runnable() {
//            public void run() {
//                loadAdView();
//            }
//        }, secondsDelayed * 1000);
//    }
//
//    @Override
//    public void onAdClicked(Ad ad) {
//
//    }
//
//    @Override
//    public void onLoggingImpression(Ad ad) {
//
//    }
//


}