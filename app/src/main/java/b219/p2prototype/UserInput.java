package b219.p2prototype;

import android.os.Parcel;
import android.os.Parcelable;



public class UserInput implements Parcelable{


    private int mood;
    private int genre;


    UserInput(int moodChosen, int genreChosen){
        mood = moodChosen;
        genre = genreChosen;
    }


    private UserInput(Parcel in) {
        mood = in.readInt();
        genre = in.readInt();
    }


    public int describeContents(){
        return 0;
    }


    public void writeToParcel(Parcel out, int flags){
        out.writeInt(mood);
        out.writeInt(genre);
    }


    public static final Parcelable.Creator<UserInput> CREATOR
            = new Parcelable.Creator<UserInput>() {
        public UserInput createFromParcel(Parcel in) {
            return new UserInput(in);
        }

        public UserInput[] newArray(int size) {
            return new UserInput[size];
        }
    };


    public int getMood(){
        return mood;
    }


    public int getGenre(){
        return genre;
    }


    public String toString() {
        return "Mood: "+getMood()+", genre: "+getGenre();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + genre;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserInput other = (UserInput) obj;
        if (genre != other.genre)
            return false;
        return true;
    }
}
