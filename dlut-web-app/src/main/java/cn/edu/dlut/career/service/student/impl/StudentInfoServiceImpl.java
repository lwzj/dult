package cn.edu.dlut.career.service.student.impl;

import cn.edu.dlut.career.domain.student.StudentInfo;
import cn.edu.dlut.career.dto.student.StuLoginDTO;
import cn.edu.dlut.career.dto.student.StudentInfoDTO;
import cn.edu.dlut.career.repository.student.StudentInfoRepository;
import cn.edu.dlut.career.service.student.StudentInfoService;
import cn.edu.dlut.career.shiro.UserPrincipal;
import cn.edu.dlut.career.util.PubCodeUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *  学生学籍信息数据 服务层
 * Created by HealerJean on 2017/4/12.
 */
@Service
@Transactional
public class StudentInfoServiceImpl implements StudentInfoService {
    @Autowired
    private StudentInfoRepository studentInfoRepository;

    Logger logger = LoggerFactory.getLogger(StudentInfoServiceImpl.class);


    @Override
    public StudentInfo saveStudentInfo(StudentInfo studentInfo) {
        return studentInfoRepository.save(studentInfo);
    }

    @Override
    public StudentInfo findById(UUID id) {
        return studentInfoRepository.findOne(id);
    }

    /**
     * 学生更新自己的信息
     * @param studentInfoDTO
     * @return
     */
    @Override
    public StudentInfo updateStudentInfo(StudentInfoDTO studentInfoDTO) {
        StudentInfo studentInfo = studentInfoRepository.findById(studentInfoDTO.getId());
        //判断是修改还是 全部提交进行上报
        if(studentInfoDTO.getAllupdateStatus()!=null){
            //学生审核时间
            studentInfo.setStuCheckTime(LocalDateTime.now());
            //学生是修改人
            studentInfo.setUpdator(studentInfoDTO.getName());
            //学生核对状态,上报状态，设置为 1，表示学生已经核对信息。
            studentInfo.setHaveReport("1");
            //教师审核状态 设置为待审核
            studentInfo.setStatus("00");
            return studentInfoRepository.save(studentInfo);
        }else {
            //学生没有审核才可以进行修改，1代表学生已经上报
            if (!studentInfo.getHaveReport().equals("1")) {
                studentInfo.setId(studentInfoDTO.getId());
                studentInfo.setIdCard(studentInfoDTO.getIdCard());
                studentInfo.setGender(studentInfoDTO.getGender());
                studentInfo.setEduDegree(studentInfoDTO.getEduDegree());
                studentInfo.setStuNo(studentInfoDTO.getStuNo());
                studentInfo.setBirthdate(LocalDate.parse(studentInfoDTO.getBirthdate()));
                studentInfo.setPolitical(studentInfoDTO.getPolitical());
                studentInfo.setEthnic(studentInfoDTO.getEthnic());
                studentInfo.setHomeland(studentInfoDTO.getHomeland());
                studentInfo.setDepartmentId(studentInfoDTO.getDepartmentId());
                studentInfo.setMajorCode(studentInfoDTO.getMajorCode());
                studentInfo.setTutorName(studentInfoDTO.getTutorName());
                studentInfo.setStartDate(studentInfoDTO.getStartDate());
                studentInfo.setEduYear(studentInfoDTO.getEduYear());
                studentInfo.setClassName(studentInfoDTO.getClassName());
                studentInfo.setFlangType(studentInfoDTO.getFlangType());
                studentInfo.setFlangType2(studentInfoDTO.getFlangType2());
                studentInfo.setName(studentInfoDTO.getName());
                studentInfo.setCounselor(studentInfoDTO.getCounselor());
                studentInfo.setEduMode(studentInfoDTO.getEduMode());
                studentInfo.setTrustee(studentInfoDTO.getTrustee());
                studentInfo.setHaveEduHukou(studentInfoDTO.getHaveEduHukou());
                studentInfo.setEmail(studentInfoDTO.getEmail());
                studentInfo.setQqNo(studentInfoDTO.getQqNo());
                studentInfo.setWechatNo(studentInfoDTO.getWechatNo());
                studentInfo.setMobilephone(studentInfoDTO.getMobilephone());
                studentInfo.setHomePhone(studentInfoDTO.getHomePhone());

                /**
                 * 通过 departmentId 和 marjor 将关联的院系 和专业 同时存储进去
                 */
                studentInfo.setDepartment(PubCodeUtil.getName("academy", studentInfoDTO.getDepartmentId()));
                studentInfo.setMajorName(PubCodeUtil.getName("major", studentInfoDTO.getMajorCode()));

                //学生审核时间
                studentInfo.setStuCheckTime(LocalDateTime.now());
                //学生是修改人
                studentInfo.setUpdator(studentInfoDTO.getName());
                return studentInfoRepository.save(studentInfo);

            }
        }
        return null;
    }

    @Override
    public String deleteStudentInfo(UUID id) {
        try {
            studentInfoRepository.delete(id);
            return "ok";
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<StudentInfo> findAllStudentInfo() {
        return studentInfoRepository.findAll();
    }

    @Override
    public void saveStudentInfos(List<StudentInfo> studentInfos) {
        studentInfoRepository.save(studentInfos);
    }

    /**
     * 学生端登录的用户 信息
     * @param stuNo
     * @return
     */
    @Override
    public StuLoginDTO findLoginInfo(String stuNo) {
        return studentInfoRepository.findLoginInfo(stuNo);
    }


    /**
     * 修改密码
     * @param newPwd
     * @return
     */
    @Override
    public int updatePwd(UUID id,String newPwd,String salt) {
             String newPwdFinale = new Md5Hash(newPwd, salt).toString();
            return studentInfoRepository.updatePwd(id, newPwdFinale);
    }

    /**
     * 查看用户修改密码时，输入的原密码是否正确
     * @param id
     * @param pwd
     * @param originalPwd
     * @param salt
     * @return
     */
    @Override
    public String findPwd(UUID id, String pwd, String originalPwd, String salt) {
        String password = new Md5Hash(pwd, salt).toString();
        if (originalPwd.trim().equals(password.trim())) {
            return "ok";
        }
            return null;

    }

    /**
     * 教师端
     * 查看所有学生的学籍信息
     */
    @Override
    public Page<StudentInfo> listDepartStuPage(String name, String stuNo, String gender, String ethnic, String departmentId, String majorCode, String eduYear, String eduMode, String endDate, String tutorName, String status, String eduDegree, Pageable pageable) {
        if(name.equals("")){
            name = null;
        }if(stuNo.equals("")){
            stuNo = null;
        }if(gender.equals("")){
            gender = null;
        }if(ethnic.equals("")){
            ethnic = null;
        }if(departmentId.equals("")){
            departmentId = null;
        }if(majorCode.equals("")){
            majorCode = null;
        }if(eduYear.equals("")){
            eduYear = null;
        }if(eduMode.equals("")){
            eduMode = null;
        }if(tutorName.equals("")){
            tutorName = null;
        }if(status.equals("")){
            status = null;
        }if(eduDegree.equals("")){
            eduDegree = null;
        }if(endDate.equals("")){
            endDate = null;
        }

        logger.info(pageable+"*****分页大小********");
//        Pageable pageable = new PageRequest(page, 1);
        return studentInfoRepository.queryGetDepartStusPage(name, stuNo, gender, ethnic, departmentId, majorCode, eduYear, eduMode, endDate, tutorName, status, eduDegree,pageable);

    }

    /**
     * 教师端
     * 查看本学院 学生的学籍信息
     */
    @Override
    public Page<StudentInfo> listMyDepartStuPage(String name, String stuNo, String gender, String ethnic,String majorCode,String departmentId, String eduYear, String eduMode, String endDate, String tutorName, String status, String eduDegree, String haveReport, Pageable pageable) {
        if(name.equals("")){
            name = null;
        }if(stuNo.equals("")){
            stuNo = null;
        }if(gender.equals("")){
            gender = null;
        }if(ethnic.equals("")){
            ethnic = null;
        }if(majorCode==null||majorCode.equals("")){
            majorCode = null;
        }if(eduYear.equals("")){
            eduYear = null;
        }if(eduMode.equals("")){
            eduMode = null;
        }if(tutorName.equals("")){
            tutorName = null;
        }if(status.equals("")){
            status = null;
        }if(eduDegree.equals("")){
            eduDegree = null;
        }if(haveReport.equals("")){
            haveReport = null;
        }if(endDate.equals("")){
            endDate = null;
        }if(departmentId==null||departmentId.equals("")){
            departmentId = null;
        }
        UserPrincipal userPrincipal = (UserPrincipal)SecurityUtils.getSubject().getPrincipal();
        if (userPrincipal.getPrincipal().equals("SCHOOL")||userPrincipal.getPrincipal().equals("SCHADMIN")){
         //校级
            return studentInfoRepository.queryGetMyDepartStusPage(name, stuNo, gender, ethnic, departmentId, majorCode, eduYear, eduMode, endDate, tutorName, status, eduDegree, haveReport,pageable);
        }else {
         //院级
            return studentInfoRepository.queryGetMyDepartStusPage(name, stuNo, gender, ethnic, userPrincipal.getDepId(), majorCode, eduYear, eduMode, endDate, tutorName, status, eduDegree, haveReport, pageable);
        }
    }


    /**
     * 教师更新学生信息
     * @param studentInfoDTO
     * @return
     */
    @Override
    public StudentInfo teaUpdateStudentInfo(StudentInfoDTO studentInfoDTO, String teaDepartId) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
        StudentInfo studentInfo = studentInfoRepository.findById(studentInfoDTO.getId());
       //教师只能修改本院系的学士的学籍，所以这里进行学士院系的判断 ,但是任意校级可以操作
        if(studentInfoDTO.getAllupdateStatus()!=null){
            //教师为更新人
            studentInfo.setUpdator(userPrincipal.getRealName());
            //教师审核，设置为1
            studentInfo.setStatus(studentInfoDTO.getStatus());
            //教师设置审核时间
            studentInfo.setSchCheckTime(LocalDateTime.now());
        }else {
            if (teaDepartId.equals(studentInfo.getDepartmentId()) || userPrincipal.getPrincipal().equals("SCHOOL") || userPrincipal.getPrincipal().equals("SCHADMIN")) {

                studentInfo.setIdCard(studentInfoDTO.getIdCard());
                studentInfo.setGender(studentInfoDTO.getGender());
                studentInfo.setEduDegree(studentInfoDTO.getEduDegree());
                studentInfo.setStuNo(studentInfoDTO.getStuNo());
                studentInfo.setBirthdate(LocalDate.parse(studentInfoDTO.getBirthdate()));
                studentInfo.setPolitical(studentInfoDTO.getPolitical());
                studentInfo.setEthnic(studentInfoDTO.getEthnic());
                studentInfo.setHomeland(studentInfoDTO.getHomeland());
                studentInfo.setDepartmentId(studentInfoDTO.getDepartmentId());
                studentInfo.setMajorCode(studentInfoDTO.getMajorCode());
                studentInfo.setTutorName(studentInfoDTO.getTutorName());
                studentInfo.setStartDate(studentInfoDTO.getStartDate());
                studentInfo.setEduYear(studentInfoDTO.getEduYear());
                studentInfo.setClassName(studentInfoDTO.getClassName());
                studentInfo.setFlangType(studentInfoDTO.getFlangType());
                studentInfo.setFlangType2(studentInfoDTO.getFlangType2());
                studentInfo.setName(studentInfoDTO.getName());
                studentInfo.setCounselor(studentInfoDTO.getCounselor());
                studentInfo.setEduMode(studentInfoDTO.getEduMode());
                studentInfo.setTrustee(studentInfoDTO.getTrustee());
                studentInfo.setHaveEduHukou(studentInfoDTO.getHaveEduHukou());
                studentInfo.setEmail(studentInfoDTO.getEmail());
                studentInfo.setQqNo(studentInfoDTO.getQqNo());
                studentInfo.setWechatNo(studentInfoDTO.getWechatNo());
                studentInfo.setMobilephone(studentInfoDTO.getMobilephone());
                studentInfo.setHomePhone(studentInfoDTO.getHomePhone());

                /**
                 * 通过 departmentId 和 marjor 将关联的院系 和专业 同时存储进去
                 */
                studentInfo.setDepartment(PubCodeUtil.getName("academy", studentInfoDTO.getDepartmentId()));
                studentInfo.setMajorName(PubCodeUtil.getName("major", studentInfoDTO.getMajorCode()));
                //教师为更新人
                studentInfo.setUpdator(userPrincipal.getRealName());

                return studentInfoRepository.save(studentInfo);
            }
        }
        return  null;
    }

    /**
     * 教师审核某个学生学籍
     * @param id
     * @return
     */
    @Override
    public StudentInfo teaUpdateStuInfoAuditStatus(UUID id) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
        String departmentId = studentInfoRepository.findById(id).getDepartmentId();
        if (userPrincipal.getDepId().equals(departmentId)||userPrincipal.getPrincipal().equals("SCHOOL")||userPrincipal.getPrincipal().equals("SCHADMIN")){
            //1 表示审核通过
            String status = "01";
            StudentInfo studentInfo = studentInfoRepository.findById(id);
            studentInfo.setStatus(status);
            studentInfo.setSchCheckTime(LocalDateTime.now());
            studentInfo.setUpdator(userPrincipal.getRealName());

            return studentInfoRepository.save(studentInfo);
         }else{
        return null;
        }
    }


    /**
     * 教师批量审核学生学籍
     * @param id
     * @return
     */
    @Override
    public List<StudentInfo> stuInfoBatchAudit(UUID[] id) {

        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();

            List<StudentInfo> studentInfos = new ArrayList<StudentInfo>();
            for (UUID sid : id) {
                String departmentId = studentInfoRepository.findById(sid).getDepartmentId();
                if (userPrincipal.getDepId().equals(departmentId)||userPrincipal.getPrincipal().equals("SCHOOL")||userPrincipal.getPrincipal().equals("SCHADMIN")) {
                    StudentInfo studentInfo = studentInfoRepository.findById(sid);
                    studentInfo.setStatus("01");
                    studentInfo.setSchCheckTime(LocalDateTime.now());
                    studentInfo.setUpdator(userPrincipal.getRealName());
                    studentInfos.add(studentInfoRepository.save(studentInfo));
                    }else {
                    return  null;
                }
            }
            return studentInfos;
    }



    @Override
    public StudentInfo findByStuNo(String stuNo) {
        return studentInfoRepository.findByStuNo(stuNo);
    }

//    @Override
//    public Page<StudentInfo> findList(String stuNo, String name, String department, String eduDegree, String endDate, String stuStatus, String majorCode, String recName, Pageable pageable) {
//        return StudentInfoRepository.findList(stuNo,name,department,eduDegree,endDate,stuStatus,majorCode,recName,pageable);
//    }


}
