package kr.ac.kyonggi.dream.dream;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by sookyung on 2015-05-31.
 */
public class DBAdapter extends CursorAdapter {

    public DBAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvName = (TextView) view.findViewById(R.id.tv_name);
        TextView tvPhone = (TextView) view.findViewById(R.id.tv_phone);

        String name = cursor.getString(cursor.getColumnIndex(DBUpdate.CreateDB.NAME));
        String phone = cursor.getString(cursor.getColumnIndex(DBUpdate.CreateDB.PHONE));

        Log.d("string", name + "," + phone);

        tvName.setText(name);
        tvPhone.setText(phone);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.list_item, parent, false);
        return v;
    }
}
