package com.example.hung_trung.cacdoituong;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import com.example.hung_trung.R;

public class CaiRo extends GameObject {
    Paint p;

    public CaiRo(Resources res, Point vantoc, Point vitri) {
        super(res, vantoc, vitri);
        this.hinh = BitmapFactory.decodeResource(res, R.drawable.cairo);
        vitri.x = vitri.x - hinh.getWidth() / 2;
        vitri.y = vitri.y - hinh.getHeight() / 2;

        p = new Paint();
        p.setColor(Color.RED);
        p.setAlpha(90);
    }

    @Override
    public void ve(Canvas canvas) {
        // TODO Auto-generated method stub
        super.ve(canvas);

        //canvas.drawCircle(vitri.x + hinh.getWidth() / 2, vitri.y+hinh.getHeight() / 2, hinh.getWidth() / 2, p);
    }
}