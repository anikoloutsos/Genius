package vncoop.genius;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;


public class Terms extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);


        double screenWidth,screenHeight,statusBarHeight,Left,Top,Right,Bottom,screenDensity;
        TextView title = (TextView) findViewById(R.id.rulesId);
        TextView text1 = (TextView) findViewById(R.id.rulesGeneralId);
        Button back = (Button)findViewById(R.id.backId);
        ScrollView sv = (ScrollView)findViewById(R.id.scrollView);
        Typeface font = Typeface.createFromAsset(getAssets(), "VAG-HandWritten.otf");

        title.setTypeface(font);
        text1.setTypeface(font);
        back.setTypeface(font);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth = (double) dm.widthPixels;
        screenHeight = (double) dm.heightPixels;
        statusBarHeight = (double) BasicMethods.getStatusBarHeight(getApplicationContext());
        screenHeight -= statusBarHeight;

        Top = (0.815) * screenHeight;
        BasicMethods.setMargins(back, 0, (int) Top, 0, (int) (0.05 * screenHeight));
        Bottom = 0.185* screenHeight;
        BasicMethods.setMargins(sv, (int) (0.02 * screenWidth), 0, 0, (int) Bottom);
    }

    public void back_click(View view){
        finish();

    }

}
