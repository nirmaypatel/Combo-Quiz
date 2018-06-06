package com.image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
public class aptitude extends Activity {
	TextView tv;
	TextView tv2;
	Button b1;
	Button b2;
	Button b3;
	Button b4;
	ArrayList<Question_Bean> al = new ArrayList<Question_Bean>();
	ArrayList<String> options = new ArrayList<String>();
	int i;
	Random r;
	ArrayList<String> right = new ArrayList<String>();
	ArrayList<Integer> random_values = new ArrayList<Integer>();
	boolean check = false;
	int count=0;
	int which_level = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aptitude);

		tv2 =(TextView)findViewById(R.id.textView2);

		// level = getIntent().getExtras().getInt("key");
		right = MainActivity.db1.get_ans(which_level);
		Random r = new Random();
		i = (r.nextInt(40));
		random_values.add(i);
		al = MainActivity.db1.get_names(which_level);

		options.clear();
		options.add(al.get(i).option_a);
		options.add(al.get(i).option_b);
		options.add(al.get(i).option_c);
		options.add(al.get(i).option_d);
		
		// shuffleArray(options);
		Collections.shuffle(options);

		tv = (TextView) findViewById(R.id.textView1);
		tv.setText(al.get(i).questions);
		b1 = (Button) findViewById(R.id.button1);
		b1.setText(options.get(0));
		b2 = (Button) findViewById(R.id.button2);
		b2.setText(options.get(1));
		b3 = (Button) findViewById(R.id.button3);
		b3.setText(options.get(2));
		b4 = (Button) findViewById(R.id.button4);
		b4.setText(options.get(3));
		which_level = getIntent().getExtras().getInt("key");
		if(which_level == 1)
		{
			level.progress_level = 1;
		}
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
	
		
		//Toast.makeText(this, i+"check random", Toast.LENGTH_LONG).show();
	}
	
	public void checka(View v) {

		if (b1.getText().toString().equalsIgnoreCase(right.get(i))) {
			Toast.makeText(aptitude.this, "your answer is correct",250).show();
			level.score=level.score + 20;
			level.right_question++;
			nextapti(true);
		} else {
			Toast.makeText(aptitude.this, "your answer is wrong",250).show();
			level.score=level.score-10;
			nextapti(false);
		}

	}

	public void checkb(View v) {

		if (b2.getText().toString().equalsIgnoreCase(right.get(i))) {
			Toast.makeText(aptitude.this, "your answer is correct",250).show();
			level.score=level.score+20;
			level.right_question++;
			nextapti(true);
		} else {
			Toast.makeText(aptitude.this, "your answer is wrong",250).show();
			level.score=level.score-10;
			nextapti(false);
		}

	}

	public void checkc(View v) {

		if (b3.getText().toString().equalsIgnoreCase(right.get(i))) {
			Toast.makeText(aptitude.this, "your answer is correct",250).show();
			level.score=level.score+20;
			level.right_question++;
			nextapti(true);
		} else {
			Toast.makeText(aptitude.this, "your answer is wrong",250).show();
			level.score=level.score-10;
			nextapti(false);
		}

	}

	public void checkd(View v) {

		if (b4.getText().toString().equalsIgnoreCase(right.get(i))) {
			Toast.makeText(aptitude.this, "your answer is correct",250).show();
			level.score=level.score+20;
			level.right_question++;
			nextapti(true);
		} else {
			Toast.makeText(aptitude.this, "your answer is wrong",250).show();
			level.score=level.score-10;
			nextapti(false);
		}

	}

	public void nextapti(boolean ans) {
	
		
		if(ans == true){
			
			MainActivity.db1.Update_name("yes",al.get(i).id);
		}
		else
		{
			MainActivity.db1.Update_name("no",al.get(i).id);
		}
		
		check = false;
		Random r = new Random();
		i=(r.nextInt(39));
		if(random_values.size() > 0)
		{
			//Check_Random();
			while (check == false) {
				Check_Random();
			}
		}
		random_values.add(i);
		options.clear();
		options.add(al.get(i).option_a);
		options.add(al.get(i).option_b);
		options.add(al.get(i).option_c);
		options.add(al.get(i).option_d);
		Collections.shuffle(options);
		tv = (TextView) findViewById(R.id.textView1);
		tv.setText(al.get(i).questions);
		b1 = (Button) findViewById(R.id.button1);
		b1.setText(options.get(0));
		b2 = (Button) findViewById(R.id.button2);
		b2.setText(options.get(1));
		b3 = (Button) findViewById(R.id.button3);
		b3.setText(options.get(2));
		b4 = (Button) findViewById(R.id.button4);
		b4.setText(options.get(3));
		count++;
		tv2.setText("Score : "+level.score+"/400");
		if(count>=20)
		{
			Toast.makeText(aptitude.this, "20 question is completed",250).show();
			end();
		}

	}

	private void end() {
		// TODO Auto-generated method stub
		//finish();
		final Dialog dialog = new Dialog(aptitude.this);
		dialog.setContentView(R.layout.score_progress);
		//dialog.setTitle("    score   ");
		dialog.setTitle("score");
		dialog.setCancelable(false);

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
		
		//Intent i = new Intent(aptitude.this,score.class);
        //startActivity(i);
	}
	
}
