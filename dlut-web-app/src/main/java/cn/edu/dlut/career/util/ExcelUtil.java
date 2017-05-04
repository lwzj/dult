package cn.edu.dlut.career.util;


import cn.edu.dlut.career.domain.student.StudentInfo;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by HealerJean on 2017/4/12.
 */
public class ExcelUtil {

    //读取单个单元格

    public static void main(String[] args)throws Exception {
    }

    //调用excel并得到数据库
        public List<StudentInfo> getStuInfos(File file){
//        File file = new File("C:\\Users\\HealerJean\\Desktop\\stuInformationFinal.xlsx");
        List<Map<String,String>> maps = getExcelContent(file.getPath());
        List<StudentInfo> studentInfos = new ArrayList<StudentInfo>();
        for (Map<String,String> map : maps){
            StudentInfo studentInfo = new StudentInfo();
            for (String key : map.keySet()) {
                String value = map.get(key);
          /*      SY_LXDH2	联系电话
                SY_BYSLX	毕业生类型
                SY_BZ备注
                SY_CT	冲突
                SY_YJSB已经上报
                SY_ZSB	有招生办数据
                SY_DRBZ	导入备注
                SY_YJS	应届生*/
                switch (key){
                    case "1": //学号
                        studentInfo.setStuNo(value);
                        break;
                    case "SY_XM": //姓名
                        studentInfo.setName(value);
                        break;
                    case "SY_MZ": //民族
                        studentInfo.setEthnic(value);
                        break;
                    case "SY_SFZH"://身份证号
                        studentInfo.setIdCard(value);
                        break;
                    case "SY_XB":   //性别
                        studentInfo.setGender(value);
                        break;
                    case "SY_ZZMM"://政治面貌
                        studentInfo.setPolitical(value);
                        break;
                    case "2":
                        studentInfo.setExamId(value);
                        break;
                    case "SY_XL"://学历
                        studentInfo.setEduDegree(value);
                        break;
                    case "SY_ZY"://专业
                        studentInfo.setMajorCode(value);
                        break;
                    case "3":
                        studentInfo.setMajorName(value);
                        break;
                    case "SY_YUZH1"://语种1
                        studentInfo.setFlangType(value);
                        break;
                    case "SY_YUZH2"://语种2
                        studentInfo.setFlangType2(value);
                        break;
                    case "SY_PYFS"://培养方式
                        studentInfo.setEduMode(value);
                        break;
                    case "SY_DXHWPDW"://定向或委培单位
                        studentInfo.setTrustee(value);
                        break;
                    case "SY_SYD"://生源地
                        studentInfo.setHomeland(value);
                        break;
                    case "SY_XZ"://学制
                        studentInfo.setEduYear(value);
                        break;
                    case "SY_RXSJ": //入学时间
                        studentInfo.setStartDate(value);
                        break;
                    case "SY_BYSJ": //毕业时间
                        studentInfo.setEndDate(value);
                        break;
                    case "4":
                        studentInfo.setDepartment(value);
                        break;
                    case "SY_YUANX"://院系
                        studentInfo.setDepartmentId(value);
                        break;
                    case "SY_BJ"://班级
                        studentInfo.setClassName(value);
                        break;
                    case "SY_DS"://导师
                        studentInfo.setTutorName(value);
                        break;
                    case "SY_FDY"://辅导员
                        studentInfo.setCounselor(value);
                        break;
                    case "SY_CSRQ"://出生日期
                        studentInfo.setBirthdate(LocalDate.parse(value));
                        break;
                    case "SY_HKSFZX"://户口是否在校
                        studentInfo.setHaveEduHukou(value);
                        break;
                    case "SY_LXDH"://	联系电话
                        studentInfo.setMobilephone(value);
                        break;
                    case "SY_YX":
                        studentInfo.setEmail(value);
                        break;
                    case "5":
                        studentInfo.setQqNo(value);
                        break;
                    case "6":
                        studentInfo.setWechatNo(value);
                        break;
                    case "7":
                        studentInfo.setHomeAddress(value);
                        break;
                    case "8":
                        studentInfo.setHomePhone(value);
                        break;
                }
            }
            studentInfos.add(studentInfo);
        }
        return null;
    }

    //读取内容，进入map
    public  List<Map<String,String>> getExcelContent(String filePath) {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Workbook book = null;
        File file = new File(filePath);

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

            Sheet sheet = book.getSheetAt(0);
            //得到标题
            List<String> titles = new ArrayList<String>();
            //表头那一行
            Row titleRow = sheet.getRow(0);


     /*       //表头那个单元格
            Cell titleCell1 = titleRow.getCell(0);
            String title = titleCell1.getStringCellValue();
            System.out.println("标题是："+title);*/
            //表头那个单元格
/*
            System.out.println(titleRow.getLastCellNum()+1);
*/
            for (int i = 0; i < titleRow.getLastCellNum(); i++) {
                String titleCell = titleRow.getCell(i).getStringCellValue();
                titles.add(titleCell);
            }


            Row row = null;
            long rowNum = 0; //有效行数
            for (int j = 1; j < sheet.getLastRowNum() + 1; j++) {
                row = sheet.getRow(j);
                Map<String, String> map = new HashMap<String, String>();
                for (int i = 0; i < row.getLastCellNum(); i++) {
                    if(row.getCell(i)!=null) {
                        row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
                    }
                    String strValue = row.getCell(i).getStringCellValue();
                    map.put(titles.get(i), strValue);
                }
                list.add(map);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  null;
    }
}
