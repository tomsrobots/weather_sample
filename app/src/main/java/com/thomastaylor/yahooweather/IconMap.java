package com.thomastaylor.yahooweather;

import java.util.HashMap;
import java.util.Map;

/**
 * Map between the icons and the various weather conditions.
 *
 * Created by tom on 8/13/16.
 */
public class IconMap {
    private static Map<String, Integer> iconMap;

    static {
        iconMap = new HashMap<>();
        iconMap.put("Partly Cloudy", R.drawable.sunny);
        iconMap.put("Mostly Cloudy", R.drawable.partly_cloudy);
        iconMap.put("Cloudy", R.drawable.partly_cloudy);
        iconMap.put("Showers", R.drawable.rain);
    }

    public static int get(String string) {
        return iconMap.get(string);
    }
}
