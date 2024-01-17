package com.example.sz_test4_5;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

public class PhoneDataDialog {
    private Context context;
    private String title;
    public interface OnSubmitListener{
        void onSubmit(PhoneData updatedData);
    }
    public PhoneDataDialog(Context context,String title){
        this.title=title;
        this.context=context;
    }

    public void showDialog(PhoneData data,OnSubmitListener l) {
        PhoneData temp=new PhoneData("","");
        if (data!=null){
            temp.setName(data.getName());
            temp.setPhone(data.getPhone());
        }
        View v= LayoutInflater.from(context)
                .inflate(R.layout.dialog_view,null,false);
        EditText et_name=v.findViewById(R.id.dialog_et_name);
        EditText et_phone=v.findViewById(R.id.dialog_et_phone);
        et_name.setText(temp.getName());
        et_phone.setText(temp.getPhone());
        AlertDialog.Builder bl=new AlertDialog.Builder(context);
        bl.setTitle(title);
        bl.setView(v);
        bl.setNegativeButton("Cancel",null);
        bl.setPositiveButton("OKEY", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PhoneData updatedData=new PhoneData(
                        et_name.getText().toString(),
                        et_phone.getText().toString());
                if (l!=null){
                    l.onSubmit(updatedData);
                }
            }
        });
        bl.show();
    }
}
