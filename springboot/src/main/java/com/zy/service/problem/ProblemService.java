package com.zy.service.problem;

import com.zy.dao.problem.ProblemDao;
import com.zy.dao.problem.ProblemToSubjectDao;
import com.zy.dao.problem.ProblemToTypeDao;
import com.zy.dao.user.UserDao;
import com.zy.jsonBean.problem.ConditionProblemReqJsonBean;
import com.zy.jsonBean.problem.EditJsonBean;
import com.zy.model.problem.Problem;
import com.zy.model.problem.ProblemToType;
import com.zy.vo.problem.ProblemByType;
import com.zy.vo.problem.ProblemCountByDifficultyVo;
import com.zy.vo.problem.ProblemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Zy
 * @date 2020/4/1 10:47
 */
@Service
public class ProblemService {
    @Autowired
    ProblemDao problemDao;
    @Autowired
    ProblemToSubjectDao problemToSubjectDao;
    @Autowired
    ProblemToTypeDao problemToTypeDao;

    @Autowired
    UserDao userDao;
    /*
    * 根据题目id 查询题目name
    * "1,10"
    * */
    @Transactional
    public List<String> getNamesByIds(String ids){
        List<String> names = new ArrayList<>();
        String[] split = ids.split(",");
        for (int i = 0 ; i < split.length ; i++){
            int id = Integer.parseInt(split[i]);
            Problem one = problemDao.getOne(id);
            names.add(one.getName());
        }
        return names;
    }


    /*
     * 根据发送来的参数 进行模糊查询
     * */
    public List<ProblemVo> getConditionResult(ConditionProblemReqJsonBean req){
        return problemDao.getProblemVoLike(req.getName(),req.getType(),req.getSubject(),req.getDifficulty(),req.getCreator());
    }

    // 获取记录条数
    public Long getProblemVoCount(){
        return  problemDao.count();
    }

    /*
     * 获取条件查询的下拉菜单选项
     * */
    public Map<String,List<String>> getSelectCondition(){
        List<String> problemCreator = problemDao.getProblemCreator();
        List<String> problemDifficulty = problemDao.getProblemDifficulty();
        List<String> problemSubject = problemDao.getProblemSubject();
        List<String> problemType = problemDao.getProblemType();
        Map<String,List<String>>  map = new HashMap<>();
        map.put("creator",problemCreator);
        map.put("difficulty",problemDifficulty);
        map.put("subject",problemSubject);
        map.put("type",problemType);
        return map;
    }

    /*
     * 这个是根据页码和页面大小查询分页结果
     * */
    public Page<ProblemVo> getAllProblemVo(Integer page , Integer size){
        Pageable pageable = new PageRequest(page,size);
        Page<ProblemVo> problemVo = problemDao.getProblemVo(pageable);
        return problemVo;
    }

    /*
    * 根据id删除problem记录
    * 先查找id在不在
    * 如果在 删除 返回 true
    * 如果不存在 返回 false
    * */
    public boolean deleteProblemById(Integer id){
        boolean existsById = problemDao.existsById(id);
        if (existsById == true){
            problemDao.deleteById(id);
        }
        return existsById;
    }

    /*
    * 根据id name analysis answer option 修改记录
    * */
    @Transactional
    public Problem editBlankProblemByEditJsonBean(EditJsonBean editJsonBean){
        Problem one = problemDao.getOne(Integer.parseInt(editJsonBean.getId()));
        one.setName(editJsonBean.getName());
        one.setAnswer(editJsonBean.getAnswer());
        one.setContent(editJsonBean.getContent());
        one.setAnalysis(editJsonBean.getAnalysis());
        Problem problem = problemDao.saveAndFlush(one);
        return problem;
    }

    /*
    * 根据ProblemVo存入数据库
    * */
    @Transactional
    public void setProblemByProblemVo(ProblemVo problemVo){
        Integer typeId = problemToTypeDao.findByType(problemVo.getType()).getId();
        Integer subjectId = problemToSubjectDao.findBySubject(problemVo.getSubject()).getId();
        Integer creator = userDao.findByNickName(problemVo.getCreator()).getId();
        Problem problem = new Problem();

        problem.setName(problemVo.getName());
        problem.setSubject(subjectId);
        problem.setType(typeId);
        problem.setAnalysis(problemVo.getAnalysis());
        problem.setContent(problemVo.getContent());
        problem.setDifficulty(1);
        problem.setAnswer(problemVo.getAnswer());
        problem.setCreator(creator);
        problem.setIsUsed(problemVo.getIsUsed());

        problemDao.saveAndFlush(problem);

    }


    /*
    * 先找到选择题题型，然后根据题型把题型中的题目的id和name找出来发送
    * */
    public List<Map<String,Object>> getProblemIdsAndNamesOrderByType(String type){
//        选择题List
       //1. 在type表里获取选择题 对应的id编号
        Integer typeId = problemToTypeDao.findByType(type).getId();
        List<Problem> allByType = problemDao.findAllByType(typeId);
        List<Map<String,Object>> result = new ArrayList<>();
        allByType.forEach(item->{
            Map<String,Object> map = new HashMap<>();
            map.put("name",item.getName());
            map.put("id",item.getId());
            result.add(map);
        });
        return result;
    }


    /*以下全是packageExam所需要用到的方法*/
    /*组装试卷时 表单的基本信息生成*/
    public Map<String, List<ProblemByType>> getPackageInitInfo(){
        List<String> problemDifficulty = problemDao.getProblemDifficulty();
        List<String> problemSubject = problemDao.getProblemSubject();
        List<String> problemType = problemDao.getProblemType();
        Map<String, List<ProblemByType>> map = new HashMap<>();
        // 一层循环 找的是subject 这里的ele 指的每一个subject
        problemSubject.forEach(ele->{
            List<ProblemByType> list = new ArrayList<>();
            // 二层循环 找的是type 这里的ele1 指得是每一个type
            problemType.forEach(ele1 -> {
                List<ProblemCountByDifficultyVo> list2 = new ArrayList<>();
                // 三层循环 找的是difficulty和count 这里的ele2 指得是每一个difficulty
                problemDifficulty.forEach(ele2 -> {
                    Integer count = problemDao.getCountBySubjectAndTypeAndDifficulty(ele, ele1, ele2);
                    list2.add(new ProblemCountByDifficultyVo(ele2,count));
                });
                list.add(new ProblemByType(ele1,list2));
            });
            map.put(ele,list);
        });

        return map;
    }

}
