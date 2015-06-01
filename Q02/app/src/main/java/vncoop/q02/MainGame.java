package vncoop.q02;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Bundle;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;


public class MainGame extends Activity implements Animation.AnimationListener {


    String[] epiloges = new String[2];
    int current_team;
    int number_of_teams;
    parcTeams[] teams;
    ImageButton firstbtn;
    ImageButton secondbtn;
    ImageButton diamondbtn;
    ImageButton spinbtn;
    ImageView[] diamond_images;
    boolean[] current_diamonds;
    int teamColor;
    private int fileIndex;

    Animation MoveRightFAdeIn;
    Animation MoveLeftFAdeIn;
    Animation ScaleFade;
    Typeface font;
    TextView Odigies;

    public TextView OmadaTxt;

    MediaPlayer s1;
    MediaPlayer s2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        s1= MediaPlayer.create(this, R.raw.selec_click);
        s2= MediaPlayer.create(this, R.raw.selec_click2);

        diamond_images = new ImageView[6];

        diamond_images[0] = (ImageView) findViewById(R.id.blueDiamId);
        diamond_images[1] = (ImageView) findViewById(R.id.pinkDiamId);
        diamond_images[2] = (ImageView) findViewById(R.id.redDiamId);
        diamond_images[3] = (ImageView) findViewById(R.id.purpleDiamId);
        diamond_images[4] = (ImageView) findViewById(R.id.greenDiamId);
        diamond_images[5] = (ImageView) findViewById(R.id.yellowDiamId);

        ImageView diamondsTable = (ImageView) findViewById(R.id.TableId);

        //INTENTS FROM INITIALIZE
        Intent intent = getIntent();
        current_team = intent.getIntExtra("current_message", 1);
        number_of_teams = intent.getIntExtra("number_of_teams", 1);
        teams = new parcTeams[number_of_teams];
        for (int i = 0; i < number_of_teams; i++) {
            teams[i] = intent.getParcelableExtra("team" + i);
        }
        current_diamonds = teams[current_team].get_diamonds();
        fileIndex =intent.getIntExtra("file_index",0);


        double screenWidth,screenHeight,statusBarHeight,Left,Top,Right,Bottom,screenDensity;

        OmadaTxt = (TextView) findViewById(R.id.textViewOmada);
        Odigies = (TextView) findViewById(R.id.instructions);
        spinbtn = (ImageButton) findViewById(R.id.btnSpin);
        ImageButton button1 = (ImageButton)findViewById(R.id.btnCat1Id);
        ImageButton button2 = (ImageButton)findViewById(R.id.btnCat2Id);
        TextView Cattxt1 = (TextView) findViewById(R.id.textCat1);
        TextView Cattxt2 = (TextView) findViewById(R.id.textCat2);
        diamondbtn = (ImageButton) findViewById(R.id.btnDiaId);
        TextView Diatxt = (TextView) findViewById(R.id.textDiam);
        font = Typeface.createFromAsset(getAssets(), "VAG-HandWritten.otf");
        ImageView separator = (ImageView) findViewById(R.id.seperator);
        ImageView separator2 = (ImageView) findViewById(R.id.seperator2);

        //Screen characteristics
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth = (double) dm.widthPixels;
        screenHeight = (double) dm.heightPixels;
        screenDensity = (double) dm.density;

        statusBarHeight = (double) getStatusBarHeight();
        screenHeight -= statusBarHeight;

        //Setting Team name\\
        OmadaTxt.setTypeface(font);
        OmadaTxt.setText(teams[current_team].get_name());
        Top = (0.035* screenHeight);
        Left = 0.05*screenWidth;
        Bottom = (1-0.135)*screenHeight;
        setMargins(OmadaTxt,(int) Left,(int) Top,(int) Left,(int) Bottom);
        refitText(OmadaTxt,(float) ((0.09 / screenDensity) * screenHeight),(int)(0.9*screenWidth));

        //Separator Margins
        Top = (0.15)*screenHeight;
        Bottom = (0.83125)*screenHeight;
        setMargins(separator,0,(int) Top,0,(int) Bottom);

        //Setting instructions\\
        Odigies.setTypeface(font);
        Top = ((0.3-0.05)* screenHeight);
        setMargins(Odigies,0,(int) Top,0,0);
        Odigies.setTextSize((float) ((0.058/screenDensity)*screenHeight));

        //Setting spin button\\
        Left = (0.344626168*screenWidth);
        Top = ((0.4692367-0.05)*screenHeight);
        Right = Left;
        Bottom = ((0.32320872+0.05)*screenHeight);
        setMargins(spinbtn,(int) Left,(int) Top, (int) Right,(int) Bottom);

        //Setting choices 1 and 2
        Left = ((0.0946262)*screenWidth);
        Right=((0.594042)*screenWidth);
        setMargins(button1,(int) Left,(int) Top, (int) Right,(int) Bottom);
        setMargins(button2,(int) Right,(int) Top, (int) Left,(int) Bottom);

        //Setting Category Texts
        Top = screenHeight-Bottom;
        Left = ((0.0946262)*screenWidth);
        Right=((0.594042)*screenWidth);
        setMargins(Cattxt1,(int) Left,(int) Top, (int) Right,0);
        setMargins(Cattxt2,(int) Right,(int) Top, (int) Left,0);
        Cattxt1.setTextSize((float) ((0.052/screenDensity)*screenHeight));
        Cattxt2.setTextSize((float) ((0.052/screenDensity)*screenHeight));

        //Setting Diamond
        Left = (0.2178738*screenWidth);
        Top = ((0.4692367-0.08)*screenHeight);
        Right = Left;
        Bottom = ((0.21300623+0.08)*screenHeight);
        setMargins(diamondbtn,(int) Left,(int) Top, (int) Right,(int) Bottom);

        //Setting Diamond Text
        Top = screenHeight-Bottom;
        Bottom = 0.23*screenHeight;
        setMargins(Diatxt,(int) Left,(int) Top, (int) Right,(int) Bottom);
        Diatxt.setTextSize((float) ((0.058/screenDensity)*screenHeight));

        //Setting Bottom Separator
        Top = 0.85*screenHeight;
        Bottom = 0.13125*screenHeight;
        setMargins(separator2,0,(int) Top,0,(int) Bottom);

        //Setting Diamonds
        Top = (0.86875)*screenHeight;
        Bottom =(0.015)*screenHeight;
        Left=0.05*screenWidth;
        setMargins(diamondsTable,(int) Left,(int) Top,(int) Left,(int) Bottom);

        for (int i=0;i<6;i++){
            setMargins(diamond_images[i],(int) Left,(int) Top,(int) Left,(int) Bottom);
        }

        //telos


        //Τυχαία επιλογή κατηγοριών ή διαμαντιών\\
        randGen rg = new randGen();
        epiloges = rg.getRandomCategories(current_diamonds);


        teamColor = teams[current_team].get_color();


        int backId = getResources().getIdentifier(intColorToString(teamColor) + "_back", "drawable", getPackageName());
        RelativeLayout Layoutc = (RelativeLayout) findViewById(R.id.something);
        Layoutc.setBackgroundResource(backId);
        for (int i = 0; i < 6; i++) {
            if (current_diamonds[i]) {
                diamond_images[i].setVisibility(View.VISIBLE);
            }
        }




        //telos


        //FOrtwse ta animation

        MoveRightFAdeIn = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.move_fade2);


        MoveRightFAdeIn.setAnimationListener(this);

        MoveLeftFAdeIn = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.move_fade1);


        MoveLeftFAdeIn.setAnimationListener(this);

        ScaleFade = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.scale_fade);

        ScaleFade.setAnimationListener(this);


    }



    public void getCatOrD(View view) {
        firstbtn = (ImageButton) findViewById(R.id.btnCat1Id);
        secondbtn = (ImageButton) findViewById(R.id.btnCat2Id);
        diamondbtn = (ImageButton) findViewById(R.id.btnDiaId);
        spinbtn = (ImageButton) findViewById(R.id.btnSpin);

        //test
        Odigies.setVisibility(View.INVISIBLE);

        //spin animation

        spinbtn.setImageResource(R.drawable.spin_animation);
        AnimationDrawable frameAnimation = (AnimationDrawable) spinbtn.getDrawable();
        int didd;
        if (epiloges[1].equals("naS")) {
            didd = getResources().getIdentifier(epiloges[0]+"_b", "drawable", getPackageName());
        }else{
            didd = getResources().getIdentifier(epiloges[0], "drawable", getPackageName());
        }
        frameAnimation.addFrame(getResources().getDrawable(didd), 50);
        frameAnimation.start();
        //

        final Handler handler = new Handler();

        //sounds
        if (epiloges[1].equals("naS")) {
        s1.start();}else{
        s2.start();}
        //

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 1000ms

                if (epiloges[1].equals("naS")) {
                    int didd = getResources().getIdentifier(epiloges[0] + "_selector", "drawable", getPackageName());
                    diamondbtn.setImageResource(didd);
                    diamondbtn.setVisibility(View.VISIBLE);
                    diamondbtn.setAnimation(ScaleFade);
                } else {


                    int did1 = getResources().getIdentifier(epiloges[0] + "_selector", "drawable", getPackageName());
                    firstbtn.setImageResource(did1);
                    firstbtn.setVisibility(View.VISIBLE);
                    firstbtn.setAnimation(MoveLeftFAdeIn);

                    int did2 = getResources().getIdentifier(epiloges[1] + "_selector", "drawable", getPackageName());
                    secondbtn.setImageResource(did2);
                    secondbtn.setVisibility(View.VISIBLE);
                    secondbtn.setAnimation(MoveRightFAdeIn);
                }
                spinbtn.setVisibility(View.INVISIBLE);
                spinbtn.setEnabled(false);

                //emfanizei ta text
                //if diamanti
                if (epiloges[1].equals("naS")) {

                    TextView Diatxt = (TextView) findViewById(R.id.textDiam);
                    Diatxt.setTypeface(font);
                    Diatxt.setText(catToText(epiloges[0] + "_b"));
                    Diatxt.setVisibility(View.VISIBLE);
                    Odigies.setVisibility(View.VISIBLE);
                    Odigies.setText("Απαντήστε σωστα για να κερδίσετε διαμάντι");

                } else {
                    Odigies.setVisibility(View.VISIBLE);
                    Odigies.setText("Επιλέξτε κατηγορία");

                    TextView Cattxt1 = (TextView) findViewById(R.id.textCat1);
                    Cattxt1.setTypeface(font);
                    Cattxt1.setText(catToText(epiloges[0]));
                    Cattxt1.setVisibility(View.VISIBLE);

                    TextView Cattxt2 = (TextView) findViewById(R.id.textCat2);
                    Cattxt2.setTypeface(font);
                    Cattxt2.setText(catToText(epiloges[1]));
                    Cattxt2.setVisibility(View.VISIBLE);
                }

            }
        }, 1100);


    }

    public void choicebtn1(View view) {
        int categoryNum;
        Intent intent = new Intent(this, QuestionScreen.class);
        switch (epiloges[0]) {
            case "geo_b":
                categoryNum = 1;
                break;
            case "cim_b":
                categoryNum = 2;
                break;
            case "his_b":
                categoryNum = 3;
                break;
            case "art_b":
                categoryNum = 4;
                break;
            case "sci_b":
                categoryNum = 5;
                break;
            default:
                categoryNum = 6;
                break;
        }

        intent.putExtra("number_of_teams", number_of_teams);
        intent.putExtra("current_message", current_team);
        for (int i = 0; i < number_of_teams; i++) {
            intent.putExtra("team" + i, (android.os.Parcelable) teams[i]);
        }
        intent.putExtra("categoryNum", categoryNum);
        intent.putExtra("isDiamond", false);
        intent.putExtra("file_index",fileIndex);

        startActivity(intent);
        finish();
    }

    public void choicebtn2(View view) {
        int categoryNum;
        Intent intent = new Intent(this, QuestionScreen.class);
        switch (epiloges[1]) {
            case "geo_b":
                categoryNum = 1;
                break;
            case "cim_b":
                categoryNum = 2;
                break;
            case "his_b":
                categoryNum = 3;
                break;
            case "art_b":
                categoryNum = 4;
                break;
            case "sci_b":
                categoryNum = 5;
                break;
            default:
                categoryNum = 6;
                break;
        }

        intent.putExtra("number_of_teams", number_of_teams);
        intent.putExtra("current_message", current_team);
        for (int i = 0; i < number_of_teams; i++) {
            intent.putExtra("team" + i, (android.os.Parcelable) teams[i]);
        }
        intent.putExtra("categoryNum", categoryNum);
        intent.putExtra("isDiamond", false);
        intent.putExtra("file_index",fileIndex);

        startActivity(intent);
        finish();
    }

    public void diamondbtn(View view) {
        int categoryNum;

        Intent intent = new Intent(this, QuestionScreen.class);
        switch (epiloges[0]) {
            case "geo":
                categoryNum = 1;
                break;
            case "cim":
                categoryNum = 2;
                break;
            case "his":
                categoryNum = 3;
                break;
            case "art":
                categoryNum = 4;
                break;
            case "sci":
                categoryNum = 5;
                break;
            default:
                categoryNum = 6;
                break;
        }

        intent.putExtra("number_of_teams", number_of_teams);
        intent.putExtra("current_message", current_team);
        for (int i = 0; i < number_of_teams; i++) {
            intent.putExtra("team" + i, (android.os.Parcelable) teams[i]);
        }
        intent.putExtra("categoryNum", categoryNum);
        intent.putExtra("isDiamond", true);
        intent.putExtra("file_index",fileIndex);

        startActivity(intent);
        finish();
    }


    @Override
    public void onAnimationEnd(Animation animation) {
        // Take any action after completing the animation


    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onAnimationStart(Animation animation) {
        // TODO Auto-generated method stub

    }

    public String intColorToString(int col) {
        if (col == 1) {
            return "yellow";
        } else if (col == 2) {
            return "blue";
        } else if (col == 3) {
            return "green";
        } else if (col == 4) {
            return "pink";
        } else if (col == 5) {
            return "purple";
        } else {
            return "red";
        }
    }

    public String catToText(String cat) {

        switch (cat) {
            case "geo_b":
                return "Γεωγραφία";
            case "cim_b":
                return "Ψυχαγωγία";
            case "his_b":
                return "Ιστορία";
            case "art_b":
                return "Τέχνες";
            case "sci_b":
                return "Επιστήμη";
            default:
                return "Χόμπυ";
        }

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Σταμάτημα Παιχνιδιού")
                .setMessage("Είστε σίγουροι ότι θέλετε να επιστρέψετε στην αρχική οθόνη;")
                .setPositiveButton("Ναι", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        finish();
                    }

                })
                .setNegativeButton("Όχι", null)
                .show();
    }


    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
    public static void setMargins(View v, int l, int t, int r, int b) {

        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {

            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }




    public void refitText(TextView tv, float maxTextSize, int width) {
        tv.measure(0, 0);
        int textWidth = tv.getMeasuredWidth();
        int availableWidth = width;
        float trySize = maxTextSize;

        while (textWidth > availableWidth) {
            trySize -= 1;
            tv.setTextSize(trySize);
            tv.measure(0, 0);
            textWidth = tv.getMeasuredWidth();
        }

        tv.setTextSize(trySize);

    }

}

