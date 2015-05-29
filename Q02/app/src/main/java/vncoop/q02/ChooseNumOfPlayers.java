package vncoop.q02;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;




public class ChooseNumOfPlayers extends Activity {
    private RadioGroup radioGroup;
    public int number_of_teams;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_num_of_players);


        double screenWidth,screenHeight,screenDensity,statusBarHeight,Left,Top,Right,Bottom;

        TextView NumberOfTeamsText = (TextView) findViewById(R.id.numberOfTeamsId);
        Typeface font = Typeface.createFromAsset(getAssets(), "VAG-HandWritten.otf");
        //refitText(RithmiseisTxt,50);
        ImageView separator = (ImageView) findViewById(R.id.separatorId);
        RadioButton radioButton2Teams = (RadioButton) findViewById(R.id.button2);
        RadioButton radioButton3Teams = (RadioButton) findViewById(R.id.button2);
        RadioButton radioButton4Teams = (RadioButton) findViewById(R.id.button2);
        radioGroup = (RadioGroup) findViewById(R.id.RadioGroup_id);

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

        double buttonsRatio = 51.0/80.0;

        Left = 0.2*screenWidth;
        Top = 0.2*screenHeight;
        setMargins(radioGroup,(int) Left,(int) Top
                ,(int) Left, (int) 0);
        //Two players selection margins
        //Left = 0.3*screenWidth;
        Left=0;
        Top = 0.35*screenHeight;
        Right = Left;
        Bottom = (screenHeight - Top - buttonsRatio*(screenWidth-2*Left)) ;
        setMargins(radioButton2Teams,(int) Left,(int) Top,(int) Right, (int) Bottom);
        Top = 0.05*screenHeight;
        setMargins(radioButton3Teams,(int) Left,(int) Top,(int) Right, (int) Bottom);
        setMargins(radioButton4Teams,(int) Left,(int) Top,(int) Right, (int) Bottom);


         /* Initialize Radio Group and attach click handler */

        //radioGroup.clearCheck();
    }
//////////////////////////


    ////ΠΙΣΩ ΚΟΥΜΠΙ
    public void back_click(View view) {
        finish();
    }

    ////ΕΠΟΜΕΝΟ ΚΟΥΜΠΙ ΣΤΕΙΛΕ ΣΤΟ INIT_TEAMS ΤΟ NUMBER OF PLAYERS
    public void next_click(View view) {
        Intent nextClick = new Intent(this, InitTeams.class);
        RadioButton rb = (RadioButton) radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
        number_of_teams = Integer.parseInt(rb.getTag().toString());
        nextClick.putExtra("n_team_message", number_of_teams);
        startActivity(nextClick);
        finish();
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
