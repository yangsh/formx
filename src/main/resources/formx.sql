CREATE DATABASE formx DEFAULT CHARSET=utf8;

USE formx;

CREATE TABLE t_wx_access_token (
    `id` INT(5) PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    `access_token` VARCHAR(600) NOT NULL COMMENT 'access_token',
    `create_time` DATETIME NOT NULL COMMENT '创建时间',
    `update_time` DATETIME NOT NULL COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信access_token表';

CREATE TABLE t_wx_jsapi_tiket (
    `id` INT(5) PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    `jsapi_tiket` VARCHAR(200) NOT NULL COMMENT 'jsapi_tiket',
    `create_time` DATETIME NOT NULL COMMENT '创建时间',
    `update_time` DATETIME NOT NULL COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信jsapi_tiket表';

CREATE TABLE t_role (
    `role_id` INT(5) PRIMARY KEY AUTO_INCREMENT COMMENT '角色ID(主键)',
    `role_name` VARCHAR(32) UNIQUE NOT NULL COMMENT '角色名称'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

CREATE TABLE t_user (
    `user_id` VARCHAR(64) PRIMARY KEY NOT NULL COMMENT '用户ID(主键)',
    `name` VARCHAR(32) COMMENT '姓名',
    `role_id` INT(5) NOT NULL COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

CREATE TABLE t_shop (
    `shop_id` INT(5) PRIMARY KEY AUTO_INCREMENT COMMENT '店铺ID(主键)',
    `shop_name` VARCHAR(64) NOT NULL COMMENT '店铺名称'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='店铺表';

CREATE TABLE t_user_shop_relation (
    `id` BIGINT(8) PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    `user_id` VARCHAR(64) NOT NULL COMMENT '用户ID',
    `shop_id` INT(5) NOT NULL COMMENT '店铺ID',
    `shop_name` VARCHAR(64) NOT NULL COMMENT '店铺名称'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户店铺关系表';

CREATE TABLE t_form (
    `form_id` INT(5) PRIMARY KEY AUTO_INCREMENT COMMENT '表单ID(主键)',
    `form_name` VARCHAR(64) UNIQUE NOT NULL COMMENT '表单名称',
    `form_type` ENUM('1', '2') NOT NULL COMMENT '表单类型: 1-文本; 2-图片',
    `order_num` INT(5) NOT NULL DEFAULT 0 COMMENT '排序权重值'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='表单表';

CREATE TABLE t_form_item (
    `form_item_id` BIGINT(8) PRIMARY KEY AUTO_INCREMENT COMMENT '表单项ID(主键)',
    `form_id` INT(5) NOT NULL COMMENT '表单ID',
    `form_item_name` VARCHAR(64) COMMENT '表单项名称',
    `order_num` INT(5) NOT NULL DEFAULT 0 COMMENT '排序权重值',
    `action_item` ENUM('0', '1') NOT NULL COMMENT '是否操作项: 0-否; 1-是'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='表单项表';

CREATE TABLE t_shop_form_execution (
    `shop_form_execution_id` BIGINT(8) PRIMARY KEY AUTO_INCREMENT COMMENT '店铺表单执行ID(主键)',
    `shop_id` INT(5) NOT NULL COMMENT '店铺ID',
    `form_id` INT(5) NOT NULL COMMENT '表单ID',
    `form_name` VARCHAR(64) NOT NULL COMMENT '表单名称',
    `form_type` ENUM('1', '2') NOT NULL COMMENT '表单类型: 1-文本; 2-图片',
    `report_status` ENUM('0', '1') NOT NULL COMMENT '汇报状态: 0-未汇报; 1-已汇报',
    `confirm_status` ENUM('0', '1') NOT NULL COMMENT '确认状态: 0-未确认; 1-已确认',
    `comment` VARCHAR(255) COMMENT '评论',
    `order_num` INT(5) NOT NULL DEFAULT 0 COMMENT '排序权重值',
    `report_time` DATETIME COMMENT '汇报时间',
    `confirm_time` DATETIME COMMENT '确认时间',
    `create_time` DATETIME NOT NULL COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='店铺表单执行表';

CREATE TABLE t_shop_form_text_item (
    `shop_form_text_item_id` BIGINT(8) PRIMARY KEY AUTO_INCREMENT COMMENT '店铺表单文本项ID(主键)',
    `shop_form_execution_id` BIGINT(8) NOT NULL COMMENT '店铺表单执行ID',
    `form_item_id` BIGINT(8) NOT NULL COMMENT '表单项ID',
    `form_item_name` VARCHAR(64) COMMENT '表单项名称',
    `order_num` INT(5) NOT NULL DEFAULT 0 COMMENT '排序权重值',
    `action_item` ENUM('0', '1') NOT NULL COMMENT '是否操作项: 0-否; 1-是',
    `execute_status` ENUM('0', '1') NOT NULL COMMENT '执行状态: 0-未执行; 1-已执行',
    `execute_time` DATETIME COMMENT '执行时间',
    `create_time` DATETIME NOT NULL COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='店铺表单文本项表';

CREATE TABLE t_shop_form_pic_item (
    `shop_form_pic_item_id` BIGINT(8) PRIMARY KEY AUTO_INCREMENT COMMENT '店铺表单图片项ID(主键)',
    `shop_form_execution_id` BIGINT(8) NOT NULL COMMENT '店铺表单执行ID',
    `pic_name` VARCHAR(64) NOT NULL COMMENT '图片名称',
    `pic_comment` VARCHAR(255) COMMENT '图片评论',
    `create_time` DATETIME NOT NULL COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='店铺表单图片项表';

CREATE TABLE t_shop_execute_record (
    `shop_execute_record_id` BIGINT(8) PRIMARY KEY AUTO_INCREMENT COMMENT '店铺执行记录ID(主键)',
    `shop_id` INT(5) NOT NULL COMMENT '店铺ID',
    `shop_name` VARCHAR(64) NOT NULL COMMENT '店铺名称',
    `complete_time` DATETIME NOT NULL COMMENT '完成时间',
    `confirm_status` ENUM('0', '1') NOT NULL COMMENT '确认状态: 0-未确认; 1-已确认',
    `confirm_time` DATETIME COMMENT '确认时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='店铺执行记录表';

