package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private static final Random RANDOM=new Random();
    private LineGraphSeries<DataPoint> series;
    private int lastX=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //we get graph
        GraphView graph =(GraphView)findViewById(R.id.graph);

        //data
        series=new LineGraphSeries<DataPoint>();
        /*LineGraphSeries<DataPoint> line_series=
                new LineGraphSeries<DataPoint>(new DataPoint[]){
                    new DataPoint(100,9);
                    new DataPoint(100,10);
                    new DataPoint(100,11);
                    new DataPoint(100,12);
                    new DataPoint(100,13);
                    new DataPoint(100,14);
                    new DataPoint(100,13);
                    new DataPoint(100,12);
                    new DataPoint(100,11);
                    new DataPoint(100,10);
                    new DataPoint(100,9);
                    new DataPoint(100,8);
                    new DataPoint(100,7);
                    new DataPoint(100,6);
                    new DataPoint(100,7);
                    new DataPoint(100,8);
                    new DataPoint(100,9);
                    new DataPoint(100,10);
                    new DataPoint(100,11);

                });*/
        //int heart_beat=new int[]{9,10,11,12,13,14,13,12,11,10,9,8,7,6,7,8,9,10,11,10,10};
        graph.addSeries(series);

        //customer viewreport
        Viewport viewport=graph.getViewport();
        viewport.setYAxisBoundsManual(true);
        viewport.setMinY(4);
        viewport.setMaxY(16);
        viewport.setMaxX(100);
        viewport.setScrollable(true);
        viewport.setScalable(true);
        GridLabelRenderer gridLabel = graph.getGridLabelRenderer();
        gridLabel.setHorizontalAxisTitle("Time");
        gridLabel.setVerticalAxisTitle("mmHg");
        StaticLabelsFormatter staticLabelsFormatter =new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setVerticalLabels(new String[]{"4","6","8","10","12","14","16"});
        staticLabelsFormatter.setHorizontalLabels(new String[]{"1","2","3","4","5"});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
        //graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setXAxisBoundsManual(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //we're going to simulate real time with thread that append data to the graph
        new  Thread(new Runnable() {
            @Override
            public void run() {
                //we add 100 new entries
                for(int i=0;i<500;i++){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(RANDOM.nextDouble()*10-9<0){
                            addEntry();}
                            else
                            {
                                addHeart_beat();


                            }
                        }
                    });
                    //sleep to slow down the add of entries
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e ){
                       // e.printStackTrace();
                    }


                }
            }
        }).start();
    }

    //add data to graph
    private void addEntry(){
        //chose to display max 10 point on the viewport and scroll to the end
        //series.appendData(new DataPoint(lastX++, RANDOM.nextDouble()*10d),true,1000);

        series.appendData(new DataPoint(lastX++, 10),true,100);
    }

    private void addHeart_beat(){
        series.appendData(new DataPoint(lastX++,9),true,50);
                               /* series.appendData(new DataPoint(lastX++,10),true,50);
                                series.appendData(new DataPoint(lastX++,11),true,50);
                                series.appendData(new DataPoint(lastX++,12),true,50);
                                series.appendData(new DataPoint(lastX++,13),true,50);*/
        series.appendData(new DataPoint(lastX++,14),true,500);
                               /* series.appendData(new DataPoint(lastX++,13),true,100);
                                series.appendData(new DataPoint(lastX++,12),true,100);
                                series.appendData(new DataPoint(lastX++,11),true,100);
                                series.appendData(new DataPoint(lastX++,10),true,100);
                                series.appendData(new DataPoint(lastX++,9),true,100);
                                series.appendData(new DataPoint(lastX++,8),true,100);
                                series.appendData(new DataPoint(lastX++,7),true,100);*/
        series.appendData(new DataPoint(lastX++,6),true,500);
                                /*series.appendData(new DataPoint(lastX++,7),true,100);
                                series.appendData(new DataPoint(lastX++,8),true,100);
                                series.appendData(new DataPoint(lastX++,9),true,100);
                                series.appendData(new DataPoint(lastX++,10),true,100);*/
        series.appendData(new DataPoint(lastX++,11),true,50);
    }

}
