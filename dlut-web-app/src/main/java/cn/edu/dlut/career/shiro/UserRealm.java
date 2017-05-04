package cn.edu.dlut.career.shiro;

import cn.edu.dlut.career.dto.company.RecLoginDTO;
import cn.edu.dlut.career.dto.school.TeacherLoginDTO;
import cn.edu.dlut.career.dto.student.StuLoginDTO;
import cn.edu.dlut.career.service.company.CompanyInfoService;
import cn.edu.dlut.career.service.school.TeacherInfoService;
import cn.edu.dlut.career.service.student.StudentInfoService;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户登录认证处理
 */
public class UserRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    private CompanyInfoService companyInfoService;

    @Autowired
    private StudentInfoService studentInfoService;

    @Autowired
    private TeacherInfoService teacherInfoService;
    /**
     * 用户认证处理
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
        throws AuthenticationException {
        SimpleAuthenticationInfo info = null;
        UserLoginToken token = (UserLoginToken) authcToken;
        String username = token.getUsername();
        //通过type判断登录的类型，决定调用哪一个认证方法
        String type = token.getType();
        if (username == null) {
            throw new AccountException("Null usernames are not allowed by this realm.");
        }
        if (token.getPassword().length < 1) {
            throw new UnknownAccountException("Null password are not allowed by this realm.");
        }

        switch (type) {
            case "COMPANY": // 企业认证
                RecLoginDTO recLoginDTO  = companyInfoService.findLoginInfo(username);
                if(recLoginDTO!=null){
                    //对原密码用盐加密处理后认证
                    String salt = recLoginDTO.getSalt();
                    char[] pwd = new Md5Hash(token.getPassword(), salt).toString().toCharArray();
                    token.setPassword(pwd);
                    info= new SimpleAuthenticationInfo(new UserPrincipal(recLoginDTO.getId(),recLoginDTO.getName(),
                        recLoginDTO.getId().toString(),recLoginDTO.getName(),recLoginDTO.getEmail(),"COMPANY","COMPANY"),
                        recLoginDTO.getPwd(),getName());
                    logger.info("用户:" + username + "登陆");
                } else{
                    throw  new AuthenticationException("No this user.");
                }
                break;
            case "STUDENT": // 学生认证
                StuLoginDTO stuLoginDTO  = studentInfoService.findLoginInfo(username);
                logger.info(stuLoginDTO.getId()+":"+stuLoginDTO.getName()+"登录中...");
                if(stuLoginDTO!=null){
                    //对原密码用盐加密处理后认证
                    String salt = stuLoginDTO.getSalt();
                    char[] pwd = new Md5Hash(token.getPassword(), salt).toString().toCharArray();
                    token.setPassword(pwd);
                    info= new SimpleAuthenticationInfo(new UserPrincipal(stuLoginDTO.getId(),stuLoginDTO.getName(),
                        stuLoginDTO.getId().toString(),stuLoginDTO.getName(),stuLoginDTO.getStuNo(),"STUDENT","STUDENT"),
                        stuLoginDTO.getPwd(),
                        getName());
                    logger.info("用户:" + username + "登陆");
                } else{
                    throw  new AuthenticationException("No this user.");
                }
                break;
            case "TEACHER": // 教师认证
                TeacherLoginDTO teacherLoginDTO  = teacherInfoService.findLoginInfo(username);
                logger.info(teacherLoginDTO.getId()+":"+teacherLoginDTO.getName()+"登录中...");
                if(teacherLoginDTO!=null){
                    //对原密码用盐加密处理后认证
                    String salt = teacherLoginDTO.getSalt();
                    char[] pwd = new Md5Hash(token.getPassword(), salt).toString().toCharArray();
                    token.setPassword(pwd);
                    //设置principal类型
                    String principal ="";
                    if("0".equals(teacherLoginDTO.getType())&&"0".equals(teacherLoginDTO.getIsAdmin())){
                        //校级普通老师
                        principal="SCHOOL";
                    }else if("0".equals(teacherLoginDTO.getType())&&"1".equals(teacherLoginDTO.getIsAdmin())){
                        //校级管理员
                        principal="SCHADMIN";
                    }else if("1".equals(teacherLoginDTO.getType())&&"0".equals(teacherLoginDTO.getIsAdmin())){
                        //院级普通老师
                        principal="ACADEMY";
                    }else if("1".equals(teacherLoginDTO.getType())&&"1".equals(teacherLoginDTO.getIsAdmin())){
                        //院级管理员
                        principal="ACADMIN";
                    }
                    info= new SimpleAuthenticationInfo(new UserPrincipal(teacherLoginDTO.getId(),teacherLoginDTO.getName(),
                        teacherLoginDTO.getDepartmentId(),teacherLoginDTO.getDepartment(),teacherLoginDTO.getUsername(),"TEACHER",principal),
                        teacherLoginDTO.getPwd(),
                        getName());
                    logger.info("用户:" + username + "登陆");
                } else{
                    throw  new AuthenticationException("No this user.");
                }
                break;
            default:
                break;
        }
        return info;
    }

    /**
     * 获取用户授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }
        Object obj = getAvailablePrincipal(principals);
        UserPrincipal principal = (UserPrincipal) obj;
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (principal.getRole()!=null) {
            principal.getRole();
            info.addRole(principal.getRole());
        }
//        if (userRole.equals("COMPANY")){
//
//        }else  if(userRole.equals("STUDENT")){
//
//        }else if(userRole.equals("TEACHER")){
//
//        }
        return info;
    }

    /**
     * 清空用户关联权限认证，待下次使用时重新加载。
     *
     * @param principal
     */
    public void clearCachedAuthorizationInfo(String principal) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
        clearCachedAuthorizationInfo(principals);
    }

    /**
     * 清空所有关联认证
     */
    public void clearAllCachedAuthorizationInfo() {
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if (cache != null) {
            for (Object key : cache.keys()) {
                cache.remove(key);
            }
        }
    }
}
