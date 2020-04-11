package com.zy.model.problem;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

/**
 * @author Zy
 * @date 2020/3/31 23:25
 */
@Entity
@Table(name = "t_problem_type")
@Data
public class ProblemToType {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "type")
    private String type;

}