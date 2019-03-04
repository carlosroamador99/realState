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
import com.example.state4reals.retrofit.services.OnCasaFavInteractionListener;
import com.example.state4reals.ui.adapters.MyCasasFavAdapter;
import com.example.state4reals.viewModel.CasaViewModel;

import java.util.ArrayList;
import java.util.List;


public class CasasFavFragment extends Fragment implements OnCasaFavInteractionListener {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnCasaFavInteractionListener mListener;
    private List<Casa> casaFavList;
    private MyCasasFavAdapter adapter;
    private RecyclerView recyclerView;
    private Context ctx;
    private CasaViewModel casaViewModel;

    public CasasFavFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static CasasFavFragment newInstance(int columnCount) {
        CasasFavFragment fragment = new CasasFavFragment();
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
        View view = inflater.inflate(R.layout.fragment_casasfav_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            casaFavList = new ArrayList<>();
            adapter = new MyCasasFavAdapter(getActivity(), casaFavList, mListener, R.layout.fragment_casasfav);
            recyclerView.setAdapter(adapter);
            lanzarViewModel();

        }
        return view;
    }

    private void lanzarViewModel() {
        casaViewModel = ViewModelProviders.of(getActivity()).get(CasaViewModel.class);
        casaViewModel.getFavCasas(UtilToken.getToken(getActivity()));
        casaViewModel.getCasasFav().observe(getActivity(), new Observer<List<Casa>>() {
            @Override
            public void onChanged(@Nullable List<Casa> casas) {
                adapter.setNuevasFavCasas(casas);
            }
        });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.ctx = context;
        if (context instanceof OnCasaFavInteractionListener) {
            mListener = (OnCasaFavInteractionListener) context;
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
