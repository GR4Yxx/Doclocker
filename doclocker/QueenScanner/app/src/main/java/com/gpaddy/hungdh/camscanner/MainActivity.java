package com.gpaddy.hungdh.camscanner;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.MainApplication;
//import com.google.android.gms.ads.AdListener;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;
import com.google.firebase.auth.FirebaseAuth;
import com.gpaddy.hungdh.listdoc.DocsActivity;
//import com.gpaddyv1.queenscanner.Config.AdsTask;
import com.gpaddyv1.queenscanner.activities.SimpleDocumentScannerActivity;
import com.gpaddyv2.queenscanner.*;

import java.io.File;
import java.io.IOException;

import static com.gpaddyv1.queenscanner.PresenterScanner.FOLDER_NAME;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView cvCamera, cvGuide, cvGallery, cvPDF;
    private static final int REQUEST_STORAGE = 212;
    private static final int REQUEST_CAMERA = 1;
    private static final int REQUEST_CAMERA_PERMISSION = 2;
    private String pathCamera = null;
    private LinearLayout llAds;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getSupportActionBar().hide();

        MainApplication application = (MainApplication) getApplication();

        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            initView();
            initAds();

        } else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_STORAGE);
        }

    }

    private void initAds() {

    }

    @Override
    protected void onRestart() {
        super.onRestart();
      //  adsTask.showInterstitialAds();
    }

    private void initView() {
        llAds = findViewById(R.id.ll_ads);
        cvCamera = (CardView) findViewById(R.id.cvCamera);
        cvGuide = (CardView) findViewById(R.id.cvFromGallery);
        cvGallery = (CardView) findViewById(R.id.cvGallery);
        cvPDF = (CardView) findViewById(R.id.cvPDF);

        cvCamera.setOnClickListener(this);
        cvGuide.setOnClickListener(this);
        cvGallery.setOnClickListener(this);
        cvPDF.setOnClickListener(this);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_STORAGE) {
            if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                initView();

            } else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_STORAGE);
            }
        }
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                callCamera();
            } else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
            }
        }

    }

    private void initBannerAds() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cvCamera:
                /*Intent intent = new Intent(MainActivity.this, OpenNoteScannerActivity.class);
                intent.setAction(Intent.ACTION_MAIN);
                startActivity(intent);*/
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    callCamera();
                } else {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
                }
                break;
            case R.id.cvFromGallery:
                Intent guides=new Intent(MainActivity.this,Guides.class);
                startActivity(guides);
//                getSharedPreferences("BVH", MODE_PRIVATE).edit().putInt("type", 1).commit();
//                SimpleDocumentScannerActivity.startScanner(MainActivity.this, "", "");
////                Intent iG = new Intent(MainActivity.this, SimpleDocumentScannerActivity.class);
////                iG.putExtra(SimpleDocumentScannerActivity.KEY_DOCUMENT, "");
////                startActivity(iG);
                break;
            case R.id.cvGallery:
                startActivity(new Intent(MainActivity.this, DocsActivity.class));
                break;
            case R.id.cvPDF:
                startActivity(new Intent(MainActivity.this, MyPDFActivity.class));
                break;
        }
    }

    private void callCamera() {
        SharedPreferences mSharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String folderName = mSharedPref.getString("storage_folder", FOLDER_NAME);
        File folder = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                + "/" + folderName);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        pathCamera = folder.getAbsolutePath() + "/.TEMP_CAMERA.xxx";
        getSharedPreferences("BVH", MODE_PRIVATE).edit().putString("path", pathCamera).commit();
        getSharedPreferences("BVH", MODE_PRIVATE).edit().putInt("type", 1).commit();
        File f = new File(pathCamera);
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Uri outputFileUri;
        if (Build.VERSION.SDK_INT < 24)
            outputFileUri = Uri.fromFile(f);
        else {
            outputFileUri = FileProvider.getUriForFile(this, getPackageName() + ".provider", f);
        }
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_CAMERA);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int i = 0;
        i++;
        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK) {
            pathCamera = getSharedPreferences("BVH", MODE_PRIVATE).getString("path", pathCamera);
            File f = new File(pathCamera);
            if (f.exists()) {
                SimpleDocumentScannerActivity.startScanner(MainActivity.this, pathCamera, "");
//                Intent iC = new Intent(MainActivity.this, SimpleDocumentScannerActivity.class);
//                iC.putExtra(SimpleDocumentScannerActivity.KEY_DOCUMENT, pathCamera);
//                startActivity(iC);
            } else {
                Toast.makeText(this, getString(R.string.file_not_found), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    public void logout1(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }
}
