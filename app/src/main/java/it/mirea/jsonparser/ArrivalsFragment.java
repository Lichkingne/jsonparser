package it.mirea.jsonparser;

import static it.mirea.jsonparser.MainActivity.ArrivalsList;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ArrivalsFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_arrivals, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.RecyclerView2);
        // создаем адаптер
        StateAdapter adapter = new StateAdapter(getContext(), ArrivalsList);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);
        return view;

    }
}