package com.camhelp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.camhelp.R;
import com.camhelp.activity.ItemLookActivity;
import com.camhelp.common.CommonGlobal;
import com.camhelp.common.FindValueForID;
import com.camhelp.entity.CommonProperty;
import com.camhelp.entity.User;
import com.camhelp.utils.LookLargeImg;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by storm on 2017-08-09.
 */

public class HomeNewAndFocusAdapter extends RecyclerView.Adapter<HomeNewAndFocusAdapter.ViewHolder> {
    private List<CommonProperty> mList;
    private Context mContext;
    private FindValueForID findValueForID = new FindValueForID();
    private LookLargeImg lookLargeImg = new LookLargeImg();
    private User user = new User();

    public HomeNewAndFocusAdapter(List<CommonProperty> CommonPropertys, Context context) {
        mList = CommonPropertys;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_home_new_or_focus, parent, false);
        final HomeNewAndFocusAdapter.ViewHolder holder = new HomeNewAndFocusAdapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final CommonProperty commonProperty = mList.get(position);
        holder.dataBinding(commonProperty, position, mContext);

        /*每一项的点击事件*/
        holder.queryItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLook = new Intent(mContext, ItemLookActivity.class);
                intentLook.putExtra(CommonGlobal.commonProperty, commonProperty);
                mContext.startActivity(intentLook);
            }
        });
        /*分享*/
        holder.ll_publishfoot_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "share" + position, Toast.LENGTH_SHORT).show();
            }
        });
        /*评论*/
        holder.ll_publishfoot_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLook = new Intent(mContext, ItemLookActivity.class);
                intentLook.putExtra("commonProperty", commonProperty);
                mContext.startActivity(intentLook);
            }
        });
        /*喜欢*/
        holder.ll_publishfoot_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        /*收藏*/
        holder.ll_publishfoot_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "collect" + position, Toast.LENGTH_SHORT).show();
            }
        });
        /*查看大图*/
        holder.item_iv_pic1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imgurl = commonProperty.getCommonPic1();
                lookLargeImg.looklargeimg(imgurl, mContext);
            }
        });
        holder.item_iv_pic2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imgurl = commonProperty.getCommonPic2();
                lookLargeImg.looklargeimg(imgurl, mContext);
            }
        });
        holder.item_iv_pic3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imgurl = commonProperty.getCommonPic3();
                lookLargeImg.looklargeimg(imgurl, mContext);
            }
        });
        holder.item_iv_pic4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imgurl = commonProperty.getCommonPic4();
                lookLargeImg.looklargeimg(imgurl, mContext);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View queryItemView;
        ImageView item_top_iv_avatar;//头像
        TextView item_top_tv_nickname,item_top_tv_createtime,item_top_iv_type;//昵称,发布时间,类型
        TextView item_tv_intro,item_foot_praisenum;//标题或简介或详情,热度（点赞量）
        LinearLayout ll_publishfoot_share, ll_publishfoot_comment, ll_publishfoot_like,ll_publishfoot_collect;
        ImageView item_iv_pic1, item_iv_pic2, item_iv_pic3, item_iv_pic4;

        public ViewHolder(View itemView) {
            super(itemView);
            queryItemView = itemView;
            item_top_iv_avatar = (ImageView) itemView.findViewById(R.id.item_top_iv_avatar);
            item_top_tv_nickname = (TextView) itemView.findViewById(R.id.item_top_tv_nickname);
            item_top_tv_createtime = (TextView) itemView.findViewById(R.id.item_top_tv_createtime);
            item_tv_intro = (TextView) itemView.findViewById(R.id.item_tv_intro);
            item_top_iv_type = (TextView) itemView.findViewById(R.id.item_top_iv_type);
            item_foot_praisenum = (TextView) itemView.findViewById(R.id.item_foot_praisenum);
            ll_publishfoot_share = (LinearLayout) itemView.findViewById(R.id.ll_publishfoot_share);
            ll_publishfoot_comment = (LinearLayout) itemView.findViewById(R.id.ll_publishfoot_comment);
            ll_publishfoot_like = (LinearLayout) itemView.findViewById(R.id.ll_publishfoot_like);
            ll_publishfoot_collect = (LinearLayout) itemView.findViewById(R.id.ll_publishfoot_collect);
            item_iv_pic1 = (ImageView) itemView.findViewById(R.id.item_iv_pic1);
            item_iv_pic2 = (ImageView) itemView.findViewById(R.id.item_iv_pic2);
            item_iv_pic3 = (ImageView) itemView.findViewById(R.id.item_iv_pic3);
            item_iv_pic4 = (ImageView) itemView.findViewById(R.id.item_iv_pic4);
        }

        public void dataBinding(final CommonProperty mCommonProperty, final int position, Context context) {
            String stitle = mCommonProperty.getCommonTitle();
            String sintro = mCommonProperty.getCommonIntro();
            String scontent = mCommonProperty.getCommonContent();
            int type = mCommonProperty.getCategoryType();
            String pic1 = mCommonProperty.getCommonPic1();
            String pic2 = mCommonProperty.getCommonPic2();
            String pic3 = mCommonProperty.getCommonPic3();
            String pic4 = mCommonProperty.getCommonPic4();

            int userId = mCommonProperty.getUserId();
            user.setUserID(userId);
            item_top_tv_nickname.setText(""+userId);

            /*设置显示文字，标题不为空显示标题，否则显示简介，否则显示详情，否则隐藏*/
            if (stitle != null && !"".equals(stitle)) {
                item_tv_intro.setText(stitle);
            } else if (sintro != null && !"".equals(sintro)) {
                item_tv_intro.setText(sintro);
            } else if (scontent != null && !"".equals(scontent)) {
                item_tv_intro.setText(scontent);
            } else {
                item_tv_intro.setVisibility(View.GONE);
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sCreatetime = sdf.format(mCommonProperty.getCreatetime());
            item_top_tv_createtime.setText(sCreatetime);

            item_top_iv_type.setText(findValueForID.findCategoryType(type));

            if (pic1 != null) {
                Glide.with(context).load(pic1).into(item_iv_pic1);
            } else {
                item_iv_pic1.setVisibility(View.GONE);
            }
            if (pic2 != null) {
                Glide.with(context).load(pic2).into(item_iv_pic2);
            } else {
                item_iv_pic2.setVisibility(View.GONE);
            }
            if (pic3 != null) {
                Glide.with(context).load(pic3).into(item_iv_pic3);
            } else {
                item_iv_pic3.setVisibility(View.GONE);
            }
            if (pic4 != null) {
                Glide.with(context).load(pic4).into(item_iv_pic4);
            } else {
                item_iv_pic4.setVisibility(View.GONE);
            }

            item_foot_praisenum.setText(""+mCommonProperty.getPraisenum()+"条热度");
        }
    }
}