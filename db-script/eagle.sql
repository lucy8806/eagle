
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` int(11) NOT NULL COMMENT '编号，主键',
  `dept_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '扩展部门id',
  `parent_id` int(11) DEFAULT NULL COMMENT '上级部门id',
  `dept_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '部门名称',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (1, '1', 0, '开发部', 1, '2019-05-20 14:57:34', '2019-05-20 14:57:36');
INSERT INTO `sys_dept` VALUES (2, '2', 1, '开发一部', 1, '2019-05-20 14:57:51', '2019-05-20 14:57:53');
INSERT INTO `sys_dept` VALUES (3, '3', 1, '开发二部', 2, '2019-05-20 14:58:08', '2019-05-20 14:58:10');

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号，主键，资源表',
  `resourceId` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '扩展资源id',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限描述',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限访问路径',
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '对应路由组件component',
  `permission` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限标识',
  `parentId` int(11) DEFAULT NULL COMMENT '父编号',
  `type` int(1) DEFAULT NULL COMMENT '资源类型:0目录 1菜单 2按钮',
  `priority` int(3) DEFAULT NULL COMMENT '显示顺序',
  `icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '图标',
  `status` int(1) NOT NULL COMMENT '是否可用:1有效2删除',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10805 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES (1, '1', '主页', '主页', ':请', NULL, ':权', 0, 0, 1, 'fa fa-home', 1, '2018-11-18 15:39:00', '2018-11-18 15:39:00');
INSERT INTO `sys_resource` VALUES (2, '2', '权限管理', '权限管理', ':求', 'PageView', ':限', 0, 0, 2, 'appstore-o', 1, '2018-11-18 15:39:00', '2018-11-18 15:39:00');
INSERT INTO `sys_resource` VALUES (3, '3', '系统管理', '系统管理', ':路', NULL, ':标', 0, 0, 3, 'fa fa-firefox', 1, '2018-11-18 15:39:00', '2018-11-18 15:39:00');
INSERT INTO `sys_resource` VALUES (4, '4', '系统工具', '系统工具', ':径', NULL, ':识', 0, 0, 4, 'fa fa-cogs', 1, '2018-11-18 15:39:00', '2018-11-18 15:39:00');
INSERT INTO `sys_resource` VALUES (100, '100', 'eagle', 'eagle', '/index', NULL, 'index', 1, 1, 1, '', 1, '2018-11-18 15:39:00', '2018-11-18 15:39:00');
INSERT INTO `sys_resource` VALUES (102, '102', '用户管理', '用户管理', '/user/user', 'system/user/User', 'user:view', 2, 1, 1, '', 1, '2018-11-18 15:39:00', '2018-11-18 15:39:00');
INSERT INTO `sys_resource` VALUES (103, '103', '角色管理', '角色管理', '/roles', NULL, 'roles', 2, 1, 2, '', 1, '2018-11-18 15:39:00', '2018-11-18 15:39:00');
INSERT INTO `sys_resource` VALUES (104, '104', '资源管理', '资源管理', '/resources', NULL, 'resources', 2, 1, 3, '', 1, '2018-11-18 15:39:00', '2018-11-18 15:39:00');
INSERT INTO `sys_resource` VALUES (105, '105', '在线用户', '在线用户', '/onlineusers', NULL, 'onlineusers', 3, 1, 4, '', 1, '2018-11-18 15:39:00', '2018-11-18 15:39:00');
INSERT INTO `sys_resource` VALUES (106, '106', '数据监控', '数据监控', '/databases', NULL, 'databases', 4, 1, 1, '', 1, '2018-07-06 15:19:55', '2018-11-18 15:39:00');
INSERT INTO `sys_resource` VALUES (107, '107', '图标工具', '图标工具', '/icons', NULL, 'icons', 4, 1, 1, '', 1, '2018-11-18 15:39:00', '2018-11-18 15:39:00');
INSERT INTO `sys_resource` VALUES (108, '108', '部门管理', '部门管理', '/system/dept', 'system/dept/Dept', 'dept:view', 2, 1, 2, '', 1, '2019-05-20 14:45:35', '2019-05-20 14:45:38');
INSERT INTO `sys_resource` VALUES (1000, '1000', '用户查询', '用户列表查询', '/user/list', NULL, 'user:list', 102, 2, 0, NULL, 1, '2018-11-18 15:09:24', '2018-11-18 15:09:24');
INSERT INTO `sys_resource` VALUES (1001, '1001', '用户新增', '用户新增', '/user/add', NULL, 'user:add', 102, 2, 0, NULL, 1, '2018-11-18 15:06:50', '2018-11-18 15:09:24');
INSERT INTO `sys_resource` VALUES (1002, '1002', '用户编辑', '用户编辑', '/user/edit', NULL, 'user:update', 102, 2, 0, NULL, 1, '2018-11-18 15:08:03', '2018-11-18 15:09:24');
INSERT INTO `sys_resource` VALUES (1003, '1003', '用户删除', '用户删除', '/user/delete', NULL, 'user:delete', 102, 2, 0, NULL, 1, '2018-11-18 15:08:42', '2018-11-18 15:09:24');
INSERT INTO `sys_resource` VALUES (1004, '1004', '用户分配角色', '用户分配角色', '/user/saveUserRoles', NULL, 'user:saveUserRoles', 102, 2, 0, '', 1, '2018-07-11 01:53:09', '2018-11-18 15:09:24');
INSERT INTO `sys_resource` VALUES (1005, '1005', '重置密码', '重置密码', '/user/resetPwd', NULL, 'user:resetPwd', 102, 2, 0, NULL, 1, '2018-11-18 15:09:24', '2018-11-18 15:09:24');
INSERT INTO `sys_resource` VALUES (1006, '1006', '角色查询', '角色列表查询', '/role/list', NULL, 'role:list', 103, 2, 0, NULL, 1, '2018-11-18 15:31:36', '2018-11-18 10:53:14');
INSERT INTO `sys_resource` VALUES (1007, '1007', '角色新增', '新增角色', '/role/add', NULL, 'role:add', 103, 2, 0, NULL, 1, '2018-11-18 14:39:46', '2018-11-18 10:53:14');
INSERT INTO `sys_resource` VALUES (1008, '1008', '角色编辑', '编辑角色', '/role/edit', NULL, 'role:edit', 103, 2, 0, NULL, 1, '2018-11-18 14:40:15', '2018-11-18 10:53:14');
INSERT INTO `sys_resource` VALUES (1009, '1009', '角色删除', '删除角色', '/role/delete', NULL, 'role:delete', 103, 2, 0, NULL, 1, '2018-11-18 14:40:57', '2018-11-18 10:53:14');
INSERT INTO `sys_resource` VALUES (1010, '1010', '角色分配资源', '角色分配资源', '/role/assign', NULL, 'role:assign', 103, 2, 0, '', 1, '2018-11-18 22:20:43', '2018-11-18 22:20:43');
INSERT INTO `sys_resource` VALUES (1011, '1011', '资源查询', '资源查询', '/resource/list', NULL, 'resource:list', 104, 2, 0, NULL, 1, '2018-11-18 16:25:28', '2018-11-18 16:25:33');
INSERT INTO `sys_resource` VALUES (1012, '1012', '资源新增', '资源新增', '/resource/add', NULL, 'resource:add', 104, 2, 0, NULL, 1, '2018-11-18 08:06:58', '2018-11-18 10:53:14');
INSERT INTO `sys_resource` VALUES (1013, '1013', '资源编辑', '资源编辑', '/resource/edit', NULL, 'resource:edit', 104, 2, 0, NULL, 1, '2018-11-18 21:29:04', '2018-11-18 10:53:14');
INSERT INTO `sys_resource` VALUES (1014, '1014', '资源删除', '资源删除', '/resource/delete', NULL, 'resource:delete', 104, 2, 0, NULL, 1, '2018-11-18 21:29:50', '2018-11-18 10:53:14');
INSERT INTO `sys_resource` VALUES (1015, '1015', '在线用户查询', '在线用户查询', '/onlineuser/list', NULL, 'onlineuser:list', 105, 2, 0, NULL, 1, '2018-11-18 21:01:25', '2018-11-18 12:48:04');
INSERT INTO `sys_resource` VALUES (1016, '1016', '踢出用户', '踢出用户', '/onlineuser/kickout', NULL, 'onlineuser:kickout', 105, 2, 0, NULL, 1, '2018-11-18 21:41:54', '2018-11-18 12:48:25');
INSERT INTO `sys_resource` VALUES (1017, '1017', '批量踢出', '批量踢出', '/onlineuser/batchkickout', NULL, 'onlineuser:batchkickout', 105, 2, 0, '', 1, '2018-11-18 12:49:30', '2018-11-18 12:49:30');
INSERT INTO `sys_resource` VALUES (1027, 'c6a93d7', '测试目录', '', '', NULL, '', 0, 0, 7, '', 1, '2018-12-26 20:55:17', '2018-12-26 20:55:17');
INSERT INTO `sys_resource` VALUES (1031, '9ab907b', '测试', '', '', NULL, '1', 1027, 2, 1, '', 1, '2018-12-27 21:11:13', '2018-12-27 21:11:13');
INSERT INTO `sys_resource` VALUES (10801, '10801', '新增部门', '新增部门', NULL, NULL, 'dept:add', 108, 2, NULL, '', 1, '2019-05-20 14:47:35', '2019-05-20 14:47:37');
INSERT INTO `sys_resource` VALUES (10802, '10802', '修改部门', '修改部门', NULL, NULL, 'dept:update', 108, 2, NULL, '', 1, '2019-05-20 14:48:40', '2019-05-20 14:48:42');
INSERT INTO `sys_resource` VALUES (10803, '10803', '删除部门', '删除部门', NULL, NULL, 'dept:delete', 108, 2, NULL, '', 1, '2019-05-20 14:48:50', '2019-05-20 14:48:52');
INSERT INTO `sys_resource` VALUES (10804, '10804', '导出部门', '导出部门', NULL, NULL, 'dept:export', 108, 2, NULL, '', 1, '2019-05-20 14:50:51', '2019-05-20 14:50:53');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号，主键，角色表',
  `roleId` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '扩展角色id',
  `role` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色描述',
  `status` int(1) NOT NULL COMMENT '是否可用：1有效2删除',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '1', '超级管理员', '超级管理员', 1, '2018-09-18 14:00:02', '2018-09-18 14:00:02');
INSERT INTO `sys_role` VALUES (37, 'b3e9e9b', '测试角色', '用来测试的角色', 1, NULL, '2018-12-27 21:20:32');

-- ----------------------------
-- Table structure for sys_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号，主键，角色-资源关联表',
  `roleId` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色id',
  `resourceId` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 272 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role_resource
-- ----------------------------
INSERT INTO `sys_role_resource` VALUES (236, '1', '1');
INSERT INTO `sys_role_resource` VALUES (237, '1', '100');
INSERT INTO `sys_role_resource` VALUES (238, '1', '101');
INSERT INTO `sys_role_resource` VALUES (239, '1', '2');
INSERT INTO `sys_role_resource` VALUES (240, '1', '102');
INSERT INTO `sys_role_resource` VALUES (241, '1', '1000');
INSERT INTO `sys_role_resource` VALUES (242, '1', '1001');
INSERT INTO `sys_role_resource` VALUES (243, '1', '1002');
INSERT INTO `sys_role_resource` VALUES (244, '1', '1003');
INSERT INTO `sys_role_resource` VALUES (245, '1', '1004');
INSERT INTO `sys_role_resource` VALUES (246, '1', '1005');
INSERT INTO `sys_role_resource` VALUES (247, '1', '103');
INSERT INTO `sys_role_resource` VALUES (248, '1', '1006');
INSERT INTO `sys_role_resource` VALUES (249, '1', '1007');
INSERT INTO `sys_role_resource` VALUES (250, '1', '1008');
INSERT INTO `sys_role_resource` VALUES (251, '1', '1009');
INSERT INTO `sys_role_resource` VALUES (252, '1', '1010');
INSERT INTO `sys_role_resource` VALUES (253, '1', '104');
INSERT INTO `sys_role_resource` VALUES (254, '1', '1011');
INSERT INTO `sys_role_resource` VALUES (255, '1', '1012');
INSERT INTO `sys_role_resource` VALUES (256, '1', '1013');
INSERT INTO `sys_role_resource` VALUES (257, '1', '1014');
INSERT INTO `sys_role_resource` VALUES (258, '1', '3');
INSERT INTO `sys_role_resource` VALUES (259, '1', '105');
INSERT INTO `sys_role_resource` VALUES (260, '1', '1015');
INSERT INTO `sys_role_resource` VALUES (261, '1', '1016');
INSERT INTO `sys_role_resource` VALUES (262, '1', '1017');
INSERT INTO `sys_role_resource` VALUES (263, '1', '4');
INSERT INTO `sys_role_resource` VALUES (264, '1', '106');
INSERT INTO `sys_role_resource` VALUES (265, '1', '107');
INSERT INTO `sys_role_resource` VALUES (267, '1', '10801');
INSERT INTO `sys_role_resource` VALUES (268, '1', '10802');
INSERT INTO `sys_role_resource` VALUES (269, '1', '10803');
INSERT INTO `sys_role_resource` VALUES (270, '1', '10804');
INSERT INTO `sys_role_resource` VALUES (271, '1', '108');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号，主键,用户表',
  `userId` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '扩展用户id',
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `salt` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '盐',
  `dept_id` int(11) DEFAULT NULL COMMENT '部门id',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '联系方式',
  `sex` int(1) DEFAULT NULL COMMENT '性别：0男1女2保密',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `status` int(1) NOT NULL COMMENT '用户状态：0锁定1有效',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `last_login_time` datetime(0) DEFAULT NULL COMMENT '最后登陆时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '1', 'admin', 'TeRARkF3rak6MokqdtwS5g==', '8cd50474d2a3c1e88298e91df8bf6f1c', 1, '523179414@qq.com', '187888899991', 1, 22, 1, '2018-09-18 14:00:02', '2018-09-18 14:00:02', '2019-03-22 18:35:10');
INSERT INTO `sys_user` VALUES (2, '2', '测试', 'eedqXGyUJwa/dFCLOZ+IYg==', '8cd50474d2a3c1e88298e91df8bf6f1c', 2, '5231794143@qq.com', '1878888999913', 0, 233, 1, '2018-09-18 14:00:02', '2018-12-31 13:33:01', '2018-12-31 15:14:01');
INSERT INTO `sys_user` VALUES (3, 'bfb2064', 'test', '+tNn/OjOwOoUZ3YezdRXjQ==', NULL, 3, '32345@qq.com', '18788896549', 1, NULL, 1, '2019-05-27 15:43:36', '2019-05-28 10:55:47', NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号，主键，用户-角色关联表',
  `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `role_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, '1', '1');
INSERT INTO `sys_user_role` VALUES (4, '3', '37');
INSERT INTO `sys_user_role` VALUES (5, '3', '1');

SET FOREIGN_KEY_CHECKS = 1;
