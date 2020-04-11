package com.zy.controller.exam;

import com.zy.dao.problem.ProblemDao;
import com.zy.jsonBean.exam.PackageExamJson;
import com.zy.jsonBean.problem.ProblemList;
import com.zy.model.exam.Exam;
import com.zy.model.problem.Problem;
import com.zy.service.exam.ExamService;
import com.zy.service.problem.ProblemPackageService;
import com.zy.service.problem.ProblemService;
import com.zy.vo.exam.AddExamVo;
import com.zy.vo.exam.ExamVo;
import org.hibernate.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Zy
 * @date 2020/4/3 11:42
 */
@RestController
@RequestMapping("/api/exam")
public class ExamController {
    @Autowired
    ProblemDao problemDao;
    @Autowired
    ExamService examService;
    @Autowired
    ProblemService problemService;
    @Autowired
    ProblemPackageService problemPackageService;
    Map<String,Object> map = new HashMap<>();

    /*
    *添加表单中渲染选择题目选项的组件
    * */
    @RequestMapping("/getIdsAndNamesOrderByType")
    public Map<String, List<Map<String, Object>>> getIdsAndNamesOrderByType(){
        List<Map<String, Object>> list1 = problemService.getProblemIdsAndNamesOrderByType("单选");
        List<Map<String, Object>> list2 = problemService.getProblemIdsAndNamesOrderByType("判断");
        List<Map<String, Object>> list3 = problemService.getProblemIdsAndNamesOrderByType("填空");
        Map<String, List<Map<String, Object>>> map = new HashMap<>();
        map.put("choice",list1);
        map.put("judge",list2);
        map.put("blank",list3);
        return map;
    }

    @RequestMapping("/getExamVoPage")
    public Map<String,Object> getExamVoPage(Integer size , Integer current){
        Pageable pageable = new PageRequest(current,size);
        return examService.getExamVo(pageable);
    }

    @RequestMapping("/getAllExamVoPage")
    public List<ExamVo> getExamVoPage(){
        return examService.getAllExamVo();
    }

    @RequestMapping("/deleteExamById")
    public void deleteExamById(Integer id){
        examService.deleteExamById(id);
    }

    @RequestMapping("/addExam")
    public void addExam(@RequestBody Exam exam){
        examService.addExam(exam);
    }

    /*
    * 根据examid返回该考试包含的题目信息
    * */
    @RequestMapping("/getProblemsByIdsOrderByTypes")
    public Map<String, List<Problem>> getProblemsByIdsOrderByTypes(Integer id)  {

        Map<String, List<Problem>> problemsByIdsOrderByTypes = examService.getProblemsByIdsOrderByTypes(id);
        return problemsByIdsOrderByTypes;
    }

    /*根据打包来的信息，随机不重复的组装试卷*/
    @RequestMapping("/packageExam")
    public void packageExam(@RequestBody PackageExamJson p){
        System.out.println(p.toString());
        String subject = p.getSubject();
        String hasChoice = problemPackageService.secondHandle(subject,"单选",p.getChoiceDiffi(),p.getChoiceCount());
        String hasBlank = problemPackageService.secondHandle(subject,"填空",p.getBlankDiffi(),p.getBlankCount());
        String hasJudge = problemPackageService.secondHandle(subject,"判断",p.getJudgeDiffi(),p.getJudgeCount());

        Exam exam = new Exam(hasChoice,hasBlank,hasJudge,p.getDes(),p.getName(), p.getCreator(), p.getChoiceMark(), p.getBlankMark(), p.getJudgeMark(), p.getTotalMark(),p.getTime());
        examService.addExam(exam);
    }
}
