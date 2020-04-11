/**
 * Copyright 2020 bejson.com
 */
package com.zy.jsonBean.problem;

/**
 * Auto-generated: 2020-04-01 17:40:2
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class EditJsonBean {

    private String id;
    private String name;
    private String analysis;
    private String answer;
    private String content;
    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "EditJsonBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", analysis='" + analysis + '\'' +
                ", answer='" + answer + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }
    public String getAnalysis() {
        return analysis;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public String getAnswer() {
        return answer;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }

}