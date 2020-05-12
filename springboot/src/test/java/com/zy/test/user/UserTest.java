package com.zy.test.user;

import com.zy.dao.user.UserDao;
import com.zy.model.user.User;
import com.zy.service.user.UserSevice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zy
 * @date 2020/3/31 12:26
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserTest {
    @Autowired
    UserSevice userSevice;
    @Autowired
    UserDao userDao;

    @Test
    public void testAvatarUploader(){
        List<User> list = new ArrayList();
        list = userDao.findAll();
        list.forEach(ele->{
            System.out.println(ele);
        });
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void testAvatarUploader1(){
        User one = userDao.getOne(1);
        one.setAvatar("11");
        userDao.save(one);
    }

    @Test()
    @Transactional
    @Rollback(value = false)
    public void addUsers(){

        List<User> list = new ArrayList<>();
        for (int i = 1 ; i <= 100000 ; i++){
            User one = new User();
            one.setUsername("这是第"+i+"个用户名");
            one.setPassword("这是第"+i+"个密码");
            one.setRole("teacher");
            one.setNickName("这是第"+i+"个昵称");
            one.setPower("exam,problem,result,userList");
            one.setAvatar("1587866861977.jpg");
            one.setEmail("这是第"+i+"个邮箱@qq.com");
            list.add(one);
        }
        userDao.saveAll(list);

    }

}
