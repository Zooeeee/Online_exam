package com.zy.dao.problem;

import com.zy.model.problem.Problem;
import com.zy.vo.problem.ProblemVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


/**
 * @author Zy
 * @date 2020/3/31 23:00
 */
public interface ProblemDao extends JpaRepository<Problem,Integer>{


    /*
    * 根据name type creator subject difficulty 来模糊查询problemVo
    * */
    @Query("select new com.zy.vo.problem.ProblemVo(p.id,t.type,s.subject,p.name,p.answer,p.content,p.analysis,d.difficulty,u.nickName) " +
            "FROM Problem as p left join ProblemToType as t on p.type = t.id left join ProblemToDifficulty as d on p.difficulty = d.id  left join ProblemToSubject as s on p.subject= s.id left join User as u on p.creator = u.id " +
            "where s.subject like CONCAT('%',?3,'%')  " +
            "and t.type like CONCAT('%',?2,'%') " +
            "and d.difficulty like CONCAT('%',?4,'%') " +
            "and u.nickName like CONCAT('%',?5,'%')" +
            "and p.name like CONCAT('%',?1,'%')  " +
            "order by p.id ")
    List<ProblemVo> getProblemVoLike(String nameLike,String typeLike,String subjectLike,String difficultyLike,String creatorLike);
    /*
    * 分页查询 Problem视图
    * */
    @Query("select new com.zy.vo.problem.ProblemVo(p.id,t.type,s.subject,p.name,p.answer,p.content,p.analysis,d.difficulty,u.nickName) " +
            "from Problem as p " +
            "left join ProblemToType as t on p.type = t.id " +
            "left join ProblemToDifficulty as d on p.difficulty = d.id " +
            "left join ProblemToSubject as s on p.subject = s.id " +
            "left join User as u on p.creator = u.id " +
            "order by p.id ")
    Page<ProblemVo> getProblemVo(Pageable pageable);

    // 获取所有题型
    @Query("select distinct t.type from Problem as p LEFT JOIN ProblemToType as t on p.type = t.id ")
    List<String> getProblemType();

    //获取所有科目
    @Query("select distinct s.subject from Problem as p LEFT JOIN ProblemToSubject as s on p.subject = s.id ")
    List<String> getProblemSubject();

    // 获取所有难度
    @Query("select distinct d.difficulty from Problem as p left join ProblemToDifficulty  as d on p.difficulty = d.id")
    List<String> getProblemDifficulty();

    //获取所有创建人
    @Query("select distinct u.nickName from Problem as p LEFT JOIN User as u on p.creator = u.id")
    List<String> getProblemCreator();

    //通过type获取一个属于该type的所有problem的List
    List<Problem> findAllByType(Integer id);

    // 根据科目和难度和题型 找到符合该条件的题目数量
    @Query("select count(p) from Problem as p left join ProblemToSubject as s on s.id = p.subject left join ProblemToDifficulty as d on d.id = p.difficulty left join ProblemToType as t on t.id = p.type where t.type = ?2 and s.subject = ?1 and d.difficulty = ?3")
    Integer getCountBySubjectAndTypeAndDifficulty(String subject, String type, String difficulty);


}
