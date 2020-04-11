package com.zy.dao.problem;


import com.zy.model.problem.ProblemToDifficulty;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Zy
 * @date 2020/4/7 18:47
 */
public interface ProblemToDifficultyDao extends JpaRepository<ProblemToDifficulty,Integer> {
}
