package com.julyyu.opencv.graphics;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.julyyu.opencv.sample.R;

import org.opencv.core.Mat;

/**
 * author : JulyYu
 * date   : 2019-05-30
 */
public class RandomImageActivity extends AppCompatActivity {

    private ImageView ivView;
    private Bitmap bitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graphics_image_view);
        Mat mat = new Mat();
        ivView = findViewById(R.id.ivView);
        bitmap = Bitmap.createBitmap(300,300,Bitmap.Config.ARGB_8888);
        bitmap = inputDatas(bitmap);
        ivView.setImageBitmap(bitmap);
    }




    public Bitmap inputDatas(Bitmap image) {
        //得到图像的宽度和长度
        int width = image.getWidth();
        int height = image.getHeight();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                //得到每点的像素值
                int col = image.getPixel(i, j);
                int alpha = col | 0xFF000000;
                int red = (col & 0x00FF0000) >> 16;
                int green = (col & 0x0000FF00) >> 8;
                int blue = (col & 0x000000FF);
                red = (int) (Math.random() * 255);
                green = (int) (Math.random() * 255);
                blue = (int)  (Math.random() * 255);
                //对图像像素越界进行处理
                if (red >= 255)
                {
                    red = 255;
                }

                if (green >= 255) {
                    green = 255;
                }

                if (blue >= 255) {
                    blue = 255;
                }
                // 新的ARGB
                int newColor = alpha | (red << 16) | (green << 8) | blue;
                //设置新图像的RGB值
                image.setPixel(i, j, newColor);
            }
        }
        return image;
    }

    public void update(View view){
        bitmap = Bitmap.createBitmap(300,300,Bitmap.Config.ARGB_8888);
        bitmap = inputDatas(bitmap);
        ivView.setImageBitmap(bitmap);
    }
}
