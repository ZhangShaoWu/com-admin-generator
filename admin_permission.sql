SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin_permission
-- ----------------------------
DROP TABLE IF EXISTS `admin_permission`;
CREATE TABLE `admin_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '跳转链接',
  `perm` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '权限标识',
  `sort` int(11) NOT NULL COMMENT '排序',
  `parent_id` bigint(11) NOT NULL COMMENT '父级id',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '图标',
  `type` tinyint(4) NOT NULL COMMENT '类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT='权限表';

-- ----------------------------
-- Records of admin_permission
-- ----------------------------
INSERT INTO `admin_permission` VALUES ('1', '系统管理', null, 'system', '12', '0', 'el-icon-s-custom', '1');
INSERT INTO `admin_permission` VALUES ('2', '角色管理', 'system/role', 'system:role', '6', '1', null, '1');
INSERT INTO `admin_permission` VALUES ('3', '管理员', 'system/admin', 'system:admin', '16', '1', null, '1');
INSERT INTO `admin_permission` VALUES ('4', '权限管理', 'system/permission', 'system:permission', '23', '1', null, '1');
INSERT INTO `admin_permission` VALUES ('11', '列表', null, 'system:permission:list', '1', '4', null, '2');
INSERT INTO `admin_permission` VALUES ('12', '新增', null, 'system:permission:create', '2', '4', null, '2');
INSERT INTO `admin_permission` VALUES ('13', '更新', null, 'system:permission:update', '3', '4', null, '2');
INSERT INTO `admin_permission` VALUES ('14', '详情', null, 'system:permission:info', '4', '4', null, '2');
INSERT INTO `admin_permission` VALUES ('15', '删除', null, 'system:permission:remove', '5', '4', null, '2');
INSERT INTO `admin_permission` VALUES ('16', '列表', null, 'system:role:list', '1', '3', null, '2');
INSERT INTO `admin_permission` VALUES ('17', '新增', null, 'system:role:create', '2', '3', null, '2');
INSERT INTO `admin_permission` VALUES ('18', '更新', null, 'system:role:update', '3', '3', null, '2');
INSERT INTO `admin_permission` VALUES ('19', '详情', null, 'system:role:info', '4', '3', null, '2');
INSERT INTO `admin_permission` VALUES ('20', '删除', null, 'system:role:remove', '5', '3', null, '2');
INSERT INTO `admin_permission` VALUES ('21', '列表', null, 'system:admin:list', '1', '2', null, '2');
INSERT INTO `admin_permission` VALUES ('22', '新增', null, 'system:admin:create', '2', '2', null, '2');
INSERT INTO `admin_permission` VALUES ('23', '更新', null, 'system:admin:update', '3', '2', null, '2');
INSERT INTO `admin_permission` VALUES ('24', '详情', null, 'system:admin:info', '4', '2', null, '2');
INSERT INTO `admin_permission` VALUES ('25', '删除', null, 'system:admin:remove', '5', '2', null, '2');
INSERT INTO `admin_permission` VALUES ('26', '设置权限', null, 'system:role:setpermission', '6', '3', null, '2');
INSERT INTO `admin_permission` VALUES ('27', '设置角色', null, 'system:admin:setrole', '6', '2', null, '2');

-- ----------------------------
-- Table structure for admin_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `version` int(11) DEFAULT NULL,
  `remove` tinyint(1) DEFAULT '0',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '名称',
  `parent_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '父级id第一级为0',
  `has_children` tinyint(1) NOT NULL COMMENT '是否有下级',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT='后台管理员角色';

-- ----------------------------
-- Records of admin_role
-- ----------------------------
INSERT INTO `admin_role` VALUES ('1', '2019-08-13 16:24:18', '2019-10-22 10:45:17', '0', '0', '超级管理员', '0', '1');

-- ----------------------------
-- Table structure for admin_role_permission_rel
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_permission_rel`;
CREATE TABLE `admin_role_permission_rel` (
  `permission_id` bigint(11) NOT NULL COMMENT '权限id',
  `role_id` bigint(11) NOT NULL COMMENT '角色id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of admin_role_permission_rel
-- ----------------------------
INSERT INTO `admin_role_permission_rel` VALUES ('1', '1');
INSERT INTO `admin_role_permission_rel` VALUES ('2', '1');
INSERT INTO `admin_role_permission_rel` VALUES ('21', '1');
INSERT INTO `admin_role_permission_rel` VALUES ('22', '1');
INSERT INTO `admin_role_permission_rel` VALUES ('23', '1');
INSERT INTO `admin_role_permission_rel` VALUES ('24', '1');
INSERT INTO `admin_role_permission_rel` VALUES ('25', '1');
INSERT INTO `admin_role_permission_rel` VALUES ('27', '1');
INSERT INTO `admin_role_permission_rel` VALUES ('3', '1');
INSERT INTO `admin_role_permission_rel` VALUES ('16', '1');
INSERT INTO `admin_role_permission_rel` VALUES ('17', '1');
INSERT INTO `admin_role_permission_rel` VALUES ('18', '1');
INSERT INTO `admin_role_permission_rel` VALUES ('19', '1');
INSERT INTO `admin_role_permission_rel` VALUES ('20', '1');
INSERT INTO `admin_role_permission_rel` VALUES ('26', '1');
INSERT INTO `admin_role_permission_rel` VALUES ('4', '1');
INSERT INTO `admin_role_permission_rel` VALUES ('11', '1');
INSERT INTO `admin_role_permission_rel` VALUES ('12', '1');
INSERT INTO `admin_role_permission_rel` VALUES ('13', '1');
INSERT INTO `admin_role_permission_rel` VALUES ('14', '1');
INSERT INTO `admin_role_permission_rel` VALUES ('15', '1');

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `version` int(11) DEFAULT NULL,
  `remove` tinyint(1) DEFAULT '0',
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '名称\r\n',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '加盐字符串（用于密码加密）',
  `status` tinyint(4) NOT NULL COMMENT '状态',
  `gender` tinyint(4) NOT NULL COMMENT '性别',
  `company` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '公司',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT='系统管理员';

-- ----------------------------
-- Records of admin_user
-- ----------------------------
INSERT INTO `admin_user` VALUES ('1', '2019-08-13 15:30:14', '2019-10-30 16:49:00', '0', '0', 'admin', '8d421e892a47dff539f46142eb09e56b', '系统管理员', '1234', '1', '0', '');

-- ----------------------------
-- Table structure for admin_user_role_rel
-- ----------------------------
DROP TABLE IF EXISTS `admin_user_role_rel`;
CREATE TABLE `admin_user_role_rel` (
  `role_id` bigint(11) NOT NULL COMMENT '角色id',
  `admin_id` bigint(11) NOT NULL COMMENT '用户表id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of admin_user_role_rel
-- ----------------------------
INSERT INTO `admin_user_role_rel` VALUES ('1', '1');



CREATE DEFINER=`skip-grants user`@`skip-grants host` FUNCTION `getAllChildPermission`(`currentId` int) RETURNS varchar(1000) CHARSET utf8mb4
BEGIN
	DECLARE result VARCHAR(4000);
	DECLARE parentIds VARCHAR(1000);

	SET result = "";
	SET parentIds = cast(currentId as char);

	WHILE parentIds is NOT NULL DO
		set result = CONCAT(result, ",", parentIds);
		SELECT GROUP_CONCAT(id) INTO parentIds from admin_permission where FIND_IN_SET(parent_id,parentIds) > 0;
	END WHILE;
	RETURN SUBSTRING(result,2);
END