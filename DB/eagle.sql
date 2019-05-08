#用户表
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号，主键,用户表',
  `userId` varchar(20) NOT NULL COMMENT '扩展用户id',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `salt` varchar(50) DEFAULT NULL COMMENT '盐',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(50) DEFAULT NULL COMMENT '联系方式',
  `sex` int(1) DEFAULT NULL COMMENT '性别：1男2女',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `status` int(1) NOT NULL COMMENT '用户状态：1有效2删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登陆时间',
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;
INSERT INTO `sys_user` VALUES ('1', '1', 'admin', 'TeRARkF3rak6MokqdtwS5g==', '8cd50474d2a3c1e88298e91df8bf6f1c', '523179414@qq.com', '187888899991', '1', '22', '1', '2018-09-18 14:00:02', '2018-09-18 14:00:02', '2019-03-22 18:35:10');
INSERT INTO `sys_user` VALUES ('2', '2', '测试', 'eedqXGyUJwa/dFCLOZ+IYg==', '8cd50474d2a3c1e88298e91df8bf6f1c', '5231794143@qq.com', '1878888999913', '0', '233', '1', '2018-09-18 14:00:02', '2018-12-31 13:33:01', '2018-12-31 15:14:01');
