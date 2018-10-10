package com.amateur.occupation.controller;

import com.amateur.occupation.constant.Const;
import com.amateur.occupation.entity.Employee;
import com.amateur.occupation.service.EmployeeService;
import com.amateur.occupation.util.EmailUtil;
import com.amateur.occupation.util.Enycryption;
import com.amateur.occupation.util.TResult;
import com.amateur.occupation.util.TResultCode;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@Slf4j
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final HttpSession session;

    @Autowired
    public EmployeeController(EmployeeService employeeService, HttpSession session) {
        this.employeeService = employeeService;
        this.session = session;
    }
    
    @PostMapping
    public TResult add(@RequestBody Employee employee) {
        log.debug(employee.toString());
        session.setAttribute(employee.getEmail(), employee);
        System.out.println(employee);
        JSONObject jsonObject = new JSONObject(employee);
        System.out.println(jsonObject);

        EmailUtil.sendAdd(employee.getEmail(), Const.EMPLOYEE_URL, jsonObject.toString());
        return TResult.success("verify email send success!");
    }

    @GetMapping("/add")
    public String addEmployee( @RequestParam("data") String data) {
        String dataStr = Enycryption.decryptBASE64(data);
        JSONObject jsonObject = new JSONObject(dataStr);
        Employee employee = new Employee(jsonObject.getString("email"),
                jsonObject.getString("name"),
                jsonObject.getString("phone"),
                jsonObject.getString("birthday"),
                jsonObject.getInt("gender"),
                jsonObject.getString("eduBackground"),
                jsonObject.getString("workExperience"),
                jsonObject.getString("expectSalary"),
                jsonObject.getString("expectCity"),
                jsonObject.getString("address"),
                jsonObject.getString("description"),
                jsonObject.getString("password"),
                jsonObject.getInt("userType"));
        TResult result = employeeService.add(employee);
        String host = EmailUtil.getCurrentHostName();
        if (result.getCode() == TResultCode.SUCCESS.getCode()) {
            session.setAttribute(Const.ID_KEY, employee.getEmail());
            session.setAttribute(Const.USER_TYPE_KEY, employee.getUserType());
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
                    "<h1>add employee success!</h1>\n" +
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
                    "<h1>add employee fail!</h1>\n" +
                    "</body>\n" +
                    "</html>";
        }
    }

    @PutMapping
    public TResult update(@RequestBody Employee employee) {
        EntityWrapper<Employee> ew = new EntityWrapper<>();
        boolean result = employeeService.update(employee, ew.eq("email", employee.getEmail()));
        if (result) {
            return TResult.success(employee);
        } else {
            return TResult.failure(TResultCode.BUSINESS_ERROR);
        }
    }

    @DeleteMapping("/{email}")
    public TResult delete(@PathVariable("email") String email) {
        if (((int) session.getAttribute(Const.USER_TYPE_KEY)) == 0) {
            boolean result = employeeService.deleteById(email);
            if (result) {
                return TResult.success(email);
            } else {
                return TResult.failure(TResultCode.BUSINESS_ERROR);
            }
        } else {
            return TResult.failure(TResultCode.PERMISSION_NO_ACCESS);
        }
    }


}
