package com.dibi.livepulllocal.entity;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Admin on 2018/10/20.
 */

@Entity
public class Gao implements Parcelable{

    @Id(autoincrement = true)
    private Long id;
    private String path;  //播放路径
    private String name; //组名
    private Long gid; //组名
    public Long getGid() {
        return this.gid;
    }
    public void setGid(Long gid) {
        this.gid = gid;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPath() {
        return this.path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1693059140)
    public Gao(Long id, String path, String name, Long gid) {
        this.id = id;
        this.path = path;
        this.name = name;
        this.gid = gid;
    }
    @Generated(hash = 1430706628)
    public Gao() {
    }

    @Override
    public String toString() {
        return "Gao{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", gid=" + gid +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
