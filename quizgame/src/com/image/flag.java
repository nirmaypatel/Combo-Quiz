package com.image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.xml.datatype.Duration;

import android.R.mipmap;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
public class flag extends Activity {
	TextView tv;
	TextView tv1;
	Button b1;
	Button b2;
	Button b3;
	Button b4;
	ArrayList<Question_Bean> al = new ArrayList<Question_Bean>();
	ArrayList<String> fl = new ArrayList<String>();
	ImageView i1;
	String i2;
	int i;
	Random r;
	ProgressBar p;
	ArrayList<String> right = new ArrayList<String>();
	ArrayList<Integer> random_values = new ArrayList<Integer>();
	boolean check = false;
	TextView tvHeader;
	int which_level = 2;
	int count=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_file);
		
		tvHeader = (TextView)findViewById(R.id.tvHeader);
		i1 = (ImageView) findViewById(R.id.imageView1);
		
		tv1 = (TextView)findViewById(R.id.textView1);
		
		which_level = getIntent().getExtras().getInt("key");
		
		if(which_level == 1)
		{
			level.progress_level = 1;
		}
		else if(which_level == 2)
		{
			tvHeader.setText("Identify Country from the below Flag?");
		    level.progress_level = 2;
		}

		else if(which_level == 3)
		{
			tvHeader.setText("Identify the Logo from below image?");
			level.progress_level = 3;
		}
		else if(which_level == 4)
		{
			tvHeader.setText("Identify the Celebrity from below image?");
			level.progress_level = 4;
		}
		Random r = new Random();
		i = (r.nextInt(40));
		//i=(r.nextInt(9)+(80));
		random_values.add(i);
		right = MainActivity.db1.get_ans(which_level);
		al = MainActivity.db1.get_names(which_level);
		
		fl.clear();
		fl.add(al.get(i).option_a);
		fl.add(al.get(i).option_b);
		fl.add(al.get(i).option_c);
		fl.add(al.get(i).option_d);
		
		Collections.shuffle(fl);
		i2 = al.get(i).questions;
		i1.setImageResource(this.getResources().getIdentifier(i2, "drawable",getPackageName()));
		b1 = (Button) findViewById(R.id.button1);
		b1.setText(fl.get(0));
		b2 = (Button) findViewById(R.id.button2);
		b2.setText(fl.get(1));
		b3 = (Button) findViewById(R.id.button3);
		b3.setText(fl.get(2));
		b4 = (Button) findViewById(R.id.button4);
		b4.setText(fl.get(3));
	}
	public void Check_Random()
	{
		check = true;
		for(int j = 0 ; j < random_values.size() ; j++)
		{
			if(i == random_values.get(j))
			{
				check = false;
				Random r = new Random();
				i = (r.nextInt(40));
				break;
			}
		}
	}

	public void ca(View v) {
		if (b1.getText().toString().equalsIgnoreCase(right.get(i))) {
			Toast.makeText(flag.this, "your answer is correct",250).show();
			level.score=level.score+20;
			level.right_question++;
			next(true);
		} else {
			Toast.makeText(flag.this, "your answer is wrong",250).show();
			level.score=level.score-10;
			next(false);
		}
	}

	public void cb(View v) {
		if (b2.getText().toString().equalsIgnoreCase(right.get(i))) {
			Toast.makeText(flag.this, "your answer is correct",250).show();
			level.score=level.score+20;
			level.right_question++;
			next(true);
		} else {
			Toast.makeText(flag.this, "your answer is wrong",250).show();
			level.score=level.score-10;
			next(false);
		}
	}

	public void cc(View v) {

		if (b3.getText().toString().equalsIgnoreCase(right.get(i))) {
			Toast.makeText(flag.this, "your answer is correct",250).show();
			level.score=level.score+20;
			level.right_question++;
			next(true);
		} else {
			Toast.makeText(flag.this, "your answer is wrong",250).show();;
			level.score=level.score-10;
			next(false);
		}

	}

	public void cd(View v) {

			if (b4.getText().toString().equalsIgnoreCase(right.get(i))) {
			Toast.makeText(flag.this, "your answer is correct",250).show();
			level.score=level.score+20;
			level.right_question++;
			next(true);
			} else {
			Toast.makeText(flag.this, "your answer is wrong",250).show();
			level.score=level.score-10;
			next(false);
			}
		}
	public void next(boolean ans)
	{
		
		if(ans == true){
			
			MainActivity.db1.Update_name("yes", al.get(i).id);
		}
		else
		{
			MainActivity.db1.Update_name("no", al.get(i).id);
		}
		
		check = false;
		Random r = new Random();
		i=(r.nextInt(40));
			if(random_values.size() > 0)
			{
				while (check == false) 
					{
					Check_Random();
					}
			}
		random_values.add(i);
		fl.clear();
		fl.add(al.get(i).option_a);
		fl.add(al.get(i).option_b);
		fl.add(al.get(i).option_c);
		fl.add(al.get(i).option_d);
		Collections.shuffle(fl);
		i2 = al.get(i).questions;
		i1.setImageResource(this.getResources().getIdentifier(i2, "drawable",getPackageName()));
		b1 = (Button) findViewById(R.id.button1);
		b1.setText(fl.get(0));
		b2 = (Button) findViewById(R.id.button2);
		b2.setText(fl.get(1));
		b3 = (Button) findViewById(R.id.button3);
		b3.setText(fl.get(2));
		b4 = (Button) findViewById(R.id.button4);
		b4.setText(fl.get(3));	
		
		tv1.setText("Score :" +level.score+ " / 400");
		count++;
		if(count>=20)
		{
			Toast.makeText(flag.this, "20 question is completed",250).show();
			end();
		}
		
	
	}

	private void end() {
		// TODO Auto-generated method stub
			final Dialog dialog = new Dialog(flag.this);
			dialog.setContentView(R.layout.score_progress);
			dialog.setCancelable(false);
			//dialog.setTitle("    score   ");
			dialog.setTitle("score");
			// set the custom dialog components - text, image and button
			TextView text = (TextView) dialog.findViewById(R.id.textView1);
			text.setText("Your score is: "+level.score+"/400");
			ImageView image = (ImageView) dialog.findViewById(R.id.imageView1);
			image.setImageResource(R.drawable.temp_logo);
			Button dialogButton = (Button) dialog.findViewById(R.id.button1);
			// if button is clicked, close the custom dialog
		
			dialogButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					dialog.dismiss();
					finish();
					
				}
			});

			dialog.show();
		
			//finish();
		//Intent i = new Intent(flag.this,score.class);
        //startActivity(i);
	}
}