package com.zy.jsonBean.result;

import com.zy.model.problem.Problem;
import com.zy.model.result.Result;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author Zy
 * @date 2020/4/10 20:49
 */
@Data
@AllArgsConstructor
public class OneResultPaperInfo {
    private Map<String, List<Problem>> proInfo;
    private Result ansInfo;
    private Integer choiceMark;
    private Integer blankMark;
    private Integer judgeMark;
}
