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
        Integer id = 1 ;
        String src = "1585628741786.jpg";
        System.out.println(userSevice.setUserAvatar(id,src));
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void testAvatarUploader1(){
        User one = userDao.getOne(1);
        one.setAvatar("11");
        userDao.save(one);
    }
}
