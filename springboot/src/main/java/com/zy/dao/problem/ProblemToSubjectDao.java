package com.zy.dao.problem;

import com.zy.model.problem.ProblemToSubject;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Zy
 * @date 2020/4/2 22:21
 */
public interface ProblemToSubjectDao extends JpaRepository<ProblemToSubject,Integer> {
    public ProblemToSubject findBySubject(String subject);

    @Query("SELECT subject from ProblemToSubject ")
    public List<String> getAllSubjects();
}
