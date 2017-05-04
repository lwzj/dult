package cn.edu.dlut.career.service.student.impl;

import cn.edu.dlut.career.domain.student.GraduateDestination;
import cn.edu.dlut.career.domain.student.StudentInfo;
import cn.edu.dlut.career.dto.school.DispatchDTO;
import cn.edu.dlut.career.repository.student.GraduateDestinationRepostiory;
import cn.edu.dlut.career.service.student.GraduateDestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Created by wei on 2017/4/12.
 */
@Service
@Transactional
public class GraduateDestinationServiceImpl implements GraduateDestinationService {
    @Autowired
    private GraduateDestinationRepostiory graduateDestinationRepostiory;

    @Override
    public void save(GraduateDestination jobDestination) {
        graduateDestinationRepostiory.save(jobDestination);
    }

    @Override
    public void delete(UUID id) {
        graduateDestinationRepostiory.delete(id);
    }

    @Override
    public GraduateDestination findOne(UUID id) {
        return graduateDestinationRepostiory.findOne(id);
    }

    @Override
    public List<GraduateDestination> findAll() {
        return graduateDestinationRepostiory.findAll();
    }

    /**
     * 根据学生ID查看学生就业意向
     * @param stuId
     * @return
     */
    @Override
    public GraduateDestination findByStuId(UUID stuId) {
        return graduateDestinationRepostiory.findByStuId(stuId);
    }

    @Override
    public GraduateDestination findByStuIdAndStatus(UUID stuId, String status) {
        return graduateDestinationRepostiory.findByStuIdAndStatus(stuId,status);
    }

    @Override
    public Page<GraduateDestination> findList(String stuNo, String name, String departmentId, String eduDegree, String endDate, String stuStatus, String majorCode, String recName, Pageable pageable) {
        return graduateDestinationRepostiory.findList(stuNo,name,departmentId,eduDegree,endDate,stuStatus,majorCode,recName,pageable);
    }


    @Override
    public Page<GraduateDestination> findDispatchDTOByStuIdPage(String stuNo, String stuName, String majorCode, String eduDegree, String eduMode, String endStatus, Pageable pageable) {
            if(stuNo.equals("")){
                stuNo = null;
            }if(stuName.equals("")){
                stuName = null;
            }if(majorCode.equals("")){
                majorCode = null;
            }if(eduDegree.equals("")){
                eduDegree = null;
            }if(eduMode.equals("")){
                eduMode = null;
            }if(endStatus.equals("")){
                endStatus = null;
            }

//        Pageable pageable = new PageRequest(0,1);
        return graduateDestinationRepostiory.findDispatchDTOByStuIdPage(stuNo, stuName, majorCode, eduDegree, eduMode, endStatus, pageable);
    }

    @Override
    public void update(UUID id) {
        graduateDestinationRepostiory.upStatus(id);
    }

    @Override
    public GraduateDestination findByStatusAndStuNo(String status, UUID id) {
        return graduateDestinationRepostiory.findByStatusAndStuNo(status, id);
    }

}
