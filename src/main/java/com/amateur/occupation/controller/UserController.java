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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
            session.setAttribute(Const.ID_KEY, result.getMessage());
            User user = (User) result.getData();
            session.setAttribute(Const.USER_TYPE_KEY, user.getUserType());
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

    @GetMapping("/add")
    public TResult add(@RequestParam("data") String data) {
        return null;
    }


    @GetMapping
//    public TResult get(@RequestParam("email") String email, @RequestParam("userType") int userType) {
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
                result = employerService.get(email);
                Employer employer = (Employer) result.getData();
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
