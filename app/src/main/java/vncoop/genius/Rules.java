package vncoop.genius;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;


public class Rules extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rules);
        TextView title = (TextView) findViewById(R.id.rulesId);
        TextView generalRules = (TextView) findViewById(R.id.rulesGeneralId);
        TextView gameObjectTitle = (TextView) findViewById(R.id.gameObjectTitle);
        TextView gameObject = (TextView) findViewById(R.id.gameObject);
        TextView howToPlayTitle = (TextView) findViewById(R.id.HowToPlayTitle);
        TextView howToPlay = (TextView) findViewById(R.id.HowToPlay);
        TextView endOfGameTitle = (TextView) findViewById(R.id.EndOfGameTitle);
        TextView endOfGame = (TextView) findViewById(R.id.EndOfGame);
        Typeface font = Typeface.createFromAsset(getAssets(), "VAG-HandWritten.otf");
        generalRules.setTypeface(font);
        gameObjectTitle.setTypeface(font);
        gameObject.setTypeface(font);
        howToPlayTitle.setTypeface(font);
        howToPlay.setTypeface(font);
        endOfGameTitle.setTypeface(font);
        endOfGame.setTypeface(font);
        title.setTypeface(font);
        //Setting home and Next buttons margins

        ImageButton homeButton = (ImageButton)findViewById(R.id.homeButtonId);
        ScrollView sv = (ScrollView)findViewById(R.id.scrollView);
        double screen[] = BasicMethods.getScreenChar(this);
        double Top = (0.815) * screen[1];
        BasicMethods.setMargins(homeButton, 0, (int) Top, 0, (int) (0.05 * screen[1]));
        double Bottom = 0.185* screen[1];
        BasicMethods.setMargins(sv, (int) (0.02 * screen[0]), 0, 0, (int) Bottom);
    }

    public void back_click(View view){
        finish();

    }

}
