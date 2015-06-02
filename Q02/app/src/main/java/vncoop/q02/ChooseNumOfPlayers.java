package vncoop.q02;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;




public class ChooseNumOfPlayers extends Activity {
    private RadioGroup radioGroup;
    public int number_of_teams;
    ImageButton rb1;
    ImageButton rb2;
    ImageButton rb3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_num_of_players);


        rb1 = (ImageButton) findViewById(R.id.twoplayerid);
        rb2 = (ImageButton) findViewById(R.id.threeplayerid);
        rb3 = (ImageButton) findViewById(R.id.fourplayerid);
        number_of_teams = 2;
        double screenWidth, screenHeight, screenDensity, statusBarHeight, Left, Top, Right, Bottom;

        TextView NumberOfTeamsText = (TextView) findViewById(R.id.titleId);
        Typeface font = Typeface.createFromAsset(getAssets(), "VAG-HandWritten.otf");

        ImageButton homeButton = (ImageButton) findViewById(R.id.homeButtonId);
        ImageButton nextButton = (ImageButton) findViewById(R.id.nextButtonId);

        //Screen characteristics
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth = (double) dm.widthPixels;
        screenHeight = (double) dm.heightPixels;
        screenDensity = (double) dm.density;
        statusBarHeight = (double) getStatusBarHeight();
        screenHeight -= statusBarHeight;

        //Number of teams text Size set
        NumberOfTeamsText.setTypeface(font);
        NumberOfTeamsText.setTextSize((float) ((0.087 / screenDensity) * screenHeight));
        Top = (0.035* screenHeight);
        setMargins(NumberOfTeamsText,0,(int) Top,0,0);
        double buttonsRatio = 51.0 / 80.0;

        //Setting Two player Button
        Left = 0.33 * screenWidth;
        Top = 0.24 * screenHeight;
        Right = Left;
        Bottom = 0.616 * screenHeight;
        setMargins(rb1, (int) Left, (int) Top, (int) Right, (int) Bottom);

        //Setting Three player Button
        Top = 0.428 * screenHeight;
        Bottom = 0.428 * screenHeight;
        setMargins(rb2, (int) Left, (int) Top, (int) Right, (int) Bottom);

        //Setting Four player Button
        Top = 0.616 * screenHeight;
        Bottom = 0.24 * screenHeight;
        setMargins(rb3, (int) Left, (int) Top, (int) Right, (int) Bottom);

        //Setting home and Next buttons margins
        Left = 0.063668224 * screenWidth;
        Top = (0.80218068+0.015) * screenHeight;
        Right = 0.702102803738*screenWidth;
        Bottom = 0.04088785 * screenHeight;
        setMargins(homeButton, (int) Left, (int) Top, (int) Right, (int) Bottom);
        setMargins(nextButton, (int) Right, (int) Top, (int) Left, (int) Bottom);

    }


    ////ΠΙΣΩ ΚΟΥΜΠΙ
    public void back_click(View view) {
        finish();
    }

    ////ΕΠΟΜΕΝΟ ΚΟΥΜΠΙ ΣΤΕΙΛΕ ΣΤΟ INIT_TEAMS ΤΟ NUMBER OF PLAYERS
    public void next_click(View view) {
        Intent nextClick = new Intent(this, InitTeams.class);
        nextClick.putExtra("n_team_message", number_of_teams);
        startActivity(nextClick);
        finish();
    }



    public void onTwoClick(View view){
        int d1id = getResources().getIdentifier("twoplayerbutton_selec", "drawable", getPackageName());
        int d2id = getResources().getIdentifier("threeplayerbutton", "drawable", getPackageName());
        int d3id = getResources().getIdentifier("fourplayerbutton", "drawable", getPackageName());

        rb1.setImageResource(d1id);
        rb2.setImageResource(d2id);
        rb3.setImageResource(d3id);
        number_of_teams=2;

    }
    public void onThreeClick(View view){
        int d1id = getResources().getIdentifier("twoplayerbutton", "drawable", getPackageName());
        int d2id = getResources().getIdentifier("threeplayerbutton_selec", "drawable", getPackageName());
        int d3id = getResources().getIdentifier("fourplayerbutton", "drawable", getPackageName());

        rb1.setImageResource(d1id);
        rb2.setImageResource(d2id);
        rb3.setImageResource(d3id);
        number_of_teams=3;

    }
    public void onFourClick(View view){
        int d1id = getResources().getIdentifier("twoplayerbutton", "drawable", getPackageName());
        int d2id = getResources().getIdentifier("threeplayerbutton", "drawable", getPackageName());
        int d3id = getResources().getIdentifier("fourplayerbutton_selec", "drawable", getPackageName());

        rb1.setImageResource(d1id);
        rb2.setImageResource(d2id);
        rb3.setImageResource(d3id);
        number_of_teams=4;

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

}
