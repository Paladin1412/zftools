drop database if exists zftools;

create database zftools;
use zftools;



/*==============================================================*/
/* Table: tb_user                                               */
/*==============================================================*/
create table tb_user
(
   id                   int unsigned AUTO_INCREMENT unique comment '序号',
   uid                  varchar(40) not null unique comment '用户编号',
   username             varchar(40) not null unique comment '用户名称',
   password             varchar(40) not null comment '用户密码',
   department_id        varchar(40) default null comment '部门编号',
   primary key (uid)
)engine=innodb charset=utf8;

-- 清掉表里数据
delete from tb_user;
-- 重置自增长id：id = 1
alter table tb_user AUTO_INCREMENT = 1;

-- 添加测试数据
insert into tb_user(uid, username, password, department_id) values ('test','test', 'qwerty', 'test');



/*==============================================================*/
/* Table: tb_department                                         */
/*==============================================================*/
create table tb_department
(
   id                   int unsigned AUTO_INCREMENT unique comment '序号',
   department_id        varchar(40) not null unique comment '部门编号',
   department_name      varchar(40) not null comment '部门名称',
   primary key (department_id)
)engine=innodb charset=utf8;

-- 清掉表里数据
delete from tb_department;
-- 重置自增长id：id = 1
alter table tb_department AUTO_INCREMENT = 1;



/*==============================================================*/
/* Table: tb_sys                                                */
/*==============================================================*/
create table tb_sys
(
   id                   int unsigned AUTO_INCREMENT unique comment '系统编号',
   sys_name             varchar(40) not null comment '系统名称',
   t_sys_address        varchar(80) not null comment '系统域名（测试）',
   t_sys_ip             varchar(40) not null comment '系统ip地址（测试）',
   q_sys_address        varchar(80) not null comment '系统域名（准生产）',
   q_sys_ip             varchar(40) not null comment '系统ip地址（准生产）',
   db_id                int unsigned default null comment '系统关联数据库的id',
   primary key (id)
)engine=innodb charset=utf8;

-- 清掉表里数据
delete from tb_sys;
-- 重置自增长id：id = 1
alter table tb_sys AUTO_INCREMENT = 1;

-- 添加测试数据
insert into tb_sys(sys_name, t_sys_address, t_sys_ip, q_sys_address, q_sys_ip, db_id) values ('财务系统', 'http://t.zf.ziroom.com', '127.0.0.1', 'http://q.zf.ziroom.com', '127.0.0.2', 1);
insert into tb_sys(sys_name, t_sys_address, t_sys_ip, q_sys_address, q_sys_ip, db_id) values ('金融系统', 'http://t.install.ziroom.com', '127.0.0.3', 'http://q.install.ziroom.com', '127.0.0.4', 2);
insert into tb_sys(sys_name, t_sys_address, t_sys_ip, q_sys_address, q_sys_ip, db_id) values ('信用系统', 'http://t.trust.ziroom.com', '127.0.0.5', 'http://q.trust.ziroom.com', '127.0.0.6', 3);
insert into tb_sys(sys_name, t_sys_address, t_sys_ip, q_sys_address, q_sys_ip, db_id) values ('账户系统', 'http://t.account.ziroom.com', '127.0.0.7', 'http://q.account.ziroom.com', '127.0.0.8', 4);



/*==============================================================*/
/* Table: tb_db                                                 */
/*==============================================================*/
create table tb_db
(
    id                  int unsigned AUTO_INCREMENT unique comment '数据库编号',
    db_category         varchar(40) not null comment '数据库种类',
    t_db_ip             varchar(40) not null comment '数据库ip地址（测试）',
    t_db_username       varchar(20) not null comment '数据库登录名（测试）',
    t_db_password       varchar(20) not null comment '数据库登录密码（测试）',
    q_db_ip             varchar(40) not null comment '数据库ip地址（准生产）',
    q_db_username       varchar(20) not null comment '数据库登录名（准生产）',
    q_db_password       varchar(20) not null comment '数据库登录密码（准生产）',
    sys_id              int unsigned default null comment '数据库关联系统的id',
    primary key (id)
)engine=innodb charset=utf8;

-- 清掉表里数据
delete from tb_db;
-- 重置自增长id：id = 1
alter table tb_db AUTO_INCREMENT=1;

-- 添加测试数据
insert into tb_db(db_category, t_db_ip, t_db_username, t_db_password, q_db_ip, q_db_username, q_db_password, sys_id) values ('MySQL', '128.0.0.1', 'root', '123456', '128.0.0.2', 'root', '123456', 1);
insert into tb_db(db_category, t_db_ip, t_db_username, t_db_password, q_db_ip, q_db_username, q_db_password, sys_id) values ('MySQL', '128.0.0.3', 'root', '123456', '128.0.0.4', 'root', '123456', 2);
insert into tb_db(db_category, t_db_ip, t_db_username, t_db_password, q_db_ip, q_db_username, q_db_password, sys_id) values ('Oracle', '128.0.0.5', 'root', '123456', '128.0.0.6', 'root', '123456', 3);
insert into tb_db(db_category, t_db_ip, t_db_username, t_db_password, q_db_ip, q_db_username, q_db_password, sys_id) values ('Oracle', '128.0.0.7', 'root', '123456', '128.0.0.8', 'root', '123456', 4);
