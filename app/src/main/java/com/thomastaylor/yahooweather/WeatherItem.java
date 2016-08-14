package com.thomastaylor.yahooweather;

/**
 * An item in the list of weather forecasts.
 *
 * Created by tom on 8/13/16.
 */
public class WeatherItem {
    public int iconId;
    public String temperature;
    public String date;

    public WeatherItem(int iconId, String temperature, String date) {
        this.iconId = iconId;
        this.temperature = temperature;
        this.date = date;
    }
}
