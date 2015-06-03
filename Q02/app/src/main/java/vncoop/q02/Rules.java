package vncoop.q02;


import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;


public class Rules extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_rules);


        TextView title = (TextView) findViewById(R.id.rulesId);
        TextView text1 = (TextView) findViewById(R.id.rulesGeneralId);
        TextView text2 = (TextView) findViewById(R.id.textView7);
        TextView text3 = (TextView) findViewById(R.id.textView8);
        TextView text4 = (TextView) findViewById(R.id.textView9);
        TextView text5 = (TextView) findViewById(R.id.textView10);
        TextView text6 = (TextView) findViewById(R.id.textView11);
        TextView text7 = (TextView) findViewById(R.id.textView12);
        Typeface font = Typeface.createFromAsset(getAssets(), "VAG-HandWritten.otf");
        title.setTypeface(font);
        text1.setTypeface(font);
        text2.setTypeface(font);
        text3.setTypeface(font);
        text4.setTypeface(font);
        text5.setTypeface(font);
        text6.setTypeface(font);
        text7.setTypeface(font);

       //Setting home button margins

        ImageButton homeButton = (ImageButton)findViewById(R.id.homeButtonId);
        ScrollView sv = (ScrollView)findViewById(R.id.scrollView);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        double screenWidth = (double) dm.widthPixels;
        double screenHeight = (double) dm.heightPixels;
        double statusBarHeight = (double) getStatusBarHeight();
        screenHeight -= statusBarHeight;

        double Top = (0.815) * screenHeight;
        setMargins(homeButton, 0, (int) Top, 0, (int)(0.05*screenHeight));
        double Bottom = 0.185* screenHeight;
        setMargins(sv, (int)(0.02*screenWidth), 0, 0, (int)Bottom);
    }

    public void back_click(View view){
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
