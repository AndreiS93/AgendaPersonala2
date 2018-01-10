package com.example.personalagenda;

import java.util.UUID;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

// baza de date memouri
public class MemoHandlerSQL{
	
	private DatabaseHelper databaseHelper;
	private SQLiteDatabase sqLiteDatabase;
	
	// activitatea
	private final Context context;
	
	// nume baza de date
	public static final String DATABASE_NAME = "memo.db";
	
	public static final int DATABASE_VERSION = 1;
	
	// tabel grup

	public static final String GROUP_TABLE_NAME = "_group";

	public static final String GROUP_TABLE_COLUMN_ID = "_id";

	public static final String GROUP_TABLE_COULMN_TITLE = "_title";

	private static final String GROUP_TABLE_CREATE = "create table " 
			+ GROUP_TABLE_NAME
			+ " ( "
			+ GROUP_TABLE_COLUMN_ID + " text primary key, "
			+ GROUP_TABLE_COULMN_TITLE + " text not null "
			+ " );";
	
	// tabel memo

	public static final String MEMO_TABLE_NAME = "_task";

	public static final String MEMO_TABLE_COLUMN_ID = "_id";

	public static final String MEMO_TABLE_COLUMN_TITLE = "_title";

	public static final String MEMO_TABLE_COLUMN_DATA = "_due_date";

	public static final String MEMO_TABLE_COLUMN_NOTE = "_note";

	public static final String MEMO_TABLE_COLUMN_PRIORITY = "_priority";

	public static final String MEMO_TABLE_COLUMN_GROUP = "_group";

	public static final String MEMO_TABLE_COLUMN_COMPLETION_STATUS = "_completion_status";

	public static final String MEMO_TABLE_CREATE
			= "create table " + MEMO_TABLE_NAME
			+ " ( "
			+ MEMO_TABLE_COLUMN_ID + " text primary key, "
			+ MEMO_TABLE_COLUMN_TITLE + " text not null, "
			+ MEMO_TABLE_COLUMN_DATA + " integer not null, "
			+ MEMO_TABLE_COLUMN_NOTE + " text,"
			+ MEMO_TABLE_COLUMN_PRIORITY + " integer not null, "
			+ MEMO_TABLE_COLUMN_GROUP + " text not null, "
			+ MEMO_TABLE_COLUMN_COMPLETION_STATUS + " integer not null, "
			+ "foreign key ( " + MEMO_TABLE_COLUMN_GROUP + " ) references " + GROUP_TABLE_NAME + " ( " + GROUP_TABLE_COLUMN_ID + " ) "
			+ " );";

	private static class DatabaseHelper extends SQLiteOpenHelper{
		
		public DatabaseHelper(Context context, String name, CursorFactory factory, int version){
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			//creare tabele
			db.execSQL(MemoHandlerSQL.GROUP_TABLE_CREATE);

			db.execSQL(MemoHandlerSQL.MEMO_TABLE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			
			db.execSQL("Drop table if exists " + MemoHandlerSQL.MEMO_TABLE_NAME);
			db.execSQL("Drop table if exists " + MemoHandlerSQL.GROUP_TABLE_NAME);
			
			onCreate(db);
		}
		
	}
	
	
	// functii pentru prelucrare
	
	// Constructor
	public MemoHandlerSQL(Context context){
		this.context = context;
	}
	
	//deschide db
	public MemoHandlerSQL open(){
		databaseHelper = new DatabaseHelper(context, MemoHandlerSQL.DATABASE_NAME, null, MemoHandlerSQL.DATABASE_VERSION);
		sqLiteDatabase = databaseHelper.getWritableDatabase();
		return this;
	}
	
	// inchide db
	public void close(){
		databaseHelper.close();
	}
	
	public Cursor getGrupuri(){
		return sqLiteDatabase.query(GROUP_TABLE_NAME, new String[] {GROUP_TABLE_COLUMN_ID, GROUP_TABLE_COULMN_TITLE}, null, null, null, null, null);
	}
	
	public Cursor getGrupDupaID(String groupId){
		return sqLiteDatabase.query(GROUP_TABLE_NAME, new String[] {GROUP_TABLE_COLUMN_ID, GROUP_TABLE_COULMN_TITLE},
				GROUP_TABLE_COLUMN_ID + " = '" + groupId + "'", null, null, null, null);
	}
	
	public long insertGrup(String groupID, String groupTitle){
		ContentValues cv = new ContentValues();
		cv.put(GROUP_TABLE_COLUMN_ID, groupID);
		cv.put(GROUP_TABLE_COULMN_TITLE, groupTitle);
		return sqLiteDatabase.insert(GROUP_TABLE_NAME, null, cv);
	}
	

	public void insertMemo(MemoMemo memo){
		ContentValues cv = new ContentValues();
		cv.put(MEMO_TABLE_COLUMN_ID, memo.getId());
		cv.put(MEMO_TABLE_COLUMN_TITLE, memo.getTitlu());
		cv.put(MEMO_TABLE_COLUMN_DATA, memo.getData().getTimeInMillis());
		cv.put(MEMO_TABLE_COLUMN_NOTE, memo.getNote());
		cv.put(MEMO_TABLE_COLUMN_PRIORITY, memo.getNivelPrioritate());
		cv.put(MEMO_TABLE_COLUMN_GROUP, memo.getGroup().getId());
		cv.put(MEMO_TABLE_COLUMN_COMPLETION_STATUS, memo.setNivelPrioritate());
		sqLiteDatabase.insert(MEMO_TABLE_NAME, null, cv);
	}
	
	public Cursor getMemo(){
		return sqLiteDatabase.query(MEMO_TABLE_NAME,
				new String[] {MEMO_TABLE_COLUMN_ID, MEMO_TABLE_COLUMN_TITLE, MEMO_TABLE_COLUMN_DATA,MEMO_TABLE_COLUMN_NOTE, MEMO_TABLE_COLUMN_PRIORITY, MEMO_TABLE_COLUMN_GROUP, MEMO_TABLE_COLUMN_COMPLETION_STATUS},
				null, null, null, null, null);
	}
	
	public Cursor getMemoDupaID(String memoId){
		return sqLiteDatabase.query(MEMO_TABLE_NAME,
				new String[] {MEMO_TABLE_COLUMN_ID, MEMO_TABLE_COLUMN_TITLE, MEMO_TABLE_COLUMN_DATA, MEMO_TABLE_COLUMN_NOTE, MEMO_TABLE_COLUMN_PRIORITY, MEMO_TABLE_COLUMN_GROUP, MEMO_TABLE_COLUMN_COMPLETION_STATUS},
				MEMO_TABLE_COLUMN_ID + " = '" + memoId + "'", null, null, null, null);
	}

	//updatare memo
	public void editMemoExistent(MemoMemo memo){
		ContentValues cv = new ContentValues();
		cv.put(MEMO_TABLE_COLUMN_TITLE, memo.getTitlu());
		cv.put(MEMO_TABLE_COLUMN_NOTE, memo.getNote());
		cv.put(MEMO_TABLE_COLUMN_DATA, memo.getData().getTimeInMillis());
		cv.put(MEMO_TABLE_COLUMN_PRIORITY, memo.getNivelPrioritate());
		cv.put(MEMO_TABLE_COLUMN_GROUP, memo.getGroup().getId());
		cv.put(MEMO_TABLE_COLUMN_COMPLETION_STATUS, memo.setNivelPrioritate());
		sqLiteDatabase.update(MEMO_TABLE_NAME, cv, MEMO_TABLE_COLUMN_ID + " = '" + memo.getId() + "'", null);

	}
	
	public void deleteMemo(MemoMemo memo){
		deleteMemo(memo.getId());
	}
	
	public void deleteMemo(String memoId){
		sqLiteDatabase.delete(MEMO_TABLE_NAME, MEMO_TABLE_COLUMN_ID + " = '" + memoId + "'", null);
	}
	
	public void deleteGroup(MemoMemo group){
		deleteGroup(group.getId());
	}
	
	public void deleteGroup(String groupId){
		sqLiteDatabase.delete(GROUP_TABLE_NAME, GROUP_TABLE_COLUMN_ID + " = '" + groupId + "'", null);
	}
	
	// generare memoID
	public String getMemoNouID(){
		String uuid = null;
		Cursor cursor = null;
		
		// creeaza id, verifica daca exita
		do {
			uuid = UUID.randomUUID().toString();
			cursor = getMemoDupaID(uuid);
		} while (cursor.getCount() > 0);
		
		return uuid;
	}
	
	// generare grupID
	public String getGrupNouID(){
		String uuid = null;
		Cursor cursor = null;
		
		// creeaza id, verifica daca exista
		do {
			uuid = UUID.randomUUID().toString();
			cursor = getGrupDupaID(uuid);
		} while (cursor.getCount() > 0);
		
		return uuid;
	}
}
