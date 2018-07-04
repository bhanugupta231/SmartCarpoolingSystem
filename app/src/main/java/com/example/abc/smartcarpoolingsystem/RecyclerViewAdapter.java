package com.example.abc.smartcarpoolingsystem;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by abc on 3/23/2018.
 */

public class RecyclerViewAdapter extends ArrayAdapter<DataBean> {
    private Activity context;
    private List<DataBean> datalist;

    public RecyclerViewAdapter(Activity context,List<DataBean> datalist){
        super(context,R.layout.list_layout,datalist);
        this.context=context;
        this.datalist=datalist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View listviewitem=inflater.inflate(R.layout.list_layout,null,true);
        TextView tvdepartureplacelist=(TextView)listviewitem.findViewById(R.id.tvdepartureplacelist);
        TextView tvdestinationplacelist=(TextView)listviewitem.findViewById(R.id.tvdestinationplacelist);
        TextView tvdatelist=(TextView)listviewitem.findViewById(R.id.tvdatelist);
        TextView tvtimelist=(TextView)listviewitem.findViewById(R.id.tvtimelist);
        TextView tvwhethercarlist=(TextView)listviewitem.findViewById(R.id.tvwhethercarlist);
        TextView tvnumber=(TextView)listviewitem.findViewById(R.id.tvnumberlist);
        DataBean dataBean=datalist.get(position);
        tvdepartureplacelist.setText(dataBean.getDepartureplace());
        tvdestinationplacelist.setText(dataBean.getArrivalplace());
        tvdatelist.setText(dataBean.getDate());
        tvtimelist.setText(dataBean.getTime());
        tvwhethercarlist.setText(dataBean.getWhethercar());
        tvnumber.setText(dataBean.getNumber());


        return listviewitem;
    }
}
