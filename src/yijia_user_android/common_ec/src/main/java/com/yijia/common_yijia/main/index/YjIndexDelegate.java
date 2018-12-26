package com.yijia.common_yijia.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.latte.delegates.bottom.BottomItemDelegate;
import com.example.latte.ec.R;
import com.example.latte.ec.R2;
import com.example.latte.ec.main.EcBottomDelegate;
import com.example.latte.ec.main.index.IndexDataConverter;
import com.example.latte.ec.main.index.IndexItemClickListener;
import com.example.latte.ec.main.index.search.SearchDelegate;
import com.example.latte.net.RestCreator;
import com.example.latte.net.rx.BaseObserver;
import com.example.latte.net.rx.RxRestClient;
import com.example.latte.ui.recycler.BaseDecoration;
import com.example.latte.ui.recycler.MultipleItemEntity;
import com.example.latte.ui.refresh.RefreshHandler;
import com.example.latte.util.callback.CallbackManager;
import com.example.latte.util.callback.CallbackType;
import com.example.latte.util.callback.IGlobalCallback;
import com.example.latte.util.log.LatteLogger;
import com.joanzapata.iconify.widget.IconTextView;
import com.yijia.common_yijia.database.YjDatabaseManager;
import com.yijia.common_yijia.database.YjUserProfile;
import com.yijia.common_yijia.database.YjUserProfileDao;
import com.yijia.common_yijia.sign.YjBottomDelegate;
import com.yijia.common_yijia.sign.YjSignHandler;

import java.util.ArrayList;
import java.util.WeakHashMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class YjIndexDelegate extends BottomItemDelegate implements View.OnFocusChangeListener, IFriendsItemListener {

    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.rv_friends)
    RecyclerView mFriendsRecyclerView = null;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout = null;
    @BindView(R2.id.tb_index)
    Toolbar mToolbar = null;
    @BindView(R2.id.icon_index_scan)
    IconTextView mIconScan = null;

    private RefreshHandler mRefreshHandler = null;

    IndexFriendsAdapter friendsAdapter = null;

    @OnClick(R2.id.icon_index_message)
    void onCLickpublish() {

    }


    @OnClick(R2.id.icon_index_scan)
    void onCLickScanOrCode() {
        startScanWithCheck(this.getParentDelegate());
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final String url = "friend/query_friends";
        String token = YjDatabaseManager.getInstance().getDao().loadAll().get(0).getYjtk();
        RxRestClient.builder()
                .url(url)
                .params("yjtk", token)
                .build()
                .post()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<String>(getContext()) {
                    @Override
                    public void onResponse(String response) {
                        LatteLogger.json("USER_FRIENDS", response);
                        final String status = JSON.parseObject(response).getString("status");
                        if (TextUtils.equals(status, "1001")) {
                            getFriendsSucceed(response);
                        } else {
                            final String msg = JSON.parseObject(response).getString("msg");
                            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFail(Throwable e) {
                        Toast.makeText(getContext(), "请稍后尝试", Toast.LENGTH_SHORT).show();
                    }
                });




        mRefreshHandler = RefreshHandler.create(mRefreshLayout, mRecyclerView, new IndexDataConverter());
        CallbackManager.getInstance()
                .addCallback(CallbackType.ON_SCAN, new IGlobalCallback<String>() {
                    @Override
                    public void executeCallback(@Nullable String args) {
                        Toast.makeText(getContext(), args, Toast.LENGTH_LONG).show();
                    }
                });

        onCallRxRestClient();
    }

    private void getFriendsSucceed(String response) {
        final ArrayList<MultipleItemEntity> data =
                new YjIndexFriendsDataConverter()
                        .setJsonData(response)
                        .convert();
        friendsAdapter = new IndexFriendsAdapter(data);
        friendsAdapter.setCartItemListener(this);
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        //调整RecyclerView的排列方向
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mFriendsRecyclerView.setLayoutManager(manager);
        mFriendsRecyclerView.setAdapter(friendsAdapter);
    }

    //TODO 测试方法
    void onCallRxGet() {

        final String url = "index.php";
        final WeakHashMap<String, Object> params = new WeakHashMap<>();

        final Observable<String> observable = RestCreator.getRxRestService().get(url, params);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //TODO测试方法*2
    private void onCallRxRestClient() {
        final String url = "index.php";
        RxRestClient.builder()
                .url(url)
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light

        );
        //第一个参数true：下拉的时候球由小变大，回弹时由大变小 第二个参数下降起始高度  第三个参数下降终止的高度
        mRefreshLayout.setProgressViewOffset(true, 120, 300);
    }

    private void initRecyclerView() {
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration
                (BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.app_background), 5));
        final YjBottomDelegate yjBottomDelegate = getParentDelegate();
        mRecyclerView.addOnItemTouchListener(IndexItemClickListener.create(yjBottomDelegate));
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        initRecyclerView();
        mRefreshHandler.firstPage("index.php");
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index_yijia;
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            getParentDelegate().getSupportDelegate().start(new SearchDelegate());
        }
    }

    @Override
    public void onFriendsItemClick(Long id) {
        if (id == 0) {
            //TODO 邀请
        } else {
            //TODO IM
        }
    }
}
