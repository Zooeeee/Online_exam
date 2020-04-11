package com.zy.model.problem;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Zy
 * @date 2020/3/31 22:56
 */
@Entity
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
@Table(name = "t_problem")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "type")
    private Integer type;

    @Column(name = "subject")
    private Integer subject;

    @Column(name = "name")
    private String name;

    @Column(name = "answer")
    private String answer;

    @Column(name = "content")
    private String content;

    @Column(name = "analysis")
    private String analysis;

    @Column(name = "difficulty")
    private Integer difficulty;

    @Column(name = "creator")
    private Integer creator;

    @Column(name = "is_used")
    private Integer isUsed;

}
