package cn.edu.dlut.career.domain.company;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by wei on 2017/3/24.
 * 大招事项信息表
 */
@Entity
@Table(name = "matters_needing_attention")
public class MattersNeedingAttention {
  //编号，主键UUID
  @Id
  @GenericGenerator(name = "idGenerator", strategy = "uuid2")
  @GeneratedValue(generator = "idGenerator")
  private UUID id;

  //大招聘会编号
  @Column(length = 100,name = "recuit_id")
  private UUID recruitId;

  //日期
  @Column(nullable = false)
  private LocalDateTime date;

  //事项
  @Column(length = 100)
  private String matter;

  //内容
  @Column(length = 100)
  private String content;

  //地点
  @Column(length = 100)
  private String address;

  //联系人
  @Column(length = 100)
  private String contacts;

  //联系电话
  @Column(length = 100)
  private String tel;

  //备注
  @Column(length = 100)
  private String remarks;

  public MattersNeedingAttention() {
  }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setRecruitId(UUID recruitId) {
        this.recruitId = recruitId;
    }

    public UUID getRecruitId() {
        return recruitId;
    }

    public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  public String getMatter() {
    return matter;
  }

  public void setMatter(String matter) {
    this.matter = matter;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getContacts() {
    return contacts;
  }

  public void setContacts(String contacts) {
    this.contacts = contacts;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }
}
