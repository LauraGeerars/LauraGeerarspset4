package com.example.laurageerars.laurageerarspset4;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.TextView;


public class TodoAdapter extends CursorAdapter {
    public TodoAdapter(Context context, Cursor cursor) {
        super(context, cursor, R.layout.row_layout);

    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewgroup) {
        return LayoutInflater.from(context).inflate(R.layout.row_layout, viewgroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView listitem = view.findViewById(R.id.ListItem);
        CheckBox checkbox = view.findViewById(R.id.Checkbox);
        listitem.setText(cursor.getString(cursor.getColumnIndex("title")));
        if (cursor.getInt(cursor.getColumnIndex("completed")) == 1) {
            checkbox.setChecked(true);
        }
        else {
            checkbox.setChecked(false);
        }
    }
}

