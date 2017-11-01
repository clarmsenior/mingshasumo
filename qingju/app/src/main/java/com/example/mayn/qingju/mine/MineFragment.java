package com.example.mayn.qingju.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mayn.qingju.R;

/**
 * 个人中心
 * Created by tsh on 2017/10/24.
 */

public class MineFragment extends Fragment {



    public static MineFragment newInstance() {

        Bundle args = new Bundle();
        args.putString("text", "我的");
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);

        initViews(view);
        return view;
    }

    private void initViews(View view) {
        //mTextView = view.findViewById(R.id.tv_mine);
        Bundle bundle = getArguments();
     // mTextView.setText(bundle == null ? "" : bundle.getString("text"));
    }
}
