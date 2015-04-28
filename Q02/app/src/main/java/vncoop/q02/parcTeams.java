package vncoop.q02;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alexandros on 20/4/2015.
 * Parcelable teams class
 */

public class parcTeams implements Parcelable{

    //WHATS INSIDE THE PARCEL\\
    private int color = 0;
    private String name;
    private boolean[] diamonds = new boolean[6];
    private int[] stats = new int[12];      //διατήρηση στατιστικών για εμφάνιση στο τέλος του παιχνιδιού

    //MANDATORY\\
    @Override
    public int describeContents() {
        return 0;
    }


    //MANDATORY + PARCEL'S DATA TO THE PASSED-IN PARCEL\\
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeBooleanArray(diamonds);   //first diamonds
        dest.writeString(name);             //seconde name
        dest.writeInt(color);               //third color
        dest.writeIntArray(stats);
    }


    // this is used to regenerate your object.
    // All Parcelables must have a CREATOR that implements these two methods
    public static final Creator<parcTeams> CREATOR = new Creator<parcTeams>() {
        public parcTeams createFromParcel(Parcel in) {      //Create a new instance of the
                                                            // Parcelable class, instantiating it
                                                            // from the given Parcel whose data had
                                                            // previously been written by
                                                            // Parcelable.writeToParcel().
            return new parcTeams(in);
        }

        public parcTeams[] newArray(int size) {             //Create a new array of the
                                                            // Parcelable class.
            return new parcTeams[size];
        }


    };


    // example constructor that takes a Parcel and gives you an object populated with it's values
    private parcTeams(Parcel in) {

        diamonds = in.createBooleanArray();
        name = in.readString();
        color = in.readInt();
        stats = in.createIntArray();
        //diamonds = in.readBooleanArray();
    }
    public parcTeams(){

        color =0;
        name = "NoNameYet";
        for(int i=0;i<6; i++) {
            diamonds[i] = false;
        }
        for (int i=0;i<12;i++){
            stats[i]=0;
        }
    }
    public parcTeams(String n, int c) {
        name = n;
        color = c;
        for(int i=0;i<6; i++) {
            diamonds[i] = true;
        }
        for (int i=0;i<12;i++){
            stats[i]=0;
        }
        //for(int i=0;i<12; i++) {
         //   stats[i] = 0;
        //}
    }


    //Get back Data\\
    public String get_name(){
        return name;
    }

    public boolean[] get_diamonds(){
        return diamonds;
    }

    public int get_color(){
        return color;
    }

    public int[] getStats(){
        return stats;
    }

    //Set Data\\
    public void set_name(String s){
        name = s;
    }

    public void set_color (int a){
        color = a;
    }

    public void set_diamonds(int a, boolean b){
        diamonds[a] = b;
    }

    public void set_stats_category_wrong(int categoryNum){
        stats[categoryNum]++;
    }
    public void set_stats_category_correct(int categoryNum){
        stats[categoryNum]++;
        stats[categoryNum+6]++;
    }

}
