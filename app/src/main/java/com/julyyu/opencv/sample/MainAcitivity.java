package com.julyyu.opencv.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.julyyu.opencv.sample.cameracalibration.CameraCalibrationActivity;
import com.julyyu.opencv.sample.colorblobdetect.ColorBlobDetectionActivity;
import com.julyyu.opencv.sample.facedetect.FdActivity;
import com.julyyu.opencv.sample.imagemanipulations.ImageManipulationsActivity;
import com.julyyu.opencv.sample.puzzle15.Puzzle15Activity;
import com.julyyu.opencv.sample.tutorial1.Tutorial1Activity;

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
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListView listView = new ListView(this);
        setContentView(listView);

        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, presidents));
        listView.setOnItemClickListener(this);
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

            default:
                break;
        }

    }
}
