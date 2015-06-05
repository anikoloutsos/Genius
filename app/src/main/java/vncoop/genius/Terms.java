package vncoop.genius;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;


public class Terms extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);


        double Top,Bottom;
        double[] screen = BasicMethods.getScreenChar(this);
        TextView title = (TextView) findViewById(R.id.rulesId);
        TextView text1 = (TextView) findViewById(R.id.rulesGeneralId);
        Button back = (Button)findViewById(R.id.backId);
        ScrollView sv = (ScrollView)findViewById(R.id.scrollView);
        Typeface font = Typeface.createFromAsset(getAssets(), "VAG-HandWritten.otf");

        title.setTypeface(font);
        text1.setTypeface(font);
        back.setTypeface(font);



        Top = (0.815) * screen[1];
        BasicMethods.setMargins(back, 0, (int) Top, 0, (int) (0.05 * screen[1]));
        Bottom = 0.185* screen[1];
        BasicMethods.setMargins(sv, (int) (0.02 * screen[0]), 0, 0, (int) Bottom);
    }

    public void back_click(View view){
        finish();

    }

}
