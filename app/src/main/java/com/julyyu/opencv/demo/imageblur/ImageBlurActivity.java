package com.julyyu.opencv.demo.imageblur;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.julyyu.opencv.sample.R;
import com.julyyu.opencv.sample.cameracalibration.CameraCalibrationActivity;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

/**
 * author : JulyYu
 * date   : 2019/3/2
 */
public class ImageBlurActivity extends AppCompatActivity {

    private final String TAG = "ImageBlurActivity";


    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                {
                    imageBlur();
                    Log.i(TAG, "OpenCV loaded successfully");
                } break;
                default:
                {
                    super.onManagerConnected(status);
                } break;
            }
        }
    };


    private ImageView ivBefore;
    private ImageView ivAfter;

    private Mat mat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_blur_view);
        ivAfter = findViewById(R.id.ivAfter);
        ivBefore = findViewById(R.id.ivBefore);
    }

    private void imageBlur(){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.timg);
        ivBefore.setImageBitmap(bitmap);
        mat = new Mat(bitmap.getHeight(),bitmap.getWidth(),CvType.CV_8UC4);
        Utils.bitmapToMat(bitmap,mat);
        Imgproc.blur(mat,mat,new Size(5,3));
        Bitmap afterBitmap = Bitmap.createBitmap(mat.cols(),mat.rows(),
                Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(mat,afterBitmap);
        ivAfter.setImageBitmap(afterBitmap);
    }


    @Override
    public void onResume()
    {
        super.onResume();
        if (!OpenCVLoader.initDebug()) {
            Log.d(TAG, "Internal OpenCV library not found. Using OpenCV Manager for initialization");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, mLoaderCallback);
        } else {
            Log.d(TAG, "OpenCV library found inside package. Using it!");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }
}
