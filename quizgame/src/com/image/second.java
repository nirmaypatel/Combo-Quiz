package com.image;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
public class second extends Activity{	
	TextView tv;
	ProgressDialog ProgressBar;
	private int progressBarStatus = 0;
	private Handler progressBarHandler=new Handler();
	private long filesize=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sec);
	}
	public void db(View v)
	{
		 Intent i = new Intent(second.this,level.class);
         startActivity(i);	
	}
	
	public void pb(View v)
	{
		Intent i = new Intent(second.this , quotes.class);
		startActivity(i);
		/*
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
                                        Thread.sleep(500);
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
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                // close the progress bar dialog
                                ProgressBar.dismiss();
                            }
                        }
                    }).start();

	*/}   
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

	
	}


