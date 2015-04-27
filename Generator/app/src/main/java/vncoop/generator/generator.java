package vncoop.generator;

import android.content.Intent;
import android.opengl.Visibility;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class generator extends ActionBarActivity {

    ImageButton[] ColoursArray = new ImageButton[61];
    //ARRAY
    int p=0,i,i1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_generator);

        //ARRAY INITIALIZATION

        //BLUE 1
        ColoursArray[1] = (ImageButton) findViewById(R.id.b1);
        ColoursArray[2] = (ImageButton) findViewById(R.id.b1);
        ColoursArray[3] = (ImageButton) findViewById(R.id.b1);
        ColoursArray[4] = (ImageButton) findViewById(R.id.b1);
        ColoursArray[5] = (ImageButton) findViewById(R.id.b1);

        //PINK 1
        ColoursArray[6] = (ImageButton) findViewById(R.id.p1);
        ColoursArray[7] = (ImageButton) findViewById(R.id.p1);
        ColoursArray[8] = (ImageButton) findViewById(R.id.p1);
        ColoursArray[9] = (ImageButton) findViewById(R.id.p1);
        ColoursArray[16] = (ImageButton) findViewById(R.id.p1);

        ///YELLOW 1
        ColoursArray[10] = (ImageButton) findViewById(R.id.y1);
        ColoursArray[11] = (ImageButton) findViewById(R.id.y1);
        ColoursArray[12] = (ImageButton) findViewById(R.id.y1);
        ColoursArray[17] = (ImageButton) findViewById(R.id.y1);
        ColoursArray[21] = (ImageButton) findViewById(R.id.y1);

        //KAFE 1
        ColoursArray[13] = (ImageButton) findViewById(R.id.k1);
        ColoursArray[14] = (ImageButton) findViewById(R.id.k1);
        ColoursArray[18] = (ImageButton) findViewById(R.id.k1);
        ColoursArray[22] = (ImageButton) findViewById(R.id.k1);
        ColoursArray[25] = (ImageButton) findViewById(R.id.k1);

        //GREEN 1
        ColoursArray[15] = (ImageButton) findViewById(R.id.g1);
        ColoursArray[19] = (ImageButton) findViewById(R.id.g1);
        ColoursArray[23] = (ImageButton) findViewById(R.id.g1);
        ColoursArray[26] = (ImageButton) findViewById(R.id.g1);
        ColoursArray[28] = (ImageButton) findViewById(R.id.g1);

        //ORANGE 1
        ColoursArray[20] = (ImageButton) findViewById(R.id.o1);
        ColoursArray[24] = (ImageButton) findViewById(R.id.o1);
        ColoursArray[27] = (ImageButton) findViewById(R.id.o1);
        ColoursArray[29] = (ImageButton) findViewById(R.id.o1);
        ColoursArray[30] = (ImageButton) findViewById(R.id.o1);

        //BLUE 2
        ColoursArray[46] = (ImageButton) findViewById(R.id.b2);
        ColoursArray[47] = (ImageButton) findViewById(R.id.b2);
        ColoursArray[48] = (ImageButton) findViewById(R.id.b2);
        ColoursArray[49] = (ImageButton) findViewById(R.id.b2);
        ColoursArray[50] = (ImageButton) findViewById(R.id.b2);

        //PINK 2
        ColoursArray[31] = (ImageButton) findViewById(R.id.p2);
        ColoursArray[51] = (ImageButton) findViewById(R.id.p2);
        ColoursArray[52] = (ImageButton) findViewById(R.id.p2);
        ColoursArray[53] = (ImageButton) findViewById(R.id.p2);
        ColoursArray[54] = (ImageButton) findViewById(R.id.p2);

        ///YELLOW 2
        ColoursArray[32] = (ImageButton) findViewById(R.id.y2);
        ColoursArray[36] = (ImageButton) findViewById(R.id.y2);
        ColoursArray[55] = (ImageButton) findViewById(R.id.y2);
        ColoursArray[56] = (ImageButton) findViewById(R.id.y2);
        ColoursArray[57] = (ImageButton) findViewById(R.id.y2);

        //KAFE 2
        ColoursArray[33] = (ImageButton) findViewById(R.id.k2);
        ColoursArray[37] = (ImageButton) findViewById(R.id.k2);
        ColoursArray[40] = (ImageButton) findViewById(R.id.k2);
        ColoursArray[58] = (ImageButton) findViewById(R.id.k2);
        ColoursArray[59] = (ImageButton) findViewById(R.id.k2);

        //GREEN 2
        ColoursArray[34] = (ImageButton) findViewById(R.id.g2);
        ColoursArray[38] = (ImageButton) findViewById(R.id.g2);
        ColoursArray[41] = (ImageButton) findViewById(R.id.g2);
        ColoursArray[43] = (ImageButton) findViewById(R.id.g2);
        ColoursArray[60] = (ImageButton) findViewById(R.id.g2);

        //ORANGE 2
        ColoursArray[35] = (ImageButton) findViewById(R.id.o2);
        ColoursArray[39] = (ImageButton) findViewById(R.id.o2);
        ColoursArray[42] = (ImageButton) findViewById(R.id.o2);
        ColoursArray[44] = (ImageButton) findViewById(R.id.o2);
        ColoursArray[45] = (ImageButton) findViewById(R.id.o2);

    }

    public void newnum(View view){



        //int i=1;
        //R.id.txt1
        if (p==1){
            ColoursArray[i+i1*15].setVisibility(View.GONE);
            ColoursArray[i+30+i1*15].setVisibility(View.GONE);
        }
        final TextView tv1 = (TextView) findViewById(R.id.txt1);
        final TextView tv2 = (TextView) findViewById(R.id.txt2);
        Random r = new Random();
        i = r.nextInt(21)+1;
        Random r1 = new Random();
        i1 = r1.nextInt(2);
        String s = String.valueOf(i);
        String s1 = String.valueOf(i1);
        tv1.setText(s);
        tv2.setText(s1);

        p=0;
        if (i>15 && i<22){
            tv2.setText("diamanti");
        }
        else if(i>21){
            tv2.setText("mpoulo");
        }
        else{
            ColoursArray[i+i1*15].setVisibility(View.VISIBLE);
            ColoursArray[i+30+i1*15].setVisibility(View.VISIBLE);
            tv2.setText("epilogi");
            p=1;
        }

        //ImageButton ib = (ImageButton) findViewById(R.id.imageButton);
        //ib.setVisibility(View.VISIBLE);
        //if(i<6) {
        //    ColoursArray[1].setVisibility(View.VISIBLE);
        //}
        //else{
        //    ColoursArray[1].setVisibility(View.GONE);
        // }
//        R.id.imageButton.setVisible(true);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_generator, menu);
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
