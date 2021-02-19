/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : localhost:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 31/10/2020 12:07:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog_article
-- ----------------------------
DROP TABLE IF EXISTS `blog_article`;
CREATE TABLE `blog_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(150) DEFAULT NULL COMMENT '标题',
  `doc` text COMMENT 'MD文档',
  `outline` varchar(500) DEFAULT NULL COMMENT '大纲',
  `content` text COMMENT 'HTML内容',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=380 DEFAULT CHARSET=utf8mb4 COMMENT='文章表';

-- ----------------------------
-- Table structure for blog_article_classify
-- ----------------------------
DROP TABLE IF EXISTS `blog_article_classify`;
CREATE TABLE `blog_article_classify` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` int(11) DEFAULT NULL,
  `classify_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=221 DEFAULT CHARSET=utf8mb4 COMMENT='文章与栏目关联相关';

-- ----------------------------
-- Table structure for blog_article_comment
-- ----------------------------
DROP TABLE IF EXISTS `blog_article_comment`;
CREATE TABLE `blog_article_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` int(11) DEFAULT NULL COMMENT '文章ID',
  `parent_id` int(11) DEFAULT NULL COMMENT '上级楼层ID',
  `nicker` varchar(50) DEFAULT NULL COMMENT '昵称',
  `email` varchar(80) DEFAULT NULL COMMENT '电子邮箱',
  `content` text COMMENT '评论内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `deleted` int(11) DEFAULT '0',
  `status` int(11) DEFAULT '0' COMMENT '0=未审核, 1=审核通过,-1=驳回',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='文章评论';


-- ----------------------------
-- Table structure for blog_classify
-- ----------------------------
DROP TABLE IF EXISTS `blog_classify`;
CREATE TABLE `blog_classify` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='栏目/分类';

-- ----------------------------
-- Records of blog_classify
-- ----------------------------
BEGIN;
INSERT INTO `blog_classify` VALUES (1, '后端技术', 1, '2020-10-16 03:28:06', '2020-10-16 03:28:06');
INSERT INTO `blog_classify` VALUES (2, '前端技术', 1, '2020-10-16 03:31:11', '2020-10-16 03:31:11');
INSERT INTO `blog_classify` VALUES (3, '运维相关', 1, '2020-10-29 07:16:46', '2020-10-29 07:16:46');
INSERT INTO `blog_classify` VALUES (4, '随笔', 1, '2020-10-29 07:16:53', '2020-10-29 07:16:53');
COMMIT;

-- ----------------------------
-- Table structure for blog_rule
-- ----------------------------
DROP TABLE IF EXISTS `blog_rule`;
CREATE TABLE `blog_rule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `keyword` text COMMENT '过滤词汇[''xxx'', ''xxx'']',
  `find_range` varchar(10) DEFAULT NULL COMMENT '匹配范围,多选,逗号隔开. 1=昵称,2=邮箱,3=评论内容',
  `rule` int(11) DEFAULT NULL COMMENT '匹配规则, 1=所有词汇出现次数总和, 2=出现率最高词汇数, 3=平均每个词汇出现数, 4=全部匹配且按最低出现率的词汇次数, 5=全部匹配且按出现率最高的词汇次数',
  `flow_fun` int(11) DEFAULT NULL COMMENT '流程处理方法: 1=转人工, 2=自动驳回, 3=自动通过',
  `threshold` int(11) DEFAULT NULL COMMENT '阀值',
  `status` int(11) DEFAULT NULL COMMENT '0 = 未启用, 1 = 启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='过滤规则表';

-- ----------------------------
-- Table structure for blog_user
-- ----------------------------
DROP TABLE IF EXISTS `blog_user`;
CREATE TABLE `blog_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) DEFAULT NULL COMMENT '账号/电子邮箱',
  `nicker` varchar(20) DEFAULT NULL COMMENT '用户昵称',
  `password` varchar(32) DEFAULT NULL COMMENT '用户密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of blog_user
-- ----------------------------
BEGIN;
INSERT INTO `blog_user` VALUES (1, 'admin', '郭胜凯', '202cb962ac59075b964b07152d234b70');
COMMIT;

-- ----------------------------
-- Table structure for blog_webset
-- ----------------------------
DROP TABLE IF EXISTS `blog_webset`;
CREATE TABLE `blog_webset` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL COMMENT '网站名称',
  `cache_time` int(11) DEFAULT '0' COMMENT '缓存时间',
  `max_update_size` int(11) DEFAULT NULL COMMENT '最大上传大小',
  `home_title` varchar(100) DEFAULT NULL COMMENT '主页标题',
  `meta_keywords` varchar(255) DEFAULT NULL COMMENT '头关键字信息',
  `meta_description` varchar(266) DEFAULT NULL COMMENT '头描述信息',
  `license` varchar(100) DEFAULT NULL COMMENT '授权信息',
  `upload_file_type` varchar(100) DEFAULT NULL COMMENT '允许上传文件类型',
  `domain` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='网站设置';

-- ----------------------------
-- Records of blog_webset
-- ----------------------------
BEGIN;
INSERT INTO `blog_webset` VALUES (1, '天涯客栈', 0, 0, '首页', 'java,天涯,客栈,blog,开源,博客,技术,后端,前端,解决方案', '这是一个基于Spring boot的开源博客项目. 开源协议GPL3.0 NPL996. GitHub:https://github.com/onlyGuo/blog. 在这里你可以浏览作者发布的一些文章或者通过此代码搭建属于自己的博客系统.', '© 2020 321aiyi.com GPL3.0 license NPL996 license', 'JAVA|JPEG|PNG|GIF|ZIP|GZ|TAR|TXT', 'localhost');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

-- 增加阅读数统计
alter table blog_article
    add display_count bigint default 0 null;

-- 增加网站默认昵称
alter table blog_webset
    add nicker varchar(20) null;
UPDATE blog.blog_webset t SET t.nicker = '芦苇小白' WHERE t.id = 1

-- 增加封面图片
alter table blog_article
    add cover_image varchar(255) null comment '封面图片';

-- 增加基本访问量统计
create table blog_log
(
    id          bigint auto_increment
        primary key,
    create_time datetime null
);