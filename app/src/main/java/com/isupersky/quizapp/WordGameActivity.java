package com.isupersky.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class WordGameActivity extends AppCompatActivity {

    private ArrayList<String> wordArrayList ;
    private HashMap<String,String> wordHashMap ;
    private ArrayAdapter <String>adapter;
    private int points = 0;
    private int highScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_game);
        wordArrayList = new ArrayList<>();
        wordHashMap = new HashMap<>();

        readFile();
        setQuestion();
        onClickFunc();
        SharedPreferences sf = getPreferences(MODE_PRIVATE);
        highScore = sf.getInt("highScore",0);
        TextView tempTextView2 = findViewById(R.id.pointTextView);
        tempTextView2.setText("High Score : "+highScore+" points : "+ points);



    }


    private void readFile(){
        String tempArray[] ;
//        String tempString;

        try {
            Scanner sc = new Scanner(getResources().openRawResource(R.raw.grewords));
            while (sc.hasNext()){
                tempArray = sc.nextLine().split(" - ");
                wordHashMap.put(tempArray[0],tempArray[1]);
                wordArrayList.add(tempArray[0]);
            }
            sc.close();
        } catch (Exception e) {
            Toast.makeText(this,"Error Reading File",Toast.LENGTH_SHORT).show();
        }


    }
    private void setWordTextView(int i){
        TextView tempTextView = findViewById(R.id.wordtextview);

        tempTextView.setText(wordArrayList.get(i));
    }

    private void inflateOptionListView(ArrayList arrayList){
        ArrayList tempArrayList = new ArrayList();
        tempArrayList.add(wordHashMap.get(wordArrayList.get((int)arrayList.get(0))));
        tempArrayList.add(wordHashMap.get(wordArrayList.get((int)arrayList.get(1))));
        tempArrayList.add(wordHashMap.get(wordArrayList.get((int)arrayList.get(2))));
        tempArrayList.add(wordHashMap.get(wordArrayList.get((int)arrayList.get(3))));
//        Toast.makeText(this,""+wordHashMap.get(wordArrayList.get((int)arrayList.get(0))),Toast.LENGTH_SHORT).show();

        ListView listView = findViewById(R.id.optionlistview);
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,tempArrayList);
        listView.setAdapter(adapter);
    }

    private void setQuestion(){
        Random r = new Random();
        int tempRandom = r.nextInt(675);
        ArrayList<Integer> tempArrayList = new ArrayList();
        for (int i=tempRandom; i<tempRandom+4;i++){
            tempArrayList.add(i);
        }
        setWordTextView(tempArrayList.get(0));
        Collections.shuffle(tempArrayList);
        inflateOptionListView(tempArrayList);

    }

    private void onClickFunc(){
        final ListView listView = findViewById(R.id.optionlistview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tempTextView = findViewById(R.id.wordtextview);
                TextView tempTextView2 = findViewById(R.id.pointTextView);
                String tempString =(String)listView.getItemAtPosition(position);

                if(wordHashMap.get(tempTextView.getText().toString()).equals(tempString)){
                    points++;
                    if(points>highScore){
                        highScore=points;
                        SharedPreferences sf = getPreferences(MODE_PRIVATE);
                        SharedPreferences.Editor editor = sf.edit();
                        editor.putInt("highScore", highScore);
                        editor.apply();
                    }
                    tempTextView2.setText("High Score : "+highScore+" points : "+ points);
                    setQuestion();
//                  Toast.makeText(this,"Awesome",Toast.LENGTH_SHORT).show();
                }
                else
                points--;
                tempTextView2.setText("High Score : "+highScore+" points : "+ points);
                setQuestion();
            }

        });
    }


}
