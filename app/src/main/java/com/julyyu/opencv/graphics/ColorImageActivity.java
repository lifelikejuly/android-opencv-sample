package com.julyyu.opencv.graphics;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.julyyu.opencv.sample.R;

/**
 * author : JulyYu
 * date   : 2019-05-30
 */
public class ColorImageActivity extends AppCompatActivity {

    private ImageView ivView;
    private Bitmap bitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.timg);
        setContentView(R.layout.graphics_image_view);
        ivView = findViewById(R.id.ivView);
        Bitmap bitmap2 = inputDatas(bitmap);
        ivView.setImageBitmap(bitmap2);
    }


    public Bitmap inputDatas(Bitmap image) {
        //得到图像的宽度和长度
        int width = image.getWidth();
        int height = image.getHeight();
        //创建二值化图像
        Bitmap binarymap = null;
        binarymap = image.copy(Bitmap.Config.ARGB_8888, true);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                //得到每点的像素值
                int col = binarymap.getPixel(i, j);
                //得到alpha通道的值
                int alpha = col & 0xFF000000;
                //得到图像的像素RGB的值
                int red = (col & 0x00FF0000) >> 16 + 30;
                int green = (col & 0x0000FF00) >> 8 + 30 ;
                int blue = (col & 0x000000FF) + 30;//对图像像素越界进行处理
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
                //设置新图像的当前像素值
                binarymap.setPixel(i, j, newColor);
            }
        }
        return binarymap;
    }



}
