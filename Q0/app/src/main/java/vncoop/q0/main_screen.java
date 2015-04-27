package vncoop.q0;

import android.content.Intent;  //FOR ONCLICK
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;       //FOR ONCLICK

public class main_screen extends ActionBarActivity {

    public final static int number_of_teams=5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide(); //HIDE ACTIVITY NAME
        setContentView(R.layout.activity_main_screen);

    }
    ////////////BUTTONS\\\\\\\\\\\\\\

        public void rulesClick(View view){
            Intent rules = new Intent(this, Rules.class);
            startActivity(rules);

        }

        public void NGClick(View view) {
            Intent new_game = new Intent(this, NewGame.class);
            startActivity(new_game);
        }

    /////////END OF BUTTONS\\\\\\\\\\\


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
