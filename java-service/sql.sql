/*
SQLyog Community v12.09 (32 bit)
MySQL - 5.5.27 : Database - registration
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`registration` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `registration`;

/*Table structure for table `attendance` */

DROP TABLE IF EXISTS `attendance`;

CREATE TABLE `attendance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `downaddress` varchar(255) DEFAULT NULL,
  `eid` int(11) NOT NULL,
  `upaddress` varchar(255) DEFAULT NULL,
  `workdown` varchar(255) DEFAULT NULL,
  `worktime` varchar(255) DEFAULT NULL,
  `workup` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4;

/*Data for the table `attendance` */

insert  into `attendance`(`id`,`downaddress`,`eid`,`upaddress`,`workdown`,`worktime`,`workup`,`status`) values (1,'',2,'广东省深圳市福田区福中三路 市民中心内,深圳市人民政府东南196米','','2020-4-5','20:25','1'),(5,'广东省深圳市福田区福中三路 市民中心内,深圳市人民政府东南196米',2,'广东省深圳市福田区福中三路 市民中心内,深圳市人民政府东南196米','19:04','2020-4-4','19:04','1'),(40,'广东省深圳市龙岗区宝龙大道 宝捷讯工业园西南157米',13,'广东省深圳市龙岗区宝龙大道 宝捷讯工业园西南157米','18:35','2020-4-8','08:55','0'),(51,'广西壮族自治区贺州市八步区向阳路1号 八步区政府附近7米',2,'广西壮族自治区贺州市八步区向阳路1号 八步区政府附近7米','17:49','2020-5-23','17:49','1'),(41,'广西壮族自治区贺州市八步区向阳路1号 八步区政府附近7米',1,'广西壮族自治区贺州市八步区向阳路1号 八步区政府附近7米','22:15','2020-4-26','22:15','0'),(42,'广西壮族自治区贺州市八步区向阳路1号 八步区政府附近7米',2,'广西壮族自治区贺州市八步区向阳路1号 八步区政府附近7米','18:04','2020-4-26','08:04','0'),(43,'广西壮族自治区贺州市八步区向阳路1号 八步区政府附近7米',1,'广西壮族自治区贺州市八步区向阳路1号 八步区政府附近7米','18:04','2020-4-27','08:04','0'),(45,'',2,'广西壮族自治区贺州市八步区向阳路1号 八步区政府附近7米','','2020-4-27','10:45','1'),(46,'',13,'广西壮族自治区贺州市八步区向阳路1号 八步区政府附近7米','','2020-4-27','10:50','2');

/*Table structure for table `bussiness` */

DROP TABLE IF EXISTS `bussiness`;

CREATE TABLE `bussiness` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `how_long` varchar(255) DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `time_end` varchar(255) DEFAULT NULL,
  `time_start` varchar(255) DEFAULT NULL,
  `uid` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4;

/*Data for the table `bussiness` */

insert  into `bussiness`(`id`,`how_long`,`reason`,`time_end`,`time_start`,`uid`,`address`,`name`) values (29,NULL,'驻场','2020-5-30','2020-5-23',2,'南宁',NULL),(28,NULL,'驻场','2020-5-30','2020-5-23',2,'南宁',NULL),(26,NULL,'介绍产品','2020-6-24','2020-5-14',13,'上海',NULL),(25,NULL,'解决技术问题','2020-5-23','2020-5-16',3,'北京',NULL),(24,NULL,'驻场','2020-5-22','2020-5-16',10,'广西贺州',NULL),(23,NULL,'联系客户','2020-5-22','2020-5-16',9,'广西北海',NULL),(22,NULL,'联系客户','2020-5-15','2020-5-8',2,'广西南宁',NULL);

/*Table structure for table `eleave` */

DROP TABLE IF EXISTS `eleave`;

CREATE TABLE `eleave` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `how_long` varchar(255) DEFAULT NULL,
  `leave_type` varchar(255) DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `reply` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `time_end` varchar(255) DEFAULT NULL,
  `time_start` varchar(255) DEFAULT NULL,
  `uid` int(11) NOT NULL,
  `deal` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4;

/*Data for the table `eleave` */

insert  into `eleave`(`id`,`how_long`,`leave_type`,`reason`,`reply`,`status`,`time_end`,`time_start`,`uid`,`deal`,`create_time`,`name`) values (1,NULL,'年假','年假','a','1','2020-3-29','2020-3-30',3,'1','2020-4-5',NULL),(34,NULL,'','',NULL,'0','2020-5-23','2020-5-23',2,NULL,'2020年5月23日',NULL),(33,NULL,'病假','生病了',NULL,'0','2020-5-22','2020-5-19',2,NULL,'2020年5月23日',NULL),(30,NULL,'病假','去医院检查身体','','2','2020-4-18','2020-4-15',13,'2',NULL,NULL);

/*Table structure for table `employee` */

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `jointime` varchar(255) DEFAULT NULL,
  `num` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `age` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4;

/*Data for the table `employee` */

insert  into `employee`(`id`,`address`,`department`,`email`,`gender`,`jointime`,`num`,`password`,`phone`,`position`,`role`,`username`,`age`) values (1,'西藏','董事会','787913269@qq.com',1,'2019年10月8日','H001','123456','15977602749','老板',1,'王富贵','22'),(2,'上海','开发部门','787913269@qq.com',1,'2019年10月8日','H123','456789','15834602448','后端',0,'张富贵','19'),(13,'湖南','人事部','1',1,'2019年10月8日','3','3','15977602749','前端',0,'梁朝伟','20'),(3,'广东','人事部','1',1,'2019年10月8日','4','1','15977602749','前端',0,'谢霆锋','21'),(10,'北京','开发部','787913269@qq.com',0,'2019年10月8日','5','123456','15977602749','后端',1,'李志','22'),(9,'广西','开发部','787913269@qq.com',0,'2019年10月8日','6','123456','15977602749','后端',1,'吴彦祖','23'),(30,'广西','销售部','1555786474@qq.com',0,'2019年10月8日','202004081117','123456','18677602749','销售人员',0,'何富贵','32');

/*Table structure for table `notice` */

DROP TABLE IF EXISTS `notice`;

CREATE TABLE `notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bid` varchar(255) DEFAULT NULL,
  `msg` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4;

/*Data for the table `notice` */

insert  into `notice`(`id`,`bid`,`msg`,`time`,`title`,`name`) values (36,'1','何静源于5月2日离职','2020-5-2','何静源离职通知',NULL),(35,'1','放假时间安排 5月1日(星期五)至5日(星期二)放假调休,共5天','2020-5-2','关于51放假通知',NULL);

/*Table structure for table `pcontact` */

DROP TABLE IF EXISTS `pcontact`;

CREATE TABLE `pcontact` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `age` varchar(255) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `ps` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `wechat` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

/*Data for the table `pcontact` */

insert  into `pcontact`(`id`,`age`,`company`,`gender`,`name`,`phone`,`ps`,`address`,`email`,`wechat`,`nickname`,`position`) values (5,'','','男','张钧杰','15977602749','','','','','张钧杰',''),(6,'','','男','张英铭','18677607234','','','','','小张','销售经理');

/*Table structure for table `sign` */

DROP TABLE IF EXISTS `sign`;

CREATE TABLE `sign` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(255) DEFAULT NULL,
  `time_start` varchar(255) DEFAULT NULL,
  `uid` int(11) NOT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `createtime` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;

/*Data for the table `sign` */

insert  into `sign`(`id`,`status`,`time_start`,`uid`,`reason`,`createtime`,`name`) values (14,'1','2020-5-23',2,'网络异常','2020-5-23',NULL),(10,'1','2020-4-4',13,'网络原因','2020-4-4',NULL),(11,'1','2020-4-27',2,'下班忘记打卡','2020-4-27',NULL),(12,'2','2020-4-27',13,'网络错误','2020-4-27',NULL);

/*Table structure for table `tb_bq` */

DROP TABLE IF EXISTS `tb_bq`;

CREATE TABLE `tb_bq` (
  `id` varchar(55) NOT NULL,
  `time` datetime DEFAULT NULL,
  `msg` varchar(255) DEFAULT NULL,
  `uid` varchar(255) DEFAULT NULL,
  `uname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_bq` */

insert  into `tb_bq`(`id`,`time`,`msg`,`uid`,`uname`) values ('bc7af4c971f8474b9ebb6ba54f873d20','2020-01-29 00:00:00','便签测试1','c82e7e84c6a2414290f8f055f09f8792','小美'),('bf570f171989476c9f0ae4230825b3b7','2020-01-29 00:00:00','便签测试2','c82e7e84c6a2414290f8f055f09f8792','小美');

/*Table structure for table `tb_dic` */

DROP TABLE IF EXISTS `tb_dic`;

CREATE TABLE `tb_dic` (
  `id` varchar(55) NOT NULL COMMENT '主键id',
  `name` varchar(66) DEFAULT '' COMMENT '字典名称',
  `type` varchar(28) DEFAULT '' COMMENT '字典类型',
  `time` date DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_dic` */

insert  into `tb_dic`(`id`,`name`,`type`,`time`) values ('1','支付宝','','2019-08-24'),('2','银行卡','','2019-08-24'),('3','微信','','2019-08-24'),('4','QQ钱包','','2019-08-24'),('a18d3a7b3df344c0917e8a668e865498','现金','新闻','2019-08-24');

/*Table structure for table `tb_gdzc` */

DROP TABLE IF EXISTS `tb_gdzc`;

CREATE TABLE `tb_gdzc` (
  `id` varchar(55) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `je` varchar(255) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `uid` varchar(255) DEFAULT NULL,
  `uname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_gdzc` */

insert  into `tb_gdzc`(`id`,`name`,`je`,`time`,`uid`,`uname`) values ('1d82d16e1f2f40afacdceee4e553eff4','宝龙商铺55平米','800000','2020-01-29 00:00:00','c82e7e84c6a2414290f8f055f09f8792','小美'),('a2ad337a384c4845809563c81fd2f250','荣威小汽车','112355','2020-01-29 00:00:00','c82e7e84c6a2414290f8f055f09f8792','小美'),('ae6b2082689b40cd9734b0b5b69222a2','电视机-惠普','6025','2020-01-29 00:00:00','c82e7e84c6a2414290f8f055f09f8792','小美');

/*Table structure for table `tb_manager` */

DROP TABLE IF EXISTS `tb_manager`;

CREATE TABLE `tb_manager` (
  `id` varchar(255) NOT NULL COMMENT '主键id',
  `username` varchar(55) NOT NULL DEFAULT '' COMMENT '账号',
  `nickname` varchar(55) NOT NULL COMMENT '姓名',
  `pass` varchar(55) NOT NULL COMMENT '密码',
  `tel` varchar(55) NOT NULL COMMENT '电话',
  `time` date DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_manager` */

insert  into `tb_manager`(`id`,`username`,`nickname`,`pass`,`tel`,`time`) values ('1','admin','管理员','1234','152312312312','2019-08-02');

/*Table structure for table `tb_sz` */

DROP TABLE IF EXISTS `tb_sz`;

CREATE TABLE `tb_sz` (
  `id` varchar(255) NOT NULL,
  `uid` varchar(255) DEFAULT NULL,
  `je` decimal(10,0) DEFAULT NULL COMMENT '金额',
  `type` varchar(255) DEFAULT '其他' COMMENT '收入类型：工资、奖金、红包、兼职、其他；支出类型：交通、购物、吃饭、话费、医疗、娱乐、其他',
  `sz` varchar(255) DEFAULT '收入' COMMENT '收入或者支出',
  `bz` varchar(255) DEFAULT NULL COMMENT '''备注''',
  `time` datetime DEFAULT NULL,
  `date` date DEFAULT NULL COMMENT '日期',
  `count` varchar(255) DEFAULT NULL COMMENT '账号,从dic里面下拉获取',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_sz` */

insert  into `tb_sz`(`id`,`uid`,`je`,`type`,`sz`,`bz`,`time`,`date`,`count`) values ('00c1be12f0694b54b2a741a108cc5b2e','d4f69570b2a14734b0ad692b97af7f81','500','吃饭','支出','吃饭费用','2020-01-16 12:06:16','2020-01-15','新来客店'),('1f4a8c1466b942b4a106e0e8e6a211b5','993494cc98124cae9dcc054a2cbe7fc6','500','红包','收入','红包','2020-01-29 00:00:00','2020-01-29','支付宝'),('3e6d1b0ce9c941c8aef2772226e33a14','c82e7e84c6a2414290f8f055f09f8792','500','红包','收入','红包','2020-01-29 10:35:50','2020-01-29',NULL),('4a1c1a71ecdc4e649918778cb46e97de','c82e7e84c6a2414290f8f055f09f8792','1500','红包','收入','红包','2020-01-29 10:35:38','2020-01-29',NULL),('5636a45c9cd74ba3bd5524d9f24be858','c82e7e84c6a2414290f8f055f09f8792','100','医疗','支出','感冒看病','2020-01-29 10:52:48','2020-01-29',NULL),('5fa91257babf43b7aebfbdad2c5bda0c','d4f69570b2a14734b0ad692b97af7f81','200','医疗','支出','感冒输液','2020-01-16 12:34:12','2020-01-14','济南东医院'),('b549f605de7f4ac099104ccb62217b1f','c82e7e84c6a2414290f8f055f09f8792','20','吃饭','支出','午饭','2020-01-16 12:06:43','2020-01-16','支付宝'),('bfcbc04c4ddb405dacb190f939353cd1','c82e7e84c6a2414290f8f055f09f8792','150','购物','支出','买菜和肉','2020-01-29 00:00:00','2020-01-29',NULL),('cb7f39ecebe741baa176ec73fdfd3191','c82e7e84c6a2414290f8f055f09f8792','500','吃饭','支出','请客吃饭','2020-01-29 10:26:43','2020-01-29',NULL),('effda857c6c74173b916e5a0af557373','d4f69570b2a14734b0ad692b97af7f81','50','兼职','收入','发传单','2020-01-16 11:57:26','2020-01-16','李总'),('f1d128fd1cfa4ac4a4610c5f332dd26d','70625accedeb4344b4bc832d8f679078','800','红包','收入','孩子满月酒份子钱','2020-01-16 00:00:00','2020-01-16','银行卡'),('f8fda78779a84e319e08b3c899b1711f','70625accedeb4344b4bc832d8f679078','456','工资','收入','rtertrtertrtret','2020-01-16 00:00:00','2020-01-15','银行卡');

/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `id` varchar(55) NOT NULL COMMENT '主键id',
  `username` varchar(55) DEFAULT NULL COMMENT '账号',
  `nickname` varchar(55) DEFAULT NULL COMMENT '姓名',
  `pass` varchar(55) DEFAULT NULL COMMENT '密码',
  `head` varchar(55) DEFAULT NULL COMMENT '头像',
  `tel` varchar(255) DEFAULT NULL COMMENT '电话',
  `xb` varchar(255) DEFAULT NULL COMMENT '性别',
  `time` date DEFAULT NULL COMMENT '添加时间',
  `mid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_user` */

insert  into `tb_user`(`id`,`username`,`nickname`,`pass`,`head`,`tel`,`xb`,`time`,`mid`) values ('70625accedeb4344b4bc832d8f679078','111','小乐','111','timg1.jpg','111','男','2019-09-26','1'),('993494cc98124cae9dcc054a2cbe7fc6','shengteng','沈腾','1234','top.jpg','1589559852','男','2020-01-29','1'),('c82e7e84c6a2414290f8f055f09f8792','123','小美','123','2.jpg','15274712323','女','2019-09-26','1'),('d4f69570b2a14734b0ad692b97af7f81','222','小天','222','31.jpeg','222','男','2019-09-26','1');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `stu_no` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

/*Data for the table `user` */

insert  into `user`(`id`,`name`,`password`,`stu_no`) values (1,'张昏聩','1610818075',787913269),(2,'张昏聩','1610818075',787913269),(3,'张昏聩','1610818075',787913269),(4,'张昏聩','1610818075',787913269),(5,'张昏聩','1610818075',787913269),(6,'张昏聩','1610818075',787913269),(7,'张','180',999),(8,'黄培强','123',123165);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
