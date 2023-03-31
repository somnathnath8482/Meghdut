package com.meghdut.android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.labters.lottiealertdialoglibrary.ClickListener;
import com.labters.lottiealertdialoglibrary.DialogTypes;
import com.labters.lottiealertdialoglibrary.LottieAlertDialog;
import com.meghdut.android.Interface.AllInterface;
import com.meghdut.android.databinding.ActivityMainBinding;
import com.meghdut.android.databinding.ToolbarBinding;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
ToolbarBinding toolbarBinding;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        toolbarBinding= ToolbarBinding.bind(binding.toolbar.getRoot());
        setContentView(binding.getRoot()) ;


        binding.web.getSettings().setJavaScriptEnabled(true);

        binding.web.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                toolbarBinding.url.setText(url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                binding.progress.setVisibility(View.GONE);
            }
        });

        binding.web.loadUrl("http://meghdut.co.in");

        toolbarBinding.back.setOnClickListener(view -> {
            if (binding.web.canGoBack()){
                binding.web.goBack();
            }
        });

        toolbarBinding.foroward.setOnClickListener(view -> {
            if (binding.web.canGoForward()){
                binding.web.goForward();
            }
        });

    }

    @Override
    public void onBackPressed() {

           askClose(new AllInterface(){
               @Override
               public void isClicked(boolean is) {
                   super.isClicked(is);
                   if (is){
                       MainActivity.super.onBackPressed();
                   }
               }
           });
    }

   private void askClose(AllInterface allInterface){
       LottieAlertDialog lottieAlertDialog = new  LottieAlertDialog.Builder(this, DialogTypes.TYPE_QUESTION)
               .setPositiveButtonColor(Color.parseColor("#048B3A"))
               .setNegativeButtonColor(Color.parseColor("#DA1606"))
               .setNoneButtonColor(Color.parseColor("#038DFC"))
               .setPositiveTextColor(Color.WHITE)
               .setNegativeTextColor(Color.WHITE)
               .setNoneTextColor(Color.WHITE)
               .setTitle("Close application")
               .setDescription("Do you want to close the application? ")
               .setPositiveText("OK")
               .setPositiveButtonColor(Color.parseColor("#048B3A"))
               .setNegativeButtonColor(Color.parseColor("#DA1606"))
               .setPositiveListener(new ClickListener() {
                   @Override
                   public void onClick(@NonNull LottieAlertDialog lottieAlertDialog) {
                       // activity.onBackPressed();
                    allInterface.isClicked(true);
                       lottieAlertDialog.dismiss();
                   }
               }).setNegativeText("Cancel")
               .setNegativeListener(new ClickListener() {
                   @Override
                   public void onClick(@NonNull LottieAlertDialog lottieAlertDialog) {
                       // activity.onBackPressed();
                       lottieAlertDialog.dismiss();
                   }
               })
               .build();

       lottieAlertDialog.setCancelable(false);
       lottieAlertDialog.setCanceledOnTouchOutside(false);
       lottieAlertDialog.show();


   }
}