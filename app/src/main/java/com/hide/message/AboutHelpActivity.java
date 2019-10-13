package com.hide.message;

import android.app.*;
import android.os.*;
import android.text.method.LinkMovementMethod;
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

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardedVideoAd;

import java.util.*;
import java.text.*;


public class AboutHelpActivity extends Activity {

	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private ScrollView vscroll1;
	private Button support_button;
	private LinearLayout linear9;
	private LinearLayout linear6;
	private LinearLayout ad_linear;
	private TextView about_title;
	private LinearLayout linear5;
	private TextView textview12;
	private TextView textview3;
	private TextView textview4;
	private TextView textview5;
	private TextView textview6;
	private TextView textview15;
	private TextView textview7;
	private TextView textview8;
	private TextView textview13;
	private TextView textview14;
	private TextView textview11;

	private RewardedVideoAd vAd;
	private InterstitialAd iAd,ivAd;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_help);
		initialize();
		initializeLogic();

		//admob app-id
		MobileAds.initialize(this,"ca-app-pub-2062147080542547~9607859536");

		AdView mAdview= (AdView) findViewById(R.id.adView);
		AdRequest adrequest = new AdRequest.Builder().build();
		mAdview.loadAd(adrequest);

		vAd = MobileAds.getRewardedVideoAdInstance(this);
		loadRewardedVideoAd();

//interstial ad unit id//
        iAd = new InterstitialAd(this);
		iAd.setAdUnitId("ca-app-pub-2062147080542547/3804868517");  	//ca-app-pub-2062147080542547/3804868517//
		ivAd = new InterstitialAd(this);
		ivAd.setAdUnitId("ca-app-pub-2062147080542547/7131624028");

		loadInterstitialAd();

		iAd.setAdListener(new AdListener()
						  {
							  @Override
							  public void onAdClosed() {

							  	loadInterstitialAd();
							  }
						  }

		);
		ivAd.setAdListener(new AdListener()
						  {
							  @Override
							  public void onAdClosed() {

								  loadInterstitialAd();
							  }
						  }

		);

		TextView privacy= (TextView) findViewById(R.id.privacy_policy);
		privacy.setMovementMethod(LinkMovementMethod.getInstance());

	}


	private void loadInterstitialAd()
	{
		if(!iAd.isLoaded()) {
			iAd.loadAd(new AdRequest.Builder().build());
		}
		if(!ivAd.isLoaded()) {
			ivAd.loadAd(new AdRequest.Builder().build());
		}
	}

	private void loadRewardedVideoAd()
	{
		//video ad unit id//

		if(!vAd.isLoaded())
		{
			vAd.loadAd("ca-app-pub-2062147080542547/5115425657", new AdRequest.Builder().build());	//ca-app-pub-2062147080542547/4160091731//
		}
	}

	private void  initialize() {
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		vscroll1 = (ScrollView) findViewById(R.id.vscroll1);
		support_button = (Button) findViewById(R.id.support_button);
		linear9 = (LinearLayout) findViewById(R.id.linear9);
		linear6 = (LinearLayout) findViewById(R.id.linear6);
		ad_linear = (LinearLayout) findViewById(R.id.ad_linear);
		about_title = (TextView) findViewById(R.id.about_title);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		textview12 = (TextView) findViewById(R.id.textview12);
		textview3 = (TextView) findViewById(R.id.textview3);
		textview4 = (TextView) findViewById(R.id.textview4);
		textview5 = (TextView) findViewById(R.id.textview5);
		textview6 = (TextView) findViewById(R.id.textview6);
		textview15 = (TextView) findViewById(R.id.textview15);
		textview7 = (TextView) findViewById(R.id.textview7);
		textview8 = (TextView) findViewById(R.id.textview8);
		textview13 = (TextView) findViewById(R.id.textview13);
		textview14 = (TextView) findViewById(R.id.textview14);
		textview11 = (TextView) findViewById(R.id.textview11);


//on support button click//
		support_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {

				if(vAd.isLoaded())
				{

					vAd.show();

					loadInterstitialAd();
				}
				else
				{
					if(ivAd.isLoaded())
					{
						ivAd.show();
						loadRewardedVideoAd();

					}
					else
					{ if(iAd.isLoaded()) {
						iAd.show();
						loadInterstitialAd();
					}
					}
				}

			}
		});

	}

	private void  initializeLogic() {
		about_title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/bungeeregular.ttf"), 0);
		support_button.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/bungeeregular.ttf"), 0);
	}

	@Override
	public void onPause() {
		super.onPause();

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
