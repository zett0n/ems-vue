package cn.edu.zjut.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import cn.edu.zjut.entity.User;
import cn.edu.zjut.service.UserService;
import cn.edu.zjut.utils.VerifyCodeUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zett0n
 * @date 2021/7/7 22:18
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @GetMapping("/getVerifyCode")
    public String getVerifyCode(HttpServletRequest request) throws IOException {
        String code = VerifyCodeUtils.generateVerifyCode(4);
        // request.getServletContext().setAttribute("code", code);
        this.httpServletRequest.getSession().setAttribute("code", code);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        VerifyCodeUtils.outputImage(220, 60, byteArrayOutputStream, code);
        String res = "data:image/png;base64," + Base64Utils.encodeToString(byteArrayOutputStream.toByteArray());

        return res;
    }

    @PostMapping("/register")
    // code通过地址栏传参， user通过请求体传参
    public Map<String, Object> register(@RequestBody User user, String code, HttpServletRequest request) {
        log.debug("用户信息：[{}]", user.toString());
        log.debug("用户输入的验证码信息：[{}]", code);

        Map<String, Object> map = new HashMap<>();

        try {
            // String key = (String)request.getServletContext().getAttribute("code");
            String key = (String)this.httpServletRequest.getSession().getAttribute("code");
            if (key.equalsIgnoreCase(code)) {
                this.userService.register(user);
                map.put("state", true);
                map.put("msg", "提示：注册成功！");
            } else {
                throw new RuntimeException("验证码出现错误！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state", false);
            map.put("msg", "提示：" + e.getMessage());
        }
        return map;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody User user) {
        Map<String, Object> map = new HashMap<>();      

        try {
            User userDB = this.userService.login(user);
            map.put("state", true);
            map.put("msg", "登录成功！");
            map.put("user", userDB);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state", false);
            map.put("msg", e.getMessage());
        }
        return map;
    }
}
