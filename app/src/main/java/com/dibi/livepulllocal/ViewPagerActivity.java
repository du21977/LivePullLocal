package com.dibi.livepulllocal;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dibi.livepulllocal.bean.AllUrlBean;
import com.dibi.livepulllocal.dialog.LoadingDialog;
import com.dibi.livepulllocal.dialog.MyAlertDialog;
import com.dibi.livepulllocal.fragment.TestFm;
import com.dibi.livepulllocal.view.NoPreloadViewPager;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {

    private static final String TAG = "ViewPagerActivity" ;
    private AllUrlBean allUrlBean;

    private NoPreloadViewPager vp;
    TextView tv_add_video_url ;
    private List<String> contentList = new ArrayList<String>(); //内容链表
    private List<TestFm> fragmentList = new ArrayList<TestFm>(); //碎片链表

    private int currentPosition;

    TextView tv_num;
    TextView tv_num1;
    TextView tv_num2;
    TextView tv_num3;
    TextView tv_num4;

    TextView tv_setup;
    ImageView iv_setup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉Activity上面的状态栏
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.activity_view_pager);
        vp = (NoPreloadViewPager) findViewById(R.id.viewPager);
        tv_num = (TextView) findViewById(R.id.tv_num);
        tv_num1 = (TextView) findViewById(R.id.tv_num1);
        tv_num2 = (TextView) findViewById(R.id.tv_num2);
        tv_num3 = (TextView) findViewById(R.id.tv_num3);
        tv_num4 = (TextView) findViewById(R.id.tv_num4);
        tv_setup = (TextView) findViewById(R.id.tv_setup);
        iv_setup = (ImageView) findViewById(R.id.iv_setup);

        vp.setOnPageChangeListener(new NoPreloadViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //tv_num.setText(((position+1)/allUrlBean.getData().size())+"");
                Log.e("-------",position+"");
                Log.e("-------",positionOffsetPixels+"");
            }

            @Override
            public void onPageSelected(int position) {
                currentPosition = position;
                tv_num.setText(((position+1)+"/"+allUrlBean.getData().size())+"");
                vp.setCurrentItem(position);
                if(position==0){
                    tv_num1.setTextColor(Color.parseColor("#ff0000"));
                    tv_num2.setTextColor(Color.parseColor("#ffffff"));
                    tv_num3.setTextColor(Color.parseColor("#ffffff"));
                    tv_num4.setTextColor(Color.parseColor("#ffffff"));
                }else if(position==1){
                    tv_num1.setTextColor(Color.parseColor("#ffffff"));
                    tv_num2.setTextColor(Color.parseColor("#ff0000"));
                    tv_num3.setTextColor(Color.parseColor("#ffffff"));
                    tv_num4.setTextColor(Color.parseColor("#ffffff"));
                }else if(position==2){
                    tv_num1.setTextColor(Color.parseColor("#ffffff"));
                    tv_num2.setTextColor(Color.parseColor("#ffffff"));
                    tv_num3.setTextColor(Color.parseColor("#ff0000"));
                    tv_num4.setTextColor(Color.parseColor("#ffffff"));
                }else if(position==3){
                    tv_num1.setTextColor(Color.parseColor("#ffffff"));
                    tv_num2.setTextColor(Color.parseColor("#ffffff"));
                    tv_num3.setTextColor(Color.parseColor("#ffffff"));
                    tv_num4.setTextColor(Color.parseColor("#ff0000"));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tv_num1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vp.setCurrentItem(0,false);
            }
        });
        tv_num2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vp.setCurrentItem(1,false);
            }
        });
        tv_num3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vp.setCurrentItem(2,false);
            }
        });
        tv_num4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vp.setCurrentItem(3,false);
            }
        });

        iv_setup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(ViewPagerActivity.this,"设置",Toast.LENGTH_LONG).show();
                setupDialog();
            }
        });

        //添加数据
        initData();
    }

    /**
     * 查询数据---添加数据
     */
    private void initData() {


    }



    /**
     * 万能的Dialog-----设置
     */
    MyAlertDialog setupDialog;
    private void setupDialog() {
        setupDialog = new MyAlertDialog.Builder(this)
                .setContentView(R.layout.alertdialog_setup)
                .setCancelable(true)
                .formLeft(true)
                .fullHeight()
                .show();
        //.formBottom(true).fullWidth().show();
//        Button button1 = niubiDialog.getView(R.id.btn_common_1);
//        Button button2 = niubiDialog.getView(R.id.btn_common_2);

//        final TextView et_yuming = setupDialog.getView(R.id.tv_webview);

//        EditText et_yuming = setupDialog.getView(R.id.et_yuming);
//        et_yuming.setText(GlobalContants.SERVER_URL);

        //跑到webview去修改数据库
        setupDialog.setOnclickListener(R.id.tv_webview, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setupDialog.dismiss();
                LoadingDialog.showDialogForLoading(ViewPagerActivity.this);
                startActivity(new Intent(ViewPagerActivity.this,Gao2Activity.class));
                LoadingDialog.cancelDialogForLoading();
                finish();


            }
        });

        //修改域名
        setupDialog.setOnclickListener(R.id.tv_yuming, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setupDialog.dismiss();
                //yumingDialog11();
            }
        });

        //查看视频地址
        setupDialog.setOnclickListener(R.id.tv_dizhi, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setupDialog.dismiss();
                if(allUrlBean.getData() ==null|| allUrlBean.getData().size()==0){
                    Toast.makeText(ViewPagerActivity.this,"没有视频哦",Toast.LENGTH_LONG).show();
                    Log.e("----------------","-----------");
                   // showNoDialog();
                }else {
                    //niubiAlertDialog();
                }


            }
        });

    }

}
