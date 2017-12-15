/*
 Navicat Premium Data Transfer

 Source Server         : ACat_MySQL
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost
 Source Database       : webshop

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : utf-8

 Date: 12/11/2017 19:19:01 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `Admin`
-- ----------------------------
DROP TABLE IF EXISTS `Admin`;
CREATE TABLE `Admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `Admin`
-- ----------------------------
BEGIN;
INSERT INTO `Admin` VALUES ('1', 'admin', 'admin');
COMMIT;

-- ----------------------------
--  Table structure for `Book`
-- ----------------------------
DROP TABLE IF EXISTS `Book`;
CREATE TABLE `Book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `author` varchar(255) DEFAULT NULL,
  `imageUrl` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `publishData` varchar(255) DEFAULT NULL,
  `publisher` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `Book`
-- ----------------------------
BEGIN;
INSERT INTO `Book` VALUES ('5', '不详', '/foreground/images/product/book_5.jpg', '拜厄钢琴基本教程', '95', '拜厄钢琴基本教程', '人民音乐出版社'), ('6', '不详', '/foreground/images/product/book_6.jpg', '中华上下五千年', '120', '中华上下五千年', '重庆人民出版社'), ('7', '不详', '/foreground/images/product/book_7.jpg', '图说世界历史', '123', '图说世界历史', '不详'), ('8', 'Bruce Eckel', '/foreground/images/product/book_8.jpg', 'Java编程思想', '79', 'Java编程思想', '机械工业出版社'), ('11', '周铭孙', '/foreground/images/product/book_11.jpg', '全国钢琴演奏考级作品集', '80', '全国钢琴演奏考级作品集', '大众文艺出版社'), ('12', 'Rob Harrop; Jan Machacek', '/foreground/images/product/book_12.jpg', 'Spring专业开发指南', '120', 'Spring专业开发指南', '电子工业出版社'), ('13', '列夫`托尔斯泰', '/foreground/images/product/book_13.jpg', '安娜`卡列尼娜', '99', '安娜`卡列尼娜', '译林出版社'), ('14', '东野圭吾', '/foreground/images/product/book_14.jpg', '白夜行', '85', '白夜行', '不详');
COMMIT;

-- ----------------------------
--  Table structure for `BookType`
-- ----------------------------
DROP TABLE IF EXISTS `BookType`;
CREATE TABLE `BookType` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `BookType`
-- ----------------------------
BEGIN;
INSERT INTO `BookType` VALUES ('1', '计算机类'), ('2', '音乐类'), ('3', '历史类'), ('4', '文学类');
COMMIT;

-- ----------------------------
--  Table structure for `Order_Detail`
-- ----------------------------
DROP TABLE IF EXISTS `Order_Detail`;
CREATE TABLE `Order_Detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `count` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `Orders`
-- ----------------------------
DROP TABLE IF EXISTS `Orders`;
CREATE TABLE `Orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `Users`
-- ----------------------------
DROP TABLE IF EXISTS `Users`;
CREATE TABLE `Users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `postTime` datetime DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `Users`
-- ----------------------------
BEGIN;
INSERT INTO `Users` VALUES ('1', 'address......', '123123123', '123123', null, null, 'lele');
COMMIT;

-- ----------------------------
--  Table structure for `book2type`
-- ----------------------------
DROP TABLE IF EXISTS `book2type`;
CREATE TABLE `book2type` (
  `type_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  PRIMARY KEY (`type_id`,`book_id`),
  UNIQUE KEY `UK_l23olc9aqs7fdf3p87yihy7u` (`book_id`),
  CONSTRAINT `FKlptfi9t7wgxuqp4grbj3sbt70` FOREIGN KEY (`book_id`) REFERENCES `Book` (`id`),
  CONSTRAINT `FKybkpufx8g14cxgtqh5fwle7q` FOREIGN KEY (`type_id`) REFERENCES `BookType` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `book2type`
-- ----------------------------
BEGIN;
INSERT INTO `book2type` VALUES ('2', '5'), ('3', '6'), ('3', '7'), ('1', '8'), ('2', '11'), ('1', '12'), ('4', '13'), ('4', '14');
COMMIT;

-- ----------------------------
--  Table structure for `detail2book`
-- ----------------------------
DROP TABLE IF EXISTS `detail2book`;
CREATE TABLE `detail2book` (
  `book_id` int(11) NOT NULL,
  `detail_id` int(11) NOT NULL,
  KEY `UK_1iowgj2a0uf6yc44wkyfl1k2` (`detail_id`) USING BTREE,
  KEY `FKrhemdn9debmumtvy3r44gtrvj` (`book_id`),
  CONSTRAINT `FKgmaipwr90jh565bgu1wtupmfl` FOREIGN KEY (`detail_id`) REFERENCES `Order_Detail` (`id`),
  CONSTRAINT `FKrhemdn9debmumtvy3r44gtrvj` FOREIGN KEY (`book_id`) REFERENCES `Book` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `order2detail`
-- ----------------------------
DROP TABLE IF EXISTS `order2detail`;
CREATE TABLE `order2detail` (
  `order_id` int(11) NOT NULL,
  `detail_id` int(11) NOT NULL,
  KEY `FK3r0xp8r0s2qtgnnkco222n39p` (`order_id`),
  KEY `FKpu041436uvvl42u99b7abx3oj` (`detail_id`),
  CONSTRAINT `FK3r0xp8r0s2qtgnnkco222n39p` FOREIGN KEY (`order_id`) REFERENCES `Orders` (`id`),
  CONSTRAINT `FKpu041436uvvl42u99b7abx3oj` FOREIGN KEY (`detail_id`) REFERENCES `Order_Detail` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `user2order`
-- ----------------------------
DROP TABLE IF EXISTS `user2order`;
CREATE TABLE `user2order` (
  `user_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  KEY `UK_phlggi96cg53fdsv5iovc2ybj` (`order_id`) USING BTREE,
  KEY `FK68js63p27dd03puvmquv383j1` (`user_id`),
  CONSTRAINT `FK68js63p27dd03puvmquv383j1` FOREIGN KEY (`user_id`) REFERENCES `Users` (`id`),
  CONSTRAINT `FK9k3ov3oh7disla8v6l251bv6p` FOREIGN KEY (`order_id`) REFERENCES `Orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
