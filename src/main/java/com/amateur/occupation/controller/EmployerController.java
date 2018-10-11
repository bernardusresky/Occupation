package com.amateur.occupation.controller;
import com.amateur.occupation.constant.Const;
import com.amateur.occupation.entity.Employer;
import com.amateur.occupation.service.EmployerService;
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
@RequestMapping("/employer")
@Slf4j
public class EmployerController {
    private final EmployerService employerService;
    private final HttpSession session;

    @Autowired
    public EmployerController(EmployerService employerService, HttpSession session) {
        this.employerService = employerService;
        this.session = session;
    }
    
    @PostMapping
    public TResult add(@RequestBody Employer employer) {
        log.debug(employer.toString());
        session.setAttribute(employer.getEmail(), employer);
        JSONObject jsonObject = new JSONObject(employer);
        EmailUtil.sendAdd(employer.getEmail(), Const.EMPLOYER_URL, jsonObject.toString());
        return TResult.success("Verify email send success");
    }

    @GetMapping("/add")
    public String addEmployer(@RequestParam("data") String data) {
        String dataStr = Enycryption.decryptBASE64(data);
        JSONObject jsonObject = new JSONObject(dataStr);
        Employer employer = new Employer(jsonObject.getString("email"),
                jsonObject.getString("name"),
                jsonObject.getString("phone"),
                jsonObject.getString("address"),
                jsonObject.getString("description"),
                jsonObject.getString("domain"),
                jsonObject.getString("scale"),
                jsonObject.getString("password"),
                jsonObject.getInt("userType"));
        TResult result = employerService.add(employer);
        String host = EmailUtil.getCurrentHostName();
        if (result.getCode() == TResultCode.SUCCESS.getCode()) {
            session.setAttribute(Const.ID_KEY, employer.getEmail());
            session.setAttribute(Const.USER_TYPE_KEY, employer.getUserType());
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
                    "<h1>Add employer success!</h1>\n" +
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
                    "<h1>Add employer fail!</h1>\n" +
                    "</body>\n" +
                    "</html>";
        }
    }

    @PutMapping
    public TResult update(@RequestBody Employer employer) {
        EntityWrapper<Employer> ew = new EntityWrapper<>();
        boolean result = employerService.update(employer, ew.eq("email", employer.getEmail()));
        if (result) {
            return TResult.success(employer);
        } else {
            return TResult.failure(TResultCode.BUSINESS_ERROR);
        }
    }

    @DeleteMapping("/{email}")
    public TResult delete(@PathVariable("email") String email) {
        if (((int) session.getAttribute(Const.USER_TYPE_KEY)) == 0) {
            boolean result = employerService.deleteById(email);
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
