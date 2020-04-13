# Online_exam
### 毕业设计项目,在线考试系统
#### 框架使用:
* vue+vuex+vrouter+axios+elementUI+less
* springboot + spring data jpa
#### 运行环境
* node 12.16
* java 13
* mysql 8
* @vue/cli 4.2.3
#### 角色功能
* 老师:能对题目进行crud,能通过手动或者自动(规定题型,科目,难度,数量)的方法将问题组装成一张试卷.能查看所有学生的试卷
* 学生:能参加考试,能查看自己的考试成绩
* 所有人:修改个人信息,上传头像.
#### 特点
* 前端根据权限(token)进行简单的路由控制,使得学生不能进入老师专属的路由,老师也不能进入学生的专属路由,位置路由全部重定向到404页面
* 在后端,根据jwt生成token发送到前端.前端解析jwt获得用户id和用户昵称.
* 前端将token储存在sessionstorage中,在axios中设置之后的每次发送都会在http的headers中添加一个Authorization字段,值为token.
* 可以根据老师选定的难度和题型,自动组装试卷,且多次产生的试卷题目避免重复.,此处是在表中添加一个is_used字段,默认值为1,当随机选取过该值时,则将此值置为0.
* 如果发送过来的请求数量<表中剩余is_used=1的记录,则从这些记录中随机选取,并将is_used置为0.
* 如果发来过来的请求的数量大于表中剩余is_used=1的字段,则将全体记录的is_used重置为1.重复上一步.

