package com.example.sz_test3_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        TextView tv=findViewById(R.id.tv);
        Spinner sp=findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,
                R.array.my_dimens_names, android.R.layout.simple_list_item_1);

        Resources res = getResources();
        TypedArray dimensValues=res.obtainTypedArray(R.array.my_dimens_values);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                float dimension=dimensValues.getDimension(position,10.0f);
                tv.setTextSize(dimension);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}