package com.zy.model.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author Zy
 * @date 2020/4/7 15:03
 */
@Entity
@Data
@Table(name = "t_result")
@AllArgsConstructor
@NoArgsConstructor
@TypeDef(name = "json", typeClass = JsonStringType.class)

@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
@EntityListeners(AuditingEntityListener.class)
public class Result  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private Integer resultId;

    @Column(name = "exam_id")
    private Integer examId;

    @Column(name = "stu_id")
    private Integer stuId;

    @Column(name = "total_mark")
    private Integer totalMark;

    //@Column(name = "update_date",insertable = false,updatable = false,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
//    @Column(name = "update_date")
//    private Date updateTime;
    @CreatedDate
    @Column(name = "create_time",updatable = false,nullable = false)
    private Date createTime;

    @Type(type = "json")
    @Column(name = "choice_answer" ,columnDefinition = "json")
    private List<ChoiceAnswerData> choiceAnswer;

    @Type(type = "json")
    @Column(name = "blank_answer" ,columnDefinition = "json")
    private List<BlankAnswerData> blankAnswer;

    @Type(type = "json")
    @Column(name = "judge_answer" ,columnDefinition = "json")
    private List<JudgeAnswerData> judgeAnswer;

}
