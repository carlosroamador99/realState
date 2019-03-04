package com.example.state4reals.ui.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.state4reals.R;
import com.example.state4reals.model.Casa;
import com.example.state4reals.retrofit.UtilToken;
import com.example.state4reals.retrofit.services.CasaInteractionListener;


import java.util.List;


public class MyCasasAdapter extends RecyclerView.Adapter<MyCasasAdapter.ViewHolder>{

    private List<Casa> mValues;
    private final CasaInteractionListener mListener;
    private Context ctx;

    public MyCasasAdapter(Context ctx, List<Casa> items, CasaInteractionListener listener, int layout) {
        mValues = items;
        mListener = listener;
        this.ctx = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_casas, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.titulo.setText(holder.mItem.getTitle());
        holder.descripcion.setText(holder.mItem.getDescription());
        holder.direccion.setText(holder.mItem.getAddress());
        holder.habitaciones.setText(Integer.toString(holder.mItem.getRoom()));
        holder.precio.setText(Integer.toString(holder.mItem.getPrice()));
        holder.tamanio.setText(Integer.toString(holder.mItem.getSize()));

        holder.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UtilToken.getToken(ctx) != null) {
                    mListener.onClickFav(holder.mItem.getId());
                    ColorFilter filter = new LightingColorFilter(Color.GREEN, Color.GREEN);
                    holder.check.setColorFilter(filter);
                } else {
                    Toast.makeText(ctx, "Logéate", Toast.LENGTH_LONG).show();
                }
            }
        });

        if (holder.mItem.getPhotos() != null) {
            Glide
                    .with(ctx)
                    .load(holder.mItem.getPhotos()[0])
                    .into(holder.piso);
        }
        //Esto cabia que cuando se de en una casa se lleve a mas detalles de la mismo
        //aun por modificar

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onClickView(holder.mItem.getId());
                }
            }
        });
    }

    public void setNuevasCasas(List<Casa> casas){
        this.mValues = casas;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mValues.size();
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

        public ViewHolder(final View view) {
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
