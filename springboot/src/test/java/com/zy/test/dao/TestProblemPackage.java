package com.zy.test.dao;

import com.zy.dao.problem.ProblemDao;
import com.zy.dao.problem.ProblemPackageDao;
import com.zy.model.problem.Problem;
import com.zy.service.problem.ProblemPackageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import sun.tools.jstat.Literal;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zy
 * @date 2020/4/8 23:26
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestProblemPackage {
    @Autowired
    ProblemPackageService service;
    @Autowired
    ProblemPackageDao dao;
    @Test
    public void testJava(){
        String[] ids = {"1","2","3"};
        System.out.println(String.join(",",ids));
    }

    @Test
    public void test1(){
        System.out.println(dao.getAllConditionCount("语文","单选","A"));
    }

    @Test
    public void test3(){
        System.out.println(dao.getConditionByRandom("语文","单选","A",3));
    }

    @Test
    public void test4(){
       service.setConditionProblemsToNotUsed("语文", "单选", "A");
    }

    @Test
    public void test5(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        service.setSelectedProblemsToUsed(list);
    }

    /*
    * 验证BaseHandle方法
    * */
    @Test
    public void testBaseHandle(){
        String s = service.baseHandle("语文", "单选", "B", 2);
        System.out.println("======================");
        System.out.println(s);
    }

    @Test
    public void testSecondHandle(){
        String[] difficulty = {"A","B"};
        Integer[] rand = {2,2};
        String s = service.secondHandle("语文", "单选", difficulty, rand);
        System.out.println("======================");
        System.out.println(s);
    }
}
