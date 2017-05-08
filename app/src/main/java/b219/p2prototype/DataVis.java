package b219.p2prototype;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import processing.core.PApplet;
import static b219.p2prototype.MainActivity.USER_INPUT;



public class DataVis extends PApplet {

    float maxL = 0;
    //float minL = 0;
    float setL = 450;

    int tICount = 0;


    //int genreCount = 22;
    int numberOfGenres=0;
    int[][] input;
    //int moodCount = 5;

    int[] gColours = new int[21];
    String[] strColours = {
            "#58F74D",
            "#74FFE7",
            "#40008B",
            "#C6F024",
            "#FF05F7",
            "#81A1B4",
            "#597E57",
            "#317BD3",
            "#FF9F0D",
            "#7C03FF",
            "#54A6DB",
            "#FF5252",
            "#A192B4",
            "#3D9B89",
            "#D17C7C",
            "#FF6ABA",
            "#E7BBFA",
            "#FF0D76",
            "#60E51E",
            "#FCA34F",
            "#B72121"
    };

    float[] totalInput;
    float[] totalInputS;


    ArrayList<UserInput> userIn = new ArrayList<UserInput>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userIn = getArguments().getParcelableArrayList(USER_INPUT);
        System.out.println("DataVis, onCreate: "+ userIn);
        if(!userIn.isEmpty()){
            listToArray();
        }
    }

    public void settings(){
        size(displayWidth, displayHeight);
    }
    public void setup(){

        for(int i = 0; i<strColours.length;i++)
            gColours[i] = Color.parseColor(strColours[i]);

        System.out.println("Width: " + ((displayWidth/2.0f)-40.0f) + " setL: " + setL);


    }
    public void draw() {
        background(255);


        //Moves everything to the middle
        translate(width/2, height/2);

        //Counting & calculating loops

        // set the calculater to start every 30 frames
        tICount = 1;
        if (tICount >= 0) {
            for (int j = 0; j < input[0].length; j++) {//for each genre
                for (int i = 0; i < input.length; i++) {// for each mood
                    if (i == 1) {
                            totalInput[j] = input[0][j] +2;
                        } else if (i == 2) {
                            totalInput[j] = input[0][j] + input[1][j]+4;
                        }

                        //counts total length/totalInput
                        totalInput[j] = input[0][j] + input[1][j] + input[2][j]+4;
                }

            }
        }
        //Sorts totalInputs
        totalInputS = sort(totalInput);

        //Set maxL
        for (int j = 0; j < input[0].length; j++) {//for each genre
            if (totalInputS[input[0].length-1] == totalInput[j]) {
                maxL = totalInput[j];

            }
        }


        //Drawing and scaling loops
        //Makes the drawing stop when the calculator is running
        if (tICount > 0) {
            for (int j = 0; j < input[0].length; j++) {//for each genre
                //println(input[0].length);
                rotate(2*PI/input[0].length);
                for (int i = 0; i < input.length; i++) {// for each mood
                    stroke(10,10,10, 100);
                    strokeWeight(1);
                    //System.out.println(j+" "+i+" "+input[i][j]);
                    if (i == 0) {
                        fill(255, 0, 0);
                        rect(-5, 50, 10, ((input[i][j]/maxL)*setL)+2);
                    } else {
                        if (i == 1) {
                            totalInput[j] = ((input[0][j]/maxL)*setL+2);
                            fill(Color.parseColor("#FFF700"));
                            rect(-5, 50 + totalInput[j]+2, 10, ((input[i][j]/maxL)*setL)+2);
                        } else if (i == 2) {
                            totalInput[j] = ((input[0][j]/maxL)*setL) + ((input[1][j]/maxL)*setL)+6;
                            fill(0, 255, 0);
                            rect(-5, 50 + totalInput[j]+2, 10, ((input[i][j]/maxL)*setL));

                        }
                    }
                }
                //Draws the arc at the end of each genre "pillar"
                noFill();
                stroke(gColours[j]);



                strokeWeight(7);
                arc(0, 0, (50 + ((input[0][j]/maxL)*setL) + ((input[1][j]/maxL)*setL) + ((input[2][j]/maxL)*setL)+4 +2)*2+7, (50 + ((input[0][j]/maxL)*setL) + ((input[1][j]/maxL)*setL) + ((input[2][j]/maxL)*setL)+4 +2)*2+7, (radians(90)-PI/input[0].length)+radians(2.5f), (radians(90)+PI/input[0].length)-radians(2.5f));

                //text(gNames[j], 210, 210);


                //counts total length/totalInput
                totalInput[j] = input[0][j] + input[1][j] + input[2][j]+4;
            }
        }
    }





    private void listToArray(){
        HashSet<UserInput> userInHSet = new HashSet<UserInput>();

        UserInput hSInput = null;
        for (Iterator<UserInput> i = userIn.iterator(); i.hasNext();) {
            hSInput = i.next();
            userInHSet.add(hSInput);
        }
        numberOfGenres = userInHSet.size();

        input = new int[3][numberOfGenres];
        totalInput = new float[input[0].length];
        totalInputS = new float[input[0].length];

        for(int i = 0; i<userIn.size(); i++) {
            for(int j = 0; j<numberOfGenres; j++) {
                if(userIn.get(i).getMood()==0 && userIn.get(i).getGenre()==j+1) {
                    input[0][j]++;
                }
                else if(userIn.get(i).getMood()==1 && userIn.get(i).getGenre()==j+1) {
                    input[1][j]++;
                }
                else if(userIn.get(i).getMood()==2 && userIn.get(i).getGenre()==j+1){
                    input[2][j]++;
                }
            }
        }
    }



}
