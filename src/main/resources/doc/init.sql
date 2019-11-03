-- 用户表
CREATE TABLE `commerce`.`member` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` char(30) NOT NULL COMMENT '用户',
  `password` char(30) NOT NULL COMMENT '密码',
  `remaining_sum` bigint(20) unsigned DEFAULT 0 COMMENT '用户余额',
  `is_delete` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否删除：0未删除，1已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_user_name` (`user_name`),
  KEY `idx_create_time` (`create_time`),
  KEY `idx_update_time` (`update_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';