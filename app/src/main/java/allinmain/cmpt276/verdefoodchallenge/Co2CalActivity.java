package allinmain.cmpt276.verdefoodchallenge;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

public class Co2CalActivity extends Activity implements View.OnClickListener{
    private Button recal, suggest;
    private TextView calinfo;
    private DataCenter dc=DataCenter.getInstance();
    private PieChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_co2cal);
        init();
    }
    private void init()
    {
        //recal=(Button)this.findViewById(R.id.recal);
        //recal.setOnClickListener(this);


        calinfo=(TextView)this.findViewById(R.id.calinfo);
        calinfo.setText(dc.getDietInfo());
        suggest=this.findViewById(R.id.suggestion_from_result);
        suggest.setOnClickListener(this);
        ImageView about = findViewById(R.id.about_result);


//        // pie chart style
//        mChart = (PieChart) findViewById(R.id.chart1);
//        mChart.setUsePercentValues(true);
//        mChart.getDescription().setEnabled(false);
//        mChart.setExtraOffsets(5, 10, 5, 5);
//
//
//        mChart.setDrawHoleEnabled(true);
//        mChart.setHoleColor(Color.WHITE);
//
//        mChart.setTransparentCircleColor(Color.WHITE);
//        mChart.setTransparentCircleAlpha(110);
//
//        mChart.setHoleRadius(58f);
//        mChart.setTransparentCircleRadius(61f);
//
//        mChart.setDrawCenterText(true);
//
//        mChart.setRotationAngle(0);
//        // enable rotation of the chart by touch
//        mChart.setRotationEnabled(true);
//        mChart.setHighlightPerTapEnabled(true);
//
//        // mChart.setUnit(" €");
//        // mChart.setDrawUnitsInChart(true);
//
//        // add a selection listener
//        mChart.setOnChartValueSelectedListener((OnChartValueSelectedListener) this);
//
//
//
//        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
//        // mChart.spin(2000, 0, 360);
//        Legend l = mChart.getLegend();
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
//        l.setOrientation(Legend.LegendOrientation.VERTICAL);
//        l.setDrawInside(false);
//        l.setXEntrySpace(7f);
//        l.setYEntrySpace(0f);
//        l.setYOffset(0f);
//        mChart.setEntryLabelColor(Color.WHITE);
////        mChart.setEntryLabelTypeface(mTfRegular);
//        mChart.setEntryLabelTextSize(12f);





        //navigation
        ImageView toGreenfood = findViewById(R.id.toGreenfood_result);
        ImageView toResult = findViewById(R.id.toResult_result);
        ImageView toCommunity = findViewById(R.id.toCommunity_result);
        ImageView toProfile = findViewById(R.id.toProfile_result);
        toGreenfood.setOnClickListener(this);
        toResult.setOnClickListener(this);
        toCommunity.setOnClickListener(this);
        toProfile.setOnClickListener(this);

        about.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.suggestion_from_result:
                intent = new Intent(Co2CalActivity.this, SuggestionType.class);
                Co2CalActivity.this.startActivity(intent);
                break;
            case R.id.about_result:
                intent = new Intent(Co2CalActivity.this, ActivityAboutUs.class);
                Co2CalActivity.this.startActivity(intent);
                break;


            //navigation bar
            case R.id.toGreenfood_result:
                intent = new Intent(Co2CalActivity.this, GreenFoodActivity.class);
                Co2CalActivity.this.startActivity(intent);
                this.finish();
                break;
            case R.id.toCommunity_result:
                intent = new Intent(Co2CalActivity.this, community.class);
                Co2CalActivity.this.startActivity(intent);
                this.finish();
                break;
            case R.id.toProfile_result:
                intent = new Intent(Co2CalActivity.this, Profile.class);
                Co2CalActivity.this.startActivity(intent);
                this.finish();
                break;


        }
    }
}


