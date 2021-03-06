package vncoop.genius;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
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


public class Continue extends Activity implements Animation.AnimationListener {

    int current_team;
    int number_of_teams;
    int prev_category;
    boolean from_saved_game;
    Teams[] teams;
    boolean[] current_diamonds;
    boolean sameTeam;
    private int fileIndex;
    Animation fadein;
    String Question = "";
    int animatedDiamond;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continue);

        //INTENTS FROM question
        Intent intent = getIntent();
        current_team = intent.getIntExtra("current_message", 1);
        number_of_teams = intent.getIntExtra("number_of_teams", 1);
        sameTeam = intent.getBooleanExtra("same_team", true);
        prev_category=intent.getIntExtra("previus_category",0);
        from_saved_game=intent.getBooleanExtra("from_saved_game",false);

        teams = new Teams[number_of_teams];
        for (int i = 0; i < number_of_teams; i++) {
            teams[i] = intent.getParcelableExtra("team" + i);
        }
        current_diamonds = teams[current_team].get_diamonds();
        fileIndex = intent.getIntExtra("file_index", 0);

        animatedDiamond =intent.getIntExtra("Catdiamond",0);


        Question = intent.getStringExtra("Question");

        ///////////
        double[] screen = BasicMethods.getScreenChar(this);
        double Left,Top,Right,Bottom;
        double basicRelativeLayoutHeight;
        double basicRelativeLayoutWidth;
        double rlMarginHeight;
        double double_number_of_teams;
        double_number_of_teams = (double) number_of_teams;
        double rlHeight;

        if(!sameTeam){
            current_team++;
            current_team = current_team%number_of_teams;
            //change stats
        }

        TextView teamText =(TextView)findViewById(R.id.teamId);
        TextView replayText =(TextView)findViewById(R.id.rulesid);
        ImageView separator = (ImageView) findViewById(R.id.separator);
        Typeface font = Typeface.createFromAsset(getAssets(), "VAG-HandWritten.otf");
        RelativeLayout basicRelativeL = (RelativeLayout) findViewById(R.id.basicRelativeLayout);
        Button continueButton = (Button)findViewById(R.id.continueButtonId);
        Button reportButton = (Button)findViewById(R.id.reportButtonId);
        ImageView separator2 = (ImageView) findViewById(R.id.separator2);
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

        //Setting team Text and replay text size (no need for margins it's below)
        teamText.setTypeface(font);
        teamText.setTextSize((float) ((0.07 / screen[2]) * screen[1]));
        replayText.setTypeface(font);
        replayText.setTextSize((float) ((0.07 / screen[2]) * screen[1]));
        Left = 0.05*screen[0];
        Top = ((0.035-0.015)* screen[1]);
        Bottom = 0.8975*screen[1];
        BasicMethods.setMargins(teamText, (int) Left, (int) Top, (int) Left, (int) Bottom);
        Top = 0.1025*screen[1];
        Bottom = (0.83-0.015)*screen[1];
        BasicMethods.setMargins(replayText, (int) Left, (int) Top, (int) Left, (int) Bottom);

        if (sameTeam) {
            teamText.setText(""+teams[current_team].get_name());
            BasicMethods.refitText(teamText, (float) ((0.07 / screen[2]) * screen[1]), (int) (0.9 * screen[0]));
            replayText.setTextSize((float) ((0.06 / screen[2]) * screen[1]));
            replayText.setText("Ξαναπαίζεις");

        }
        else{
            replayText.setText(""+teams[current_team].get_name());
            BasicMethods.refitText(replayText, (float) ((0.07 / screen[2]) * screen[1]), (int) (0.9 * screen[0]));
            teamText.setTextSize((float) ((0.06 / screen[2]) * screen[1]));
            teamText.setText("Επόμενη Ομάδα:");
        }

        //Separator Margins
        Top = 0.2*screen[1];
        Bottom = 0.78125*screen[1];
        BasicMethods.setMargins(separator, 0, (int) Top, 0, (int) Bottom);


        //Report Button
        reportButton.setTypeface(font);
        Top = 0.85*screen[1];
        Bottom = 0.05*screen[1];
        Left = 0.05*screen[0];
        Right = 0.6*screen[0];
        reportButton.setTextSize((float) ((0.045 / screen[2]) * screen[1]));
        BasicMethods.setMargins(reportButton, (int) Left, (int) Top, (int) Right, (int) Bottom);

        //Continue Button
        continueButton.setTypeface(font);
        continueButton.setTextSize((float) ((0.045 / screen[2]) * screen[1]));
        BasicMethods.setMargins(continueButton, (int) Right, (int) Top, (int) Left, (int) Bottom);

        //Separator2 Margins
        Bottom = 0.165*screen[1];
        Top = 0.81625*screen[1];
        BasicMethods.setMargins(separator2, 0, (int) Top, 0, (int) Bottom);

        //BasicRelativeLayout Margins
        Top = 0.21875*screen[1];
        Bottom = 0.18375*screen[1];
        Left = 0.05*screen[0];
        Right = Left;
        BasicMethods.setMargins(basicRelativeL, (int) Left, (int) Top, (int) Right, (int) Bottom);

        basicRelativeLayoutHeight = screen[1]-Top-Bottom;
        basicRelativeLayoutWidth = screen[0]-Left-Right;
        
        float letterHeight;
        if (number_of_teams==2){
            rlMarginHeight=0.08*basicRelativeLayoutHeight;
            letterHeight =(float) ((0.07 / screen[2])* screen[1]);
        }
        else if (number_of_teams==3){
            rlMarginHeight=0.05*basicRelativeLayoutHeight;

            letterHeight =(float) ((0.06 / screen[2])* screen[1]);
        }
        else{
            rlMarginHeight=0.02*basicRelativeLayoutHeight;
            letterHeight =(float) ((0.05 / screen[2])* screen[1]);
        }

        rlHeight = ((1.0/ double_number_of_teams)*basicRelativeLayoutHeight-2.0*rlMarginHeight);

        Left = 0.05*basicRelativeLayoutWidth;
        Right = Left;
        for (int i=0;i<number_of_teams;i++){
            Top = (double) (i)*rlHeight+(2.0*(double)(i)+1.0)*rlMarginHeight;
            Bottom = (double_number_of_teams-1.0-(double)i)*rlHeight+(2*(double_number_of_teams-1.0-(double)i)+1.0)*rlMarginHeight;
            BasicMethods.setMargins(rl[i], 0, (int) Top, 0, (int) Bottom);
            BasicMethods.setMargins(allTeamsTxt[i], (int) Left, 0, (int) Right, (int) (rlHeight / 2.0));
            BasicMethods.setMargins(allTeamsDiamondTables[i], (int) Left, (int) (rlHeight / 2.0), (int) Right, 0);
            allTeamsDiamondTables[i].setVisibility(View.VISIBLE);
            int backId = getResources().getIdentifier(intColorToString(teams[i].get_color()) + "_color", "drawable", getPackageName());
            rl[i].setBackgroundResource(backId);
            for (int j =0;j<6;j++) {
                if (teams[i].get_diamonds()[j]) {
                    diamond_images[j+6* i].setVisibility(View.VISIBLE);
                    BasicMethods.setMargins(diamond_images[j + 6 * i], (int) Left, (int) (rlHeight / 2.0), (int) Right, 0);
                }
            }
            allTeamsTxt[i].setTypeface(font);
            allTeamsTxt[i].setText(teams[i].get_name());
            allTeamsTxt[i].setTextSize(letterHeight);
            BasicMethods.refitText(allTeamsTxt[i], letterHeight, (int) (0.9 * basicRelativeLayoutWidth));
            allTeamsTxt[i].setVisibility(View.VISIBLE);
        }



        if(animatedDiamond !=0) {
            //fortwse animation
            fadein = AnimationUtils.loadAnimation(getApplicationContext(),
                    R.anim.fadein);
            fadein.setAnimationListener(this);
            //
            int diamId = getResources().getIdentifier("team" + (current_team + 1) + BasicMethods.intCatToColorString(animatedDiamond) + "DiamId", "id", getPackageName());

            ImageView animatedImage = (ImageView) findViewById(diamId);
            animatedImage.setVisibility(View.VISIBLE);
            animatedImage.setAnimation(fadein);
        }



        //SAVE STATE SE PERIPTWSI POU VGEI
        String[] FILE = new String[3];
        //String directory = "/data/vncoop.genius/databases/";

        FILE[0] = BasicMethods.save1;
        FILE[1] = BasicMethods.save2;
        FILE[2] = BasicMethods.save3;

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
        for (int i = 0;i<number_of_teams;i++) {
            intent.putExtra("team"+i, (android.os.Parcelable) teams[i]);
        }
        intent.putExtra("file_index", fileIndex);
        intent.putExtra("current_message", current_team);

        //change stats
        if(!from_saved_game) {
            if (sameTeam) {
                teams[current_team].set_stats_category_correct(prev_category - 1);
            } else {
                teams[current_team].set_stats_category_wrong(prev_category - 1);
            }

            BasicMethods.closeQuestion(this);
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

    public void reportBtn(View view){
        Intent goReport = new Intent(this, Report.class);
        goReport.putExtra("Question",Question);
        startActivity(goReport);
    }
    @Override
    public void onBackPressed() {
        if(animatedDiamond!=0) {
            current_diamonds[animatedDiamond - 1] = false;
        }
        if(!sameTeam){
            current_team--;
            current_team = current_team%number_of_teams;
            //change stats
        }
        finish();
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




}
