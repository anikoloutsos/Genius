package vncoop.genius;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;


public class AboutUs extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_about_us);
        TextView t1= (TextView) findViewById(R.id.aboutId);
        TextView t2 = (TextView) findViewById(R.id.text1id);
        TextView t3 = (TextView) findViewById(R.id.text2id);
        TextView t4= (TextView) findViewById(R.id.names1id);
        TextView t5 = (TextView) findViewById(R.id.names2id);
        TextView t6 = (TextView) findViewById(R.id.generalid);
        Typeface font = Typeface.createFromAsset(getAssets(), "VAG-HandWritten.otf");
        t1.setTypeface(font);
        t2.setTypeface(font);
        t3.setTypeface(font);
        t4.setTypeface(font);
        t5.setTypeface(font);
        t6.setTypeface(font);
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
