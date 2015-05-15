package vncoop.q02;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by thanos on 14/5/2015.
 */
public class CardBackFragment extends Fragment {

    private View myFragmentView;

    String[] questionAndAnswer = new String[2];
    int current_team;
    int number_of_teams;
    parcTeams[] teams;
    boolean isDiamond;
    int category;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myFragmentView = inflater.inflate(R.layout.card_back, container, false);




        String answer = getArguments().getString("ans");
        String category = getArguments().getString("cat");

        TextView questiontext = (TextView) myFragmentView.findViewById(R.id.questionId);
        questiontext.setText(answer);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "VAG-HandWritten.otf");
        questiontext.setTypeface(font);

        int backId= getResources().getIdentifier(category + "_que", "drawable", getActivity().getPackageName());
        RelativeLayout Layoutc= (RelativeLayout)myFragmentView.findViewById(R.id.layout);
        Layoutc.setBackgroundResource(backId);


        TextView catTitle = (TextView)myFragmentView.findViewById(R.id.categoryTitle);
        catTitle.setTypeface(font);
        catTitle.setText("Απάντηση");

        TextView hint = (TextView) myFragmentView.findViewById(R.id.hintId);
        hint.setTypeface(font);


        return myFragmentView;
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




}
