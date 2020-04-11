package com.zy.model.problem;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Zy
 * @date 2020/3/31 23:24
 */
@Entity
@Table(name = "t_problem_subject")
@Data
public class ProblemToSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "subject")
    private String subject;

}
