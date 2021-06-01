package com.example.ironsourcebonusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.integration.IntegrationHelper;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.sdk.InterstitialListener;


public class MainActivity extends AppCompatActivity implements InterstitialListener {

    private final String APP_KEY = "fad6e029";
    private Button loadButton;
    private Button showButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntegrationHelper.validateIntegration(this);
        initUI();
        initIronSourceTask();
    }
    private void initIronSourceTask(){
        String advertisingId = IronSource.getAdvertiserId(MainActivity.this);
        initIronSourceConfig(APP_KEY, advertisingId);
    }

    private void initIronSourceConfig(String appKey, String userId){
        IronSource.setInterstitialListener(this);
        IronSource.setUserId(userId);
        IronSource.init(this, appKey);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IronSource.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        IronSource.onPause(this);
    }

    private void initUI(){
        loadButton = (Button) findViewById(R.id.load_button);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IronSource.loadInterstitial();
            }
        });


        showButton = (Button) findViewById(R.id.show_button);
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (IronSource.isInterstitialReady()) {
                    IronSource.showInterstitial();
                }
            }
        });
    }

    @Override
    public void onInterstitialAdReady() {

    }

    @Override
    public void onInterstitialAdLoadFailed(IronSourceError ironSourceError) {

    }

    @Override
    public void onInterstitialAdOpened() {

    }

    @Override
    public void onInterstitialAdClosed() {

    }

    @Override
    public void onInterstitialAdShowSucceeded() {

    }

    @Override
    public void onInterstitialAdShowFailed(IronSourceError ironSourceError) {

    }

    @Override
    public void onInterstitialAdClicked() {

    }
}