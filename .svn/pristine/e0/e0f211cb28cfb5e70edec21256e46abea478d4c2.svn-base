------------------------------------------------------
-- Export file for user SYNC                        --
-- Created by Administrator on 2018-09-12, 15:42:51 --
------------------------------------------------------

prompt
prompt Creating table CONFIG
prompt =====================
prompt
create table SYNC.CONFIG
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
comment on column SYNC.CONFIG.CONFIGID
  is 'id';
comment on column SYNC.CONFIG.PATHFOLDER
  is '图片文件夹';
comment on column SYNC.CONFIG.PATHTEMP
  is '临时文件夹';
comment on column SYNC.CONFIG.PATHURL
  is 'http图片接口地址';
comment on column SYNC.CONFIG.STATIONCODE
  is '探测站编号';
alter table SYNC.CONFIG
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

prompt
prompt Creating table SCHEDULE_RECORD
prompt ==============================
prompt
create table SYNC.SCHEDULE_RECORD
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
comment on column SYNC.SCHEDULE_RECORD.FAULT_SERIAL
  is '同步数据id';
comment on column SYNC.SCHEDULE_RECORD.DATA_STATE
  is '关联数据同步状态 0 未同步   1 已同步';
comment on column SYNC.SCHEDULE_RECORD.IMG_STATE
  is '图片数据同步状态 0 未同步   1 已同步';
comment on column SYNC.SCHEDULE_RECORD.SCHEDULE_TIME
  is '数据同步时间';
comment on column SYNC.SCHEDULE_RECORD.STATE
  is '数据状态0 自动同步过来的数据   1 手动同步过来的数据';
alter table SYNC.SCHEDULE_RECORD
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

prompt
prompt Creating table TASK_SCHEDULE
prompt ============================
prompt
create table SYNC.TASK_SCHEDULE
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
comment on column SYNC.TASK_SCHEDULE.SCHEDULEJOBID
  is 'uuid';
comment on column SYNC.TASK_SCHEDULE.ALIASNAME
  is '任务别名';
comment on column SYNC.TASK_SCHEDULE.CREATETIME
  is '创建时间';
comment on column SYNC.TASK_SCHEDULE.CRONEXPRESSION
  is '任务运行时间表达式';
comment on column SYNC.TASK_SCHEDULE.DESCRIPTION
  is '任务描述';
comment on column SYNC.TASK_SCHEDULE.JOBCLASS
  is '指定执行类';
comment on column SYNC.TASK_SCHEDULE.JOBGROUP
  is '任务分组 ';
comment on column SYNC.TASK_SCHEDULE.JOBNAME
  is '任务名称';
comment on column SYNC.TASK_SCHEDULE.STATUS
  is '任务状态 0停用 1启用 2删除';
comment on column SYNC.TASK_SCHEDULE.UPDATETIME
  is '修改时间';
alter table SYNC.TASK_SCHEDULE
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

prompt
prompt Creating table TASK_SCHEDULE_JD
prompt ===============================
prompt
create table SYNC.TASK_SCHEDULE_JD
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
comment on column SYNC.TASK_SCHEDULE_JD.JD_ID
  is '节点id';
comment on column SYNC.TASK_SCHEDULE_JD.COLUMNNAME
  is '同步表列名';
comment on column SYNC.TASK_SCHEDULE_JD.COLUMNVALUE_START
  is '同步表开始节点';
comment on column SYNC.TASK_SCHEDULE_JD.SCHEDULEORDER
  is '排序';
comment on column SYNC.TASK_SCHEDULE_JD.SCHEDULETIME
  is '同步时间';
comment on column SYNC.TASK_SCHEDULE_JD.TABLENAME
  is '同步表名';
comment on column SYNC.TASK_SCHEDULE_JD.COLUMNVALUE_END
  is '同步表结束节点';
alter table SYNC.TASK_SCHEDULE_JD
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

prompt
prompt Creating table TD_DATABASE
prompt ==========================
prompt
create table SYNC.TD_DATABASE
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
comment on column SYNC.TD_DATABASE.DATAID
  is '主键id';
comment on column SYNC.TD_DATABASE.C_DATA_TYPE
  is '数据源类型';
comment on column SYNC.TD_DATABASE.C_DRIVER_CLASS_NAME
  is '驱动';
comment on column SYNC.TD_DATABASE.C_JNDI_NAME
  is '数据源名称';
comment on column SYNC.TD_DATABASE.C_PASSWORD
  is '数据库登陆密码';
comment on column SYNC.TD_DATABASE.C_TYPE
  is '数据库类型';
comment on column SYNC.TD_DATABASE.C_URL
  is '数据库连接url';
comment on column SYNC.TD_DATABASE.C_USER_NAME
  is '数据库登录名';
alter table SYNC.TD_DATABASE
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

prompt
prompt Creating table TD_MENU
prompt ======================
prompt
create table SYNC.TD_MENU
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
alter table SYNC.TD_MENU
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

prompt
prompt Creating table TF_CFG_DETECTSTATION
prompt ===================================
prompt
create table SYNC.TF_CFG_DETECTSTATION
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
comment on column SYNC.TF_CFG_DETECTSTATION.STATION_SERIAL
  is '配置流水号';
comment on column SYNC.TF_CFG_DETECTSTATION.STATION_ID
  is '探测站编码';
comment on column SYNC.TF_CFG_DETECTSTATION.STATION_NAME
  is '探测站名称';
comment on column SYNC.TF_CFG_DETECTSTATION.DIRECTION
  is '行车方向';
comment on column SYNC.TF_CFG_DETECTSTATION.SERVER_BASE
  is '服务器存储位置';
comment on column SYNC.TF_CFG_DETECTSTATION.LOCAL_BASE
  is '本地存储位置';
comment on column SYNC.TF_CFG_DETECTSTATION.IMAGE_IP
  is '加速器IP';
comment on column SYNC.TF_CFG_DETECTSTATION.IMAGE_PORT
  is '加速器端口';
comment on column SYNC.TF_CFG_DETECTSTATION.IMAGE_POLICY
  is '取图策略:local/rmi/jtvaccel/hxaccel/IFfImageReader...';
comment on column SYNC.TF_CFG_DETECTSTATION.IMAGE_TIMEOUT
  is '取图超时ms';
comment on column SYNC.TF_CFG_DETECTSTATION.IMAGE_USER
  is '取图用户名';
comment on column SYNC.TF_CFG_DETECTSTATION.IMAGE_PWD
  is '取图密码';
comment on column SYNC.TF_CFG_DETECTSTATION.NAME_PREFIX
  is '文件名前缀';
comment on column SYNC.TF_CFG_DETECTSTATION.NAME_SPLIT
  is '文件名中的分隔符';
comment on column SYNC.TF_CFG_DETECTSTATION.PAD_ZEROS
  is '文件名中序号补零个数';
comment on column SYNC.TF_CFG_DETECTSTATION.NAME_PATTERN
  is '命名规则 TEDS/TFDS/具体的定义类';
comment on column SYNC.TF_CFG_DETECTSTATION.STATION_LID
  is '所属列检所';
comment on column SYNC.TF_CFG_DETECTSTATION.FACTORY_NAME
  is '设备厂家';
comment on column SYNC.TF_CFG_DETECTSTATION.REMOTE_IP
  is '联网软件远程看图IP(本地服务器IP)';
comment on column SYNC.TF_CFG_DETECTSTATION.REMOTE_PORT
  is '联网软件远程看图端口(本地服务器端口)';
comment on column SYNC.TF_CFG_DETECTSTATION.REMOTE_WEBNAME
  is '工程名';
comment on column SYNC.TF_CFG_DETECTSTATION.REMOTE_URL
  is '看图路径';
comment on column SYNC.TF_CFG_DETECTSTATION.VIEW_MODE
  is '看图模式：0-面阵 1-线阵';
comment on column SYNC.TF_CFG_DETECTSTATION.IS_COUNTDOWN
  is '是否启用倒计时 0-不启用 1-启用';
comment on column SYNC.TF_CFG_DETECTSTATION.COUNTDOWN_TIME
  is '倒计时间隔';
comment on column SYNC.TF_CFG_DETECTSTATION.IS_SIDEMID
  is '是否查看侧部中间';

prompt
prompt Creating table TF_OP_FAULT
prompt ==========================
prompt
create table SYNC.TF_OP_FAULT
(
  FAULT_SERIAL         VARCHAR2(40) default SYS_GUID() not null,
  TRAIN_SERIAL         VARCHAR2(40),
  VEHICLE_SERIAL       VARCHAR2(40),
  FAULT_NUMBER         NUMBER(5),
  FAULT_PLACE          VARCHAR2(2),
  FAULT_ID             VARCHAR2(14),
  DETECTOR             VARCHAR2(40),
  DETECT_TIME          DATE,
  FOUND_MODE           VARCHAR2(1),
  TRANSACTOR           VARCHAR2(40),
  TRANSACT_TIME        DATE,
  TRANSACT_MODE        VARCHAR2(10),
  AFFIRMANT            VARCHAR2(40),
  VERIFY_TIME          DATE,
  PONDERANCE           VARCHAR2(1),
  LAST_REPAIR_DATE     DATE,
  POSITION_LEFT        VARCHAR2(10),
  POSITION_RIGHT       VARCHAR2(10),
  POSITION_TOP         VARCHAR2(10),
  POSITION_BOTTOM      VARCHAR2(10),
  IMG_LENGTH           NUMBER(7),
  IMG_TYPE             VARCHAR2(2),
  REMARK               VARCHAR2(1024),
  LOCK_VERSION         NUMBER default 0,
  IMG_INDEX            NUMBER(2),
  DEL_FLAG             VARCHAR2(1) default '0',
  DEL_TIME             DATE,
  DEL_USER             VARCHAR2(60),
  VIEW_FLAG            VARCHAR2(1) default 0,
  VIEW_TIME            DATE,
  VIEW_USER            VARCHAR2(4000),
  CXINFO               VARCHAR2(100),
  DXINFO               VARCHAR2(100),
  FXINFO               VARCHAR2(100),
  DOWN_TIME            DATE,
  FK_TIME              DATE,
  O_ID_DOWN            VARCHAR2(40),
  DOWN_RECEIVE_TIME    DATE,
  DOWN_RECEIVE_USER    VARCHAR2(40),
  FK_USER              VARCHAR2(40),
  FK_STATUS            VARCHAR2(1) default 0,
  TJZBY_FLAG           VARCHAR2(1) default '0',
  TJZBY_TIME           DATE,
  TJZBY_USER           VARCHAR2(40),
  IDENTIFY_ID          VARCHAR2(400),
  FK_LJ                VARCHAR2(40),
  ZWXX                 VARCHAR2(40),
  IMAGE_NAME           VARCHAR2(50),
  IDENTIFY_RESULT      NUMBER(3),
  O_ID_CHECK           VARCHAR2(40),
  CHECK_TIME           DATE,
  CHECK_RESULT         VARCHAR2(10) default 0,
  ADDTIME              DATE default sysdate,
  CONFIDENCE           NUMBER,
  INFO_CREATED_RESULT  VARCHAR2(1),
  IMAGE_CREATED_RESULT VARCHAR2(1),
  INFO_CREATED_REMARK  VARCHAR2(1024),
  IMAGE_CREATED_REMARK VARCHAR2(1024),
  FAULT_NAME           VARCHAR2(100),
  IMG_DATA             BLOB
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
comment on column SYNC.TF_OP_FAULT.FAULT_SERIAL
  is '故障唯一号';
comment on column SYNC.TF_OP_FAULT.TRAIN_SERIAL
  is '列车唯一号';
comment on column SYNC.TF_OP_FAULT.VEHICLE_SERIAL
  is '车辆唯一号';
comment on column SYNC.TF_OP_FAULT.FAULT_NUMBER
  is '故障序号';
comment on column SYNC.TF_OP_FAULT.FAULT_PLACE
  is '故障部位';
comment on column SYNC.TF_OP_FAULT.FAULT_ID
  is '故障编码';
comment on column SYNC.TF_OP_FAULT.DETECTOR
  is '室内检车员';
comment on column SYNC.TF_OP_FAULT.DETECT_TIME
  is '发现时间';
comment on column SYNC.TF_OP_FAULT.FOUND_MODE
  is '发现方式';
comment on column SYNC.TF_OP_FAULT.TRANSACTOR
  is '处理人';
comment on column SYNC.TF_OP_FAULT.TRANSACT_TIME
  is '处理时间';
comment on column SYNC.TF_OP_FAULT.TRANSACT_MODE
  is '处理方式';
comment on column SYNC.TF_OP_FAULT.AFFIRMANT
  is '故障确认人';
comment on column SYNC.TF_OP_FAULT.VERIFY_TIME
  is '确认时间';
comment on column SYNC.TF_OP_FAULT.PONDERANCE
  is '严重程度';
comment on column SYNC.TF_OP_FAULT.LAST_REPAIR_DATE
  is '前次定检时间';
comment on column SYNC.TF_OP_FAULT.POSITION_LEFT
  is '故障部位左侧坐标';
comment on column SYNC.TF_OP_FAULT.POSITION_RIGHT
  is '故障部位右侧坐标';
comment on column SYNC.TF_OP_FAULT.POSITION_TOP
  is '故障部位上方坐标';
comment on column SYNC.TF_OP_FAULT.POSITION_BOTTOM
  is '故障部位下方坐标';
comment on column SYNC.TF_OP_FAULT.IMG_LENGTH
  is '图像长度';
comment on column SYNC.TF_OP_FAULT.IMG_TYPE
  is '图像类型';
comment on column SYNC.TF_OP_FAULT.REMARK
  is '备注';
comment on column SYNC.TF_OP_FAULT.LOCK_VERSION
  is '乐观锁定标识';
comment on column SYNC.TF_OP_FAULT.IMG_INDEX
  is '图像序号';
comment on column SYNC.TF_OP_FAULT.DEL_FLAG
  is '删除标志';
comment on column SYNC.TF_OP_FAULT.DEL_TIME
  is '删除时间';
comment on column SYNC.TF_OP_FAULT.DEL_USER
  is '删除人';
comment on column SYNC.TF_OP_FAULT.VIEW_FLAG
  is '工长查看标志';
comment on column SYNC.TF_OP_FAULT.VIEW_TIME
  is '查看时间';
comment on column SYNC.TF_OP_FAULT.VIEW_USER
  is '查看用户';
comment on column SYNC.TF_OP_FAULT.CXINFO
  is '厂修信息';
comment on column SYNC.TF_OP_FAULT.DXINFO
  is '段修信息';
comment on column SYNC.TF_OP_FAULT.FXINFO
  is '辅修信息';
comment on column SYNC.TF_OP_FAULT.FK_TIME
  is '反馈上传时间';
comment on column SYNC.TF_OP_FAULT.O_ID_DOWN
  is '下发人';
comment on column SYNC.TF_OP_FAULT.DOWN_RECEIVE_TIME
  is '下发接收时间';
comment on column SYNC.TF_OP_FAULT.DOWN_RECEIVE_USER
  is '下发接收人';
comment on column SYNC.TF_OP_FAULT.FK_USER
  is '反馈上传人';
comment on column SYNC.TF_OP_FAULT.FK_STATUS
  is '反馈状态';
comment on column SYNC.TF_OP_FAULT.TJZBY_FLAG
  is '提交值班员标志';
comment on column SYNC.TF_OP_FAULT.TJZBY_TIME
  is '提交值班员时间';
comment on column SYNC.TF_OP_FAULT.TJZBY_USER
  is '提交人';
comment on column SYNC.TF_OP_FAULT.IDENTIFY_ID
  is '故障标识位';
comment on column SYNC.TF_OP_FAULT.FK_LJ
  is '反馈列检';
comment on column SYNC.TF_OP_FAULT.ZWXX
  is '轴位信息';
comment on column SYNC.TF_OP_FAULT.IMAGE_NAME
  is '图片名称';
comment on column SYNC.TF_OP_FAULT.FAULT_NAME
  is '故障名称';
alter table SYNC.TF_OP_FAULT
  add constraint FKEY primary key (FAULT_SERIAL)
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

prompt
prompt Creating table TF_OP_TRAIN
prompt ==========================
prompt
create table SYNC.TF_OP_TRAIN
(
  TRAIN_SERIAL           VARCHAR2(40) default SYS_GUID() not null,
  TRAIN_ID               VARCHAR2(10),
  STATION_ID             VARCHAR2(9),
  PASS_TIME              DATE,
  DIRECTION              VARCHAR2(1),
  VEHICLE_NUMBER         NUMBER(20),
  FREIGHT_VEHICLE_NUMBER NUMBER(20),
  CAR_NUMBER             NUMBER(20),
  SPEED                  NUMBER(20),
  TRAIN_SORT             VARCHAR2(1),
  ENGINE_NUMBER          NUMBER(20),
  PASS_KIND              VARCHAR2(1),
  PASS_STATE             VARCHAR2(1) default '0',
  VIEW_STATE             VARCHAR2(1) default '0',
  PIC_VALID_STATE        VARCHAR2(1) default '0',
  HIGHEST_SPEED          NUMBER(20),
  LOWEST_SPEED           NUMBER(20),
  EQU_DETECT_NUMBER      NUMBER(20),
  INDEX_ID               NUMBER(20),
  DETECT_NUMBER          NUMBER(20),
  DAY_NIGHT              VARCHAR2(1),
  TEAM                   VARCHAR2(1024),
  REMARK                 VARCHAR2(1024),
  IMG_READY_STATUS       VARCHAR2(1) default '0',
  LOCK_VERSION           NUMBER default 0,
  TRAIN_STATUS           VARCHAR2(2),
  VEHICLE_EXPOSURE       NUMBER(20),
  PHOTO_EXPOSURE         NUMBER(20),
  VEHICLE_BLACK          NUMBER(20),
  PHOTO_BLACK            NUMBER(20),
  VEHICLE_LOST           NUMBER(20),
  PHOTO_LOST             NUMBER(20),
  VEHICLE_DISORDER       NUMBER(20),
  STATION_TRACK          VARCHAR2(10),
  BC                     VARCHAR2(50),
  IS_REPEAT              VARCHAR2(5),
  QCSTATION_ID           VARCHAR2(9),
  QCPASS_TIME            DATE
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
comment on table SYNC.TF_OP_TRAIN
  is '存放列车信息';
comment on column SYNC.TF_OP_TRAIN.TRAIN_SERIAL
  is '列车唯一号';
comment on column SYNC.TF_OP_TRAIN.TRAIN_ID
  is '车次';
comment on column SYNC.TF_OP_TRAIN.STATION_ID
  is '探测站编号';
comment on column SYNC.TF_OP_TRAIN.PASS_TIME
  is '过车时间';
comment on column SYNC.TF_OP_TRAIN.DIRECTION
  is '行车方向';
comment on column SYNC.TF_OP_TRAIN.VEHICLE_NUMBER
  is '车辆数量';
comment on column SYNC.TF_OP_TRAIN.FREIGHT_VEHICLE_NUMBER
  is '货车总辆数';
comment on column SYNC.TF_OP_TRAIN.CAR_NUMBER
  is '客车总辆数';
comment on column SYNC.TF_OP_TRAIN.SPEED
  is '平均速度';
comment on column SYNC.TF_OP_TRAIN.TRAIN_SORT
  is '编组类型';
comment on column SYNC.TF_OP_TRAIN.ENGINE_NUMBER
  is '机车数量';
comment on column SYNC.TF_OP_TRAIN.PASS_KIND
  is '通过类型';
comment on column SYNC.TF_OP_TRAIN.PASS_STATE
  is '接车状态  0代表正在接车 1代表接车完毕';
comment on column SYNC.TF_OP_TRAIN.VIEW_STATE
  is '图像浏览状态 0代表未看完 1代表已看完';
comment on column SYNC.TF_OP_TRAIN.PIC_VALID_STATE
  is '图像过期标志 0代表未过期 1代表已过期';
comment on column SYNC.TF_OP_TRAIN.HIGHEST_SPEED
  is '最高车速';
comment on column SYNC.TF_OP_TRAIN.LOWEST_SPEED
  is '最低车速';
comment on column SYNC.TF_OP_TRAIN.EQU_DETECT_NUMBER
  is '设备接车辆数';
comment on column SYNC.TF_OP_TRAIN.INDEX_ID
  is '图像存放目录号';
comment on column SYNC.TF_OP_TRAIN.DETECT_NUMBER
  is '检测辆数';
comment on column SYNC.TF_OP_TRAIN.DAY_NIGHT
  is '白夜班';
comment on column SYNC.TF_OP_TRAIN.TEAM
  is '班组';
comment on column SYNC.TF_OP_TRAIN.REMARK
  is '备注';
comment on column SYNC.TF_OP_TRAIN.IMG_READY_STATUS
  is '图像准备完毕';
comment on column SYNC.TF_OP_TRAIN.LOCK_VERSION
  is '乐观锁定标识';
comment on column SYNC.TF_OP_TRAIN.TRAIN_STATUS
  is '列车属性(01正常 02漏探)';
comment on column SYNC.TF_OP_TRAIN.VEHICLE_EXPOSURE
  is '曝光辆数';
comment on column SYNC.TF_OP_TRAIN.PHOTO_EXPOSURE
  is '曝光照片数';
comment on column SYNC.TF_OP_TRAIN.VEHICLE_BLACK
  is '黑图辆数';
comment on column SYNC.TF_OP_TRAIN.PHOTO_BLACK
  is '黑图照片数';
comment on column SYNC.TF_OP_TRAIN.VEHICLE_LOST
  is '丢图辆数';
comment on column SYNC.TF_OP_TRAIN.PHOTO_LOST
  is '丢图照片数';
comment on column SYNC.TF_OP_TRAIN.VEHICLE_DISORDER
  is '窜图车辆数';
comment on column SYNC.TF_OP_TRAIN.IS_REPEAT
  is '是否重复';
comment on column SYNC.TF_OP_TRAIN.QCSTATION_ID
  is '前次过车探测站';
comment on column SYNC.TF_OP_TRAIN.QCPASS_TIME
  is '前次过车时间';
alter table SYNC.TF_OP_TRAIN
  add constraint TKEY primary key (TRAIN_SERIAL)
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

prompt
prompt Creating table TF_OP_VEHICLE
prompt ============================
prompt
create table SYNC.TF_OP_VEHICLE
(
  VEHICLE_SERIAL        VARCHAR2(40) default SYS_GUID() not null,
  TRAIN_SERIAL          VARCHAR2(40),
  VEHICLE_ID            VARCHAR2(7),
  VEHICLE_SORT          VARCHAR2(1),
  VEHICLE_TYPE          VARCHAR2(10),
  VEHICLE_ORDER         VARCHAR2(3),
  MADE_FACTORY          VARCHAR2(1),
  MADE_DATE             VARCHAR2(10),
  CONVERSION            VARCHAR2(5),
  VEHICLE_OWNER         VARCHAR2(1),
  AXES_DISTANCE         VARCHAR2(160),
  MIDPART_PICS_NUMBER   NUMBER,
  LOCK_VERSION          NUMBER default 0,
  FAULT_AUTO            NUMBER,
  FAULT_MANUL           NUMBER,
  CAR_AXIS_NUMBER       NUMBER(2),
  GRAB_INFO             VARCHAR2(100),
  CMSPCAR_PICS_NUMBER   NUMBER(5) default 0,
  DMSPCAR_PICS_NUMBER   NUMBER(5) default 0,
  POS_AB_FLAG           VARCHAR2(1) default '2',
  MIDPARTCM_PICS_NUMBER NUMBER(3),
  CMCTCAR_PICS_NUMBER   NUMBER(3),
  CMCAR_PICS_NUMBER     NUMBER(3),
  DMCAR_PICS_NUMBER     NUMBER(3),
  IS_TRACK              VARCHAR2(10),
  WORK_MODE             VARCHAR2(10),
  WORK_STATE            VARCHAR2(10)
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
comment on table SYNC.TF_OP_VEHICLE
  is '存放车辆信息';
comment on column SYNC.TF_OP_VEHICLE.VEHICLE_SERIAL
  is '车辆唯一号';
comment on column SYNC.TF_OP_VEHICLE.TRAIN_SERIAL
  is '列车唯一号';
comment on column SYNC.TF_OP_VEHICLE.VEHICLE_ID
  is '车号';
comment on column SYNC.TF_OP_VEHICLE.VEHICLE_SORT
  is '车辆类型';
comment on column SYNC.TF_OP_VEHICLE.VEHICLE_TYPE
  is '车型';
comment on column SYNC.TF_OP_VEHICLE.VEHICLE_ORDER
  is '辆序';
comment on column SYNC.TF_OP_VEHICLE.MADE_FACTORY
  is '制造厂';
comment on column SYNC.TF_OP_VEHICLE.MADE_DATE
  is '制造日期';
comment on column SYNC.TF_OP_VEHICLE.CONVERSION
  is '换长';
comment on column SYNC.TF_OP_VEHICLE.VEHICLE_OWNER
  is '车辆属性';
comment on column SYNC.TF_OP_VEHICLE.AXES_DISTANCE
  is '轴距';
comment on column SYNC.TF_OP_VEHICLE.MIDPART_PICS_NUMBER
  is '中间部张数';
comment on column SYNC.TF_OP_VEHICLE.LOCK_VERSION
  is '乐观锁定标识';
comment on column SYNC.TF_OP_VEHICLE.FAULT_AUTO
  is '自动识别故障数';
comment on column SYNC.TF_OP_VEHICLE.FAULT_MANUL
  is '人工识别故障数';
comment on column SYNC.TF_OP_VEHICLE.CAR_AXIS_NUMBER
  is '车辆轴数';
comment on column SYNC.TF_OP_VEHICLE.GRAB_INFO
  is '拍摄图像定位信息';
comment on column SYNC.TF_OP_VEHICLE.CMSPCAR_PICS_NUMBER
  is '存储车辆侧部单通道相机采集的图像数量';
comment on column SYNC.TF_OP_VEHICLE.DMSPCAR_PICS_NUMBER
  is '存储车辆底部单通道相机采集的图像数量';
comment on column SYNC.TF_OP_VEHICLE.MIDPARTCM_PICS_NUMBER
  is '存储车中间部侧部单通道相机采集的图像数量,车辆种类为0时填写';
comment on column SYNC.TF_OP_VEHICLE.CMCTCAR_PICS_NUMBER
  is '存储车辆侧面定检单通道相机采集的图像数量';
comment on column SYNC.TF_OP_VEHICLE.CMCAR_PICS_NUMBER
  is '  存储侧部线扫描相机单通道相机采集的图像数量';
comment on column SYNC.TF_OP_VEHICLE.DMCAR_PICS_NUMBER
  is '存储底部线扫描相机单通道相机采集的图像数量';
comment on column SYNC.TF_OP_VEHICLE.IS_TRACK
  is '是否追踪';
comment on column SYNC.TF_OP_VEHICLE.WORK_MODE
  is '作业模式';
comment on column SYNC.TF_OP_VEHICLE.WORK_STATE
  is '作业状态';
alter table SYNC.TF_OP_VEHICLE
  add constraint VKEY primary key (VEHICLE_SERIAL)
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

prompt
prompt Creating sequence TD_MENU_ID_SEQ
prompt ================================
prompt
create sequence SYNC.TD_MENU_ID_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;


spool off
