package com.zy.controller.user;

import com.zy.jsonBean.user.UserNickNameAndEmailBean;
import com.zy.model.user.User;
import com.zy.service.user.UserSevice;
import com.zy.utils.Configure;
import com.zy.utils.TokenUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Zy
 * @date 2020/3/30 22:07
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserSevice userSevice;

    Map<String,Object> map = new HashMap<>();

    @RequestMapping("/getUserNickNameAndEmail")
    public Map<String,Object> getUserNickNameAndEmail(Integer id){
        User one = userSevice.getOne(id);
        String nickName = one.getNickName();
        String email = one.getEmail();
        map.put("nickName",nickName);
        map.put("email",email);
        return map;
    }

    @RequestMapping("/setUserNickNameAndEmail")
    public String setUserNickNameAndEmail(@RequestBody UserNickNameAndEmailBean data){
        System.out.println("setUserNickNameAndEmail");
        System.out.println(data.toString());
        User user = userSevice.setUserNickNameAndEmail(Integer.parseInt(data.getId()), data.getNickName(), data.getEmail());
        return "200";
    }

    @RequestMapping("/setUserPassword")
    public String setUserPassword(Integer id , String password){
        System.out.println("setUserNickNameAndEmail");
        User user = userSevice.setUserPassword(id, password);
        return "200";
    }


    /**
    * @methodsName: uploadAvatar
    * @description: 收到前端传来的头像图片，改名后存回数据库
    * @param:
    * @return: 前端用的submit方法，返回一个图片地址字符串
    */
    @RequestMapping("/uploadAvatar")
    public String uploadAvatar(@RequestParam(name = "image_data", required = false) MultipartFile file, HttpServletRequest req ) throws FileNotFoundException {
        //这里是通过头获取token从而解析出id
   //     System.out.println(req.getHeader("Authorization"));
        String getToken = req.getHeader("Authorization");
        Claims claims = TokenUtil.parseJWT(getToken);
   //     System.out.println(claims.getIssuer());
   //    System.out.println(claims);
        Integer id = Integer.parseInt( claims.getId());
        System.out.println(id);
//        这里是获取static/img的实际运行位置，在springboot中，会将resources下的非空目录 static 、resources等直接复制到target中
//        此处最好现在src的静态资源中随便放一个图片，让其认为非空，从而复制到target中
//        String imgPath = ClassUtils.getDefaultClassLoader().getResource("static/img").getPath();
//        System.out.println(imgPath);

        //1. 接受上传的文件  @RequestParam("file") MultipartFile file
        try {

            //2.根据时间戳创建新的文件名，这样即便是第二次上传相同名称的文件，也不会把第一次的文件覆盖了
            String fileName =  file.getOriginalFilename();
            String time = System.currentTimeMillis()+"";
            String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 截取后缀名
            String newFileName = time+suffixName;
            //3.通过req.getServletContext().getRealPath("") 获取当前项目的真实路径，然后拼接前面的文件名
            String destFileName = ClassUtils.getDefaultClassLoader().getResource("static/img/avatar").getPath() + File.separator + newFileName;
            //4.第一次运行的时候，这个文件所在的目录往往是不存在的
            System.out.println("destFileName:"+destFileName);
            File destFile = new File(destFileName);
            //如果没有目录  要创建
            // destFile.getParentFile().mkdirs();
            //5.把浏览器上传的文件复制到希望的位置
            System.out.println("newFileName:"+newFileName); //新文件名字
            file.transferTo(destFile);

            //通过service层保存数据
            User user = userSevice.setUserAvatar(id, newFileName);
            return Configure.serverUrl +  "/img/avatar/" + user.getAvatar();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "上传失败," + e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败," + e.getMessage();
        }
    }


}
