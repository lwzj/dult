package cn.edu.dlut.career.controller.student;

import cn.edu.dlut.career.domain.student.GraduateDestination;
import cn.edu.dlut.career.domain.student.StudentInfo;
import cn.edu.dlut.career.service.student.GraduateDestinationService;
import cn.edu.dlut.career.service.student.StudentInfoService;
import cn.edu.dlut.career.shiro.UserPrincipal;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by HealerJean on 2017/4/22.
 */
@Controller
public class ExcelCommand {

    @Autowired
    private StudentInfoService studentInfoService;
    Logger logger = LoggerFactory.getLogger(ExcelCommand.class);
    @Autowired
    private GraduateDestinationService graduateDestinationService;
    @Autowired
    private HttpServletRequest request;
    @PostMapping("/teacher/intoStuInfo")
    @ResponseBody
    public String intoStuInfo(@RequestParam("file") MultipartFile multfile) {
        //通过上传的文件取得文件名称
        String fileName = multfile.getOriginalFilename();
        //取得文件
        File file = new File("C:\\Users\\HealerJean\\Desktop\\" + fileName);

        Workbook book = null;
        try {
            if ((file.getName()).endsWith("xls".trim())) {
                //2003
                book = new HSSFWorkbook(new FileInputStream(file));
            } else if ((file.getName()).endsWith("xlsx".trim())) {
                //2007
                book = new XSSFWorkbook(new FileInputStream(file));
            } else {
                System.out.println("文件格式不支持");
            }
            //获得第一个文本簿
            Sheet sheet = book.getSheetAt(0);
            //得到标题说明 比如id ，stuNo等
            List<String> titles = new ArrayList<String>();
            //表头那一行 ，第一行为说明，第二行才是id等
            Row titleRow = sheet.getRow(1);
            for (int i = 0; i < titleRow.getLastCellNum(); i++) {
                String titleCell = titleRow.getCell(i).getStringCellValue();
                titles.add(titleCell);
            }
            Row row = null;
            long rowNum = 0; //有效行数
            List<StudentInfo> studentInfos = new ArrayList<StudentInfo>();

            for (int j = 2; j < sheet.getLastRowNum() + 1; j++) {
                row = sheet.getRow(j);
                String sid = row.getCell(0).getStringCellValue().toString();
                UUID id = UUID.fromString(sid);
                if(row.getCell(1)!=null) {
                    row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                }
                String stuNo = row.getCell(1).getStringCellValue().toString();
                String pwd = row.getCell(2).getStringCellValue().toString();
                String salt = row.getCell(3).getStringCellValue().toString();
                String name = row.getCell(4).getStringCellValue().toString();
                if(row.getCell(5)!=null) {
                    row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                }
                String examId = row.getCell(5).getStringCellValue().toString();
                if(row.getCell(6)!=null) {
                    row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                }
                String idCard = row.getCell(6).getStringCellValue().toString();
                if(row.getCell(7)!=null) {
                    row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
                }
                String gender = row.getCell(7).getStringCellValue().toString();
                if(row.getCell(8)!=null) {
                    row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
                }
                String ethnic = row.getCell(8).getStringCellValue().toString();
                if(row.getCell(9)!=null) {
                    row.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
                }
                String political = row.getCell(9).getStringCellValue().toString();
                if(row.getCell(10)!=null) {
                    row.getCell(10).setCellType(Cell.CELL_TYPE_STRING);
                }
                String eduDegree = row.getCell(10).getStringCellValue().toString();
                if(row.getCell(11)!=null) {
                    row.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
                }
                String majorCode = row.getCell(11).getStringCellValue().toString();
                String majorName = row.getCell(12).getStringCellValue().toString();
                if(row.getCell(13)!=null) {
                    row.getCell(13).setCellType(Cell.CELL_TYPE_STRING);
                }
                String flangType = row.getCell(13).getStringCellValue().toString();
                if(row.getCell(14)!=null) {
                    row.getCell(14).setCellType(Cell.CELL_TYPE_STRING);
                }
                String flangType2 = row.getCell(14).getStringCellValue().toString();
                if(row.getCell(15)!=null) {
                    row.getCell(15).setCellType(Cell.CELL_TYPE_STRING);
                }
                String eduMode = row.getCell(15).getStringCellValue().toString();
                String homeland = row.getCell(16).getStringCellValue().toString();
                if(row.getCell(17)!=null) {
                    row.getCell(17).setCellType(Cell.CELL_TYPE_STRING);
                }
                String eduYear = row.getCell(17).getStringCellValue().toString();
                String startDate = "2013-08";
                String endDate = "2017-08";
                if(row.getCell(20)!=null) {
                    row.getCell(20).setCellType(Cell.CELL_TYPE_STRING);
                }
                String departmentId = row.getCell(20).getStringCellValue().toString();
                String department = row.getCell(21).getStringCellValue().toString();
                String className = row.getCell(22).getStringCellValue().toString();
                String tutorName = row.getCell(23).getStringCellValue().toString();
                String counselor = row.getCell(24).getStringCellValue().toString();
                LocalDate birthdate = LocalDate.now();
                if(row.getCell(26)!=null) {
                    row.getCell(26).setCellType(Cell.CELL_TYPE_STRING);
                }
                String haveEduHukou = row.getCell(26).getStringCellValue().toString();
                if(row.getCell(27)!=null) {
                    row.getCell(27).setCellType(Cell.CELL_TYPE_STRING);
                }
                String mobilephone = row.getCell(27).getStringCellValue().toString();
                String email = row.getCell(28).getStringCellValue().toString();
                if(row.getCell(29)!=null) {
                    row.getCell(29).setCellType(Cell.CELL_TYPE_STRING);
                }
                String qqNo = row.getCell(29).getStringCellValue().toString();
                String wechatNo = row.getCell(30).getStringCellValue().toString();

                String homeAddress = row.getCell(31).getStringCellValue().toString();
                if(row.getCell(32)!=null) {
                    row.getCell(32).setCellType(Cell.CELL_TYPE_STRING);
                }
                String homePhone = row.getCell(32).getStringCellValue().toString();
                if(row.getCell(33)!=null) {
                    row.getCell(33).setCellType(Cell.CELL_TYPE_STRING);
                }
                String haveReport = row.getCell(33).getStringCellValue().toString();
                if(row.getCell(34)!=null) {
                    row.getCell(34).setCellType(Cell.CELL_TYPE_STRING);
                }
                String status = row.getCell(34).getStringCellValue().toString();
                String creator = row.getCell(35).getStringCellValue().toString();
                String updator = row.getCell(36).getStringCellValue().toString();
                LocalDateTime stuCheckTime = LocalDateTime.now();
                LocalDateTime schCheckTime = LocalDateTime.now();
                LocalDateTime createTime = LocalDateTime.now();
                LocalDateTime updateTime = LocalDateTime.now();
                String trustee = row.getCell(41).getStringCellValue().toString();
                StudentInfo studentInfo = new StudentInfo(id, stuNo, pwd, salt, name, examId, idCard, gender, ethnic, political, eduDegree, majorCode, majorName, flangType, flangType2, eduMode, trustee, homeland, eduYear, startDate, endDate, departmentId, department, className, tutorName, counselor, birthdate,  haveEduHukou, mobilephone, email, qqNo, wechatNo, homeAddress, homePhone, haveReport, status, creator, updator);
                studentInfos.add(studentInfo);
                StudentInfo stuInfo  =    studentInfoService.saveStudentInfo(studentInfo);
                logger.info(stuInfo.toString());
            }

            return "导入成功";

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    ////从数据库中读取，创建excel 文件 ,提供下载用
    @RequestMapping(value = "/teacher/downloaStuExcel", method = RequestMethod.GET)
    public void downloadFile(HttpServletResponse response)
        throws IOException {
        List<StudentInfo> studentInfos = studentInfoService.findAllStudentInfo();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("countryDB");
            XSSFRow row = sheet.createRow((short) 1);
            XSSFRow rowTitle = sheet.createRow((short) 0);
            XSSFCell cell = null;
            cell = rowTitle.createCell((short) 0);
            cell.setCellValue("id主键");
            cell = row.createCell((short) 0);
            cell.setCellValue("id");
            cell = rowTitle.createCell((short) 1);
            cell.setCellValue("学号");
            cell = row.createCell((short) 1);
            cell.setCellValue("stuNo");
            cell = rowTitle.createCell((short) 2);
            cell.setCellValue("密码");
            cell = row.createCell((short) 2);
            cell.setCellValue("pwd");
            cell = rowTitle.createCell((short) 3);
            cell.setCellValue("盐");
            cell = row.createCell((short) 3);
            cell.setCellValue("salt");
            cell = rowTitle.createCell((short) 4);
            cell.setCellValue("姓名");
            cell = row.createCell((short) 4);
            cell.setCellValue("name");
            cell = rowTitle.createCell((short) 5);
            cell.setCellValue("准考证号");
            cell = row.createCell((short) 5);
            cell.setCellValue("examId");
            cell = rowTitle.createCell((short) 6);
            cell.setCellValue("身份证号");
            cell = row.createCell((short) 6);
            cell.setCellValue("idCard");
            cell = rowTitle.createCell((short) 7);
            cell.setCellValue("性别");
            cell = row.createCell((short) 7);
            cell.setCellValue("gender");
            cell = rowTitle.createCell((short) 8);
            cell.setCellValue("民族");
            cell = row.createCell((short) 8);
            cell.setCellValue("ethnic");
            cell = rowTitle.createCell((short) 9);
            cell.setCellValue("政治面貌");
            cell = row.createCell((short) 9);
            cell.setCellValue("political");
            cell = rowTitle.createCell((short) 10);
            cell.setCellValue("学历");
            cell = row.createCell((short) 10);
            cell.setCellValue("eduDegree");
            cell = rowTitle.createCell((short) 11);
            cell.setCellValue("专业代码");
            cell = row.createCell((short) 11);
            cell.setCellValue("majorCode");
            cell = rowTitle.createCell((short) 12);
            cell.setCellValue("专业名称");
            cell = row.createCell((short) 12);
            cell.setCellValue("majorName");
            cell = rowTitle.createCell((short) 13);
            cell.setCellValue("外语语种1");
            cell = row.createCell((short) 13);
            cell.setCellValue("flangType");
            cell = rowTitle.createCell((short) 14);
            cell.setCellValue("外语语种2");
            cell = row.createCell((short) 14);
            cell.setCellValue("flangType2");
            cell = rowTitle.createCell((short) 15);
            cell.setCellValue("培养方式");
            cell = row.createCell((short) 15);
            cell.setCellValue("eduMode");
            cell = rowTitle.createCell((short) 16);
            cell.setCellValue("生源地");
            cell = row.createCell((short) 16);
            cell.setCellValue("homeland");
            cell = rowTitle.createCell((short) 17);
            cell.setCellValue("学制");
            cell = row.createCell((short) 17);
            cell.setCellValue("eduYear");
            cell = rowTitle.createCell((short) 18);
            cell.setCellValue("入学时间");
            cell = row.createCell((short) 18);
            cell.setCellValue("startDate");
            cell = rowTitle.createCell((short) 19);
            cell.setCellValue("毕业时间");
            cell = row.createCell((short) 19);
            cell.setCellValue("endDate");
            cell = rowTitle.createCell((short) 20);
            cell.setCellValue("院系Id");
            cell = row.createCell((short) 20);
            cell.setCellValue("departmentId");
            cell = rowTitle.createCell((short) 21);
            cell.setCellValue("院系名称");
            cell = row.createCell((short) 21);
            cell.setCellValue("department");
            cell = rowTitle.createCell((short) 22);
            cell.setCellValue("班级名称");
            cell = row.createCell((short) 22);
            cell.setCellValue("className");
            cell = rowTitle.createCell((short) 23);
            cell.setCellValue("导师姓名");
            cell = row.createCell((short) 23);
            cell.setCellValue("tutorName");
            cell = rowTitle.createCell((short) 24);
            cell.setCellValue("辅导员姓名");
            cell = row.createCell((short) 24);
            cell.setCellValue("counselor");
            cell = rowTitle.createCell((short) 25);
            cell.setCellValue("出生日期");
            cell = row.createCell((short) 25);
            cell.setCellValue("birthdate");
            cell = rowTitle.createCell((short) 26);
            cell.setCellValue("户口是否转入学校（1，是，2 不是）");
            cell = row.createCell((short) 26);
            cell.setCellValue("haveEduHukou");
            cell = rowTitle.createCell((short) 27);
            cell.setCellValue("手机号码");
            cell = row.createCell((short) 27);
            cell.setCellValue("mobilephone");
            cell = rowTitle.createCell((short) 28);
            cell.setCellValue("邮箱");
            cell = row.createCell((short) 28);
            cell.setCellValue("email");
            cell = rowTitle.createCell((short) 29);
            cell.setCellValue("qq号码");
            cell = row.createCell((short) 29);
            cell.setCellValue("qqNo");
            cell = rowTitle.createCell((short) 30);
            cell.setCellValue("微信号码");
            cell = row.createCell((short) 30);
            cell.setCellValue("wechatNo");
            cell = rowTitle.createCell((short) 31);
            cell.setCellValue("家庭住址");
            cell = row.createCell((short) 31);
            cell.setCellValue("homeAddress");
            cell = rowTitle.createCell((short) 32);
            cell.setCellValue("家庭手机号");
            cell = row.createCell((short) 32);
            cell.setCellValue("homePhone");
            cell = rowTitle.createCell((short) 33);
            cell.setCellValue("是否上报");
            cell = row.createCell((short) 33);
            cell.setCellValue("haveReport");
            cell = rowTitle.createCell((short) 34);
            cell.setCellValue("教师审核状态");
            cell = row.createCell((short) 34);
            cell.setCellValue("status");
            cell = rowTitle.createCell((short) 35);
            cell.setCellValue("创建人");
            cell = row.createCell((short) 35);
            cell.setCellValue("creator");
            cell = rowTitle.createCell((short) 36);
            cell.setCellValue("修改人");
            cell = row.createCell((short) 36);
            cell.setCellValue("updator");
            cell = rowTitle.createCell((short) 37);
            cell.setCellValue("学生审核时间");
            cell = row.createCell((short) 37);
            cell.setCellValue("stuCheckTime");
            cell = rowTitle.createCell((short) 38);
            cell.setCellValue("教师审核时间");
            cell = row.createCell((short) 38);
            cell.setCellValue("schCheckTime");
            cell = rowTitle.createCell((short) 39);
            cell.setCellValue("创建时间");
            cell = row.createCell((short) 39);
            cell.setCellValue("createTime");
            cell = rowTitle.createCell((short) 40);
            cell.setCellValue("更新时间");
            cell = row.createCell((short) 40);
            cell.setCellValue("updateTime");
            cell = rowTitle.createCell((short) 41);
            cell.setCellValue("定向委培单位");
            cell = row.createCell((short) 41);
            cell.setCellValue("trustee");

            int i = 2;
            for (StudentInfo studentInfo : studentInfos) {
                logger.info(studentInfo.toString() + "*****************88");
                row = sheet.createRow(i);
                cell = row.createCell(0);
                cell.setCellValue(studentInfo.getId().toString());
                cell = row.createCell(1);
                cell.setCellValue(studentInfo.getStuNo());
                cell = row.createCell(2);
                cell.setCellValue(studentInfo.getPwd());
                cell = row.createCell(3);
                cell.setCellValue(studentInfo.getSalt());
                cell = row.createCell(4);
                cell.setCellValue(studentInfo.getName());
                cell = row.createCell(5);
                cell.setCellValue(studentInfo.getExamId());
                cell = row.createCell(6);
                cell.setCellValue(studentInfo.getIdCard());
                cell = row.createCell(7);
                cell.setCellValue(studentInfo.getGender());
                cell = row.createCell(8);
                cell.setCellValue(studentInfo.getEthnic());
                cell = row.createCell(9);
                cell.setCellValue(studentInfo.getPolitical());
                cell = row.createCell(10);
                cell.setCellValue(studentInfo.getEduDegree());
                cell = row.createCell(11);
                cell.setCellValue(studentInfo.getMajorCode());
                cell = row.createCell(12);
                cell.setCellValue(studentInfo.getMajorName());
                cell = row.createCell(13);
                cell.setCellValue(studentInfo.getFlangType());
                cell = row.createCell(14);
                cell.setCellValue(studentInfo.getFlangType2());
                cell = row.createCell(15);
                cell.setCellValue(studentInfo.getEduMode());
                cell = row.createCell(16);
                cell.setCellValue(studentInfo.getHomeland());
                cell = row.createCell(17);
                cell.setCellValue(studentInfo.getEduYear());
                cell = row.createCell(18);
                cell.setCellValue(studentInfo.getStartDate().toString());
                cell = row.createCell(19);
                cell.setCellValue(studentInfo.getEndDate().toString());
                cell = row.createCell(20);
                cell.setCellValue(studentInfo.getDepartmentId());
                cell = row.createCell(21);
                cell.setCellValue(studentInfo.getDepartment());
                cell = row.createCell(22);
                cell.setCellValue(studentInfo.getClassName());
                cell = row.createCell(23);
                cell.setCellValue(studentInfo.getTutorName());
                cell = row.createCell(24);
                cell.setCellValue(studentInfo.getCounselor());
                cell = row.createCell(25);
                cell.setCellValue(studentInfo.getBirthdate().toString());
                cell = row.createCell(26);
                cell.setCellValue(studentInfo.getHaveEduHukou());
                cell = row.createCell(27);
                cell.setCellValue(studentInfo.getMobilephone());
                cell = row.createCell(28);
                cell.setCellValue(studentInfo.getEmail());
                cell = row.createCell(29);
                cell.setCellValue(studentInfo.getQqNo());
                cell = row.createCell(30);
                cell.setCellValue(studentInfo.getWechatNo());
                cell = row.createCell(31);
                cell.setCellValue(studentInfo.getHomeAddress());
                cell = row.createCell(32);
                cell.setCellValue(studentInfo.getHomePhone());
                cell = row.createCell(33);
                cell.setCellValue(studentInfo.getHaveReport());
                cell = row.createCell(34);
                cell.setCellValue(studentInfo.getStatus());
                cell = row.createCell(35);
                cell.setCellValue(studentInfo.getCreator());
                cell = row.createCell(36);
                cell.setCellValue(studentInfo.getUpdator());
                cell = row.createCell(37);
                cell.setCellValue("getStuCheckTime()");
//                cell.setCellValue(studentInfo.getStuCheckTime().toString());
                cell = row.createCell(38);
                cell.setCellValue("getSchCheckTime()");
//                cell.setCellValue(studentInfo.getSchCheckTime().toString());
                cell = row.createCell(39);
                cell.setCellValue(studentInfo.getCreateTime().toString());
                cell = row.createCell(40);
                cell.setCellValue(studentInfo.getUpdateTime().toString());
                cell = row.createCell(41);
                cell.setCellValue(studentInfo.getTrustee());
                i++;
            }

            try {
                ByteArrayOutputStream os = new ByteArrayOutputStream();

                workbook.write(os);
                byte[] bytes = os.toByteArray();

                response.setContentType("application/x-msdownload");
                response.setHeader("Content-Disposition", "attachment;filename=stuInformation.xlsx");
                response.setContentLength(bytes.length);
                response.getOutputStream().write(bytes);
                response.getOutputStream().flush();
                response.getOutputStream().close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @GetMapping("/teacher/excelinfo.html")
    public String excel(){
        return "excel";
    }




    @PostMapping("/teacher/intoStuInfoFinal")
    @ResponseBody
    public String intoStuInfoFinal(@RequestParam("file") MultipartFile multfile) {

        System.out.println("开始");
        String fileName1 = multfile.getOriginalFilename();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        FileOutputStream fos = null;

        File minFile = new File(request.getSession().getServletContext().getRealPath("/")+ "excelUpload");

          minFile.mkdirs();



        try {
            logger.info(request.getSession().getServletContext().getRealPath("/")+ "excelUpload/"+sdf.format(new Date())+fileName1.substring(fileName1.lastIndexOf("."))+"***********");
            fos = new FileOutputStream(request.getSession().getServletContext().getRealPath("/")+ "excelUpload/"+sdf.format(new Date())+fileName1.substring(fileName1.lastIndexOf(".")));
            fos.write(multfile.getBytes());
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {

            if (minFile.exists()){
                minFile.delete();
            }
            e.printStackTrace();
        } catch (IOException e) {
            if (minFile.exists()){
                minFile.delete();
            }
            e.printStackTrace();
        }finally {

        }


        //通过上传的文件取得文件名称
        String fileName = multfile.getOriginalFilename();
        //取得文件
//        File file = new File("C:\\Users\\HealerJean\\Desktop\\" + fileName);
        File file = new File(request.getSession().getServletContext().getRealPath("/")+ "excelUpload/"+sdf.format(new Date())+fileName1.substring(fileName1.lastIndexOf(".")));

        Workbook book = null;
        try {
            if ((file.getName()).endsWith("xls".trim())) {
                //2003
                book = new HSSFWorkbook(new FileInputStream(file));
            } else if ((file.getName()).endsWith("xlsx".trim())) {
                //2007
                book = new XSSFWorkbook(new FileInputStream(file));
            } else {
                logger.info("文件格式不支持");
            }
            //获得第一个文本簿
            Sheet sheet = book.getSheetAt(0);
            //得到标题说明 比如id ，stuNo等
            List<String> titles = new ArrayList<String>();
            //表头那一行 ，第一行为说明，第二行才是id等
            Row titleRow = sheet.getRow(1);
            for (int i = 0; i < titleRow.getLastCellNum(); i++) {
                String titleCell = titleRow.getCell(i).getStringCellValue();
                titles.add(titleCell);
            }
            Row row = null;
            long rowNum = 0; //有效行数

            for (int j = 2; j < sheet.getLastRowNum() + 1; j++) {
                row = sheet.getRow(j);
                if(row.getCell(0)!=null) {
                    row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                }
                String stuNo = row.getCell(0).getStringCellValue().toString();
                String name = row.getCell(1).getStringCellValue().toString();
                if(row.getCell(2)!=null) {
                    row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                }
                String examId = row.getCell(2).getStringCellValue().toString();
                if(row.getCell(3)!=null) {
                    row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                }
                String idCard = row.getCell(3).getStringCellValue().toString();
                if(row.getCell(4)!=null) {
                    row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                }
                String gender = row.getCell(4).getStringCellValue().toString();
                if(row.getCell(5)!=null) {
                    row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                }
                String ethnic = row.getCell(5).getStringCellValue().toString();
                if(row.getCell(6)!=null) {
                    row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                }
                String political = row.getCell(6).getStringCellValue().toString();
                if(row.getCell(7)!=null) {
                    row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
                }
                String eduDegree = row.getCell(7).getStringCellValue().toString();
                if(row.getCell(8)!=null) {
                    row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
                }
                String majorCode = row.getCell(8).getStringCellValue().toString();
                String majorName = row.getCell(9).getStringCellValue().toString();
                if(row.getCell(10)!=null) {
                    row.getCell(10).setCellType(Cell.CELL_TYPE_STRING);
                }
                String flangType = row.getCell(10).getStringCellValue().toString();
                if(row.getCell(11)!=null) {
                    row.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
                }
                String flangType2 = row.getCell(11).getStringCellValue().toString();
                if(row.getCell(12)!=null) {
                    row.getCell(12).setCellType(Cell.CELL_TYPE_STRING);
                }
                String eduMode = row.getCell(12).getStringCellValue().toString();
                String homeland = row.getCell(13).getStringCellValue().toString();
                if(row.getCell(14)!=null) {
                    row.getCell(14).setCellType(Cell.CELL_TYPE_STRING);
                }
                String eduYear = row.getCell(14).getStringCellValue().toString();
                if(row.getCell(15)!=null) {
                    row.getCell(15).setCellType(Cell.CELL_TYPE_STRING);
                }
                String startDate = row.getCell(15).getStringCellValue().toString();
                if(row.getCell(16)!=null) {
                    row.getCell(16).setCellType(Cell.CELL_TYPE_STRING);
                }
                String endDate = row.getCell(16).getStringCellValue().toString();
                if(row.getCell(17)!=null) {
                    row.getCell(17).setCellType(Cell.CELL_TYPE_STRING);
                }
                String departmentId = row.getCell(17).getStringCellValue().toString();
                String department = row.getCell(18).getStringCellValue().toString();
                String className = row.getCell(19).getStringCellValue().toString();
                String tutorName = row.getCell(20).getStringCellValue().toString();
                String counselor = row.getCell(21).getStringCellValue().toString();
                if(row.getCell(22)!=null) {
                    row.getCell(22).setCellType(Cell.CELL_TYPE_STRING);
                }
                LocalDate birthdate = LocalDate.parse(row.getCell(22).getStringCellValue().toString());

                if(row.getCell(23)!=null) {
                    row.getCell(23).setCellType(Cell.CELL_TYPE_STRING);
                }
                String haveEduHukou = row.getCell(23).getStringCellValue().toString();
                if(row.getCell(24)!=null) {
                    row.getCell(24).setCellType(Cell.CELL_TYPE_STRING);
                }
                String mobilephone = row.getCell(24).getStringCellValue().toString();
                String email = row.getCell(25).getStringCellValue().toString();
                if(row.getCell(26)!=null) {
                    row.getCell(26).setCellType(Cell.CELL_TYPE_STRING);
                }
                String qqNo = row.getCell(26).getStringCellValue().toString();
                if(row.getCell(27)!=null) {
                    row.getCell(27).setCellType(Cell.CELL_TYPE_STRING);
                }
                String wechatNo = row.getCell(27).getStringCellValue().toString();

                String homeAddress = row.getCell(28).getStringCellValue().toString();
                if(row.getCell(29)!=null) {
                    row.getCell(29).setCellType(Cell.CELL_TYPE_STRING);
                }
                String homePhone = row.getCell(29).getStringCellValue().toString();
                if(row.getCell(30)!=null) {
                    row.getCell(30).setCellType(Cell.CELL_TYPE_STRING);
                }
                String trustee = row.getCell(30).getStringCellValue().toString();

                StudentInfo studentInfo = new StudentInfo();


                studentInfo.setStuNo(stuNo);
                studentInfo.setName(name);
                studentInfo.setExamId(examId);
                studentInfo.setIdCard(idCard);
                studentInfo.setGender(gender);
                studentInfo.setEthnic(ethnic);
                studentInfo.setPolitical(political);
                studentInfo.setEduDegree(eduDegree);
                studentInfo.setMajorCode(majorCode);
                studentInfo.setMajorName(majorName);
                studentInfo.setFlangType(flangType);
                studentInfo.setFlangType2(flangType2);
                studentInfo.setEduMode(eduMode);
                studentInfo.setTrustee(trustee);
                studentInfo.setHomeland(homeland);
                studentInfo.setEduYear(eduYear);
                studentInfo.setStartDate(startDate);
                studentInfo.setEndDate(endDate);
                studentInfo.setDepartment(department);
                studentInfo.setDepartmentId(departmentId);
                studentInfo.setClassName(className);
                studentInfo.setTutorName(tutorName);
                studentInfo.setCounselor(counselor);
                studentInfo.setBirthdate(birthdate);
                studentInfo.setHaveEduHukou(haveEduHukou);
                studentInfo.setMobilephone(mobilephone);
                studentInfo.setEmail(email);
                studentInfo.setQqNo(qqNo);
                studentInfo.setWechatNo(wechatNo);
                studentInfo.setHomeAddress(homeAddress);
                studentInfo.setHomePhone(homePhone);



                //获得随机盐
                SecureRandomNumberGenerator secureRandomNumberGenerator = new SecureRandomNumberGenerator();
                String salt = secureRandomNumberGenerator.nextBytes().toHex();
                //对密码加密后,将加密后的密码和盐存入对象
                logger.info(idCard+"idcard");
//                str=str.Substring(str.Length-i); // or str=str.Remove(0,str.Length-i);
                String password = idCard.substring(idCard.length()-6);
                logger.info(password+"********");
                String  pwd = new Md5Hash(password, salt).toString();
                studentInfo.setSalt(salt);
                studentInfo.setPwd(pwd);
                //教师审核状态 未审核  设置为00
                studentInfo.setStatus("00");
                //学生没有上报
                studentInfo.setHaveReport("0");
                UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
                studentInfo.setCreator(userPrincipal.getRealName());
                studentInfo.setUpdator(userPrincipal.getRealName());

                //就业去向
                GraduateDestination graduateDestination = new GraduateDestination();
                graduateDestination.setStudentInfo(studentInfo);
                graduateDestination.setStatus("00");
                graduateDestination.setStuStatus("00");
                graduateDestinationService.save(graduateDestination);

                StudentInfo stuInfo  =    studentInfoService.saveStudentInfo(studentInfo);
                logger.info(stuInfo.toString());
            }


            return "导入成功";

        } catch (Exception e) {

            e.printStackTrace();
        }finally {
            if (file.exists()) {
                file.delete();
            }if (minFile.exists()){
                minFile.delete();
            }
        }
        return null;
    }









    ////从数据库中读取，创建excel 文件 ,提供下载用
    @RequestMapping(value = "/teacher/downloaStuExcelFinal", method = RequestMethod.GET)
    public void downloadFileFinal(HttpServletResponse response)
        throws IOException {
        List<StudentInfo> studentInfos = studentInfoService.findAllStudentInfo();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("countryDB");
            XSSFRow rowTitle = sheet.createRow((short) 0);
            XSSFRow row = sheet.createRow((short) 1);
            XSSFCell cell = null;

            cell = rowTitle.createCell((short) 0);
            cell.setCellValue("学号");
            cell = row.createCell((short) 0);
            cell.setCellValue("stuNo");
            cell = rowTitle.createCell((short) 1);
            cell.setCellValue("姓名");
            cell = row.createCell((short) 1);
            cell.setCellValue("name");
            cell = rowTitle.createCell((short) 2);
            cell.setCellValue("准考证号");
            cell = row.createCell((short) 2);
            cell.setCellValue("examId");
            cell = rowTitle.createCell((short) 3);
            cell.setCellValue("身份证号");
            cell = row.createCell((short) 3);
            cell.setCellValue("idCard");
            cell = rowTitle.createCell((short) 4);
            cell.setCellValue("性别");
            cell = row.createCell((short) 4);
            cell.setCellValue("gender");
            cell = rowTitle.createCell((short) 5);
            cell.setCellValue("民族");
            cell = row.createCell((short) 5);
            cell.setCellValue("ethnic");
            cell = rowTitle.createCell((short) 6);
            cell.setCellValue("政治面貌");
            cell = row.createCell((short) 6);
            cell.setCellValue("political");
            cell = rowTitle.createCell((short) 7);
            cell.setCellValue("学历");
            cell = row.createCell((short) 7);
            cell.setCellValue("eduDegree");
            cell = rowTitle.createCell((short) 8);
            cell.setCellValue("专业代码");
            cell = row.createCell((short) 8);
            cell.setCellValue("majorCode");
            cell = rowTitle.createCell((short) 9);
            cell.setCellValue("专业名称");
            cell = row.createCell((short) 9);
            cell.setCellValue("majorName");
            cell = rowTitle.createCell((short) 10);
            cell.setCellValue("外语语种1");
            cell = row.createCell((short) 10);
            cell.setCellValue("flangType");
            cell = rowTitle.createCell((short) 11);
            cell.setCellValue("外语语种2");
            cell = row.createCell((short) 11);
            cell.setCellValue("flangType2");
            cell = rowTitle.createCell((short) 12);
            cell.setCellValue("培养方式");
            cell = row.createCell((short) 12);
            cell.setCellValue("eduMode");
            cell = rowTitle.createCell((short) 13);
            cell.setCellValue("生源地");
            cell = row.createCell((short) 13);
            cell.setCellValue("homeland");
            cell = rowTitle.createCell((short) 14);
            cell.setCellValue("学制");
            cell = row.createCell((short) 14);
            cell.setCellValue("eduYear");
            cell = rowTitle.createCell((short) 15);
            cell.setCellValue("入学时间");
            cell = row.createCell((short) 15);
            cell.setCellValue("startDate");
            cell = rowTitle.createCell((short) 16);
            cell.setCellValue("毕业时间");
            cell = row.createCell((short) 16);
            cell.setCellValue("endDate");
            cell = rowTitle.createCell((short) 17);
            cell.setCellValue("院系Id");
            cell = row.createCell((short) 17);
            cell.setCellValue("departmentId");
            cell = rowTitle.createCell((short) 18);
            cell.setCellValue("院系名称");
            cell = row.createCell((short) 18);
            cell.setCellValue("department");
            cell = rowTitle.createCell((short) 19);
            cell.setCellValue("班级名称");
            cell = row.createCell((short) 19);
            cell.setCellValue("className");
            cell = rowTitle.createCell((short) 20);
            cell.setCellValue("导师姓名");
            cell = row.createCell((short) 20);
            cell.setCellValue("tutorName");
            cell = rowTitle.createCell((short) 21);
            cell.setCellValue("辅导员姓名");
            cell = row.createCell((short) 21);
            cell.setCellValue("counselor");
            cell = rowTitle.createCell((short) 22);
            cell.setCellValue("出生日期");
            cell = row.createCell((short) 22);
            cell.setCellValue("birthdate");
            cell = rowTitle.createCell((short) 23);
            cell.setCellValue("户口是否转入学校（1，是，2 不是）");
            cell = row.createCell((short) 23);
            cell.setCellValue("haveEduHukou");
            cell = rowTitle.createCell((short) 24);
            cell.setCellValue("手机号码");
            cell = row.createCell((short) 24);
            cell.setCellValue("mobilephone");
            cell = rowTitle.createCell((short) 25);
            cell.setCellValue("邮箱");
            cell = row.createCell((short) 25);
            cell.setCellValue("email");
            cell = rowTitle.createCell((short) 26);
            cell.setCellValue("qq号码");
            cell = row.createCell((short) 26);
            cell.setCellValue("qqNo");
            cell = rowTitle.createCell((short) 27);
            cell.setCellValue("微信号码");
            cell = row.createCell((short) 27);
            cell.setCellValue("wechatNo");
            cell = rowTitle.createCell((short) 28);
            cell.setCellValue("家庭住址");
            cell = row.createCell((short) 28);
            cell.setCellValue("homeAddress");
            cell = rowTitle.createCell((short) 29);
            cell.setCellValue("家庭手机号");
            cell = row.createCell((short) 29);
            cell.setCellValue("homePhone");
            cell = rowTitle.createCell((short) 30);
            cell.setCellValue("定向委培单位");
            cell = row.createCell((short) 30);
            cell.setCellValue("trustee");

            int i = 2;
            for (StudentInfo studentInfo : studentInfos) {
                logger.info(studentInfo.toString() + "*****************88");
                row = sheet.createRow(i);

                cell = row.createCell(0);
                cell.setCellValue(studentInfo.getStuNo());
                cell = row.createCell(1);
                cell.setCellValue(studentInfo.getName());
                cell = row.createCell(2);
                cell.setCellValue(studentInfo.getExamId());
                cell = row.createCell(3);
                cell.setCellValue(studentInfo.getIdCard());
                cell = row.createCell(4);
                cell.setCellValue(studentInfo.getGender());
                cell = row.createCell(5);
                cell.setCellValue(studentInfo.getEthnic());
                cell = row.createCell(6);
                cell.setCellValue(studentInfo.getPolitical());
                cell = row.createCell(7);
                cell.setCellValue(studentInfo.getEduDegree());
                cell = row.createCell(8);
                cell.setCellValue(studentInfo.getMajorCode());
                cell = row.createCell(9);
                cell.setCellValue(studentInfo.getMajorName());
                cell = row.createCell(10);
                cell.setCellValue(studentInfo.getFlangType());
                cell = row.createCell(11);
                cell.setCellValue(studentInfo.getFlangType2());
                cell = row.createCell(12);
                cell.setCellValue(studentInfo.getEduMode());
                cell = row.createCell(13);
                cell.setCellValue(studentInfo.getHomeland());
                cell = row.createCell(14);
                cell.setCellValue(studentInfo.getEduYear());
                cell = row.createCell(15);
                cell.setCellValue(studentInfo.getStartDate());
                cell = row.createCell(16);
                cell.setCellValue(studentInfo.getEndDate());
                cell = row.createCell(17);
                cell.setCellValue(studentInfo.getDepartmentId());
                cell = row.createCell(18);
                cell.setCellValue(studentInfo.getDepartment());
                cell = row.createCell(19);
                cell.setCellValue(studentInfo.getClassName());
                cell = row.createCell(20);
                cell.setCellValue(studentInfo.getTutorName());
                cell = row.createCell(21);
                cell.setCellValue(studentInfo.getCounselor());
                cell = row.createCell(22);
                cell.setCellValue(studentInfo.getBirthdate().toString());
                cell = row.createCell(23);
                cell.setCellValue(studentInfo.getHaveEduHukou());
                cell = row.createCell(24);
                cell.setCellValue(studentInfo.getMobilephone());
                cell = row.createCell(25);
                cell.setCellValue(studentInfo.getEmail());
                cell = row.createCell(26);
                cell.setCellValue(studentInfo.getQqNo());
                cell = row.createCell(27);
                cell.setCellValue(studentInfo.getWechatNo());
                cell = row.createCell(28);
                cell.setCellValue(studentInfo.getHomeAddress());
                cell = row.createCell(29);
                cell.setCellValue(studentInfo.getHomePhone());
                cell = row.createCell(30);
                cell.setCellValue(studentInfo.getTrustee());
                i++;
            }

            try {
                ByteArrayOutputStream os = new ByteArrayOutputStream();

                workbook.write(os);
                byte[] bytes = os.toByteArray();

                response.setContentType("application/x-msdownload");
                response.setHeader("Content-Disposition", "attachment;filename=stuInformationFinal.xlsx");
                response.setContentLength(bytes.length);
                response.getOutputStream().write(bytes);
                response.getOutputStream().flush();
                response.getOutputStream().close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }




}
