package com.zy.dao.user;

import com.zy.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Zy
 * @date 2020/3/28 20:01
 */
public interface UserDao extends JpaRepository<User,Integer> {
    public User findByUsernameAndPassword(String userName,String password);

    public User findByNickName(String nickname);
}
