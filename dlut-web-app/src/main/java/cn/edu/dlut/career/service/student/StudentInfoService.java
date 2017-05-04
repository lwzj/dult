package cn.edu.dlut.career.service.student;

import cn.edu.dlut.career.domain.student.StudentInfo;
import cn.edu.dlut.career.dto.student.StuLoginDTO;
import cn.edu.dlut.career.dto.student.StudentInfoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

/**
 * 学生学籍信息表 服务层接口
 * Created by HealerJean on 2017/4/12.
 */
public interface StudentInfoService {
    //保存，添加
    StudentInfo saveStudentInfo(StudentInfo studentInfo) ;

    //根据id查询
    StudentInfo findById(UUID id);

    // 学生更新
    StudentInfo updateStudentInfo(StudentInfoDTO studentInfoDTO);

    //根据id删除 删除成功返回 ok ，否则 null
    String deleteStudentInfo(UUID id);

    //查询所有的数据
    List<StudentInfo> findAllStudentInfo();

    void saveStudentInfos(List<StudentInfo> studentInfos);

    StuLoginDTO findLoginInfo(String stuNo);

    //修改密码
    int updatePwd(UUID id,String newPwd,String salt);

    //判断是不是有这个密码
    String findPwd(UUID id, String pwd, String originalPwd, String salt);


    //分页查询所有学生的学籍
    Page<StudentInfo> listDepartStuPage(String name, String stuNo, String gender, String ethnic, String departmentId, String majorCode, String eduYear , String eduMode, String endDate , String tutorName, String status, String eduDegree, Pageable pageable);

    //分页查询 本学院 所有学生的学籍
    Page<StudentInfo> listMyDepartStuPage(String name, String stuNo, String gender, String ethnic, String departmentId, String majorCode, String eduYear , String eduMode, String endDate , String tutorName, String status, String eduDegree, String haveReport, Pageable pageable);

    //教师更新学生信息
    StudentInfo teaUpdateStudentInfo(StudentInfoDTO studentInfoDTO, String teaDepartId) ;

    //教师直接 审核学生单个审核信息
    StudentInfo teaUpdateStuInfoAuditStatus(UUID id) ;

    //教师学籍批量审核
    List<StudentInfo> stuInfoBatchAudit(UUID []id);



    StudentInfo findByStuNo(String stuNo);

//   Page<StudentInfo> findList(String stuNo, String name, String department, String eduDegree, String endDate, String stuStatus, String majorCode, String recName, Pageable pageable);
}
