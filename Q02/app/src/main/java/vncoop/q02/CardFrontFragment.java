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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by thanos on 14/5/2015.
 */
public class CardFrontFragment extends Fragment {

    private View myFragmentView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myFragmentView = inflater.inflate(R.layout.card_front, container, false);

        ImageView test = (ImageView) myFragmentView.findViewById(R.id.card);
        int orgnlHeight = test.getDrawable().getIntrinsicHeight();
        int orgnlWidth = test.getDrawable().getIntrinsicWidth();

        Log.d("o_height",Integer.toString(orgnlHeight));

        Log.d("o_width",Integer.toString(orgnlWidth));


        RelativeLayout rl = (RelativeLayout) myFragmentView.findViewById(R.id.rlt);
        rl.getLayoutParams().height = orgnlHeight;
        rl.getLayoutParams().width = orgnlWidth;


        String question = getArguments().getString("que");
        String category = getArguments().getString("cat");
        String categoryText = getArguments().getString("catText");
        boolean isdiamond = getArguments().getBoolean("isdiamond");


        TextView questiontext = (TextView) myFragmentView.findViewById(R.id.questionId);
        questiontext.setText(question);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "VAG-HandWritten.otf");
        questiontext.setTypeface(font);

        int backId= getResources().getIdentifier(category + "_que", "drawable", getActivity().getPackageName());
        ImageView Layoutc = (ImageView) myFragmentView.findViewById(R.id.card);
        Layoutc.setImageResource(backId);

        TextView catTitle = (TextView)myFragmentView.findViewById(R.id.categoryTitle);
        catTitle.setTypeface(font);
        catTitle.setText(categoryText);

        if(isdiamond) {
            ImageView diamond = (ImageView) myFragmentView.findViewById(R.id.diamond);
            int diamId = getResources().getIdentifier(category, "drawable", getActivity().getPackageName());
            diamond.setImageResource(diamId);
        }


        return myFragmentView;
    }



}
