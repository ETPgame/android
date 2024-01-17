package com.example.sz_test4_7;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PhoneDataAdapter extends ArrayAdapter<PhoneData> {
    private Context context;
    private List<PhoneData> list;
    int selectedItemPosition = -1; // Initially, no item is selected


    public PhoneDataAdapter(@NonNull Context context, List<PhoneData> list) {
        super(context, android.R.layout.simple_list_item_1, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v;
        v= LayoutInflater.from(context).inflate(R.layout.row_view,null,false);
        TextView tv_name=v.findViewById(R.id.row_view_name);
        TextView tv_phone=v.findViewById(R.id.row_view_phone);
        CheckBox cb=v.findViewById(R.id.row_view_cb);

        PhoneData phoneData=list.get(position);
        tv_name.setText(phoneData.getName());
        tv_phone.setText(phoneData.getPhone());
        cb.setChecked(phoneData.isChecked());
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                Log.d("CheckBox",""+isChecked);
                phoneData.setChecked(isChecked);
            }
        });
        return v;
    }
    public void setSelectedItem(int position) {
        selectedItemPosition = position;
    }

    public int getSelectedItemPosition() {
        return selectedItemPosition;
    }

    public int getCheckedCount() {
        int count=0;
        for (PhoneData phoneData : list) {
            if (phoneData.isChecked())count++;
        }
        return count;
    }

    public void setAllChecked() {
        for (PhoneData phoneData : list) {
            phoneData.setChecked(true);
        }
        notifyDataSetChanged();
    }

    public void clearAllChecked() {
        for (PhoneData phoneData : list) {
            phoneData.setChecked(false);
        }
        notifyDataSetChanged();
    }

    public void reverseChecked() {
        for (PhoneData phoneData : list) {
            phoneData.setChecked(!phoneData.isChecked());
        }
        notifyDataSetChanged();
    }

    public List<PhoneData> getCheckedList() {
        ArrayList<PhoneData> out=new ArrayList<>();
        for (PhoneData phoneData : list) {
            if (phoneData.isChecked())
                out.add(phoneData);
        }
        return out;
    }
}
