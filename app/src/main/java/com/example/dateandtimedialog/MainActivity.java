package com.example.dateandtimedialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.crypto.AEADBadTagException;


public class MainActivity extends AppCompatActivity {
    private TextView resultTextView;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private DatePicker.OnDateChangedListener mDateChangedListener;
    SimpleDateFormat simpleDateFormat =new SimpleDateFormat("EE, MMMM dd, yyyy",Locale.US);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTextView = findViewById(R.id.result);
    }



    public void myClick(View view){
        switch (view.getId()){
            case R.id.date:
                pickTime();
                break;
            case R.id.option:
                showOptionDialog(view);
                break;
            case R.id.checkbox:
                showCheckBoxDialog(view);
                break;
        }
    }

    private void pickTime() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int day_week = cal.get(Calendar.DAY_OF_WEEK);

        Date date = new Date();
        //SimpleDateFormat simpleDateFormat =new SimpleDateFormat("EE, MMMM dd, yyyy",Locale.US);
        String show_date = simpleDateFormat.format(date);
        DatePickerDialog dialog = new DatePickerDialog(this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListener,
                year, month, day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setTitle(show_date);

        dialog.getDatePicker().init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Log.i("TAG", "ischanged");
                Date date_changed = new Date(year - 1900, monthOfYear, dayOfMonth);
                Log.i("TAG", "" + date_changed);
                String show_date = simpleDateFormat.format(date_changed);
                dialog.setTitle(show_date);
            }
        });
        dialog.show();


        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Toast.makeText(MainActivity.this, year + "年" + month + "月" + dayOfMonth + "日", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }

        };


//        mDateChangedListener = new DatePicker.OnDateChangedListener() {
//            @Override
//            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                Log.i("TAG", "ischanged");
//                Date date_changed = new Date(year - 1900, monthOfYear, dayOfMonth);
//                Log.i("TAG", "" + date_changed);
//                String show_date = simpleDateFormat.format(date_changed);
//                dialog.setTitle(show_date);
//            }
//
//        };
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
                Toast.makeText(getApplicationContext(),"你选择了："+items[position],Toast.LENGTH_SHORT).show();
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