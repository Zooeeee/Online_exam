package com.zy.vo.problem;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author Zy
 * @date 2020/4/8 18:09
 */
@Data
@AllArgsConstructor
public class ProblemByType {
    String type;
    List<ProblemCountByDifficultyVo> data;
}
