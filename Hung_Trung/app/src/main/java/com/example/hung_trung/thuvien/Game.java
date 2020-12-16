package com.example.hung_trung.thuvien;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import java.util.Timer;
import java.util.TimerTask;

public class Game extends Thread {

    SurfaceHolder surfaceHolder;
    GamePanel gamePanel;

    boolean isRunning;

    public Game(SurfaceHolder surfaceHolder, GamePanel gamePanel) {
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    @Override
    public void run() {
        super.run();

        Timer mainTimer = new Timer();
        TimerTask task = new TimerTask() {
            int count = 0;
            Canvas canvas = null;

            @Override
            public void run() {
                if (isRunning) {
                    canvas = surfaceHolder.lockCanvas();
                    if (canvas != null) {
                        gamePanel.capnhat();
                        gamePanel.onDraw(canvas);
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }

                } else {
                    this.cancel();
                }

            }
        };

        mainTimer.scheduleAtFixedRate(task, 0, 1000 / 30);

    }
}
