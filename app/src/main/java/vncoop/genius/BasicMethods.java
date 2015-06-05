package vncoop.genius;


import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BasicMethods {

    static String directory;
    static String save1, save2, save3;

    public static void setDirectory(Context context){
        directory = context.getFilesDir().getPath();
        save1 = directory + "/saveGame1";
        save2 = directory + "/saveGame2";
        save3 = directory + "/saveGame3";

    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;

        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
    public static void setMargins(View v, int l, int t, int r, int b) {

        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {

            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }
    public static void refitText(TextView tv, float maxTextSize, int width) {
        tv.measure(0, 0);
        int textWidth = tv.getMeasuredWidth();
        float trySize = maxTextSize;
        while (textWidth > width) {
            trySize -= 1;
            tv.setTextSize(trySize);
            tv.measure(0, 0);
            textWidth = tv.getMeasuredWidth();
        }

        tv.setTextSize(trySize);

    }
    public static double[] getScreenChar(Activity activity){
        double[] screen = new double[3];
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        screen[0] = (double) dm.widthPixels;
        screen[1] = (double) dm.heightPixels;
        screen[2] = (double) dm.density;
        screen[1] -= (double) getStatusBarHeight(activity.getApplicationContext());
        return screen;

    }

    public static String catToText(String cat) {

        switch (cat) {
            case "geo_b":
                return "Γεωγραφία";
            case "cim_b":
                return "Ψυχαγωγία";
            case "his_b":
                return "Ιστορία";
            case "art_b":
                return "Τέχνες";
            case "sci_b":
                return "Επιστήμη";
            default:
                return "Χόμπυ";
        }

    }
    public static String intCatToText(int col){
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

    public static String intCatToString(int col){
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

    public static String intCatToColorString(int col){
        if(col==1){
            return "blue";
        }else if(col==2){
            return "pink";
        }else if(col==3){
            return "red";
        }else if(col==4){
            return "purple";
        }else if(col==5){
            return "green";
        }else{
            return "yellow";
        }
    }

}
