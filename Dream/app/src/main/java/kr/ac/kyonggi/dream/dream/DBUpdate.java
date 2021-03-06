package kr.ac.kyonggi.dream.dream;

import android.provider.BaseColumns;

/**
 * Created by sookyung on 2015-05-29.
 * DataBase Table
 * Restaurant Information List
 */
public class DBUpdate {
    public static final class CreateDB implements BaseColumns {
        public static final String ID = "_id";
        public static final String NAME = "name";
        public static final String PHONE = "phone";
        public static final String CATEGORY = "category";
        public static final String _TABLENAME = "R_list";
        public static final String _CREATE =
                "create table " + _TABLENAME + " ("
                + ID + " text primary key, "
                + NAME + " text not null , "
                + PHONE + " text not null , "
                + CATEGORY + " smallint )";
    }

    public static final class MenuDB implements BaseColumns {
        public static final String ID = "_id";
        public static final String NAME = "name";
        public static final String PRICE = "price";
        public static final String _TABLENAME = "M_list";
        public static final String _CREATE =
                "create table " + _TABLENAME + " ("
                + ID + " integer , "
                + NAME + " text , "
                + PRICE + " integer not null , "
                + "PRIMARY KEY("+ID+","+NAME+"))";
    }
}
