package com.example.hung_trung.cacdoituong;

import java.util.Random;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import com.example.hung_trung.R;
import com.example.hung_trung.thuvien.GamePanel;

public class TrungGa extends GameObject {

    public int diem = 1;
    public boolean trongro;
    public int count = 50;

    Random ran = new Random();
    Paint p;

    Resources res;

    public TrungGa(Resources res, Point vantoc, Point vitri) {
        super(res, vantoc, vitri);

        this.res = res;
        if (ran.nextFloat() < 0.5F) {
            diem = -2;
            this.hinh = BitmapFactory.decodeResource(res, R.drawable.phanga);
        } else if (ran.nextFloat() < 0.7F) {
            diem = 1;
            this.hinh = BitmapFactory.decodeResource(res, R.drawable.trungga);
        } else if (ran.nextFloat() < 0.9F) {
            diem = 2;
            this.hinh = BitmapFactory.decodeResource(res, R.drawable.trungvang);
        } else {
            diem = 3;
            this.hinh = BitmapFactory.decodeResource(res, R.drawable.congatihon);
        }

        if (diem > 0)
            vantoc.y = vantoc.y*diem/2;

        vitri.x = vitri.x - hinh.getWidth() / 2;
        vitri.y = vitri.y - hinh.getHeight() / 2;

        p = new Paint();
        p.setColor(Color.RED);
        p.setAlpha(80);
        p.setTextSize(32);
    }

    @Override
    public void capnhat() {
        super.capnhat();
        vantoc.x = 0;

        if (vitri.y + hinh.getHeight() >= GamePanel.maxHeight - 200) {
            this.vantoc.y = 0;
            if (diem > 0) {
                this.hinh = BitmapFactory.decodeResource(res, R.drawable.trungvo);
            } else
                count = 0;
        }

        if (vantoc.y == 0)
            count--;
    }

    @Override
    public void ve(Canvas canvas) {
        super.ve(canvas);

        //canvas.drawText(count+"",vitri.x + hinh.getWidth()/2, vitri.y+hinh.getHeight() / 2, p);
    }
}