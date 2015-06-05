package vncoop.genius;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class CardBackFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myFragmentView = inflater.inflate(R.layout.card_back, container, false);
        
        double[] screen = BasicMethods.getScreenChar(getActivity());
        double cardHeight, cardWidth, cardRatio, percentWidth,
                percentHeight, differenceInWidth, differenceInHeight, screenRatio, Left,
                Right, Top, Bottom,orgnlHeight,orgnlWidth;

        String answer = getArguments().getString("ans");
        String category = getArguments().getString("cat");
        ImageView card = (ImageView) myFragmentView.findViewById(R.id.card);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "VAG-HandWritten.otf");
        RelativeLayout rl = (RelativeLayout) myFragmentView.findViewById(R.id.rlt);
        TextView categoryTitle = (TextView) myFragmentView.findViewById(R.id.categoryTitle);
        TextView questionText = (TextView) myFragmentView.findViewById(R.id.questionId);
        TextView hint = (TextView) myFragmentView.findViewById(R.id.hintId);
        ImageButton correct = (ImageButton) myFragmentView.findViewById(R.id.correctbtnid);
        ImageButton wrong = (ImageButton) myFragmentView.findViewById(R.id.wrongbtnid);

        int backId= getResources().getIdentifier(category + "_que", "drawable", getActivity().getPackageName());
        card.setImageResource(backId);

        questionText.setText(answer);
        questionText.setTypeface(font);

        categoryTitle.setTypeface(font);
        categoryTitle.setText("Απάντηση");

        hint.setTypeface(font);

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

        //Answer margins
        Left = (0.012245* orgnlWidth);
        Top = (0.38330046* orgnlHeight);
        Right = (0.012245* orgnlWidth);
        BasicMethods.setMargins(questionText, (int) Left,(int) Top,(int) Right, 0);
        questionText.setTextSize((float) ((0.058/screen[2])*orgnlHeight));

        //Hint margins
        Left = (0.012245* orgnlWidth);
        Top = (0.580539119* orgnlHeight);
        Right = (0.012245* orgnlWidth);
        BasicMethods.setMargins(hint, (int) Left,(int) Top,(int) Right, 0);
        hint.setTextSize((float) ((0.04/screen[2])*orgnlHeight));

        //Wrong button margins
        Left = (0.0612244897* orgnlWidth);
        Top = (0.7034845496* orgnlHeight);
        Right = (0.5408632* orgnlWidth);
        Bottom = (0.0394477317*orgnlHeight);
        BasicMethods.setMargins(wrong, (int) Left, (int) Top, (int) Right, (int) Bottom);

        //Correct button margins
        BasicMethods.setMargins(correct, (int) Right, (int) Top, (int) Left, (int) Bottom);
        return myFragmentView;
    }

}
