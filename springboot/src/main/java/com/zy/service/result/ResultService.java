package com.zy.service.result;

import com.zy.dao.exam.ExamDao;
import com.zy.dao.result.ResultDao;
import com.zy.dao.user.UserDao;
import com.zy.jsonBean.result.OneResultPaperInfo;
import com.zy.model.exam.Exam;
import com.zy.model.problem.Problem;
import com.zy.model.result.Result;
import com.zy.model.user.User;
import com.zy.service.exam.ExamService;
import com.zy.vo.result.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Zy
 * @date 2020/4/7 15:15
 */
@Service
public class ResultService  {
    @Autowired
    ResultDao resultDao;
    @Autowired
    ExamDao examDao;
    @Autowired
    UserDao userDao;
    @Autowired
    ExamService examService;

    public Result getOne(Integer id){return resultDao.getOne(id);}

    @Transactional
    public OneResultPaperInfo getOneResultPaperInfo(Integer resultId, Integer examId){
        Map<String,Object> map = new HashMap<>();
        Map<String, List<Problem>> problemsByIdsOrderByTypes = examService.getProblemsByIdsOrderByTypes(examId);
        Result result = resultDao.getOne(resultId);
        Exam exam = examDao.getOne(examId);

        return new OneResultPaperInfo(problemsByIdsOrderByTypes,result,exam.getChoiceMark(),exam.getBlankMark(),exam.getJudgeMark());
    }

    public void saveResult(Result result){
        resultDao.saveAndFlush(result);
    }

    public List<ResultVo>  getAllResultVo(){ return resultDao.getAllResultVo(); }

    public List<ResultVo>  getMyAllResultVo(Integer id){ return resultDao.getMyAllResultVo(id); }

    public void deleteOneResultByResultId(Integer id){ resultDao.deleteById(id); }

    // 根据exam id  返回创建者昵称 和创建者头像
    public Map<String,Object> getNickNameAndAvatarByExamId(Integer examId){
        Integer id =  examDao.getOne(examId).getCreator();
        User user = userDao.getOne(id);
        String avatar = user.getAvatar();
        String nickName= user.getNickName();
        Map<String,Object> map = new HashMap<>();
        map.put("avatar",avatar);
        map.put("nickName",nickName);
        return map;
    }
}
