package com.image;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.viewpagerindicator.CirclePageIndicator;

public class quotes extends Activity {

	// ViewPagerAdapter mAdapter;
	ViewPager mPager;
	FrameLayout fl_pager;
	CirclePageIndicator mIndicator;
	ViewPagerAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quotes);
		mPager = (ViewPager)findViewById(R.id.pager);
		mIndicator = (CirclePageIndicator)findViewById(R.id.indicator);
		mAdapter = new ViewPagerAdapter();
		mPager.setAdapter(mAdapter);
		mIndicator.setViewPager(mPager);
	}

	public class ViewPagerAdapter extends PagerAdapter {

		public int getCount() {
			return 10;
		}

		public LinearLayout instantiateItem(View collection, int position) {
			ImageView iv = new ImageView(quotes.this);
			// iv.setPadding(20, 20, 20, 20);
			LinearLayout layout = new LinearLayout(quotes.this);
			layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT));
			layout.setGravity(Gravity.CENTER);
			iv.setScaleType(ScaleType.FIT_XY);
			if (position % 2 == 0) {
				iv.setBackgroundResource(R.drawable.india);
			} else {
				iv.setBackgroundResource(R.drawable.australia);
			}
			layout.setTag(position);
			layout.addView(iv);

			((ViewPager) collection).addView(layout, 0);
			return layout;
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView((LinearLayout) arg2);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == ((LinearLayout) arg1);
		}

		@Override
		public Parcelable saveState() {
			return null;
		}
	}
}
