package com.hide.message;

import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.content.ClipboardManager;
import android.graphics.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.util.*;
import java.text.*;


public class SplashActivity extends Activity {

	private LinearLayout linear1;
	private LinearLayout linear7;
	private LinearLayout linear2;
	private LinearLayout linear8;
	private LinearLayout logo;
	private LinearLayout linear6;
	private LinearLayout linear5;
	private LinearLayout linear4;
	private TextView textview1;



	private Timer _timer = new Timer();
	private TimerTask t;
	private ObjectAnimator ani = new ObjectAnimator();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		initialize();
		initializeLogic();
	}

	private void  initialize() {
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear7 = (LinearLayout) findViewById(R.id.linear7);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		linear8 = (LinearLayout) findViewById(R.id.linear8);
		logo = (LinearLayout) findViewById(R.id.logo);
		linear6 = (LinearLayout) findViewById(R.id.linear6);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		textview1 = (TextView) findViewById(R.id.textview1);





	}

	private void  initializeLogic() {
		t = new TimerTask() {
					@Override
						public void run() {
							runOnUiThread(new Runnable() {
							@Override
								public void run() {
										finish();
								}
							});
						}
					};
					_timer.schedule(t, (int)(1300));
		t = new TimerTask() {
					@Override
						public void run() {
							runOnUiThread(new Runnable() {
							@Override
								public void run() {
										ani.setTarget(logo);
								ani.setPropertyName("rotation");
								ani.setFloatValues((float)(360));
								ani.setDuration((int)(500));
								ani.setInterpolator(new DecelerateInterpolator());
								ani.start();
								}
							});
						}
					};
					_timer.schedule(t, (int)(100));
	}






	// created automatically
	private void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}

	private int getLocationX(View _v) {
		 int _location[] = new int[2];
		 _v.getLocationInWindow(_location);
		 return _location[0];
	}

	private int getLocationY(View _v) {
		 int _location[] = new int[2];
		 _v.getLocationInWindow(_location);
		 return _location[1];
	}

	private int getRandom(int _minValue ,int _maxValue){
		Random random = new Random();
		return random.nextInt(_maxValue - _minValue + 1) + _minValue;
	}

	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
				_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}

	private float getDip(int _input){
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}

	private int getDisplayWidthPixels(){
		return getResources().getDisplayMetrics().widthPixels;
	}

	private int getDisplayHeightPixels(){
		return getResources().getDisplayMetrics().heightPixels;
	}


}
