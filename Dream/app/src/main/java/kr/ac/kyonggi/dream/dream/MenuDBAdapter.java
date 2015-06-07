package kr.ac.kyonggi.dream.dream;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by sookyunt on 2015-06-08.
 * 이 클래스는 만들기 싫었는데...
 */
public class MenuDBAdapter extends CursorAdapter {

public MenuDBAdapter(Context context, Cursor c) {
        super(context, c);
        }

@Override
public void bindView(View view, Context context, Cursor cursor) {
        TextView tvName = (TextView) view.findViewById(R.id.tv_name);
        TextView tvPhone = (TextView) view.findViewById(R.id.tv_phone);

        String name = cursor.getString(cursor.getColumnIndex(DBUpdate.MenuDB.NAME));
        String phone = cursor.getString(cursor.getColumnIndex(DBUpdate.MenuDB.PRICE));

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
