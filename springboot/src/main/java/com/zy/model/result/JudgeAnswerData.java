package com.zy.model.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Zy
 * @date 2020/4/7 15:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JudgeAnswerData implements Serializable {
    private Integer proId;
    private String write;
}
