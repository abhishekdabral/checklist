package test.test;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ABHISHEK on 4/21/2015.
 */
public class TestAdapter extends ArrayAdapter<String> {
    private LayoutInflater mInflater;
    private String[] mStrings = {"10","20","30","40","50","60","70", "80", "90", "100", "110", "120", "130", "140","10","20","30","40","50","60","70", "80", "90", "100", "110", "120", "130", "140"};
    private TypedArray mIcons;
    private int mViewResourceId;
    Holder holder;


    public void setListener(TotalListener listener) {
        this.listener = listener;
    }

    private TotalListener listener;
    ArrayList<Boolean> selectedStrings = new ArrayList<>();

    public TestAdapter(Context ctx, int viewResourceId, String[] strings) {
        super(ctx, viewResourceId, strings);

        mInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

//        mStrings = strings;

        mViewResourceId = viewResourceId;
    }

    public int getCount() {
        return mStrings.length;
    }

    public String getItem(int position) {
        return mStrings[position];
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = mInflater.inflate(mViewResourceId, null);
            holder = new Holder();
            holder.textView = (TextView) convertView.findViewById(R.id.text);
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);
            convertView.setTag(holder);
        }else{
            holder = (Holder) convertView.getTag();
        }


//        holder.checkBox.setText(mStrings[position]);
        holder.textView.setText(mStrings[position]);
        holder.checkBox.setChecked(false);
        if(selectedStrings.size() <= position){
            selectedStrings.add(position, false);
        }else
            holder.checkBox.setChecked(selectedStrings.get(position));
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean state = selectedStrings.get(position);
                selectedStrings.remove(position);
                selectedStrings.add(position, state ? false : true);
                int sum = 0;
                for(int i = 0; i < selectedStrings.size(); i++){
                    if(selectedStrings.get(i)){
                        sum += Integer.parseInt(mStrings[i]);
                    }
                    listener.onTotalChanged(sum);
                }
            }
        });

        return convertView;
    }



class Holder {
    CheckBox checkBox;
    TextView textView;
}

}