# BarGraph

Currently supports only portrait orientation.  Download the aar file from repository and add it to your project.

### XML

    <com.packagename.BarGraphView
        android:id="@+id/listGraph"
        android:layout_width="match_parent"
        android:layout_height="120dp" />


### Java

    private void setupBarGraph() {
        BarGraphView barGraphView = findViewById(R.id.listGraph);
        barGraphView.setBarData(createTestData());
        
        // Animation customized value
        barGraphView.setAnimateDuration(1000); // Default is 700ms
    }
         
    private List<BarData> createTestData() {

        List<BarData> list = new ArrayList<>();

        for (int i = 30; i > 0; i--) {

            int color;

            if (i > 20) {
                color = Color.GREEN;
            } else if (i > 10) {
                color = Color.RED;
            } else {
                color = Color.CYAN;
            }

            BarData barData = new BarData("1/"+i, i, i * 10, color);
            list.add(barData);
        }

        return list;
    }
    
  #### BarGraph in action

![alt text](https://github.com/mbalajee/BarGraph/blob/master/bar_graph_sample.gif)
