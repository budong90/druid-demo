drop table biz_user;
drop table biz_address;
CREATE TABLE `biz_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(32) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `telephone` varchar(32) DEFAULT NULL,
  `sex` tinyint(1) NOT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `biz_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `user_name` varchar(32) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `telephone` varchar(32) DEFAULT NULL,
  `sex` tinyint(1) NOT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `biz_address`
  ADD INDEX `index_user_id` (`user_id`) USING BTREE ;


drop table biz_user2;
drop table biz_address2;
CREATE TABLE `biz_user2` (
  `id` varchar(30) NOT NULL,
  `user_name` varchar(32) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `telephone` varchar(32) DEFAULT NULL,
  `sex` tinyint(1) NOT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `biz_address2` (
  `id` varchar(30) NOT NULL,
  `user_id` varchar(30) NOT NULL,
  `user_name` varchar(32) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `telephone` varchar(32) DEFAULT NULL,
  `sex` tinyint(1) NOT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `biz_address2`
  ADD INDEX `index_user_id` (`user_id`) USING BTREE ;


drop table biz_user3;
drop table biz_address3;
CREATE TABLE `biz_user3` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sid` varchar(30) NOT NULL,
  `user_name` varchar(32) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `telephone` varchar(32) DEFAULT NULL,
  `sex` tinyint(1) NOT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `biz_address3` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `user_sid` varchar(30) NOT NULL,
  `user_name` varchar(32) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `telephone` varchar(32) DEFAULT NULL,
  `sex` tinyint(1) NOT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `biz_user3`
  ADD INDEX `index_sid` (`sid`) USING BTREE ;
ALTER TABLE `biz_address3`
  ADD INDEX `index_user_id` (`user_id`) USING BTREE ;
ALTER TABLE `biz_address3`
  ADD INDEX `index_user_sid` (`user_sid`) USING BTREE ;

