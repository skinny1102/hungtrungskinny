package com.example.hung_trung.game2d;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hung_trung.R;
import com.example.hung_trung.thuvien.GamePanel;


public class GameOver extends Activity {
    MediaPlayer p;
    ScaleAnimation in, out;
    ImageView conga;

    SharedPreferences pre;
    SharedPreferences.Editor edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        pre = getPreferences(Context.MODE_PRIVATE);
        edt = pre.edit();

        if (pre.getInt("diem", 0) < GamePanel.soDiem){
            //thong bao diem moi
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(GameOver.this);
            alertDialog.setTitle("THÔNG BÁO");
            alertDialog.setMessage("Bạn đã là quán quân! \n"+ "Kỉ lục mới: " +GamePanel.soDiem+ "\nKỉ lục: "+pre.getInt("diem", 0));
            alertDialog.setPositiveButton("Đóng lại!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    edt.putInt("diem", GamePanel.soDiem);
                    edt.commit();
                    dialog.cancel();
                }
            });
            alertDialog.show();
        }else{
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(GameOver.this);
            alertDialog.setTitle("THÔNG BÁO");
            alertDialog.setMessage("Điểm của bạn: " +GamePanel.soDiem+ "\nKỉ lục: "+pre.getInt("diem", 0));
            alertDialog.setPositiveButton("Đóng lại", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            alertDialog.show();
        }


        p = MediaPlayer.create(getApplicationContext(), R.raw.dance);

        Typeface font = Typeface.createFromAsset(this.getAssets(), "starcraft.ttf");
        ((TextView)findViewById(R.id.textView)).setTypeface(font);
        ((TextView)findViewById(R.id.textView2)).setTypeface(font);

        conga = (ImageView)findViewById(R.id.imageView);

        conga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GamePanel.reset();
                Intent i = new Intent(GameOver.this, FlashScreen.class);
                startActivity(i);
                finish();
            }
        });

        in = new ScaleAnimation(0.8f, 1.2f, 0.8f, 1.2f,  Animation.RELATIVE_TO_SELF, 0.5F, Animation.RELATIVE_TO_SELF, 0.5F);
        in.setDuration(200);
        out = new ScaleAnimation(1.2f, 0.8f, 1.2f, 0.8f,  Animation.RELATIVE_TO_SELF, 0.5F, Animation.RELATIVE_TO_SELF, 0.5F);
        out.setDuration(200);

        in.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                conga.startAnimation(out);
            }
        });

        out.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                conga.startAnimation(in);
            }
        });
        conga.startAnimation(in);
    }
}
