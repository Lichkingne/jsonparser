package it.mirea.jsonparser;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

public class    DeparturesFragment extends Fragment {

    //ListView
    public ListView listDepartures;
    //Adapter
    public ArrayAdapter<String> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_departures, container, false);

        listDepartures = view.findViewById(R.id.listDepartures);

        adapter = new ArrayAdapter<String>(getActivity(), R.layout.custom_list, it.mirea.jsonparser.DataDepartures.departuresList);
        listDepartures.setAdapter(adapter);
        return view;
    }
}