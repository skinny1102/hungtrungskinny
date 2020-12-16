package com.example.hung_trung.cacdoituong;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;

import com.example.hung_trung.R;
import com.example.hung_trung.thuvien.GamePanel;


public class Nen extends GameObject {

    private int toadonen1_X = 0;
    private int toadonen2_X = 0;
    private Bitmap hinhnen2;
    private Bitmap hinhnen1;

    public Nen(Resources res, Point vantoc, Point vitri) {
        super(res, vantoc, vitri);
        hinhnen1 = BitmapFactory.decodeResource(res, R.drawable.skylayer);
        hinhnen1 = getResizedBitmap(hinhnen1, GamePanel.maxHeight, GamePanel.maxWidth);
        hinhnen2 = BitmapFactory.decodeResource(res, R.drawable.groundlayer);
        hinhnen2 = getResizedBitmap(hinhnen2, GamePanel.maxHeight, GamePanel.maxWidth);
    }

    @Override
    public void ve(Canvas canvas) {
        // giam toa do de dich chuyen cho nen1
        toadonen1_X = toadonen1_X - 1;
        // giam toa do de dich chuyen cho nen2
        toadonen2_X = toadonen2_X - 4;
        // tinh do lech cho hinh 2
        int toadonen1_phu_X = hinhnen1.getWidth() - (-toadonen1_X);
        // da di chuyen het thi quay lai tu dau
        if (toadonen1_phu_X <= 0) {
            toadonen1_X = 0;
            // chi can ve 1 tam
            canvas.drawBitmap(hinhnen1, 0, 0, null);
        } else {
            // ve 1 tam lech va tam 2 noi duoi theo
            canvas.drawBitmap(hinhnen1, toadonen1_X, 0, null);
            canvas.drawBitmap(hinhnen1, toadonen1_phu_X, 0, null);
        }
        int toadonen2_phu_X = hinhnen2.getWidth() - (-toadonen2_X);
        if (toadonen2_phu_X <= 0) {
            toadonen2_X = 0;
            canvas.drawBitmap(hinhnen2, toadonen2_X, 0, null);
        } else {
            canvas.drawBitmap(hinhnen2, toadonen2_X, 0, null);
            canvas.drawBitmap(hinhnen2, toadonen2_phu_X, 0, null);
        }
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        // tính tỉ lệ co giãn theo 02 chiều
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        float scale = scaleWidth > scaleHeight ? scaleWidth : scaleHeight;

        // tạo ma trận điểm ảnh
        Matrix matrix = new Matrix();
        // scale ảnh
        matrix.postScale(scale, scale);
        // tạo ảnh mới đã co/giãn
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
        return resizedBitmap;
    }

}
