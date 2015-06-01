package vncoop.q02;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class ContinueScreen extends Activity implements Animation.AnimationListener {

    int current_team;
    int number_of_teams;
    parcTeams[] teams;
    boolean[] current_diamonds;
    boolean sameTeam;
    private int fileIndex;
    Animation fadein;
    private int animatedDiamond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continue_screen);

        //get metrics
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        double screenWidth = (double) dm.widthPixels;
        //

        //INTENTS FROM question
        Intent intent = getIntent();
        current_team = intent.getIntExtra("current_message", 1);
        number_of_teams = intent.getIntExtra("number_of_teams", 1);
        sameTeam = intent.getBooleanExtra("same_team", true);

        teams = new parcTeams[number_of_teams];
        for (int i = 0; i < number_of_teams; i++) {
            teams[i] = intent.getParcelableExtra("team" + i);
        }
        current_diamonds = teams[current_team].get_diamonds();
        fileIndex = intent.getIntExtra("file_index", 0);
        animatedDiamond=0;
        animatedDiamond=intent.getIntExtra("Catdiamond",0);
        Log.d("afsfafafasfasfasf"," " +animatedDiamond);




    ///set text
        TextView tv1 =(TextView)findViewById(R.id.textView1);
        TextView tv2 =(TextView)findViewById(R.id.textView2);
        TextView tv3 =(TextView)findViewById(R.id.textView3);
        Button btn1 = (Button)findViewById(R.id.button);

        Typeface font = Typeface.createFromAsset(getAssets(), "VAG-HandWritten.otf");
        tv1.setTypeface(font);
        tv2.setTypeface(font);
        tv3.setTypeface(font);
        btn1.setTypeface(font);

        //Εμφάνιση Διαμαντιων ομάδας\\
        ImageView[] diamond_images;
        diamond_images = new ImageView[24];

        diamond_images[0] = (ImageView) findViewById(R.id.team1blueDiamId);
        diamond_images[1] = (ImageView) findViewById(R.id.team1pinkDiamId);
        diamond_images[2] = (ImageView) findViewById(R.id.team1redDiamId);
        diamond_images[3] = (ImageView) findViewById(R.id.team1purpleDiamId);
        diamond_images[4] = (ImageView) findViewById(R.id.team1greenDiamId);
        diamond_images[5] = (ImageView) findViewById(R.id.team1yellowDiamId);
        diamond_images[6] = (ImageView) findViewById(R.id.team2blueDiamId);
        diamond_images[7] = (ImageView) findViewById(R.id.team2pinkDiamId);
        diamond_images[8] = (ImageView) findViewById(R.id.team2redDiamId);
        diamond_images[9] = (ImageView) findViewById(R.id.team2purpleDiamId);
        diamond_images[10] = (ImageView) findViewById(R.id.team2greenDiamId);
        diamond_images[11] = (ImageView) findViewById(R.id.team2yellowDiamId);
        diamond_images[12] = (ImageView) findViewById(R.id.team3blueDiamId);
        diamond_images[13] = (ImageView) findViewById(R.id.team3pinkDiamId);
        diamond_images[14] = (ImageView) findViewById(R.id.team3redDiamId);
        diamond_images[15] = (ImageView) findViewById(R.id.team3purpleDiamId);
        diamond_images[16] = (ImageView) findViewById(R.id.team3greenDiamId);
        diamond_images[17] = (ImageView) findViewById(R.id.team3yellowDiamId);
        diamond_images[18] = (ImageView) findViewById(R.id.team4blueDiamId);
        diamond_images[19] = (ImageView) findViewById(R.id.team4pinkDiamId);
        diamond_images[20] = (ImageView) findViewById(R.id.team4redDiamId);
        diamond_images[21] = (ImageView) findViewById(R.id.team4purpleDiamId);
        diamond_images[22] = (ImageView) findViewById(R.id.team4greenDiamId);
        diamond_images[23] = (ImageView) findViewById(R.id.team4yellowDiamId);





        ImageView[] allTeamsDiamondTables = new ImageView[4];
        allTeamsDiamondTables[0] = (ImageView) findViewById(R.id.team1DiamId);
        allTeamsDiamondTables[1] = (ImageView) findViewById(R.id.team2DiamId);
        allTeamsDiamondTables[2] = (ImageView) findViewById(R.id.team3DiamId);
        allTeamsDiamondTables[3] = (ImageView) findViewById(R.id.team4DiamId);

        RelativeLayout[] rl = new RelativeLayout[4];
        rl[0]= (RelativeLayout) findViewById(R.id.rl1);
        rl[1]= (RelativeLayout) findViewById(R.id.rl2);
        rl[2]= (RelativeLayout) findViewById(R.id.rl3);
        rl[3]= (RelativeLayout) findViewById(R.id.rl4);

        TextView[] allTeamsTxt = new TextView[4];
        allTeamsTxt[0] = (TextView) findViewById(R.id.team1NameId);
        allTeamsTxt[1] = (TextView) findViewById(R.id.team2NameId);
        allTeamsTxt[2] = (TextView) findViewById(R.id.team3NameId);
        allTeamsTxt[3] = (TextView) findViewById(R.id.team4NameId);


        for (int j =0; j<number_of_teams; j++) {
            allTeamsDiamondTables[j].setVisibility(View.VISIBLE);
            int backId = getResources().getIdentifier(intColorToString(teams[j].get_color()) + "_color", "drawable", getPackageName());
            rl[j].setBackgroundResource(backId);

            allTeamsTxt[j].setTypeface(font);
            allTeamsTxt[j].setText(teams[j].get_name());

            refitText(allTeamsTxt[j],35,(int)screenWidth/2);
            allTeamsTxt[j].setVisibility(View.VISIBLE);

            for (int i = 0; i < 6; i++) {
                if (teams[j].get_diamonds()[i]) {
                    diamond_images[i+6*j].setVisibility(View.VISIBLE);
                }
            }
        }
        if(animatedDiamond!=0) {
            //fortwse animation
            fadein = AnimationUtils.loadAnimation(getApplicationContext(),
                    R.anim.fadein);
            fadein.setAnimationListener(this);
            //
            int diamId = getResources().getIdentifier("team" + (current_team + 1) + intCatToColorString(animatedDiamond) + "DiamId", "id", getPackageName());
            Log.d("sssssssssss", "" + "team" + (current_team + 1) + intCatToColorString(animatedDiamond) + "DiamId");
            ImageView animatedImage = (ImageView) findViewById(diamId);
            animatedImage.setVisibility(View.VISIBLE);
            animatedImage.setAnimation(fadein);
        }





        if (sameTeam) {

        tv1.setText("Συγχαρητήρια!");
        tv2.setText(""+teams[current_team].get_name());
            refitText(tv2,45,(int)screenWidth);
        tv3.setText("Ξαναπαίζεις");
        }else{
            tv1.setText("Απαντήσατε Λάθος!");
            tv3.setText(""+teams[current_team].get_name());
            refitText(tv3,45,(int)screenWidth);
            tv2.setText("Επόμενη Ομάδα:");
        }


        //SAVE STATE SE PERIPTWSI POU VGEI
        String[] FILE = new String[3];
        FILE[0] = "/data/data/vncoop.q02/databases/savegame1";
        FILE[1] = "/data/data/vncoop.q02/databases/savegame2";
        FILE[2]= "/data/data/vncoop.q02/databases/savegame3";



        File f = new File(FILE[fileIndex]);
        f.delete();
        try {

            ObjectOutputStream oOS = new ObjectOutputStream(

                    new FileOutputStream(FILE[fileIndex]));
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


    }

    public void onContinue(View view){
        Intent intent = new Intent(this, MainGame.class);

        intent.putExtra("number_of_teams",number_of_teams);
        intent.putExtra("current_message", current_team);
        for (int i = 0;i<number_of_teams;i++) {
            intent.putExtra("team"+i, (android.os.Parcelable) teams[i]);
        }

        startActivity(intent);
        finish();
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
            Log.d("textwidth " + textWidth, "textsize " + trySize);
            //tv.requestLayout();
        }

        tv.setTextSize(trySize);

    }


    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    public String intCatToColorString(int col){
        if(col==1){
            return "blue";
        }else if(col==2){
            return "pink";
        }else if(col==3){
            return "red";
        }else if(col==4){
            return "purple";
        }else if(col==5){
            return "green";
        }else{
            return "yellow";
        }
    }
}
