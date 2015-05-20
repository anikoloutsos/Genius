package vncoop.q02;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Bundle;

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

    Animation MoveRightFAdeIn;
    Animation MoveLeftFAdeIn;
    Animation ScaleFade;

    Typeface font;
    TextView Odigies;

    public TextView OmadaTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);


        diamond_images = new ImageView[6];

        diamond_images[0] = (ImageView) findViewById(R.id.blueDiamId);
        diamond_images[1] = (ImageView) findViewById(R.id.pinkDiamId);
        diamond_images[2] = (ImageView) findViewById(R.id.redDiamId);
        diamond_images[3] = (ImageView) findViewById(R.id.purpleDiamId);
        diamond_images[4] = (ImageView) findViewById(R.id.greenDiamId);
        diamond_images[5] = (ImageView) findViewById(R.id.yellowDiamId);


        //INTENTS FROM INITIALIZE
        Intent intent = getIntent();
        current_team = intent.getIntExtra("current_message", 1);
        number_of_teams = intent.getIntExtra("number_of_teams", 1);
        teams = new parcTeams[number_of_teams];
        for (int i = 0; i < number_of_teams; i++) {
            teams[i] = intent.getParcelableExtra("team" + i);
        }
        current_diamonds = teams[current_team].get_diamonds();


        //SAVE STATE SE PERIPTWSI POU VGEI
        String PATH = "/data/data/vncoop.q02/databases/";
        String FILE = "poutsa1";
        File f = new File(PATH + FILE);
        f.delete();
        try {

            ObjectOutputStream oOS = new ObjectOutputStream(

                    new FileOutputStream(PATH + FILE));
            oOS.writeInt(number_of_teams);
            for (int i = 0; i < number_of_teams; i++) {
                oOS.writeObject(teams[i]);
            }

            oOS.writeInt(current_team);
            oOS.flush();
            oOS.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //WS EDW TO SAVE STATE


        //set layout margin of button categories

        int width=getDisplaywidth();
        Log.d("____________",""+width);
        ImageButton b1 = (ImageButton)findViewById(R.id.btnCat1Id);
        ImageButton b2 = (ImageButton)findViewById(R.id.btnCat2Id);

        double margind = width*0.1;
        int margin = (int) margind;
        Log.d("MARGIn" + margin, "Width" + width);
        setMargins(b1,margin,0,0, 0);
        setMargins(b2,0,0,margin,0);
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

        //Εμφάνιση ονόματος ομάδας\\
        OmadaTxt = (TextView) findViewById(R.id.textViewOmada);
        font = Typeface.createFromAsset(getAssets(), "VAG-HandWritten.otf");
        OmadaTxt.setTypeface(font);
        OmadaTxt.setText(teams[current_team].get_name());


        OmadaTxt.measure(0, 0);       //must call measure!
        int textwidth=OmadaTxt.getMeasuredWidth(); //get width

        Log.d("Text size " +OmadaTxt.getTextSize(), "text width " + textwidth);
        refitText(OmadaTxt,width, textwidth, 45);
        Log.d("Text Height "+width, "text width " + textwidth);
        Odigies = (TextView) findViewById(R.id.instractions);
        Odigies.setTypeface(font);
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

        //animation
        spinbtn.setImageResource(R.drawable.spin_animation);
        AnimationDrawable frameAnimation = (AnimationDrawable) spinbtn.getDrawable();
        frameAnimation.start();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 1000ms

                if (epiloges[1].equals("naS")) {
                    int didd = getResources().getIdentifier(epiloges[0] + "_selector", "drawable", getPackageName());
                    diamondbtn.setScaleType(ImageButton.ScaleType.FIT_XY);
                    diamondbtn.setImageResource(didd);
                    diamondbtn.setVisibility(View.VISIBLE);
                    diamondbtn.setAnimation(ScaleFade);
                } else {


                    int did1 = getResources().getIdentifier(epiloges[0] + "_selector", "drawable", getPackageName());
                    firstbtn.setScaleType(ImageButton.ScaleType.FIT_XY);
                    firstbtn.setImageResource(did1);
                    firstbtn.setVisibility(View.VISIBLE);
                    firstbtn.setAnimation(MoveLeftFAdeIn);

                    int did2 = getResources().getIdentifier(epiloges[1] + "_selector", "drawable", getPackageName());
                    secondbtn.setScaleType(ImageButton.ScaleType.FIT_XY);
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
        }, 1050);


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




    public static void setMargins(View v, int l, int t, int r, int b) {

        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {

            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

   /* public static void setWidth(View v, int w) {

        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {

            ViewGroup.LayoutParams p = (ViewGroup.LayoutParams) v.getLayoutParams();
            p.width=w;
            v.requestLayout();
        }
    }*/

    public int getDisplaywidth(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        if(height<=width){width=height;}
        return width;
    }


    public void refitText(TextView tv,int width, int textWidth, float maxTextSize) {

            int availableWidth = width;
            float trySize = maxTextSize;

            while (textWidth > availableWidth) {
                trySize -= 1;
                tv.setTextSize(trySize);
                tv.measure(0, 0);
                textWidth =tv.getMeasuredWidth();
                Log.d("textwidth " +textWidth,"textsize " +trySize);
                //tv.requestLayout();
                }

        tv.setTextSize(trySize);

    }

}

