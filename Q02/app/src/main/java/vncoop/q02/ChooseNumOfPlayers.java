package vncoop.q02;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
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


        TextView RithmiseisTxt = (TextView) findViewById(R.id.textView);
        refitText(RithmiseisTxt,50);
        Typeface font = Typeface.createFromAsset(getAssets(), "VAG-HandWritten.otf");
        RithmiseisTxt.setTypeface(font);



         /* Initialize Radio Group and attach click handler */
        radioGroup = (RadioGroup) findViewById(R.id.RadioGroup_id);
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
    ///////////////////////


    public int getDisplaywidth() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        if (height <= width) {
            width = height;
        }
        return width;
    }


    public void refitText(TextView tv, float maxTextSize) {
        tv.measure(0, 0);
        int width = getDisplaywidth();
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

}
