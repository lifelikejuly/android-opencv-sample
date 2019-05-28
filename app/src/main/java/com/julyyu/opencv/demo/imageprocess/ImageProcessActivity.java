package com.julyyu.opencv.demo.imageprocess;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.julyyu.opencv.sample.R;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

/**
 * author : JulyYu
 * date   : 2019-05-28
 */
public class ImageProcessActivity extends AppCompatActivity {

    private final String TAG = "ImageProcessActivity";

    private boolean initLoadSuccess = false;

    private Bitmap bitmap;

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                {
                    initLoadSuccess = true;
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
        setContentView(R.layout.demo_image_variety_view);
        ivAfter = findViewById(R.id.ivAfter);
        ivBefore = findViewById(R.id.ivBefore);
        bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.timg);
        ivBefore.setImageBitmap(bitmap);
    }

    public void blur(View view){
        imageBlur();
    }

    public void binarization(View view){
        imageBinarization();
    }

    public void sharpen(View view){
        imageSharpen();
    }


    public void canny(View view){
        imageCanny();
    }

    public void edge(View view){
        imageEdge();
    }



    private void imageBinarization(){
        mat = new Mat(bitmap.getHeight(),bitmap.getWidth(), CvType.CV_8UC4);
        Utils.bitmapToMat(bitmap,mat);
        //灰度化
        Imgproc.cvtColor(mat,mat,Imgproc.COLOR_BGRA2GRAY);
        //二值化
        Imgproc.threshold(mat,mat,95,255,Imgproc.THRESH_BINARY);
        Bitmap afterBitmap = Bitmap.createBitmap(mat.cols(),mat.rows(),
                Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(mat,afterBitmap);
        ivAfter.setImageBitmap(afterBitmap);
    }

    private void imageBlur(){
        mat = new Mat(bitmap.getHeight(),bitmap.getWidth(),CvType.CV_8UC4);
        Utils.bitmapToMat(bitmap,mat);
        Imgproc.blur(mat,mat,new Size(50,50));
        Bitmap afterBitmap = Bitmap.createBitmap(mat.cols(),mat.rows(),
                Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(mat,afterBitmap);
        ivAfter.setImageBitmap(afterBitmap);
    }

    private void imageSharpen(){
        Mat kenrl = new Mat(5, 5, CvType.CV_16SC1);
        kenrl.put(0, 0,
                -2, -1, 0,
                -1,1, 1,
                0, 1, 2);
        mat = new Mat(bitmap.getHeight(),bitmap.getWidth(),CvType.CV_8UC4);
        Utils.bitmapToMat(bitmap,mat);
        Imgproc.filter2D(mat,mat,mat.depth(),kenrl);
        Bitmap afterBitmap = Bitmap.createBitmap(mat.cols(),mat.rows(),
                Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(mat,afterBitmap);
        ivAfter.setImageBitmap(afterBitmap);
    }

    private void imageCanny(){
        mat = new Mat(bitmap.getHeight(),bitmap.getWidth(),CvType.CV_8UC4);
        Utils.bitmapToMat(bitmap,mat);
        Imgproc.cvtColor(mat,mat,Imgproc.COLOR_BGRA2GRAY);
        Imgproc.Canny(mat,mat,10,100);
        Bitmap afterBitmap = Bitmap.createBitmap(mat.cols(),mat.rows(),
                Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(mat,afterBitmap);
        ivAfter.setImageBitmap(afterBitmap);
    }


    private void imageEdge(){
        Mat Cmat = new Mat();
        Mat Bmat = new Mat();
        mat = new Mat(bitmap.getHeight(),bitmap.getWidth(),CvType.CV_8UC4);
        Utils.bitmapToMat(bitmap,mat);
        Imgproc.Canny(mat,Cmat,30,100);
        Core.bitwise_not(Cmat,Cmat);
        Bitmap afterBitmap = Bitmap.createBitmap(mat.cols(),mat.rows(),
                Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(Cmat,afterBitmap);
        ivAfter.setImageBitmap(afterBitmap);
    }

//    private void imageSketch(){
//        Mat SM = new Mat();
//        Mat SM1 = new Mat();
//        Bitmap sumiaoMap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
//        Bitmap SMB = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
//        Bitmap SMB1 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
//        Utils.bitmapToMat(bitmap, SM);
//        //灰度化
//        Imgproc.cvtColor(SM, SM, Imgproc.COLOR_RGB2GRAY);
//        //颜色取反
//        Core.bitwise_not(SM,SM1);
//        //高斯模糊
//        Imgproc.GaussianBlur(SM1,SM1,new Size(13,13),0,0);
//        Utils.matToBitmap(SM, SMB);
//        Utils.matToBitmap(SM1, SMB1);
//        for(int i = 0;i<SMB.getWidth();i++){
//            for( int j = 0;j<SMB.getHeight();j++){
//                int A = SMB.getPixel(i,j);
//                int B = SMB1.getPixel(i,j);
//                int CR = colordodge(Color.red(A),Color.red(B));
//                int CG = colordodge(Color.green(A),Color.red(B));
//                int CB = colordodge(Color.blue(A),Color.blue(B));
//                sumiaoMap.setPixel(i,j, Color.rgb(CR,CG,CB));
//            }
//        }
//    }


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
