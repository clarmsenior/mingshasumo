package com.example.mayn.qingju.shop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mayn.qingju.R;
import com.example.mayn.qingju.shop.adapter.ShopAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 商城界面
 * Created by tsh on 2017/10/24.
 */

public class ShopFragment extends Fragment {


    @InjectView(R.id.tv_title_left)
    TextView tvTitleLeft;
    @InjectView(R.id.iv_title_left)
    ImageView ivTitleLeft;
    @InjectView(R.id.view_left)
    RelativeLayout viewLeft;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.et_title)
    EditText etTitle;
    @InjectView(R.id.view_title)
    RelativeLayout viewTitle;
    @InjectView(R.id.tv_title_right)
    ImageView tvTitleRight;
    @InjectView(R.id.iv_title_right)
    ImageView ivTitleRight;
    @InjectView(R.id.view_right)
    RelativeLayout viewRight;
    @InjectView(R.id.ln_title)
    LinearLayout lnTitle;
    //private TextView mTextView;
    private RecyclerView recyshop;
    private List<String> picList;
    private List<Map<String, Object>> channelList;
    private List<Integer> girlList;
    private List<String> normalList;
    private ShopAdapter adapter;
    int mDistance = 0;
    int maxDistance = 255;//当距离在[0,255]变化时，透明度在[0,255之间变化]
    int alpha = 0;

    public ShopFragment() {
    }

    public static ShopFragment newInstance() {

        Bundle args = new Bundle();
        args.putString("text", "商城");
        ShopFragment fragment = new ShopFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        ButterKnife.inject(this, view);
        etTitle.setInputType(InputType.TYPE_NULL);
        initViews(view);
        initData();

        //lyTopBar.getBackground().setAlpha(alpha);
        RecyclerView.OnScrollListener onScrollListener = ClickListener();
        recyshop.addOnScrollListener(onScrollListener);

        return view;
    }


    private void initViews(View view) {
        //mTextView = view.findViewById(R.id.tv_shop);
        recyshop = view.findViewById(R.id.recy_shop);

        picList = new ArrayList<>();
        channelList = new ArrayList<>();
        girlList = new ArrayList<>();
        normalList = new ArrayList<>();
        setSystemBarAlpha(0);
        //设置布局管理器
        GridLayoutManager layoutManage = new GridLayoutManager(getContext(), 2);
        layoutManage.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                return setSpanSize(position);
            }
        });
        recyshop.setLayoutManager(layoutManage);
        Bundle bundle = getArguments();
        // mTextView.setText(bundle == null ? "" : bundle.getString("text"));
    }

    private int setSpanSize(int position) {
        int count;
        if (position == 0) {
            count = 2;
        } else if (position == 1) {
            count = 2;
        } else {
            count = 1;
        }

        return count;
    }

    private void initData() {//初始化数据
        //轮播图需要的图片地址
        String picPath = "http://fdfs.xmcdn.com/group27/M04/D4/24/wKgJW1j11VzTmYOeAAG9Mld0icA505_android_large.jpg";
        String picPath1 = "http://fdfs.xmcdn.com/group27/M0A/D4/81/wKgJR1j13gKTWVXaAALwrIB1AVY346_android_large.jpg";
        String picPath2 = "http://fdfs.xmcdn.com/group26/M05/D8/97/wKgJRlj13vqRHDmVAASRJaudX3I424_android_large.jpg";
        picList.add(picPath);
        picList.add(picPath1);
        picList.add(picPath2);

        for (int i = 0; i < 6; i++) {
            girlList.add(R.mipmap.ic_launcher);
        }
        for (int i = 0; i < 20; i++) {
            normalList.add("正常布局" + i);
        }

        adapter = new ShopAdapter(getContext(), picList, girlList, normalList);
        recyshop.setAdapter(adapter);

    }

    private RecyclerView.OnScrollListener ClickListener() {

        RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
            //dy:每一次竖直滑动增量 向下为正 向上为负
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mDistance += dy;
                float percent = mDistance * 1f / maxDistance;//百分比
                alpha = (int) (percent * 255);//得到当前应当设置给标题栏的透明度
                setSystemBarAlpha(alpha);
            }
        };
        return mOnScrollListener;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    /**
     * 设置标题栏背景透明度
     *
     * @param alpha 透明度
     */
    private void setSystemBarAlpha(int alpha) {
        if (alpha >= 255) {
            alpha = 255;
        } else {
            //标题栏渐变。a:alpha透明度 r:红 g：绿 b蓝
//        titlebar.setBackgroundColor(Color.rgb(57, 174, 255));//没有透明效果
//        titlebar.setBackgroundColor(Color.argb(alpha, 57, 174, 255));//透明效果是由参数1决定的，透明范围[0,255]
            lnTitle.getBackground().setAlpha(alpha);
        }
    }

}
