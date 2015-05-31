package vncoop.q02;


import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;


public class Rules extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_rules);
    }

    public void back_click(View view){
        finish();

    }

}
