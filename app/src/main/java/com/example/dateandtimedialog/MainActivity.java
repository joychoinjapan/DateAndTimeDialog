package com.example.dateandtimedialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.ArraySet;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import javax.crypto.AEADBadTagException;


public class MainActivity extends AppCompatActivity {
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTextView = findViewById(R.id.result);
    }



    public void myClick(View view){
        switch (view.getId()){
            case R.id.date:
                break;
            case R.id.option:
                showOptionDialog(view);
                break;
            case R.id.checkbox:
                showCheckBoxDialog(view);
                break;
        }
    }


    private void showOptionDialog(View view) {
        final String[] items = {"男","女","性别未知","你猜"};

        View custom_title = LayoutInflater.from(this).inflate(R.layout.option_title_view,null);

        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),R.layout.list_view_item_option,R.id.radioTextView,items);
        ListView listView = new ListView(getApplicationContext());
        listView.setAdapter(adapter);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

        AlertDialog alertDialog=new AlertDialog.Builder(this)
                .setCustomTitle(custom_title)
                .setView(listView).create();

        alertDialog.show();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"!!你选择了："+items[position],Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });
    }



    private void showCheckBoxDialog(View view) {
        final String[] items = {"编程", "LOL", "旅游", "篮球"};
        View custom_title = LayoutInflater.from(this).inflate(R.layout.checkbox_title_view, null);
        ArrayAdapter adapter =new ArrayAdapter(this, android.R.layout.simple_list_item_checked,items);
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setCustomTitle(custom_title);
        Map<Integer,String> resultMap =new HashMap<Integer, String>();
        builder.setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if(isChecked){
                    resultMap.put(which,items[which]);
                }else{
                    resultMap.remove(which);
                }
            }
        });
        builder.setAdapter(adapter,null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String res = "";
                for(Integer key:resultMap.keySet()){
                    res = res+resultMap.get(key)+"\n";
                }

                resultTextView.setText("个人爱好: "+res);

                resultMap.clear();
            }
        });

        builder.show();
    }


}