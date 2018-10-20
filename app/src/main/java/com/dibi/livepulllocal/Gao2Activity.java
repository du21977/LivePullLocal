package com.dibi.livepulllocal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.dibi.livepulllocal.entity.Gao;
import com.dibi.livepulllocal.entity.GaoId;
import com.dibi.livepulllocal.entity.User;
import com.dibi.livepulllocal.greendao.GaoDao;
import com.dibi.livepulllocal.greendao.GaoIdDao;
import com.dibi.livepulllocal.greendao.UserDao;

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
        listsGao = dao.loadAll();
         gaoAdapter = new GaoAdapter();
        listview.setAdapter(gaoAdapter);

//        GaoId gaoId = new GaoId(null,"组名1");
//        GaoId gaoId = new GaoId(null,"组名2");
//        GaoId gaoId = new GaoId(null,"组名3");
//        gaoIdDao.insert(gaoId);
        tv_add_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gaoIdList = gaoIdDao.loadAll();

//                GaoId gaoId = new GaoId(null,"组名1");
//                Gao gaoInsertData = new Gao(null,"http://mpv.videocc.net/692c80f137/a/692c80f137eba8ee04a41d58ee585a1a_2.mp4?pid=1537543084757X1274431","组名1",(long)(gaoIdList.size()+1));
//                GaoId gaoId = new GaoId(null,"组名2");
//                Gao gaoInsertData = new Gao(null,"http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4","组名2",(long)(gaoIdList.size()+1));
//                GaoId gaoId = new GaoId(null,"组名3");
//                Gao gaoInsertData = new Gao(null,"http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4","组名3",(long)(gaoIdList.size()+1));

//                Gao gaoInsertData = new Gao(null,"http://mpv.videocc.net/692c80f137/a/692c80f137eba8ee04a41d58ee585a1a_2.mp4?pid=1537543084757X1274431","组名1",(long)1);
//
//                Gao gaoInsertData = new Gao(null,"http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4","组名2",(long)2);
//
                Gao gaoInsertData = new Gao(null,"http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4","组名3",(long)3);


                dao.insert(gaoInsertData);
                listsGao = dao.loadAll();
                gaoAdapter.notifyDataSetChanged();
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

            tv_list_item_zu_name.setText(listsGao.get(position).getName());
            tv_list_item_path.setText(listsGao.get(position).getPath());

            tv_list_item_detele.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dao.delete(listsGao.get(position));
                    listsGao = dao.loadAll();
                    notifyDataSetChanged();
                }
            });

            return view;
        }
    }


}
