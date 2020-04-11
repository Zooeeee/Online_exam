package com.zy.controller.result;

import com.zy.jsonBean.result.OneResultPaperInfo;
import com.zy.model.problem.Problem;
import com.zy.model.result.Result;
import com.zy.service.exam.ExamService;
import com.zy.service.result.ResultService;
import com.zy.vo.result.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Zy
 * @date 2020/4/7 15:14
 */
@RestController
@RequestMapping("/api/result")
public class ResultController {
    @Autowired
    ResultService resultService;
    @Autowired
    ExamService examService;

    @RequestMapping("/addResult")
    public void addResult(@RequestBody Result result){
        System.out.println(result.toString());
        resultService.saveResult(result);
    }

    @RequestMapping("/getResultVo")
    public List<ResultVo> addResult(String role , Integer id){
        if (role.equals ("teacher")) { return resultService.getAllResultVo(); }
        if (role.equals ("student")) return resultService.getMyAllResultVo(id);
        else return null;
    }


    /*渲染头部组件*/
    // 根据exam id  返回创建者昵称 和创建者头像
    @RequestMapping("/getCreatorInfoByExam")
    public Map<String,Object> getCreatorInfoByExam(Integer examId){
        return resultService.getNickNameAndAvatarByExamId(examId);
    }


    /*渲染整体部分*/
    @RequestMapping("/getOneResultPaperInfo")
    public OneResultPaperInfo getOneResultPaperInfo(Integer resultId,Integer examId){
        return resultService.getOneResultPaperInfo(resultId,examId);
    }

    //删除某个记录
    @RequestMapping("/deleteOneResult")
    public void deleteOneResultByResultId(Integer resultId){
        resultService.deleteOneResultByResultId(resultId);
    }
}
