-- 新港人生活号数据库初始化脚本

-- 创建数据库
CREATE DATABASE IF NOT EXISTS newhker DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE newhker;

-- 用户表
CREATE TABLE `tb_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `openid` varchar(100) DEFAULT NULL COMMENT '微信openid',
  `unionid` varchar(100) DEFAULT NULL COMMENT '微信unionid',
  `nickname` varchar(50) DEFAULT NULL COMMENT '用户昵称',
  `avatar` varchar(500) DEFAULT NULL COMMENT '用户头像',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `gender` tinyint DEFAULT '0' COMMENT '性别：0-未知，1-男，2-女',
  `status` tinyint DEFAULT '0' COMMENT '用户状态：0-正常，1-拉黑',
  `deleted` tinyint DEFAULT '0' COMMENT '逻辑删除：0-未删除，1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_openid` (`openid`),
  KEY `idx_phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

-- 管理员表
CREATE TABLE `tb_admin` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(50) NOT NULL COMMENT '管理员账号',
  `password` varchar(100) NOT NULL COMMENT '管理员密码',
  `nickname` varchar(50) DEFAULT NULL COMMENT '管理员昵称',
  `avatar` varchar(500) DEFAULT NULL COMMENT '头像',
  `role` tinyint DEFAULT '2' COMMENT '角色：1-超级管理员，2-普通管理员',
  `status` tinyint DEFAULT '0' COMMENT '状态：0-正常，1-禁用',
  `deleted` tinyint DEFAULT '0' COMMENT '逻辑删除：0-未删除，1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='管理员表';

-- 插入默认管理员账号（密码：admin123）
-- 注意：首次运行后请通过 PasswordGenerator 工具类生成新的密码哈希并更新数据库
INSERT INTO `tb_admin` (`username`, `password`, `nickname`, `role`) 
VALUES ('admin', '$2a$10$b4azLwjRlXjmojzTENvX3.SWN6vTxnJBd4U3B6uVwOvZNpORkHh5.', '超级管理员', 1);

-- 模块分类表
CREATE TABLE `tb_module` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `parent_id` bigint DEFAULT '0' COMMENT '父级模块ID，0表示顶级模块',
  `name` varchar(50) NOT NULL COMMENT '模块名称',
  `icon` varchar(500) DEFAULT NULL COMMENT '模块图标',
  `level` tinyint NOT NULL COMMENT '模块层级：1-L1，2-L2，3-L3',
  `sort_order` int DEFAULT '0' COMMENT '排序',
  `status` tinyint DEFAULT '0' COMMENT '状态：0-启用，1-禁用',
  `deleted` tinyint DEFAULT '0' COMMENT '逻辑删除：0-未删除，1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_parent_id` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='模块分类表';

-- 插入初始模块数据
INSERT INTO `tb_module` (`parent_id`, `name`, `level`, `sort_order`) VALUES
(0, '身份', 1, 1),
(0, '教育', 1, 2),
(0, '金融', 1, 3),
(0, '生活', 1, 4);

-- 身份二级模块
INSERT INTO `tb_module` (`parent_id`, `name`, `level`, `sort_order`) 
SELECT id, '身份介绍', 2, 1 FROM tb_module WHERE name='身份' AND level=1;
INSERT INTO `tb_module` (`parent_id`, `name`, `level`, `sort_order`) 
SELECT id, '身份申请', 2, 2 FROM tb_module WHERE name='身份' AND level=1;
INSERT INTO `tb_module` (`parent_id`, `name`, `level`, `sort_order`) 
SELECT id, '身份续签', 2, 3 FROM tb_module WHERE name='身份' AND level=1;
INSERT INTO `tb_module` (`parent_id`, `name`, `level`, `sort_order`) 
SELECT id, '永居政策', 2, 4 FROM tb_module WHERE name='身份' AND level=1;

-- 教育二级模块
INSERT INTO `tb_module` (`parent_id`, `name`, `level`, `sort_order`) 
SELECT id, '中小学教育', 2, 1 FROM tb_module WHERE name='教育' AND level=1;
INSERT INTO `tb_module` (`parent_id`, `name`, `level`, `sort_order`) 
SELECT id, '留学', 2, 2 FROM tb_module WHERE name='教育' AND level=1;
INSERT INTO `tb_module` (`parent_id`, `name`, `level`, `sort_order`) 
SELECT id, '短期进修', 2, 3 FROM tb_module WHERE name='教育' AND level=1;
INSERT INTO `tb_module` (`parent_id`, `name`, `level`, `sort_order`) 
SELECT id, '粤语培训', 2, 4 FROM tb_module WHERE name='教育' AND level=1;

-- 身份三级模块
-- 身份介绍的子模块
INSERT INTO `tb_module` (`parent_id`, `name`, `level`, `sort_order`) 
SELECT id, '身份优势', 3, 1 FROM tb_module WHERE name='身份介绍' AND level=2;

-- 身份申请的子模块
INSERT INTO `tb_module` (`parent_id`, `name`, `level`, `sort_order`) 
SELECT id, '申请条件', 3, 1 FROM tb_module WHERE name='身份申请' AND level=2;
INSERT INTO `tb_module` (`parent_id`, `name`, `level`, `sort_order`) 
SELECT id, '申请通道', 3, 2 FROM tb_module WHERE name='身份申请' AND level=2;
INSERT INTO `tb_module` (`parent_id`, `name`, `level`, `sort_order`) 
SELECT id, '申请流程', 3, 3 FROM tb_module WHERE name='身份申请' AND level=2;

-- 身份续签的子模块
INSERT INTO `tb_module` (`parent_id`, `name`, `level`, `sort_order`) 
SELECT id, '续签标准', 3, 1 FROM tb_module WHERE name='身份续签' AND level=2;
INSERT INTO `tb_module` (`parent_id`, `name`, `level`, `sort_order`) 
SELECT id, '续签流程', 3, 2 FROM tb_module WHERE name='身份续签' AND level=2;

-- 永居政策的子模块
INSERT INTO `tb_module` (`parent_id`, `name`, `level`, `sort_order`) 
SELECT id, '入籍条件', 3, 1 FROM tb_module WHERE name='永居政策' AND level=2;
INSERT INTO `tb_module` (`parent_id`, `name`, `level`, `sort_order`) 
SELECT id, '申请流程', 3, 2 FROM tb_module WHERE name='永居政策' AND level=2;

-- 教育三级模块
-- 中小学教育的子模块
INSERT INTO `tb_module` (`parent_id`, `name`, `level`, `sort_order`) 
SELECT id, '学校信息', 3, 1 FROM tb_module WHERE name='中小学教育' AND level=2;
INSERT INTO `tb_module` (`parent_id`, `name`, `level`, `sort_order`) 
SELECT id, '插班攻略', 3, 2 FROM tb_module WHERE name='中小学教育' AND level=2;
INSERT INTO `tb_module` (`parent_id`, `name`, `level`, `sort_order`) 
SELECT id, '升学考试', 3, 3 FROM tb_module WHERE name='中小学教育' AND level=2;

-- 留学的子模块
INSERT INTO `tb_module` (`parent_id`, `name`, `level`, `sort_order`) 
SELECT id, '学校信息', 3, 1 FROM tb_module WHERE name='留学' AND level=2;
INSERT INTO `tb_module` (`parent_id`, `name`, `level`, `sort_order`) 
SELECT id, '申请指南', 3, 2 FROM tb_module WHERE name='留学' AND level=2;
INSERT INTO `tb_module` (`parent_id`, `name`, `level`, `sort_order`) 
SELECT id, '入学考试', 3, 3 FROM tb_module WHERE name='留学' AND level=2;
INSERT INTO `tb_module` (`parent_id`, `name`, `level`, `sort_order`) 
SELECT id, '生活导览', 3, 4 FROM tb_module WHERE name='留学' AND level=2;

-- 短期进修的子模块
INSERT INTO `tb_module` (`parent_id`, `name`, `level`, `sort_order`) 
SELECT id, '学校信息', 3, 1 FROM tb_module WHERE name='短期进修' AND level=2;
INSERT INTO `tb_module` (`parent_id`, `name`, `level`, `sort_order`) 
SELECT id, 'IANG签', 3, 2 FROM tb_module WHERE name='短期进修' AND level=2;

-- 粤语培训的子模块
INSERT INTO `tb_module` (`parent_id`, `name`, `level`, `sort_order`) 
SELECT id, '内容', 3, 1 FROM tb_module WHERE name='粤语培训' AND level=2;

-- 文章表
CREATE TABLE `tb_article` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(200) NOT NULL COMMENT '文章标题',
  `cover` varchar(500) DEFAULT NULL COMMENT '文章封面',
  `content` text COMMENT '文章内容',
  `type` tinyint DEFAULT '1' COMMENT '文章类型：1-图文，2-视频',
  `video_url` varchar(500) DEFAULT NULL COMMENT '视频URL（type=2时有效）',
  `module_id` bigint NOT NULL COMMENT '所属模块ID',
  `view_count` int DEFAULT '0' COMMENT '浏览次数',
  `like_count` int DEFAULT '0' COMMENT '点赞次数',
  `collect_count` int DEFAULT '0' COMMENT '收藏次数',
  `share_count` int DEFAULT '0' COMMENT '分享次数',
  `audit_status` tinyint DEFAULT '0' COMMENT '审核状态：0-待审核，1-审核通过，2-审核拒绝',
  `audit_user_id` bigint DEFAULT NULL COMMENT '审核人ID',
  `audit_time` datetime DEFAULT NULL COMMENT '审核时间',
  `audit_remark` varchar(500) DEFAULT NULL COMMENT '审核意见',
  `publish_status` tinyint DEFAULT '0' COMMENT '发布状态：0-草稿，1-已发布，2-已下架',
  `publish_time` datetime DEFAULT NULL COMMENT '发布时间',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `deleted` tinyint DEFAULT '0' COMMENT '逻辑删除：0-未删除，1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_module_id` (`module_id`),
  KEY `idx_publish_status` (`publish_status`),
  KEY `idx_publish_time` (`publish_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文章表';

-- Banner表
CREATE TABLE `tb_banner` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(100) NOT NULL COMMENT 'Banner标题',
  `image_url` varchar(500) NOT NULL COMMENT 'Banner图片URL',
  `jump_type` tinyint DEFAULT '1' COMMENT '跳转类型：1-文章详情，2-外链',
  `jump_target` varchar(500) DEFAULT NULL COMMENT '跳转目标（文章ID或外链URL）',
  `sort_order` int DEFAULT '0' COMMENT '排序',
  `audit_status` tinyint DEFAULT '0' COMMENT '审核状态：0-待审核，1-审核通过，2-审核拒绝',
  `audit_user_id` bigint DEFAULT NULL COMMENT '审核人ID',
  `audit_time` datetime DEFAULT NULL COMMENT '审核时间',
  `publish_status` tinyint DEFAULT '0' COMMENT '发布状态：0-草稿，1-已发布，2-已下架',
  `publish_time` datetime DEFAULT NULL COMMENT '发布时间',
  `offline_time` datetime DEFAULT NULL COMMENT '下架时间',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `deleted` tinyint DEFAULT '0' COMMENT '逻辑删除：0-未删除，1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_publish_status` (`publish_status`),
  KEY `idx_sort_order` (`sort_order`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Banner表';

-- 用户收藏表
CREATE TABLE `tb_user_collect` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `article_id` bigint NOT NULL COMMENT '文章ID',
  `deleted` tinyint DEFAULT '0' COMMENT '逻辑删除：0-未删除，1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_article` (`user_id`, `article_id`),
  KEY `idx_article_id` (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户收藏表';

-- 用户点赞表
CREATE TABLE `tb_user_like` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `article_id` bigint NOT NULL COMMENT '文章ID',
  `deleted` tinyint DEFAULT '0' COMMENT '逻辑删除：0-未删除，1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_article` (`user_id`, `article_id`),
  KEY `idx_article_id` (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户点赞表';

-- 用户浏览记录表
CREATE TABLE `tb_user_browse` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `article_id` bigint NOT NULL COMMENT '文章ID',
  `deleted` tinyint DEFAULT '0' COMMENT '逻辑删除：0-未删除，1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_article_id` (`article_id`),
  KEY `idx_update_time` (`update_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户浏览记录表';

-- 用户反馈表
CREATE TABLE `tb_feedback` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `content` text COMMENT '反馈内容',
  `images` varchar(2000) DEFAULT NULL COMMENT '反馈图片（多张图片用逗号分隔）',
  `contact` varchar(100) DEFAULT NULL COMMENT '联系方式',
  `status` tinyint DEFAULT '0' COMMENT '处理状态：0-待处理，1-已处理',
  `handle_user_id` bigint DEFAULT NULL COMMENT '处理人ID',
  `handle_time` datetime DEFAULT NULL COMMENT '处理时间',
  `handle_remark` varchar(500) DEFAULT NULL COMMENT '处理备注',
  `deleted` tinyint DEFAULT '0' COMMENT '逻辑删除：0-未删除，1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户反馈表';
