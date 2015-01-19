import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by tchat_000 on 1/15/2015.
 */
public class WordScramblerContract {
    public WordScramblerContract(){}

    public static abstract class WordScrambler implements BaseColumns{
        public static final String TABLE_NAME="entry";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME_WORD="word";
        private static final String TEXT_TYPE = " TEXT";
        private static final String COMMA_SEP = ",";
        private static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + WordScrambler.TABLE_NAME + " (" +
                        WordScrambler._ID + " INTEGER PRIMARY KEY," +
                        WordScrambler.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
                        WordScrambler.COLUMN_NAME_WORD + TEXT_TYPE + COMMA_SEP +

                " )";

        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + WordScrambler.TABLE_NAME;
        Activity thisActivity = new Activity();
        WordScramblerDbHelper mDbHelper = new WordScramblerDbHelper(thisActivity.getApplicationContext());

        public class WordScramblerDbHelper extends SQLiteOpenHelper {
            public static final int DATABASE_VERSION =1;
            public static final String DATABASE_NAME = "WordScrambler.db";

            public WordScramblerDbHelper(Context context) {
                super(context, DATABASE_NAME, null, DATABASE_VERSION);
            }
            public void onCreate(SQLiteDatabase db) {
                db.execSQL(SQL_CREATE_ENTRIES);
            }
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                // This database is only a cache for online data, so its upgrade policy is
                // to simply to discard the data and start over
                db.execSQL(SQL_DELETE_ENTRIES);
                onCreate(db);
            }
            public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                onUpgrade(db, oldVersion, newVersion);
            }


        }
    }


}
