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

import java.util.*;
import java.text.*;


public class MainActivity extends Activity {

	private LinearLayout linear9;
	private ScrollView vscroll1;
	private TextView textview1;
	private LinearLayout linear16;
	private ImageView imageview1;
	private LinearLayout about_button;
	private LinearLayout linear15;
	private LinearLayout linear14;
	private LinearLayout linear4;
	private LinearLayout button_backlinear;
	private EditText inputtext;
	private LinearLayout setkey_linear;
	private LinearLayout linear17;
	private ImageView setkey_but;
	private TextView setkeytext;
	private Button copy_button;
	private ImageView share_button;
	private EditText proc_edittext;
	private Button e_button;
	private Button d_button;
	private LinearLayout linear7;
	private Button about_helpbutton;
	private TextView about;
	private LinearLayout ad_linear;

	private String t = "";
	private double n = 0;
	private double i = 0;
	private double j = 0;
	private double c = 0;
	private boolean dc;

	private ArrayList<String> array = new ArrayList<String>();
	private ArrayList<String> buffer = new ArrayList<String>();

	private Intent a = new Intent();
	private AlertDialog.Builder dialog;
	private SharedPreferences db;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initialize();
		initializeLogic();

		//admob app-id
		MobileAds.initialize(this,"ca-app-pub-2062147080542547~9607859536");

		AdView mAdview= (AdView) findViewById(R.id.adView);
		AdRequest adrequest = new AdRequest.Builder().build();
		mAdview.loadAd(adrequest);

	}

	private void  initialize() {
		linear9 = (LinearLayout) findViewById(R.id.linear9);
		vscroll1 = (ScrollView) findViewById(R.id.vscroll1);
		textview1 = (TextView) findViewById(R.id.textview1);
		linear16 = (LinearLayout) findViewById(R.id.linear16);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		about_button = (LinearLayout) findViewById(R.id.about_button);
		linear15 = (LinearLayout) findViewById(R.id.linear15);
		linear14 = (LinearLayout) findViewById(R.id.linear14);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		button_backlinear = (LinearLayout) findViewById(R.id.button_backlinear);
		inputtext = (EditText) findViewById(R.id.inputtext);
		setkey_linear = (LinearLayout) findViewById(R.id.setkey_linear);
		linear17 = (LinearLayout) findViewById(R.id.linear17);
		setkey_but = (ImageView) findViewById(R.id.setkey_but);
		setkeytext = (TextView) findViewById(R.id.setkeytext);
		copy_button = (Button) findViewById(R.id.copy_button);
		share_button = (ImageView) findViewById(R.id.share_button);
		proc_edittext = (EditText) findViewById(R.id.proc_edittext);
		e_button = (Button) findViewById(R.id.e_button);
		d_button = (Button) findViewById(R.id.d_button);
		linear7 = (LinearLayout) findViewById(R.id.linear7);
		about_helpbutton = (Button) findViewById(R.id.about_helpbutton);
		about = (TextView) findViewById(R.id.about);
		ad_linear = (LinearLayout) findViewById(R.id.ad_linear);


		dialog = new AlertDialog.Builder(this);
		db = getSharedPreferences("key", Activity.MODE_PRIVATE);

		e_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) { 
				if (inputtext.getText().toString().length() == 0) {
					showMessage("Field is Empty!");
				}
				else {
					t = inputtext.getText().toString();
					n = 0;
					for(int _repeat62 = 0; _repeat62 < (int)(t.length()); _repeat62++) {
						array.add(t.substring((int)(n), (int)(n + 1)));
						n++;
					}
					_logic();
				}
			}
		});
		d_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) { 
				if (inputtext.getText().toString().length() == 0) {
					showMessage("Field is Empty!");
				}
				else {
					if (dc) {
						n = 0;
						i = 0;
						j = 0;
						for(int _repeat37 = 0; _repeat37 < (int)(t.length()); _repeat37++) {
							if (t.substring((int)(n), (int)(n + 1)).equals("#")) {
								array.add((int)(j), String.valueOf((long)(Double.parseDouble(t.substring((int)(i), (int)(n))) - c)));
								j++;
								i=n+1;
							}
							n++;
						}
						i = 0;
						for(int _repeat62 = 0; _repeat62 < (int)(array.size()); _repeat62++) {
							proc_edittext.setText(proc_edittext.getText().toString().concat(array.get((int)(i))));
							i++;
						}
						array.clear();
						t = proc_edittext.getText().toString();
						n = 0;
						for(int _repeat13 = 0; _repeat13 < (int)((t.length() / 2)); _repeat13++) {
							array.add(t.substring((int)(n), (int)(n + 2)));
							n++;
							n++;
						}
						_d_logic();
					}
					else {
						showMessage("Enter valid Encrypted message to Decrypt");
					}
				}
			}
		});
		copy_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) { 
				if (inputtext.getText().toString().length() == 0) {
					showMessage("Field is Empty!!");
				}
				else {
					((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", inputtext.getText().toString()));
					showMessage("Copied !!");
				}
			}
		});
		about_helpbutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) { 
				a.setClass(getApplicationContext(), AboutHelpActivity.class);
				startActivity(a);
			}
		});
		share_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) { 
				if (inputtext.getText().toString().length() == 0) {
					showMessage("Field is Empty!!");
				}
				else {
					t = inputtext.getText().toString();
					Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND); sharingIntent.setType("text/plain"); sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, t); 
 startActivity(Intent.createChooser(sharingIntent, "Share Message via"));
				}
			}
		});
		setkey_but.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) { 
				a.setClass(getApplicationContext(), SetkeyActivityActivity.class);
				startActivity(a);
			}
		});
		inputtext.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence _text, int _start, int _count, int _after) {
			}
			@Override
			public void onTextChanged(final CharSequence _charSeq, int _start, int _before, int _count) {
				t = inputtext.getText().toString();
				dc=t.matches("^([0-9]{4,}#)+");
			}
			@Override
			public void afterTextChanged(Editable _text) {
			}
		});

	}

	private void  initializeLogic() {
		a.setClass(getApplicationContext(), SplashActivity.class);
		startActivity(a);
		proc_edittext.setVisibility(View.GONE);
		e_button.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/bungeeregular.ttf"), 0);
		d_button.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/bungeeregular.ttf"), 0);
		about_helpbutton.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/bungeeregular.ttf"), 0);
		copy_button.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/bungeeregular.ttf"), 0);
		setkeytext.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/bungeeregular.ttf"), 0);
	}

	@Override
	public void onBackPressed() {
				dialog.setTitle("Exit from the App");
				dialog.setMessage("\nDo you want to exit?");
				dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
											finish();
					}
				});
				dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
				
					}
				});
				dialog.create().show();
	}
	@Override
	public void onResume() {
		super.onResume();
				if (db.getString("key", "").length() == 0) {
					c = 3214;
				}
				else {
					c = Double.parseDouble(db.getString("key", ""));
				}
	}

	private void _logic () {
		i = 0;
		for(int _repeat148 = 0; _repeat148 < (int)(array.size()); _repeat148++) {
			if (array.get((int)(i)).equals("a")) {
				buffer.add("11");
			}
			if (array.get((int)(i)).equals("b")) {
				buffer.add("12");
			}
			if (array.get((int)(i)).equals("c")) {
				buffer.add("13");
			}
			if (array.get((int)(i)).equals("d")) {
				buffer.add("14");
			}
			if (array.get((int)(i)).equals("e")) {
				buffer.add("22");
			}
			if (array.get((int)(i)).equals("f")) {
				buffer.add("21");
			}
			if (array.get((int)(i)).equals("g")) {
				buffer.add("23");
			}
			if (array.get((int)(i)).equals("h")) {
				buffer.add("24");
			}
			if (array.get((int)(i)).equals("i")) {
				buffer.add("33");
			}
			if (array.get((int)(i)).equals("j")) {
				buffer.add("31");
			}
			if (array.get((int)(i)).equals("k")) {
				buffer.add("32");
			}
			if (array.get((int)(i)).equals("l")) {
				buffer.add("34");
			}
			if (array.get((int)(i)).equals("m")) {
				buffer.add("44");
			}
			if (array.get((int)(i)).equals("n")) {
				buffer.add("41");
			}
			if (array.get((int)(i)).equals("o")) {
				buffer.add("42");
			}
			if (array.get((int)(i)).equals("p")) {
				buffer.add("43");
			}
			if (array.get((int)(i)).equals("q")) {
				buffer.add("55");
			}
			if (array.get((int)(i)).equals("r")) {
				buffer.add("51");
			}
			if (array.get((int)(i)).equals("s")) {
				buffer.add("52");
			}
			if (array.get((int)(i)).equals("t")) {
				buffer.add("53");
			}
			if (array.get((int)(i)).equals("u")) {
				buffer.add("66");
			}
			if (array.get((int)(i)).equals("v")) {
				buffer.add("61");
			}
			if (array.get((int)(i)).equals("w")) {
				buffer.add("62");
			}
			if (array.get((int)(i)).equals("x")) {
				buffer.add("63");
			}
			if (array.get((int)(i)).equals("y")) {
				buffer.add("77");
			}
			if (array.get((int)(i)).equals("z")) {
				buffer.add("71");
			}
			if (array.get((int)(i)).equals(" ")) {
				buffer.add("99");
			}
			if (array.get((int)(i)).equals(".")) {
				buffer.add("98");
			}
			if (array.get((int)(i)).equals("A")) {
				buffer.add("10");
			}
			if (array.get((int)(i)).equals("B")) {
				buffer.add("19");
			}
			if (array.get((int)(i)).equals("C")) {
				buffer.add("18");
			}
			if (array.get((int)(i)).equals("D")) {
				buffer.add("17");
			}
			if (array.get((int)(i)).equals("E")) {
				buffer.add("20");
			}
			if (array.get((int)(i)).equals("F")) {
				buffer.add("29");
			}
			if (array.get((int)(i)).equals("G")) {
				buffer.add("28");
			}
			if (array.get((int)(i)).equals("H")) {
				buffer.add("27");
			}
			if (array.get((int)(i)).equals("I")) {
				buffer.add("30");
			}
			if (array.get((int)(i)).equals("J")) {
				buffer.add("39");
			}
			if (array.get((int)(i)).equals("K")) {
				buffer.add("38");
			}
			if (array.get((int)(i)).equals("L")) {
				buffer.add("37");
			}
			if (array.get((int)(i)).equals("M")) {
				buffer.add("40");
			}
			if (array.get((int)(i)).equals("N")) {
				buffer.add("49");
			}
			if (array.get((int)(i)).equals("O")) {
				buffer.add("48");
			}
			if (array.get((int)(i)).equals("P")) {
				buffer.add("47");
			}
			if (array.get((int)(i)).equals("Q")) {
				buffer.add("50");
			}
			if (array.get((int)(i)).equals("R")) {
				buffer.add("59");
			}
			if (array.get((int)(i)).equals("S")) {
				buffer.add("58");
			}
			if (array.get((int)(i)).equals("T")) {
				buffer.add("57");
			}
			if (array.get((int)(i)).equals("U")) {
				buffer.add("60");
			}
			if (array.get((int)(i)).equals("V")) {
				buffer.add("69");
			}
			if (array.get((int)(i)).equals("W")) {
				buffer.add("68");
			}
			if (array.get((int)(i)).equals("X")) {
				buffer.add("67");
			}
			if (array.get((int)(i)).equals("Y")) {
				buffer.add("80");
			}
			if (array.get((int)(i)).equals("Z")) {
				buffer.add("89");
			}
			if (array.get((int)(i)).equals("0")) {
				buffer.add("15");
			}
			if (array.get((int)(i)).equals("1")) {
				buffer.add("25");
			}
			if (array.get((int)(i)).equals("2")) {
				buffer.add("35");
			}
			if (array.get((int)(i)).equals("3")) {
				buffer.add("45");
			}
			if (array.get((int)(i)).equals("4")) {
				buffer.add("54");
			}
			if (array.get((int)(i)).equals("5")) {
				buffer.add("64");
			}
			if (array.get((int)(i)).equals("6")) {
				buffer.add("65");
			}
			if (array.get((int)(i)).equals("7")) {
				buffer.add("72");
			}
			if (array.get((int)(i)).equals("8")) {
				buffer.add("73");
			}
			if (array.get((int)(i)).equals("9")) {
				buffer.add("74");
			}
			if (array.get((int)(i)).equals("#")) {
				buffer.add("85");
			}
			if (array.get((int)(i)).equals("'")) {
				buffer.add("93");
			}
			if (array.get((int)(i)).equals(",")) {
				buffer.add("84");
			}
			if (array.get((int)(i)).equals("&")) {
				buffer.add("83");
			}
			if (array.get((int)(i)).equals("!")) {
				buffer.add("91");
			}
			if (array.get((int)(i)).equals("?")) {
				buffer.add("95");
			}
			if (array.get((int)(i)).equals("/")) {
				buffer.add("82");
			}
			if (array.get((int)(i)).equals("-")) {
				buffer.add("97");
			}
			if (array.get((int)(i)).equals("*")) {
				buffer.add("94");
			}
			if (array.get((int)(i)).equals("+")) {
				buffer.add("96");
			}
			if (array.get((int)(i)).equals(":")) {
				buffer.add("86");
			}
			i++;
		}
		_e_process();
	}
	private void _print () {
		i = 0;
		for(int _repeat12 = 0; _repeat12 < (int)(buffer.size()); _repeat12++) {
			inputtext.setText(inputtext.getText().toString().concat(buffer.get((int)(i))));
			i++;
		}
		array.clear();
		buffer.clear();
		proc_edittext.getText().clear();
	}
	private void _d_logic () {
		i = 0;
		for(int _repeat11 = 0; _repeat11 < (int)(array.size()); _repeat11++) {
			if (array.get((int)(i)).equals("11")) {
				buffer.add("a");
			}
			if (array.get((int)(i)).equals("12")) {
				buffer.add("b");
			}
			if (array.get((int)(i)).equals("13")) {
				buffer.add("c");
			}
			if (array.get((int)(i)).equals("14")) {
				buffer.add("d");
			}
			if (array.get((int)(i)).equals("22")) {
				buffer.add("e");
			}
			if (array.get((int)(i)).equals("21")) {
				buffer.add("f");
			}
			if (array.get((int)(i)).equals("23")) {
				buffer.add("g");
			}
			if (array.get((int)(i)).equals("24")) {
				buffer.add("h");
			}
			if (array.get((int)(i)).equals("33")) {
				buffer.add("i");
			}
			if (array.get((int)(i)).equals("31")) {
				buffer.add("j");
			}
			if (array.get((int)(i)).equals("32")) {
				buffer.add("k");
			}
			if (array.get((int)(i)).equals("34")) {
				buffer.add("l");
			}
			if (array.get((int)(i)).equals("44")) {
				buffer.add("m");
			}
			if (array.get((int)(i)).equals("41")) {
				buffer.add("n");
			}
			if (array.get((int)(i)).equals("42")) {
				buffer.add("o");
			}
			if (array.get((int)(i)).equals("43")) {
				buffer.add("p");
			}
			if (array.get((int)(i)).equals("55")) {
				buffer.add("q");
			}
			if (array.get((int)(i)).equals("51")) {
				buffer.add("r");
			}
			if (array.get((int)(i)).equals("52")) {
				buffer.add("s");
			}
			if (array.get((int)(i)).equals("53")) {
				buffer.add("t");
			}
			if (array.get((int)(i)).equals("66")) {
				buffer.add("u");
			}
			if (array.get((int)(i)).equals("61")) {
				buffer.add("v");
			}
			if (array.get((int)(i)).equals("62")) {
				buffer.add("w");
			}
			if (array.get((int)(i)).equals("63")) {
				buffer.add("x");
			}
			if (array.get((int)(i)).equals("77")) {
				buffer.add("y");
			}
			if (array.get((int)(i)).equals("71")) {
				buffer.add("z");
			}
			if (array.get((int)(i)).equals("99")) {
				buffer.add(" ");
			}
			if (array.get((int)(i)).equals("98")) {
				buffer.add(".");
			}
			if (array.get((int)(i)).equals("10")) {
				buffer.add("A");
			}
			if (array.get((int)(i)).equals("19")) {
				buffer.add("B");
			}
			if (array.get((int)(i)).equals("18")) {
				buffer.add("C");
			}
			if (array.get((int)(i)).equals("17")) {
				buffer.add("D");
			}
			if (array.get((int)(i)).equals("20")) {
				buffer.add("E");
			}
			if (array.get((int)(i)).equals("29")) {
				buffer.add("F");
			}
			if (array.get((int)(i)).equals("28")) {
				buffer.add("G");
			}
			if (array.get((int)(i)).equals("27")) {
				buffer.add("H");
			}
			if (array.get((int)(i)).equals("30")) {
				buffer.add("I");
			}
			if (array.get((int)(i)).equals("39")) {
				buffer.add("J");
			}
			if (array.get((int)(i)).equals("38")) {
				buffer.add("K");
			}
			if (array.get((int)(i)).equals("37")) {
				buffer.add("L");
			}
			if (array.get((int)(i)).equals("40")) {
				buffer.add("M");
			}
			if (array.get((int)(i)).equals("49")) {
				buffer.add("N");
			}
			if (array.get((int)(i)).equals("48")) {
				buffer.add("O");
			}
			if (array.get((int)(i)).equals("47")) {
				buffer.add("P");
			}
			if (array.get((int)(i)).equals("50")) {
				buffer.add("Q");
			}
			if (array.get((int)(i)).equals("59")) {
				buffer.add("R");
			}
			if (array.get((int)(i)).equals("58")) {
				buffer.add("S");
			}
			if (array.get((int)(i)).equals("57")) {
				buffer.add("T");
			}
			if (array.get((int)(i)).equals("60")) {
				buffer.add("U");
			}
			if (array.get((int)(i)).equals("69")) {
				buffer.add("V");
			}
			if (array.get((int)(i)).equals("68")) {
				buffer.add("W");
			}
			if (array.get((int)(i)).equals("67")) {
				buffer.add("X");
			}
			if (array.get((int)(i)).equals("80")) {
				buffer.add("Y");
			}
			if (array.get((int)(i)).equals("89")) {
				buffer.add("Z");
			}
			if (array.get((int)(i)).equals("15")) {
				buffer.add("0");
			}
			if (array.get((int)(i)).equals("25")) {
				buffer.add("1");
			}
			if (array.get((int)(i)).equals("35")) {
				buffer.add("2");
			}
			if (array.get((int)(i)).equals("45")) {
				buffer.add("3");
			}
			if (array.get((int)(i)).equals("54")) {
				buffer.add("4");
			}
			if (array.get((int)(i)).equals("64")) {
				buffer.add("5");
			}
			if (array.get((int)(i)).equals("65")) {
				buffer.add("6");
			}
			if (array.get((int)(i)).equals("72")) {
				buffer.add("7");
			}
			if (array.get((int)(i)).equals("73")) {
				buffer.add("8");
			}
			if (array.get((int)(i)).equals("74")) {
				buffer.add("9");
			}
			if (array.get((int)(i)).equals("85")) {
				buffer.add("#");
			}
			if (array.get((int)(i)).equals("93")) {
				buffer.add("'");
			}
			if (array.get((int)(i)).equals("84")) {
				buffer.add(",");
			}
			if (array.get((int)(i)).equals("83")) {
				buffer.add("&");
			}
			if (array.get((int)(i)).equals("91")) {
				buffer.add("!");
			}
			if (array.get((int)(i)).equals("95")) {
				buffer.add("?");
			}
			if (array.get((int)(i)).equals("82")) {
				buffer.add("/");
			}
			if (array.get((int)(i)).equals("97")) {
				buffer.add("-");
			}
			if (array.get((int)(i)).equals("94")) {
				buffer.add("*");
			}
			if (array.get((int)(i)).equals("96")) {
				buffer.add("+");
			}
			if (array.get((int)(i)).equals("86")) {
				buffer.add(":");
			}
			i++;
		}
		_d_process();
	}
	private void _e_process () {
		i = 0;
		inputtext.getText().clear();
		proc_edittext.getText().clear();
		for(int _repeat21 = 0; _repeat21 < (int)(buffer.size()); _repeat21++) {
			proc_edittext.setText(proc_edittext.getText().toString().concat(buffer.get((int)(i))));
			i++;
		}
		buffer.clear();
		t = proc_edittext.getText().toString();
		i = 0;
		n = 0;
		if ((t.length() % 4) == 0) {
			for(int _repeat43 = 0; _repeat43 < (int)((t.length() / 4)); _repeat43++) {
				buffer.add((int)(i), String.valueOf((long)(Double.parseDouble(t.substring((int)(n), (int)(n + 4))) + c)).concat("#"));
				n=n+4;
				i++;
			}
		}
		else {
			for(int _repeat60 = 0; _repeat60 < (int)(((t.length() - 2) / 4)); _repeat60++) {
				buffer.add((int)(i), String.valueOf((long)(Double.parseDouble(t.substring((int)(n), (int)(n + 4))) + c)).concat("#"));
				n=n+4;
				i++;
			}
			buffer.add((int)(i), String.valueOf((long)(Double.parseDouble(t.substring((int)(n), (int)(n + 2))) + c)).concat("#"));
		}
		_print();
	}
	private void _d_process () {
		i = 0;
		inputtext.getText().clear();
		for(int _repeat13 = 0; _repeat13 < (int)(buffer.size()); _repeat13++) {
			inputtext.setText(inputtext.getText().toString().concat(buffer.get((int)(i))));
			i++;
		}
		proc_edittext.getText().clear();
		array.clear();
		buffer.clear();
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


	public void appShare(View view) {
		Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
		sharingIntent.setType("text/plain");
		sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Hide Message : A private encryption app.");
		sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Hide Message : A private encryption app for hiding your Message,Text,Passwords,Emails from other people.\n\nhttps://play.google.com/store/apps/details?id=com.hide.message");
		startActivity(Intent.createChooser(sharingIntent, "Share App via"));
	}
}
