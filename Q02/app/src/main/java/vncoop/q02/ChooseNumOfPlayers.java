package vncoop.q02;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;




public class ChooseNumOfPlayers extends Activity {
    private RadioGroup radioGroup;
    public int number_of_teams;
    ImageButton[] rb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_num_of_players);

        rb = new ImageButton[3];
        rb[0] = (ImageButton) findViewById(R.id.twoplayerid);
        rb[1] = (ImageButton) findViewById(R.id.threeplayerid);
        rb[2] = (ImageButton) findViewById(R.id.fourplayerid);
        number_of_teams = 2;
        double screenWidth, screenHeight, screenDensity, statusBarHeight, Left, Top, Right, Bottom;

        TextView NumberOfTeamsText = (TextView) findViewById(R.id.titleId);
        Typeface font = Typeface.createFromAsset(getAssets(), "VAG-HandWritten.otf");

        ImageView separator = (ImageView) findViewById(R.id.seperator);
        ImageView separator2 = (ImageView) findViewById(R.id.seperator2);
        ImageButton homeButton = (ImageButton) findViewById(R.id.homeButtonId);
        ImageButton nextButton = (ImageButton) findViewById(R.id.nextButtonId);
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.rl);

        //Screen characteristics
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth = (double) dm.widthPixels;
        screenHeight = (double) dm.heightPixels;
        screenDensity = (double) dm.density;
        statusBarHeight = (double) getStatusBarHeight();
        screenHeight -= statusBarHeight;

        //Setting Number of teams text margins
        NumberOfTeamsText.setTypeface(font);
        Top = (0.035* screenHeight);
        Left = 0.05*screenWidth;
        Bottom = (1-0.135)*screenHeight;
        setMargins(NumberOfTeamsText,(int) Left,(int) Top,(int) Left,(int) Bottom);
        NumberOfTeamsText.setTextSize((float) ((0.075 / screenDensity) * screenHeight));

        //Separator Margins
        Top = (0.15)*screenHeight;
        Bottom = (0.83125)*screenHeight;
        setMargins(separator, 0, (int) Top, 0, (int) Bottom);

        //Separator2 Margins
        Top = (0.8*screenHeight);
        Bottom = 0.18125*screenHeight;
        setMargins(separator2,0,(int) Top,0,(int) Bottom);

        //Setting home and Next buttons margins
        Top = (0.83875) * screenHeight;
        Bottom = 0.04*screenHeight;
        Left = 0.05*screenWidth;
        Right = screenWidth-Left-(screenHeight-Top-Bottom);
        setMargins(homeButton, (int) Left, (int) Top, (int) Right, (int) Bottom);
        setMargins(nextButton, (int) Right, (int) Top, (int) Left, (int) Bottom);

        //Relative Layout Margins
        Top = (0.16875)* screenHeight;
        Bottom = (0.2*screenHeight);
        setMargins(rl,0,(int) Top,0,(int) Bottom);
        double rlHeight = screenHeight-Top-Bottom;
        double marginHeight = 0.0498125*screenHeight;
        double buttonHeight = 0.144*screenHeight;

        Log.d("rlTop", String.valueOf(Top));
        Log.d("rlBottom", String.valueOf(Bottom));
        double buttonsRatio = 51.0 / 80.0;
        Left = 0.33*screenWidth;
        for (int i=0;i<3;i++){
            Top = ((double) i*buttonHeight + (double) (i+1)*marginHeight);
            Bottom = ((double) (2-i)*buttonHeight+(double) (3-i)*marginHeight);
            setMargins(rb[i], (int) Left, (int) Top, (int) Left, (int) Bottom);
            Log.d("Top", String.valueOf(Top));
            Log.d("Bottom", String.valueOf(Bottom));
        }
        Top = 0.24 * screenHeight;
        Bottom = 0.616 * screenHeight;
        Log.d("b1Top", String.valueOf(Top));
        Log.d("b1Bottom", String.valueOf(Bottom));
        Top = 0.428 * screenHeight;
        Bottom = 0.428 * screenHeight;
        Log.d("b2Top", String.valueOf(Top));
        Log.d("b2Bottom", String.valueOf(Bottom));
        Top = 0.616 * screenHeight;
        Bottom = 0.24 * screenHeight;
        Log.d("b3Top", String.valueOf(Top));
        Log.d("b3Bottom", String.valueOf(Bottom));
 /*
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
*/

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

        rb[0].setImageResource(d1id);
        rb[1].setImageResource(d2id);
        rb[2].setImageResource(d3id);
        number_of_teams=2;

    }
    public void onThreeClick(View view){
        int d1id = getResources().getIdentifier("twoplayerbutton", "drawable", getPackageName());
        int d2id = getResources().getIdentifier("threeplayerbutton_selec", "drawable", getPackageName());
        int d3id = getResources().getIdentifier("fourplayerbutton", "drawable", getPackageName());

        rb[0].setImageResource(d1id);
        rb[1].setImageResource(d2id);
        rb[2].setImageResource(d3id);
        number_of_teams=3;

    }
    public void onFourClick(View view){
        int d1id = getResources().getIdentifier("twoplayerbutton", "drawable", getPackageName());
        int d2id = getResources().getIdentifier("threeplayerbutton", "drawable", getPackageName());
        int d3id = getResources().getIdentifier("fourplayerbutton_selec", "drawable", getPackageName());

        rb[0].setImageResource(d1id);
        rb[1].setImageResource(d2id);
        rb[2].setImageResource(d3id);
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
