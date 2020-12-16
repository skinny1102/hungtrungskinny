package com.example.hung_trung.cacdoituong;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

public class GameObject {
    public Point vitri;
    public Point vantoc;
    public Bitmap hinh;

    public GameObject(Resources res, Point vantoc, Point vitri) {
        this.vitri = vitri;
        this.vantoc = vantoc;
    }

    public void ve(Canvas canvas) {
        canvas.drawBitmap(hinh, vitri.x, vitri.y, null);
    }

    public void capnhat() {
        vitri.x = vitri.x + vantoc.x;
        vitri.y = vitri.y + vantoc.y;
    }
}
