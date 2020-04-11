package com.zy.jsonBean.problem;

import lombok.Data;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Zy
 * @date 2020/4/2 13:03
 */
@Data
public class ConditionProblemReqJsonBean {
    //写个静态方法，把null都变成空字符串

    String name ;
    String difficulty;
    String creator;
    String type;
    String subject;
}
