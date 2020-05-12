package com.zy.service.user;

import com.zy.dao.user.UserDao;
import com.zy.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Zy
 * @date 2020/3/28 20:09
 */
@Service
public class UserSevice  {
    @Autowired
    UserDao userDao;

    /**
     * @description: 通过用户名和密码检查是否存在这个用户
     * @param: String username, String password
     * @return: 存在则返回true，不存在则返回false
     */
    public boolean checkUserLogin(String username, String password){
        User user = userDao.findByUsernameAndPassword(username, password);
        if (user == null)
            return false;
        else
            return true;
    }
    /**
    * @methodsName: getUserInfo
    * @description: 通过用户名密码返回一个User实体类
    * @param:  String username, String password
    * @return: User
    */
    public User getUserInfo(String username, String password){
        User user = userDao.findByUsernameAndPassword(username, password);
        return user;
    }

    /**
    * @methodsName: setUserAvatar
    * @description: 通过获取的用户id和图片地址，修改表中数据
    * @param:  Integer id,String src
    * @return: 修改后的该User实体
    */
    @Transactional
    public User setUserAvatar(Integer id,String src){
        User one = userDao.getOne(id);
        one.setAvatar(src);
        userDao.save(one);
        return one;
    }

    public User getOne(Integer id){
        User one = userDao.getOne(id);
        return one;
    }

    @Transactional
    public User setUserNickNameAndEmail(Integer id, String nickName, String email){
        User one = userDao.getOne(id);
        one.setNickName(nickName);
        one.setEmail(email);
        userDao.save(one);
        return one;
    }

    @Transactional
    public User setUserPassword(Integer id, String password){
        User one = userDao.getOne(id);
        one.setPassword(password);
        userDao.save(one);
        return one;
    }


    public List<User> getAllUser(){
        List<User> list = new ArrayList();
        list = userDao.findAll();
        return list;


    }
}
