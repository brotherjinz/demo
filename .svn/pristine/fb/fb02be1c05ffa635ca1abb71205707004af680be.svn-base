prompt PL/SQL Developer import file
prompt Created on 2018年9月12日 by Administrator
set feedback off
set define off
prompt Creating CONFIG...
create table CONFIG
(
  CONFIGID    VARCHAR2(255) default SYS_GUID() not null,
  PATHFOLDER  VARCHAR2(255),
  PATHTEMP    VARCHAR2(255),
  PATHURL     VARCHAR2(255),
  STATIONCODE VARCHAR2(255)
)
tablespace SYNC_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on column CONFIG.CONFIGID
  is 'id';
comment on column CONFIG.PATHFOLDER
  is '图片文件夹';
comment on column CONFIG.PATHTEMP
  is '临时文件夹';
comment on column CONFIG.PATHURL
  is 'http图片接口地址';
comment on column CONFIG.STATIONCODE
  is '探测站编号';
alter table CONFIG
  add primary key (CONFIGID)
  using index 
  tablespace SYNC_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating SCHEDULE_RECORD...
create table SCHEDULE_RECORD
(
  FAULT_SERIAL   VARCHAR2(255) not null,
  DATA_STATE     NUMBER(10),
  IMG_STATE      NUMBER(10),
  SCHEDULE_TIME  DATE default sysdate,
  STATE          NUMBER(10),
  STATIONCODE    VARCHAR2(255),
  TRAIN_SERIAL   VARCHAR2(255),
  VEHICLE_SERIAL VARCHAR2(255)
)
tablespace SYNC_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on column SCHEDULE_RECORD.FAULT_SERIAL
  is '同步数据id';
comment on column SCHEDULE_RECORD.DATA_STATE
  is '关联数据同步状态 0 未同步   1 已同步';
comment on column SCHEDULE_RECORD.IMG_STATE
  is '图片数据同步状态 0 未同步   1 已同步';
comment on column SCHEDULE_RECORD.SCHEDULE_TIME
  is '数据同步时间';
comment on column SCHEDULE_RECORD.STATE
  is '数据状态0 自动同步过来的数据   1 手动同步过来的数据';
alter table SCHEDULE_RECORD
  add primary key (FAULT_SERIAL)
  using index 
  tablespace SYNC_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating TASK_SCHEDULE...
create table TASK_SCHEDULE
(
  SCHEDULEJOBID  VARCHAR2(255) not null,
  ALIASNAME      VARCHAR2(255),
  CREATETIME     DATE,
  CRONEXPRESSION VARCHAR2(255),
  DESCRIPTION    VARCHAR2(255),
  JOBCLASS       VARCHAR2(255),
  JOBGROUP       VARCHAR2(255),
  JOBNAME        VARCHAR2(255),
  STATUS         NUMBER(10),
  UPDATETIME     DATE
)
tablespace SYNC_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column TASK_SCHEDULE.SCHEDULEJOBID
  is 'uuid';
comment on column TASK_SCHEDULE.ALIASNAME
  is '任务别名';
comment on column TASK_SCHEDULE.CREATETIME
  is '创建时间';
comment on column TASK_SCHEDULE.CRONEXPRESSION
  is '任务运行时间表达式';
comment on column TASK_SCHEDULE.DESCRIPTION
  is '任务描述';
comment on column TASK_SCHEDULE.JOBCLASS
  is '指定执行类';
comment on column TASK_SCHEDULE.JOBGROUP
  is '任务分组 ';
comment on column TASK_SCHEDULE.JOBNAME
  is '任务名称';
comment on column TASK_SCHEDULE.STATUS
  is '任务状态 0停用 1启用 2删除';
comment on column TASK_SCHEDULE.UPDATETIME
  is '修改时间';
alter table TASK_SCHEDULE
  add primary key (SCHEDULEJOBID)
  using index 
  tablespace SYNC_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating TASK_SCHEDULE_JD...
create table TASK_SCHEDULE_JD
(
  JD_ID             VARCHAR2(255) default SYS_GUID() not null,
  COLUMNNAME        VARCHAR2(255),
  COLUMNVALUE_START VARCHAR2(255),
  SCHEDULEORDER     NUMBER(10),
  SCHEDULETIME      DATE,
  TABLENAME         VARCHAR2(255),
  COLUMNVALUE_END   VARCHAR2(255),
  STATIONCODE       VARCHAR2(255)
)
tablespace SYNC_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on column TASK_SCHEDULE_JD.JD_ID
  is '节点id';
comment on column TASK_SCHEDULE_JD.COLUMNNAME
  is '同步表列名';
comment on column TASK_SCHEDULE_JD.COLUMNVALUE_START
  is '同步表开始节点';
comment on column TASK_SCHEDULE_JD.SCHEDULEORDER
  is '排序';
comment on column TASK_SCHEDULE_JD.SCHEDULETIME
  is '同步时间';
comment on column TASK_SCHEDULE_JD.TABLENAME
  is '同步表名';
comment on column TASK_SCHEDULE_JD.COLUMNVALUE_END
  is '同步表结束节点';
alter table TASK_SCHEDULE_JD
  add constraint ID primary key (JD_ID)
  using index 
  tablespace SYNC_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating TD_DATABASE...
create table TD_DATABASE
(
  DATAID              VARCHAR2(255) default SYS_GUID() not null,
  C_DATA_TYPE         NUMBER(10),
  C_DRIVER_CLASS_NAME VARCHAR2(255),
  C_JNDI_NAME         VARCHAR2(255),
  C_NAME              VARCHAR2(255),
  C_PASSWORD          VARCHAR2(255),
  C_TYPE              NUMBER(10),
  C_URL               VARCHAR2(255),
  C_USER_NAME         VARCHAR2(255)
)
tablespace SYNC_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on column TD_DATABASE.DATAID
  is '主键id';
comment on column TD_DATABASE.C_DATA_TYPE
  is '数据源类型';
comment on column TD_DATABASE.C_DRIVER_CLASS_NAME
  is '驱动';
comment on column TD_DATABASE.C_JNDI_NAME
  is '数据源名称';
comment on column TD_DATABASE.C_PASSWORD
  is '数据库登陆密码';
comment on column TD_DATABASE.C_TYPE
  is '数据库类型';
comment on column TD_DATABASE.C_URL
  is '数据库连接url';
comment on column TD_DATABASE.C_USER_NAME
  is '数据库登录名';
alter table TD_DATABASE
  add primary key (DATAID)
  using index 
  tablespace SYNC_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating TD_MENU...
create table TD_MENU
(
  MENU_ID       NUMBER(10) not null,
  CREATE_TIME   DATE,
  IS_DELETED    VARCHAR2(255),
  MENU_ICO_URL  VARCHAR2(255),
  MENU_NAME     VARCHAR2(255),
  MENU_ORDER_BY NUMBER(10),
  MENU_URL      VARCHAR2(255),
  P_MENU_ID     VARCHAR2(255)
)
tablespace SYNC_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table TD_MENU
  add primary key (MENU_ID)
  using index 
  tablespace SYNC_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating TF_CFG_DETECTSTATION...
create table TF_CFG_DETECTSTATION
(
  STATION_SERIAL VARCHAR2(40) not null,
  STATION_ID     VARCHAR2(40),
  STATION_NAME   VARCHAR2(100),
  DIRECTION      VARCHAR2(10),
  SERVER_BASE    VARCHAR2(40),
  LOCAL_BASE     VARCHAR2(40),
  IMAGE_IP       VARCHAR2(40),
  IMAGE_PORT     NUMBER(5),
  IMAGE_POLICY   VARCHAR2(123),
  IMAGE_TIMEOUT  NUMBER(5),
  IMAGE_USER     VARCHAR2(40),
  IMAGE_PWD      VARCHAR2(40),
  NAME_PREFIX    VARCHAR2(20),
  NAME_SPLIT     VARCHAR2(20),
  PAD_ZEROS      NUMBER(2),
  NAME_PATTERN   VARCHAR2(100),
  STATION_LID    VARCHAR2(20),
  FACTORY_NAME   VARCHAR2(100),
  REMOTE_IP      VARCHAR2(40),
  REMOTE_PORT    VARCHAR2(40),
  REMOTE_WEBNAME VARCHAR2(40),
  REMOTE_URL     VARCHAR2(100),
  VIEW_MODE      VARCHAR2(1) default 0,
  IS_COUNTDOWN   VARCHAR2(1) default 0,
  COUNTDOWN_TIME NUMBER(9) default 3,
  IS_SIDEMID     VARCHAR2(1) default 1
)
tablespace SYNC_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column TF_CFG_DETECTSTATION.STATION_SERIAL
  is '配置流水号';
comment on column TF_CFG_DETECTSTATION.STATION_ID
  is '探测站编码';
comment on column TF_CFG_DETECTSTATION.STATION_NAME
  is '探测站名称';
comment on column TF_CFG_DETECTSTATION.DIRECTION
  is '行车方向';
comment on column TF_CFG_DETECTSTATION.SERVER_BASE
  is '服务器存储位置';
comment on column TF_CFG_DETECTSTATION.LOCAL_BASE
  is '本地存储位置';
comment on column TF_CFG_DETECTSTATION.IMAGE_IP
  is '加速器IP';
comment on column TF_CFG_DETECTSTATION.IMAGE_PORT
  is '加速器端口';
comment on column TF_CFG_DETECTSTATION.IMAGE_POLICY
  is '取图策略:local/rmi/jtvaccel/hxaccel/IFfImageReader...';
comment on column TF_CFG_DETECTSTATION.IMAGE_TIMEOUT
  is '取图超时ms';
comment on column TF_CFG_DETECTSTATION.IMAGE_USER
  is '取图用户名';
comment on column TF_CFG_DETECTSTATION.IMAGE_PWD
  is '取图密码';
comment on column TF_CFG_DETECTSTATION.NAME_PREFIX
  is '文件名前缀';
comment on column TF_CFG_DETECTSTATION.NAME_SPLIT
  is '文件名中的分隔符';
comment on column TF_CFG_DETECTSTATION.PAD_ZEROS
  is '文件名中序号补零个数';
comment on column TF_CFG_DETECTSTATION.NAME_PATTERN
  is '命名规则 TEDS/TFDS/具体的定义类';
comment on column TF_CFG_DETECTSTATION.STATION_LID
  is '所属列检所';
comment on column TF_CFG_DETECTSTATION.FACTORY_NAME
  is '设备厂家';
comment on column TF_CFG_DETECTSTATION.REMOTE_IP
  is '联网软件远程看图IP(本地服务器IP)';
comment on column TF_CFG_DETECTSTATION.REMOTE_PORT
  is '联网软件远程看图端口(本地服务器端口)';
comment on column TF_CFG_DETECTSTATION.REMOTE_WEBNAME
  is '工程名';
comment on column TF_CFG_DETECTSTATION.REMOTE_URL
  is '看图路径';
comment on column TF_CFG_DETECTSTATION.VIEW_MODE
  is '看图模式：0-面阵 1-线阵';
comment on column TF_CFG_DETECTSTATION.IS_COUNTDOWN
  is '是否启用倒计时 0-不启用 1-启用';
comment on column TF_CFG_DETECTSTATION.COUNTDOWN_TIME
  is '倒计时间隔';
comment on column TF_CFG_DETECTSTATION.IS_SIDEMID
  is '是否查看侧部中间';

prompt Disabling triggers for CONFIG...
alter table CONFIG disable all triggers;
prompt Disabling triggers for SCHEDULE_RECORD...
alter table SCHEDULE_RECORD disable all triggers;
prompt Disabling triggers for TASK_SCHEDULE...
alter table TASK_SCHEDULE disable all triggers;
prompt Disabling triggers for TASK_SCHEDULE_JD...
alter table TASK_SCHEDULE_JD disable all triggers;
prompt Disabling triggers for TD_DATABASE...
alter table TD_DATABASE disable all triggers;
prompt Disabling triggers for TD_MENU...
alter table TD_MENU disable all triggers;
prompt Disabling triggers for TF_CFG_DETECTSTATION...
alter table TF_CFG_DETECTSTATION disable all triggers;
prompt Loading CONFIG...
insert into CONFIG (CONFIGID, PATHFOLDER, PATHTEMP, PATHURL, STATIONCODE)
values ('06B41D8F32D9489494FD41363CF4D044', 'D:\img\', 'D:\temp\', 'http://10.97.134.132:8278/', 'F38F02F01');
insert into CONFIG (CONFIGID, PATHFOLDER, PATHTEMP, PATHURL, STATIONCODE)
values ('8a415985657fed83016583327ef50009', 'D:\img\', 'D:\temp\', 'http://10.193.217.7:8278/', 'W76F02F01');
insert into CONFIG (CONFIGID, PATHFOLDER, PATHTEMP, PATHURL, STATIONCODE)
values ('8a4159856583a81201658418e7e70003', 'D:\img\', 'D:\temp\', 'http://10.193.217.11:8278/', 'W76F03F01');
insert into CONFIG (CONFIGID, PATHFOLDER, PATHTEMP, PATHURL, STATIONCODE)
values ('8a4159856583a8120165841adc5e0004', 'D:\img\', 'D:\temp\', 'http://10.193.217.12:8278/', 'W76F10F01');
insert into CONFIG (CONFIGID, PATHFOLDER, PATHTEMP, PATHURL, STATIONCODE)
values ('8a4159856583a8120165841f68770006', 'D:\img\', 'D:\temp\', 'http://10.193.217.10:8278/', 'W76F04F01');
insert into CONFIG (CONFIGID, PATHFOLDER, PATHTEMP, PATHURL, STATIONCODE)
values ('8a4159856583a81201658423b5320009', 'D:\img\', 'D:\temp\', 'http://10.193.217.8:8278/', 'W76F01F01');
insert into CONFIG (CONFIGID, PATHFOLDER, PATHTEMP, PATHURL, STATIONCODE)
values ('8a4159856583a812016584254244000a', 'D:\img\', 'D:\temp\', 'http://10.193.217.99:8278/', 'W76F16F01');
insert into CONFIG (CONFIGID, PATHFOLDER, PATHTEMP, PATHURL, STATIONCODE)
values ('8a4159856583a81201658428cee3000d', 'D:\img\', 'D:\temp\', 'http://10.193.217.98:8278/', 'W76F15F01');
insert into CONFIG (CONFIGID, PATHFOLDER, PATHTEMP, PATHURL, STATIONCODE)
values ('8a4159856583a81201658429d66b000e', 'D:\img\', 'D:\temp\', 'http://10.193.217.67:8278/', 'W76F11F02');
insert into CONFIG (CONFIGID, PATHFOLDER, PATHTEMP, PATHURL, STATIONCODE)
values ('8a4159856588b8d101658ed63b660000', 'E:\img\', 'E:\temp\', 'http://10.193.217.36:8278/', 'W76F05F01');
insert into CONFIG (CONFIGID, PATHFOLDER, PATHTEMP, PATHURL, STATIONCODE)
values ('8a4159856588b8d101658edaaadb0004', 'E:\temp\', 'E:\temp\', 'http://10.193.217.37:8278/', 'W76F14F01');
insert into CONFIG (CONFIGID, PATHFOLDER, PATHTEMP, PATHURL, STATIONCODE)
values ('8a4159856588b8d101658edbd8970005', 'E:\img\', 'E:\temp\', 'http://10.193.217.38:8278/', 'W76F08F01');
insert into CONFIG (CONFIGID, PATHFOLDER, PATHTEMP, PATHURL, STATIONCODE)
values ('8a4159856588b8d101658ee02bba000a', 'E:\img\', 'E:\temp\', 'http://10.193.217.9:8278/', 'W76F09F01');
insert into CONFIG (CONFIGID, PATHFOLDER, PATHTEMP, PATHURL, STATIONCODE)
values ('8a4159856588b8d101658ee3ec4a000d', 'E:\img\', 'E:\temp\', 'http://10.193.217.68:8278/', 'W76F12F01');
insert into CONFIG (CONFIGID, PATHFOLDER, PATHTEMP, PATHURL, STATIONCODE)
values ('8a4159856588b8d101658ee50138000e', 'E:\img\', 'E:\temp\', 'http://10.193.217.58:8278/', 'W76F19F01');
commit;
prompt 15 records loaded
prompt Loading SCHEDULE_RECORD...
prompt Table is empty
prompt Loading TASK_SCHEDULE...
insert into TASK_SCHEDULE (SCHEDULEJOBID, ALIASNAME, CREATETIME, CRONEXPRESSION, DESCRIPTION, JOBCLASS, JOBGROUP, JOBNAME, STATUS, UPDATETIME)
values ('030d6dc1-5a41-5bcc-aefd-00d757b021ae', '同步故障数据', to_date('12-08-2018', 'dd-mm-yyyy'), '*/5 * * * * ?', '每5秒同步一次故障的数据', 'com.web.info.job.TrainFailureDataJob', '同步任务', '同步表数据', 0, to_date('17-08-2018 16:07:39', 'dd-mm-yyyy hh24:mi:ss'));
insert into TASK_SCHEDULE (SCHEDULEJOBID, ALIASNAME, CREATETIME, CRONEXPRESSION, DESCRIPTION, JOBCLASS, JOBGROUP, JOBNAME, STATUS, UPDATETIME)
values ('030d6dc1-5a41-5bcc-aefd-00d757b021ad', '自动后台远程取图', to_date('21-08-2018 00:00:01', 'dd-mm-yyyy hh24:mi:ss'), '*/5 * * * * ?', '每5秒扫描一次数据是否需要取图', 'com.web.info.job.ImageDataJob', '远端取图', '取图片数据', 0, to_date('21-08-2018 12:00:00', 'dd-mm-yyyy hh24:mi:ss'));
commit;
prompt 2 records loaded
prompt Loading TASK_SCHEDULE_JD...
prompt Table is empty
prompt Loading TD_DATABASE...
insert into TD_DATABASE (DATAID, C_DATA_TYPE, C_DRIVER_CLASS_NAME, C_JNDI_NAME, C_NAME, C_PASSWORD, C_TYPE, C_URL, C_USER_NAME)
values ('7CFAAFF97D684DF1AF1B47561C6520D1', 1, 'oracle.jdbc.driver.OracleDriver', 'W76F02F01', 'W76F02F01', 'jtv312jtw', 1, 'jdbc:oracle:thin:@10.193.217.7:1525/tfds', 'tfds');
insert into TD_DATABASE (DATAID, C_DATA_TYPE, C_DRIVER_CLASS_NAME, C_JNDI_NAME, C_NAME, C_PASSWORD, C_TYPE, C_URL, C_USER_NAME)
values ('610D488878D1470091ED3E17FE5B58C5', 1, 'oracle.jdbc.driver.OracleDriver', 'W76F03F01', 'W76F03F01', 'jtv312jtw', 1, 'jdbc:oracle:thin:@10.193.217.11:1525/tfds', 'tfds');
insert into TD_DATABASE (DATAID, C_DATA_TYPE, C_DRIVER_CLASS_NAME, C_JNDI_NAME, C_NAME, C_PASSWORD, C_TYPE, C_URL, C_USER_NAME)
values ('BD6CCEAA3D3D40DFB51F6FBAF6E55727', 1, 'oracle.jdbc.driver.OracleDriver', 'W76F10F01', 'W76F10F01', 'jtv312jtw', 1, 'jdbc:oracle:thin:@10.193.217.12:1525/tfds', 'tfds');
insert into TD_DATABASE (DATAID, C_DATA_TYPE, C_DRIVER_CLASS_NAME, C_JNDI_NAME, C_NAME, C_PASSWORD, C_TYPE, C_URL, C_USER_NAME)
values ('0387FCFAD3B54E58BA98924725778DB2', 1, 'oracle.jdbc.driver.OracleDriver', 'W76F04F01', 'W76F04F01', 'jtv312jtw', 1, 'jdbc:oracle:thin:@10.193.217.10:1525/tfds', 'tfds');
insert into TD_DATABASE (DATAID, C_DATA_TYPE, C_DRIVER_CLASS_NAME, C_JNDI_NAME, C_NAME, C_PASSWORD, C_TYPE, C_URL, C_USER_NAME)
values ('854625C7CB3244C482FB7302FF1EDBBF', 1, 'oracle.jdbc.driver.OracleDriver', 'W76F09F01', 'W76F09F01', 'jtv312jtw', 1, 'jdbc:oracle:thin:@10.193.217.9:1525/tfds', 'tfds');
insert into TD_DATABASE (DATAID, C_DATA_TYPE, C_DRIVER_CLASS_NAME, C_JNDI_NAME, C_NAME, C_PASSWORD, C_TYPE, C_URL, C_USER_NAME)
values ('2A63EFB235E74413A73636D362EF21C6', 1, 'oracle.jdbc.driver.OracleDriver', 'W76F01F01', 'W76F01F01', 'jtv312jtw', 1, 'jdbc:oracle:thin:@10.193.217.8:1525/tfds', 'tfds');
insert into TD_DATABASE (DATAID, C_DATA_TYPE, C_DRIVER_CLASS_NAME, C_JNDI_NAME, C_NAME, C_PASSWORD, C_TYPE, C_URL, C_USER_NAME)
values ('82FC081CDD4F42A5AD269BE39EB9DEA4', 1, 'oracle.jdbc.driver.OracleDriver', 'W76F16F01', 'W76F16F01', 'jtv312jtw', 1, 'jdbc:oracle:thin:@10.193.217.99:1525/tfds', 'tfds');
insert into TD_DATABASE (DATAID, C_DATA_TYPE, C_DRIVER_CLASS_NAME, C_JNDI_NAME, C_NAME, C_PASSWORD, C_TYPE, C_URL, C_USER_NAME)
values ('23F08FEA24AF40E6A2FB59F474493DF1', 1, 'oracle.jdbc.driver.OracleDriver', 'W76F15F01', 'W76F15F01', 'jtv312jtw', 1, 'jdbc:oracle:thin:@10.193.217.98:1525/tfds', 'tfds');
insert into TD_DATABASE (DATAID, C_DATA_TYPE, C_DRIVER_CLASS_NAME, C_JNDI_NAME, C_NAME, C_PASSWORD, C_TYPE, C_URL, C_USER_NAME)
values ('6BD36362B92441DC931692F46C1A45CB', 1, 'oracle.jdbc.driver.OracleDriver', 'W76F11F02', 'W76F11F02', 'jtv312jtw', 1, 'jdbc:oracle:thin:@10.193.217.67:1525/tfds', 'tfds');
insert into TD_DATABASE (DATAID, C_DATA_TYPE, C_DRIVER_CLASS_NAME, C_JNDI_NAME, C_NAME, C_PASSWORD, C_TYPE, C_URL, C_USER_NAME)
values ('095015B516A244CAB61D6EE030B07369', 1, 'oracle.jdbc.driver.OracleDriver', 'W76F12F01', 'W76F12F01', 'jtv312jtw', 1, 'jdbc:oracle:thin:@10.193.217.68:1525/tfds', 'tfds');
insert into TD_DATABASE (DATAID, C_DATA_TYPE, C_DRIVER_CLASS_NAME, C_JNDI_NAME, C_NAME, C_PASSWORD, C_TYPE, C_URL, C_USER_NAME)
values ('08B5C728407E4C11BC670702F7B56FE3', 1, 'oracle.jdbc.driver.OracleDriver', 'W76F19F01', 'W76F19F01', 'jtv312jtw', 1, 'jdbc:oracle:thin:@10.193.217.58:1525/tfds', 'tfds');
insert into TD_DATABASE (DATAID, C_DATA_TYPE, C_DRIVER_CLASS_NAME, C_JNDI_NAME, C_NAME, C_PASSWORD, C_TYPE, C_URL, C_USER_NAME)
values ('8a415985658ef07001658ef7b3910000', 1, 'oracle.jdbc.driver.OracleDriver', 'W76F05F01', 'W76F05F01', 'jtv312jtw', 1, 'jdbc:oracle:thin:@10.193.217.36:1525:tfds', 'tfds');
insert into TD_DATABASE (DATAID, C_DATA_TYPE, C_DRIVER_CLASS_NAME, C_JNDI_NAME, C_NAME, C_PASSWORD, C_TYPE, C_URL, C_USER_NAME)
values ('8a415985658ef07001658ef985190002', 1, 'oracle.jdbc.driver.OracleDriver', 'W76F08F01', 'W76F08F01', 'jtv312jtw', 1, 'jdbc:oracle:thin:@10.193.217.38:1525:tfds', 'tfds');
insert into TD_DATABASE (DATAID, C_DATA_TYPE, C_DRIVER_CLASS_NAME, C_JNDI_NAME, C_NAME, C_PASSWORD, C_TYPE, C_URL, C_USER_NAME)
values ('8a415985658ef07001658ef8677e0001', 1, 'oracle.jdbc.driver.OracleDriver', 'W76F14F01', 'W76F14F01', 'jtv312jtw', 1, 'jdbc:oracle:thin:@10.193.217.37:1525:tfds', 'tfds');
commit;
prompt 14 records loaded
prompt Loading TD_MENU...
insert into TD_MENU (MENU_ID, CREATE_TIME, IS_DELETED, MENU_ICO_URL, MENU_NAME, MENU_ORDER_BY, MENU_URL, P_MENU_ID)
values (8, to_date('28-08-2018 12:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'N', null, '文件路径', 7, 'Init/imgconfig', '6');
insert into TD_MENU (MENU_ID, CREATE_TIME, IS_DELETED, MENU_ICO_URL, MENU_NAME, MENU_ORDER_BY, MENU_URL, P_MENU_ID)
values (11, to_date('03-09-2018 12:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'N', null, '图形统计', 10, 'Init/echart', '9');
insert into TD_MENU (MENU_ID, CREATE_TIME, IS_DELETED, MENU_ICO_URL, MENU_NAME, MENU_ORDER_BY, MENU_URL, P_MENU_ID)
values (1, to_date('24-08-2018 12:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'N', 'menu-icon fa fa-list-alt', '数据管理', 0, null, '0');
insert into TD_MENU (MENU_ID, CREATE_TIME, IS_DELETED, MENU_ICO_URL, MENU_NAME, MENU_ORDER_BY, MENU_URL, P_MENU_ID)
values (2, to_date('24-08-2018 00:00:01', 'dd-mm-yyyy hh24:mi:ss'), 'N', null, '可同步数据', 1, 'Init/faultData', '1');
insert into TD_MENU (MENU_ID, CREATE_TIME, IS_DELETED, MENU_ICO_URL, MENU_NAME, MENU_ORDER_BY, MENU_URL, P_MENU_ID)
values (3, to_date('24-08-2018 12:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'N', null, '已同步数据', 2, 'Init/syncData', '1');
insert into TD_MENU (MENU_ID, CREATE_TIME, IS_DELETED, MENU_ICO_URL, MENU_NAME, MENU_ORDER_BY, MENU_URL, P_MENU_ID)
values (4, to_date('24-08-2018 00:00:59', 'dd-mm-yyyy hh24:mi:ss'), 'N', 'menu-icon fa fa-list', '任务管理', 3, null, '0');
insert into TD_MENU (MENU_ID, CREATE_TIME, IS_DELETED, MENU_ICO_URL, MENU_NAME, MENU_ORDER_BY, MENU_URL, P_MENU_ID)
values (5, to_date('24-08-2018 00:00:59', 'dd-mm-yyyy hh24:mi:ss'), 'N', null, '定时任务', 4, 'Init/initScheduleJob', '4');
insert into TD_MENU (MENU_ID, CREATE_TIME, IS_DELETED, MENU_ICO_URL, MENU_NAME, MENU_ORDER_BY, MENU_URL, P_MENU_ID)
values (12, to_date('07-09-2018 12:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'N', null, '车辆重编排', 11, 'Init/vehicleEditing', '1');
insert into TD_MENU (MENU_ID, CREATE_TIME, IS_DELETED, MENU_ICO_URL, MENU_NAME, MENU_ORDER_BY, MENU_URL, P_MENU_ID)
values (6, to_date('27-08-2018 00:00:01', 'dd-mm-yyyy hh24:mi:ss'), 'N', 'menu-icon fa fa-cog', '系统管理', 5, null, '0');
insert into TD_MENU (MENU_ID, CREATE_TIME, IS_DELETED, MENU_ICO_URL, MENU_NAME, MENU_ORDER_BY, MENU_URL, P_MENU_ID)
values (7, to_date('27-08-2018 12:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'N', null, '数据源管理', 6, 'Init/database', '6');
insert into TD_MENU (MENU_ID, CREATE_TIME, IS_DELETED, MENU_ICO_URL, MENU_NAME, MENU_ORDER_BY, MENU_URL, P_MENU_ID)
values (9, to_date('03-09-2018 12:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'N', 'menu-icon fa fa-tachometer', '统计分析', 8, null, '0');
insert into TD_MENU (MENU_ID, CREATE_TIME, IS_DELETED, MENU_ICO_URL, MENU_NAME, MENU_ORDER_BY, MENU_URL, P_MENU_ID)
values (10, to_date('03-09-2018 12:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'N', null, '数据查询', 9, 'Init/queryData', '9');
commit;
prompt 12 records loaded
prompt Loading TF_CFG_DETECTSTATION...
insert into TF_CFG_DETECTSTATION (STATION_SERIAL, STATION_ID, STATION_NAME, DIRECTION, SERVER_BASE, LOCAL_BASE, IMAGE_IP, IMAGE_PORT, IMAGE_POLICY, IMAGE_TIMEOUT, IMAGE_USER, IMAGE_PWD, NAME_PREFIX, NAME_SPLIT, PAD_ZEROS, NAME_PATTERN, STATION_LID, FACTORY_NAME, REMOTE_IP, REMOTE_PORT, REMOTE_WEBNAME, REMOTE_URL, VIEW_MODE, IS_COUNTDOWN, COUNTDOWN_TIME, IS_SIDEMID)
values ('1', 'W76F03F01', '宝成线成都北下行', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', '0', 3, '1');
insert into TF_CFG_DETECTSTATION (STATION_SERIAL, STATION_ID, STATION_NAME, DIRECTION, SERVER_BASE, LOCAL_BASE, IMAGE_IP, IMAGE_PORT, IMAGE_POLICY, IMAGE_TIMEOUT, IMAGE_USER, IMAGE_PWD, NAME_PREFIX, NAME_SPLIT, PAD_ZEROS, NAME_PATTERN, STATION_LID, FACTORY_NAME, REMOTE_IP, REMOTE_PORT, REMOTE_WEBNAME, REMOTE_URL, VIEW_MODE, IS_COUNTDOWN, COUNTDOWN_TIME, IS_SIDEMID)
values ('2', 'W76F10F01', '宝成线成都北上行', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', '0', 3, '1');
insert into TF_CFG_DETECTSTATION (STATION_SERIAL, STATION_ID, STATION_NAME, DIRECTION, SERVER_BASE, LOCAL_BASE, IMAGE_IP, IMAGE_PORT, IMAGE_POLICY, IMAGE_TIMEOUT, IMAGE_USER, IMAGE_PWD, NAME_PREFIX, NAME_SPLIT, PAD_ZEROS, NAME_PATTERN, STATION_LID, FACTORY_NAME, REMOTE_IP, REMOTE_PORT, REMOTE_WEBNAME, REMOTE_URL, VIEW_MODE, IS_COUNTDOWN, COUNTDOWN_TIME, IS_SIDEMID)
values ('3', 'W76F02F01', '成昆线成都北上行', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', '0', 3, '1');
insert into TF_CFG_DETECTSTATION (STATION_SERIAL, STATION_ID, STATION_NAME, DIRECTION, SERVER_BASE, LOCAL_BASE, IMAGE_IP, IMAGE_PORT, IMAGE_POLICY, IMAGE_TIMEOUT, IMAGE_USER, IMAGE_PWD, NAME_PREFIX, NAME_SPLIT, PAD_ZEROS, NAME_PATTERN, STATION_LID, FACTORY_NAME, REMOTE_IP, REMOTE_PORT, REMOTE_WEBNAME, REMOTE_URL, VIEW_MODE, IS_COUNTDOWN, COUNTDOWN_TIME, IS_SIDEMID)
values ('4', 'W76F14F01', '广巴线广元南上行', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', '0', 3, '1');
insert into TF_CFG_DETECTSTATION (STATION_SERIAL, STATION_ID, STATION_NAME, DIRECTION, SERVER_BASE, LOCAL_BASE, IMAGE_IP, IMAGE_PORT, IMAGE_POLICY, IMAGE_TIMEOUT, IMAGE_USER, IMAGE_PWD, NAME_PREFIX, NAME_SPLIT, PAD_ZEROS, NAME_PATTERN, STATION_LID, FACTORY_NAME, REMOTE_IP, REMOTE_PORT, REMOTE_WEBNAME, REMOTE_URL, VIEW_MODE, IS_COUNTDOWN, COUNTDOWN_TIME, IS_SIDEMID)
values ('5', 'W76F08F01', '广元下行', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', '0', 3, '1');
insert into TF_CFG_DETECTSTATION (STATION_SERIAL, STATION_ID, STATION_NAME, DIRECTION, SERVER_BASE, LOCAL_BASE, IMAGE_IP, IMAGE_PORT, IMAGE_POLICY, IMAGE_TIMEOUT, IMAGE_USER, IMAGE_PWD, NAME_PREFIX, NAME_SPLIT, PAD_ZEROS, NAME_PATTERN, STATION_LID, FACTORY_NAME, REMOTE_IP, REMOTE_PORT, REMOTE_WEBNAME, REMOTE_URL, VIEW_MODE, IS_COUNTDOWN, COUNTDOWN_TIME, IS_SIDEMID)
values ('6', 'W76F15F01', '渡口支线密地下行', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', '0', 3, '1');
insert into TF_CFG_DETECTSTATION (STATION_SERIAL, STATION_ID, STATION_NAME, DIRECTION, SERVER_BASE, LOCAL_BASE, IMAGE_IP, IMAGE_PORT, IMAGE_POLICY, IMAGE_TIMEOUT, IMAGE_USER, IMAGE_PWD, NAME_PREFIX, NAME_SPLIT, PAD_ZEROS, NAME_PATTERN, STATION_LID, FACTORY_NAME, REMOTE_IP, REMOTE_PORT, REMOTE_WEBNAME, REMOTE_URL, VIEW_MODE, IS_COUNTDOWN, COUNTDOWN_TIME, IS_SIDEMID)
values ('7', 'W76F04F01', '达成线成都北下行', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', '0', 3, '1');
insert into TF_CFG_DETECTSTATION (STATION_SERIAL, STATION_ID, STATION_NAME, DIRECTION, SERVER_BASE, LOCAL_BASE, IMAGE_IP, IMAGE_PORT, IMAGE_POLICY, IMAGE_TIMEOUT, IMAGE_USER, IMAGE_PWD, NAME_PREFIX, NAME_SPLIT, PAD_ZEROS, NAME_PATTERN, STATION_LID, FACTORY_NAME, REMOTE_IP, REMOTE_PORT, REMOTE_WEBNAME, REMOTE_URL, VIEW_MODE, IS_COUNTDOWN, COUNTDOWN_TIME, IS_SIDEMID)
values ('8', 'W76F09F01', '达成线成都北上行', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', '0', 3, '1');
insert into TF_CFG_DETECTSTATION (STATION_SERIAL, STATION_ID, STATION_NAME, DIRECTION, SERVER_BASE, LOCAL_BASE, IMAGE_IP, IMAGE_PORT, IMAGE_POLICY, IMAGE_TIMEOUT, IMAGE_USER, IMAGE_PWD, NAME_PREFIX, NAME_SPLIT, PAD_ZEROS, NAME_PATTERN, STATION_LID, FACTORY_NAME, REMOTE_IP, REMOTE_PORT, REMOTE_WEBNAME, REMOTE_URL, VIEW_MODE, IS_COUNTDOWN, COUNTDOWN_TIME, IS_SIDEMID)
values ('9', 'W76F01F01', '成渝线成都北上行', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', '0', 3, '1');
insert into TF_CFG_DETECTSTATION (STATION_SERIAL, STATION_ID, STATION_NAME, DIRECTION, SERVER_BASE, LOCAL_BASE, IMAGE_IP, IMAGE_PORT, IMAGE_POLICY, IMAGE_TIMEOUT, IMAGE_USER, IMAGE_PWD, NAME_PREFIX, NAME_SPLIT, PAD_ZEROS, NAME_PATTERN, STATION_LID, FACTORY_NAME, REMOTE_IP, REMOTE_PORT, REMOTE_WEBNAME, REMOTE_URL, VIEW_MODE, IS_COUNTDOWN, COUNTDOWN_TIME, IS_SIDEMID)
values ('10', 'W76F16F01', '渡口支线密地上行', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', '0', 3, '1');
insert into TF_CFG_DETECTSTATION (STATION_SERIAL, STATION_ID, STATION_NAME, DIRECTION, SERVER_BASE, LOCAL_BASE, IMAGE_IP, IMAGE_PORT, IMAGE_POLICY, IMAGE_TIMEOUT, IMAGE_USER, IMAGE_PWD, NAME_PREFIX, NAME_SPLIT, PAD_ZEROS, NAME_PATTERN, STATION_LID, FACTORY_NAME, REMOTE_IP, REMOTE_PORT, REMOTE_WEBNAME, REMOTE_URL, VIEW_MODE, IS_COUNTDOWN, COUNTDOWN_TIME, IS_SIDEMID)
values ('11', 'W76F18F01', '兰渝线广元西上行', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', '0', 3, '1');
insert into TF_CFG_DETECTSTATION (STATION_SERIAL, STATION_ID, STATION_NAME, DIRECTION, SERVER_BASE, LOCAL_BASE, IMAGE_IP, IMAGE_PORT, IMAGE_POLICY, IMAGE_TIMEOUT, IMAGE_USER, IMAGE_PWD, NAME_PREFIX, NAME_SPLIT, PAD_ZEROS, NAME_PATTERN, STATION_LID, FACTORY_NAME, REMOTE_IP, REMOTE_PORT, REMOTE_WEBNAME, REMOTE_URL, VIEW_MODE, IS_COUNTDOWN, COUNTDOWN_TIME, IS_SIDEMID)
values ('12', 'W76F12F01', '成昆线西昌南上行', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', '0', 3, '1');
insert into TF_CFG_DETECTSTATION (STATION_SERIAL, STATION_ID, STATION_NAME, DIRECTION, SERVER_BASE, LOCAL_BASE, IMAGE_IP, IMAGE_PORT, IMAGE_POLICY, IMAGE_TIMEOUT, IMAGE_USER, IMAGE_PWD, NAME_PREFIX, NAME_SPLIT, PAD_ZEROS, NAME_PATTERN, STATION_LID, FACTORY_NAME, REMOTE_IP, REMOTE_PORT, REMOTE_WEBNAME, REMOTE_URL, VIEW_MODE, IS_COUNTDOWN, COUNTDOWN_TIME, IS_SIDEMID)
values ('13', 'W76F11F02', '成昆线西昌南下行', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', '0', 3, '1');
insert into TF_CFG_DETECTSTATION (STATION_SERIAL, STATION_ID, STATION_NAME, DIRECTION, SERVER_BASE, LOCAL_BASE, IMAGE_IP, IMAGE_PORT, IMAGE_POLICY, IMAGE_TIMEOUT, IMAGE_USER, IMAGE_PWD, NAME_PREFIX, NAME_SPLIT, PAD_ZEROS, NAME_PATTERN, STATION_LID, FACTORY_NAME, REMOTE_IP, REMOTE_PORT, REMOTE_WEBNAME, REMOTE_URL, VIEW_MODE, IS_COUNTDOWN, COUNTDOWN_TIME, IS_SIDEMID)
values ('14', 'W76F05F01', '广元南上行', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', '0', 3, '1');
insert into TF_CFG_DETECTSTATION (STATION_SERIAL, STATION_ID, STATION_NAME, DIRECTION, SERVER_BASE, LOCAL_BASE, IMAGE_IP, IMAGE_PORT, IMAGE_POLICY, IMAGE_TIMEOUT, IMAGE_USER, IMAGE_PWD, NAME_PREFIX, NAME_SPLIT, PAD_ZEROS, NAME_PATTERN, STATION_LID, FACTORY_NAME, REMOTE_IP, REMOTE_PORT, REMOTE_WEBNAME, REMOTE_URL, VIEW_MODE, IS_COUNTDOWN, COUNTDOWN_TIME, IS_SIDEMID)
values ('15', 'W76F19F01', '兰渝线太公下行', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', '0', 3, '1');
commit;
prompt 15 records loaded
prompt Enabling triggers for CONFIG...
alter table CONFIG enable all triggers;
prompt Enabling triggers for SCHEDULE_RECORD...
alter table SCHEDULE_RECORD enable all triggers;
prompt Enabling triggers for TASK_SCHEDULE...
alter table TASK_SCHEDULE enable all triggers;
prompt Enabling triggers for TASK_SCHEDULE_JD...
alter table TASK_SCHEDULE_JD enable all triggers;
prompt Enabling triggers for TD_DATABASE...
alter table TD_DATABASE enable all triggers;
prompt Enabling triggers for TD_MENU...
alter table TD_MENU enable all triggers;
prompt Enabling triggers for TF_CFG_DETECTSTATION...
alter table TF_CFG_DETECTSTATION enable all triggers;
set feedback on
set define on
prompt Done.
