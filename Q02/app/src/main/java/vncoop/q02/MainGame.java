package vncoop.q02;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

        //Τυχαία επιλογή κατηγοριών ή διαμαντιών\\
        randGen rg = new randGen();
        epiloges = rg.getRandomCategories(current_diamonds);
        //

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
        TextView OmadaTxt = (TextView) findViewById(R.id.textViewOmada);
        font = Typeface.createFromAsset(getAssets(), "VAG-HandWritten.otf");
        OmadaTxt.setTypeface(font);
        OmadaTxt.setText(teams[current_team].get_name());

        Odigies = (TextView) findViewById(R.id.instractions);
        Odigies.setTypeface(font);


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
                    int didd = getResources().getIdentifier(epiloges[0], "drawable", getPackageName());
                    diamondbtn.setScaleType(ImageButton.ScaleType.FIT_XY);
                    diamondbtn.setImageResource(didd);
                    diamondbtn.setVisibility(View.VISIBLE);
                    diamondbtn.setAnimation(ScaleFade);
                } else {


                    int did1 = getResources().getIdentifier(epiloges[0], "drawable", getPackageName());
                    firstbtn.setScaleType(ImageButton.ScaleType.FIT_XY);
                    firstbtn.setImageResource(did1);
                    firstbtn.setVisibility(View.VISIBLE);
                    firstbtn.setAnimation(MoveLeftFAdeIn);

                    int did2 = getResources().getIdentifier(epiloges[1], "drawable", getPackageName());
                    secondbtn.setScaleType(ImageButton.ScaleType.FIT_XY);
                    secondbtn.setImageResource(did2);
                    secondbtn.setVisibility(View.VISIBLE);
                    secondbtn.setAnimation(MoveRightFAdeIn);
                }
                spinbtn.setVisibility(View.GONE);

                //emfanizei ta text
                if (epiloges[1].equals("naS")) {

                    TextView Diatxt = (TextView) findViewById(R.id.textDiam);
                    Diatxt.setTypeface(font);
                    Diatxt.setText(catToText(epiloges[0]+"_b"));
                    Diatxt.setVisibility(View.VISIBLE);
                    Odigies.setVisibility(View.VISIBLE);
                    Odigies.setText("Απαντήστε σωστα για να κερδίσετε διαμάντι");

                }else {
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
            intent.putExtra("team" + i, teams[i]);
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
            intent.putExtra("team" + i, teams[i]);
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
            intent.putExtra("team" + i, teams[i]);
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
                .setPositiveButton("Ναι", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        finish();
                    }

                })
                .setNegativeButton("Όχι", null)
                .show();
    }

    @Override
    protected void onDestroy() throws IOException{

        super.onDestroy();
    }
}

