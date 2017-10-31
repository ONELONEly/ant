create table CBASE000
(
	USID VARCHAR2(10) not null
		constraint USID_INDEX
			primary key,
	DSCA VARCHAR2(50),
	PAWD VARCHAR2(50),
	GROP VARCHAR2(10) default NULL,
	DEPT CHAR(6),
	FLAG NUMBER(1),
	COMP VARCHAR2(3),
	CPID VARCHAR2(7),
	MAIL VARCHAR2(50),
	ACCO VARCHAR2(100),
	BANK VARCHAR2(100),
	TEL1 VARCHAR2(50),
	REMK VARCHAR2(100),
	JWWJ VARCHAR2(50),
	STA1 NUMBER(1),
	TOID VARCHAR2(8),
	IPID VARCHAR2(25),
	COMM VARCHAR2(50),
	JDEP CHAR(6),
	STA2 NUMBER(1),
	YYNO VARCHAR2(10),
	STA3 NUMBER(1),
	PHOT BLOB
)
/

comment on table CBASE000 is '用户表'
/

comment on column CBASE000.USID is '用户编号'
/

comment on column CBASE000.DSCA is '名称'
/

comment on column CBASE000.PAWD is '密码'
/

comment on column CBASE000.GROP is '组'
/

comment on column CBASE000.DEPT is '部门'
/

comment on column CBASE000.FLAG is '状态,1后门用户，0前台用户'
/

comment on column CBASE000.COMP is '公司'
/

comment on column CBASE000.CPID is '厂牌编号'
/

comment on column CBASE000.MAIL is '邮箱'
/

comment on column CBASE000.ACCO is '科室 cbase017'
/

comment on column CBASE000.BANK is '银行帐号'
/

comment on column CBASE000.TEL1 is '手机号码'
/

comment on column CBASE000.REMK is '备注'
/

comment on column CBASE000.JWWJ is '岗位'
/

comment on column CBASE000.STA1 is '是否委托'
/

comment on column CBASE000.TOID is 'KEYID'
/

comment on column CBASE000.IPID is 'IP地址'
/

comment on column CBASE000.COMM is '计算机名'
/

comment on column CBASE000.JDEP is '借入部门'
/

comment on column CBASE000.STA2 is '权限级别'
/

comment on column CBASE000.YYNO is '团队'
/

comment on column CBASE000.STA3 is '是否开短信通知0,1：是'
/

comment on column CBASE000.PHOT is '头像'
/

create table CBASE001
(
	COMP VARCHAR2(3) not null,
	DSCA VARCHAR2(50),
	STA1 NUMBER(1)
)
/

comment on table CBASE001 is '用户公司表'
/

comment on column CBASE001.COMP is '公司代码'
/

comment on column CBASE001.DSCA is '描述'
/

comment on column CBASE001.STA1 is '显示'
/

create table CBASE002
(
	PONO VARCHAR2(4) not null
		constraint PONO_INDEX
			primary key,
	DSCA VARCHAR2(100),
	STYP NUMBER(1),
	PURL VARCHAR2(100)
)
/

comment on table CBASE002 is '进程表'
/

comment on column CBASE002.PONO is '进程号'
/

comment on column CBASE002.DSCA is '描述'
/

comment on column CBASE002.STYP is '节点类型'
/

comment on column CBASE002.PURL is '路径'
/

create table CBASE003
(
	FLNO CHAR(3) not null
		constraint CBASE003_FLNO_PK
			primary key,
	PONO CHAR(3) default NULL   not null,
	WZNO NUMBER(2)
)
/

comment on table CBASE003 is '进程关系表'
/

comment on column CBASE003.FLNO is '点节号'
/

comment on column CBASE003.PONO is '父节点号'
/

comment on column CBASE003.WZNO is '位置'
/

create table CBASE004
(
	PONO CHAR(3) not null,
	ROID CHAR(6) not null,
	constraint PONO_ROID_INDEX
		primary key (PONO, ROID)
)
/

comment on table CBASE004 is '进程,角色表'
/

comment on column CBASE004.PONO is '进程号'
/

comment on column CBASE004.ROID is '角色编号'
/

create table CBASE005
(
	USID VARCHAR2(10) default NULL not null,
	ROID CHAR(6) not null,
	constraint USID_ROID_INDEX
		primary key (ROID, USID)
)
/

comment on table CBASE005 is '用户,角色表'
/

comment on column CBASE005.USID is '用户编号'
/

comment on column CBASE005.ROID is '角色编号'
/

create table CBASE007
(
	ROID CHAR(6) not null
		constraint ROID_INDEX
			primary key,
	DSCA VARCHAR2(50),
	COMP VARCHAR2(3)
)
/

comment on table CBASE007 is '角色表'
/

comment on column CBASE007.ROID is '角色编号'
/

comment on column CBASE007.DSCA is '描述'
/

comment on column CBASE007.COMP is '公司'
/

create table CBASE006
(
	DEPT CHAR(6) not null
		constraint DEPT_INDEX
			primary key,
	DSCA VARCHAR2(70),
	STA1 NUMBER(1),
	COMP VARCHAR2(3)
)
/

comment on table CBASE006 is '部门表'
/

comment on column CBASE006.DEPT is '部门编号'
/

comment on column CBASE006.DSCA is '描述'
/

comment on column CBASE006.STA1 is '二级审批(0:否,1:是)'
/

comment on column CBASE006.COMP is '公司'
/

create table CBASE008
(
	CYYO VARCHAR2(10) not null,
	DSCA VARCHAR2(200)
)
/

comment on table CBASE008 is '科室表'
/

comment on column CBASE008.CYYO is '编号'
/

comment on column CBASE008.DSCA is '描述'
/

create table CBASE009
(
	GROP VARCHAR2(10) default NULL not null,
	DSCA VARCHAR2(200)
)
/

comment on table CBASE009 is '团队表'
/

comment on column CBASE009.GROP is '组ID'
/

comment on column CBASE009.DSCA is '描述'
/

create table CBASE010
(
	GROP VARCHAR2(10) default NULL not null,
	USID VARCHAR2(10) not null
)
/

comment on table CBASE010 is '团队成员'
/

comment on column CBASE010.GROP is '组编号'
/

comment on column CBASE010.USID is '用户ID'
/

create table TBUSS001
(
	PTNO VARCHAR2(10) not null
		constraint PTNO_PK
			primary key,
	DSCA VARCHAR2(200),
	PDAT VARCHAR2(10),
	USID VARCHAR2(10),
	UDAT DATE,
	GROP VARCHAR2(10),
	STA1 NUMBER(1),
	STA2 NUMBER(1)
)
/

comment on table TBUSS001 is '项目绩效表'
/

comment on column TBUSS001.PTNO is '项目编号'
/

comment on column TBUSS001.DSCA is '描述'
/

comment on column TBUSS001.PDAT is '月份'
/

comment on column TBUSS001.USID is '创建用户'
/

comment on column TBUSS001.UDAT is '创建日期'
/

comment on column TBUSS001.GROP is '团队'
/

comment on column TBUSS001.STA1 is '手动打分可编辑0-否，1-是'
/

comment on column TBUSS001.STA2 is '自动打分可编辑0-否，1-是'
/

create table CBASE011
(
	PJNO VARCHAR2(10) not null,
	DSCA VARCHAR2(200),
	PLSU VARCHAR2(200),
	PJJP VARCHAR2(200),
	DETI VARCHAR2(1500),
	CONS VARCHAR2(200),
	REMK VARCHAR2(1500),
	USID VARCHAR2(10),
	UDAT DATE,
	STAT NUMBER
)
/

comment on table CBASE011 is '绩效评分项'
/

comment on column CBASE011.PJNO is '绩效评估项目编号'
/

comment on column CBASE011.DSCA is '描述'
/

comment on column CBASE011.PLSU is '评估占比'
/

comment on column CBASE011.PJJP is '分项占比'
/

comment on column CBASE011.DETI is '评估细则'
/

comment on column CBASE011.CONS is '基础分数'
/

comment on column CBASE011.REMK is '备注'
/

comment on column CBASE011.USID is '创建用户'
/

comment on column CBASE011.UDAT is '创建时间'
/

comment on column CBASE011.STAT is '1.自动，0：手动'
/

create table TBUSS002
(
	PJNO VARCHAR2(10),
	PTNO VARCHAR2(10)
)
/

comment on table TBUSS002 is '绩效对应评分项'
/

comment on column TBUSS002.PJNO is '评分项'
/

comment on column TBUSS002.PTNO is '项目绩效表'
/

create table CBASE012
(
	PJNO VARCHAR2(10) default NULL,
	OPCO VARCHAR2(10),
	REMK VARCHAR2(200)
)
/

comment on table CBASE012 is '评分项对应分值'
/

comment on column CBASE012.PJNO is '评分项'
/

create table TBUSS003
(
	TAID VARCHAR2(10) not null
		constraint TAID_PK
			primary key,
	NOTE CLOB,
	CDAT DATE,
	USID VARCHAR2(10),
	CSID VARCHAR2(10),
	STA1 NUMBER,
	PTYP VARCHAR2(10),
	STA2 NUMBER,
	TEPR VARCHAR2(10),
	PDAT DATE,
	KSID VARCHAR2(10),
	TADD VARCHAR2(100),
	STA3 NUMBER,
	SYNO VARCHAR2(6),
	RSID VARCHAR2(6),
	PUNO VARCHAR2(6),
	PTNO VARCHAR2(10),
	TITL VARCHAR2(100),
	XDAT DATE,
	FDAT DATE,
	TDAT DATE,
	ADAT DATE,
	STAG NUMBER(1) default 0  not null,
	FAHH FLOAT default 0  not null
)
/

comment on table TBUSS003 is '任务单表'
/

comment on column TBUSS003.TAID is '编号'
/

comment on column TBUSS003.NOTE is '内容'
/

comment on column TBUSS003.CDAT is '创建时间'
/

comment on column TBUSS003.USID is '创建人（关联CBASE000.DSCA）'
/

comment on column TBUSS003.CSID is '接收人（关联CBASE000.DSCA）'
/

comment on column TBUSS003.STA1 is '状态:0：保存，1，下发，2，执行，3,驳回，4，变更，5，交付测试，6，交付验收，7，测试通过，8，测试不通过，9，验收通过，10，验收不通过，11，任务结束'
/

comment on column TBUSS003.PTYP is '类型'
/

comment on column TBUSS003.STA2 is '紧急状态：0,普通，1，急，2，紧急'
/

comment on column TBUSS003.TEPR is '测试人（关联CBASE000.DSCA）'
/

comment on column TBUSS003.PDAT is '计划完成时间'
/

comment on column TBUSS003.KSID is '关建用户 （关联CBASE000.DSCA）'
/

comment on column TBUSS003.TADD is '测试备注'
/

comment on column TBUSS003.STA3 is '重要程度：0一般，1，重要'
/

comment on column TBUSS003.SYNO is '系统编号(cbase013 t)'
/

comment on column TBUSS003.RSID is '验收人（关联CBASE000.DSCA）'
/

comment on column TBUSS003.PUNO is '任务阶段编号'
/

comment on column TBUSS003.PTNO is '项目编号'
/

comment on column TBUSS003.TITL is '标题'
/

comment on column TBUSS003.XDAT is '下发时间(默认操作下发按钮时)'
/

comment on column TBUSS003.FDAT is '交付验收时间'
/

comment on column TBUSS003.TDAT is '交付测试时间'
/

comment on column TBUSS003.ADAT is '执行时间'
/

comment on column TBUSS003.STAG is '当前任务的等级'
/

comment on column TBUSS003.FAHH is '花费工时'
/

create table TBUSS004
(
	TAID VARCHAR2(10),
	STA1 NUMBER,
	EDAT DATE,
	REMK VARCHAR2(200)
)
/

comment on table TBUSS004 is '日志表'
/

comment on column TBUSS004.TAID is '编号'
/

comment on column TBUSS004.STA1 is '状态'
/

comment on column TBUSS004.EDAT is '操作时间'
/

comment on column TBUSS004.REMK is '备注, 执行，更改，交付测试，验收不通过，弹出窗口。'
/

create table CBASE013
(
	SYNO VARCHAR2(6) not null,
	DSCA VARCHAR2(100),
	SADD VARCHAR2(100),
	TADD VARCHAR2(100)
)
/

comment on table CBASE013 is '系统表'
/

comment on column CBASE013.SYNO is '系统编号'
/

comment on column CBASE013.DSCA is '系统描述'
/

comment on column CBASE013.SADD is '正式地址'
/

comment on column CBASE013.TADD is '测试地址'
/

create table CBASE015
(
	DJID VARCHAR2(10) not null,
	DUTA CHAR(15) not null
		constraint CBASE015_DUTA_PK
			primary key,
	FFIL VARCHAR2(500),
	FSIZ NUMBER,
	USID VARCHAR2(50),
	CDAT DATE
)
/

comment on table CBASE015 is '附件表'
/

comment on column CBASE015.DJID is '任务ID'
/

comment on column CBASE015.DUTA is '流水号'
/

comment on column CBASE015.FFIL is '文件名'
/

comment on column CBASE015.FSIZ is '大小'
/

comment on column CBASE015.USID is '上传用户'
/

comment on column CBASE015.CDAT is '上传时间'
/

create table TBUSS005
(
	PDAT DATE,
	CSID VARCHAR2(10),
	CONS VARCHAR2(10),
	PJNO VARCHAR2(10) default NULL,
	REMK VARCHAR2(100),
	CDAT DATE,
	PTNO VARCHAR2(10)
)
/

comment on table TBUSS005 is '评分结果表'
/

comment on column TBUSS005.PDAT is '考评时间'
/

comment on column TBUSS005.CSID is '收接人'
/

comment on column TBUSS005.CONS is '分数'
/

comment on column TBUSS005.PJNO is 'cbase011.pjno'
/

comment on column TBUSS005.REMK is '备注'
/

comment on column TBUSS005.CDAT is '操作时间'
/

comment on column TBUSS005.PTNO is '绩效表编号 TBUSS001.PTNO'
/

create table CBASE014
(
	PUNO VARCHAR2(6),
	DSCA VARCHAR2(50)
)
/

comment on table CBASE014 is '任务阶段'
/

comment on column CBASE014.PUNO is '任务阶段编号'
/

comment on column CBASE014.DSCA is '描述'
/

create table TBUSS006
(
	STID NVARCHAR2(2) not null
		constraint STID
			primary key,
	DSCA NVARCHAR2(100)
)
/

comment on column TBUSS006.STID is '状态'
/

comment on column TBUSS006.DSCA is '描述'
/

create table TBUSS007
(
	STA2 NUMBER not null
		constraint STA2
			primary key,
	DSCA VARCHAR2(10)
)
/

comment on table TBUSS007 is '紧急状态表（工具表）'
/

comment on column TBUSS007.STA2 is '紧急状态编号码'
/

comment on column TBUSS007.DSCA is '紧急状态描述'
/

create table TBUSS008
(
	STA3 NUMBER not null
		constraint STA3
			primary key,
	DSCA VARCHAR2(10)
)
/

comment on column TBUSS008.STA3 is '重要程度编号码'
/

comment on column TBUSS008.DSCA is '重要程度描述'
/

create table TBUSS009
(
	DOID NUMBER not null,
	NOTE CLOB,
	CDAT DATE,
	USID VARCHAR2(10),
	CSID VARCHAR2(10),
	CTYP VARCHAR2(2),
	STAT NUMBER,
	STA2 NUMBER,
	TILT VARCHAR2(100)
)
/

comment on table TBUSS009 is '发表文档表'
/

comment on column TBUSS009.DOID is '编号毫秒数'
/

comment on column TBUSS009.NOTE is '内容'
/

comment on column TBUSS009.CDAT is '创建时间'
/

comment on column TBUSS009.USID is '创建人默认当前用户'
/

comment on column TBUSS009.CSID is '接收人'
/

comment on column TBUSS009.CTYP is '文档类型,cbase016.ctyp'
/

comment on column TBUSS009.STAT is '0:私有1:组内 2:科室 3:部门 4:公司 5:完全公开'
/

comment on column TBUSS009.STA2 is '0显示;1：隐藏'
/

comment on column TBUSS009.TILT is '主题'
/

create table CBASE016
(
	CTYP VARCHAR2(3) not null,
	DSCA VARCHAR2(20)
)
/

comment on table CBASE016 is '文档类型表'
/

comment on column CBASE016.CTYP is '类型编号'
/

comment on column CBASE016.DSCA is '描述'
/

create table CBASE017
(
	ACCO VARCHAR2(10) not null
		constraint CBASE017_ACCO_PK
			primary key,
	DSCA VARCHAR2(20)
)
/

comment on table CBASE017 is '科室表'
/

comment on column CBASE017.ACCO is '科室编号'
/

comment on column CBASE017.DSCA is '描述'
/

create table TBUSS015
(
	DOID NUMBER not null,
	FFIL VARCHAR2(500),
	FSIZ NUMBER,
	USID VARCHAR2(50),
	CDAT DATE,
	DUTA VARCHAR2(15) not null
		constraint TBUSS015_DUTA_PK
			primary key
)
/

comment on column TBUSS015.DOID is '文档ID'
/

comment on column TBUSS015.FFIL is '文件名'
/

comment on column TBUSS015.FSIZ is '大小'
/

comment on column TBUSS015.USID is '上传用户'
/

comment on column TBUSS015.CDAT is '上传时间'
/

comment on column TBUSS015.DUTA is '流水号'
/

create table UTILL001
(
	STAT NUMBER,
	DSCA VARCHAR2(45)
)
/

comment on table UTILL001 is '文档权限表'
/

comment on column UTILL001.STAT is '权限编号'
/

comment on column UTILL001.DSCA is '权限描述'
/

create table UTILL002
(
	STA2 NUMBER,
	DSCA VARCHAR2(45)
)
/

comment on table UTILL002 is '文档状态表'
/

comment on column UTILL002.STA2 is '状态编号'
/

comment on column UTILL002.DSCA is '状态描述'
/

create table UTILL003
(
	PTYP NUMBER(1) not null
		primary key,
	DSCA VARCHAR2(45)
)
/

create table TBUSS010
(
	TAID VARCHAR2(10) not null,
	USID VARCHAR2(10) not null,
	constraint TBUSS010_TAID_USID_PK
		primary key (TAID, USID)
)
/

comment on column TBUSS010.TAID is '任务ID'
/

comment on column TBUSS010.USID is '用户ID'
/

create table CBASE018
(
	EMID NUMBER(4) not null
		primary key,
	DSCA VARCHAR2(40)
)
/

create table CBASE019
(
	EMID VARCHAR2(4),
	USID VARCHAR2(10),
	constraint "CBASE019_EMID_USID_pk"
		unique (EMID, USID)
)
/

comment on column CBASE019.EMID is '邮件组ID'
/

comment on column CBASE019.USID is '用户ID'
/

CREATE VIEW V_TBUSS003 AS (select t."TAID",t."TITL",t."NOTE",t."CDAT",t."USID",t."CSID",t."STA1",t."PTYP",t."STA2",t."TEPR",t."PDAT",t."ADAT",t."XDAT",t."FDAT",t."TDAT",t."KSID",t."TADD",t."STA3",t."SYNO",t."RSID",t."PUNO",t."PTNO",t."STAG",t."FAHH",(select c.dsca from CBASE000 c where c.usid=t.usid)unam,
(select c.dsca from CBASE000 c where c.usid=t.csid)cnam,(select c.dsca from CBASE000 c where c.usid=t.ksid)knam,(select c.dsca from CBASE000 c where c.usid=t.rsid)rnam,(select c.dsca from cbase000 c where c.usid = t.tepr)tnam,
(select c.dsca from CBASE011 c where c.pjno=t.ptyp)ptypnam,(select c.dsca from CBASE014 c where c.puno=t.puno)punonam,(select c.dsca from CBASE013 c where c.syno=t.syno)synonam,(select c.dsca from TBUSS006 c where c.stid=t.sta1)sta1nam,
(select c.cons from CBASE011 c where c.pjno=t.ptyp)cons,(select c.dsca from tbuss007 c where c.sta2=t.sta2)sta2nam,(select c.dsca from tbuss008 c where c.sta3=t.sta3)sta3nam,(select c.dsca from tbuss001 c where c.ptno=t.ptno)ptnonam,
(select d.dsca from cbase017 d where d.acco = (select c.acco from cbase000 c where c.usid = t.usid))acconam,(select d.dsca from cbase006 d where d.dept = (select c.dept from cbase000 c where c.usid = t.ksid))kdnam,trunc((sysdate - t.ADAT)/(t.PDAT-t.ADAT),2)perc from TBUSS003 t)
/

CREATE VIEW V_CBASE000 AS (select c."USID",c."DSCA",c."PAWD",c."GROP",c."DEPT",c."FLAG",c."COMP",c."CPID",c."MAIL",c."ACCO",c."BANK",c."TEL1",c."REMK",c."JWWJ",c."STA1",c."TOID",c."IPID",c."COMM",c."JDEP",c."STA2",c."YYNO",c."STA3",c."PHOT",(select t.dsca from Cbase009 t where t.grop = c.grop)gropnam,(select t.dsca from cbase006 t where t.dept = c.dept)deptnam,
(select t.dsca from cbase001 t where t.comp = c.comp)compnam,(select t.dsca from CBASE017 t where t.acco = c.acco)acconam from Cbase000 c)
/

CREATE VIEW V_TBUSS009 AS (select t."DOID",t."NOTE",t."CDAT",t."USID",t."CSID",t."CTYP",t."STAT",t."STA2",t."TILT",(select c.dsca from cbase000 c where c.usid = t.usid)unam,(select c.dsca from cbase000 c where c.usid = t.csid)cnam,
(select c.dsca from cbase016 c where c.ctyp = t.ctyp)ctypnam,(select c.dsca from utill001 c where c.stat = t.stat)statnam,(select c.dsca from utill002 c where c.sta2 = t.sta2)sta2nam from tbuss009 t)
/

CREATE VIEW V_TBUSS001 AS (select t."PTNO",t."DSCA",t."PDAT",t."USID",t."UDAT",t."GROP",t."STA1",t."STA2",(select c.dsca from cbase000 c where c.usid = t.usid)unam,(select c.dsca from cbase009 c where c.grop = t.grop)gropnam from tbuss001 t)
/

CREATE VIEW V_CBASE002 AS (SELECT c."PONO",c."DSCA",c."STYP",c."PURL",(SELECT u.dsca FROM UTILL003 u where u.PTYP = c.STYP)stypnam FROM CBASE002 c)
/

