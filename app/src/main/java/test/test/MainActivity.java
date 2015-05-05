package test.test;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import junit.framework.Test;


public class MainActivity extends ActionBarActivity implements TotalListener{

    private static final String TAG = Test.class.getSimpleName();
    ImageView articleImage;
    ListView mList;
    TextView total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mList = (ListView) findViewById(R.id.list);
        total = (TextView) findViewById(R.id.total);
        String [] string = {};
        TestAdapter ad = new TestAdapter(this, R.layout.test, string);
        ad.setListener(this);
        mList.setAdapter(ad);

    }

    @Override
    public void onTotalChanged(int sum) {
        total.setText("Total = " + String.valueOf(sum));
    }
}
