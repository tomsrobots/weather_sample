package com.thomastaylor.yahooweather;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Adapter to populate the weather list.
 *
 * Created by tom on 8/13/16.
 */
public class WeatherArrayAdapter extends ArrayAdapter<WeatherItem> {
    private Activity context;
    private int layoutResourceId;
    private WeatherItem items[] = null;

    public WeatherArrayAdapter(Activity context, int layoutResourceId, WeatherItem[] items) {
        super(context, layoutResourceId, items);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        WeatherItemHolder holder;
        if(row == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new WeatherItemHolder();
            holder.weatherIcon = (ImageView)row.findViewById(R.id.image_weather_icon);
            holder.textTemperature = (TextView)row.findViewById(R.id.text_temperature);
            holder.textDate = (TextView)row.findViewById(R.id.text_date);
            row.setTag(holder);
        } else {
            holder = (WeatherItemHolder)row.getTag();
        }
        WeatherItem weatherItem = items[position];
        holder.weatherIcon.setImageResource(weatherItem.iconId);
        holder.textTemperature.setText(weatherItem.temperature);
        holder.textDate.setText(weatherItem.date);
        return row;
    }

    static class WeatherItemHolder {
        ImageView weatherIcon;
        TextView textTemperature;
        TextView textDate;
    }
}
