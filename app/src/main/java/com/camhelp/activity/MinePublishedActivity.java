package com.camhelp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.camhelp.R;
import com.camhelp.adapter.MinePublishedAdapter;
import com.camhelp.common.CommonGlobal;
import com.camhelp.entity.CommonProperty;
import com.camhelp.entity.User;
import com.camhelp.utils.FullyLinearLayoutManager;
import com.camhelp.utils.L;
import com.camhelp.utils.MyLinearLayoutManager;

import org.litepal.crud.DataSupport;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 我发布的内容activity
 * */
public class MinePublishedActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = "MinePublishedActivity";
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private String colorPrimary, colorPrimaryBlew, colorPrimaryDark, colorAccent;
    User mUser = new User();

    private CircleImageView cimg_mine_avatar;

    MinePublishedAdapter minePublishedAdapter;
    private List<CommonProperty> commonPropertyList;
    private RecyclerView recycler_mine_published;
    private LinearLayout ll_nodata,ll_recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_published);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        mUser = getUser();
        initcolor();
        inittitle();
        initdata();
        initview();
//        recycler_mine_published.setLayoutManager(mLinearLayoutManager);
        recycler_mine_published.setLayoutManager(new FullyLinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
        recycler_mine_published.setNestedScrollingEnabled(false);

        minePublishedAdapter = new MinePublishedAdapter(commonPropertyList, this);
        recycler_mine_published.setAdapter(minePublishedAdapter);
    }

    /*获取主题色*/
    public void initcolor() {
        String defaultColorPrimary = "#" + Integer.toHexString(getResources().getColor(R.color.colorPrimary));
        String defaultColorPrimaryBlew = "#" + Integer.toHexString(getResources().getColor(R.color.colorPrimaryBlew));
        String defaultColorPrimaryDark = "#" + Integer.toHexString(getResources().getColor(R.color.colorPrimaryDark));
        String defaultColorAccent = "#" + Integer.toHexString(getResources().getColor(R.color.colorAccent));

        colorPrimary = pref.getString(CommonGlobal.colorPrimary, defaultColorPrimary);
        colorPrimaryBlew = pref.getString(CommonGlobal.colorPrimaryBlew, defaultColorPrimaryBlew);
        colorPrimaryDark = pref.getString(CommonGlobal.colorPrimaryDark, defaultColorPrimaryDark);
        colorAccent = pref.getString(CommonGlobal.colorAccent, defaultColorAccent);
    }

    public void inittitle() {
        String nickname = mUser.getNickname();//昵称
        String photoBg = mUser.getBgpicture();//背景
        String photoAvatar = mUser.getAvatar();//头像
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        ImageView fruitImageView = (ImageView) findViewById(R.id.fruit_image_view);
        cimg_mine_avatar = (CircleImageView) findViewById(R.id.cimg_mine_avatar);
        cimg_mine_avatar.bringToFront();//把头像放在最上层不被标题栏挡住（失败！）
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle(nickname);

        Glide.with(this).load(photoBg)
                .error(R.drawable.mine_bg)
                .placeholder(R.drawable.mine_bg)
                .into(fruitImageView);
        Glide.with(this).load(photoAvatar)
                .error(R.drawable.avatar)
                .placeholder(R.drawable.avatar)
                .into(cimg_mine_avatar);
    }

    public void initview() {
        ll_nodata = (LinearLayout) findViewById(R.id.ll_nodata);
        ll_recyclerView = (LinearLayout) findViewById(R.id.ll_recyclerView);
        ll_recyclerView.bringToFront();

        recycler_mine_published = (RecyclerView) findViewById(R.id.recycler_mine_published);

        if (commonPropertyList.size()==0){
            ll_nodata.setVisibility(View.VISIBLE);
        }
    }

    public void initdata(){
        commonPropertyList = DataSupport.findAll(CommonProperty.class);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        }
    }

    public User getUser() {
        String temp = pref.getString(CommonGlobal.userobj, "");
        L.d(TAG, temp);
        ByteArrayInputStream bais = new ByteArrayInputStream(Base64.decode(temp.getBytes(), Base64.DEFAULT));
        User user = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(bais);
            user = (User) ois.readObject();
        } catch (IOException e) {
            L.d(TAG, e.toString());
        } catch (ClassNotFoundException e1) {
            L.d(TAG, e1.toString());
        }
        return user;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
