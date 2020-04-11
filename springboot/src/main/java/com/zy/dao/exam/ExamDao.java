package com.zy.dao.exam;

import com.zy.model.exam.Exam;
import com.zy.vo.exam.ExamVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Zy
 * @date 2020/4/3 11:40
 */
public interface ExamDao extends JpaRepository<Exam,Integer> {
    //Integer id,String name, String creatorName,String hasJudge,String hasBlank,String hasChoice,  Integer choiceMark, Integer judgeMark, Integer blankMark,String des,   Integer totalMark
    @Query("select new com.zy.vo.exam.ExamVo(e.id , e.name , u.nickName,e.hasJudge,e.hasBlank,e.hasChoice,e.choiceMark,e.judgeMark,e.blankMark,e.des,e.totalMark,e.time) " +
            "FROM Exam as e " +
            "LEFT JOIN User  as u " +
            "on e.creator = u.id")
    Page<ExamVo> getExamVo(Pageable pageable);

    // 用于takeExam界面
    @Query("SELECT  new com.zy.vo.exam.ExamVo(e.id, e.hasChoice, e.hasBlank, e.hasJudge, e.des, e.name, u.nickName, e.choiceMark, e.blankMark, e.judgeMark, e.totalMark, u.avatar,e.time)" +
            "FROM Exam as e LEFT JOIN User as u on e.creator = u.id order by e.id")
    List<ExamVo> getAllExamVo();

}
