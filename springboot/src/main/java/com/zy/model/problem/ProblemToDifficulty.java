package com.zy.model.problem;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Zy
 * @date 2020/4/7 18:45
 */
@Entity
@Table(name = "t_problem_difficulty")
@Data
public class ProblemToDifficulty {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "difficulty")
    private String difficulty;
}
