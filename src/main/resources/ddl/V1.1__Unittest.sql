
DROP TABLE IF EXISTS `demo`;
CREATE TABLE `demo` (
    `demo_id` bigint(20) NOT NULL COMMENT 'ID',
    `demo_code` varchar(50) DEFAULT NULL COMMENT '编码',
    `demo_name` varchar(50) DEFAULT NULL COMMENT '名称',
    `status` varchar(50) DEFAULT NULL COMMENT '状态',
    `status_desc` varchar(50) DEFAULT NULL COMMENT '状态描述',
    `demo_qty` int(11) DEFAULT NULL COMMENT '数量',
    `demo_rate` decimal(10,3) DEFAULT NULL COMMENT '税率',
    `start_date` datetime DEFAULT NULL COMMENT '开始时间',
    `end_date` datetime DEFAULT NULL COMMENT '结束时间',
    `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
    `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
    `create_by_name` varchar(50) DEFAULT NULL COMMENT '创建人姓名',
    `update_time` datetime DEFAULT NULL COMMENT '修改时间',
    `update_by` varchar(50) DEFAULT NULL COMMENT '修改人',
    `update_by_name` varchar(50) DEFAULT NULL COMMENT '修改人描述',
    `version` int(11) NOT NULL DEFAULT '0',
    PRIMARY KEY (`demo_id`)
) ;


INSERT INTO `demo`(`demo_id`, `demo_code`, `demo_name`, `status`, `status_desc`, `demo_qty`, `demo_rate`, `start_date`, `end_date`, `create_time`, `create_by`, `create_by_name`, `update_time`, `update_by`, `update_by_name`, `version`) VALUES (1, 'bbb', 'aaa', 'Y', '启用', 1, 33.000, '2019-07-28 00:00:00', '2019-07-28 00:00:00', '2019-07-31 20:22:30', 'admin', 'admin', '2019-07-31 20:22:30', 'admin', 'admin', 0);
INSERT INTO `demo`(`demo_id`, `demo_code`, `demo_name`, `status`, `status_desc`, `demo_qty`, `demo_rate`, `start_date`, `end_date`, `create_time`, `create_by`, `create_by_name`, `update_time`, `update_by`, `update_by_name`, `version`) VALUES (6562301868079329280, 'D201907310019', 'hhe7312', 'Y', '启用', 1, 22.000, '2019-07-28 00:00:00', '2019-07-28 00:00:00', '2019-07-31 20:04:47', 'admin', 'admin', NULL, NULL, NULL, 0);
INSERT INTO `demo`(`demo_id`, `demo_code`, `demo_name`, `status`, `status_desc`, `demo_qty`, `demo_rate`, `start_date`, `end_date`, `create_time`, `create_by`, `create_by_name`, `update_time`, `update_by`, `update_by_name`, `version`) VALUES (6562301868687503360, 'D201907310020', '随机名称：1518070981', 'Y', '启用', 1, 33.000, '2019-07-28 00:00:00', '2019-07-28 00:00:00', '2019-07-31 20:04:47', 'admin', 'admin', '2019-07-31 20:04:47', 'admin', 'admin', 0);
INSERT INTO `demo`(`demo_id`, `demo_code`, `demo_name`, `status`, `status_desc`, `demo_qty`, `demo_rate`, `start_date`, `end_date`, `create_time`, `create_by`, `create_by_name`, `update_time`, `update_by`, `update_by_name`, `version`) VALUES (6562302561842376704, 'D201907310021', '随机名称：-869629984', 'Y', '启用', 1, 22.000, '2019-07-28 00:00:00', '2019-07-28 00:00:00', '2019-07-31 20:07:32', 'admin', 'admin', '2019-07-31 20:07:32', 'admin', 'admin', 0);
INSERT INTO `demo`(`demo_id`, `demo_code`, `demo_name`, `status`, `status_desc`, `demo_qty`, `demo_rate`, `start_date`, `end_date`, `create_time`, `create_by`, `create_by_name`, `update_time`, `update_by`, `update_by_name`, `version`) VALUES (6562302976415772672, 'D201907310022', '李四1', 'N', '停用', 1, 33.000, '2019-07-28 00:00:00', '2019-07-28 00:00:00', '2019-07-31 20:09:11', 'admin', 'admin', '2019-07-31 20:09:11', 'admin', 'admin', 0);
INSERT INTO `demo`(`demo_id`, `demo_code`, `demo_name`, `status`, `status_desc`, `demo_qty`, `demo_rate`, `start_date`, `end_date`, `create_time`, `create_by`, `create_by_name`, `update_time`, `update_by`, `update_by_name`, `version`) VALUES (6562306324720267264, 'D201907310023', '张三2', 'N', '停用', 1, 22.000, '2019-07-28 00:00:00', '2019-07-28 00:00:00', '2019-07-31 20:22:30', 'admin', 'admin', '2019-07-31 20:22:30', 'admin', 'admin', 0);
INSERT INTO `demo`(`demo_id`, `demo_code`, `demo_name`, `status`, `status_desc`, `demo_qty`, `demo_rate`, `start_date`, `end_date`, `create_time`, `create_by`, `create_by_name`, `update_time`, `update_by`, `update_by_name`, `version`) VALUES (6562306325131309056, 'D201907310024', '李四2', 'N', '停用', 1, 33.000, '2019-07-28 00:00:00', '2019-07-28 00:00:00', '2019-07-31 20:22:30', 'admin', 'admin', '2019-07-31 20:22:30', 'admin', 'admin', 0);
INSERT INTO `demo`(`demo_id`, `demo_code`, `demo_name`, `status`, `status_desc`, `demo_qty`, `demo_rate`, `start_date`, `end_date`, `create_time`, `create_by`, `create_by_name`, `update_time`, `update_by`, `update_by_name`, `version`) VALUES (6566940247236542464, 'D201908130001', '导入测试名称1', 'Y', '是', 1, 1.000, NULL, NULL, '2019-08-13 15:16:03', 'admin', 'admin', '2019-08-13 15:16:03', 'admin', 'admin', 0);
INSERT INTO `demo`(`demo_id`, `demo_code`, `demo_name`, `status`, `status_desc`, `demo_qty`, `demo_rate`, `start_date`, `end_date`, `create_time`, `create_by`, `create_by_name`, `update_time`, `update_by`, `update_by_name`, `version`) VALUES (6567946921279750144, 'D201908160001', 'AAA', 'Y', '是', 2, 7.000, '2019-08-16 00:00:00', '2019-08-17 00:00:00', '2019-08-16 09:56:13', 'admin', 'admin', '2019-08-16 09:56:13', 'admin', 'admin', 0);
INSERT INTO `demo`(`demo_id`, `demo_code`, `demo_name`, `status`, `status_desc`, `demo_qty`, `demo_rate`, `start_date`, `end_date`, `create_time`, `create_by`, `create_by_name`, `update_time`, `update_by`, `update_by_name`, `version`) VALUES (6567947472537124864, 'D201908160002', 'AAA', 'Y', '是', 2, 7.000, '2019-08-16 00:00:00', '2019-08-17 00:00:00', '2019-08-16 09:58:24', 'admin', 'admin', '2019-08-16 09:58:24', 'admin', 'admin', 0);
INSERT INTO `demo`(`demo_id`, `demo_code`, `demo_name`, `status`, `status_desc`, `demo_qty`, `demo_rate`, `start_date`, `end_date`, `create_time`, `create_by`, `create_by_name`, `update_time`, `update_by`, `update_by_name`, `version`) VALUES (6567947711692144640, 'D201908160003', '杀手', 'Y', '是', 2, 7.000, '2019-08-16 00:00:00', '2019-08-17 00:00:00', '2019-08-16 09:59:21', 'admin', 'admin', '2019-08-16 09:59:21', 'admin', 'admin', 0);
INSERT INTO `demo`(`demo_id`, `demo_code`, `demo_name`, `status`, `status_desc`, `demo_qty`, `demo_rate`, `start_date`, `end_date`, `create_time`, `create_by`, `create_by_name`, `update_time`, `update_by`, `update_by_name`, `version`) VALUES (6567952911945433088, 'D201908160004', 'AAA', 'Y', '是', 2, 7.000, '2019-08-16 00:00:00', '2019-08-17 00:00:00', '2019-08-16 10:20:01', 'admin', 'admin', '2019-08-16 10:20:01', 'admin', 'admin', 0);
INSERT INTO `demo`(`demo_id`, `demo_code`, `demo_name`, `status`, `status_desc`, `demo_qty`, `demo_rate`, `start_date`, `end_date`, `create_time`, `create_by`, `create_by_name`, `update_time`, `update_by`, `update_by_name`, `version`) VALUES (6567952973857554432, 'D201908160005', 'AAA', 'Y', '是', 2, 7.000, '2019-08-16 00:00:00', '2019-08-17 00:00:00', '2019-08-16 10:20:16', 'admin', 'admin', '2019-08-16 10:20:16', 'admin', 'admin', 0);
INSERT INTO `demo`(`demo_id`, `demo_code`, `demo_name`, `status`, `status_desc`, `demo_qty`, `demo_rate`, `start_date`, `end_date`, `create_time`, `create_by`, `create_by_name`, `update_time`, `update_by`, `update_by_name`, `version`) VALUES (6567953486372143104, 'D201908160006', 'AAA', 'Y', '是', 2, 7.000, '2019-08-16 00:00:00', '2019-08-17 00:00:00', '2019-08-16 10:22:18', 'admin', 'admin', '2019-08-16 10:22:18', 'admin', 'admin', 0);
