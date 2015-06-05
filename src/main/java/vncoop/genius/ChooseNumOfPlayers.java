package vncoop.genius;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;




public class ChooseNumOfPlayers extends Activity {

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
        double[] screen;
        screen = BasicMethods.getScreenChar(this);
        double  Left, Top, Right, Bottom, marginHeight, buttonHeight;

        TextView NumberOfTeamsText = (TextView) findViewById(R.id.titleId);
        ImageView separator = (ImageView) findViewById(R.id.separator);
        ImageView separator2 = (ImageView) findViewById(R.id.separator2);
        ImageButton homeButton = (ImageButton) findViewById(R.id.homeButtonId);
        ImageButton nextButton = (ImageButton) findViewById(R.id.nextButtonId);
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.rl);
        Typeface font = Typeface.createFromAsset(getAssets(), "VAG-HandWritten.otf");

        //Setting Number of teams text margins
        NumberOfTeamsText.setTypeface(font);
        Top = (0.035* screen[1]);
        Left = 0.05*screen[0];
        Bottom = (1-0.135)*screen[1];
        BasicMethods.setMargins(NumberOfTeamsText,(int) Left,(int) Top,(int) Left,(int) Bottom);
        NumberOfTeamsText.setTextSize((float) ((0.075 / screen[2]) * screen[1]));

        //Separator Margins
        Top = (0.15)*screen[1];
        Bottom = (0.83125)*screen[1];
        BasicMethods.setMargins(separator, 0, (int) Top, 0, (int) Bottom);

        //Separator2 Margins
        Top = (0.8*screen[1]);
        Bottom = 0.18125*screen[1];
        BasicMethods.setMargins(separator2,0,(int) Top,0,(int) Bottom);

        //Setting home and Next buttons margins
        Top = (0.83875) * screen[1];
        Bottom = 0.04*screen[1];
        Left = 0.05*screen[0];
        Right = screen[0]-Left-(screen[1]-Top-Bottom);
        BasicMethods.setMargins(homeButton, (int) Left, (int) Top, (int) Right, (int) Bottom);
        BasicMethods.setMargins(nextButton, (int) Right, (int) Top, (int) Left, (int) Bottom);

        //Relative Layout Margins
        Top = (0.16875)* screen[1];
        Bottom = (0.2*screen[1]);
        BasicMethods.setMargins(rl,0,(int) Top,0,(int) Bottom);
        marginHeight = 0.0498125*screen[1];
        buttonHeight = 0.144*screen[1];

        Left = 0.33*screen[0];
        for (int i=0;i<3;i++){
            Top = ((double) i*buttonHeight + (double) (i+1)*marginHeight);
            Bottom = ((double) (2-i)*buttonHeight+(double) (3-i)*marginHeight);
            BasicMethods.setMargins(rb[i], (int) Left, (int) Top, (int) Left, (int) Bottom);
        }

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



    public void onTeamClick(View view){

        int d1id = getResources().getIdentifier("twoplayerbutton", "drawable", getPackageName());
        int d2id = getResources().getIdentifier("threeplayerbutton", "drawable", getPackageName());
        int d3id = getResources().getIdentifier("fourplayerbutton", "drawable", getPackageName());

        int id = view.getId();
        int  P2 = R.id.twoplayerid;
        int  P3 = R.id.threeplayerid;
        if (id == P2){
            d1id = getResources().getIdentifier("twoplayerbutton_selec", "drawable", getPackageName());
            number_of_teams=2;
        }else if(id == P3){
            d2id = getResources().getIdentifier("threeplayerbutton_selec", "drawable", getPackageName());
            number_of_teams=3;
        }else{
            d3id = getResources().getIdentifier("fourplayerbutton_selec", "drawable", getPackageName());
            number_of_teams=4;
        }

        rb[0].setImageResource(d1id);
        rb[1].setImageResource(d2id);
        rb[2].setImageResource(d3id);

    }

}
