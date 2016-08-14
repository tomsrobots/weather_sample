package com.thomastaylor.yahooweather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity {
    private static final String url = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22nome%2C%20ak%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forecast_list);

        new WeatherRetriever(this) {
            @Override
            protected void onPostExecute(String result) {
                try {
                    final JSONArray jsonArray = new JSONObject(result).getJSONObject("query")
                            .getJSONObject("results")
                            .getJSONObject("channel")
                            .getJSONObject("item")
                            .getJSONArray("forecast");
                    WeatherItem items[] = new WeatherItem[jsonArray.length()];
                    for(int i=0; i<jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        WeatherItem item = new WeatherItem(IconMap.get(jsonObject.getString("text")),
                                jsonObject.getString("high"),
                                jsonObject.getString("date"));
                        items[i] = item;
                    }
                    final ListView weatherList = (ListView)findViewById(R.id.listWeather);
                    WeatherArrayAdapter adapter = new WeatherArrayAdapter(getActivity(),
                            R.layout.forecast_item, items);
                    weatherList.setAdapter(adapter);

                    weatherList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            try {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Intent intent = new Intent(getActivity(), WeatherDetailActivity.class);
                                intent.putExtra("high", jsonObject.getString("high"));
                                intent.putExtra("low", jsonObject.getString("low"));
                                intent.putExtra("date", jsonObject.getString("date"));
                                intent.putExtra("text", jsonObject.getString("text"));
                                startActivity(intent);
                            } catch (JSONException e) {
                                Log.e("web", e.toString());
                                e.printStackTrace();
                            }
                        }
                    });
                } catch (JSONException e) {
                    Log.e("web", e.toString());
                    e.printStackTrace();
                }
            }

        }.execute(url);
    }
}
