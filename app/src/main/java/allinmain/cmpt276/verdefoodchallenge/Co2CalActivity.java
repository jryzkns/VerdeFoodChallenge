package allinmain.cmpt276.verdefoodchallenge;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Co2CalActivity extends Activity implements View.OnClickListener, bottom_bar.OnFragmentInteractionListener {
    private Button recal, suggest;
    private TextView calinfo;
    private DataCenter dc=DataCenter.getInstance();
    private PieChart mChart;
    HashMap<Integer,Float> suggestion = dc.makeDietChangeTable("LESSMEANT");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_co2cal);

        FragmentManager fm = getFragmentManager();
        bottom_bar fragment = new bottom_bar();
        fm.beginTransaction().add(R.id.bottom_bar_frame,fragment).commit();

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

        about.setOnClickListener(this);
        SetUpPieChart();
    }

    private void SetUpPieChart() {
        List<PieEntry> poeEntries = new ArrayList<>();

        for (int i = 0 ; i < dc.getFoodsSize();i++){
            float temp=dc.getDietItem(i);
            if (temp!=-1f)
                poeEntries.add(new PieEntry(dc.getDietItem(i),dc.mFoodLst.get(i).getName()));
        }
        PieDataSet dataSet=new PieDataSet(poeEntries, "Deit Item pie chart" ) ;
        //make the pie chart colorful
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        //set the pie chart show up the percentage in the graph
        dataSet.setValueFormatter(new PercentFormatter());
        PieData data = new PieData (dataSet);

        PieChart chart = (PieChart) findViewById(R.id.chart_co2cal);
        chart.setData(data);

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

        }
    }

    @Override
    public void onFragmentInteraction() {

    }
}


