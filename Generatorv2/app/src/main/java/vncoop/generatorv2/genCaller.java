package vncoop.generatorv2;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class genCaller extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* MADE FOR TESTING randGen NOT USEFULL ANYMORE
        boolean[] d = new boolean[6];
        for (int i = 0; i<6;i++){
            d[i] = false;
        }
        d[2]=true;
        d[3]=true;
        randGen a = new randGen();
        String[] choices = a.getRandomCategories(d);
        for (int i = 0 ;i<50;i++) {
            Log.d("Choice 1 "+i+" time", choices[0]);
            Log.d("Choice 2 "+i+" time", choices[1]);
            choices = a.getRandomCategories(d);
        }
        */
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
