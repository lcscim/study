package com.example.asus.study;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class Spinner_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);
        ArrayAdapter<String> starAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, starArray);
        starAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        Spinner sp = findViewById(R.id.sp_dialog);
        sp.setPrompt("请选择行星");
        sp.setAdapter(starAdapter);
        sp.setSelection(0);
        sp.setOnItemSelectedListener(new MySelectedListener());
    }
    private String[] starArray = {"水星","金星","地球","火星","木星","土星","天王星","海王星"};
    private class MySelectedListener implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            Toast.makeText(Spinner_Activity.this, "您选择的是"+starArray[arg2], Toast.LENGTH_SHORT).show();
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

}
