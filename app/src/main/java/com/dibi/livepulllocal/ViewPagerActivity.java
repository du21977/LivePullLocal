package com.dibi.livepulllocal;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dibi.livepulllocal.bean.AllUrlBean;
import com.dibi.livepulllocal.dialog.LoadingDialog;
import com.dibi.livepulllocal.dialog.MyAlertDialog;
import com.dibi.livepulllocal.entity.Gao;
import com.dibi.livepulllocal.entity.GaoId;
import com.dibi.livepulllocal.fragment.FragmentVPAdapter;
import com.dibi.livepulllocal.fragment.TestFm;
import com.dibi.livepulllocal.greendao.GaoDao;
import com.dibi.livepulllocal.greendao.GaoIdDao;
import com.dibi.livepulllocal.view.NoPreloadViewPager;

import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {

    private static final String TAG = "ViewPagerActivity" ;
  //  private AllUrlBean allUrlBean;

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

    private List<Gao> listsGao;
    private  List<GaoId> gaoIdList;
    private GaoDao dao;
    private GaoIdDao gaoIdDao;

    List<List<Gao>> lists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉Activity上面的状态栏
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.activity_view_pager);
        dao = GlobalApplication.getApplication().getDaoSession().getGaoDao();
        gaoIdDao =GlobalApplication.getApplication().getDaoSession().getGaoIdDao();
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
                tv_num.setText(((position+1)+"/"+lists.size())+"");
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

        //查询数据
        initData();
    }

    /**
     * 查询数据---添加数据
     */
    private void initData() {

        listsGao = dao.loadAll();
        gaoIdList = gaoIdDao.loadAll();
        Log.e("--全部id--",gaoIdList.toString());
        Log.e("--全部--",listsGao.toString());
//        Query query = dao.queryBuilder().where(UserDao.Properties.Name.eq(et_Name.getText().toString().trim())).build();
//        Toast.makeText(this, "查询的结果是：" + query.list().toString(), Toast.LENGTH_LONG).show();
        //dao.queryBuilder();

        for(int i = 0;i<gaoIdList.size();i++){
            List<Gao> gao1 = new ArrayList<>();
            for (int j = 0;j<listsGao.size();j++){

                if(((long)listsGao.get(j).getGid())==(gaoIdList.get(i).getId())){
                    gao1.add(listsGao.get(j));
                }
            }
            lists.add(gao1);
        }

        Log.e("--全部改造后--",lists.toString());

        //设置全局变量
        GlobalApplication.listsAll =lists;

        fragmentList = new ArrayList<TestFm>();
        for(int i=0;i<lists.size();i++){
            TestFm testFm = new TestFm().newInstance("hah", i);
            fragmentList.add(testFm);
        }
        vp.setAdapter(new FragmentVPAdapter(getSupportFragmentManager(), (ArrayList<TestFm>) fragmentList));


        //设置TM的标签
        if(lists.size()==1){
            tv_num1.setVisibility(View.VISIBLE);
            tv_num2.setVisibility(View.GONE);
            tv_num3.setVisibility(View.GONE);
            tv_num4.setVisibility(View.GONE);
            if(lists.get(0)!=null&&lists.get(0).size()>0){
                tv_num1.setText(lists.get(0).get(0).getName());
            }

        }else if(lists.size()==2){
            tv_num1.setVisibility(View.VISIBLE);
            tv_num2.setVisibility(View.VISIBLE);
            tv_num3.setVisibility(View.GONE);
            tv_num4.setVisibility(View.GONE);
            if(lists.get(0)!=null&&lists.get(0).size()>0){
                tv_num1.setText(lists.get(0).get(0).getName());
            }
            if(lists.get(1)!=null&&lists.get(1).size()>0){
                tv_num2.setText(lists.get(1).get(0).getName());
            }

        }else if(lists.size()==3){
            tv_num1.setVisibility(View.VISIBLE);
            tv_num2.setVisibility(View.VISIBLE);
            tv_num3.setVisibility(View.VISIBLE);
            tv_num4.setVisibility(View.GONE);
            if(lists.get(0)!=null&&lists.get(0).size()>0){
                tv_num1.setText(lists.get(0).get(0).getName());
            }
            if(lists.get(1)!=null&&lists.get(1).size()>0){
                tv_num2.setText(lists.get(1).get(0).getName());
            }
            if(lists.get(2)!=null&&lists.get(2).size()>0){
                tv_num3.setText(lists.get(2).get(0).getName());
            }


        }else if(lists.size()==4){
            tv_num1.setVisibility(View.VISIBLE);
            tv_num2.setVisibility(View.VISIBLE);
            tv_num3.setVisibility(View.VISIBLE);
            tv_num4.setVisibility(View.VISIBLE);
            if(lists.get(0)!=null&&lists.get(0).size()>0){
                tv_num1.setText(lists.get(0).get(0).getName());
            }
            if(lists.get(1)!=null&&lists.get(1).size()>0){
                tv_num2.setText(lists.get(1).get(0).getName());
            }
            if(lists.get(2)!=null&&lists.get(2).size()>0){
                tv_num3.setText(lists.get(2).get(0).getName());
            }
            if(lists.get(3)!=null&&lists.get(3).size()>0){
                tv_num4.setText(lists.get(3).get(0).getName());
            }

        }
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
                if(lists ==null|| lists.size()==0){
                    Toast.makeText(ViewPagerActivity.this,"没有视频哦",Toast.LENGTH_LONG).show();
                    Log.e("----------------","-----------");
                   // showNoDialog();
                }else {
                    niubiAlertDialog();
                }


            }
        });

    }


    MyAlertDialog niubiDialog;
    private void niubiAlertDialog() {
        niubiDialog = new MyAlertDialog.Builder(this)
                .setContentView(R.layout.alertdialog_commom_1)
                .show();
        //.formBottom(true).fullWidth().show();
//        Button button1 = niubiDialog.getView(R.id.btn_common_1);
//        Button button2 = niubiDialog.getView(R.id.btn_common_2);

        final EditText et_1 = niubiDialog.getView(R.id.et_1);
        final EditText et_2 = niubiDialog.getView(R.id.et_2);
        final EditText et_3 = niubiDialog.getView(R.id.et_3);
        final EditText et_4 = niubiDialog.getView(R.id.et_4);

        niubiDialog.setText(R.id.btn_common_1,"哈哈");

        if (lists !=null&& lists.size()>0){
            Log.e(TAG,lists.get(currentPosition).size()+"");
            if(lists.get(currentPosition).size()==3){
                et_1.setVisibility(View.VISIBLE);
                et_2.setVisibility(View.VISIBLE);
                et_3.setVisibility(View.VISIBLE);
                et_1.setText(lists.get(currentPosition).get(0).getPath());
                et_2.setText(lists.get(currentPosition).get(1).getPath());
                et_3.setText(lists.get(currentPosition).get(2).getPath());
                et_4.setVisibility(View.GONE);
            }else if(lists.get(currentPosition).size()>=4){
                et_1.setVisibility(View.VISIBLE);
                et_2.setVisibility(View.VISIBLE);
                et_3.setVisibility(View.VISIBLE);
                et_1.setText(lists.get(currentPosition).get(0).getPath());
                et_2.setText(lists.get(currentPosition).get(1).getPath());
                et_3.setText(lists.get(currentPosition).get(2).getPath());
                et_4.setVisibility(View.VISIBLE);
                et_4.setText(lists.get(currentPosition).get(3).getPath());
            }if(lists.get(currentPosition).size()==2){
                et_1.setVisibility(View.VISIBLE);
                et_2.setVisibility(View.VISIBLE);
                et_1.setText(lists.get(currentPosition).get(0).getPath());
                et_2.setText(lists.get(currentPosition).get(1).getPath());
                et_3.setVisibility(View.GONE);
                et_4.setVisibility(View.GONE);
            }if(lists.get(currentPosition).size()==1){
                et_1.setVisibility(View.VISIBLE);
                et_1.setText(lists.get(currentPosition).get(0).getPath());
                et_4.setVisibility(View.GONE);
                et_3.setVisibility(View.GONE);
                et_2.setVisibility(View.GONE);
            }
        }

    }

}
