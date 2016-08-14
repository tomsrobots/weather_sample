package com.thomastaylor.yahooweather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Screen showing a detailed view of weather.
 *
 * Created by tom on 8/13/16.
 */
public class WeatherDetailActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_forecast_item);
        Intent intent = getIntent();
        String high = intent.getStringExtra("high");
        String low = intent.getStringExtra("low");
        String date = intent.getStringExtra("date");
        String text = intent.getStringExtra("text");
        ((ImageView)findViewById(R.id.image_weather_icon)).setImageResource(IconMap.get(text));
        ((TextView)findViewById(R.id.text_high)).setText(high);
        ((TextView)findViewById(R.id.text_low)).setText(low);
        ((TextView)findViewById(R.id.text_weather_desc)).setText(text);
        ((TextView)findViewById(R.id.text_date)).setText(date);
    }
}
