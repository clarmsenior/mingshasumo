package com.example.mayn.qingju;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mayn.qingju.assignment.AssignmentFragment;
import com.example.mayn.qingju.base.BaseActivity;
import com.example.mayn.qingju.cashback.CashbackFragment;
import com.example.mayn.qingju.mine.MineFragment;
import com.example.mayn.qingju.shop.ShopFragment;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    public FrameLayout fragmentcontainer;//中间布局容器
    public RadioGroup rdgroup;           //底部导航栏容器
    public RadioButton rdmenumine;       //我的
    public RadioButton rdmenucashback;   //返现
    public RadioButton rdmenuassignment; //转让
    public RadioButton rdmenushop;       //商城
    public RelativeLayout lytopbar;      //topbar信息栏容器
    public TextView txttopbar;           //topbar信息栏
    public ShopFragment shopFragment;
    public AssignmentFragment assignmentFragment;
    public CashbackFragment cashbackFragment;
    MineFragment mineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindView();
        initView();
    }

    public void bindView() {
        fragmentcontainer = (FrameLayout) findViewById(R.id.fragment_container);
        rdgroup = (RadioGroup) findViewById(R.id.rd_group);
        rdmenumine = (RadioButton) findViewById(R.id.rd_menu_mine);
        rdmenucashback = (RadioButton) findViewById(R.id.rd_menu_cashback);
        rdmenuassignment = (RadioButton) findViewById(R.id.rd_menu_assignment);
        rdmenushop = (RadioButton) findViewById(R.id.rd_menu_shop);
        rdgroup.setOnCheckedChangeListener(this);
        rdmenushop.setChecked(true);

    }

    public void initView(){

        Drawable shop = getResources().getDrawable(R.drawable.tab_menu_deal);
        Drawable assignment = getResources().getDrawable(R.drawable.tab_menu_assignment);
        Drawable cashback = getResources().getDrawable(R.drawable.tab_menu_cashback);
        Drawable mine = getResources().getDrawable(R.drawable.tab_menu_mine);
        assignment.setBounds(0,0,50,50);
        shop.setBounds(0,0,50,50);
        cashback.setBounds(0,0,50,50);
        mine.setBounds(0,0,50,50);
        rdmenushop.setCompoundDrawables(null, shop, null, null);
        rdmenuassignment.setCompoundDrawables(null, assignment, null, null);
        rdmenucashback.setCompoundDrawables(null, cashback, null, null);
        rdmenumine.setCompoundDrawables(null, mine, null, null);
    }

    public void hideAllFragment(FragmentTransaction transaction) {
        if (shopFragment != null) {
            transaction.hide(shopFragment);
        }
        if (assignmentFragment != null) {
            transaction.hide(assignmentFragment);
        }
        if (cashbackFragment != null) {
            transaction.hide(cashbackFragment);
        }
        if (mineFragment != null) {
            transaction.hide(mineFragment);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        switch (checkedId) {


            case R.id.rd_menu_shop:
                if (shopFragment == null) {
                    shopFragment = ShopFragment.newInstance();
                    transaction.add(R.id.fragment_container, shopFragment);
                } else {
                    transaction.show(shopFragment);
                }

                break;
            case R.id.rd_menu_assignment:
                if (assignmentFragment == null) {
                    assignmentFragment = AssignmentFragment.newInstance();
                    transaction.add(R.id.fragment_container, assignmentFragment);
                } else {
                    transaction.show(assignmentFragment);
                }
                break;
            case R.id.rd_menu_cashback:
                if (cashbackFragment == null) {
                    cashbackFragment = CashbackFragment.newInstance();
                    transaction.add(R.id.fragment_container, cashbackFragment);
                } else {
                    transaction.show(cashbackFragment);
                }
                break;
            case R.id.rd_menu_mine:
                if (mineFragment == null) {
                    mineFragment = MineFragment.newInstance();
                    transaction.add(R.id.fragment_container, mineFragment);
                } else {
                    transaction.show(mineFragment);
                }
                break;
        }
        transaction.commit();
    }


}
