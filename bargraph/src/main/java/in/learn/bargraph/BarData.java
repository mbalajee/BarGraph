package in.learn.bargraph;

import android.annotation.SuppressLint;

/**
 * Created by balajim on 3/12/18.
 */

public class BarData {

    private String date;
    private float hours;
    private int index;
    private int color;
    private int height = -1;

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

    int getHeight(int maxHeight) {
        if (height == -1) {
            height = computeHeight(maxHeight);
        }
        return height;
    }

    private int computeHeight(int maxHeight) {
        return Math.round(maxHeight - (maxHeight * (hours / 24)));
    }

    public int getColor() {
        return color;
    }
}
