package com.xiezhenqi.business.more.lazyload;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xiezhenqi.R;
import com.xiezhenqi.XZQApplication;
import com.xiezhenqi.base.fragments.LazyLoadFragment;
import com.xiezhenqi.business.account.ShareDialog;
import com.xiezhenqi.business.h5help.H5HelpActivity;
import com.xiezhenqi.business.more.MoreActivity;
import com.xiezhenqi.business.selectpic.BaseSelectPicActivity;
import com.xiezhenqi.utils.LogUtils;
import com.xiezhenqi.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * DFragment
 * Created by Tse on 2016/10/29.
 */

public class DFragment2 extends LazyLoadFragment implements Runnable {

    @BindView(R.id.account_siv_head)
    ImageView ivHead;

    @Override
    protected int getLayoutId(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_d;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        ButterKnife.bind(this, getView());
        if (getView() != null)
            getView().setVisibility(View.GONE);
    }

    @OnClick({
            R.id.account_rl_head,
            R.id.account_llyt_order,
            R.id.account_llyt_coupon,
            R.id.account_llyt_message,
            R.id.account_tv_share,
            R.id.account_tv_suggest})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.account_rl_head:
                startActivityForResult(new Intent(getActivity(), BaseSelectPicActivity.class), 0);
                break;
            case R.id.account_llyt_order:
                ToastUtils.showToast(getActivity(), "我的订单");
                break;
            case R.id.account_llyt_coupon:
                ToastUtils.showToast(getActivity(), "米星券");
                break;
            case R.id.account_llyt_message:
                MoreActivity.start(getActivity());
                break;
            case R.id.account_tv_share:
                new ShareDialog(getActivity()).show();
                break;
            case R.id.account_tv_suggest:
                H5HelpActivity.start(getActivity(), "https://www.baidu.com");
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && data != null) {
            Uri uri = BaseSelectPicActivity.getUri(data);
            ivHead.setImageBitmap(null);
            ivHead.setImageURI(uri);
        }
    }

    @Override
    protected void loadData() {
        LogUtils.debug("WISH", "我的 loadData");
        if (getView() != null)
            getView().postDelayed(this, 500);
    }

    @Override
    public void run() {
        if (getView() != null)
            getView().setVisibility(View.VISIBLE);
    }

    public static CharSequence getPageTitle() {
        return "我的";
    }

    public static Bitmap getNormalDrawable(Context context) {
        return BitmapFactory.decodeResource(XZQApplication.getContext().getResources(), R.drawable.ic_main_me_normal);
    }

    public static Bitmap getSelectedDrawable(Context context) {
        return BitmapFactory.decodeResource(XZQApplication.getContext().getResources(), R.drawable.ic_main_me_selected);
    }

}
