package com.example.basic.myapplication;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {

    private DBHelper helper;
    private SQLiteDatabase db;

    public BlankFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView listView = (ListView) view.findViewById(R.id.list_view);
        MyAdapter adapter = new MyAdapter();

        helper = new DBHelper(getActivity());
        db = helper.getWritableDatabase();

        for (int i = 0; i < 50; i++) {
            ContentValues values = new ContentValues();
            values.put("name", "item" + i);
            db.insert("MyTable", null, values);
        }
        Cursor cursor = db.query("MyTable", null, null, null, null, null, null);
        Log.e("A", "a ");
        if (cursor.moveToFirst()){
            int idIndex=cursor.getColumnIndex("id");
            int nameIndex=cursor.getColumnIndex("name");
            while (cursor.moveToNext()){
                String id = cursor.getColumnName(idIndex);
                String name = cursor.getColumnName(nameIndex);
                int getId = cursor.getInt(idIndex);
                String getName = cursor.getString(nameIndex);
                Log.e("DB",  id + " = " + getId + ", " + name + " = " + getName + "\n");
            }
        }
        db.close();
    }
}
