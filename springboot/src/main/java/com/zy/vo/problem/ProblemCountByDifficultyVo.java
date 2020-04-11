package com.zy.vo.problem;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Zy
 * @date 2020/4/8 16:47
 */
@Data
@AllArgsConstructor
public class ProblemCountByDifficultyVo {
    private String difficulty;
    private Integer count;
}
