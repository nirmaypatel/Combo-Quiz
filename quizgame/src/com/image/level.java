package com.image;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
public class level extends Activity {
	ProgressBar p;
	public static int score=0;
	public static int right_question=0;
	public static int progress_level=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dbl);	
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(progress_level == 1)
			{
			TextView tv1=(TextView)findViewById(R.id.textView4);
			tv1.setText(+right_question+"/20");
			p=(ProgressBar)findViewById(R.id.progressBar1);
			//p.setProgress((100*right_question)/20);
			String MyColor1 = "#139cab";
			final float[] roundedCorners1 = new float[] { 5, 5, 5, 5, 5, 5, 5, 5 };
			ShapeDrawable pgDrawable1 = new ShapeDrawable(new RoundRectShape(roundedCorners1, null,null));
			pgDrawable1.getPaint().setColor(Color.parseColor(MyColor1));
			ClipDrawable progress1 = new ClipDrawable(pgDrawable1, Gravity.LEFT, ClipDrawable.HORIZONTAL);
			p.setProgressDrawable(progress1);   
			p.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
			//int percentage1 = (100 * total_correct[0])/20;
			p.setProgress((100*right_question)/20);
			score=0;
			right_question=0;
			}
		else if(progress_level == 2)
				{
				TextView tv1=(TextView)findViewById(R.id.textView8);
				tv1.setText(+right_question+"/20");
				p=(ProgressBar)findViewById(R.id.progressBar2);
				String MyColor1 = "#139cab";
				final float[] roundedCorners1 = new float[] { 5, 5, 5, 5, 5, 5, 5, 5 };
				ShapeDrawable pgDrawable1 = new ShapeDrawable(new RoundRectShape(roundedCorners1, null,null));
				pgDrawable1.getPaint().setColor(Color.parseColor(MyColor1));
				ClipDrawable progress1 = new ClipDrawable(pgDrawable1, Gravity.LEFT, ClipDrawable.HORIZONTAL);
				p.setProgressDrawable(progress1);   
				p.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));				
				p.setProgress((100*right_question)/20);
				score=0;
				right_question=0;
				}
			else if(progress_level == 3)
				{
				TextView tv1=(TextView)findViewById(R.id.textView12);
				tv1.setText(+right_question+"/20");
				p=(ProgressBar)findViewById(R.id.progressBar3);
				String MyColor1 = "#139cab";
				final float[] roundedCorners1 = new float[] { 5, 5, 5, 5, 5, 5, 5, 5 };
				ShapeDrawable pgDrawable1 = new ShapeDrawable(new RoundRectShape(roundedCorners1, null,null));
				pgDrawable1.getPaint().setColor(Color.parseColor(MyColor1));
				ClipDrawable progress1 = new ClipDrawable(pgDrawable1, Gravity.LEFT, ClipDrawable.HORIZONTAL);
				p.setProgressDrawable(progress1);   
				p.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));				
				p.setProgress((100*right_question)/20);
				score=0;
				right_question=0;
				}
			else if(progress_level == 4)
				{
				TextView tv1=(TextView)findViewById(R.id.textView16);
				tv1.setText(+right_question+"/20");
				p=(ProgressBar)findViewById(R.id.progressBar4);
				String MyColor1 = "#139cab";
				final float[] roundedCorners1 = new float[] { 5, 5, 5, 5, 5, 5, 5, 5 };
				ShapeDrawable pgDrawable1 = new ShapeDrawable(new RoundRectShape(roundedCorners1, null,null));
				pgDrawable1.getPaint().setColor(Color.parseColor(MyColor1));
				ClipDrawable progress1 = new ClipDrawable(pgDrawable1, Gravity.LEFT, ClipDrawable.HORIZONTAL);
				p.setProgressDrawable(progress1);   
				p.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));				
				p.setProgress((100*right_question)/20);
				score=0;
				right_question=0;
				}
		}
	public void apti(View v)
	{
		Intent i = new Intent(level.this ,aptitude.class);
		i.putExtra("key", 1);
        startActivity(i);	
	}
	public void Flag_Quiz(View v)
	{
		Intent i = new Intent(level.this, flag.class);
		i.putExtra("key", 2);
        startActivity(i);
	}
	public void Celebrity_Quiz(View v)
	{
		Intent i = new Intent(level.this, flag.class);
		i.putExtra("key", 3);
        startActivity(i);
	}
	public void Logo_Quiz(View v)
	{
		Intent i = new Intent(level.this, flag.class);
		i.putExtra("key", 4);
        startActivity(i);
	}
}
