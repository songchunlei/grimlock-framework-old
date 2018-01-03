package org.grimlock.sms.server.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author:chunlei.song@live.com
 * @Description:
 * @Date Create in 15:49 2018-1-2
 * @Modified By:
 */

@Entity
@Table(name = "t_sms")
public class SmsDomain implements Serializable {

    @Id
    @GeneratedValue
    private Long smsId;

    @Column
    private String phone;

    @Column
    private String content;

    public Long getSmsId() {
        return smsId;
    }

    public void setSmsId(Long smsId) {
        this.smsId = smsId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
