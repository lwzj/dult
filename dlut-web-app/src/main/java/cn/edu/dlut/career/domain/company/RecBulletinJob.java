package cn.edu.dlut.career.domain.company;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by 史念念 on 2017/4/21.
 *
 * 招聘简章 职位中间表
 */
@Entity
@Table(name = "rec_bulletin_job")
public class RecBulletinJob {
    //主键ID
    @Id
    @GenericGenerator(name = "idGenerator",strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    //招聘简章id 外键
    @Column(name = "bulletin_id",nullable = false)
    private String bulletinId;

    //招聘职位id 外键
    @Column(name= "job_id",nullable = false)
    private String jobId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBulletinId() {
        return bulletinId;
    }

    public void setBulletinId(String bulletinId) {
        this.bulletinId = bulletinId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
}
