package it.mirea.jsonparser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StateAdapterDeparture  extends RecyclerView.Adapter<StateAdapterDeparture.ViewHolder1>{

    private final LayoutInflater inflater;
    private final ArrayList<AirportFlights> states;

    StateAdapterDeparture(Context context, ArrayList<AirportFlights> states) {
        this.states = states;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public StateAdapterDeparture.ViewHolder1 onCreateViewHolder(ViewGroup parent1, int viewType) {

        View view = inflater.inflate(R.layout.custom_list, parent1, false);
        return new ViewHolder1(view);
    }

    @Override
    public void onBindViewHolder(StateAdapterDeparture.ViewHolder1 holder, int position) {
        AirportFlights state = states.get(position);
        holder.cityView1.setText(state.getCity());
        holder.timeView1.setText(state.getTime());
        holder.statusView1.setText(state.getStatus());
        holder.planeView1.setText(state.getPlaneid());
    }

    @Override
    public int getItemCount() {
        return states.size();
    }

    public static class ViewHolder1 extends RecyclerView.ViewHolder {
        final TextView cityView1, timeView1,planeView1,statusView1;
        ViewHolder1(View view){
            super(view);
            cityView1 = view.findViewById(R.id.city);
            timeView1 = view.findViewById(R.id.time);
            planeView1 = view.findViewById(R.id.planeid);
            statusView1 = view.findViewById(R.id.status);

        }
    }
}
