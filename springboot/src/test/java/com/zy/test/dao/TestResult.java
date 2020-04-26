package com.zy.test.dao;


import com.zy.dao.result.ResultDao;
import com.zy.model.result.Result;
import com.zy.service.result.ResultService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author Zy
 * @date 2020/4/7 15:12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestResult {
    @Autowired
    ResultDao resultDao;
    @Autowired
    ResultService resultService;

    @Test
    public void test1(){
        System.out.println(resultDao.count());
    }



    @Test
    //@Transactional
    public void test3(){
        Result result = new Result();
        result.setResultId(10);
        resultDao.saveAndFlush(result);
    }
    
    @Test
    @Transactional
    public  void test4(){
        Map<String, Object> map = resultService.getNickNameAndAvatarByExamId(1);
        System.out.println(map.get("avatar"));
        System.out.println(map.get("nickName"));
    }



}
