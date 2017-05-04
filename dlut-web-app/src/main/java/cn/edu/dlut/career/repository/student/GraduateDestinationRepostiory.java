package cn.edu.dlut.career.repository.student;

import cn.edu.dlut.career.domain.student.GraduateDestination;
import cn.edu.dlut.career.domain.student.StudentInfo;
import cn.edu.dlut.career.dto.school.DispatchDTO;
import cn.edu.dlut.career.dto.student.JobDestinationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by wei on 2017/4/12.
 */
public interface GraduateDestinationRepostiory extends CrudRepository<GraduateDestination,UUID> {
    List<GraduateDestination> findAll();

    @Query(value = "From GraduateDestination g where g.studentInfo.id=?1")
    GraduateDestination findByStuId(UUID stuId);

    @Query(value = "From GraduateDestination g where g.studentInfo.id=?1 and status = ?2")
    GraduateDestination findByStuIdAndStatus(UUID stuId,String status);

    @Query(value = "From GraduateDestination as s where " +
                "(s.studentInfo.stuNo = ?1 or ?1 = '') and "  +
                "(s.studentInfo.name like %?2% or ?2 = '') and " +
                "(s.studentInfo.departmentId = ?3 or ?3 = '') and "  +
                "(s.studentInfo.eduDegree = ?4 or ?4 = '') and "  +
                "(s.studentInfo.endDate like ?5% or ?5= '') and " +
                "(s.stuStatus = ?6 or ?6 = '') and "  +
                "(s.studentInfo.majorCode = ?7 or ?7 = '') and " +
                "(s.recName = ?8 or ?8 = '')")
    Page<GraduateDestination> findList(String stuNo, String name, String departmentId, String eduDegree, String endDate, String stuStatus, String majorCode, String recName, Pageable pageable);


    //查看派遣信息
    //张宇晋
    @Query("From GraduateDestination  as s WHERE " +
        "(s.studentInfo.stuNo =?1 or ?1=null) and " +
        "(s.studentInfo.name like %?2% or ?2 =null) and " +
        "(s.studentInfo.majorCode =?3 or ?3=null) and " +
        "(s.studentInfo.eduDegree =?4 or ?4=null) and " +
        "(s.studentInfo.eduMode =?5 or ?5=null) and " +
        "(s.status =?6 or ?6=null) ")
    Page<GraduateDestination> findDispatchDTOByStuIdPage(
        String stuNo,
        String stuName,
        String majorCode,
        String eduDegree,
        String eduMode,
        String endStatus,
        Pageable pageable);


    /**
     * @Description 根据毕业时间获取各学院已就业人数.
     * @Author  wangyj
     * @CreateDate 2017/4/28 9:57
     * @Param
     * @Return
     */
    @Query(value = "select new map(g.studentInfo.department as department,count(*) as count) from GraduateDestination g where g.studentInfo.endDate like ?1% and g.stuStatus='01'  group by g.studentInfo.department")
    List<Map<String,Object>> getAcademyEmplo(String graduateDate);

    /**
     * @Description 根据毕业时间获取各学院总人数.
     * @Author  wangyj
     * @CreateDate 2017/4/28 15:26
     * @Param
     * @Return
     */
    @Query(value = "select new map(g.studentInfo.department as department,count(*) as count) from GraduateDestination g where g.studentInfo.endDate like ?1%  group by g.studentInfo.department")
    List<Map<String,Object>> getAcademyTotal(String graduateDate);

    /**
     * @Description 根据毕业时间统计该届毕业生总人数.
     * @Author  wangyj
     * @CreateDate 2017/4/28 14:20
     * @Param
     * @Return
     */
    @Query(value = "select count(*) from GraduateDestination g where g.studentInfo.endDate like ?1% ")
    int countAllGraduate(String graduateDate);

    /**
     * @Description 根据毕业时间统计该届毕业生已就业的总数.
     * @Author  wangyj
     * @CreateDate 2017/4/28 14:21
     * @Param
     * @Return
     */
    @Query(value = "select count(*) from GraduateDestination g where g.studentInfo.endDate like ?1% and g.stuStatus='01'")
    int countEmplo(String graduateDate);

    /**
     * @Description 根据毕业时间统计就业地区信息.
     * @Author  wangyj
     * @CreateDate 2017/4/28 14:31
     * @Param
     * @Return
     */
    @Query(value = "select new map(g.recProvince as name,count(*) as value) from GraduateDestination g where g.studentInfo.endDate like ?1% and g.stuStatus='01'  group by g.recProvince")
    List<Map<String,Object>> getEmploArea(String graduateDate);

    /**
     * @Description 根据毕业时间统计就业性质信息.
     * @Author  wangyj
     * @CreateDate 2017/4/28 15:35
     * @Param
     * @Return
     */
    @Query(value = "select new map(g.recNature as name,count(*) as value) from GraduateDestination g where g.studentInfo.endDate like ?1% and g.stuStatus='01'  group by g.recNature")
    List<Map<String,Object>> getEmploNature(String graduateDate);

    /**
     * @Description 根据毕业时间统计业行业信息.
     * @Author  wangyj
     * @CreateDate 2017/4/28 15:39
     * @Param
     * @Return
     */
    @Query(value = "select new map(g.recIndustry as name,count(*) as value) from GraduateDestination g where g.studentInfo.endDate like ?1% and g.stuStatus='01'  group by g.recIndustry")
    List<Map<String,Object>> getEmploIndustry(String graduateDate);

    /**
     * 查询某一院系在某学年的就业人数
     * @param departmentId 院系id
     * @param endDate 毕业时间
     * @return
     */
    @Query(value="select count(*) from GraduateDestination g " +
        "where g.studentInfo.departmentId=?1 and " +
        "g.studentInfo.endDate like ?2% and " +
        "g.stuStatus='01'")
    int findStuEmpNum(String departmentId, String endDate);

    /**
     * 某一院系在某学年 学生就业去向统计
     * @param departmentId
     * @param graduateDate
     * @return
     */
    @Query("select new Map(g.destinationType as destination,count(*) as count) " +
        "from GraduateDestination g " +
        "where g.studentInfo.departmentId=?1 and " +
        "g.studentInfo.endDate like ?2% and " +
        "g.stuStatus='01'" +
        "group by g.destinationType")
    List<Map<String,Object>> findEmpDesList(String departmentId, String graduateDate);

    /**
     * 某一院系在某学年 学生就业地区分布统计
     * @param departmentId
     * @param graduateDate
     * @return
     */
    @Query("select new Map(g.recProvince as area,count(*) as count) " +
        "from GraduateDestination g " +
        "where g.studentInfo.departmentId=?1 and " +
        "g.studentInfo.endDate like ?2% and " +
        "g.stuStatus='01'" +
        "group by g.recProvince")
    List<Map<String,Object>> findEmpAreaList(String departmentId, String graduateDate);

    /**
     * 某一院系在某学年 学生就业单位性质统计
     * @param departmentId
     * @param graduateDate
     * @return
     */
    @Query("select new Map(g.recNature as nature,count(*) as count) " +
        "from GraduateDestination g " +
        "where g.studentInfo.departmentId=?1 and " +
        "g.studentInfo.endDate like ?2% and " +
        "g.stuStatus='01'" +
        "group by g.recNature")
    List<Map<String,Object>> findEmpNatureList(String departmentId, String graduateDate);

    /**
     * 某一院系在某学年 学生就业行业分布统计
     * @param departmentId
     * @param graduateDate
     * @return
     */
    @Query("select new Map(g.recIndustry as category,count(*) as count) " +
        "from GraduateDestination g " +
        "where g.studentInfo.departmentId=?1 and " +
        "g.studentInfo.endDate like ?2% and " +
        "g.stuStatus='01'" +
        "group by g.recIndustry")
    List<Map<String,Object>> findEmpCategoryList(String departmentId, String graduateDate);

    @Modifying
    @Query(value = "UPDATE GraduateDestination as g SET stuStatus = '01' WHERE id = ?1")
    void upStatus(UUID id);




    @Query("from GraduateDestination g where g.status = ?1 and g.id = ?2 ")
    GraduateDestination findByStatusAndStuNo(String status, UUID id);

    /**
     * 根据学生学号查找就业去向
     * @param stuNo
     * @return
     */
    @Query(value = "From GraduateDestination g where g.studentInfo.stuNo=?1")
    GraduateDestination findByStuNo(String stuNo);
}
