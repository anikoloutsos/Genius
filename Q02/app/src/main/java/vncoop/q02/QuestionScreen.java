package vncoop.q02;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class QuestionScreen extends Activity implements FragmentManager.OnBackStackChangedListener {

    String[] questionAndAnswer = new String[2];
    int current_team;
    int number_of_teams;
    parcTeams[] teams;
    boolean isDiamond;
    int category;
    private int fileIndex;
    private int diamondNum;
    CardFrontFragment cardfrontfragment= new CardFrontFragment();
    CardBackFragment cardbackfragment= new CardBackFragment();

    MediaPlayer wrongSound;
    MediaPlayer diamondsound;
    MediaPlayer correctsound;



    private boolean mShowingBack = false;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_screen);

        //
        wrongSound = MediaPlayer.create(this, R.raw.wrong);
        diamondsound = MediaPlayer.create(this, R.raw.diamondi);
        correctsound = MediaPlayer.create(this, R.raw.correct);
        ///get intents

        Intent intent=getIntent();
        current_team = intent.getIntExtra("current_message", 1);
        number_of_teams = intent.getIntExtra("number_of_teams", 1);
        teams = new parcTeams[number_of_teams];
        for (int i=0;i<number_of_teams;i++) {
            teams[i] = intent.getParcelableExtra("team"+i);
        }
        isDiamond = intent.getBooleanExtra("isDiamond",false);
        category = intent.getIntExtra("categoryNum",0);
        fileIndex = intent.getIntExtra("file_index", 0);

       

        DBHelper finder = new DBHelper(getApplicationContext());
        questionAndAnswer = finder.randFromCat(category);



        //

        //send to fragments

        Bundle bundle1 = new Bundle();
        bundle1.putString("catText",intCatToText(category));
        bundle1.putString("cat",intCatToString(category));
        bundle1.putString("que",questionAndAnswer[0]);
        bundle1.putBoolean("isdiamond",isDiamond);

        Bundle bundle2 = new Bundle();
        bundle2.putString("cat",intCatToString(category));
        bundle2.putString("ans",questionAndAnswer[1]);

        cardfrontfragment.setArguments(bundle1);
        cardbackfragment.setArguments(bundle2);

        //




        if (savedInstanceState == null) {
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.layout, cardfrontfragment)
                    .commit();
        }


    }

    private void flipCard() {

        //flip to front
        if (mShowingBack) {

            getFragmentManager()
                    .beginTransaction()

                            // Replace the default fragment animations with animator resources representing
                            // rotations when switching to the back of the card, as well as animator
                            // resources representing rotations when flipping back to the front (e.g. when
                            // the system Back button is pressed).
                    .setCustomAnimations(
                            R.anim.card_flip_right_in, R.anim.card_flip_right_out,
                            R.anim.card_flip_left_in, R.anim.card_flip_left_out)

                            // Replace any fragments currently in the container view with a fragment
                            // representing the next page (indicated by the just-incremented currentPage
                            // variable).
                    .replace(R.id.layout, cardfrontfragment)

                            // Add this transaction to the back stack, allowing users to press Back
                            // to get to the front of the card.
                    .addToBackStack(null)



                            // Commit the transaction.
                    .commit();
            mShowingBack=false;

            return;
        }else{



            getFragmentManager()
                    .beginTransaction()


                            // Replace the default fragment animations with animator resources representing
                            // rotations when switching to the back of the card, as well as animator
                            // resources representing rotations when flipping back to the front (e.g. when
                            // the system Back button is pressed).
                    .setCustomAnimations(
                            R.anim.card_flip_right_in, R.anim.card_flip_right_out,
                            R.anim.card_flip_left_in, R.anim.card_flip_left_out)

                            // Replace any fragments currently in the container view with a fragment
                            // representing the next page (indicated by the just-incremented currentPage
                            // variable).
                    .replace(R.id.layout, cardbackfragment)

                            // Add this transaction to the back stack, allowing users to press Back
                            // to get to the front of the card.
                    .addToBackStack(null)

                            // Commit the transaction.
                    .commit();
            mShowingBack = true;

            return;
        }

    }

    @Override
    public void onBackStackChanged() {
        mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);

        // When the back stack changes, invalidate the options menu (action bar).
        RelativeLayout answb= (RelativeLayout)findViewById(R.id.buttonid);
        answb.setEnabled(false);

    }






    public void answerbtn(View view){

        RelativeLayout answb= (RelativeLayout)findViewById(R.id.buttonid);
        answb.setEnabled(true);
        flipCard();

    }


    public void onCorrect(View view){


        teams[current_team].set_stats_category_correct(category-1);


        Intent intent1 = new Intent(this,Winner.class);
        boolean WeDontHaveAWinner= true;
        //an itan erwtisi gia diamanti kane true to sigkekrimeno diamanti
        if (isDiamond){

            teams[current_team].set_diamonds(category-1,true);
            //AN EXEI OLA TA DIAMANTIA NIKAEI
            boolean[] AllDiamonds;
            int diamondCounter=0;
            AllDiamonds = teams[current_team].get_diamonds();
            for (int i=0;i<6;i++){
                if (AllDiamonds[i]){
                    diamondCounter++;
                }
            }
            if (true){//changed diamondCounter == 6 to true to check winning team

                for (int i = 0;i<number_of_teams;i++) {
                    intent1.putExtra("team"+i, (android.os.Parcelable) teams[i]);
                }
                intent1.putExtra("current_message",current_team);
                intent1.putExtra("number_of_teams",number_of_teams);
                intent1.putExtra("file_index",fileIndex);
                WeDontHaveAWinner = false;
            }
        }
            //hxos
            correctsound.start();
            //telos

        Intent intent = new Intent(this, ContinueScreen.class);

        intent.putExtra("number_of_teams",number_of_teams);
        intent.putExtra("current_message", current_team);
        intent.putExtra("same_team",true);
        //for animation
        if(isDiamond) {
            intent.putExtra("Catdiamond", category);
        }
        //
        for (int i = 0;i<number_of_teams;i++) {
            intent.putExtra("team"+i, (android.os.Parcelable) teams[i]);
        }
        intent.putExtra("file_index",fileIndex);
        intent.putExtra("Question",questionAndAnswer[0]);
        if (WeDontHaveAWinner){
            startActivity(intent);
        }
        else{
            startActivity(intent1);
        }
        this.finish();
    }

    public void onWrong(View view){

        wrongSound.start();


        teams[current_team].set_stats_category_wrong(category-1);

        current_team++;
        current_team = current_team%number_of_teams;

        Intent intent = new Intent(this, ContinueScreen.class);

        intent.putExtra("number_of_teams",number_of_teams);
        intent.putExtra("current_message", current_team);
        intent.putExtra("same_team",false);
        for (int i = 0;i<number_of_teams;i++) {
            intent.putExtra("team"+i, (android.os.Parcelable) teams[i]);
        }
        intent.putExtra("file_index",fileIndex);
        intent.putExtra("Question",questionAndAnswer[0]);
        startActivity(intent);
        finish();
    }



    public String intCatToString(int col){
        if(col==1){
            return "geo";
        }else if(col==2){
            return "cim";
        }else if(col==3){
            return "his";
        }else if(col==4){
            return "art";
        }else if(col==5){
            return "sci";
        }else{
            return "spo";
        }
    }


    public String intCatToText(int col){
        if(col==1){
            return "Γεωγραφία";
        }else if(col==2){
            return "Ψυχαγωγία";
        }else if(col==3){
            return "Ιστορία";
        }else if(col==4){
            return "Τέχνες";
        }else if(col==5){
            return "Επιστήμη";
        }else{
            return "Χόμπυ";
        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Σταμάτημα Παιχνιδιού")
                .setMessage("Είστε σίγουροι ότι θέλετε να επιστρέψετε στην αρχική οθόνη;")
                .setPositiveButton("Ναι", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("Όχι", null)
                .show();
    }

}
