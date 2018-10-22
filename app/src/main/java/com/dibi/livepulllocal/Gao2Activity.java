package com.dibi.livepulllocal;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dibi.livepulllocal.dialog.MyAlertDialog;
import com.dibi.livepulllocal.entity.Gao;
import com.dibi.livepulllocal.entity.GaoId;
import com.dibi.livepulllocal.entity.User;
import com.dibi.livepulllocal.greendao.GaoDao;
import com.dibi.livepulllocal.greendao.GaoIdDao;
import com.dibi.livepulllocal.greendao.UserDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

public class Gao2Activity extends AppCompatActivity {

    ListView listview;
    TextView tv_add_insert;
    private List<Gao> listsGao;
    private  List<GaoId> gaoIdList;
    private GaoDao dao;
    private GaoIdDao gaoIdDao;
    GaoAdapter gaoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.activity_gao2);
        listview = (ListView) findViewById(R.id.listview);
        tv_add_insert = (TextView) findViewById(R.id.tv_add_insert);

        dao = GlobalApplication.getApplication().getDaoSession().getGaoDao();
        gaoIdDao =GlobalApplication.getApplication().getDaoSession().getGaoIdDao();
        listsGao = new ArrayList<>();
        //listsGao = dao.loadAll();
        listsGao = queryGaoList();
        gaoIdList = gaoIdDao.loadAll();
         gaoAdapter = new GaoAdapter();
        listview.setAdapter(gaoAdapter);

//        GaoId gaoId = new GaoId(null,"组名1");
//        GaoId gaoId = new GaoId(null,"组名2");
//        GaoId gaoId = new GaoId(null,"组名3");
//        gaoIdDao.insert(gaoId);
        tv_add_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                gaoIdList = gaoIdDao.loadAll();
//
////                GaoId gaoId = new GaoId(null,"组名1");
////                Gao gaoInsertData = new Gao(null,"http://mpv.videocc.net/692c80f137/a/692c80f137eba8ee04a41d58ee585a1a_2.mp4?pid=1537543084757X1274431","组名1",(long)(gaoIdList.size()+1));
////                GaoId gaoId = new GaoId(null,"组名2");
////                Gao gaoInsertData = new Gao(null,"http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4","组名2",(long)(gaoIdList.size()+1));
////                GaoId gaoId = new GaoId(null,"组名3");
////                Gao gaoInsertData = new Gao(null,"http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4","组名3",(long)(gaoIdList.size()+1));
//
////                Gao gaoInsertData = new Gao(null,"http://mpv.videocc.net/692c80f137/a/692c80f137eba8ee04a41d58ee585a1a_2.mp4?pid=1537543084757X1274431","组名1",(long)1);
////
////                Gao gaoInsertData = new Gao(null,"http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4","组名2",(long)2);
////
//                Gao gaoInsertData = new Gao(null,"http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4","组名3",(long)3);
//
//
//                dao.insert(gaoInsertData);
//                listsGao = dao.loadAll();
//                gaoAdapter.notifyDataSetChanged();

                setupDialog();
            }
        });

    }


    /**
     * 万能的Dialog-----添加拉流地址
     */
    MyAlertDialog addAddressDialog;
    private void setupDialog() {
        addAddressDialog = new MyAlertDialog.Builder(this)
                .setContentView(R.layout.alertdialog_commom)
                .setCancelable(true)
                .show();
        final EditText et_1 = addAddressDialog.getView(R.id.et_1);
        final EditText et_2 = addAddressDialog.getView(R.id.et_2);
        final EditText et_3 = addAddressDialog.getView(R.id.et_3);
        final EditText et_4 = addAddressDialog.getView(R.id.et_4);
        final EditText et_name = addAddressDialog.getView(R.id.et_name);//组名


        addAddressDialog.setOnclickListener(R.id.tv_dialog_add, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_name.getText().toString().trim().equals("")&&et_1.getText().toString().trim().equals("")&&et_2.getText().toString().trim().equals("")&&et_3.getText().toString().trim().equals("")&&et_4.getText().toString().trim().equals("")){
                    Toast.makeText(Gao2Activity.this,"组名或拉流地址不能为空",Toast.LENGTH_LONG).show();
                    return;
                }

                List<Gao> gaoList = new ArrayList<Gao>();
                if(!et_1.getText().toString().trim().equals("")){
                    Gao gao1 = new Gao(null,et_1.getText().toString().trim(),et_name.getText().toString().trim(),(long)(gaoIdList.size()+1) );
                    gaoList.add(gao1);
                }
                if(!et_2.getText().toString().trim().equals("")){
                    Gao gao1 = new Gao(null,et_2.getText().toString().trim(),et_name.getText().toString().trim(),(long)(gaoIdList.size()+1) );
                    gaoList.add(gao1);
                }
                if(!et_3.getText().toString().trim().equals("")){
                    Gao gao1 = new Gao(null,et_3.getText().toString().trim(),et_name.getText().toString().trim(),(long)(gaoIdList.size()+1) );
                    gaoList.add(gao1);
                }
                if(!et_4.getText().toString().trim().equals("")){
                    Gao gao1 = new Gao(null,et_4.getText().toString().trim(),et_name.getText().toString().trim(),(long)(gaoIdList.size()+1) );
                    gaoList.add(gao1);
                }
                dao.insertInTx(gaoList);
                listsGao = dao.loadAll();

                GaoId gaoId = new GaoId(null,et_name.getText().toString().trim());
                gaoIdDao.insert(gaoId);
                gaoIdList = gaoIdDao.loadAll();
                gaoAdapter.notifyDataSetChanged();
                addAddressDialog.dismiss();
            }
        });
    }





    class GaoAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return listsGao.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = View.inflate(getApplicationContext(),R.layout.my_list_item, null);
            TextView tv_list_item_zu_name = view.findViewById(R.id.tv_list_item_zu_name);
            TextView tv_list_item_path = view.findViewById(R.id.tv_list_item_path);
            TextView tv_list_item_edit = view.findViewById(R.id.tv_list_item_edit);
            TextView tv_list_item_detele = view.findViewById(R.id.tv_list_item_detele);
            TextView tv_list_item_add = view.findViewById(R.id.tv_list_item_add);
            LinearLayout ll_background = view.findViewById(R.id.ll_background);

            tv_list_item_zu_name.setText(listsGao.get(position).getName());
            tv_list_item_path.setText(listsGao.get(position).getPath());

            if (position ==0){
                if(color==0){
                    ll_background.setBackgroundColor(Color.parseColor("#ffffff"));
                }else if(color ==1){
                    ll_background.setBackgroundColor(Color.parseColor("#ff0000"));
                }

            }else {
                if(listsGao.get(position).getGid().equals(listsGao.get(position-1).getGid())){
                    if(color == 0){
                        ll_background.setBackgroundColor(Color.parseColor("#ffffff"));
                    }else if(color ==1){
                        ll_background.setBackgroundColor(Color.parseColor("#ff0000"));
                    }

                }else {
                    if(color == 0){
                        ll_background.setBackgroundColor(Color.parseColor("#ffffff"));
                    }else if(color ==1){
                        ll_background.setBackgroundColor(Color.parseColor("#ff0000"));
                    }
                    changeColor();
                }
            }

            //删除单个视频
            tv_list_item_detele.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dao.delete(listsGao.get(position));
                    //listsGao = dao.loadAll();
                    listsGao = queryGaoList();
                    notifyDataSetChanged();
                }
            });
            //添加单个视频
            tv_list_item_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = listsGao.get(position).getName();
                    long gid = listsGao.get(position).getGid();
                    AddOneVideoDialog( name, gid);

                }
            });

            //编辑单个视频
            tv_list_item_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = listsGao.get(position).getName();
                    String path = listsGao.get(position).getPath();
                    long gid = listsGao.get(position).getGid();
                    long id = listsGao.get(position).getId();
                    EditOneVideoDialog(id, name,  gid, path) ;

                }
            });


            return view;
        }
    }


    /**
     * 添加单个视频
     */
    MyAlertDialog addOneAddressDialog;
    private void AddOneVideoDialog(final String name, final long gid) {
        addOneAddressDialog = new MyAlertDialog.Builder(this)
                .setContentView(R.layout.alertdialog_add_one_address)
                .setCancelable(true)
                .show();
        final EditText et_1 = addOneAddressDialog.getView(R.id.et_1);
        addOneAddressDialog.setOnclickListener(R.id.tv_dialog_add, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_1.getText().toString().trim().equals("")){
                    Toast.makeText(Gao2Activity.this,"视频地址不能为空",Toast.LENGTH_LONG).show();
                }else {
                    Gao gao = new Gao(null,et_1.getText().toString().trim(),name,gid);
                    dao.insert(gao);
                    //listsGao = dao.loadAll();
                    listsGao = queryGaoList();
                    gaoAdapter.notifyDataSetChanged();
                }
                addOneAddressDialog.dismiss();

            }
        });

    }

    /**
     * 修改单个视频
     */
    MyAlertDialog editOneAddressDialog;
    private void EditOneVideoDialog( final long id,final String name, final long gid,final String path) {
        editOneAddressDialog = new MyAlertDialog.Builder(this)
                .setContentView(R.layout.alertdialog_edit_one_address)
                .setCancelable(true)
                .show();
        final EditText et_1 = editOneAddressDialog.getView(R.id.et_1);
        et_1.setText(path);
        editOneAddressDialog.setOnclickListener(R.id.tv_dialog_add, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_1.getText().toString().trim().equals("")){
                    Toast.makeText(Gao2Activity.this,"视频地址不能为空",Toast.LENGTH_LONG).show();
                }else {
                    Gao gao = new Gao(id,et_1.getText().toString().trim(),name,gid);
                    dao.update(gao);
                    //listsGao = dao.loadAll();
                    listsGao = queryGaoList();
                    gaoAdapter.notifyDataSetChanged();
                }
                editOneAddressDialog.dismiss();

            }
        });

    }

    private  int color=0;
    public int changeColor(){
        if(color == 0){
            color=1;
            return color;
        }else if(color == 1){
            color = 0;
            return color;
        }
        return color;

    }

    public  List<Gao> queryGaoList() {
        List<Gao> gao = new ArrayList<>();
        QueryBuilder queryBuilder = dao.queryBuilder();
        queryBuilder.orderAsc(GaoDao.Properties.Gid);
        gao = queryBuilder.list();
        return gao;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Gao2Activity.this,ViewPagerActivity.class));
        finish();
    }
}
