package com.zy.model.exam;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

/**
 * @author Zy
 * @date 2020/4/3 11:34
 */
@Entity
@Table(name = "t_exam")
@Data
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "has_choice")
    private String hasChoice;

    @Column(name = "has_blank")
    private String hasBlank;

    @Column(name = "has_judge")
    private String hasJudge;

    @Column(name = "des")
    private String des;

    @Column(name = "name")
    private String name;

    @Column(name =  "creator")
    private Integer creator;

    @Column(name = "choice_mark")
    private Integer choiceMark;

    @Column(name = "blank_mark")
    private Integer blankMark;

    @Column(name = "judge_mark")
    private Integer judgeMark;

    @Column(name = "total_mark")
    private Integer totalMark;

    @Column(name = "time")
    private Integer time;

//    @CreatedDate
//    @Column(name = "write_date",nullable = false)
//    private Date writeTime;


    public Exam(String hasChoice, String hasBlank, String hasJudge, String des, String name, Integer creator, Integer choiceMark, Integer blankMark, Integer judgeMark, Integer totalMark) {
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
    }
    public Exam(String hasChoice, String hasBlank, String hasJudge, String des, String name, Integer creator, Integer choiceMark, Integer blankMark, Integer judgeMark, Integer totalMark,Integer time) {
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

    public Exam() {
    }

    public Exam(Integer id, String hasChoice, String hasBlank, String hasJudge, String des, String name, Integer creator, Integer choiceMark, Integer blankMark, Integer judgeMark, Integer totalMark) {
        this.id = id;
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
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", hasChoice='" + hasChoice + '\'' +
                ", hasBlank='" + hasBlank + '\'' +
                ", hasJudge='" + hasJudge + '\'' +
                ", des='" + des + '\'' +
                ", name='" + name + '\'' +
                ", creator=" + creator +
                ", choiceMark=" + choiceMark +
                ", blankMark=" + blankMark +
                ", judgeMark=" + judgeMark +
                ", totalMark=" + totalMark +
                ", time=" + time +
//                ", writeTime=" + writeTime +
                '}';
    }
}
