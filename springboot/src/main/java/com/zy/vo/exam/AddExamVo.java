package com.zy.vo.exam;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author Zy
 * @date 2020/4/3 21:01
 */
@Data
@AllArgsConstructor
public class AddExamVo {
    String hasChoice;
    String hasBlank;
    String hasJudge;
    String des;
    String name;
    Integer creator;
    Integer choiceMark;
    Integer blankMark;
    Integer judgeMark;
    Integer totalMark;


}
