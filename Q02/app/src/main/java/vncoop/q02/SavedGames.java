package vncoop.q02;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class SavedGames extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_games);

        TextView dateFile1Text = (TextView) findViewById(R.id.dateFile1textId);

        dateFile1Text.setText("");



    }


}
