package com.zy.test.dao;

import com.zy.dao.exam.ExamDao;
import com.zy.dao.problem.ProblemDao;
import com.zy.model.exam.Exam;
import com.zy.model.problem.Problem;
import com.zy.service.exam.ExamService;
import com.zy.service.problem.ProblemService;
import com.zy.vo.exam.ExamVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Zy
 * @date 2020/4/3 12:12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestExam {
    @Autowired
    ExamDao examDao;
    @Autowired
    ExamService examService;
    @Autowired
    ProblemService problemService;
    @Autowired
    ProblemDao problemDao;
    @Test
    public void test1(){
        Page<ExamVo> examVo = examDao.getExamVo(new PageRequest(0, 1));
        List<ExamVo> content = examVo.getContent();
        content.forEach(element-> System.out.println(element.toString()));
        content.forEach(item -> {
            String hasBlank = item.getHasBlank();
            List<String> namesByIds = problemService.getNamesByIds(hasBlank);
            item.setBlankList(namesByIds);

            System.out.println(item.toString());
        });
    }
    @Test
    public void test2(){
        Map<String, Object> examVo = examService.getExamVo(new PageRequest(0,1));
        examVo.forEach((Key, Value)->{
            System.out.println(Key.toString());
            System.out.println(Value.toString());
        });

    }

    @Test
    public void test3(){
        List<Map<String, Object>> list = problemService.getProblemIdsAndNamesOrderByType("单选");
        list.forEach(item->{
            System.out.println(item.toString());
        });

    }

    @Test
    @Transactional
    public void test4(){
        Integer id = 1 ;
        Exam exam = examDao.getOne(id);
        String hasChoice = exam.getHasChoice();
        System.out.println(hasChoice);
        String[] arr = hasChoice.split(",");
        List<Problem> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(problemDao.getOne(Integer.parseInt(arr[i])));
        }
        list.forEach(ele-> System.out.println(ele.toString()));
    }

//    @Test
//    @Transactional
//    public void test9(){
//        Exam one = examDao.getOne(1);
//        Time time = one.getTime();
//        System.out.println(time.toString());
//
//
//    }

}
