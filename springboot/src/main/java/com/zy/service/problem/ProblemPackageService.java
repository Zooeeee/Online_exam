package com.zy.service.problem;

import com.zy.dao.problem.ProblemDao;
import com.zy.dao.problem.ProblemPackageDao;
import com.zy.model.problem.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.Subject;
import java.util.List;

/**
 * @author Zy
 * @date 2020/4/8 23:26
 */
@Service
public class ProblemPackageService {
    @Autowired
    ProblemPackageDao problemPackageDao;

    /**
    * @methodsName: setConditionProblemsToNotUsed
    * @description: 当提出要求的数量大于表中is_used=1的剩余数量时，将所有符合条件的 is_used 置为1
    * @param:  String subject,String type,String difficulty
    * @return: void
    */
    public void setConditionProblemsToNotUsed(String subject,String type,String difficulty){
        List<Problem> list = problemPackageDao.getAllConditionList(subject,type,difficulty);
        list.forEach(ele->{
            ele.setIsUsed(1);
            System.out.println(ele);
            problemPackageDao.saveAndFlush(ele);
        });
    }


    /**
    * @methodsName: setSelectedProblemsToUsed
    * @description: 通过一个integer id的List  把所有id代表的记录的is_used置为0
    * @param:  List<Integer> list   id
    * @return: void
    */
    @Transactional
    public void setSelectedProblemsToUsed(List<Integer> list){
        list.forEach(ele->{
            Problem one = problemPackageDao.getOne(ele);
            one.setIsUsed(0);
            problemPackageDao.saveAndFlush(one);
        });
    }

    /**
     * @methodsName: getAllConditionCount
     * @description: 通过参数 获取数据库表中符合该参数的所有未被选取过的记录数量 is_usd =1
     * @param:  科目 类型 难度
     * @return: 记录条数
     */
    public Integer getAllConditionCount(String subject,String type,String difficulty){
        return problemPackageDao.getAllConditionCount(subject,type,difficulty);
    }

    /**
     * @methodsName: getAllConditionCount
     * @description: 通过参数 获取数据库表中符合该参数的所有记录 不管 is_usd
     * @param:  科目 类型 难度
     * @return: 记录条数
     */
    public List<Problem> getAllConditionList(String subject,String type,String difficulty){
        return problemPackageDao.getAllConditionList(subject,type,difficulty);
    }

    /**
     * @methodsName: getConditionByRandom
     * @description: 通过科目 类型 难度 随机个数来获取随机选取的记录id
     * @param:  String subject 科目,String type 题目类型,String difficulty 难度,Integer rand 选取的条数
     * @return: List<Integer> 带有id的list
     */
    public List<Integer> getConditionByRandom(String subject,String type,String difficulty,Integer rand){
        List<Integer> conditionByRandom = problemPackageDao.getConditionByRandom(subject, type, difficulty, rand);
        return conditionByRandom;
    }


   /**
   * @methodsName: baseHandle
   * @description:
    * 1.先根据 subject 科目 ， type 类型 ，difficulty 难度 取出符合条件的is_used为1的记录条数 count
    * 2.如果count >= rand  说明未被调用过的记录数量足够 不处理
    * 3.如果count <rand 则说明 说明未被调用过的记录数量不足
    * 则 先利用subject 科目 ， type 类型 ，difficulty 难度 把符合条件的记录 的is_used全部置为1
    * 4.再用 subject 科目 ， type 类型 ，difficulty 难度，rand随机数 取出List<id>
    * 5.并将id代表的记录is_used置为0
    * 6 返回字符串
   * @param:  subject 科目 ， type 类型 ，difficulty 难度，rand  随机选取的个数
   * @return: String  用,连接的id字符串
   */
    @Transactional
    public String baseHandle(String subject,String type,String difficulty,Integer rand){
        // 先根据 subject 科目 ， type 类型 ，difficulty 难度 取出符合条件的is_used为1的记录条数 count
        Integer count = getAllConditionCount(subject, type, difficulty);
        // 2.如果count >= rand  说明未被调用过的记录数量足够 不处理
        if (count >= rand){ }
        // * 3.如果count <rand 则说明 说明未被调用过的记录数量不足
        if (count < rand){
            setConditionProblemsToNotUsed(subject,type,difficulty);
        }
        //  * 4.再用 subject 科目 ， type 类型 ，difficulty 难度，rand随机数 取出List<id>
        List<Integer> conditionByRandom = getConditionByRandom(subject, type, difficulty, rand);
        //     * 5.并将id代表的记录is_used置为0
        setSelectedProblemsToUsed(conditionByRandom);
        // 6 返回字符串
        String res = conditionByRandom.get(0).toString();
        for (int i = 1; i < conditionByRandom.size(); i++) {
            res = res + ',' + conditionByRandom.get(i).toString();
        }
        return res;
    }
    /**
    * @methodsName:     secondHandle
    * @description:  基于baseHandle的二次封装，将难度和数量通过数组的方式传入
    * @param: string subject 科目 ，string type 类型 ，string[] difficulty 难度，integer[]rand  随机选取的个数
    * @return: String  用,连接的id字符串
    */
    @Transactional
    public String secondHandle(String subject,String type,String[] difficulty,Integer[] rand){
        String res = "";
        for (int i = 0; i < difficulty.length; i++) {
            String oneRes = baseHandle(subject,type,difficulty[i],rand[i]);
            res = res + oneRes + ",";
        }
        res = res.substring(0,res.length() -1);
        return res;
    }
}
