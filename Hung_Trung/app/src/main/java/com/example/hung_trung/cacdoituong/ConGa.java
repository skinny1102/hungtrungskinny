package com.example.hung_trung.cacdoituong;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.media.MediaPlayer;
import com.example.hung_trung.R;
import com.example.hung_trung.thuvien.GamePanel;
import java.util.ArrayList;
import java.util.Random;

public class ConGa extends GameObject {
    public ArrayList<TrungGa> trung = new ArrayList<TrungGa>();

    int top = 150;
    int thoigian = 45;

    Resources res;
    Random ran = new Random();

    Context context;
    MediaPlayer play;

    public ConGa(Resources res, Point vantoc, Point vitri, Context c) {
        super(res, vantoc, vitri);
        this.res = res;
        this.hinh = BitmapFactory.decodeResource(res, R.drawable.conga);

        vitri.x = vitri.x - hinh.getWidth() / 2;
        vitri.y = vitri.y - hinh.getHeight() / 2;

        context = c;
        play = MediaPlayer.create(context, R.raw.tienggamai);
    }

    @Override
    public void capnhat() {
        super.capnhat();
        vantoc.y = 0;
        thoigian -= 1;

        if (vitri.y + hinh.getHeight() > GamePanel.maxHeight -200) {
            vantoc.x = 0;
        }

        /**if (vitri.x + hinh.getWidth() > GamePanel.maxWidth || vitri.x < 0) {
         this.vantoc.x = -this.vantoc.x;
         }*/

        if (vitri.x + hinh.getWidth() > GamePanel.maxWidth){
            vantoc.x = -2;
        }

        if (vitri.x < 0){
            vantoc.x = 2;
        }


        if (thoigian-- == 0) {
            TrungGa trungga = new TrungGa(res, new Point(0, (int) (10 * ran.nextFloat() + 5) * GamePanel.capDo), new Point(vitri.x + hinh.getWidth() / 2, vitri.y + hinh.getWidth()));
            trung.add(trungga);
            thoigian = 45;

            play.start();
            // ghi nhận loại trứng
            switch (trungga.diem) {
                case 1:
                    GamePanel.soTrungGa++;
                    break;
                case 2:
                    GamePanel.soTrungVang++;
                    break;
                case 3:
                    GamePanel.soConGa++;
                    break;
                case -2:
                    GamePanel.soPhanGa++;
                    break;
                default:
                    break;
            }
        }

        for (int j = trung.size() - 1; j >= 0; j--) {
            trung.get(j).capnhat();
            if (trung.get(j).vantoc.y == 0) {
                if (trung.get(j).trongro) {
                    // hứng con gà sẽ bay lên
                    if (trung.get(j).diem >= 3)
                        vitri.y -= GamePanel.capDo * trung.get(j).diem;

                    if (vitri.y < top)
                        vitri.y = top;
                    // xóa trứng
                    trung.remove(j);
                    continue;
                }

                // trung vo
                if (trung.get(j).diem > 0 && trung.get(j).count == 29) {
                    vitri.y += 50 * GamePanel.capDo * trung.get(j).diem;
                }

                if (trung.get(j).count <= 0) {
                    trung.remove(j);
                }
            }
        }


    }
}
