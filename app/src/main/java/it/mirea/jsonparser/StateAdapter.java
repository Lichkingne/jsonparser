package it.mirea.jsonparser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StateAdapter  extends RecyclerView.Adapter<StateAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final ArrayList<AirportFlightsArrival> states;

    StateAdapter(Context context, ArrayList<AirportFlightsArrival> states) {
        this.states = states;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public StateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.custom_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StateAdapter.ViewHolder holder, int position) {
        AirportFlightsArrival state = states.get(position);
        holder.cityView.setText(state.getCityArr());
        holder.timeView.setText(state.getTimeArr());
        holder.statusView.setText(state.getStatusArr());
        holder.planeView.setText(state.getPlaneidArr());
    }

    @Override
    public int getItemCount() {
        return states.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView cityView, timeView,planeView,statusView;
        ViewHolder(View view){
            super(view);
            cityView = view.findViewById(R.id.city);
            timeView = view.findViewById(R.id.time);
            planeView = view.findViewById(R.id.planeid);
            statusView = view.findViewById(R.id.status);

        }
    }
}
