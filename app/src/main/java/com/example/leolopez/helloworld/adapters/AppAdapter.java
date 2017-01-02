package com.example.leolopez.helloworld.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.leolopez.helloworld.R;
import com.example.leolopez.helloworld.models.App;

import java.util.List;

/**
 * Created by leolopez94 on 30/12/16.
 */

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.ViewHolder> {

    private List<App> apps;
    private Context context;

    public AppAdapter(List<App> apps, Context context) {
        this.apps = apps;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.app_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        App app = apps.get(position);
        holder.txtName.setText(app.getName(context.getPackageManager()));
        holder.txtReceived.setText(Integer.toString(app.getReceived()) + " KB");
    }

    @Override
    public int getItemCount() {
        return apps.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtName;
        TextView txtReceived;

        public ViewHolder(View itemView) {
            super(itemView);

            txtName = (TextView) itemView.findViewById(R.id.txt_name_app);
            txtReceived = (TextView) itemView.findViewById(R.id.txt_received_app);

        }
    }

    public List<App> getApps() {
        return apps;
    }
}
