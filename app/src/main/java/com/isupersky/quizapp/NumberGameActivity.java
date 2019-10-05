package com.isupersky.quizapp;

import androidx.appcompat.app.AppCompatActivity;

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

public class NumberGameActivity extends AppCompatActivity {

    private ArrayList<String> numArrayList;
    private HashMap<String,String> numHashMap;
    private ArrayAdapter <String>adapter;
    private int points = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_game);
        numArrayList = new ArrayList<>();
        numHashMap = new HashMap<>();
        readFile();
//        setWordTextView();
//        inflateOptionListView();
//        Toast.makeText(this,count,Toast.LENGTH_SHORT).show();
        setQuestion();
        final ListView listView = findViewById(R.id.optionlistview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tempTextView = findViewById(R.id.numtextview);
                TextView tempTextView2 = findViewById(R.id.pointTextView);
                String tempString =(String)listView.getItemAtPosition(position);

                if(numHashMap.get(tempTextView.getText().toString()).equals(tempString)){
                    points++;
                    tempTextView2.setText("points : "+ points);
                    setQuestion();
//                  Toast.makeText(this,"Awesome",Toast.LENGTH_SHORT).show();
                }
                else
                    points--;
                tempTextView2.setText("points : "+ points);
                setQuestion();
            }

        });
    }


    private void readFile(){
        String tempArray[] ;
//        String tempString;

        try {
            Scanner sc = new Scanner(getResources().openRawResource(R.raw.dectobin));
            while (sc.hasNext()){

//                numArrayList.add(sc.nextLine());
//                Toast.makeText(this,"Word Added",Toast.LENGTH_SHORT).show();
                tempArray = sc.nextLine().split("\t");
                numHashMap.put(tempArray[0],tempArray[1]);
                numArrayList.add(tempArray[0]);
            }
            sc.close();
        } catch (Exception e) {
            Toast.makeText(this,"Error Reading File",Toast.LENGTH_SHORT).show();
        }


    }
    private void setWordTextView(int i){
        TextView tempTextView = findViewById(R.id.numtextview);

        tempTextView.setText(numArrayList.get(i));
//        Toast.makeText(this,"word is "+numArrayList.get(0)+" meaning is "+ numHashMap.get(numArrayList.get(0)),Toast.LENGTH_SHORT).show();
    }

    private void inflateOptionListView(ArrayList arrayList){
        ArrayList tempArrayList = new ArrayList();
        tempArrayList.add(numHashMap.get(numArrayList.get((int)arrayList.get(0))));
        tempArrayList.add(numHashMap.get(numArrayList.get((int)arrayList.get(1))));
        tempArrayList.add(numHashMap.get(numArrayList.get((int)arrayList.get(2))));
        tempArrayList.add(numHashMap.get(numArrayList.get((int)arrayList.get(3))));
//        Toast.makeText(this,""+numHashMap.get(numArrayList.get((int)arrayList.get(0))),Toast.LENGTH_SHORT).show();

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
}
