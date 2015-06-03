package vncoop.q02;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
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
    String Question = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continue_screen);


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

        Log.d("FileIndex", String.valueOf(fileIndex));
        animatedDiamond=0;
        animatedDiamond=intent.getIntExtra("Catdiamond",0);


        Question = intent.getStringExtra("Question");

        ///////////
        double screenWidth,screenHeight,statusBarHeight,Left,Top,Right,Bottom,screenDensity;


        //Screen characteristics
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth = (double) dm.widthPixels;
        screenHeight = (double) dm.heightPixels;
        screenDensity = (double) dm.density;

        statusBarHeight = (double) getStatusBarHeight();
        screenHeight -= statusBarHeight;
        TextView teamText =(TextView)findViewById(R.id.teamId);
        TextView replayText =(TextView)findViewById(R.id.rulesid);
        ImageView separator = (ImageView) findViewById(R.id.seperator);
        Typeface font = Typeface.createFromAsset(getAssets(), "VAG-HandWritten.otf");
        RelativeLayout basicRelativeL = (RelativeLayout) findViewById(R.id.basicRelativeLayout);
        Button continueButton = (Button)findViewById(R.id.continueButtonId);
        Button reportButton = (Button)findViewById(R.id.reportButtonId);
        ImageView separator2 = (ImageView) findViewById(R.id.seperator2);
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
        teamText.setTextSize((float) ((0.07 / screenDensity) * screenHeight));
        replayText.setTypeface(font);
        replayText.setTextSize((float) ((0.07 / screenDensity) * screenHeight));
        Left = 0.05*screenWidth;
        Top = ((0.035-0.015)* screenHeight);
        Bottom = 0.8975*screenHeight;
        setMargins(teamText,(int) Left,(int) Top,(int) Left,(int) Bottom);
        Top = 0.1025*screenHeight;
        Bottom = (0.83-0.015)*screenHeight;
        setMargins(replayText,(int) Left,(int) Top,(int) Left,(int) Bottom);

        if (sameTeam) {
            teamText.setText(""+teams[current_team].get_name());
            refitText(teamText,(float) ((0.07 / screenDensity) * screenHeight),(int)(0.9*screenWidth));
            replayText.setTextSize((float) ((0.06 / screenDensity) * screenHeight));
            replayText.setText("Ξαναπαίζεις");

        }
        else{
            replayText.setText(""+teams[current_team].get_name());
            refitText(replayText, (float) ((0.07 / screenDensity) * screenHeight), (int) (0.9 * screenWidth));
            teamText.setTextSize((float) ((0.06 / screenDensity) * screenHeight));
            teamText.setText("Επόμενη Ομάδα:");
        }

        //Separator Margins
        Top = 0.2*screenHeight;
        Bottom = 0.78125*screenHeight;
        setMargins(separator,0,(int) Top,0,(int) Bottom);


        //Report Button
        reportButton.setTypeface(font);
        Top = 0.85*screenHeight;
        Bottom = 0.05*screenHeight;
        Left = 0.05*screenWidth;
        Right = 0.6*screenWidth;
        reportButton.setTextSize((float) ((0.045 / screenDensity) * screenHeight));
        setMargins(reportButton,(int) Left,(int) Top,(int) Right,(int) Bottom);

        //Continue Button
        continueButton.setTypeface(font);
        continueButton.setTextSize((float) ((0.045 / screenDensity) * screenHeight));
        setMargins(continueButton,(int) Right,(int) Top,(int) Left,(int) Bottom);

        //Seperator2 Margins
        Bottom = 0.165*screenHeight;
        Top = 0.81625*screenHeight;
        setMargins(separator2,0,(int) Top,0,(int) Bottom);

        //BasicRelativeLayout Margins
        Top = 0.21875*screenHeight;
        Bottom = 0.18375*screenHeight;
        Left = 0.05*screenWidth;
        Right = Left;
        setMargins(basicRelativeL,(int) Left,(int) Top,(int) Right,(int) Bottom);

        double basicRelativeLayoutHeight = screenHeight-Top-Bottom;
        double basicRelativeLayoutWidth = screenWidth-Left-Right;
        double rlMarginHeight=0.0;
        float letterHeight;
        if (number_of_teams==2){
            rlMarginHeight=0.08*basicRelativeLayoutHeight;
            letterHeight =(float) ((0.07 / screenDensity)* screenHeight);
        }
        else if (number_of_teams==3){
            rlMarginHeight=0.05*basicRelativeLayoutHeight;

            letterHeight =(float) ((0.06 / screenDensity)* screenHeight);
        }
        else{
            rlMarginHeight=0.02*basicRelativeLayoutHeight;
            letterHeight =(float) ((0.05 / screenDensity)* screenHeight);
        }
        double num_of_teams;
        num_of_teams = (double) number_of_teams;
        double rlHeight = ((1.0/ num_of_teams)*basicRelativeLayoutHeight-2.0*rlMarginHeight);

        Left = 0.05*basicRelativeLayoutWidth;
        Right = Left;
        for (int i=0;i<number_of_teams;i++){
            Top = (double) (i)*rlHeight+(2.0*(double)(i)+1.0)*rlMarginHeight;
            Bottom = (num_of_teams-1.0-(double)i)*rlHeight+(2*(num_of_teams-1.0-(double)i)+1.0)*rlMarginHeight;
            setMargins(rl[i],0,(int) Top,0,(int) Bottom);
            setMargins(allTeamsTxt[i],(int) Left,0,(int)Right,(int)(rlHeight/2.0));
            setMargins(allTeamsDiamondTables[i],(int) Left,(int)(rlHeight/2.0),(int)Right,0);
            allTeamsDiamondTables[i].setVisibility(View.VISIBLE);
            int backId = getResources().getIdentifier(intColorToString(teams[i].get_color()) + "_color", "drawable", getPackageName());
            rl[i].setBackgroundResource(backId);
            for (int j =0;j<6;j++) {
                if (teams[i].get_diamonds()[j]) {
                    diamond_images[j+6* i].setVisibility(View.VISIBLE);
                    setMargins(diamond_images[j+6*i],(int) Left,(int)(rlHeight/2.0),(int)Right,0);
                }
            }
            allTeamsTxt[i].setTypeface(font);
            allTeamsTxt[i].setText(teams[i].get_name());
            allTeamsTxt[i].setTextSize(letterHeight);
            refitText(allTeamsTxt[i], letterHeight,(int)(0.9*basicRelativeLayoutWidth));
            allTeamsTxt[i].setVisibility(View.VISIBLE);
        }



        if(animatedDiamond!=0) {
            //fortwse animation
            fadein = AnimationUtils.loadAnimation(getApplicationContext(),
                    R.anim.fadein);
            fadein.setAnimationListener(this);
            //
            int diamId = getResources().getIdentifier("team" + (current_team + 1) + intCatToColorString(animatedDiamond) + "DiamId", "id", getPackageName());

            ImageView animatedImage = (ImageView) findViewById(diamId);
            animatedImage.setVisibility(View.VISIBLE);
            animatedImage.setAnimation(fadein);
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
        intent.putExtra("file_index",fileIndex);
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
        Intent goReport = new Intent(this, report.class);
        goReport.putExtra("Question",Question);
        startActivity(goReport);
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
