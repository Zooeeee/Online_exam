package com.zy.test.dao;

import com.zy.dao.user.UserDao;
import com.zy.model.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Zy
 * @date 2020/3/28 20:03
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class testUserDao {
    @Autowired
    UserDao userDao;
    @Test
    @Transactional
    @Rollback(value = false)
    public void testUpdate(){
        User one = userDao.getOne(1);
        one.setNickName("dddddd");
        userDao.saveAndFlush(one);
    }
    @Test
    public void testFindAll(){
        List<User> all = userDao.findAll();
        all.forEach(item-> System.out.println(item.toString()));
    }

    @Test
    public void testFindByUsernameAndPassword(){
        String userName = "1233";
        String passWord = "123";
        User user = userDao.findByUsernameAndPassword(userName, passWord);
        System.out.println(user == null);
    }
}
