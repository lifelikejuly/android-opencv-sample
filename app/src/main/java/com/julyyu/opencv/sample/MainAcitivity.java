package com.julyyu.opencv.sample;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.julyyu.opencv.demo.imageblur.ImageBlurActivity;
import com.julyyu.opencv.sample.cameracalibration.CameraCalibrationActivity;
import com.julyyu.opencv.sample.colorblobdetect.ColorBlobDetectionActivity;
import com.julyyu.opencv.sample.facedetect.FdActivity;
import com.julyyu.opencv.sample.imagemanipulations.ImageManipulationsActivity;
import com.julyyu.opencv.sample.puzzle15.Puzzle15Activity;
import com.julyyu.opencv.sample.tutorial1.Tutorial1Activity;
import com.julyyu.opencv.sample.tutorial2.Tutorial2Activity;
import com.julyyu.opencv.sample.tutorial3.Tutorial3Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * author : JulyYu
 * date   : 2019/2/20
 */
public class MainAcitivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private String[] presidents = {
            "puzzle15",
            "cameracalibration",
            "colorblobdetect",
            "facedetect",
            "imagemanipulations",
            "tutorial1",
            "tutorial2",
            "tutorial3",
            "imageblur"
    };

    private String[] permissions ={
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListView listView = new ListView(this);
        setContentView(listView);

        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, presidents));
        listView.setOnItemClickListener(this);

        requestPermission(permissions,1);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        startActivity(new Intent(this,FdActivity.class));
        switch (position) {
            case 0:
                startActivity(new Intent(this, Puzzle15Activity.class));
                break;
            case 1:
                startActivity(new Intent(this, CameraCalibrationActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, ColorBlobDetectionActivity.class));
                break;
            case 3:
                startActivity(new Intent(this, FdActivity.class));
                break;
            case 4:
                startActivity(new Intent(this, ImageManipulationsActivity.class));
                break;
            case 5:
                startActivity(new Intent(this, Tutorial1Activity.class));
                break;
            case 6:
                startActivity(new Intent(this, Tutorial2Activity.class));
                break;
            case 7:
                startActivity(new Intent(this, Tutorial3Activity.class));
                break;
            case 8:
                startActivity(new Intent(this,ImageBlurActivity.class));
                break;
            default:
                break;
        }

    }

    private boolean requestPermission(String[] permissions, int requestCode) {
        //判断当前Activity是否已经获得了该权限
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        List<String> DeniedPermission = new ArrayList<>();
        for(String permission : permissions){
            if (ContextCompat.checkSelfPermission(this,
                    permission)
                    != PackageManager.PERMISSION_GRANTED) {
                DeniedPermission.add(permission);
            }
        }
        if(DeniedPermission.size() > 0){
            ActivityCompat.requestPermissions(this,(String[])DeniedPermission.toArray(),
                    requestCode);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for(int i =0;i < grantResults.length ;i++){
            if(grantResults[0] != PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"NO PERMISSION",Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}
