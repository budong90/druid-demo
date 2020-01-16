package cc.eslink.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *@ClassName SysUser
 *@Description sys_user表
 *@Author zeng.yakun (0178)
 *@Date 2019/11/22 16:39
 *@Version 1.0
 **/
public class SysUser implements Serializable {

    private Integer id;

    private String userName;

    private String address;

    private String telephone;

    private Integer sex;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
