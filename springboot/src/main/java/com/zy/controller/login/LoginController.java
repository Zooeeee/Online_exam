package com.zy.controller.login;

import com.zy.model.user.User;
import com.zy.service.user.UserSevice;
import com.zy.utils.TokenUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Zy
 * @date 2020/3/28 21:10
 */
@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    UserSevice userSevice;

    /**
    * @methodsName: login
    * @description: 前端发来username和password，后台拿到检验，
     * 如果正确，则返回状态码，权限，头像和一个以昵称和id为参数生成的token
     * 错误，则返回一个错误状态码
    * @param:  用户名和密码
    * @return: Map对象
    */
    @RequestMapping("/loginCheck")
    public Map<String,Object> login(String username, String password){
//        System.out.println("username:"+username);
//        System.out.println("password:"+password);
        Map<String, Object> map = new HashMap<>();
        if (userSevice.checkUserLogin(username,password)){
            User user = userSevice.getUserInfo(username,password);
            map.put("status","200");
            map.put("role",user.getRole());
            map.put("power",user.getPower());
            map.put("avatar","http://localhost/img/avatar/" + user.getAvatar());
            //生成token，参数是用户id和用户名
        //    System.out.println(user.getId().toString());
            String token = TokenUtil.createJwtToken(user.getId().toString(),user.getNickName());
            map.put("token",token);
        }
        else {
            map.put("status","100");
        }
        return map;
    }

    @RequestMapping("/tokenCheck")
    public void getToken(HttpServletRequest httpServletRequest) {
        System.out.println("有验证token 进来了");
        System.out.println(httpServletRequest.getHeader("Authorization"));
        String getToken = httpServletRequest.getHeader("Authorization");
        Claims claims = TokenUtil.parseJWT(getToken);
        System.out.println(claims.getIssuer());
        System.out.println(claims);
    }
}
