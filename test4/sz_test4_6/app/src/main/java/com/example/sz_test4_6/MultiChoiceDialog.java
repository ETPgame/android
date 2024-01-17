package com.example.sz_test4_6;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;

public class MultiChoiceDialog {
    private Context context;
    private String title;
    private String[] hobbies;
    private boolean[] checkedTable;

    public MultiChoiceDialog(Context context, String title, String[] hobbies) {
        this.context = context;
        this.title = title;
        this.hobbies = hobbies;
        checkedTable = new boolean[hobbies.length];
    }

    public interface OnDialogSubmitListener{
        void onSubmit(List<String> selectedItems);
    }


    public void showDialog(OnDialogSubmitListener l){
        AlertDialog.Builder bl=new AlertDialog.Builder(context);
        bl.setTitle(title);
        boolean[] temp=checkedTable.clone();


        bl.setMultiChoiceItems(hobbies, temp, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                temp[which]=isChecked;
            }
        });

        bl.setNegativeButton("Cancel",null);
        bl.setPositiveButton("OKEY", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                checkedTable=temp;
                List<String> selectedItems=getSelectedItems();
                if (l!=null){
                    l.onSubmit(selectedItems);
                }
            }
        });

        bl.show();


    }

    public List<String> getSelectedItems() {
        ArrayList<String> list=new ArrayList<>();
        for (int i = 0; i < checkedTable.length; i++) {
            if (checkedTable[i]){
                list.add(hobbies[i]);
            }
        }
        return list;

    }

}
