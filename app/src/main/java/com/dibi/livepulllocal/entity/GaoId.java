package com.dibi.livepulllocal.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Admin on 2018/10/20.
 */

@Entity
public class GaoId {

    @Id(autoincrement = true)
    private Long id;
    private String name; //组名
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1044721093)
    public GaoId(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 1378964305)
    public GaoId() {
    }

    @Override
    public String toString() {
        return "GaoId{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
