package com.example.mayn.qingju.cashback;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mayn.qingju.R;

/**
 * 返现界面
 * Created by tsh on 2017/10/24.
 */

public class CashbackFragment extends Fragment {

    private TextView mTextView;

    public CashbackFragment() {
    }

    public static CashbackFragment newInstance() {

        Bundle args = new Bundle();
        args.putString("text", "返现");
        CashbackFragment fragment = new CashbackFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cashback, container, false);

        initViews(view);

        return view;
    }

    private void initViews(View view) {
        mTextView = view.findViewById(R.id.tv_cashback);
        Bundle bundle = getArguments();
        mTextView.setText(bundle == null ? "" : bundle.getString("text"));
    }
}
