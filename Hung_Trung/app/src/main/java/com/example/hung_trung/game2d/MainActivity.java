package com.example.hung_trung.game2d;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.example.hung_trung.R;
import com.example.hung_trung.thuvien.GamePanel;

public class MainActivity extends Activity {

    GamePanel gamePanel;
    /**Timer t;
    TimerTask task;
    Boolean an = true;*/
    FrameLayout ln;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        GamePanel.maxWidth = size.x;
        GamePanel.maxHeight = size.y;

        gamePanel = new GamePanel(MainActivity.this);

        setContentView(R.layout.activity_main);

        ln = (FrameLayout)findViewById(R.id.layout1);
        ln.addView(gamePanel, 0);
    }


    protected void onResume(){
        super.onResume();
        gamePanel.chay();
    }

    protected void onPause(){
        super.onPause();
        gamePanel.stop();
    }
}
