package vncoop.genius;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CardFrontFragment extends Fragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myFragmentView = inflater.inflate(R.layout.card_front, container, false);
        //Orismoi
        double[] screen = BasicMethods.getScreenChar(getActivity());
        double cardHeight, cardWidth, cardRatio, percentWidth,
                percentHeight, differenceInWidth, differenceInHeight, screenRatio, Left,
                Right, Top, Bottom,orgnlHeight,orgnlWidth;

        ImageView card = (ImageView) myFragmentView.findViewById(R.id.card);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "VAG-HandWritten.otf");
        RelativeLayout rl = (RelativeLayout) myFragmentView.findViewById(R.id.rlt);
        TextView categoryTitle = (TextView) myFragmentView.findViewById(R.id.categoryTitle);
        TextView questionText = (TextView) myFragmentView.findViewById(R.id.questionId);

        int backId = getResources().getIdentifier(getArguments().getString("cat") + "_que", "drawable", getActivity().getPackageName());
        card.setImageResource(backId);

        //Card characteristics
        cardHeight = (double) (card.getDrawable().getIntrinsicHeight());
        cardWidth = (double) (card.getDrawable().getIntrinsicWidth());
        cardRatio = cardHeight / cardWidth;


        screenRatio = (screen[1] / screen[0]);
        percentWidth = (screen[0] / cardWidth);
        percentHeight = (screen[1] / cardHeight);

        if (cardRatio < screenRatio){
            orgnlWidth = screen[0];
            orgnlHeight = (cardHeight *percentWidth);
            differenceInWidth = 0;
            differenceInHeight = Math.abs(screen[1] - orgnlHeight);

        }else{
            orgnlWidth = (cardWidth * percentHeight);
            orgnlHeight = screen[1];
            differenceInHeight = 0;
            differenceInWidth = Math.abs(screen[0] - orgnlWidth);

        }




        categoryTitle.setTypeface(font);
        categoryTitle.setText(getArguments().getString("catText"));

        questionText.setTypeface(font);
        questionText.setText(getArguments().getString("que"));



        //RelativeLayout margins
        Left = (differenceInWidth/2+(0.073*orgnlWidth));
        Right = (differenceInWidth/2+(0.074*orgnlWidth));
        Top = (differenceInHeight/2+(0.044* orgnlHeight));
        Bottom = (differenceInHeight/2+(0.052* orgnlHeight));
        BasicMethods.setMargins(rl, (int) Left, (int) Top, (int) Right, (int) Bottom);


        //CategoryTitle margins
        Top = (0.002629848783694* orgnlHeight);
        Bottom = (0.12360289* orgnlHeight);
        BasicMethods.setMargins(categoryTitle,0,(int) Top,0,(int) Bottom);
        categoryTitle.setTextSize((float) ((0.09/screen[2])*orgnlHeight));

        //Question margins
        Left = (0.012245* orgnlWidth);
        Top = (0.41617357* orgnlHeight);
        Right = (0.012245* orgnlWidth);
        BasicMethods.setMargins(questionText, (int) Left,(int) Top,(int) Right, 0);
        questionText.setTextSize((float) ((0.058/screen[2])*orgnlHeight));






        if (getArguments().getBoolean("isdiamond")) {
            ImageView diamond = (ImageView) myFragmentView.findViewById(R.id.diamond);
            int diamId = getResources().getIdentifier(getArguments().getString("cat"), "drawable", getActivity().getPackageName());
            diamond.setImageResource(diamId);
        }


            return myFragmentView;

    }

}
