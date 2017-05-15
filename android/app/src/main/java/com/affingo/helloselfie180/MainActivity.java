package com.affingo.helloselfie180;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static final String PKG_PATH="com.affingo.selfie180";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button uploadvideo = (Button)findViewById(R.id.uploadvideo);
        uploadvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadVideo();
            }
        });     
        
        Button downloadvideo = (Button)findViewById(R.id.downloadvideo);
        downloadvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                downloadVideo();

            }
        });

        Button downloadvideofromzip = (Button)findViewById(R.id.downloadvideofromzip);
        downloadvideofromzip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                downloadvideofromzip();

            }
        });


    }

    private void downloadVideo() {
        String downloadJson = "{\n" +
                "  \"opcode\": \"download\",\n" +
                "  \"baseurl\": \"http://khojpal.com/static/images/Demo.mp4\",\n" +
                "  \"localfile\": \"/sdcard/1.mp4\",\n" +
                "  \"cookies\": {\n" +
                "    \"webpysessionid\": \"daedwdefwfwefrfgghjuyutr\",\n" +
                "    \"auth\": \"dsadadgtrhythtyhtjyyjytjtjytjytj\"\n" +
                "  },\n" +
                "  \"params\": {\n" +
                "    \"param1\": \"sdaasdsada\",\n" +
                "    \"param2\": \"dsadadasdas\"\n" +
                "  },\n" +
                "  \"method\": \"GET\",\n" +
                "  \"client\": \"android-anuroop\"\n" +
                "}";

        Intent launchIntent = getPackageManager().getLaunchIntentForPackage(PKG_PATH);
        if (launchIntent != null) {
            launchIntent.putExtra("jsondata",downloadJson);
            launchIntent.putExtra("thirdparty","thirdparty");
            launchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            launchIntent.putExtra("selfieFlag","download");
            startActivity(launchIntent); // null pointer check in case package name was not found
        }
        else
        {
            downloadSelfieApp(MainActivity.this);
        }
    }
    
    private void downloadvideofromzip() {

         String downloadJson = "{\n" +
                "  \"opcode\": \"download\",\n" +
                "  \"baseurl\": \"http://www.selfie180.com/static/images/scuba_w_320_q_10.zip\",\n" +
                "  \"localfile\": \"/sdcard/1.mp4\",\n" +
                "  \"cookies\": {\n" +
                "    \"webpysessionid\": \"daedwdefwfwefrfgghjuyutr\",\n" +
                "    \"auth\": \"dsadadgtrhythtyhtjyyjytjtjytjytj\"\n" +
                "  },\n" +
                "  \"params\": {\n" +
                "    \"param1\": \"sdaasdsada\",\n" +
                "    \"param2\": \"dsadadasdas\"\n" +
                "  },\n" +
                "  \"method\": \"GET\",\n" +
                "  \"client\": \"android-anuroop\"\n" +
                "}";

        Intent launchIntent = getPackageManager().getLaunchIntentForPackage(PKG_PATH);
        if (launchIntent != null) {
            launchIntent.putExtra("jsondata",downloadJson);
            launchIntent.putExtra("thirdparty","thirdparty");
            launchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            launchIntent.putExtra("selfieFlag","download");
            startActivity(launchIntent); // null pointer check in case package name was not found
        }
        else
        {
            downloadSelfieApp(MainActivity.this);
        }
    }

    private void uploadVideo() {
        
        String uploadJson =
                "{\n" +
                        "  \"opcode\": \"upload\",\n" +
                        "  \"baseurl\": \"http://192.168.1.72:8070/upload\",\n" +
                        "  \"localfile\": \"/sdcard/1.mp4\",\n" +
                        "  \"cookies\": {\n" +
                        "    \"webpysessionid\": \"849dc4aeba006e6a86240ac7d9b00289d40cdecd\",\n" +
                        "    \"auth\": \"849dc4aeba006e6a86240ac7d9b00289d40cdecd\"\n" +
                        "  },\n" +
                        "  \"params\": {\n" +
                        "    \"param1\": \"sdaasdsada\",\n" +
                        "    \"param2\": \"dsadadasdas\"\n" +
                        "  },\n" +
                        "  \"method\": \"POST\",\n" +
                        "  \"client\": \"android-anuroop\"\n" +
                        "}";


        Intent launchIntent = getPackageManager().getLaunchIntentForPackage(PKG_PATH);
        if (launchIntent != null) {
            launchIntent.putExtra("jsondata",uploadJson);
            launchIntent.putExtra("selfieFlag","upload");
            launchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            launchIntent.putExtra("thirdparty","thirdparty");
            startActivity(launchIntent);
        }
        else
        {
            downloadSelfieApp(MainActivity.this);
        }
    }

    private void downloadSelfieApp(final Activity context) {


        new AlertDialog.Builder(context)
                .setTitle("Download apk from Google Play")
                .setMessage("")
                .setCancelable(false)
                .setPositiveButton("Download", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id="+PKG_PATH));
                        context.startActivity(browserIntent);
                    }
                })
                .setNegativeButton("Don't Download", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("req"+requestCode,"res"+resultCode);

    }
}