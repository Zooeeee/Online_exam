package com.zy.dao.result;

import com.zy.model.result.Result;
import com.zy.vo.result.ResultVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Zy
 * @date 2020/4/7 15:12
 */
public interface ResultDao  extends JpaRepository<Result,Integer> {
    // Integer resultId, Integer examId, Data createTime, List<ChoiceAnswerData> choiceAnswer, List<BlankAnswerData> blankAnswer, List<JudgeAnswerData> judgeAnswer, Integer totalMark, String stuNickname, String name, String des
    // @Query("select new com.zy.vo.result.ResultVo(r.resultId ,r.examId,r.totalMark,r.createTime,r.choiceAnswer,r.blankAnswer,r.judgeAnswer,u.nickName,e.name,e.des) from Result as r left join User as u on r.stuId = u.id left join Exam as e on e.id = r.examId")
    @Query("select new com.zy.vo.result.ResultVo(r.resultId ,r.examId,r.totalMark,r.createTime,u.nickName,e.name,e.des,u.avatar) from Result as r left join User as u on r.stuId = u.id left join Exam as e on e.id = r.examId")
    List<ResultVo> getAllResultVo();

    @Query("select new com.zy.vo.result.ResultVo(r.resultId ,r.examId,r.totalMark,r.createTime,u.nickName,e.name,e.des,u.avatar) from Result as r left join User as u on r.stuId = u.id left join Exam as e on e.id = r.examId where r.stuId = ?1")
    List<ResultVo> getMyAllResultVo(Integer stuId);
}
