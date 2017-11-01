package com.example.mayn.qingju.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mayn.qingju.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.List;
import java.util.Map;

/**
 * Created by mayn on 2017/10/24.
 */

public class ShopAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<String> picList;
    private List<Integer> girlList;
    private List<String> normalList;
    private final int BANNER_VIEW_TYPE = 0;//轮播图
    private final int CHANNEL_VIEW_TYPE = 1;//网格
    private final int NORMAL_VIEW_TYPE = 2;//正常布局

    public ShopAdapter(Context context, List<String> picList, List<Integer> girlList, List<String> normalList) {
        this.context = context;
        this.picList = picList;
        this.girlList = girlList;
        this.normalList = normalList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view;
        if (viewType == BANNER_VIEW_TYPE) {//如果viewType是轮播图就去创建轮播图的viewHolder
            view = getView(R.layout.banner_shop);
            MyBannerViewHolder bannerHolder = new MyBannerViewHolder(view);
            return bannerHolder;
        } else if (viewType == CHANNEL_VIEW_TYPE) {//网格的type
            view = getView(R.layout.shop_gridcenter);
            return new GridViewHolder(view);
        } else {//正常list
            view = getView(R.layout.shop_list_normal);
            RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(lp);
            return new NormalHolder(view);
        }


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyBannerViewHolder) {//轮播图
            MyBannerViewHolder bannerHolder = (MyBannerViewHolder) holder;
            //调用设置轮播图相关方法
            setBanner(bannerHolder);
        }  else if (holder instanceof GridViewHolder) {//网格
            GridViewHolder girlHolder = (GridViewHolder) holder;


        } else if (holder instanceof NormalHolder) {//正常布局
            NormalHolder normalHolder = (NormalHolder) holder;
            normalHolder.phonename.setText(normalList.get(position - 2));
            normalHolder.phoneprice.setText(normalList.get(position - 2));

        }

    }

    /**
     * 获取item的类型
     *
     * @param position item的位置
     * @return item的类型
     * 有几种type就回在onCreateViewHolder方法中引入几种布局,也就是创建几个ViewHolder
     */
    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return BANNER_VIEW_TYPE;
        } else if (position == 1) {
            return CHANNEL_VIEW_TYPE;
        } else
            return NORMAL_VIEW_TYPE;


    }


    @Override
    public int getItemCount() {
        return normalList.size() +2;
    }


    private View getView(int view) {
        View view1 = View.inflate(context, view, null);
        return view1;
    }
    private void setBanner(MyBannerViewHolder bannerHolder) {//设置轮播
        //设置banner样式
        bannerHolder.banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        bannerHolder.banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        bannerHolder.banner.setImages(picList);
        //设置banner动画效果
        bannerHolder.banner.setBannerAnimation(Transformer.Default);
        //设置标题集合（当banner样式有显示title时）
//            bannerHolder.banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        bannerHolder.banner.isAutoPlay(true);
        //设置轮播时间
//            bannerHolder.banner.setDelayTime(3500);
        //设置指示器位置（当banner模式中有指示器时）
        bannerHolder.banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        bannerHolder.banner.start();
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            //Glide 加载图片简单用法
            Glide.with(context).load(path).into(imageView);
        }
    }

    public static class MyBannerViewHolder extends RecyclerView.ViewHolder {//轮播图holder
        Banner banner;

        public MyBannerViewHolder(View itemView) {

            super(itemView);

            banner = (Banner) itemView.findViewById(R.id.banner);

        }
    }

    public static class GridViewHolder extends RecyclerView.ViewHolder {//网格的布局
        ImageView house;
        ImageView electrical;
        ImageView car;
        ImageView baihuo;
        ImageView yifu;
        ImageView water;
        ImageView zhubao;
        ImageView yule;

        public GridViewHolder(View itemView) {

            super(itemView);
            house = (ImageView) itemView.findViewById(R.id.iv_house);//房产
            electrical = (ImageView) itemView.findViewById(R.id.iv_electrical);//电器
            car = (ImageView) itemView.findViewById(R.id.iv_car);//汽车
            baihuo = (ImageView) itemView.findViewById(R.id.iv_baihuo);//百货
            yifu = (ImageView) itemView.findViewById(R.id.iv_yifu);//服装
            water = (ImageView) itemView.findViewById(R.id.iv_water);//酒水
            zhubao = (ImageView) itemView.findViewById(R.id.iv_zhubao);//珠宝
            yule = (ImageView) itemView.findViewById(R.id.iv_yule);//娱乐
        }
    }

    public static class NormalHolder extends RecyclerView.ViewHolder {
        ImageView recyiv;
        TextView phonename;
        TextView phoneprice;

        public NormalHolder(View itemView) {
            super(itemView);
            recyiv = (ImageView) itemView.findViewById(R.id.item_iv_recy);
            phonename = (TextView) itemView.findViewById(R.id.tv_phonename);//手机名字
            phoneprice = (TextView) itemView.findViewById(R.id.tv_phoneprice);//手机价格
        }
    }
}
