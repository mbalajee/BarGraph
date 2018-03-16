package in.learn.bargraph_sample;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import in.learn.bargraph.BarData;
import in.learn.bargraph.BarGraphView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupBarGraph();
    }

    private void setupBarGraph() {
        BarGraphView barGraphView = findViewById(R.id.listGraph);
        barGraphView.setBarData(createTestData());
    }

    private List<BarData> createTestData() {

        List<BarData> list = new ArrayList<>();

        for (int i = 24; i > 0; i--) {

            int color;

            if (i > 10) {
                color = Color.GREEN;
            } else if (i > 5) {
                color = Color.RED;
            } else {
                color = Color.CYAN;
            }

            BarData barData = new BarData("1/"+i, i, i * 10, color);
            list.add(barData);
        }

        return list;
    }
}
