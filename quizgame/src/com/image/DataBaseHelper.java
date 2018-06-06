package com.image;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

public class DataBaseHelper extends SQLiteOpenHelper{

	 private static String DB_PATH = "/data/data/com.image";
	 private static String DB_NAME = "questions.sqlite";
	 static String Path = Environment.getExternalStorageDirectory().toString();
	 SQLiteDatabase checkDB;
	 public boolean dbExist;
	 int levels_id;
	 private final Context myContext;	    
	 
	 public DataBaseHelper(Context context) {
		super(context, DB_NAME, null, 1);
        this.myContext = context;
        
        //db_writable= new DataBaseHelper(myContext).getWritableDatabase();
	 }

	 @Override
	 public void onCreate(SQLiteDatabase db) 
	 {
	 }
	 @Override
	 public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	 }

	 @Override
	 public synchronized void close() {
		 if(checkDB != null)
			 checkDB.close();
     	 super.close();
	}
 
	public void createDataBase() throws IOException{	
		dbExist =  checkDataBase();
		if(!dbExist)
			copyDataBase();  
    }
 
    public Boolean checkDataBase(){ 
    	checkDB = null;
    	try{
    		String myPath = DB_PATH + "/" + DB_NAME;
    		checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    	}
    	catch(SQLiteException e){ 
    	}
    	return checkDB!=null?true:false;
    }
 
    ///////////////////copy database /////////////////
    private void copyDataBase() throws IOException{
    	
    	OutputStream databaseOutputStream = new 
    	FileOutputStream("/data/data/com.image/questions.sqlite");
    	
    	InputStream databaseInputStream;
    	        
    	byte[] buffer = new byte[1024];
    	   	        
    	databaseInputStream = myContext.getAssets().open("questions.sqlite");
    	 
    	while ( (databaseInputStream.read(buffer)) > 0 ) 
    	{
    		databaseOutputStream.write(buffer);
    	}
    	databaseInputStream.close();
    	        	
    	databaseOutputStream.flush();
    	databaseOutputStream.close();
    }
 
    public void openDataBase() throws SQLException{
    	
        String myPath = DB_PATH + "/" +DB_NAME;
    	checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }
    
    public void CloseDataBase() {
    	checkDB.close();
    }

    public ArrayList<Question_Bean> get_names(int levels_id){
       	openDataBase();
    	ArrayList<Question_Bean> names= new ArrayList<Question_Bean>();
    	
       	Cursor c1 = checkDB.rawQuery("SELECT * FROM quiz_1 where level_id="+ levels_id,null);
    			c1.moveToFirst();
			    	for(int i=0;i<c1.getCount();i++)
			    	    {
			    			Question_Bean ques = new Question_Bean();
			    			ques.id = c1.getInt(0);
			    			ques.questions = c1.getString(2);
			    			ques.option_a = c1.getString(3);
			    			ques.option_b = c1.getString(4);
			    			ques.option_c = c1.getString(5);
			    			ques.option_d = c1.getString(6);
			    			ques.ans_truefalse = c1.getString(7);
			    			names.add(ques);
			    			c1.moveToNext();
			    		}
			    	c1.close();
    	CloseDataBase();
		return names;
    } 
    
 public ArrayList<String> get_ans(int levels_id){
    	
    	openDataBase();
    	ArrayList<String> answer = new ArrayList<String>(); 

    	Cursor c1 = checkDB.rawQuery("SELECT A FROM quiz_1 where level_id="+levels_id,null);
    	
    	c1.moveToFirst();
    	for(int i=0;i<c1.getCount();i++)
    	{
    		answer.add(c1.getString(0));
    		c1.moveToNext();
    	}
    	c1.close();
    	CloseDataBase();
		return answer;
    }
 
 public void Update_name(String name,int question_no) {
		
	  	openDataBase();
	  	ContentValues initialValues = new ContentValues();
		initialValues.put("is_ans",name);
		checkDB.update("quiz_1",initialValues,"id="+question_no,null);
		CloseDataBase();
	}
 
    
   /* public ArrayList<Integer> get_counter_step(){
    	
    	openDataBase();
    	ArrayList<Integer> counter_steps = new ArrayList<Integer>(); 

    	Cursor c1 = checkDB.rawQuery("SELECT ZCSTEP FROM ZCOUNTER",null);
    	
    	c1.moveToFirst();
    	for(int i=0;i<c1.getCount();i++){
    		counter_steps.add(c1.getInt(0));
    		c1.moveToNext();
    	}
    	c1.close();
    	CloseDataBase();
		return counter_steps;
    }

    public ArrayList<Integer> get_counter_values(){
    	
    	openDataBase();
    	ArrayList<Integer> counter_values = new ArrayList<Integer>(); 

    	Cursor c1 = checkDB.rawQuery("SELECT ZCVALUE FROM ZCOUNTER",null);
    	
    	c1.moveToFirst();
    	for(int i=0;i<c1.getCount();i++){
    		counter_values.add(c1.getInt(0));
    		c1.moveToNext();
    	}
    	c1.close();
    	CloseDataBase();
		return counter_values;
    }

  public ArrayList<String> get_counter_names(){
    	
    	openDataBase();
    	ArrayList<String> counter_names = new ArrayList<String>(); 

    	Cursor c1 = checkDB.rawQuery("SELECT ZCNAME FROM ZCOUNTER",null);
    	
    	c1.moveToFirst();
    	for(int i=0;i<c1.getCount();i++){
    		counter_names.add(c1.getString(0));
    		c1.moveToNext();
    	}
    	c1.close();
    	CloseDataBase();
		return counter_names;
  }
  public void Add_name(String name) {
		
	  	openDataBase();
	  	ContentValues initialValues = new ContentValues();
		initialValues.put("name", name);
		checkDB.insert("test_table", null, initialValues);
		CloseDataBase();
	}
public void Delete_name(int id)
	{
		openDataBase();
		checkDB.execSQL("DELETE FROM test_table where id ="+id);
		CloseDataBase();
	}
    public void Add_counter(Integer id , Integer step , Integer value , String name , String date) {
		
	  	openDataBase();
	  	ContentValues initialValues = new ContentValues();
		initialValues.put("Z_ENT", 1);
		initialValues.put("Z_OPT", 1);
		initialValues.put("ZCID", id);
		initialValues.put("ZCSTEP", step);
		initialValues.put("ZCVALUE", value);
		initialValues.put("ZCNAME", name);
		//long a = checkDB.insert("ZCOUNTER", null, initialValues);
		ContentValues initialValues1 = new ContentValues();
		initialValues1.put("Z_ENT", 2);
		initialValues1.put("Z_OPT", 1);
		initialValues1.put("ZCID", id);
		initialValues1.put("ZCVALUE", value);
		initialValues1.put("ZCDATE", date);
		checkDB.insert("ZCOUNTERDATE", null, initialValues1);
		CloseDataBase();
	}
	
	
	public void Save_counter_values(Integer id , Integer value)
	{
		openDataBase();
		ContentValues initialValues = new ContentValues();
	    initialValues.put("ZCID",id);
	    initialValues.put("ZCVALUE",value);
	    checkDB.update("ZCOUNTER",initialValues,"Z_PK="+id,null);
	    CloseDataBase();
	}
	
	public void Save_counter_values_date(Integer id , String date)
	{
		openDataBase();
		ContentValues initialValues = new ContentValues();
	    initialValues.put("ZCID",id);
	    initialValues.put("ZCDATE",date);
	    checkDB.update("ZCOUNTERDATE",initialValues,"ZCID="+id,null);
	    CloseDataBase();
	}
	
	public void Save_counter_steps(Integer id , Integer step)
	{
		openDataBase();
		ContentValues initialValues = new ContentValues();
	    initialValues.put("ZCID",id);
	    initialValues.put("ZCSTEP",step);
	    checkDB.update("ZCOUNTER",initialValues,"Z_PK="+id,null);
	    CloseDataBase();
	}
	
	public void Save_counter_names(Integer id , String name)
	{
		openDataBase();
		ContentValues initialValues = new ContentValues();
	    initialValues.put("ZCID",id);
	    initialValues.put("ZCNAME",name);
	    checkDB.update("ZCOUNTER",initialValues,"Z_PK="+id,null);
	    CloseDataBase();
	}
	
	public boolean Check_for_graph(int id , String date)
	{
		boolean flag = true;
		openDataBase();
		Cursor c1 = checkDB.rawQuery("SELECT ZCVALUE FROM ZCOUNTERDATE where ZCID =" + id + " and ZCDATE = '"+date+"'",null);
		c1.moveToFirst();
    	if(c1.getCount() == 0)
    	{
    		flag = false;
    	}
    	c1.close();
    	CloseDataBase();
		return flag;
	}
	
	public void Insert_Value_For_Graph(int id, int value , String date)
	{
		openDataBase();
		ContentValues initialValues = new ContentValues();
	    initialValues.put("Z_ENT",2);
	    initialValues.put("Z_OPT",1);
	    initialValues.put("ZCID",id);
	    initialValues.put("ZCVALUE",value);
	    initialValues.put("ZCDATE",date);
	    checkDB.insert("ZCOUNTERDATE", null, initialValues);
	    CloseDataBase();
	}
	
	public void Update_Value_For_Graph(int id , int value , String date)
	{
		openDataBase();
		checkDB.execSQL("update ZCOUNTERDATE set ZCVALUE='"+value+"' where ZCID ="+ id + " and ZCDATE = '"+date+"'");
	    CloseDataBase();
	}
	
	public ArrayList<Integer> Graph_Plot_Values(int id)
	{
		openDataBase();
    	ArrayList<Integer> graph_values = new ArrayList<Integer>(); 

    	Cursor c1 = checkDB.rawQuery("SELECT ZCVALUE FROM ZCOUNTERDATE where ZCID = "+ id,null);
    	
    	c1.moveToFirst();
    	for(int i=0;i<c1.getCount();i++){
    		graph_values.add(c1.getInt(0));
    		c1.moveToNext();
    	}
    	c1.close();
    	CloseDataBase();
		return graph_values;

	}
	
	public ArrayList<String> Graph_Plot_Dates(int id)
	{
		openDataBase();
    	ArrayList<String> graph_dates = new ArrayList<String>(); 

    	Cursor c1 = checkDB.rawQuery("SELECT ZCDATE FROM ZCOUNTERDATE where ZCID = "+ id,null);
    	
    	c1.moveToFirst();
    	for(int i=0;i<c1.getCount();i++){
    		graph_dates.add(c1.getString(0));
    		c1.moveToNext();
    	}
    	c1.close();
    	CloseDataBase();
		return graph_dates;

	}
	
	public void Delete_counter(int id)
	{
		openDataBase();
		checkDB.execSQL("DELETE FROM ZCOUNTERDATE where ZCID ="+ id);
		checkDB.execSQL("DELETE FROM ZCOUNTER where ZCID ="+ id);
	    CloseDataBase();
	}

 */
  
 }
