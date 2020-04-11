package com.zy.controller.problem;

import com.zy.jsonBean.problem.ConditionProblemReqJsonBean;
import com.zy.jsonBean.problem.EditJsonBean;
import com.zy.model.problem.Problem;
import com.zy.service.problem.ProblemService;
import com.zy.vo.problem.ProblemByType;
import com.zy.vo.problem.ProblemCountByDifficultyVo;
import com.zy.vo.problem.ProblemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Zy
 * @date 2020/4/1 10:46
 */
@RestController
@RequestMapping("/api/problem")
public class ProblemController {
    @Autowired
    ProblemService problemService;

    Map<String,Object> map = new HashMap<>();

    /*
    * 根据发送来的ProblemVo 存入t_problem表
    * */
    @RequestMapping("/addProblem")
    public void addProblem(@RequestBody ProblemVo problemVo){
      problemService.setProblemByProblemVo(problemVo);
    }

    /*
    * 根据发送来的参数 进行模糊查询
    * */
    @RequestMapping("/getConditionResult")
    public List<ProblemVo> getConditionResult(@RequestBody ConditionProblemReqJsonBean req){
        System.out.println("======================");
        System.out.println(req.toString());
        List<ProblemVo> conditionResult = problemService.getConditionResult(req);
        return conditionResult;

    }


    /*
    * 获取条件查询的下拉菜单选项
    * */
    @RequestMapping("/getSelectCondition")
    public Map<String, List<String>> getSelectCondition(){
        Map<String, List<String>> selectCondition = problemService.getSelectCondition();
        return selectCondition;
    }

    /*
     * 修改填空题
     * */
    @RequestMapping("/editProblem")
    public void editBlankProblem(@RequestBody EditJsonBean axiosInfo){
        System.out.println(axiosInfo.toString());
        Problem problem = problemService.editBlankProblemByEditJsonBean(axiosInfo);
    }
    /*
    * 这个api是根据页码和页面大小查询分页结果
    * */
    @RequestMapping("/getProblemVoPage")
    public List<ProblemVo> getProblemVoPage(Integer page , Integer size){
        Page<ProblemVo> allProblemVo = problemService.getAllProblemVo(page, size);
        return allProblemVo.getContent();
    }

    /*
    * 这个api是查询总条数，用来给前端分页组件赋值
    * */
    @RequestMapping("/getProblemVoCount")
    public Long getProblemVoCount(){
        return problemService.getProblemVoCount();
    }
    /*
    * 通过id删除记录
    * */
    @RequestMapping("/deleteProblemById")
    public String deleteProblemById(Integer id){
        if (problemService.deleteProblemById(id)){
            return "删除成功";
        }
        return "删除失败";
    }


    /*Map<String,List<ProblemCountByDifficultyVo>> packageInitInfo*/
    @RequestMapping("getInitInfo")
    public Map<String, List<ProblemByType>> getInitInfo(){
        return problemService.getPackageInitInfo();
    }
}
