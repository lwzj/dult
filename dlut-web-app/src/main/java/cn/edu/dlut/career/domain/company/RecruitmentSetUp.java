package cn.edu.dlut.career.domain.company;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * Created by wei on 2017/3/24.
 * 大型招聘会设置信息
 */
@Entity
@Table(name = "recuit_setup")
public class RecruitmentSetUp {
  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name="recuit_id")
  private Set<MattersNeedingAttention> mattersNeedingAttentions;



  //编号，主键UUID
  @Id
  @GenericGenerator(name = "idGenerator",strategy = "uuid")
  @GeneratedValue(generator = "idGenerator")
  private UUID id;

  //招聘会名称
  @Column(length = 100)
  private String name;

  //招聘会类型
  @Column(length = 100)
  private String type;

  //邀请码
  @Column(length = 100)
  private String inviteCode;

  //邀请函
  @Column(length = 100)
  private String invitation;

  //是否付费
  /**
   * "1"免费
   * "0"付费
   */
  @Column(length = 100)
  private String isfree;

  //开始时间
  @Column(nullable = false)
  private LocalDateTime startTime;

  //结束时间
  @Column(nullable = false)
  private LocalDateTime endTime;

  //报名开始时间
  @Column(nullable = false)
  private LocalDateTime enrollStartTime;

  //报名结束时间
  @Column(nullable = false)
  private LocalDateTime enrollEndTime;

  //展厅
  @Column(length = 100)
  private String displayHall;

  //召开地点1
  @Column(length = 100)
  private String palceOne;

  //召开地点2
  @Column(length = 100)
  private  String palceTwo;

  //招聘会简介
  @Column(length = 100)
  private String synopsis;

  //创建时间
  @Column(nullable = false)
  private LocalDateTime createOn;

  //创建人
  @Column(length = 100)
  private String createPeople;

  public RecruitmentSetUp() {
  }

  public Set<MattersNeedingAttention> getMattersNeedingAttentions() {
    return mattersNeedingAttentions;
  }

  public void setMattersNeedingAttentions(Set<MattersNeedingAttention> mattersNeedingAttentions) {
    this.mattersNeedingAttentions = mattersNeedingAttentions;
  }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getInviteCode() {
    return inviteCode;
  }

  public void setInviteCode(String inviteCode) {
    this.inviteCode = inviteCode;
  }

  public String getInvitation() {
    return invitation;
  }

  public void setInvitation(String invitation) {
    this.invitation = invitation;
  }

  public String getIsfree() {
    return isfree;
  }

  public void setIsfree(String isfree) {
    this.isfree = isfree;
  }

  public LocalDateTime getStartTime() {
    return startTime;
  }

  public void setStartTime(LocalDateTime startTime) {
    this.startTime = startTime;
  }

  public LocalDateTime getEndTime() {
    return endTime;
  }

  public void setEndTime(LocalDateTime endTime) {
    this.endTime = endTime;
  }

  public LocalDateTime getEnrollStartTime() {
    return enrollStartTime;
  }

  public void setEnrollStartTime(LocalDateTime enrollStartTime) {
    this.enrollStartTime = enrollStartTime;
  }

  public LocalDateTime getEnrollEndTime() {
    return enrollEndTime;
  }

  public void setEnrollEndTime(LocalDateTime enrollEndTime) {
    this.enrollEndTime = enrollEndTime;
  }

  public String getDisplayHall() {
    return displayHall;
  }

  public void setDisplayHall(String displayHall) {
    this.displayHall = displayHall;
  }

  public String getPalceOne() {
    return palceOne;
  }

  public void setPalceOne(String palceOne) {
    this.palceOne = palceOne;
  }

  public String getPalceTwo() {
    return palceTwo;
  }

  public void setPalceTwo(String palceTwo) {
    this.palceTwo = palceTwo;
  }

  public String getSynopsis() {
    return synopsis;
  }

  public void setSynopsis(String synopsis) {
    this.synopsis = synopsis;
  }

  public LocalDateTime getCreateOn() {
    return createOn;
  }

  public void setCreateOn(LocalDateTime createOn) {
    this.createOn = createOn;
  }

  public String getCreatePeople() {
    return createPeople;
  }

  public void setCreatePeople(String createPeople) {
    this.createPeople = createPeople;
  }
}
