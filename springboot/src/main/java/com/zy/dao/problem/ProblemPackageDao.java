package com.zy.dao.problem;

import com.zy.model.problem.Problem;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Zy
 * @date 2020/4/8 23:35
 */
public interface ProblemPackageDao extends JpaRepository<Problem,Integer> {
    /**
    * @methodsName: getAllConditionCount
    * @description: 通过参数 获取数据库表中符合该参数的所有未被选取过的记录数量 is_usd =1
    * @param:  科目 类型 难度
    * @return: 记录条数
    */
    @Query("select count(p) from Problem as p left join ProblemToSubject as s on s.id = p.subject left join ProblemToType as t on t.id = p.type left join ProblemToDifficulty as d on d.id = p.difficulty where p.isUsed = 1 and s.subject = ?1 and t.type = ?2 and d.difficulty = ?3")
    Integer getAllConditionCount(String subject,String type,String difficulty);
    /**
     * @methodsName: getAllConditionCount
     * @description: 通过参数 获取数据库表中符合该参数的所有记录 不管 is_usd
     * @param:  科目 类型 难度
     * @return: 记录条数
     */
    @Query("select new com.zy.model.problem.Problem(p.id,p.type,p.subject,p.name,p.answer,p.content,p.analysis,p.difficulty,p.creator,p.isUsed) from Problem as p left join ProblemToSubject as s on s.id = p.subject left join ProblemToType as t on t.id = p.type left join ProblemToDifficulty as d on d.id = p.difficulty where s.subject = ?1 and t.type = ?2 and d.difficulty = ?3")
    List<Problem> getAllConditionList(String subject,String type,String difficulty);
   /**
   * @methodsName: getConditionByRandom
   * @description: 通过科目 类型 难度 随机个数来获取随机选取的记录id
    * 是基于baseHandle的再次封装，可直接用于controller类中
   * @param:  String subject 科目,String type 题目类型,String difficulty 难度,Integer rand 选取的条数
   * @return: List<Integer> 带有id的list
   */
    @Query(nativeQuery = true,value = "SELECT p.id FROM t_problem as p left join t_problem_subject as s on s.id = p.subject left join t_problem_type as t on t.id = p.type left join t_problem_difficulty as d on d.id = p.difficulty where p.is_used = 1 and s.subject = ?1 and t.type = ?2 and d.difficulty = ?3 ORDER BY RAND() LIMIT ?4")
    List<Integer> getConditionByRandom(String subject,String type,String difficulty,Integer rand);
}
