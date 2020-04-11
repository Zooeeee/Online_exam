## springboot
* spring data jpa
* 静态图片资源的非空才能生成target
* 一些基本的注释
* contoller类接受复杂json数据需要相应的jsonBean，并且加上@RequestBody注释参数
* 
## vue
* 编辑每条记录，却使用一个dialog，会造成数据不更新
此时在父组件dialog的子组件中动态绑定一个key值，利用vue的diff算法，可以实现重新挂载
* 场景：编辑某个数据，在想给下拉选择多选框赋值时，一开始以为就是把赋值反过来做，多选框里始终现实的时value而不是label。最后试出来应该在倒推赋值的时候用map将数组中的每个值从字符串变成int
* 组件穿透修改样式
* 动态添加vmodel https://www.jianshu.com/p/4eda6a92bca4
## springdatajpa
* option是mysql的关键字
* 返回vo类时要注意vo类有没有相应的构造函数
* 关于mysql数据库中字段是json的存取值方法  https://blog.csdn.net/weixin_44808592/article/details/103428107
* List返回entity类报错 https://blog.csdn.net/liu_yulong/article/details/84594771?depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-1&utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-1
* jpa的最后修改时间和创建时间 https://blog.csdn.net/qphelloworld/article/details/99438683
* 最后修改时间 时区问题 https://www.jianshu.com/p/597bbb5c299a