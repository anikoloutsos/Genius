package vncoop.q02;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.IOException;


public class mainMenu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


        //String[] dblist = this.databaseList();

        //Log.d("i have", dblist[0]);

        //this.deleteDatabase("FDB.sqlite");

        DBHelper dbCreator = new DBHelper(getApplicationContext());
        try{
            dbCreator.createDB();
        }catch (IOException ex){
            throw new Error("Mpoulo");
        }


    }

    ////////////BUTTONS\\\\\\\\\\\\\\


    public void NGClick(View view) {
        Intent new_game = new Intent(this, ChooseNumOfPlayers.class);
        startActivity(new_game);
    }

    public void GoOnClick(View view){
        Intent goOn = new Intent(this,MainGame.class);
        //todo vale ta parcelables (teams current klp)
        startActivity(goOn);
    }

    public void rulesClick(View view){
        Intent rules = new Intent(this, Rules.class);
        startActivity(rules);

    }



    /////////END OF BUTTONS\\\\\\\\\\\


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
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
