package qqzone.pojo;

import java.sql.Date;
import java.time.LocalDateTime;

/**
 * @create 2023- 05- 21- 19:47
 * @desc
 */
public class UserDetail {
    private Integer id;
    private String realName;
    private String tel;
    private String email;
    private LocalDateTime brith;
    private String star;

    public UserDetail() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getBrith() {
        return brith;
    }

    public void setBrith(LocalDateTime brith) {
        this.brith = brith;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }
}
