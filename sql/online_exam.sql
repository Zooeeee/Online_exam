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

 Date: 11/05/2020 18:33:56
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
  `time` int(0) NULL DEFAULT NULL COMMENT '考试时长-分钟',
  `write_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '考试时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 88 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_exam
-- ----------------------------
INSERT INTO `t_exam` VALUES (1, '第一场考试', 1, '1,6', '2,4', '3,5', 5, 5, 5, 30, '我是第一场考试的简介', 60, '2020-04-30 14:09:55');
INSERT INTO `t_exam` VALUES (2, '第二场考试', 3, '1', '4,25', '3,37', 5, 7, 5, 29, '第二场考试', 20, '2020-04-17 10:24:06');
INSERT INTO `t_exam` VALUES (77, '3333', 3, '30,33', '24,27', '36,37', 5, 5, 5, 30, '我是第3场考试的简介', 1, '2020-05-01 20:45:37');
INSERT INTO `t_exam` VALUES (78, '4444', 1, '126,54,34,72,108,90,91,35,73,127,109,55', '48,28,102,103', '114,96,115,79', 10, 6, 6, 168, '我是第444一场考试的简介', 40, '2020-04-30 14:10:01');
INSERT INTO `t_exam` VALUES (79, '33', 3, '6,33,32', '25,28', '5,37,39', 8, 8, 7, 61, '我是31123123一场考试的简介', 6, '2020-04-30 14:10:04');
INSERT INTO `t_exam` VALUES (81, '555', 1, '88,70,52,124,106,33,107,71,6,125,89', '100,46,26,83,27', '76,38,130,94,95,113,59,77', 5, 5, 5, 120, '我是第一场44444考试的简介', 20, '2020-04-30 14:10:06');
INSERT INTO `t_exam` VALUES (82, '33', 3, '32,52,124,70,88,106,33,53,125,107,89', '118,82,64,2,47', '76,58,38,112,95,39,113,77', 5, 5, 5, 120, '我是水水水水水水一场考试的简介', 25, '2020-04-30 14:10:09');
INSERT INTO `t_exam` VALUES (86, '测试考试', 3, '31,33,6', '2,4', '3,5', 5, 5, 8, 41, '我是第2222222222场考试的简介', 20, '2020-04-30 14:10:14');

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
  `creator` int(0) NULL DEFAULT NULL COMMENT '出题人id',
  `difficulty` int(0) NULL DEFAULT NULL COMMENT '难度',
  `is_used` int(0) NULL DEFAULT NULL COMMENT '是否被调用过，用于自动组装试卷，1为未调用过，调用过，则置为0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 137 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_problem
-- ----------------------------
INSERT INTO `t_problem` VALUES (1, 2, 1, '以下哪个不是四大名著', '封神榜', '三国演义,封神榜,水浒传,红楼梦', '中国古典长篇小说四大名著，简称四大名著，是指《水浒传》《三国演义》《西游记》《红楼梦》（按照成书先后顺序）这四部巨著。', 1, 1, 0);
INSERT INTO `t_problem` VALUES (2, 1, 2, '0是不是自然数', '正确', '正确,错误', '0是自然数', 1, 2, 0);
INSERT INTO `t_problem` VALUES (3, 3, 3, '我国古代五音', '角', '我国古代五音是：宫，商，()，徵，羽', '宫商角徵羽', 3, 1, 0);
INSERT INTO `t_problem` VALUES (4, 1, 1, '曾巩是唐宋八大家？', '正确', '正确,错误', '唐宋八大家，又称为“唐宋散文八大家”，是中国唐代柳宗元、韩愈和宋代欧阳修、苏洵、苏轼、苏辙、王安石、曾巩八位散文家的合称。', 3, 1, 1);
INSERT INTO `t_problem` VALUES (5, 3, 1, '我国古代儒家并称四书的经典', '中庸', '我国古代儒家并称四书的经典是《论语》《孟子》《大学》《()》', ' 四书又称四子书,是《论语》《孟子》《大学》《中庸》的合称。', 1, 2, 1);
INSERT INTO `t_problem` VALUES (6, 2, 2, '2 x 3 = ？', '6', '3,2,6,9', '无', 3, 2, 1);
INSERT INTO `t_problem` VALUES (24, 1, 1, '测试数据1', '错误', '正确,错误', '测试数据1', 1, 1, 1);
INSERT INTO `t_problem` VALUES (25, 1, 1, '测试数据2', '错误', '正确,错误', '测试数据2', 3, 2, 1);
INSERT INTO `t_problem` VALUES (26, 1, 2, '测试数据3', '错误', '正确,错误', '测试数据3', 1, 1, 0);
INSERT INTO `t_problem` VALUES (27, 1, 2, '测试数据4', '错误', '正确,错误', '测试数据4', 3, 2, 1);
INSERT INTO `t_problem` VALUES (28, 1, 3, '测试数据5', '错误', '正确,错误', '测试数据5', 1, 1, 0);
INSERT INTO `t_problem` VALUES (29, 1, 3, '测试数据6', '错误', '正确,错误', '测试数据6', 3, 2, 1);
INSERT INTO `t_problem` VALUES (30, 2, 1, '测试数据7', '封神榜', '三国演义,封神榜,水浒传,红楼梦', '测试数据7', 1, 1, 1);
INSERT INTO `t_problem` VALUES (31, 2, 1, '测试数据8', '封神榜', '三国演义,封神榜,水浒传,红楼梦', '测试数据8', 3, 2, 0);
INSERT INTO `t_problem` VALUES (32, 2, 2, '测试数据9', '封神榜', '三国演义,封神榜,水浒传,红楼梦', '测试数据9', 1, 1, 0);
INSERT INTO `t_problem` VALUES (33, 2, 2, '测试数据10', '封神榜', '三国演义,封神榜,水浒传,红楼梦', '测试数据10', 3, 2, 0);
INSERT INTO `t_problem` VALUES (34, 2, 3, '测试数据11', '封神榜', '三国演义,封神榜,水浒传,红楼梦', '测试数据11', 1, 1, 0);
INSERT INTO `t_problem` VALUES (35, 2, 3, '测试数据12', '封神榜', '三国演义,封神榜,水浒传,红楼梦', '测试数据12', 3, 2, 0);
INSERT INTO `t_problem` VALUES (36, 3, 1, '测试数据13', '中庸', '我国古代儒家并称四书的经典是《论语》《孟子》《大学》《()》', '测试数据13', 1, 1, 1);
INSERT INTO `t_problem` VALUES (37, 3, 1, '测试数据14', '中庸', '我国古代儒家并称四书的经典是《论语》《孟子》《大学》《()》', '测试数据14', 3, 2, 1);
INSERT INTO `t_problem` VALUES (38, 3, 2, '测试数据15', '中庸', '我国古代儒家并称四书的经典是《论语》《孟子》《大学》《()》', '测试数据15', 1, 1, 0);
INSERT INTO `t_problem` VALUES (39, 3, 2, '测试数据16', '中庸', '我国古代儒家并称四书的经典是《论语》《孟子》《大学》《()》', '测试数据16', 3, 2, 0);
INSERT INTO `t_problem` VALUES (40, 3, 3, '测试数据17', '中庸', '我国古代儒家并称四书的经典是《论语》《孟子》《大学》《()》', '测试数据17', 1, 1, 1);
INSERT INTO `t_problem` VALUES (41, 3, 3, '测试数据18', '中庸', '我国古代儒家并称四书的经典是《论语》《孟子》《大学》《()》', '测试数据18', 3, 2, 1);
INSERT INTO `t_problem` VALUES (44, 1, 1, '测试数据1', '错误', '正确,错误', '测试数据1', 1, 1, 1);
INSERT INTO `t_problem` VALUES (45, 1, 1, '测试数据2', '错误', '正确,错误', '测试数据2', 3, 2, 1);
INSERT INTO `t_problem` VALUES (46, 1, 2, '测试数据3', '错误', '正确,错误', '测试数据3', 1, 1, 1);
INSERT INTO `t_problem` VALUES (47, 1, 2, '测试数据4', '错误', '正确,错误', '测试数据4', 3, 2, 0);
INSERT INTO `t_problem` VALUES (48, 1, 3, '测试数据5', '错误', '正确,错误', '测试数据5', 1, 1, 0);
INSERT INTO `t_problem` VALUES (49, 1, 3, '测试数据6', '错误', '正确,错误', '测试数据6', 3, 2, 1);
INSERT INTO `t_problem` VALUES (50, 2, 1, '测试数据7', '封神榜', '三国演义,封神榜,水浒传,红楼梦', '测试数据7', 1, 1, 1);
INSERT INTO `t_problem` VALUES (51, 2, 1, '测试数据8', '封神榜', '三国演义,封神榜,水浒传,红楼梦', '测试数据8', 3, 2, 1);
INSERT INTO `t_problem` VALUES (52, 2, 2, '测试数据9', '封神榜', '三国演义,封神榜,水浒传,红楼梦', '测试数据9', 1, 1, 0);
INSERT INTO `t_problem` VALUES (53, 2, 2, '测试数据10', '封神榜', '三国演义,封神榜,水浒传,红楼梦', '测试数据10', 3, 2, 1);
INSERT INTO `t_problem` VALUES (54, 2, 3, '测试数据11', '封神榜', '三国演义,封神榜,水浒传,红楼梦', '测试数据11', 1, 1, 0);
INSERT INTO `t_problem` VALUES (55, 2, 3, '测试数据12', '封神榜', '三国演义,封神榜,水浒传,红楼梦', '测试数据12', 3, 2, 0);
INSERT INTO `t_problem` VALUES (56, 3, 1, '测试数据13', '中庸', '我国古代儒家并称四书的经典是《论语》《孟子》《大学》《()》', '测试数据13', 1, 1, 1);
INSERT INTO `t_problem` VALUES (57, 3, 1, '测试数据14', '中庸', '我国古代儒家并称四书的经典是《论语》《孟子》《大学》《()》', '测试数据14', 3, 2, 1);
INSERT INTO `t_problem` VALUES (58, 3, 2, '测试数据15', '中庸', '我国古代儒家并称四书的经典是《论语》《孟子》《大学》《()》', '测试数据15', 1, 1, 1);
INSERT INTO `t_problem` VALUES (59, 3, 2, '测试数据16', '中庸', '我国古代儒家并称四书的经典是《论语》《孟子》《大学》《()》', '测试数据16', 3, 2, 0);
INSERT INTO `t_problem` VALUES (60, 3, 3, '测试数据17', '中庸', '我国古代儒家并称四书的经典是《论语》《孟子》《大学》《()》', '测试数据17', 1, 1, 1);
INSERT INTO `t_problem` VALUES (61, 3, 3, '测试数据18', '中庸', '我国古代儒家并称四书的经典是《论语》《孟子》《大学》《()》', '测试数据18', 3, 2, 1);
INSERT INTO `t_problem` VALUES (62, 1, 1, '测试数据1', '错误', '正确,错误', '测试数据1', 1, 1, 1);
INSERT INTO `t_problem` VALUES (63, 1, 1, '测试数据2', '错误', '正确,错误', '测试数据2', 3, 2, 1);
INSERT INTO `t_problem` VALUES (64, 1, 2, '测试数据3', '错误', '正确,错误', '测试数据3', 1, 1, 0);
INSERT INTO `t_problem` VALUES (65, 1, 2, '测试数据4', '错误', '正确,错误', '测试数据4', 3, 2, 0);
INSERT INTO `t_problem` VALUES (66, 1, 3, '测试数据5', '错误', '正确,错误', '测试数据5', 1, 1, 1);
INSERT INTO `t_problem` VALUES (67, 1, 3, '测试数据6', '错误', '正确,错误', '测试数据6', 3, 2, 1);
INSERT INTO `t_problem` VALUES (68, 2, 1, '测试数据7', '封神榜', '三国演义,封神榜,水浒传,红楼梦', '测试数据7', 1, 1, 1);
INSERT INTO `t_problem` VALUES (69, 2, 1, '测试数据8', '封神榜', '三国演义,封神榜,水浒传,红楼梦', '测试数据8', 3, 2, 0);
INSERT INTO `t_problem` VALUES (70, 2, 2, '测试数据9', '封神榜', '三国演义,封神榜,水浒传,红楼梦', '测试数据9', 1, 1, 0);
INSERT INTO `t_problem` VALUES (71, 2, 2, '测试数据10', '封神榜', '三国演义,封神榜,水浒传,红楼梦', '测试数据10', 3, 2, 0);
INSERT INTO `t_problem` VALUES (72, 2, 3, '测试数据11', '封神榜', '三国演义,封神榜,水浒传,红楼梦', '测试数据11', 1, 1, 0);
INSERT INTO `t_problem` VALUES (73, 2, 3, '测试数据12', '封神榜', '三国演义,封神榜,水浒传,红楼梦', '测试数据12', 3, 2, 0);
INSERT INTO `t_problem` VALUES (74, 3, 1, '测试数据13', '中庸', '我国古代儒家并称四书的经典是《论语》《孟子》《大学》《()》', '测试数据13', 1, 1, 1);
INSERT INTO `t_problem` VALUES (75, 3, 1, '测试数据14', '中庸', '我国古代儒家并称四书的经典是《论语》《孟子》《大学》《()》', '测试数据14', 3, 2, 1);
INSERT INTO `t_problem` VALUES (76, 3, 2, '测试数据15', '中庸', '我国古代儒家并称四书的经典是《论语》《孟子》《大学》《()》', '测试数据15', 1, 1, 1);
INSERT INTO `t_problem` VALUES (77, 3, 2, '测试数据16', '中庸', '我国古代儒家并称四书的经典是《论语》《孟子》《大学》《()》', '测试数据16', 3, 2, 0);
INSERT INTO `t_problem` VALUES (78, 3, 3, '测试数据17', '中庸', '我国古代儒家并称四书的经典是《论语》《孟子》《大学》《()》', '测试数据17', 1, 1, 1);
INSERT INTO `t_problem` VALUES (79, 3, 3, '测试数据18', '中庸', '我国古代儒家并称四书的经典是《论语》《孟子》《大学》《()》', '测试数据18', 3, 2, 0);
INSERT INTO `t_problem` VALUES (80, 1, 1, '测试数据1', '错误', '正确,错误', '测试数据1', 1, 1, 1);
INSERT INTO `t_problem` VALUES (81, 1, 1, '测试数据2', '错误', '正确,错误', '测试数据2', 3, 2, 1);
INSERT INTO `t_problem` VALUES (82, 1, 2, '测试数据3', '错误', '正确,错误', '测试数据3', 1, 1, 1);
INSERT INTO `t_problem` VALUES (83, 1, 2, '测试数据4', '错误', '正确,错误', '测试数据4', 3, 2, 1);
INSERT INTO `t_problem` VALUES (84, 1, 3, '测试数据5', '错误', '正确,错误', '测试数据5', 1, 1, 1);
INSERT INTO `t_problem` VALUES (85, 1, 3, '测试数据6', '错误', '正确,错误', '测试数据6', 3, 2, 1);
INSERT INTO `t_problem` VALUES (86, 2, 1, '测试数据7', '封神榜', '三国演义,封神榜,水浒传,红楼梦', '测试数据7', 1, 1, 1);
INSERT INTO `t_problem` VALUES (87, 2, 1, '测试数据8', '封神榜', '三国演义,封神榜,水浒传,红楼梦', '测试数据8', 3, 2, 0);
INSERT INTO `t_problem` VALUES (88, 2, 2, '测试数据9', '封神榜', '三国演义,封神榜,水浒传,红楼梦', '测试数据9', 1, 1, 0);
INSERT INTO `t_problem` VALUES (89, 2, 2, '测试数据10', '封神榜', '三国演义,封神榜,水浒传,红楼梦', '测试数据10', 3, 2, 1);
INSERT INTO `t_problem` VALUES (90, 2, 3, '测试数据11', '封神榜', '三国演义,封神榜,水浒传,红楼梦', '测试数据11', 1, 1, 0);
INSERT INTO `t_problem` VALUES (91, 2, 3, '测试数据12', '封神榜', '三国演义,封神榜,水浒传,红楼梦', '测试数据12', 3, 2, 0);
INSERT INTO `t_problem` VALUES (92, 3, 1, '测试数据13', '中庸', '我国古代儒家并称四书的经典是《论语》《孟子》《大学》《()》', '测试数据13', 1, 1, 1);
INSERT INTO `t_problem` VALUES (93, 3, 1, '测试数据14', '中庸', '我国古代儒家并称四书的经典是《论语》《孟子》《大学》《()》', '测试数据14', 3, 2, 1);
INSERT INTO `t_problem` VALUES (94, 3, 2, '测试数据15', '中庸', '我国古代儒家并称四书的经典是《论语》《孟子》《大学》《()》', '测试数据15', 1, 1, 0);
INSERT INTO `t_problem` VALUES (95, 3, 2, '测试数据16', '中庸', '我国古代儒家并称四书的经典是《论语》《孟子》《大学》《()》', '测试数据16', 3, 2, 0);
INSERT INTO `t_problem` VALUES (96, 3, 3, '测试数据17', '中庸', '我国古代儒家并称四书的经典是《论语》《孟子》《大学》《()》', '测试数据17', 1, 1, 0);
INSERT INTO `t_problem` VALUES (97, 3, 3, '测试数据18', '中庸', '我国古代儒家并称四书的经典是《论语》《孟子》《大学》《()》', '测试数据18', 3, 2, 1);
INSERT INTO `t_problem` VALUES (98, 1, 1, '测试数据1', '错误', '正确,错误', '测试数据1', 1, 1, 1);
INSERT INTO `t_problem` VALUES (99, 1, 1, '测试数据2', '错误', '正确,错误', '测试数据2', 3, 2, 1);
INSERT INTO `t_problem` VALUES (100, 1, 2, '测试数据3', '错误', '正确,错误', '测试数据3', 1, 1, 1);
INSERT INTO `t_problem` VALUES (101, 1, 2, '测试数据4', '错误', '正确,错误', '测试数据4', 3, 2, 0);
INSERT INTO `t_problem` VALUES (102, 1, 3, '测试数据5', '错误', '正确,错误', '测试数据5', 1, 1, 0);
INSERT INTO `t_problem` VALUES (103, 1, 3, '测试数据6', '错误', '正确,错误', '测试数据6', 3, 2, 0);
INSERT INTO `t_problem` VALUES (104, 2, 1, '测试数据7', '封神榜', '三国演义,封神榜,水浒传,红楼梦', '测试数据7', 1, 1, 1);
INSERT INTO `t_problem` VALUES (105, 2, 1, '测试数据8', '封神榜', '三国演义,封神榜,水浒传,红楼梦', '测试数据8', 3, 2, 0);
INSERT INTO `t_problem` VALUES (106, 2, 2, '测试数据9', '封神榜', '三国演义,封神榜,水浒传,红楼梦', '测试数据9', 1, 1, 0);
INSERT INTO `t_problem` VALUES (107, 2, 2, '测试数据10', '封神榜', '三国演义,封神榜,水浒传,红楼梦', '测试数据10', 3, 2, 0);
INSERT INTO `t_problem` VALUES (108, 2, 3, '测试数据11', '封神榜', '三国演义,封神榜,水浒传,红楼梦', '测试数据11', 1, 1, 0);
INSERT INTO `t_problem` VALUES (109, 2, 3, '测试数据12', '封神榜', '三国演义,封神榜,水浒传,红楼梦', '测试数据12', 3, 2, 0);
INSERT INTO `t_problem` VALUES (110, 3, 1, '测试数据13', '中庸', '我国古代儒家并称四书的经典是《论语》《孟子》《大学》《()》', '测试数据13', 1, 1, 1);
INSERT INTO `t_problem` VALUES (111, 3, 1, '测试数据14', '中庸', '我国古代儒家并称四书的经典是《论语》《孟子》《大学》《()》', '测试数据14', 3, 2, 1);
INSERT INTO `t_problem` VALUES (112, 3, 2, '测试数据15', '中庸', '我国古代儒家并称四书的经典是《论语》《孟子》《大学》《()》', '测试数据15', 1, 1, 1);
INSERT INTO `t_problem` VALUES (113, 3, 2, '测试数据16', '中庸', '我国古代儒家并称四书的经典是《论语》《孟子》《大学》《()》', '测试数据16', 3, 2, 0);
INSERT INTO `t_problem` VALUES (114, 3, 3, '测试数据17', '中庸', '我国古代儒家并称四书的经典是《论语》《孟子》《大学》《()》', '测试数据17', 1, 1, 0);
INSERT INTO `t_problem` VALUES (115, 3, 3, '测试数据18', '中庸', '我国古代儒家并称四书的经典是《论语》《孟子》《大学》《()》', '测试数据18', 3, 2, 0);
INSERT INTO `t_problem` VALUES (116, 1, 1, '测试数据1', '错误', '正确,错误', '测试数据1', 1, 1, 1);
INSERT INTO `t_problem` VALUES (117, 1, 1, '测试数据2', '错误', '正确,错误', '测试数据2', 3, 2, 1);
INSERT INTO `t_problem` VALUES (118, 1, 2, '测试数据3', '错误', '正确,错误', '测试数据3', 1, 1, 1);
INSERT INTO `t_problem` VALUES (119, 1, 2, '测试数据4', '错误', '正确,错误', '测试数据4', 3, 2, 0);
INSERT INTO `t_problem` VALUES (120, 1, 3, '测试数据5', '错误', '正确,错误', '测试数据5', 1, 1, 1);
INSERT INTO `t_problem` VALUES (121, 1, 3, '测试数据6', '错误', '正确,错误', '测试数据6', 3, 2, 1);
INSERT INTO `t_problem` VALUES (122, 2, 1, '测试数据7', '封神榜', '三国演义,封神榜,水浒传,红楼梦', '测试数据7', 1, 1, 1);
INSERT INTO `t_problem` VALUES (123, 2, 1, '测试数据8', '封神榜', '三国演义,封神榜,水浒传,红楼梦', '测试数据8', 3, 2, 1);
INSERT INTO `t_problem` VALUES (124, 2, 2, '测试数据9', '封神榜', '三国演义,封神榜,水浒传,红楼梦', '测试数据9', 1, 1, 0);
INSERT INTO `t_problem` VALUES (125, 2, 2, '测试数据10', '封神榜', '三国演义,封神榜,水浒传,红楼梦', '测试数据10', 3, 2, 1);
INSERT INTO `t_problem` VALUES (126, 2, 3, '测试数据11', '封神榜', '三国演义,封神榜,水浒传,红楼梦', '测试数据11', 1, 1, 0);
INSERT INTO `t_problem` VALUES (127, 2, 3, '测试数据12', '封神榜', '三国演义,封神榜,水浒传,红楼梦', '测试数据12', 3, 2, 0);
INSERT INTO `t_problem` VALUES (128, 3, 1, '测试数据13', '中庸', '我国古代儒家并称四书的经典是《论语》《孟子》《大学》《()》', '测试数据13', 1, 1, 1);
INSERT INTO `t_problem` VALUES (129, 3, 1, '测试数据14', '中庸', '我国古代儒家并称四书的经典是《论语》《孟子》《大学》《()》', '测试数据14', 3, 2, 1);
INSERT INTO `t_problem` VALUES (130, 3, 2, '测试数据15', '中庸', '我国古代儒家并称四书的经典是《论语》《孟子》《大学》《()》', '测试数据15', 1, 1, 0);
INSERT INTO `t_problem` VALUES (131, 3, 2, '测试数据16', '中庸', '我国古代儒家并称四书的经典是《论语》《孟子》《大学》《()》', '测试数据16', 3, 2, 0);
INSERT INTO `t_problem` VALUES (132, 3, 3, '测试数据17', '中庸', '我国古代儒家并称四书的经典是《论语》《孟子》《大学》《()》', '测试数据17', 1, 1, 1);
INSERT INTO `t_problem` VALUES (133, 3, 3, '测试数据18', '中庸', '我国古代儒家并称四书的经典是《论语》《孟子》《大学》《()》', '测试数据18', 3, 2, 1);

-- ----------------------------
-- Table structure for t_problem_difficulty
-- ----------------------------
DROP TABLE IF EXISTS `t_problem_difficulty`;
CREATE TABLE `t_problem_difficulty`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `difficulty` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_problem_difficulty
-- ----------------------------
INSERT INTO `t_problem_difficulty` VALUES (1, 'A');
INSERT INTO `t_problem_difficulty` VALUES (2, 'B');
INSERT INTO `t_problem_difficulty` VALUES (3, 'C');

-- ----------------------------
-- Table structure for t_problem_subject
-- ----------------------------
DROP TABLE IF EXISTS `t_problem_subject`;
CREATE TABLE `t_problem_subject`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `subject` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '科目',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_problem_subject
-- ----------------------------
INSERT INTO `t_problem_subject` VALUES (1, '语文');
INSERT INTO `t_problem_subject` VALUES (2, '数学');
INSERT INTO `t_problem_subject` VALUES (3, '音乐');

-- ----------------------------
-- Table structure for t_problem_type
-- ----------------------------
DROP TABLE IF EXISTS `t_problem_type`;
CREATE TABLE `t_problem_type`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '整形',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `choice_answer` json NULL COMMENT '[{\"proId\":1,wtirte:\"正确\",\"mark\":3},...]顺序是题目id，考生答案',
  `blank_answer` json NULL COMMENT '[{\"proId\":1,wtirte:\"正确\",\"mark\":3},...]顺序是题目id，考生答案',
  `judge_answer` json NULL COMMENT '[{\"proId\":1,wtirte:\"正确\",\"mark\":3},...]顺序是题目id，考生答案',
  `total_mark` int(0) NULL DEFAULT NULL COMMENT '总分',
  PRIMARY KEY (`result_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 115 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_result
-- ----------------------------
INSERT INTO `t_result` VALUES (1, 1, 2, '2020-04-10 00:51:50', '[{\"proId\": 1, \"write\": \"水浒传\"}, {\"proId\": 6, \"write\": \"6\"}]', '[{\"proId\": 3, \"write\": \"44\"}, {\"proId\": 5, \"write\": \"11\"}]', '[{\"proId\": 2, \"write\": \"正确\"}, {\"proId\": 4, \"write\": \"错误\"}]', 5);
INSERT INTO `t_result` VALUES (17, 79, 5, '2020-04-11 21:35:27', '[{\"proId\": 6, \"write\": \"6\"}, {\"proId\": 33, \"write\": \"红楼梦\"}, {\"proId\": 32, \"write\": \"水浒传\"}]', '[{\"proId\": 5, \"write\": \"22\"}, {\"proId\": 37, \"write\": \"33\"}, {\"proId\": 39, \"write\": \"22\"}]', '[{\"proId\": 25, \"write\": \"正确\"}, {\"proId\": 28, \"write\": \"错误\"}]', 16);
INSERT INTO `t_result` VALUES (18, 1, 4, '2020-04-17 00:27:34', '[{\"proId\": 1, \"write\": \"封神榜\"}, {\"proId\": 6, \"write\": \"9\"}]', '[{\"proId\": 3, \"write\": \"22\"}, {\"proId\": 5, \"write\": \"2222\"}]', '[{\"proId\": 2, \"write\": \"错误\"}, {\"proId\": 4, \"write\": \"正确\"}]', 10);
INSERT INTO `t_result` VALUES (19, 86, 4, '2020-04-17 00:31:20', '[{\"proId\": 31, \"write\": \"封神榜\"}, {\"proId\": 33, \"write\": \"水浒传\"}, {\"proId\": 6, \"write\": \"9\"}]', '[{\"proId\": 3, \"write\": \"3\"}, {\"proId\": 5, \"write\": \"11\"}]', '[{\"proId\": 2, \"write\": \"正确\"}, {\"proId\": 4, \"write\": \"错误\"}]', 10);
INSERT INTO `t_result` VALUES (28, 86, 4, '2020-05-01 20:27:25', '[{\"proId\": 31, \"write\": \"红楼梦\"}, {\"proId\": 33, \"write\": \"水浒传\"}, {\"proId\": 6, \"write\": \"2\"}]', '[{\"proId\": 3, \"write\": \"33\"}, {\"proId\": 5, \"write\": \"11\"}]', '[{\"proId\": 2, \"write\": \"错误\"}, {\"proId\": 4, \"write\": \"正确\"}]', 5);

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
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, '123', '123', 'teacher', '老师1', 'exam,problem,result', '1587866861977.jpg', 'xxxx@163.com');
INSERT INTO `t_user` VALUES (2, '111', '111', 'student', '某某某111', 'takeExam,examDetail,result', '1586615381228.jpg', 'xxxxx@qq.com');
INSERT INTO `t_user` VALUES (3, '321', '321', 'teacher', '老师2', 'exam,problem,result', '1586007942350.jpg', 'xxx@qq.com');
INSERT INTO `t_user` VALUES (4, '222', '222', 'student', '学生某某某222', 'takeExam,examDetail,result', '1586498168717.jpg', 'xxxxx@qq.com');
INSERT INTO `t_user` VALUES (5, '333', '333', 'student', '学生某3333', 'takeExam,examDetail,result', '1586615327774.jpg', 'xxxxx@qq.com');

SET FOREIGN_KEY_CHECKS = 1;
