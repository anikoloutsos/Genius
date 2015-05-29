package vncoop.q02;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
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

        double cardHeight, cardWidth, cardRatio, screenWidth, screenHeight, percentWidth,
                percentHeight, differenceInWidth, differenceInHeight, screenRatio, Left,
                Right, Top, Bottom,statusBarHeight,orgnlWidth,orgnlHeight;

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

        //Screen characteristics
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth = (double) dm.widthPixels;
        screenHeight = (double) dm.heightPixels;
        double screenDensity = (double) dm.density;

        statusBarHeight = (double) getStatusBarHeight();
        screenHeight -= statusBarHeight;

        screenRatio = (screenHeight / screenWidth);
        percentWidth = (screenWidth / cardWidth);
        percentHeight = (screenHeight / cardHeight);

        if (cardRatio < screenRatio){
            orgnlWidth = screenWidth;
            orgnlHeight = (cardHeight *percentWidth);
            differenceInWidth = 0;
            differenceInHeight = Math.abs(screenHeight - orgnlHeight);

        }else{
            orgnlWidth = (cardWidth * percentHeight);
            orgnlHeight = screenHeight;
            differenceInHeight = 0;
            differenceInWidth = Math.abs(screenWidth - orgnlWidth);

        }


        //RelativeLayout margins
        Left = (differenceInWidth/2+(0.073*orgnlWidth));
        Right = (differenceInWidth/2+(0.074*orgnlWidth));
        Top = (differenceInHeight/2+(0.044* orgnlHeight));
        Bottom = (differenceInHeight/2+(0.052* orgnlHeight));
        setMargins(rl, (int) Left, (int) Top, (int) Right, (int) Bottom);

        //CategoryTitle margins
        Top = (0.002629848783694* orgnlHeight);
        Bottom = (0.12360289* orgnlHeight);
        setMargins(categoryTitle,0,(int) Top,0,(int) Bottom);
        categoryTitle.setTextSize((float) ((0.09/screenDensity)*orgnlHeight));

        //Answer margins
        Left = (0.012245* orgnlWidth);
        Top = (0.38330046* orgnlHeight);
        Right = (0.012245* orgnlWidth);
        setMargins(questionText, (int) Left,(int) Top,(int) Right, 0);
        questionText.setTextSize((float) ((0.058/screenDensity)*orgnlHeight));

        //Hint margins
        Left = (0.012245* orgnlWidth);
        Top = (0.580539119* orgnlHeight);
        Right = (0.012245* orgnlWidth);
        setMargins(hint, (int) Left,(int) Top,(int) Right, 0);
        hint.setTextSize((float) ((0.04/screenDensity)*orgnlHeight));

        //Wrong button margins
        Left = (0.0612244897* orgnlWidth);
        Top = (0.7034845496* orgnlHeight);
        Right = (0.5408632* orgnlWidth);
        Bottom = (0.0394477317*orgnlHeight);
        setMargins(wrong, (int) Left, (int) Top, (int) Right, (int) Bottom);

        //Correct button margins
        setMargins(correct, (int) Right, (int) Top, (int) Left, (int) Bottom);
        return myFragmentView;
    }

    public static void setMargins(View v, int l, int t, int r, int b) {

        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {

            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

}
