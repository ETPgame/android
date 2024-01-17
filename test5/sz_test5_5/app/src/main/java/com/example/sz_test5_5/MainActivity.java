package com.example.sz_test5_5;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    String baseUrl="http://guolin.tech/api/china/17";
    ListView lv;
    ArrayAdapter<City> adapter;
    ActivityResultLauncher<Intent> launcher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        lv=findViewById(R.id.listView);
        WebApiUtils.getApiDataAsync(this, baseUrl,
                new WebApiUtils.OnReadFinishedListener() {
                    @Override
                    public void onFinished(List<City> readOutList) {
                        showList(readOutList);
                    }

                    @Override
                    public void onFail(String e) {
                        showToast(e);
                    }
                });
        iniActivityLauncher();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                City item=adapter.getItem(position);
                String tempUrl=String.format("%s/%d",baseUrl,item.id);
                Bundle bundle=new Bundle();
                bundle.putString(CommonValues.KEY_URL,tempUrl);
                Intent intent=new Intent(getApplicationContext(),
                        MainActivity2.class);
                intent.putExtras(bundle);
                launcher.launch(intent);
            }
        });
    }

    private void iniActivityLauncher() {
        launcher=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Intent data=result.getData();
                        int resultCode= result.getResultCode();
                        if (resultCode==RESULT_OK){
                            Bundle b=data.getExtras();
                            City city=(City) b.getSerializable(
                                    CommonValues.KEY_CITY
                            );
                            showSnackBar(city);
                        }

                    }
                });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("onNewIntent",this.getLocalClassName());
    }

    private void showSnackBar(City city) {
        Snackbar.make(lv,"City:"+city.name,Snackbar.LENGTH_LONG)
                .setAction("WeatherId", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showToast("Weather_id:"+city.weatherid);
                    }
                }).show();
    }

    private void showToast(String e) {
        Toast.makeText(this,e,Toast.LENGTH_LONG).show();
    }

    private void showList(List<City> readOutList) {
        adapter=new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,readOutList);
        lv.setAdapter(adapter);
    }

}