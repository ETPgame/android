package com.example.sz_test6_4;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class PhoneDialog {
    private Context context;
    private String title;

    public interface OnSubmitLister{
        void onSubmit(String updatedName,String updatedPhone);
    }
    public PhoneDialog(Context context,String title){
        this.title=title;
        this.context=context;
    }

    public void showDialog(String name,String phone,OnSubmitLister l){
        View v= LayoutInflater.from(context).inflate(R.layout.dialog_view,null,false);
        EditText et_name=v.findViewById(R.id.dialog_et_name);
        EditText et_phone=v.findViewById(R.id.dialog_et_phone);
        et_name.setText(name);
        et_phone.setText(phone);

        AlertDialog.Builder bl=new AlertDialog.Builder(context);
        bl.setTitle(title);
        bl.setView(v);
        bl.setNegativeButton("取消",null);
        bl.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (l != null) {
                    l.onSubmit(et_name.getText().toString(),
                            et_phone.getText().toString());
                }
            }
        });
        bl.show();

    }
}
