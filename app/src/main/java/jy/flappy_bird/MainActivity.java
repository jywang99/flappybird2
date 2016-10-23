package jy.flappy_bird;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Point;
import android.media.Image;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends ActionBarActivity {

    Timer mTimer;
    Timer nTimer;
    int mTime = 0;
    Handler handler;
    Handler handler2;
    ImageView top;
    ImageView bottom;
    ImageView top2;
    ImageView bottom2;
    ImageView bird;
    int position;
    int width = 0;
    int height = 0;
    int check = 1;
    int nTime = 0;
    TextView gameover;
    Button startbutton;
    int score;
    TextView currentscore;
    ImageView life1;
    ImageView life2;
    ImageView life3;
    int life = 3;
    Boolean a = false;
    int endingtime = 0;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        width = findViewById(R.id.relative).getWidth();
        height = findViewById(R.id.relative).getWidth();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RelativeLayout relative = (RelativeLayout) findViewById(R.id.relative);
        top = new ImageView(this);
        bottom = new ImageView(this);
        top2 = new ImageView(this);
        bottom2 = new ImageView(this);
        bird = new ImageView(this);
        startbutton = (Button) findViewById(R.id.startbutton);
        currentscore = (TextView) findViewById(R.id.score);
        bird.setImageResource(R.drawable.bird);
        bird.setX(width / 2);
        bird.setY(height / 2 + 400);
        gameover = (TextView) findViewById(R.id.textView);
        life1 = (ImageView) findViewById(R.id.imageView);
        life1.setImageResource(R.drawable.life);
        life2 = (ImageView) findViewById(R.id.imageView2);
        life2.setImageResource(R.drawable.life);
        life3 = (ImageView) findViewById(R.id.imageView3);
        life3.setImageResource(R.drawable.life);
    }

    public void start(View V) {
        gameover.setText(" ");
        final RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        final RelativeLayout relative = (RelativeLayout) findViewById(R.id.relative);
        relative.addView(bird);
        relative.removeView(startbutton);
        mTimer = new Timer(false);
        handler = new Handler();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        mTime++;
                        if (mTime % 300 == 0) {
                            a = true;
                            Random random = new Random();
                            position = random.nextInt(10);
                            int bottomposition = height + position * 40;
                            top.setY(bottomposition - 2000);
                            bottom.setY(bottomposition);
                            top.setImageResource(R.drawable.top);
                            bottom.setImageResource(R.drawable.bottom);
                            top.setLayoutParams(params);
                            bottom.setLayoutParams(params);
                            top.setX(width);
                            bottom.setX(width);
                            relative.removeView(top);
                            relative.removeView(bottom);
                            relative.addView(top);
                            relative.addView(bottom);
                        }
                        if (mTime % 320 == 0) {
                            a = false;
                        }
                        if (mTime % 150 == 0 && !a) {
                            Random random = new Random();
                            position = random.nextInt(10);
                            int bottomposition = height + position * 40;
                            top2.setY(bottomposition - 2000);
                            bottom2.setY(bottomposition);
                            top2.setImageResource(R.drawable.top);
                            bottom2.setImageResource(R.drawable.bottom);
                            top2.setLayoutParams(params);
                            bottom2.setLayoutParams(params);
                            top2.setX(width);
                            bottom2.setX(width);
                            relative.removeView(top2);
                            relative.removeView(bottom2);
                            relative.addView(top2);
                            relative.addView(bottom2);
                        }

                        top.setX(top.getX() - 4);
                        bottom.setX(bottom.getX() - 4);
                        top2.setX(top2.getX() - 4);
                        bottom2.setX(bottom2.getX() - 4);

                        if (top.getX() < -width) {
                            relative.removeView(top);
                            relative.removeView(bottom);
                        }
                        if (top2.getX() < -width) {
                            relative.removeView(top2);
                            relative.removeView(bottom2);
                        }

                        float birdedgeX = bird.getX() + bird.getWidth();
                        float birdtopY = bird.getY();
                        float birdbottomY = bird.getY() + bird.getHeight();
                        float pipeedgeX = top.getX();
                        float topbottomY = top.getY() + top.getHeight();
                        float bottomtopY = bottom.getY();
                        float pipeedgeX2 = top2.getX();
                        float topbottomY2 = top2.getY() + top2.getHeight();
                        float bottomtopY2 = bottom2.getY();

                        if ((pipeedgeX + 2 >= birdedgeX && pipeedgeX - 2 <= birdedgeX)) {
                            if (life > 0) {
                                addscore();
                            }
                            Log.e("birdtopY", String.valueOf(birdtopY));
                            Log.e("birdbottomY", String.valueOf(birdbottomY));
                            Log.e("topbottomY", String.valueOf(topbottomY));
                            Log.e("bottomtopY", String.valueOf(bottomtopY));
                            if (birdtopY + 8 < topbottomY || birdbottomY - 8 > bottomtopY) {
                                life--;
                            }
                        }
                        if ((pipeedgeX2 + 2 >= birdedgeX && pipeedgeX2 - 2 <= birdedgeX)) {
                            if (life > 0) {
                                addscore();
                            }
                            Log.e("birdtopY", String.valueOf(birdtopY));
                            Log.e("birdbottomY", String.valueOf(birdbottomY));
                            Log.e("topbottomY2", String.valueOf(topbottomY2));
                            Log.e("bottomtopY2", String.valueOf(bottomtopY2));
                            if (birdtopY + 8 < topbottomY2 || birdbottomY - 8 > bottomtopY2) {
                                life--;
                            }
                        }

                        switch (check) {
                            case 1:
                                bird.setY(gravity(bird.getY()));
                                break;
                            case 0:
                                bird.setY(flying(bird.getY()));
                                break;
                        }

                        if (life == 2) {
                            life3.setImageResource(R.drawable.dead);
                        } else if (life == 1) {
                            life2.setImageResource(R.drawable.dead);
                        } else if (life == 0||bird.getY()<0) {
                            life1.setImageResource(R.drawable.dead);
                            life2.setImageResource(R.drawable.dead);
                            life3.setImageResource(R.drawable.dead);
                            relative.removeView(bird);
                            gameover.setText("GAME OVER");
                            mTimer.cancel();
                            relative.addView(startbutton);
                            life = 3;
                            life1.setImageResource(R.drawable.life);
                            life2.setImageResource(R.drawable.life);
                            life3.setImageResource(R.drawable.life);
                            relative.removeView(top);
                            relative.removeView(bottom);
                            relative.removeView(top2);
                            relative.removeView(bottom2);
                            score=0;
                            bird.setY(height/2+400);
                        }
                    }
                });
            }
        }, 0, 10);


    }


    public void fly(View V) {

        if (check == 1) {
            check = 0;
            nTime = 0;
            nTimer = new Timer(false);
            handler2 = new Handler();
            nTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler2.post(new Runnable() {
                        @Override
                        public void run() {
                            nTime += 10;
                            if (nTime == 200) {
                                nTimer.cancel();
                                endingtime = mTime;
                                check = 1;
                            }
                        }
                    });
                }
            }, 0, 10);
        } else {
            nTime = 0;
        }

    }

    public float gravity(float y) {
        int duration = mTime-endingtime;
        return y+y*duration*duration*0.5f*0.000098f;

    }

    public float flying(float h) {
        return h - 14;
    }

    public void addscore() {
        score += 1;
        if (score < 20) {
            currentscore.setText("SCORE:" + " 0" + score / 2);
        } else {
            currentscore.setText("SCORE:" + " " + score / 2);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
