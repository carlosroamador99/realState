package com.example.state4reals.ui.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.state4reals.R;
import com.example.state4reals.model.Casa;
import com.example.state4reals.retrofit.UtilToken;
import com.example.state4reals.retrofit.services.OnMyCasaInteractionListener;
import com.example.state4reals.ui.adapters.MyMyCasasRecyclerViewAdapter;
import com.example.state4reals.viewModel.CasaViewModel;

import java.util.ArrayList;
import java.util.List;

public class MyCasasFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private OnMyCasaInteractionListener mListener;
    private MyMyCasasRecyclerViewAdapter adapter;
    private List<Casa> myCasasList;
    private RecyclerView recyclerView;
    private Context context;
    private CasaViewModel casaViewModel;


    public MyCasasFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static MyCasasFragment newInstance(int columnCount) {
        MyCasasFragment fragment = new MyCasasFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mycasas_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            myCasasList = new ArrayList<>();
            adapter = new MyMyCasasRecyclerViewAdapter(getActivity(), myCasasList, mListener, R.layout.fragment_mycasas);
            recyclerView.setAdapter(adapter);
            lanzarViewModel();

        }
        return view;
    }

    private void lanzarViewModel() {
        casaViewModel = ViewModelProviders.of(getActivity()).get(CasaViewModel.class);
        casaViewModel.getMyCasas(UtilToken.getToken(getActivity()));
        casaViewModel.getListMisCasas().observe(getActivity(), new Observer<List<Casa>>() {
            @Override
            public void onChanged(@Nullable List<Casa> casas) {
                adapter.setNuevaMyCasas(casas);
            }
        });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        if (context instanceof OnMyCasaInteractionListener) {
            mListener = (OnMyCasaInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



}
