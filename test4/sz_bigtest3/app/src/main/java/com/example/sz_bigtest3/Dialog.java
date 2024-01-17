package com.example.sz_bigtest3;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

public class Dialog {
    private Context context;
    private String title;

    public interface OnDialogSubmitListener{
        void onSubmit(Landscape updatedLandscape);
    }
    public Dialog(Context context, String title){
        this.context = context;
        this.title = title;
    }

    public void showDialog(Landscape landscape, final OnDialogSubmitListener l){
        AlertDialog.Builder bl = new AlertDialog.Builder(context);
        bl.setTitle(title);
        View v = LayoutInflater.from(context).inflate(R.layout.dialog_view,null,false);
        final EditText et = v.findViewById(R.id.editText);
        final ImageView img = v.findViewById(R.id.imageView);
        GridView gv = v.findViewById(R.id.gridView);

        if(landscape == null){
            landscape = new Landscape("",R.mipmap.ic_launcher);
        }
        et.setText(landscape.getName());
        img.setImageResource(landscape.getImg());
        img.setTag(landscape.getImg());
        final ArrayList<Landscape> allData =  com.example.sz_bigtest3.LandscapeDateCenter.getData();
        com.example.sz_bigtest3.LandscapeAdapter adapter = new com.example.sz_bigtest3.LandscapeAdapter(context, allData, 2);
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Landscape index = com.example.sz_bigtest3.LandscapeDateCenter.getData().get(i);
                et.setText(index.getName());
                img.setImageResource(index.getImg());
                img.setTag(index.getImg());
            }
        });
        bl.setView(v);
        bl.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String name = et.getText().toString();
                int picId = (int) img.getTag();
                Landscape updatedLandscape = new Landscape(name, picId);
                if(l != null){
                    l.onSubmit(updatedLandscape);
                }
            }
        });

        bl.setNegativeButton("Cancel",null);
        bl.create().show();
    }
}
