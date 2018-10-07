package com.amateur.occupation.controller;

import com.amateur.occupation.constant.Const;
import com.amateur.occupation.entity.Employee;
import com.amateur.occupation.entity.Employer;
import com.amateur.occupation.entity.User;
import com.amateur.occupation.service.EmployeeService;
import com.amateur.occupation.service.EmployerService;
import com.amateur.occupation.service.UserService;
import com.amateur.occupation.util.EmailUtil;
import com.amateur.occupation.util.Enycryption;
import com.amateur.occupation.util.TResult;
import com.amateur.occupation.util.TResultCode;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final EmployeeService employeeService;
    private final EmployerService employerService;
    private final HttpSession session;

    @Autowired
    public UserController(UserService userService, EmployeeService employeeService, EmployerService employerService, HttpSession session) {
        this.userService = userService;
        this.employeeService = employeeService;
        this.employerService = employerService;
        this.session = session;
    }


    @PostMapping("/login")
    public TResult login(@RequestBody Map map) {
        String email = (String) map.get("email");
        String password = (String) map.get("password");
        TResult result = userService.login(email, password);
        if (result.getCode() == TResultCode.SUCCESS.getCode()) {
            log.info("set session attribute id:" + result.getMessage());
            User user = (User) result.getData();
            if (user.getIsForbidden() == 1) {
                return TResult.failure(TResultCode.PERMISSION_NO_ACCESS);
            } else {
                session.setAttribute(Const.ID_KEY, result.getMessage());
                session.setAttribute(Const.USER_TYPE_KEY, user.getUserType());

            }
        }
        return result;
    }

    /**
     * admin registe
     *
     * @param user
     * @return
     */
    @PostMapping
    public TResult add(@RequestBody User user) {
        TResult result = userService.add(user);
        if (result.getCode() == TResultCode.SUCCESS.getCode()) {
            session.setAttribute(Const.ID_KEY, user.getEmail());
            session.setAttribute(Const.USER_TYPE_KEY, user.getUserType());
        }
        return result;
    }


    @GetMapping("/employee_list")
    public TResult employeeList() {
        if ((int) session.getAttribute(Const.USER_TYPE_KEY) == 0) {
            List<Employee> employeeList = employeeService.list();
            if (employeeList == null) {
                return TResult.failure(TResultCode.BUSINESS_ERROR);
            } else {
                return TResult.success(employeeList);
            }
        } else {
            return TResult.failure(TResultCode.PERMISSION_NO_ACCESS);
        }

    }

    @GetMapping("/employer_list")
    public TResult employerList() {
        if ((int) session.getAttribute(Const.USER_TYPE_KEY) == 0) {
            List<Employer> employerList = employerService.list();
            if (employerList == null) {
                return TResult.failure(TResultCode.BUSINESS_ERROR);
            } else {
                return TResult.success(employerList);
            }
        } else {
            return TResult.failure(TResultCode.PERMISSION_NO_ACCESS);
        }

    }


    @GetMapping
    public TResult get() {
        String email = (String) session.getAttribute(Const.ID_KEY);
        if (session.getAttribute(Const.USER_TYPE_KEY) == null) {
            return TResult.failure(TResultCode.USER_NOT_LOGGED_IN);
        }
        int userType = (Integer) session.getAttribute(Const.USER_TYPE_KEY);
        TResult result = TResult.failure(TResultCode.BUSINESS_ERROR);
        switch (userType) {
            case 0:
                result = TResult.success(new User(email, "******", userType));
                break;
            case 1:
                System.out.println(email);
                result = employerService.get(email);
                System.out.println(result);
                Employer employer = (Employer) result.getData();
                System.out.println(result.getData());
                employer.setUserType(userType);
                result.setData(employer);
                break;
            case 2:
                result = employeeService.get(email);
                Employee employee = (Employee) result.getData();
                employee.setUserType(userType);
                result.setData(employee);
                break;
            default:
                return TResult.failure(TResultCode.BUSINESS_ERROR);
        }
        return result;
    }

    /**
     * to be tested
     *
     * @param email
     * @return
     */
    @PutMapping("/report/{email}")
    public TResult report(@PathVariable("email") String email) {
        EntityWrapper<User> ew = new EntityWrapper<>();
        boolean result = userService.updateForSet("  reported_num=reported_num+1 ", ew.eq("email", email));
        if (result) {
            return TResult.success("update user add reportNum 1 ");
        } else {
            return TResult.failure(TResultCode.BUSINESS_ERROR);
        }
    }

    @PostMapping("/logout")
    public TResult logout() {
        session.setAttribute(Const.ID_KEY, null);
        return TResult.success();
    }

    @PutMapping
    public TResult updatePassword(@RequestBody User user) {
        EmailUtil.sendUpdatePassword(user.getEmail(), user.getPassword());
//        return userService.updatePassword(user.getEmail(),user.getPassword());
        return TResult.success("update password email send success", user.getEmail());
    }

    @GetMapping("/update_password")
    public String handleLink(@RequestParam("email") String emailStr, @RequestParam("password") String pwd) {
        String email = Enycryption.decryptBASE64(emailStr);
        String password = Enycryption.decryptBASE64(pwd);
        TResult result = userService.updatePassword(email, password);
        String host = EmailUtil.getCurrentHostName();
        if (result.getCode() == TResultCode.SUCCESS.getCode()) {
            return "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Occupation</title>\n" +
                    "</head>\n" +
                    "<script>\n" +
                    "  window.location='http://" + host + ":9000' \n" +
                    "</script>" +
                    "<body>\n" +
                    "<h1>Update password success!</h1>\n" +
                    "</body>\n" +
                    "</html>";
        } else {

            return "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Occupation</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<h1>Update password fail!</h1>\n" +
                    "</body>\n" +
                    "</html>";
        }
    }
}
