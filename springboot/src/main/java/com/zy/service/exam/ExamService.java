package com.zy.service.exam;

import com.zy.dao.exam.ExamDao;
import com.zy.dao.problem.ProblemDao;
import com.zy.model.exam.Exam;
import com.zy.model.problem.Problem;
import com.zy.service.problem.ProblemService;
import com.zy.vo.exam.AddExamVo;
import com.zy.vo.exam.ExamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Zy
 * @date 2020/4/3 11:41
 */
@Service
public class ExamService {
    @Autowired
    ExamDao examDao;
    @Autowired
    ProblemDao problemDao;
    @Autowired
    ProblemService problemService;
    /*
    * 接受所有的ExamVo类信息
    * */
    public List<ExamVo> getAllExamVo(){
        List<ExamVo> allExamVo = examDao.getAllExamVo();
        return allExamVo;
    }
    /*
    * 接受一个pagerequest作为参数
    * 返回一个已经分页的 List ExamVo类 还有当前尺寸和页面 总条数的
    * */
    public Map<String,Object> getExamVo(Pageable pageable){
        Map<String,Object> map = new HashMap<>();
        Page<ExamVo> page = examDao.getExamVo(pageable);
        List<ExamVo> content = page.getContent();
        content.forEach(item->{
            if (item.getHasBlank() != null){
                List<String> namesByIds = problemService.getNamesByIds(item.getHasBlank());
                item.setBlankList(namesByIds);
            }
            else {
                item.setBlankList(null);
            }
            if (item.getHasChoice() != null){
                List<String> namesByIds1 = problemService.getNamesByIds(item.getHasChoice());
                item.setChoiceList(namesByIds1);
            }
            else {
                item.setHasChoice(null);
            }
            if (item.getHasJudge() != null){
                List<String> namesByIds3 = problemService.getNamesByIds(item.getHasJudge());
                item.setJudgeList(namesByIds3);
            }
        });
        map.put("size",page.getSize());
        map.put("currentPage",page.getNumber());
        map.put("count",examDao.count());
        map.put("data",content);

        return map;
    }

    /*
    *根据id删除记录
    * */
    public void deleteExamById(Integer id){
        examDao.deleteById(id);
    }

    /*
    * 保存exam记录
    * */
    @Transactional
    public void addExam(Exam exam){
        examDao.saveAndFlush(exam);
    }

    /*
    * 根据考试id，找到题目，并且分组
    * */
    @Transactional
    public Map<String,List<Problem>> getProblemsByIdsOrderByTypes(Integer id) {
        Exam exam = examDao.getOne(id);

        String[] choiceArr = exam.getHasChoice().split(",");
        List<Problem> choiceList = new ArrayList<>();
        for (int i = 0; i < choiceArr.length; i++) {
            choiceList.add(problemDao.getOne(Integer.parseInt(choiceArr[i])));
        }

        String[] blankArr = exam.getHasBlank().split(",");
        List<Problem> blankList = new ArrayList<>();
        for (int i = 0; i < blankArr.length; i++) {
            blankList.add(problemDao.getOne(Integer.parseInt(blankArr[i])));
        }

        String[] judgeArr = exam.getHasJudge().split(",");
        List<Problem> judgeList = new ArrayList<>();
        for (int i = 0; i < judgeArr.length; i++) {
            judgeList.add(problemDao.getOne(Integer.parseInt(judgeArr[i])));
        }

        Map<String,List<Problem>> map = new HashMap<>();
        map.put("choiceList",choiceList);
        map.put("blankList",blankList);
        map.put("judgeList",judgeList);

        return map;
    }
}
