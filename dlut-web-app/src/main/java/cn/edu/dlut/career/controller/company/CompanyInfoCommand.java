package cn.edu.dlut.career.controller.company;
import cn.edu.dlut.career.domain.company.CompanyInfo;
import cn.edu.dlut.career.dto.ResponseInfo;
import cn.edu.dlut.career.service.company.CompanyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
/**
 * Created by wei on 2017/3/23.
 */
@Transactional
@Controller
public class CompanyInfoCommand {
    @Autowired
    private CompanyInfoService companyInfoService;
    /**
     * 用户注册页面的跳转
     * @return
     */
    @GetMapping("/register/company/register.html")
    public String register(){
        return "companyHTML/register";
    }
    /**
     * 企业信息修改页面跳转
     * @return
     */
    @GetMapping("/company/unitInformation.html")
    public String unitInformation(){
        return "companyHTML/unitInformation";
    }
    /**
     * 公司注册信息
     * @param companyInfo
     * @return
     */
    @PostMapping("/register/company/companyInfo")
    public String saveCompany(CompanyInfo companyInfo,@RequestParam("file") MultipartFile file) throws IOException {
        String path ="";
        if (!file.isEmpty()) {
            try {
                // 上传图片
                path = "C:/upload/";
                BufferedOutputStream out = new BufferedOutputStream(
                    new FileOutputStream(new File(path+file.getOriginalFilename())));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            companyInfo.setLicense(path);
            companyInfoService.saveCompany(companyInfo);
            return "login";
        }
        return "companyHTML/register";
    }
    /**
     * 查看表中是否已经存在该邮箱
     * @param email
     * @return
     */
    @GetMapping("/register/company/findByEmail")
    @ResponseBody
    public ResponseInfo findByEmail(String email){
        CompanyInfo companyInfo = companyInfoService.findByEmail(email);
        ResponseInfo responseInfo = new ResponseInfo();
        if(companyInfo != null){
            responseInfo.setStatus(403);
            responseInfo.setMessage("邮箱已存在");
        }else {
            responseInfo.setStatus(200);
            responseInfo.setMessage("邮箱不存在");
        }
        return responseInfo;
    }
    /**
     * 企业用户对企业信息的维护修改
     * @param companyInfo
     * @return
     */
    @PostMapping("/company/companyInfo")
    public String updateCompany(CompanyInfo companyInfo){
        companyInfoService.update(companyInfo);
        return "companyHTML/unitlnformation";
    }
}
