package com.example.hung_trung.game2d;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hung_trung.R;

public class FlashScreen extends Activity {

    MediaPlayer play;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_flash_screen);
        play = MediaPlayer.create(this, R.raw.dance);
        play.setLooping(true);
        play.start();

        TextView tv = (TextView) findViewById(R.id.tvHelps);
        Typeface font = Typeface.createFromAsset(
                this.getAssets(), "starcraft.ttf");
        tv.setTypeface(font);

        TextView tv2 = (TextView) findViewById(R.id.tvTutorial);
        Typeface font2 = Typeface.createFromAsset(this.getAssets(), "starcraft.ttf");
        tv2.setTypeface(font2);

        img = (ImageView) findViewById(R.id.imgConGa);
        img.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                play.stop();
                Intent i = new Intent(FlashScreen.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        final ScaleAnimation al1 = new ScaleAnimation(0.9f, 1.0f, 0.9f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5F, Animation.RELATIVE_TO_SELF, 0.5F);
        al1.setFillAfter(true);
        al1.setFillEnabled(true);
        al1.setDuration(300);
        al1.setInterpolator(new OvershootInterpolator(6f));

        final ScaleAnimation al2 = new ScaleAnimation(0.9f, 1.0f, 0.9f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5F, Animation.RELATIVE_TO_SELF, 0.5F);
        al2.setFillAfter(true);
        al2.setFillEnabled(true);
        al2.setDuration(300);
        al2.setInterpolator(new OvershootInterpolator(6f));

        al2.setAnimationListener(new AnimationListener() {

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
                img.startAnimation(al1);
            }
        });

        al1.setAnimationListener(new AnimationListener() {

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
                img.startAnimation(al2);
            }
        });

        img.startAnimation(al1);
    }


    @Override
    protected void onDestroy() {
        play.stop();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        play.start();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        play.pause();
        super.onPause();
    }
}
