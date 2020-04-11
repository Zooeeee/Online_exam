package com.zy.test.dao;

import com.zy.dao.problem.ProblemDao;
import com.zy.dao.problem.ProblemToSubjectDao;
import com.zy.jsonBean.problem.EditJsonBean;
import com.zy.model.problem.Problem;
import com.zy.model.problem.ProblemToSubject;
import com.zy.service.problem.ProblemService;
import com.zy.vo.problem.ProblemCountByDifficultyVo;
import com.zy.vo.problem.ProblemVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author Zy
 * @date 2020/3/31 23:28
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class testProblemDao {
    @Autowired
    ProblemDao problemDao;
    @Autowired
    ProblemService problemService;
    @Autowired
    ProblemToSubjectDao problemToSubjectDao;

    @Test
    public void test1(){
        Page<ProblemVo> problemVo = problemDao.getProblemVo(new PageRequest(0, 1));
       problemVo.getContent().forEach(item-> System.out.println(item));

    }

    @Test
    @Transactional
    public void test2(){
        List<String> distinctBySubject = problemToSubjectDao.getAllSubjects();
        distinctBySubject.forEach(item-> System.out.println(item.toString()));

        List<String> problemDifficulty = problemDao.getProblemDifficulty();
        problemDifficulty.forEach(item-> System.out.println(item.toString()));
    }




}
