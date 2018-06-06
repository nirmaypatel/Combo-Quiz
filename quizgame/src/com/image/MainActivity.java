package com.image;

import java.io.IOException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
public class MainActivity extends Activity {
	ProgressDialog ProgressBar;
	private int progressBarStatus = 0;
	private Handler progressBarHandler=new Handler();
	private long filesize=0;	
	MyCounter my;
	//int i,j;
	public TextView tv;
	//ProgressBar p;
	public static DataBaseHelper db1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		db1=new DataBaseHelper(MainActivity.this);
		try{
			db1.createDataBase();
		}
		catch(IOException e){
			//e.printStackTrace();
		}	
		
		 tv=(TextView)findViewById(R.id.textView1);
 		 tv.setText("loading:");
         my= new MyCounter(8000,1000);
 		 my.start();
	}	
	
 		 public void pb(View v){
			 ProgressBar = new ProgressDialog(v.getContext());
	         ProgressBar.setCancelable(true);
	         ProgressBar.setMessage("loading quootesss...");
	         ProgressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
	         ProgressBar.setProgress(10);
	         ProgressBar.setMax(100);
	         ProgressBar.show();
	    
	                              //reset progress bar status
	                    progressBarStatus = 0;

	                    //reset filesize
	                    filesize = 0;

	                    new Thread(new Runnable() {
	                        public void run() {
	                            while (progressBarStatus < 100) {

	                                    // process some tasks
	                                progressBarStatus = fileDownloadStatus();

	                                //  sleep 1 second to show the progress
	                                try {
	                                        Thread.sleep(50);
	                                    } 
	                                catch (InterruptedException e) {
	                                        e.printStackTrace();
	                                    }

	                                // Update the progress bar
	                                progressBarHandler.post(new Runnable() {
	   //background ni process pati gaya pachi aa call thay.
	                                	public void run() {
	                                        ProgressBar.setProgress(progressBarStatus);
	                                        //ProgressBar.setMessage("ready .........");
	                                    }
	                                });
	                            }

	                            // when, file is downloaded 100%,
	                            if (progressBarStatus >= 100) {

	                                // sleep 2 seconds, so that you can see the 100% of file download
	                                try {
	                                    Thread.sleep(500);
	                                } catch (InterruptedException e) {
	                                    e.printStackTrace();
	                                }

	                                // close the progress bar dialog
	                                ProgressBar.dismiss();
	                            }
	                        }
	                    }).start();                
	      }
                    
	         public int fileDownloadStatus()
	         {

	             while (filesize <= 1000000) {

	                           filesize++;

	                            if (filesize == 100000) {
	                                 return 10;
	                            } else if (filesize == 200000) {
	                                return 20;
	                            } else if (filesize == 300000) {
	                               return 30;
	                            } else if (filesize == 400000) {
	                               return 40;
	                            } else if (filesize == 500000) {
	                               return 50;
	                           } else if (filesize == 600000) {
	                              return 60; 
	                           }else if (filesize == 700000) {
	                               return 70;
	                               
	                            }else if (filesize == 800000) {
	                                return 80;
	                            }else if (filesize == 900000) {
	                                return 90;   
	                            }
	                 // write your code here

	             								}
	             		return 100;
	         }
	   
		
		

		
		
		
		
		/*p=new ProgressBar(null);
		p=(ProgressBar)findViewById(R.id.progressBar1);
		p.setMax(100);
        p.setClickable(true);
      // p.setProgress(ProgressBar.STYLE_HORIZONTAL);
        p.setProgress(10);
		j=10;
		for(i=1;i<=7;i++)
		{
			p.setProgress(j);
			j=j+10;
		}*/
		
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

	
	public  class MyCounter extends CountDownTimer{
	
		public MyCounter(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
             }
        @Override
        public void onFinish() {
            System.out.println("Timer Completed.");
        //    tv.setText("Timer Completed.");
            finish();
            Intent i = new Intent(MainActivity.this , second.class);
            startActivity(i);
        }
 
        @Override
        public void onTick(long millisUntilFinished) {
        	
      //      tv.setText(("loading"+(millisUntilFinished/1000)+"");
        	tv.setText(millisUntilFinished/1000+"");
           	System.out.println("Timer  : " + (millisUntilFinished/1000));
        }	
		
	}
}
