use exam;

-- 性别：1
INSERT INTO `master`(`category`,`code`,`name`)VALUES(1,'1','男');
INSERT INTO `master`(`category`,`code`,`name`)VALUES(1,'2','女');
-- 婚姻状况：2
INSERT INTO `master`(`category`,`code`,`name`)VALUES(2,'1','已婚');
INSERT INTO `master`(`category`,`code`,`name`)VALUES(2,'2','未婚');
-- 政治面貌：3
INSERT INTO `master`(`category`,`code`,`name`)VALUES(3,'1','共产党员');
INSERT INTO `master`(`category`,`code`,`name`)VALUES(3,'2','共青团员');
INSERT INTO `master`(`category`,`code`,`name`)VALUES(3,'3','群众');
-- 身份：4
INSERT INTO `master`(`category`,`code`,`name`)VALUES(4,'1','应届毕业生');
INSERT INTO `master`(`category`,`code`,`name`)VALUES(4,'2','无业人员');
INSERT INTO `master`(`category`,`code`,`name`)VALUES(4,'3','在职人员');
-- 民族：5
INSERT INTO `master`(`category`,`code`,`name`)VALUES(5,'1','汉族');
INSERT INTO `master`(`category`,`code`,`name`)VALUES(5,'2','少数民族');
-- 证件类型：6
INSERT INTO `master`(`category`,`code`,`name`)VALUES(6,'1','身份证');
INSERT INTO `master`(`category`,`code`,`name`)VALUES(6,'2','军官证');


-- INSERT INTO `exam`.`city`(`name`,`description`)VALUES('合肥市','合肥市');
INSERT INTO `exam`.`city`(`name`,`description`)VALUES('阜阳市','阜阳市');

INSERT INTO `exam`.`depart`(`name`,`description`,`cityId`)VALUES('办公室','办公室','1');
INSERT INTO `exam`.`depart`(`name`,`description`,`cityId`)VALUES('财政局','财政局','1');
INSERT INTO `exam`.`depart`(`name`,`description`,`cityId`)VALUES('规划建设局','规划建设局','1');
INSERT INTO `exam`.`depart`(`name`,`description`,`cityId`)VALUES('社会事业局','社会事业局','1');
INSERT INTO `exam`.`depart`(`name`,`description`,`cityId`)VALUES('经贸（招商）局','经贸（招商）局','1');
INSERT INTO `exam`.`depart`(`name`,`description`,`cityId`)VALUES('企业服务中心','企业服务中心','1');

INSERT INTO `exam`.`office`(`name`,`code`,`major`,`degree`,`recruits`,`limitAge`,`description`,`departId`,`examId`,`applyYear`)
                     VALUES('管理岗位','101','中文及相关专业','大学本科',1,26,'',1,'1',2013);
INSERT INTO `exam`.`office`(`name`,`code`,`major`,`degree`,`recruits`,`limitAge`,`description`,`departId`,`examId`,`applyYear`)
                     VALUES('管理岗位','102','中文及相关专业','大学本科',1,26,'应届毕业生',1,'1',2013);
INSERT INTO `exam`.`office`(`name`,`code`,`major`,`degree`,`recruits`,`limitAge`,`description`,`departId`,`examId`,`applyYear`)
                     VALUES('专业技术岗位 ','103','计算机、动漫、文案策划','大学本科',1,26,'有较强的计算机专业知识',1,'1',2013);
INSERT INTO `exam`.`office`(`name`,`code`,`major`,`degree`,`recruits`,`limitAge`,`description`,`departId`,`examId`,`applyYear`)
                     VALUES('专业技术岗位','201','财务管理、金融等相关专业','大学本科',2,26,'有较强的金融专业知识',2,'1',2013);
INSERT INTO `exam`.`office`(`name`,`code`,`major`,`degree`,`recruits`,`limitAge`,`description`,`departId`,`examId`,`applyYear`)
                     VALUES('专业技术岗位','202','会计、审计等相关专业 ','大学本科',2,26,'有较强的财会专业知识 ',2,'1',2013);
INSERT INTO `exam`.`office`(`name`,`code`,`major`,`degree`,`recruits`,`limitAge`,`description`,`departId`,`examId`,`applyYear`)
                     VALUES('专业技术岗位','301','建设工程类等相关专业','大学本科',3,26,'有较强的建设工程专业知识',3,'1',2013);
INSERT INTO `exam`.`office`(`name`,`code`,`major`,`degree`,`recruits`,`limitAge`,`description`,`departId`,`examId`,`applyYear`)
                     VALUES('专业技术岗位','302','土地管理专业','大学本科',1,26,'有较强的土地管理专业知识',3,'1',2013);
INSERT INTO `exam`.`office`(`name`,`code`,`major`,`degree`,`recruits`,`limitAge`,`description`,`departId`,`examId`,`applyYear`)
                     VALUES('专业技术岗位','303','规划专业','大学本科',1,26,'有较强的城市规划专业知识',3,'1',2013);
INSERT INTO `exam`.`office`(`name`,`code`,`major`,`degree`,`recruits`,`limitAge`,`description`,`departId`,`examId`,`applyYear`)
                     VALUES('专业技术岗位','304','环保专业','大学本科',1,26,'有较强的环境保护专业知识',3,'1',2013);
INSERT INTO `exam`.`office`(`name`,`code`,`major`,`degree`,`recruits`,`limitAge`,`description`,`departId`,`examId`,`applyYear`)
                     VALUES('专业技术岗位','401','社会管理等相关专业','大学本科',4,26,'社区管理工作经验',4,'1',2013);
INSERT INTO `exam`.`office`(`name`,`code`,`major`,`degree`,`recruits`,`limitAge`,`description`,`departId`,`examId`,`applyYear`)
                     VALUES('管理岗位','501','专业不限','大学本科',2,26,'具有招商引资经验，有较强的社交沟通能力',5,'1',2013);
INSERT INTO `exam`.`office`(`name`,`code`,`major`,`degree`,`recruits`,`limitAge`,`description`,`departId`,`examId`,`applyYear`)
                     VALUES('管理岗位','502','专业不限','大学本科',1,26,'应届毕业生，有较强的社交沟通能力 ',5,'1',2013);
INSERT INTO `exam`.`office`(`name`,`code`,`major`,`degree`,`recruits`,`limitAge`,`description`,`departId`,`examId`,`applyYear`)
                     VALUES('专业技术岗位','503','统计','大学本科',1,26,'有较强的统计专业知识',5,'1',2013);
INSERT INTO `exam`.`office`(`name`,`code`,`major`,`degree`,`recruits`,`limitAge`,`description`,`departId`,`examId`,`applyYear`)
                     VALUES('专业技术岗位','504','英语','大学本科',1,26,'全日制本科及以上、具有相应学位，英语专业具有专业八级证书',5,'1',2013);
INSERT INTO `exam`.`office`(`name`,`code`,`major`,`degree`,`recruits`,`limitAge`,`description`,`departId`,`examId`,`applyYear`)
                     VALUES('管理岗位','601','专业不限','大学本科',3,26,'应届毕业生',6,'1',2013);

INSERT INTO `exam`.`exam`(`name`,`applyBeginDate`,`applyDeadDate`, `examDate`,`examTime`, `examPosition`)VALUES('阜阳合肥现代产业园区管委会招聘工作人员考试','2013/02/21 9:00:00','2013/03/04 16:00:00','2013/03/16','8：30-11:30','待定');

INSERT INTO `exam`.`admin`(`administratorId`, `password`) VALUES ('administrator', 'examadmin');