package com.zy.vo.exam;

import lombok.Data;

import java.sql.Time;
import java.util.List;

/**
 * @author Zy
 * @date 2020/4/3 11:44
 */
@Data
public class ExamVo {
     Integer id;
     String hasChoice;
     List<String> choiceList;
     String hasBlank;
     List<String> blankList;
     String hasJudge;
     List<String> judgeList;
     String des;
     String name;
     Integer creator;
     String creatorName;
     Integer choiceMark;
     Integer blankMark;
     Integer judgeMark;
     Integer totalMark;
     String avatar;
        Integer time;

    public ExamVo(Integer id,String name, String creatorName,String hasJudge,String hasBlank,String hasChoice,  Integer choiceMark, Integer judgeMark, Integer blankMark,String des,   Integer totalMark,Integer time) {
        this.id = id;
        this.hasChoice = hasChoice;
        this.hasBlank = hasBlank;
        this.hasJudge = hasJudge;
        this.des = des;
        this.name = name;
        this.creatorName = creatorName;
        this.choiceMark = choiceMark;
        this.blankMark = blankMark;
        this.judgeMark = judgeMark;
        this.totalMark = totalMark;
        this.time = time;
    }

    public ExamVo(String hasChoice, String hasBlank, String hasJudge, String des, String name, Integer creator, Integer choiceMark, Integer blankMark, Integer judgeMark, Integer totalMark,Integer time) {
        this.hasChoice = hasChoice;
        this.hasBlank = hasBlank;
        this.hasJudge = hasJudge;
        this.des = des;
        this.name = name;
        this.creator = creator;
        this.choiceMark = choiceMark;
        this.blankMark = blankMark;
        this.judgeMark = judgeMark;
        this.totalMark = totalMark;
        this.time = time;
    }

    public ExamVo(Integer id, String hasChoice, String hasBlank, String hasJudge, String des, String name, String creatorName, Integer choiceMark, Integer blankMark, Integer judgeMark, Integer totalMark, String avatar,  Integer time) {
        this.id = id;
        this.hasChoice = hasChoice;
        this.hasBlank = hasBlank;
        this.hasJudge = hasJudge;
        this.des = des;
        this.name = name;
        this.creatorName = creatorName;
        this.choiceMark = choiceMark;
        this.blankMark = blankMark;
        this.judgeMark = judgeMark;
        this.totalMark = totalMark;
        this.avatar = avatar;
        this.time = time;
    }
}
