package com.example.state4reals.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.state4reals.R;
import com.example.state4reals.retrofit.UtilToken;
import com.example.state4reals.retrofit.services.UserInteractionListener;
import com.example.state4reals.ui.DashBoard;


public class UserFragment extends Fragment {

    private Button desButtogin, myCasas;
    private Fragment misCasas;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Context ctx = getActivity();
    private String username;
    private String email;
    private String avatar;
    private UserInteractionListener mListener;

    private TextView tvEmail, tvUserName;
    private ImageView ivAvatar;

    public UserFragment() {
        // Required empty public constructor
    }


    public static UserFragment newInstance(String param1, String param2) {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_user, container, false);
        desButtogin = view.findViewById(R.id.desloguearse);
        myCasas = view.findViewById(R.id.misPropiedades);

        username = UtilToken.getUsername(getActivity());
        email = UtilToken.getEmail(getActivity());
        avatar = UtilToken.getAvatar(getActivity());

        tvEmail = view.findViewById(R.id.email);
        tvUserName = view.findViewById(R.id.username);
        ivAvatar = view.findViewById(R.id.avatar);
        String token = UtilToken.getToken(ctx);
        if (token != null) {
            tvUserName.setText(username);
            tvEmail.setText(email);
            Glide
                    .with(ctx)
                    .load(avatar)
                    .into(ivAvatar);
        }
        myCasas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              misCasas =  new MyCasasFragment();


            }
        });

        desButtogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences  sharedPreferences= ctx.getSharedPreferences("login", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.remove("");
                editor.commit();
                Intent intent = new Intent(getActivity(), DashBoard.class);
                getActivity().startActivity(intent);
            }
        });
        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event

    public void changeFragment(){
        misCasas = new MyCasasFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.ctx = context;
        if (context instanceof UserInteractionListener) {
            mListener = (UserInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


}
