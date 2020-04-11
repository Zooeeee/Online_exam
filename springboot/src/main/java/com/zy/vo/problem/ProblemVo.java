package com.zy.vo.problem;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Zy
 * @date 2020/3/31 23:06
 */
@Data
@AllArgsConstructor
public class ProblemVo {
    private Integer id;
    private String type;
    private String subject;
    private String name;
    private String answer;
    private String content;
    private String analysis;
    private String difficulty;
    private String creator;
    private Integer isUsed;
    public ProblemVo(Integer id, String type, String subject, String name, String answer, String content, String analysis, String difficulty, String creator) {
        this.id = id;
        this.type = type;
        this.subject = subject;
        this.name = name;
        this.answer = answer;
        this.content = content;
        this.analysis = analysis;
        this.difficulty = difficulty;
        this.creator = creator;
    }


}
