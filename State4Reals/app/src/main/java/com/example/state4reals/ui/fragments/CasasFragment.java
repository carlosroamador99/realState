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
import com.example.state4reals.retrofit.services.CasaInteractionListener;
import com.example.state4reals.ui.adapters.MyCasasAdapter;
import com.example.state4reals.viewModel.CasaViewModel;

import java.util.ArrayList;
import java.util.List;


public class CasasFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private CasaInteractionListener mListener;
    private MyCasasAdapter adapter;
    private List<Casa> casaList;
    private RecyclerView recyclerView;
    private Context ctx;
    private CasaViewModel viewModel;


    public CasasFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static CasasFragment newInstance(int columnCount) {
        CasasFragment fragment = new CasasFragment();
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
        View view = inflater.inflate(R.layout.fragment_item_listcasa, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = view.findViewById(R.id.list);
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            casaList = new ArrayList<>();
            adapter = new MyCasasAdapter(getActivity(), casaList, mListener, R.layout.fragment_casas);
            recyclerView.setAdapter(adapter);
            lanzarViewModel();
        }
        return view;
    }

    private void lanzarViewModel() {
        viewModel = ViewModelProviders.of(getActivity()).get(CasaViewModel.class);
        viewModel.getListCasas().observe(getActivity(), new Observer<List<Casa>>() {
            @Override
            public void onChanged(@Nullable List<Casa> casas) {
                adapter.setNuevasCasas(casas);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.ctx = context;
        if (context instanceof CasaInteractionListener) {
            mListener = (CasaInteractionListener) context;
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


