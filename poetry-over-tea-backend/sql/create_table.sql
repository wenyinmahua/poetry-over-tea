create database if not exists poetry_over_tea;
use poetry_over_tea;

drop table if exists user;
-- 用户表
CREATE TABLE if not exists user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '用户ID，自增主键',
    username VARCHAR(255) NOT NULL COMMENT '用户昵称',
    account VARCHAR(255) NOT NULL UNIQUE COMMENT '用户账号',
    password VARCHAR(255) NOT NULL COMMENT '用户密码',
    avatar_url VARCHAR(1024) COMMENT '用户头像URL',
    profile varchar(513) NULL COMMENT '用户简历',
    role tinyint unsigned DEFAULT 0 COMMENT '用户角色，0-普通用户 1-管理员',
    status tinyint unsigned DEFAULT 0 COMMENT '用户状态，0-正常，1-禁用',
    longitude decimal(9, 6) default 0.000000 null comment '经度',
    latitude decimal(9, 6) default 0.000000 null comment '纬度',
    balance INT NULL DEFAULT 0 COMMENT '账户余额',
    created_time DATETIME default CURRENT_TIMESTAMP NOT NULL COMMENT '账户创建时间',
    updated_time DATETIME default CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '信息最后更新时间',
    is_delete tinyint  unsigned default 0  not null comment '是否删除 0-未删除，1-删除 '
    ) COMMENT='存储用户基本信息';

drop table if exists poem;
-- 古诗词表
CREATE TABLE if not exists poem (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '古诗 ID，自增主键',
    title VARCHAR(255) NOT NULL COMMENT '古诗题目',
    poet_id TINYINT COMMENT '诗人 ID，关联 poet 表',
    content TEXT NOT NULL COMMENT '古诗正文内容',
    category_id BIGINT COMMENT '古诗分类 Id',
    translation TEXT COMMENT '古诗译文',
    appreciation TEXT COMMENT '古诗赏析内容',
    thumb_num   int      default 0                 not null comment '点赞数',
    favour_num  int      default 0                 not null comment '收藏数'
)COMMENT='存储古诗详细信息';

drop table if exists poet;
-- 诗人表
CREATE TABLE if not exists poet (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '诗人 ID，自增主键',
    avatar_url VARCHAR(255) COMMENT '诗人画像URL',
    name VARCHAR(255) NOT NULL COMMENT '诗人姓名',
    dynasty_id BIGINT COMMENT '诗人所在朝代 Id',
    profile TEXT COMMENT '作者简介',
    thumb_num   int      default 0                 not null comment '点赞数',
    favour_num  int      default 0                 not null comment '收藏数',
    index idx_dynasty_id(dynasty_id)
)COMMENT='存储作者基本信息';

-- 诗词分类表
drop table if exists category;
CREATE TABLE category (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '分类ID，自增主键',
    name VARCHAR(255) UNIQUE COMMENT '分类名'
)COMMENT='存储诗词分类基本信息';

-- 朝代表
drop table if exists dynasty;
CREATE TABLE dynasty (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '朝代ID，自增主键',
    name VARCHAR(255) UNIQUE COMMENT '朝代名'
)COMMENT='存储诗人朝代基本信息';


-- 古诗点赞表
drop table if exists poem_thumb;
create table if not exists poem_thumb(
    id         bigint auto_increment comment 'id' primary key,
    poem_id     bigint                             not null comment '诗词 id',
    user_id     bigint                             not null comment '创建用户 id',
    index idx_poem_id (poem_id),
    index idx_user_id (user_id)
) comment '存储用户-古诗点赞基本信息';

-- 古诗收藏表
drop table if exists poem_favor;
create table if not exists poem_favor(
    id         bigint auto_increment comment 'id' primary key,
    poem_id     bigint                             not null comment '诗词 id',
    user_id     bigint                             not null comment '创建用户 id',
    index idx_poem_id (poem_id),
    index idx_user_id (user_id)
) comment '存储用户-古诗收藏基本信息';

-- 用户-诗人点赞表
drop table if exists poet_thumb;
create table if not exists poet_thumb(
    id         bigint auto_increment comment 'id' primary key,
    poet_id     bigint                             not null comment '诗词 id',
    user_id     bigint                             not null comment '创建用户 id',
    index idx_poet_id (poet_id),
    index idx_user_id (user_id)
) comment '存储用户-诗人点赞基本信息';


-- 用户-诗人收藏表
drop table if exists poet_favor;
create table if not exists poet_favor
(
    id         bigint auto_increment comment 'id' primary key,
    poet_id     bigint                             not null comment '诗词 id',
    user_id     bigint                             not null comment '创建用户 id',
    index idx_poet_id (poet_id),
    index idx_user_id (user_id)
) comment '存储用户-诗人收藏基本信息';



-- 评论表


-- 字帖秒杀表
