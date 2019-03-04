package com.example.state4reals.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.state4reals.R;
import com.example.state4reals.model.Casa;
import com.example.state4reals.retrofit.services.OnMyCasaInteractionListener;

import java.util.List;

public class MyMyCasasRecyclerViewAdapter extends RecyclerView.Adapter<MyMyCasasRecyclerViewAdapter.ViewHolder> {

    private Context ctx;
    private List<Casa> mValues;
    private final OnMyCasaInteractionListener mListener;


    public MyMyCasasRecyclerViewAdapter(Context context, List<Casa> items, OnMyCasaInteractionListener listener, int layout) {
        this.ctx = context;
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_mycasas, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mItem = mValues.get(position);
        holder.titulo.setText(holder.mItem.getTitle());
        holder.descripcion.setText(holder.mItem.getDescription());
        holder.direccion.setText(holder.mItem.getAddress());
        holder.habitaciones.setText(Integer.toString(holder.mItem.getRoom()));
        holder.precio.setText(Integer.toString(holder.mItem.getPrice()));
        holder.tamanio.setText(Integer.toString(holder.mItem.getSize()));

        if (holder.mItem.getPhotos() != null) {
            Glide
                    .with(ctx)
                    .load(holder.mItem.getPhotos()[0])
                    .into(holder.piso);
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void setNuevaMyCasas(List<Casa> casas) {
        this.mValues = casas;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView titulo;
        public final TextView descripcion;
        public final TextView direccion;
        public final TextView habitaciones;
        public final TextView precio;
        public final TextView tamanio;
        public final ImageView piso;
        public final ImageView check;
        public Casa mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            titulo = view.findViewById(R.id.titulo);
            descripcion = view.findViewById(R.id.descripction);
            direccion = view.findViewById(R.id.direccion);
            habitaciones = view.findViewById(R.id.habitaciones);
            precio = view.findViewById(R.id.precio);
            tamanio = view.findViewById(R.id.tamanio);
            piso = view.findViewById(R.id.piso);
            check = view.findViewById(R.id.check);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
