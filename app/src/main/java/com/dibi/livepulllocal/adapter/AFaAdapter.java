package com.dibi.livepulllocal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.dibi.livepulllocal.R;
import com.dibi.livepulllocal.entity.User;

import java.util.ArrayList;
import java.util.List;

public class AFaAdapter extends BaseAdapter {
	
	private Context context;
	private List<User> datas;
	
	public AFaAdapter(Context context, List<User> datas){
		
		this.context = context;
		if(datas ==null){
			datas = new ArrayList<User>();
		}
		this.datas = datas;
		
		
	}

	@Override
	public int getCount() {
		return datas.size();
	}


	public void addData(User datas ){
		this.datas.add(datas);
		notifyDataSetChanged();


	}

	public void setData(List<User> data) {
		if (data != null) {
			datas.clear();
			datas.addAll(data);
			notifyDataSetChanged();
		}
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView ==null){
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.list_item, null);
			holder.name = (TextView)convertView.findViewById(R.id.tv_name);
			holder.age = (TextView)convertView.findViewById(R.id.tv_age);
			holder.sex = (TextView)convertView.findViewById(R.id.tv_sex);
			holder.salary = (TextView)convertView.findViewById(R.id.tv_salary);
			convertView.setTag(holder);
			
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		if(datas !=null) {
			User data = datas.get(position);
			holder.name.setText(data.getName());
			holder.age.setText(data.getAge() + "");
			holder.sex.setText(data.getSex());
			holder.salary.setText(data.getSalary());

		}
		
		return convertView;
	}
	
	
	
	class ViewHolder{
		
		TextView name,age,sex,salary;
		
	}

	
	
	
	
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

}
