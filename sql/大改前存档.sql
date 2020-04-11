/*
 Navicat Premium Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : online_exam

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 07/04/2020 18:33:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_exam
-- ----------------------------
DROP TABLE IF EXISTS `t_exam`;
CREATE TABLE `t_exam`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `creator` int(0) NULL DEFAULT NULL COMMENT '谁创建的考试',
  `has_choice` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '有的选择题',
  `has_judge` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '有的判断题',
  `has_blank` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '有的填空题',
  `choice_mark` int(0) NULL DEFAULT NULL COMMENT '单选分值',
  `judge_mark` int(0) NULL DEFAULT NULL COMMENT '判断分值',
  `blank_mark` int(0) NULL DEFAULT NULL COMMENT '填空分值',
  `total_mark` int(0) NULL DEFAULT NULL COMMENT '总分',
  `des` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 74 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_exam
-- ----------------------------
INSERT INTO `t_exam` VALUES (1, '第一场考试', 1, '1,10', '2,4', '3,5', 5, 5, 5, 30, '123');
INSERT INTO `t_exam` VALUES (2, '第3场考试', 3, '2,10', '2,4', '3,5', 5, 5, 5, 30, '12312414');
INSERT INTO `t_exam` VALUES (3, '第3场考试', 3, '1,10', '2,4', '3,5', 5, 5, 5, 30, 'dddddddd');
INSERT INTO `t_exam` VALUES (4, '第一场考试', 3, '1,10', '2,4', '3,5', 5, 5, 5, 30, 'gggggggggggggggg');
INSERT INTO `t_exam` VALUES (5, '第3场考试', 3, '1,10', '2,4', '3,5', 5, 5, 5, 30, 'ccccccccccccccccccccc');
INSERT INTO `t_exam` VALUES (22, '第一场考试', 1, '1,10', '2,4', '3,5', 5, 5, 5, 30, '123');
INSERT INTO `t_exam` VALUES (23, '第3场考试', 1, '1,10', '2,4', '3,5', 5, 5, 5, 30, '123');
INSERT INTO `t_exam` VALUES (32, '第一场考试', 1, '1,10', '2,4', '3,5', 5, 5, 5, 30, '123');
INSERT INTO `t_exam` VALUES (33, '第3场考试', 3, '1,10', '2,4', '3,5', 5, 5, 5, 30, '12312414');
INSERT INTO `t_exam` VALUES (34, '第3场考试', 3, '1,10', '2,4', '3,5', 5, 5, 5, 30, 'dddddddd');
INSERT INTO `t_exam` VALUES (35, '第一场考试', 3, '1,10', '2,4', '3,5', 5, 5, 5, 30, 'gggggggggggggggg');
INSERT INTO `t_exam` VALUES (36, '第3场考试', 3, '1,10', '2,4', '3,5', 5, 5, 5, 30, 'ccccccccccccccccccccc');
INSERT INTO `t_exam` VALUES (37, '第一场考试', 1, '1,10', '2,4', '3,5', 5, 5, 5, 30, '123');
INSERT INTO `t_exam` VALUES (38, '第3场考试', 1, '1,10', '2,4', '3,5', 5, 5, 5, 30, '123');
INSERT INTO `t_exam` VALUES (39, '第一场考试', 1, '1,10', '2,4', '3,5', 5, 5, 5, 30, '123');
INSERT INTO `t_exam` VALUES (40, '第3场考试', 3, '1,10', '2,4', '3,5', 5, 5, 5, 30, '12312414');
INSERT INTO `t_exam` VALUES (41, '第3场考试', 3, '1,10', '2,4', '3,5', 5, 5, 5, 30, 'dddddddd');
INSERT INTO `t_exam` VALUES (42, '第一场考试', 3, '1,10', '2,4', '3,5', 5, 5, 5, 30, 'gggggggggggggggg');
INSERT INTO `t_exam` VALUES (43, '第3场考试', 3, '1,10', '2,4', '3,5', 5, 5, 5, 30, 'ccccccccccccccccccccc');
INSERT INTO `t_exam` VALUES (44, '第一场考试', 1, '1,10', '2,4', '3,5', 5, 5, 5, 30, '123');
INSERT INTO `t_exam` VALUES (45, '第3场考试', 1, '1,10', '2,4', '3,5', 5, 5, 5, 30, '123');
INSERT INTO `t_exam` VALUES (46, '第一场考试', 1, '1,10', '2,4', '3,5', 5, 5, 5, 30, '123');
INSERT INTO `t_exam` VALUES (47, '第3场考试', 3, '1,10', '2,4', '3,5', 5, 5, 5, 30, '12312414');
INSERT INTO `t_exam` VALUES (48, '第3场考试', 3, '1,10', '2,4', '3,5', 5, 5, 5, 30, 'dddddddd');
INSERT INTO `t_exam` VALUES (49, '第一场考试', 3, '1,10', '2,4', '3,5', 5, 5, 5, 30, 'gggggggggggggggg');
INSERT INTO `t_exam` VALUES (50, '第3场考试', 3, '1,10', '2,4', '3,5', 5, 5, 5, 30, 'ccccccccccccccccccccc');
INSERT INTO `t_exam` VALUES (51, '第一场考试', 1, '1,10', '2,4', '3,5', 5, 5, 5, 30, '123');
INSERT INTO `t_exam` VALUES (52, '第3场考试', 1, '1,10', '2,4', '3,5', 5, 5, 5, 30, '123');
INSERT INTO `t_exam` VALUES (53, '第一场考试', 1, '1,10', '2,4', '3,5', 5, 5, 5, 30, '123');
INSERT INTO `t_exam` VALUES (54, '第3场考试', 3, '1,10', '2,4', '3,5', 5, 5, 5, 30, '12312414');
INSERT INTO `t_exam` VALUES (55, '第3场考试', 3, '1,10', '2,4', '3,5', 5, 5, 5, 30, 'dddddddd');
INSERT INTO `t_exam` VALUES (56, '第一场考试', 3, '1,10', '2,4', '3,5', 5, 5, 5, 30, 'gggggggggggggggg');
INSERT INTO `t_exam` VALUES (57, '第3场考试', 3, '1,10', '2,4', '3,5', 5, 5, 5, 30, 'ccccccccccccccccccccc');
INSERT INTO `t_exam` VALUES (58, '第一场考试', 1, '1,10', '2,4', '3,5', 5, 5, 5, 30, '123');
INSERT INTO `t_exam` VALUES (59, '第3场考试', 1, '1,10', '2,4', '3,5', 5, 5, 5, 30, '123');
INSERT INTO `t_exam` VALUES (60, '第一场考试', 1, '1,10', '2,4', '3,5', 5, 5, 5, 30, '123');
INSERT INTO `t_exam` VALUES (61, '第3场考试', 3, '1,10', '2,4', '3,5', 5, 5, 5, 30, '12312414');
INSERT INTO `t_exam` VALUES (62, '第3场考试', 3, '1,10', '2,4', '3,5', 5, 5, 5, 30, 'dddddddd');
INSERT INTO `t_exam` VALUES (63, '第一场考试', 3, '1,10', '2,4', '3,5', 5, 5, 5, 30, 'gggggggggggggggg');
INSERT INTO `t_exam` VALUES (64, '第3场考试', 3, '1,10', '2,4', '3,5', 5, 5, 5, 30, 'ccccccccccccccccccccc');
INSERT INTO `t_exam` VALUES (65, '第一场考试', 1, '1,10', '2,4', '3,5', 5, 5, 5, 30, '123');
INSERT INTO `t_exam` VALUES (66, '第3场考试', 1, '1,10', '2,4', '3,5', 5, 5, 5, 30, '123');
INSERT INTO `t_exam` VALUES (67, '第一场考试', 1, '1,10', '2,4', '3,5', 5, 5, 5, 30, '123');
INSERT INTO `t_exam` VALUES (68, '第3场考试', 3, '1,10', '2,4', '3,5', 5, 5, 5, 30, '12312414');
INSERT INTO `t_exam` VALUES (69, '第3场考试', 3, '1,10', '2,4', '3,5', 5, 5, 5, 30, 'dddddddd');
INSERT INTO `t_exam` VALUES (70, '第一场考试', 3, '1,10', '2,4', '3,5', 5, 5, 5, 30, 'gggggggggggggggg');
INSERT INTO `t_exam` VALUES (71, '第3场考试', 3, '1,10', '2,4', '3,5', 5, 5, 5, 30, 'ccccccccccccccccccccc');
INSERT INTO `t_exam` VALUES (72, '第一场考试', 1, '1,10', '2,4', '3,5', 5, 5, 5, 30, '123');
INSERT INTO `t_exam` VALUES (73, '第3场考试', 1, '1,10', '2,4', '3,5', 5, 5, 5, 30, '123');

-- ----------------------------
-- Table structure for t_problem
-- ----------------------------
DROP TABLE IF EXISTS `t_problem`;
CREATE TABLE `t_problem`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `type` int(0) NULL DEFAULT NULL COMMENT '题目类型',
  `subject` int(0) NULL DEFAULT NULL COMMENT '科目',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '题干',
  `answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '答案',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '选项(选择题为空)',
  `analysis` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '解析',
  `difficulty` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '难度',
  `creator` int(0) NULL DEFAULT NULL COMMENT '出题人id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 97 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_problem
-- ----------------------------
INSERT INTO `t_problem` VALUES (1, 2, 1, '以下哪个不是四大名著', '封神榜', '三国演义,封神榜,水浒传,红楼梦', '中国古典长篇小说四大名著，简称四大名著，是指《水浒传》《三国演义》《西游记》《红楼梦》（按照成书先后顺序）这四部巨著。', '简单', 1);
INSERT INTO `t_problem` VALUES (2, 1, 2, '0是不是自然数', '错误', '正确,错误', '0是自然数', '简单', 1);
INSERT INTO `t_problem` VALUES (3, 3, 3, '我国古代五音', '角', '我国古代五音是：宫，商，()，徵，羽', '宫商角徵羽', '中等', 3);
INSERT INTO `t_problem` VALUES (4, 1, 1, '曾巩是唐宋八大家？', '正确', '正确,错误', '唐宋八大家，又称为“唐宋散文八大家”，是中国唐代柳宗元、韩愈和宋代欧阳修、苏洵、苏轼、苏辙、王安石、曾巩八位散文家的合称。', '中等', 3);
INSERT INTO `t_problem` VALUES (5, 3, 1, '我国古代儒家并称四书的经典', '中庸', '我国古代儒家并称四书的经典是《论语》《孟子》《大学》《()》', ' 四书又称四子书,是《论语》《孟子》《大学》《中庸》的合称。', '困难', 1);
INSERT INTO `t_problem` VALUES (6, 2, 2, '2 x 3 = ？', '6', '3,2,6,9', '无', '简单', 3);
INSERT INTO `t_problem` VALUES (7, 2, 5, '以下那个不是vue的框架特点', '关注DOM的操作', '关注MVVM层,数据双向绑定,渐进式开发,关注DOM的操作', 'Vue关注虚拟DOM的操作', '困难', 1);
INSERT INTO `t_problem` VALUES (8, 3, 5, 'MVVM中的VM', 'ViewModel', 'MVVM中的VM的英文全称是()', 'MVVM是Model-View-ViewModel的简写。它本质上就是MVC 的改进版。', '困难', 3);
INSERT INTO `t_problem` VALUES (9, 3, 4, '对象关系映射简称', 'ORM', '对象关系映射的英文简称是(),是通过使用描述对象和数据库之间映射的元数据，将面向对象语言程序中的对象自动持久化到关系数据库中。', '对象关系映射（Object Relational Mapping，简称ORM）', '中等', 3);
INSERT INTO `t_problem` VALUES (10, 2, 1, '以下哪个不是四大名著', '三国演义', '三国演义,封神榜,水浒传,红楼梦', '中国古典长篇小说四大名著，简称四大名著，是指《水浒传》《三国演义》《西游记》《红楼梦》（按照成书先后顺序）这四部巨著。', '简单', 1);
INSERT INTO `t_problem` VALUES (11, 1, 2, '0是不是自然数', '错误', '正确,错误', '0是自然数', '简单', 1);
INSERT INTO `t_problem` VALUES (12, 3, 3, '我国古代五音', '角', '我国古代五音是：宫，商，()，徵，羽', '宫商角徵羽', '中等', 3);
INSERT INTO `t_problem` VALUES (13, 1, 1, '曾巩是唐宋八大家？', '正确', '正确,错误', '唐宋八大家，又称为“唐宋散文八大家”，是中国唐代柳宗元、韩愈和宋代欧阳修、苏洵、苏轼、苏辙、王安石、曾巩八位散文家的合称。', '中等', 3);
INSERT INTO `t_problem` VALUES (14, 3, 1, '我国古代儒家并称四书的经典', '中庸', '我国古代儒家并称四书的经典是 四书又称四子书,是《论语》《孟子》《大学》《()》', ' 四书又称四子书,是《论语》《孟子》《大学》《中庸》的合称。', '困难', 1);
INSERT INTO `t_problem` VALUES (15, 2, 2, '2 x 3 = ？', '6', '3,2,6,9', '无', '简单', 3);
INSERT INTO `t_problem` VALUES (16, 2, 5, '以下那个不是vue的框架特点', '关注DOM的操作', '关注MVVM层,数据双向绑定,渐进式开发,关注DOM的操作', 'Vue关注虚拟DOM的操作', '困难', 1);
INSERT INTO `t_problem` VALUES (17, 3, 5, 'MVVM中的VM', 'ViewModel', 'MVVM中的VM的英文全称是()', 'MVVM是Model-View-ViewModel的简写。它本质上就是MVC 的改进版。', '困难', 3);
INSERT INTO `t_problem` VALUES (18, 3, 4, '对象关系映射简称', 'ORM', '对象关系映射的英文简称是(),是通过使用描述对象和数据库之间映射的元数据，将面向对象语言程序中的对象自动持久化到关系数据库中。', '对象关系映射（Object Relational Mapping，简称ORM）', '中等', 3);
INSERT INTO `t_problem` VALUES (19, 2, 1, '以下哪个不是四大名著', '封神榜', '三国演义,封神榜,水浒传,红楼梦', '中国古典长篇小说四大名著，简称四大名著，是指《水浒传》《三国演义》《西游记》《红楼梦》（按照成书先后顺序）这四部巨著。', '简单', 1);
INSERT INTO `t_problem` VALUES (20, 1, 2, '0是不是自然数', '错误', '正确,错误', '0是自然数', '简单', 1);
INSERT INTO `t_problem` VALUES (21, 3, 3, '我国古代五音', '角', '我国古代五音是：宫，商，()，徵，羽', '宫商角徵羽', '中等', 3);
INSERT INTO `t_problem` VALUES (22, 1, 1, '曾巩是唐宋八大家？', '正确', '正确,错误', '唐宋八大家，又称为“唐宋散文八大家”，是中国唐代柳宗元、韩愈和宋代欧阳修、苏洵、苏轼、苏辙、王安石、曾巩八位散文家的合称。', '中等', 3);
INSERT INTO `t_problem` VALUES (23, 3, 1, '我国古代儒家并称四书的经典', '中庸', '我国古代儒家并称四书的经典是 四书又称四子书,是《论语》《孟子》《大学》《()》', ' 四书又称四子书,是《论语》《孟子》《大学》《中庸》的合称。', '困难', 1);
INSERT INTO `t_problem` VALUES (24, 2, 2, '2 x 3 = ？', '6', '3,2,6,9', '无', '简单', 3);
INSERT INTO `t_problem` VALUES (25, 2, 5, '以下那个不是vue的框架特点', '关注DOM的操作', '关注MVVM层,数据双向绑定,渐进式开发,关注DOM的操作', 'Vue关注虚拟DOM的操作', '困难', 1);
INSERT INTO `t_problem` VALUES (26, 3, 5, 'MVVM中的VM', 'ViewModel', 'MVVM中的VM的英文全称是()', 'MVVM是Model-View-ViewModel的简写。它本质上就是MVC 的改进版。', '困难', 3);
INSERT INTO `t_problem` VALUES (27, 3, 4, '对象关系映射简称', 'ORM', '对象关系映射的英文简称是(),是通过使用描述对象和数据库之间映射的元数据，将面向对象语言程序中的对象自动持久化到关系数据库中。', '对象关系映射（Object Relational Mapping，简称ORM）', '中等', 3);
INSERT INTO `t_problem` VALUES (28, 2, 1, '以下哪个不是四大名著', '封神榜', '三国演义,封神榜,水浒传,红楼梦', '中国古典长篇小说四大名著，简称四大名著，是指《水浒传》《三国演义》《西游记》《红楼梦》（按照成书先后顺序）这四部巨著。', '简单', 1);
INSERT INTO `t_problem` VALUES (29, 1, 2, '0是不是自然数', '错误', '正确,错误', '0是自然数', '简单', 1);
INSERT INTO `t_problem` VALUES (30, 3, 3, '我国古代五音', '角', '我国古代五音是：宫，商，()，徵，羽', '宫商角徵羽', '中等', 3);
INSERT INTO `t_problem` VALUES (31, 1, 1, '曾巩是唐宋八大家？', '正确', '正确,错误', '唐宋八大家，又称为“唐宋散文八大家”，是中国唐代柳宗元、韩愈和宋代欧阳修、苏洵、苏轼、苏辙、王安石、曾巩八位散文家的合称。', '中等', 3);
INSERT INTO `t_problem` VALUES (32, 3, 1, '我国古代儒家并称四书的经典', '中庸', '我国古代儒家并称四书的经典是 四书又称四子书,是《论语》《孟子》《大学》《()》', ' 四书又称四子书,是《论语》《孟子》《大学》《中庸》的合称。', '困难', 1);
INSERT INTO `t_problem` VALUES (33, 2, 2, '2 x 3 = ？', '6', '3,2,6,9', '无', '简单', 3);
INSERT INTO `t_problem` VALUES (34, 2, 5, '以下那个不是vue的框架特点', '关注DOM的操作', '关注MVVM层,数据双向绑定,渐进式开发,关注DOM的操作', 'Vue关注虚拟DOM的操作', '困难', 1);
INSERT INTO `t_problem` VALUES (35, 3, 5, 'MVVM中的VM', 'ViewModel', 'MVVM中的VM的英文全称是()', 'MVVM是Model-View-ViewModel的简写。它本质上就是MVC 的改进版。', '困难', 3);
INSERT INTO `t_problem` VALUES (36, 3, 4, '对象关系映射简称', 'ORM', '对象关系映射的英文简称是(),是通过使用描述对象和数据库之间映射的元数据，将面向对象语言程序中的对象自动持久化到关系数据库中。', '对象关系映射（Object Relational Mapping，简称ORM）', '中等', 3);
INSERT INTO `t_problem` VALUES (37, 2, 1, '以下哪个不是四大名著', '封神榜', '三国演义,封神榜,水浒传,红楼梦', '中国古典长篇小说四大名著，简称四大名著，是指《水浒传》《三国演义》《西游记》《红楼梦》（按照成书先后顺序）这四部巨著。', '简单', 1);
INSERT INTO `t_problem` VALUES (38, 1, 2, '0是不是自然数', '错误', '正确,错误', '0是自然数', '简单', 1);
INSERT INTO `t_problem` VALUES (39, 3, 3, '我国古代五音', '角', '我国古代五音是：宫，商，()，徵，羽', '宫商角徵羽', '中等', 3);
INSERT INTO `t_problem` VALUES (40, 1, 1, '曾巩是唐宋八大家？', '正确', '正确,错误', '唐宋八大家，又称为“唐宋散文八大家”，是中国唐代柳宗元、韩愈和宋代欧阳修、苏洵、苏轼、苏辙、王安石、曾巩八位散文家的合称。', '中等', 3);
INSERT INTO `t_problem` VALUES (41, 3, 1, '我国古代儒家并称四书的经典', '中庸', '我国古代儒家并称四书的经典是 四书又称四子书,是《论语》《孟子》《大学》《()》', ' 四书又称四子书,是《论语》《孟子》《大学》《中庸》的合称。', '困难', 1);
INSERT INTO `t_problem` VALUES (42, 2, 2, '2 x 3 = ？', '6', '3,2,6,9', '无', '简单', 3);
INSERT INTO `t_problem` VALUES (43, 2, 5, '以下那个不是vue的框架特点', '关注DOM的操作', '关注MVVM层,数据双向绑定,渐进式开发,关注DOM的操作', 'Vue关注虚拟DOM的操作', '困难', 1);
INSERT INTO `t_problem` VALUES (44, 3, 5, 'MVVM中的VM', 'ViewModel', 'MVVM中的VM的英文全称是()', 'MVVM是Model-View-ViewModel的简写。它本质上就是MVC 的改进版。', '困难', 3);
INSERT INTO `t_problem` VALUES (45, 3, 4, '对象关系映射简称', 'ORM', '对象关系映射的英文简称是(),是通过使用描述对象和数据库之间映射的元数据，将面向对象语言程序中的对象自动持久化到关系数据库中。', '对象关系映射（Object Relational Mapping，简称ORM）', '中等', 3);
INSERT INTO `t_problem` VALUES (46, 2, 1, '以下哪个不是四大名著', '封神榜', '三国演义,封神榜,水浒传,红楼梦', '中国古典长篇小说四大名著，简称四大名著，是指《水浒传》《三国演义》《西游记》《红楼梦》（按照成书先后顺序）这四部巨著。', '简单', 1);
INSERT INTO `t_problem` VALUES (47, 1, 2, '0是不是自然数', '错误', '正确,错误', '0是自然数', '简单', 1);
INSERT INTO `t_problem` VALUES (48, 3, 3, '我国古代五音', '角', '我国古代五音是：宫，商，()，徵，羽', '宫商角徵羽', '中等', 3);
INSERT INTO `t_problem` VALUES (49, 1, 1, '曾巩是唐宋八大家？', '正确', '正确,错误', '唐宋八大家，又称为“唐宋散文八大家”，是中国唐代柳宗元、韩愈和宋代欧阳修、苏洵、苏轼、苏辙、王安石、曾巩八位散文家的合称。', '中等', 3);
INSERT INTO `t_problem` VALUES (50, 3, 1, '我国古代儒家并称四书的经典', '中庸', '我国古代儒家并称四书的经典是 四书又称四子书,是《论语》《孟子》《大学》《()》', ' 四书又称四子书,是《论语》《孟子》《大学》《中庸》的合称。', '困难', 1);
INSERT INTO `t_problem` VALUES (51, 2, 2, '2 x 3 = ？', '6', '3,2,6,9', '无', '简单', 3);
INSERT INTO `t_problem` VALUES (52, 2, 5, '以下那个不是vue的框架特点', '关注DOM的操作', '关注MVVM层,数据双向绑定,渐进式开发,关注DOM的操作', 'Vue关注虚拟DOM的操作', '困难', 1);
INSERT INTO `t_problem` VALUES (53, 3, 5, 'MVVM中的VM', 'ViewModel', 'MVVM中的VM的英文全称是()', 'MVVM是Model-View-ViewModel的简写。它本质上就是MVC 的改进版。', '困难', 3);
INSERT INTO `t_problem` VALUES (54, 3, 4, '对象关系映射简称', 'ORM', '对象关系映射的英文简称是(),是通过使用描述对象和数据库之间映射的元数据，将面向对象语言程序中的对象自动持久化到关系数据库中。', '对象关系映射（Object Relational Mapping，简称ORM）', '中等', 3);
INSERT INTO `t_problem` VALUES (55, 2, 1, '以下哪个不是四大名著', '封神榜', '三国演义,封神榜,水浒传,红楼梦', '中国古典长篇小说四大名著，简称四大名著，是指《水浒传》《三国演义》《西游记》《红楼梦》（按照成书先后顺序）这四部巨著。', '简单', 1);
INSERT INTO `t_problem` VALUES (56, 1, 2, '0是不是自然数', '错误', '正确,错误', '0是自然数', '简单', 1);
INSERT INTO `t_problem` VALUES (57, 3, 3, '我国古代五音', '角', '我国古代五音是：宫，商，()，徵，羽', '宫商角徵羽', '中等', 3);
INSERT INTO `t_problem` VALUES (58, 1, 1, '曾巩是唐宋八大家？', '正确', '正确,错误', '唐宋八大家，又称为“唐宋散文八大家”，是中国唐代柳宗元、韩愈和宋代欧阳修、苏洵、苏轼、苏辙、王安石、曾巩八位散文家的合称。', '中等', 3);
INSERT INTO `t_problem` VALUES (59, 3, 1, '我国古代儒家并称四书的经典', '中庸', '我国古代儒家并称四书的经典是 四书又称四子书,是《论语》《孟子》《大学》《()》', ' 四书又称四子书,是《论语》《孟子》《大学》《中庸》的合称。', '困难', 1);
INSERT INTO `t_problem` VALUES (60, 2, 2, '2 x 3 = ？', '6', '3,2,6,9', '无', '简单', 3);
INSERT INTO `t_problem` VALUES (61, 2, 5, '以下那个不是vue的框架特点', '关注DOM的操作', '关注MVVM层,数据双向绑定,渐进式开发,关注DOM的操作', 'Vue关注虚拟DOM的操作', '困难', 1);
INSERT INTO `t_problem` VALUES (62, 3, 5, 'MVVM中的VM', 'ViewModel', 'MVVM中的VM的英文全称是()', 'MVVM是Model-View-ViewModel的简写。它本质上就是MVC 的改进版。', '困难', 3);
INSERT INTO `t_problem` VALUES (63, 3, 4, '对象关系映射简称', 'ORM', '对象关系映射的英文简称是(),是通过使用描述对象和数据库之间映射的元数据，将面向对象语言程序中的对象自动持久化到关系数据库中。', '对象关系映射（Object Relational Mapping，简称ORM）', '中等', 3);
INSERT INTO `t_problem` VALUES (64, 2, 1, '以下哪个不是四大名著', '封神榜', '三国演义,封神榜,水浒传,红楼梦', '中国古典长篇小说四大名著，简称四大名著，是指《水浒传》《三国演义》《西游记》《红楼梦》（按照成书先后顺序）这四部巨著。', '简单', 1);
INSERT INTO `t_problem` VALUES (65, 1, 2, '0是不是自然数', '错误', '正确,错误', '0是自然数', '简单', 1);
INSERT INTO `t_problem` VALUES (66, 3, 3, '我国古代五音', '角', '我国古代五音是：宫，商，()，徵，羽', '宫商角徵羽', '中等', 3);
INSERT INTO `t_problem` VALUES (67, 1, 1, '曾巩是唐宋八大家？', '正确', '正确,错误', '唐宋八大家，又称为“唐宋散文八大家”，是中国唐代柳宗元、韩愈和宋代欧阳修、苏洵、苏轼、苏辙、王安石、曾巩八位散文家的合称。', '中等', 3);
INSERT INTO `t_problem` VALUES (68, 3, 1, '我国古代儒家并称四书的经典', '中庸', '我国古代儒家并称四书的经典是 四书又称四子书,是《论语》《孟子》《大学》《()》', ' 四书又称四子书,是《论语》《孟子》《大学》《中庸》的合称。', '困难', 1);
INSERT INTO `t_problem` VALUES (69, 2, 2, '2 x 3 = ？', '6', '3,2,6,9', '无', '简单', 3);
INSERT INTO `t_problem` VALUES (70, 2, 5, '以下那个不是vue的框架特点', '关注DOM的操作', '关注MVVM层,数据双向绑定,渐进式开发,关注DOM的操作', 'Vue关注虚拟DOM的操作', '困难', 1);
INSERT INTO `t_problem` VALUES (71, 3, 5, 'MVVM中的VM', 'ViewModel', 'MVVM中的VM的英文全称是()', 'MVVM是Model-View-ViewModel的简写。它本质上就是MVC 的改进版。', '困难', 3);
INSERT INTO `t_problem` VALUES (72, 3, 4, '对象关系映射简称', 'ORM', '对象关系映射的英文简称是(),是通过使用描述对象和数据库之间映射的元数据，将面向对象语言程序中的对象自动持久化到关系数据库中。', '对象关系映射（Object Relational Mapping，简称ORM）', '中等', 3);
INSERT INTO `t_problem` VALUES (73, 2, 1, '以下哪个不是四大名著', '封神榜', '三国演义,封神榜,水浒传,红楼梦', '中国古典长篇小说四大名著，简称四大名著，是指《水浒传》《三国演义》《西游记》《红楼梦》（按照成书先后顺序）这四部巨著。', '简单', 1);
INSERT INTO `t_problem` VALUES (74, 1, 2, '0是不是自然数', '错误', '正确,错误', '0是自然数', '简单', 1);
INSERT INTO `t_problem` VALUES (75, 3, 3, '我国古代五音', '角', '我国古代五音是：宫，商，()，徵，羽', '宫商角徵羽', '中等', 3);
INSERT INTO `t_problem` VALUES (76, 1, 1, '曾巩是唐宋八大家？', '正确', '正确,错误', '唐宋八大家，又称为“唐宋散文八大家”，是中国唐代柳宗元、韩愈和宋代欧阳修、苏洵、苏轼、苏辙、王安石、曾巩八位散文家的合称。', '中等', 3);
INSERT INTO `t_problem` VALUES (77, 3, 1, '我国古代儒家并称四书的经典', '中庸', '我国古代儒家并称四书的经典是 四书又称四子书,是《论语》《孟子》《大学》《()》', ' 四书又称四子书,是《论语》《孟子》《大学》《中庸》的合称。', '困难', 1);
INSERT INTO `t_problem` VALUES (78, 2, 2, '2 x 3 = ？', '6', '3,2,6,9', '无', '简单', 3);
INSERT INTO `t_problem` VALUES (79, 2, 5, '以下那个不是vue的框架特点', '关注DOM的操作', '关注MVVM层,数据双向绑定,渐进式开发,关注DOM的操作', 'Vue关注虚拟DOM的操作', '困难', 1);
INSERT INTO `t_problem` VALUES (80, 3, 5, 'MVVM中的VM', 'ViewModel', 'MVVM中的VM的英文全称是()', 'MVVM是Model-View-ViewModel的简写。它本质上就是MVC 的改进版。', '困难', 3);
INSERT INTO `t_problem` VALUES (81, 3, 4, '对象关系映射简称', 'ORM', '对象关系映射的英文简称是(),是通过使用描述对象和数据库之间映射的元数据，将面向对象语言程序中的对象自动持久化到关系数据库中。', '对象关系映射（Object Relational Mapping，简称ORM）', '中等', 3);
INSERT INTO `t_problem` VALUES (82, 2, 1, '以下哪个不是四大名著', '封神榜', '三国演义,封神榜,水浒传,红楼梦', '中国古典长篇小说四大名著，简称四大名著，是指《水浒传》《三国演义》《西游记》《红楼梦》（按照成书先后顺序）这四部巨著。', '简单', 1);
INSERT INTO `t_problem` VALUES (83, 1, 2, '0是不是自然数', '错误', '正确,错误', '0是自然数', '简单', 1);
INSERT INTO `t_problem` VALUES (84, 3, 3, '我国古代五音', '角', '我国古代五音是：宫，商，()，徵，羽', '宫商角徵羽', '中等', 3);
INSERT INTO `t_problem` VALUES (85, 1, 1, '曾巩是唐宋八大家？', '正确', '正确,错误', '唐宋八大家，又称为“唐宋散文八大家”，是中国唐代柳宗元、韩愈和宋代欧阳修、苏洵、苏轼、苏辙、王安石、曾巩八位散文家的合称。', '中等', 3);
INSERT INTO `t_problem` VALUES (86, 3, 1, '我国古代儒家并称四书的经典', '中庸', '我国古代儒家并称四书的经典是 四书又称四子书,是《论语》《孟子》《大学》《()》', ' 四书又称四子书,是《论语》《孟子》《大学》《中庸》的合称。', '困难', 1);
INSERT INTO `t_problem` VALUES (87, 2, 2, '2 x 3 = ？', '6', '3,2,6,9', '无', '简单', 3);
INSERT INTO `t_problem` VALUES (88, 2, 5, '以下那个不是vue的框架特点', '关注DOM的操作', '关注MVVM层,数据双向绑定,渐进式开发,关注DOM的操作', 'Vue关注虚拟DOM的操作', '困难', 1);
INSERT INTO `t_problem` VALUES (89, 3, 5, 'MVVM中的VM', 'ViewModel', 'MVVM中的VM的英文全称是()', 'MVVM是Model-View-ViewModel的简写。它本质上就是MVC 的改进版。', '困难', 3);

-- ----------------------------
-- Table structure for t_problem_difficulty
-- ----------------------------
DROP TABLE IF EXISTS `t_problem_difficulty`;
CREATE TABLE `t_problem_difficulty`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `difficulty` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_problem_difficulty
-- ----------------------------
INSERT INTO `t_problem_difficulty` VALUES (1, 'A');
INSERT INTO `t_problem_difficulty` VALUES (2, 'B');
INSERT INTO `t_problem_difficulty` VALUES (3, 'C');
INSERT INTO `t_problem_difficulty` VALUES (4, 'D');
INSERT INTO `t_problem_difficulty` VALUES (5, 'E');

-- ----------------------------
-- Table structure for t_problem_subject
-- ----------------------------
DROP TABLE IF EXISTS `t_problem_subject`;
CREATE TABLE `t_problem_subject`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `subject` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '科目',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_problem_subject
-- ----------------------------
INSERT INTO `t_problem_subject` VALUES (1, '语文');
INSERT INTO `t_problem_subject` VALUES (2, '数学');
INSERT INTO `t_problem_subject` VALUES (3, '音乐');
INSERT INTO `t_problem_subject` VALUES (4, 'java');
INSERT INTO `t_problem_subject` VALUES (5, 'vue');

-- ----------------------------
-- Table structure for t_problem_type
-- ----------------------------
DROP TABLE IF EXISTS `t_problem_type`;
CREATE TABLE `t_problem_type`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '整形',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_problem_type
-- ----------------------------
INSERT INTO `t_problem_type` VALUES (1, '判断');
INSERT INTO `t_problem_type` VALUES (2, '单选');
INSERT INTO `t_problem_type` VALUES (3, '填空');

-- ----------------------------
-- Table structure for t_result
-- ----------------------------
DROP TABLE IF EXISTS `t_result`;
CREATE TABLE `t_result`  (
  `result_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '试卷id',
  `exam_id` int(0) NULL DEFAULT NULL COMMENT '考试id',
  `stu_id` int(0) NULL DEFAULT NULL COMMENT '学生id',
  `date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `write_answer` json NULL COMMENT '[{\"proId\":1,wtirte:\"正确\"},...]顺序是题目id，考生答案',
  PRIMARY KEY (`result_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_result
-- ----------------------------
INSERT INTO `t_result` VALUES (4, 3, 33, '2020-04-07 15:46:07', NULL);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色',
  `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `power` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户权限：能看的页面',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像src',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, '123', '123', 'teacher', '老师1', 'exam,result,problem', '1586246771931.jpg', 'xxxx@163.com');
INSERT INTO `t_user` VALUES (2, '321', '321', 'student', '学生某某某11', 'result,takeExam,examDetail', '1585973472384.jpg', 'xxxxx@qq.com');
INSERT INTO `t_user` VALUES (3, '111', '111', 'teacher', '老师2', 'exam,result,problem', '1586007942350.jpg', 'xxx@qq.com');

SET FOREIGN_KEY_CHECKS = 1;
