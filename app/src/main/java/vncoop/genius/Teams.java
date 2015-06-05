package vncoop.genius;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Teams implements Parcelable,Serializable{

    //WHATS INSIDE THE PARCEL\\
    private int color = 0;
    private String name;
    private boolean[] diamonds = new boolean[6];
    private int[] stats = new int[12];

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
    public static final Creator<Teams> CREATOR = new Creator<Teams>() {
        public Teams createFromParcel(Parcel in) {      //Create a new instance of the
            // Parcelable class, instantiating it
            // from the given Parcel whose data had
            // previously been written by
            // Parcelable.writeToParcel().
            return new Teams(in);
        }

        public Teams[] newArray(int size) {             //Create a new array of the
            // Parcelable class.
            return new Teams[size];
        }


    };


    // example constructor that takes a Parcel and gives you an object populated with it's values
    private Teams(Parcel in) {

        diamonds = in.createBooleanArray();
        name = in.readString();
        color = in.readInt();
        stats = in.createIntArray();
        //diamonds = in.readBooleanArray();
    }
    public Teams(){

        color =0;
        name = "NoNameYet";
        for(int i=0;i<6; i++) {
            diamonds[i] = false;
        }
        for (int i=0;i<12;i++){
            stats[i]=0;
        }
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


    public float get_category_stats(int i){
        return (float) stats[i];
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

    public String get_string_color(){
        if (color == 1) {
            return "yellow";
        } else if (color == 2) {
            return "blue";
        } else if (color == 3) {
            return "green";
        } else if (color == 4) {
            return "pink";
        } else if (color == 5) {
            return "purple";
        } else {
            return "red";
        }
    }

}
