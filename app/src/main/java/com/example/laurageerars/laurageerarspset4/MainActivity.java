package com.example.laurageerars.laurageerarspset4;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TodoDatabase db;
    private TodoAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = TodoDatabase.getInstance(getApplicationContext());
        Cursor cursor = db.selectAll();
        adapter = new TodoAdapter(this, cursor);
        ListView ListView = findViewById(R.id.ListView);
        ListView.setAdapter(adapter);
        //Adapter(cursor);
        ListView.setOnItemClickListener(new Click());
        ListView.setOnItemLongClickListener(new LongClick());
    }

    /*public void Adapter(Cursor Info) {
        ListView ListView = findViewById(R.id.ListView);
        adapter = new TodoAdapter(this, Info);
        ListView.setAdapter(adapter);
    }*/

    public class Click implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            CheckBox checkbox = view.findViewById(R.id.Checkbox);
            Cursor cursor = db.selectAll();
            cursor.move(i + 1 );
            int ID = cursor.getInt(cursor.getColumnIndex("_id"));

            if (checkbox.isChecked()) {
                db.update(ID, 0);
            }

            else {
                db.update(ID, 1);
            }

            updateData();
        }

    }

    private class LongClick implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long id) {
            //CheckBox checkbox = view.findViewById(R.id.Checkbox);
            Cursor cursor = db.selectAll();
            cursor.move(i + 1 );
            int ID = cursor.getInt(cursor.getColumnIndex("_id"));
            db.delete(ID);
            updateData();
            return true;
        }

    }

    public void addItem(View view) {
        EditText itemInvul = findViewById(R.id.invulText);
        if (itemInvul.getText().toString().equals("")){
            Toast.makeText(this, "Something went wrong: empty input", Toast.LENGTH_SHORT).show();
        }
        else {
            TodoDatabase db = TodoDatabase.getInstance(getApplicationContext());
            db.insert(itemInvul.getText().toString(), 0);
            updateData();
            Toast.makeText(this, "A new To Do Item added!", Toast.LENGTH_SHORT).show();
            itemInvul.setText("");
        }

        updateData();
    }

    private void updateData() {
        db = TodoDatabase.getInstance(getApplicationContext());
        Cursor cursor = db.selectAll();
        adapter.swapCursor(cursor);
        ListView ListView = findViewById(R.id.ListView);
        ListView.setAdapter(adapter);

    }
}
