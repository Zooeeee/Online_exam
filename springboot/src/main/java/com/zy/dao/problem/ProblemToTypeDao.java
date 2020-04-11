package com.zy.dao.problem;

import com.zy.model.problem.ProblemToType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Zy
 * @date 2020/4/2 22:22
 */
public interface ProblemToTypeDao extends JpaRepository<ProblemToType,Integer> {
    public ProblemToType findByType(String type);
}
