package com.dibi.livepulllocal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.dibi.livepulllocal.adapter.AFaAdapter;
import com.dibi.livepulllocal.entity.User;
import com.dibi.livepulllocal.greendao.UserDao;

import org.greenrobot.greendao.query.Query;

import java.util.List;

public class GaoActivity extends AppCompatActivity implements View.OnClickListener{

    private GlobalApplication app;

    private Button btn, btn_insert, btn_query, btn_queryAll, btn_delete, btn_update;
    private ListView list;
    private EditText et_Name, et_age, et_sex, et_Salary;
    private String name, sex, salary, age;
    private AFaAdapter adapter;
    private List<User> listsUser;
    private UserDao dao;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gao);
        dao = GlobalApplication.getApplication().getDaoSession().getUserDao();
        int a = 5;
        initView();
    }



    /**
     * 初始化 控件
     */
    private void initView() {
        et_Name = (EditText) findViewById(R.id.et_name);
        et_age = (EditText) findViewById(R.id.et_age);
        et_sex = (EditText) findViewById(R.id.et_sex);
        et_Salary = (EditText) findViewById(R.id.et_salary);

        btn = (Button) findViewById(R.id.btn_main);
        btn_insert = (Button) findViewById(R.id.btn_insert);
        btn_query = (Button) findViewById(R.id.btn_query);
        btn_queryAll = (Button) findViewById(R.id.btn_queryAll);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_update = (Button) findViewById(R.id.btn_update);
        list = (ListView) findViewById(R.id.list);
        btn.setOnClickListener(this);
        btn_insert.setOnClickListener(this);
        btn_query.setOnClickListener(this);
        btn_queryAll.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_update.setOnClickListener(this);
      //  list.setOnItemLongClickListener(new MyOnItemLongClickListener());

        listsUser = dao.loadAll();
        adapter = new AFaAdapter(this, listsUser);
        list.setAdapter(adapter);



      /*  btn_GetData =(Button)findViewById(R.id.btn_main_activity_getData);
      //  list_DisplayContent = (PullToRefreshListView)findViewById(R.id.listView_mainActivity);
        btn_GetData.setOnClickListener(this);*/
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_insert:

                insertData();
                break;
            case R.id.btn_query:
                query();
                break;
            case R.id.btn_queryAll:
                queryAll();
                break;

            case R.id.btn_update:

                Toast.makeText(GaoActivity.this, "编辑", Toast.LENGTH_LONG).show();
                //update();


                break;
            case R.id.btn_delete:
                Toast.makeText(GaoActivity.this, "删除", Toast.LENGTH_LONG).show();
                //删除全部
                dao.deleteAll();
                adapter.setData(dao.loadAll());


                break;
        }
    }

    private void insertData() {
        name = et_Name.getText().toString().trim();
        age = et_age.getText().toString();
        sex = et_sex.getText().toString().trim();
        salary = et_Salary.getText().toString().trim();
        User insertData = new User(null, name, age, sex, salary);
        dao.insert(insertData);
        listsUser = dao.loadAll();
        adapter.addData(insertData);
    }

    private void queryAll() {
        dao.loadAll();

    }

    private void query() {

        Query query = dao.queryBuilder().where(UserDao.Properties.Name.eq(et_Name.getText().toString().trim())).build();
        Toast.makeText(this, "查询的结果是：" + query.list().toString(), Toast.LENGTH_LONG).show();

    }
}
