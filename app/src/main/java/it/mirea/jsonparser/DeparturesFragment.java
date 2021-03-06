package it.mirea.jsonparser;

import static it.mirea.jsonparser.MainActivity.ArrivalsList;
import static it.mirea.jsonparser.MainActivity.departuresList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class    DeparturesFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_departures, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.RecyclerView);
        // создаем адаптер
        StateAdapterDeparture adapter = new StateAdapterDeparture(getContext(), departuresList);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);
        return view;
    }
}