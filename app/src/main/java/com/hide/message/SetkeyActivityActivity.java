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

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.NativeExpressAdView;

import java.util.*;
import java.text.*;


public class SetkeyActivityActivity extends Activity {

	private LinearLayout linear1;
	private LinearLayout linear2;
	private TextView textview6;
	private LinearLayout linear11;
	private ImageView imageview1;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private LinearLayout linear5;
	private LinearLayout linear9;
	private TextView textview1;
	private TextView keyshow_text;
	private EditText editkeytext;
	private Button setkey_button;
	private LinearLayout linear12;
	private Button defkey_button;
	private LinearLayout linear6;
	private ScrollView vscroll3;
	private TextView impotent_textview;
	private LinearLayout linear10;
	private TextView textview4;
	private TextView textview7;
	private TextView textview8;
	private TextView textview9;
	private LinearLayout ad_linear;
	private TextView textview10;
	private TextView textview13;
	private TextView textview12;
	private TextView textview11;

   // private NativeExpressAdView nativeAd;

	private SharedPreferences db;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setkey_activity);
		initialize();
		initializeLogic();

        /* admob app-id
        MobileAds.initialize(this,"ca-app-pub-3940256099942544~3347511713");

        nativeAd = (NativeExpressAdView) findViewById(R.id.nativeAd);
        nativeAd.loadAd(new AdRequest.Builder().addTestDevice("DF7EFEC49F374CAE7CE63ABC38A705B5").build()); */

	}

	private void  initialize() {
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		textview6 = (TextView) findViewById(R.id.textview6);
		linear11 = (LinearLayout) findViewById(R.id.linear11);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		linear9 = (LinearLayout) findViewById(R.id.linear9);
		textview1 = (TextView) findViewById(R.id.textview1);
		keyshow_text = (TextView) findViewById(R.id.keyshow_text);
		editkeytext = (EditText) findViewById(R.id.editkeytext);
		setkey_button = (Button) findViewById(R.id.setkey_button);
		linear12 = (LinearLayout) findViewById(R.id.linear12);
		defkey_button = (Button) findViewById(R.id.defkey_button);
		linear6 = (LinearLayout) findViewById(R.id.linear6);
		vscroll3 = (ScrollView) findViewById(R.id.vscroll3);
		impotent_textview = (TextView) findViewById(R.id.impotent_textview);
		linear10 = (LinearLayout) findViewById(R.id.linear10);
		textview4 = (TextView) findViewById(R.id.textview4);
		textview7 = (TextView) findViewById(R.id.textview7);
		textview8 = (TextView) findViewById(R.id.textview8);
		textview9 = (TextView) findViewById(R.id.textview9);
		ad_linear = (LinearLayout) findViewById(R.id.ad_linear);
		textview10 = (TextView) findViewById(R.id.textview10);
		textview13 = (TextView) findViewById(R.id.textview13);
		textview12 = (TextView) findViewById(R.id.textview12);
		textview11 = (TextView) findViewById(R.id.textview11);

		db = getSharedPreferences("key", Activity.MODE_PRIVATE);

		setkey_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) { 
				if (editkeytext.getText().toString().length() > 1) {
					if (Double.parseDouble(editkeytext.getText().toString().substring((int)(0), (int)(2))) == 00) {
						showMessage("First 2 digit can't be Zero");
					}
					else {
						if (editkeytext.getText().toString().length() == 4) {
							db.edit().putString("key", editkeytext.getText().toString()).commit();
							keyshow_text.setText(db.getString("key", ""));
							showMessage("Key is Set");
						}
						else {
							showMessage("You must Enter 4 digit key");
						}
					}
				}
				else {
					showMessage("You must Enter 4 digit key");
				}
			}
		});
		defkey_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) { 
				db.edit().remove("key").commit();
				keyshow_text.setText("Default");
				showMessage("Default key is Set");
			}
		});

	}

	private void  initializeLogic() {
		setkey_button.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/bungeeregular.ttf"), 0);
		defkey_button.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/bungeeregular.ttf"), 0);
		impotent_textview.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/bungeeregular.ttf"), 0);
		if (db.getString("key", "").length() == 0) {
			keyshow_text.setText("Default");
		}
		else {
			keyshow_text.setText(db.getString("key", ""));
		}
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
