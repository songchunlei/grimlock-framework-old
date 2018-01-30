package org.grimlock.uua.server.db;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author:chunlei.song@live.com
 * @Description:
 * @Date Create in 14:54 2018-1-22
 * @Modified By:
 */
@Entity
@Table(name = "s_user")
public class User implements Serializable {

    @Id
    @GeneratedValue
    @Column
    private Long id; // 自增长的用户ID
    @Column
    private String userName; // 用户名
    @Column
    private String phone; // 手机号码
    @Column
    private String email; // 邮箱
    @Column
    private String password; // 密码

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
