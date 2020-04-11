package com.zy.vo.result;
import com.zy.model.result.BlankAnswerData;
import com.zy.model.result.ChoiceAnswerData;
import com.zy.model.result.JudgeAnswerData;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author Zy
 * @date 2020/4/10 11:12
 */
@Data
public class ResultVo {
    private Integer resultId;
    private Integer examId;
    private Date createTime;
    private List<ChoiceAnswerData> choiceAnswer;
    private List<BlankAnswerData> blankAnswer;
    private List<JudgeAnswerData> judgeAnswer;
    private Integer totalMark;
    private String stuNickname;
    private String name;
    private String des;
    private String avatar;

    public ResultVo(Integer resultId, Integer examId, Integer totalMark, Date createTime, String stuNickname, String name, String des,String avatar) {
        this.resultId = resultId;
        this.examId = examId;
        this.createTime = createTime;
        this.totalMark = totalMark;
        this.stuNickname = stuNickname;
        this.name = name;
        this.des = des;
        this.avatar = avatar;
    }

    public ResultVo(Integer resultId, Integer examId, Integer totalMark, Date createTime, List<ChoiceAnswerData> choiceAnswer, List<BlankAnswerData> blankAnswer, List<JudgeAnswerData> judgeAnswer, String stuNickname, String name, String des ,String avatar) {
        this.resultId = resultId;
        this.examId = examId;
        this.createTime = createTime;
        this.choiceAnswer = choiceAnswer;
        this.blankAnswer = blankAnswer;
        this.judgeAnswer = judgeAnswer;
        this.totalMark = totalMark;
        this.stuNickname = stuNickname;
        this.name = name;
        this.des = des;
        this.avatar = avatar;
    }
}
