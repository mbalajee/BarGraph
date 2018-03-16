package in.learn.bargraph;

import android.annotation.SuppressLint;
import android.util.Log;

/**
 * Created by balajim on 3/12/18.
 */

public class BarData {

    private String date;
    private float hours;
    private int index;
    private int color;
    private int height = -1;

    byte animate = -1;

    public BarData(String date, float hours, int index, int color) {
        this.date = date;
        this.hours = hours > 24.0 ? 24 : hours;
        this.index = index;
        this.color = color;
    }

    String getDate() {
        return date;
    }

    int getIndex() {
        return index;
    }

    @SuppressLint("DefaultLocale")
    String getFormattedHours() {
        return String.format("%d %s",(long) hours, 2.0 <= hours ? "hrs" : "hr");
    }

    int getHeight(int totalHeight) {
        if (height == -1) {
            height = computeHeight(totalHeight);
        }
        return height;
    }

    private int computeHeight(int totalHeight) {
        return Math.round(totalHeight - (totalHeight * (hours / 24f)));
    }

    public int getColor() {
        return color;
    }
}
