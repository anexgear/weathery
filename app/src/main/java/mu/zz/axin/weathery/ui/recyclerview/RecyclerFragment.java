package mu.zz.axin.weathery.ui.recyclerview;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mu.zz.axin.weathery.R;

public class RecyclerFragment extends Fragment {
    RecyclerView mRecyclerView;


    public RecyclerFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler, container, false);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        return view;


    }

}
