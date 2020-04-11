package com.zy.jsonBean.exam;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Time;

/**
 * @author Zy
 * @date 2020/4/9 19:43
 */
@Data
@AllArgsConstructor
public class PackageExamJson {
    String  name;
    String des;
    Integer time;
    String subject;
    Integer choiceMark;
    Integer blankMark;
    Integer judgeMark;
    String[] choiceDiffi;
    Integer[] choiceCount;
    String[] blankDiffi;
    Integer[] blankCount;
    String[] judgeDiffi;
    Integer[] judgeCount;
    Integer totalMark;
    Integer creator;
}
