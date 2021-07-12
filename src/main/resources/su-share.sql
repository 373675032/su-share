/*
Navicat MySQL Data Transfer

Source Server         : 本地-127.0.0.1
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : su-share

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2020-11-29 21:42:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int(11) DEFAULT NULL COMMENT '评论者ID',
  `material_id` int(11) DEFAULT NULL COMMENT '素材ID',
  `content` varchar(500) DEFAULT NULL COMMENT '评论内容',
  `stars` int(11) DEFAULT NULL COMMENT '评星数【0-5】',
  `reply_id` int(11) DEFAULT '0' COMMENT '被回复的评论ID，一级评论id为0',
  `comment_time` datetime DEFAULT NULL COMMENT '评论时间',
  `is_first` int(11) DEFAULT '0' COMMENT '是否置顶【0：否】【1：置顶】',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '2', '13', '多多支持！', '5', '0', '2020-11-29 18:49:00', '0');
INSERT INTO `comment` VALUES ('2', '2', '13', '非常不错的素材！', null, '1', '2020-11-29 18:49:16', '0');
INSERT INTO `comment` VALUES ('3', '2', '12', '一般', '3', '0', '2020-11-29 18:49:43', '0');
INSERT INTO `comment` VALUES ('4', '2', '18', 'Nice', '5', '0', '2020-11-29 19:01:13', '0');
INSERT INTO `comment` VALUES ('5', '2', '15', '。。。', '1', '0', '2020-11-29 19:01:23', '0');
INSERT INTO `comment` VALUES ('6', '2', '16', '。。。', '3', '0', '2020-11-29 19:01:35', '0');
INSERT INTO `comment` VALUES ('7', '2', '14', '@！！', '5', '0', '2020-11-29 19:01:46', '0');
INSERT INTO `comment` VALUES ('8', '2', '20', '！！！', '4', '0', '2020-11-29 19:01:58', '0');
INSERT INTO `comment` VALUES ('9', '2', '19', '。。。', '1', '0', '2020-11-29 19:02:07', '0');
INSERT INTO `comment` VALUES ('10', '2', '17', '@@@@', '5', '0', '2020-11-29 19:02:18', '0');
INSERT INTO `comment` VALUES ('11', '1', '22', '111', '5', '0', '2020-11-29 19:10:44', '0');
INSERT INTO `comment` VALUES ('12', '3', '22', '2222', null, '11', '2020-11-29 19:11:08', '0');
INSERT INTO `comment` VALUES ('13', '3', '22', 'Nice', '5', '0', '2020-11-29 19:11:17', '0');
INSERT INTO `comment` VALUES ('14', '1', '23', '666', '5', '0', '2020-11-29 19:13:42', '0');
INSERT INTO `comment` VALUES ('15', '1', '23', '66666666', null, '14', '2020-11-29 19:13:47', '0');
INSERT INTO `comment` VALUES ('16', '1', '21', '3', '4', '0', '2020-11-29 19:18:51', '0');
INSERT INTO `comment` VALUES ('17', '2', '24', '还行', '5', '0', '2020-11-29 19:38:22', '0');
INSERT INTO `comment` VALUES ('18', '2', '23', '不粗哟', null, '14', '2020-11-29 19:38:37', '0');
INSERT INTO `comment` VALUES ('19', '2', '23', '可以！', '4', '0', '2020-11-29 19:38:50', '0');
INSERT INTO `comment` VALUES ('20', '2', '22', '哈哈', null, '11', '2020-11-29 19:39:04', '0');
INSERT INTO `comment` VALUES ('21', '2', '22', '。', null, '13', '2020-11-29 19:39:09', '0');
INSERT INTO `comment` VALUES ('22', '2', '22', '不好', '1', '0', '2020-11-29 19:39:19', '0');
INSERT INTO `comment` VALUES ('23', '2', '21', '66', '4', '0', '2020-11-29 19:39:40', '0');
INSERT INTO `comment` VALUES ('24', '2', '20', '1111', '1', '0', '2020-11-29 19:39:53', '0');

-- ----------------------------
-- Table structure for `favorite`
-- ----------------------------
DROP TABLE IF EXISTS `favorite`;
CREATE TABLE `favorite` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `material_id` int(11) DEFAULT NULL COMMENT '素材ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of favorite
-- ----------------------------

-- ----------------------------
-- Table structure for `kind`
-- ----------------------------
DROP TABLE IF EXISTS `kind`;
CREATE TABLE `kind` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `count` int(11) DEFAULT '0' COMMENT '素材数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kind
-- ----------------------------
INSERT INTO `kind` VALUES ('1', '图片', '3');
INSERT INTO `kind` VALUES ('3', '音频', '2');
INSERT INTO `kind` VALUES ('4', '字体', '3');
INSERT INTO `kind` VALUES ('5', 'WorldPress主题', '1');
INSERT INTO `kind` VALUES ('6', '前端页面', '3');
INSERT INTO `kind` VALUES ('13', 'PPT模板', '4');

-- ----------------------------
-- Table structure for `link`
-- ----------------------------
DROP TABLE IF EXISTS `link`;
CREATE TABLE `link` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  `target_url` varchar(255) DEFAULT NULL COMMENT '链接地址',
  `type` int(11) DEFAULT NULL COMMENT '类型：顶部菜单（1），友情链接（2）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of link
-- ----------------------------
INSERT INTO `link` VALUES ('5', '百度', 'http://www.baidu.com', '2');
INSERT INTO `link` VALUES ('6', '字体素材', 'http://127.0.0.1:8082/su-share/materials?kId=4', '1');
INSERT INTO `link` VALUES ('7', 'WordPress精选主题', 'http://127.0.0.1:8082/su-share/materials?kId=5', '1');

-- ----------------------------
-- Table structure for `material`
-- ----------------------------
DROP TABLE IF EXISTS `material`;
CREATE TABLE `material` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int(11) DEFAULT NULL COMMENT '上传者ID',
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  `kind_id` int(11) DEFAULT NULL COMMENT '分类ID【默认为0，代表未分类】',
  `info` longtext COMMENT '描述',
  `img` varchar(255) DEFAULT NULL COMMENT '封面图片地址',
  `imgs` longtext COMMENT '素材的阅览图',
  `read_count` int(11) DEFAULT '0' COMMENT '阅读量',
  `download_count` int(11) DEFAULT '0' COMMENT '下载量',
  `publish_time` datetime DEFAULT NULL COMMENT '发布时间',
  `file_path` varchar(255) DEFAULT NULL COMMENT '文件路径',
  `extension` varchar(20) DEFAULT NULL COMMENT '源文件扩展名',
  `size` varchar(10) DEFAULT '0' COMMENT '文件大小，MB为单位，保留一位小数',
  `status` int(11) DEFAULT '1' COMMENT '素材状态【0：回收站】【1：已发布】',
  `is_checked` int(11) DEFAULT '0' COMMENT '审核状态【0：等待审核】【1：审核通过】【2：审核未通过】',
  `checked_info` varchar(255) DEFAULT '-' COMMENT '审核信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of material
-- ----------------------------
INSERT INTO `material` VALUES ('12', '2', '微软雅黑', '4', '<p>\r\n微软雅黑是美国微软公司委托中国北大方正电子有限公司设计的一款全面支持ClearType技术的字体。Monotype公司负责字体Hinting工作。它属于OpenType类型，文件名是MSYH.TTF，字体设计上属于无衬线字体和黑体。这种字体每个字的造价成本在100美元左右。该字体家族还包括“微软雅黑Bold”（粗体），文件名为MSYHBD.TTF。这个粗体不是单纯的将普通字符加粗，而是在具体笔画上分别进行处理，因此是独立的一个字体。微软雅黑随简体中文版Windows Vista一起发布，是Windows Vista默认字体。另外，Microsoft Office 2007简体中文版也附带这个字体。当使用于不能显示中文字型名称的系统时，会显示为Microsoft YaHei。\r\n</p>\r\n<p>\r\n微软雅黑的版权分为两个部分（详见底部 知识产权 部分）：个人使用为目的的Windows系统的内嵌使用、屏幕输出和打印；以商业发布为目的的微软雅黑版权，由北大方正保留。\r\n</p>', 'https://gitee.com/cn_moti/su-share/raw/master/3/e44a511f6fda4b488fecfe4a3a06d1ed.jpg', '[\"https://gitee.com/cn_moti/su-share/raw/master/3/ef0929522962495388958634e741ea50.png\",\"https://gitee.com/cn_moti/su-share/raw/master/3/fc6ccc1f73d84a39855efd9b8abb8228.jpg\"]', '27', '11', '2020-11-24 20:01:14', 'https://su-share.oss-cn-beijing.aliyuncs.com/3/7d9561a9681141278e395952691e56e7.zip', '.zip', '208B', '1', '1', '-');
INSERT INTO `material` VALUES ('13', '2', '秋天可爱稻草人PPT背景图片', '13', '秋天可爱稻草人PPT背景图片。一套秋天麦田里的稻草人背景图片合集，共3张，大图JPG格式，非常可爱。', 'https://gitee.com/cn_moti/su-share/raw/master/2/a5c92e5311de4628aae39bdbf542c653.jpg', '[\"https://gitee.com/cn_moti/su-share/raw/master/2/795ce61ad3354908b9cebed302d570f8.jpg\",\"https://gitee.com/cn_moti/su-share/raw/master/2/6594dd4aa7c34b6693d8c3376750096b.jpg\"]', '3', '1', '2020-11-29 18:48:10', 'https://su-share.oss-cn-beijing.aliyuncs.com/2/7efd7b1f4209473ba24e042090d7b903.zip', '.zip', '188B', '1', '1', '-');
INSERT INTO `material` VALUES ('14', '2', '养眼绿色植物PPT背景图片', '13', '养眼绿色植物PPT背景图片。JPG格式，1920x1080分辨率，高清大图。关键词：绿色叶子 植物 绿叶。\r\n\r\n', 'https://gitee.com/cn_moti/su-share/raw/master/2/37f9a77d35294fd9adbd53120e89da6a.jpg', '[\"https://gitee.com/cn_moti/su-share/raw/master/2/e299e5d566e24e40b924ba4652539951.jpg\",\"https://gitee.com/cn_moti/su-share/raw/master/2/93940766fbac4cfaa4c1f76bda0332ea.jpg\",\"https://gitee.com/cn_moti/su-share/raw/master/2/ffb426da861145a0adc30a0b503c54ec.jpg\"]', '2', '0', '2020-11-29 18:50:54', 'https://su-share.oss-cn-beijing.aliyuncs.com/2/3b51b501824f4550a9cfab736f30c35d.zip', '.zip', '188B', '1', '1', '-');
INSERT INTO `material` VALUES ('15', '2', '汉仪粗圆简字体', '4', '汉仪粗圆简字体下载。上一个：汉仪舒圆黑简 ，下一个：没有了 。安装方法：打开压缩包，双击字体文件，点击“安装”按钮即可。仅供学习使用，商用请联系作者。', 'https://gitee.com/cn_moti/su-share/raw/master/2/d37239ed61dc4815a5ea2720d8081b83.jpg', '[]', '2', '0', '2020-11-29 18:52:09', 'https://su-share.oss-cn-beijing.aliyuncs.com/2/855aa9e9edfc45a394dc88c883cff382.zip', '.zip', '188B', '1', '1', '-');
INSERT INTO `material` VALUES ('16', '2', '字魂7号-温暖童稚体字体', '4', '字魂7号-温暖童稚体字体下载。上一个：熊孩子体 ，下一个：没有了 。安装方法：打开压缩包，双击字体文件，点击“安装”按钮即可。仅供学习使用，商用请联系作者。', 'https://gitee.com/cn_moti/su-share/raw/master/2/75df4f2e44494d6d916050d4b832d989.jpg', '[]', '2', '0', '2020-11-29 18:53:05', 'https://su-share.oss-cn-beijing.aliyuncs.com/2/1230de4175ba4ec28964f1a254359299.zip', '.zip', '188B', '1', '1', '-');
INSERT INTO `material` VALUES ('17', '2', '城市俯视鸟瞰背景图片', '1', '城市俯视鸟瞰PPT背景图片。一组城市俯瞰图片，jpg格式高清大图，大气磅礴，适合商务类PPT背景图片使用。', 'https://gitee.com/cn_moti/su-share/raw/master/2/72d8adf428c04734bf3ab4716f28fa02.jpg', '[\"https://gitee.com/cn_moti/su-share/raw/master/2/6c62b29b0a9c4c6f9ea203efe275ab92.jpg\",\"https://gitee.com/cn_moti/su-share/raw/master/2/e8ce1e01fe5f4366aad7a585d4f18f4f.jpg\",\"https://gitee.com/cn_moti/su-share/raw/master/2/7a89ddf9fc484b159ce7bd0f5624cdc2.jpg\"]', '2', '0', '2020-11-29 18:56:08', 'https://su-share.oss-cn-beijing.aliyuncs.com/2/8f695c0ef4aa4ff18ccf09a214e4b4a2.zip', '.zip', '188B', '1', '1', '-');
INSERT INTO `material` VALUES ('18', '2', '26张高清沙漠背景图片', '1', '高清沙漠PPT背景图片，共26张，JPG格式，高清大图，漂亮的沙漠风景幻灯片背景图片。 ', 'https://gitee.com/cn_moti/su-share/raw/master/2/a002baff05494e408799962e095b5717.jpg', '[\"https://gitee.com/cn_moti/su-share/raw/master/2/3470504d63984b0aae5dcb78636b705d.jpg\",\"https://gitee.com/cn_moti/su-share/raw/master/2/119c2a45c200454cb72e4fa160ae7c6f.jpg\"]', '2', '0', '2020-11-29 18:57:15', 'https://su-share.oss-cn-beijing.aliyuncs.com/2/bbe08735bbc34b5a9eaf5e9890206ff7.zip', '.zip', '188B', '1', '1', '-');
INSERT INTO `material` VALUES ('19', '2', 'bootstrap响应式鞋子商城网页模板', '6', 'jQuery bootstrap响应式鞋子商城网页模板，多种风格，页面功能齐全', 'https://gitee.com/cn_moti/su-share/raw/master/2/08494ccfe40d45c6af92701fa9f49a39.jpg', '[]', '2', '0', '2020-11-29 18:58:43', 'https://su-share.oss-cn-beijing.aliyuncs.com/2/16a10468d5974cbfbaeace4f5b34ccea.zip', '.zip', '188B', '1', '1', '-');
INSERT INTO `material` VALUES ('20', '2', '抽奖插件 仿京东大转盘抽京豆', '6', '一个基于原生 javript vue2 vue3 的大转盘抽奖插件', 'https://gitee.com/cn_moti/su-share/raw/master/2/c41cc8b0c988419599ef264c9d2c9c8d.jpg', '[]', '5', '0', '2020-11-29 18:59:37', 'https://su-share.oss-cn-beijing.aliyuncs.com/2/179eb1733a9c4b6b911968848821e080.zip', '.zip', '188B', '1', '1', '-');
INSERT INTO `material` VALUES ('21', '3', '毛不易-想你想你', '3', '超好听！', 'https://gitee.com/cn_moti/su-share/raw/master/3/0bdfe1cc7b0148e38de35c484198f3f3.jpg', '[]', '4', '0', '2020-11-29 19:09:27', 'https://su-share.oss-cn-beijing.aliyuncs.com/3/9cd4b91244144085bc512c7ac7227842.zip', '.zip', '188B', '1', '1', '-');
INSERT INTO `material` VALUES ('22', '3', '毛不易-像我这样的人', '3', '啦啦啦', 'https://gitee.com/cn_moti/su-share/raw/master/3/4897821f525d4821a8e06af2ff7d6a50.jpg', '[]', '9', '0', '2020-11-29 19:10:23', 'https://su-share.oss-cn-beijing.aliyuncs.com/3/46a92e54298d4fd4bb105d4eeead495d.zip', '.zip', '188B', '1', '1', '-');
INSERT INTO `material` VALUES ('23', '3', '简约版个人博客主题', '5', '舒服！', 'https://gitee.com/cn_moti/su-share/raw/master/3/21dc87caf4ed4b43ba9148281c210865.jpg', '[]', '7', '0', '2020-11-29 19:13:21', 'https://su-share.oss-cn-beijing.aliyuncs.com/3/c4d7139c2bab4245b9b29550141771d9.zip', '.zip', '188B', '1', '1', '-');
INSERT INTO `material` VALUES ('24', '3', '全宽布局绿色果蔬食品', '6', '好看', 'https://gitee.com/cn_moti/su-share/raw/master/3/f107324731b6494bab6ce2ed430f69d9.jpg', '[]', '6', '0', '2020-11-29 19:15:16', 'https://su-share.oss-cn-beijing.aliyuncs.com/3/c2a7cdaf2d9743a9bb0455b69a2f8089.zip', '.zip', '188B', '1', '1', '-');
INSERT INTO `material` VALUES ('25', '2', '时尚画册杂志欧美风PPT模板', '13', '时尚画册杂志欧美风PPT模板。一套时尚欧美风幻灯片模板，多种图文排版页面，适合杂志画册幻灯片设计。', 'https://gitee.com/cn_moti/su-share/raw/master/2/3cab494ab6e649fca5d89b28ce4b3105.jpg', '[\"https://gitee.com/cn_moti/su-share/raw/master/2/9f91c84baa5844a89cf4e23a510223e2.jpg\"]', '0', '0', '2020-11-29 19:36:52', 'https://su-share.oss-cn-beijing.aliyuncs.com/2/2e6624d1dc6c4947887d55aa264a29ef.zip', '.zip', '188B', '1', '1', '-');
INSERT INTO `material` VALUES ('26', '2', '植物叶子昆虫线条PPT小图标', '13', '植物叶子昆虫线条PPT小图标。一组线性线条植物叶子小昆虫小图标，PPT绘制，颜色大小可编辑', 'https://gitee.com/cn_moti/su-share/raw/master/2/196ad9b10e4d4d48adcc606617010976.jpg', '[]', '0', '0', '2020-11-29 19:37:37', 'https://su-share.oss-cn-beijing.aliyuncs.com/2/125b9c69931c4a48b48ab160d5f6ddc8.zip', '.zip', '188B', '1', '0', '-');
INSERT INTO `material` VALUES ('27', '2', '1', '1', '1', 'https://gitee.com/cn_moti/su-share/raw/master/2/d24ddc263bf9420bb5d3574c84338c34.jpg', '[]', '0', '0', '2020-11-29 20:30:57', 'https://su-share.oss-cn-beijing.aliyuncs.com/2/cda012604d0f4c0b951b6c7f6b54c410.zip', '.zip', '188B', '1', '0', '-');

-- ----------------------------
-- Table structure for `message`
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `receive_user_id` int(11) DEFAULT NULL COMMENT '接收消息的用户ID',
  `sent_user_id` int(11) DEFAULT NULL COMMENT '发送消息的用户ID',
  `sent_user_img` varchar(255) DEFAULT NULL COMMENT '发送消息的用户头像地址',
  `content` varchar(500) DEFAULT NULL COMMENT '消息的内容',
  `material_id` int(11) DEFAULT NULL COMMENT '素材ID',
  `is_read` int(11) DEFAULT '0' COMMENT '是否已读：未读（0），已读（1）',
  `message_time` datetime DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('24', '3', '1', 'https://s1.ax1x.com/2020/08/30/dqJ6wd.jpg', '你的素材《毛不易-像我这样的人》已经通过审核！', '22', '1', '2020-11-29 19:10:30');
INSERT INTO `message` VALUES ('25', '3', '1', 'https://s1.ax1x.com/2020/08/30/dqJ6wd.jpg', '你的素材《毛不易-想你想你》已经通过审核！', '21', '1', '2020-11-29 19:10:33');
INSERT INTO `message` VALUES ('26', '3', '1', 'https://s1.ax1x.com/2020/08/30/dqJ6wd.jpg', '评论了你的素材《毛不易-像我这样的人》：111', '22', '1', '2020-11-29 19:10:44');
INSERT INTO `message` VALUES ('27', '3', '1', 'https://s1.ax1x.com/2020/08/30/dqJ6wd.jpg', '你的素材《简约版个人博客主题》已经通过审核！', '23', '1', '2020-11-29 19:13:30');
INSERT INTO `message` VALUES ('28', '3', '1', 'https://s1.ax1x.com/2020/08/30/dqJ6wd.jpg', '评论了你的素材《简约版个人博客主题》：666', '23', '1', '2020-11-29 19:13:42');
INSERT INTO `message` VALUES ('29', '3', '1', 'https://s1.ax1x.com/2020/08/30/dqJ6wd.jpg', '回复了你的评论《简约版个人博客主题》：66666666', '23', '1', '2020-11-29 19:13:47');
INSERT INTO `message` VALUES ('30', '3', '1', 'https://s1.ax1x.com/2020/08/30/dqJ6wd.jpg', '你的素材《全宽布局绿色果蔬食品电商网站模板》已经通过审核！', '24', '1', '2020-11-29 19:15:23');
INSERT INTO `message` VALUES ('31', '3', '1', 'https://s1.ax1x.com/2020/08/30/dqJ6wd.jpg', '评论了你的素材《毛不易-想你想你》：3', '21', '1', '2020-11-29 19:18:51');
INSERT INTO `message` VALUES ('32', '3', '2', 'https://s1.ax1x.com/2020/08/30/dqJhSf.jpg', '评论了你的素材《全宽布局绿色果蔬食品模板》：还行', '24', '1', '2020-11-29 19:38:22');
INSERT INTO `message` VALUES ('33', '3', '2', 'https://s1.ax1x.com/2020/08/30/dqJhSf.jpg', '回复了你的评论《简约版个人博客主题》：不粗哟', '23', '1', '2020-11-29 19:38:37');
INSERT INTO `message` VALUES ('34', '3', '2', 'https://s1.ax1x.com/2020/08/30/dqJhSf.jpg', '评论了你的素材《简约版个人博客主题》：可以！', '23', '1', '2020-11-29 19:38:50');
INSERT INTO `message` VALUES ('35', '3', '2', 'https://s1.ax1x.com/2020/08/30/dqJhSf.jpg', '回复了你的评论《毛不易-像我这样的人》：哈哈', '22', '1', '2020-11-29 19:39:04');
INSERT INTO `message` VALUES ('36', '3', '2', 'https://s1.ax1x.com/2020/08/30/dqJhSf.jpg', '回复了你的评论《毛不易-像我这样的人》：。', '22', '1', '2020-11-29 19:39:10');
INSERT INTO `message` VALUES ('37', '3', '2', 'https://s1.ax1x.com/2020/08/30/dqJhSf.jpg', '评论了你的素材《毛不易-像我这样的人》：不好', '22', '1', '2020-11-29 19:39:19');
INSERT INTO `message` VALUES ('38', '3', '2', 'https://s1.ax1x.com/2020/08/30/dqJhSf.jpg', '评论了你的素材《毛不易-想你想你》：66', '21', '1', '2020-11-29 19:39:40');
INSERT INTO `message` VALUES ('39', '3', '1', 'https://s1.ax1x.com/2020/08/30/dqJ6wd.jpg', '你的素材《毛不易-像我这样的人》已经通过审核！', '22', '1', '2020-11-29 19:58:03');
INSERT INTO `message` VALUES ('40', '3', '1', 'https://s1.ax1x.com/2020/08/30/dqJ6wd.jpg', '你的素材《全宽布局绿色果蔬食品模板》已经通过审核！', '24', '1', '2020-11-29 19:58:06');
INSERT INTO `message` VALUES ('41', '2', '1', 'https://s1.ax1x.com/2020/08/30/dqJ6wd.jpg', '你的素材《时尚画册杂志欧美风PPT模板》已经通过审核！', '25', '0', '2020-11-29 19:58:09');
INSERT INTO `message` VALUES ('42', '3', '1', 'https://s1.ax1x.com/2020/08/30/dqJ6wd.jpg', '你的素材《简约版个人博客主题》已经通过审核！', '23', '1', '2020-11-29 19:58:12');
INSERT INTO `message` VALUES ('43', '3', '1', 'https://s1.ax1x.com/2020/08/30/dqJ6wd.jpg', '你的素材《全宽布局绿色果蔬食品》已经通过审核！', '24', '0', '2020-11-29 19:59:08');
INSERT INTO `message` VALUES ('44', '2', '1', 'https://s1.ax1x.com/2020/08/30/dqJ6wd.jpg', '你的素材《毛不易-像我这样的人》已被管理员删除！', null, '0', '2020-11-29 21:41:38');
INSERT INTO `message` VALUES ('45', '2', '1', 'https://s1.ax1x.com/2020/08/30/dqJ6wd.jpg', '你的素材《111》已被管理员删除！', null, '0', '2020-11-29 21:41:42');
INSERT INTO `message` VALUES ('46', '2', '1', 'https://s1.ax1x.com/2020/08/30/dqJ6wd.jpg', '你的素材《1》已被管理员删除！', null, '0', '2020-11-29 21:41:45');
INSERT INTO `message` VALUES ('47', '2', '1', 'https://s1.ax1x.com/2020/08/30/dqJ6wd.jpg', '你的素材《简约版个人博客主题》已被管理员删除！', null, '0', '2020-11-29 21:41:48');
INSERT INTO `message` VALUES ('48', '2', '1', 'https://s1.ax1x.com/2020/08/30/dqJ6wd.jpg', '你的素材《毛不易-像我这样的人》已被管理员删除！', null, '0', '2020-11-29 21:41:50');
INSERT INTO `message` VALUES ('49', '2', '1', 'https://s1.ax1x.com/2020/08/30/dqJ6wd.jpg', '你的素材《简约版个人博客主题》已被管理员删除！', null, '0', '2020-11-29 21:41:53');

-- ----------------------------
-- Table structure for `notice`
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `content` varchar(500) DEFAULT NULL COMMENT '公告内容',
  `notice_time` datetime DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('1', '本站承诺所有素材都可免费下载！本站致力于打造一个友好免费的素材分享平台。快来注册分享你的宝库吧~\n<br>', '2020-11-29 17:46:11');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `open_id` varchar(255) DEFAULT NULL COMMENT 'QQ用户的唯一标识',
  `name` varchar(20) DEFAULT NULL COMMENT '用户名',
  `sex` int(11) DEFAULT '0' COMMENT '性别【0：女】【1：男】',
  `img` varchar(255) DEFAULT NULL COMMENT '头像地址',
  `info` varchar(255) DEFAULT NULL COMMENT '个人说明',
  `status` int(11) DEFAULT '1' COMMENT '封禁状态【1：正常】【2：不可上传】【3：不可上传和下载】',
  `role` int(11) DEFAULT '0' COMMENT '用户角色【0：普通用户】【1：管理员】',
  `register_time` datetime DEFAULT NULL COMMENT '注册时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '1111', '管理员', '1', 'https://s1.ax1x.com/2020/08/30/dqJ6wd.jpg', '一只小张三', '1', '1', '2020-11-02 20:50:28');
INSERT INTO `user` VALUES ('2', '2222', '杰瑞', '0', 'https://s1.ax1x.com/2020/08/30/dqJhSf.jpg', '一只小耗子', '1', '0', '2020-11-18 15:56:06');
INSERT INTO `user` VALUES ('3', '3333', '露丝', '0', 'https://dss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2135068181,2006355012&fm=111&gp=0.jpg', '哦~', '1', '0', '2020-11-29 19:03:18');
