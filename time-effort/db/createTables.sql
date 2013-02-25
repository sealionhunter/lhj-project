DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
 `id` INT NOT NULL PRIMARY KEY,
 `name` VARCHAR(32),
 `shortName` VARCHAR(16),
 `deleteFlg` BIT(10)
);


DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
 `id` INT NOT NULL PRIMARY KEY,
 `name` VARCHAR(32),
 `shortName` CHAR(16),
 `deleteFlg` BIT(10)
);


DROP TABLE IF EXISTS `subCategory`;
CREATE TABLE `subCategory` (
 `id` INT NOT NULL PRIMARY KEY,
 `categoryId` INT NOT NULL,
 `name` VARCHAR(32),
 `shortName` VARCHAR(16),
 `deleteFlg` INT,

 FOREIGN KEY (`categoryId`) REFERENCES `category` (`id`)
);


DROP TABLE IF EXISTS `organization`;
CREATE TABLE `organization` (
 `id` INT NOT NULL PRIMARY KEY,
 `code` CHAR(10) NOT NULL,
 `name` VARCHAR(32),
 `parent` INT,
 `startDate` DATE,
 `endDate` DATE,
 `deleteFlg` BIT(10),

 FOREIGN KEY (`parent`) REFERENCES `organization` (`id`)
);


DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
 `id` INT NOT NULL PRIMARY KEY,
 `name` VARCHAR(32),
 `shortName` CHAR(16),
 `customerId` INT,
 `startDate` DATE,
 `endDate` DATE,
 `deleteFlg` BIT(10),

 FOREIGN KEY (`customerId`) REFERENCES `customer` (`id`)
);


DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
 `id` INT NOT NULL PRIMARY KEY,
 `code` CHAR(6),
 `orgId` INT NOT NULL,
 `name` VARCHAR(32),
 `sex` BIT(10),
 `national` VARCHAR(16),
 `homeTown` CHAR(32),
 `birthday` DATE,
 `politicallandscape` VARCHAR(16),
 `maritalStatus` INT,
 `degree` VARCHAR(16),
 `graduationUniversity` VARCHAR(32),
 `discipline` VARCHAR(32),
 `telephone` CHAR(15),
 `deleteFlg` BIT(10),

 FOREIGN KEY (`orgId`) REFERENCES `organization` (`id`)
);


DROP TABLE IF EXISTS `userInProject`;
CREATE TABLE `userInProject` (
 `employeeId` INT NOT NULL,
 `projectId` INT NOT NULL,
 `startDate` DATE,
 `endDate` DATE,
 `deleteFlg` BIT(10),

 PRIMARY KEY (`employeeId`,`projectId`),

 FOREIGN KEY (`employeeId`) REFERENCES `employee` (`id`),
 FOREIGN KEY (`projectId`) REFERENCES `project` (`id`)
);


DROP TABLE IF EXISTS `effort`;
CREATE TABLE `effort` (
 `id` INT NOT NULL PRIMARY KEY,
 `employeeId` INT,
 `projectId` INT,
 `customerId` INT,
 `categoryId` INT,
 `subCategoryId` INT,
 `startDate` CHAR(10),
 `duration` CHAR(10),
 `detail` CHAR(10),

 FOREIGN KEY (`employeeId`) REFERENCES `employee` (`id`),
 FOREIGN KEY (`projectId`) REFERENCES `project` (`id`),
 FOREIGN KEY (`customerId`) REFERENCES `customer` (`id`),
 FOREIGN KEY (`categoryId`) REFERENCES `category` (`id`),
 FOREIGN KEY (`subCategoryId`) REFERENCES `subCategory` (`id`)
);


COMMENT ON TABLE `category` IS '大分类';
COMMENT ON COLUMN `category`.`id` IS '大分类ID';
COMMENT ON COLUMN `category`.`name` IS '名称';
COMMENT ON COLUMN `category`.`shortName` IS '略称';
COMMENT ON COLUMN `category`.`deleteFlg` IS '删除标志';
COMMENT ON TABLE `customer` IS '客户';
COMMENT ON COLUMN `customer`.`id` IS '客户ID';
COMMENT ON COLUMN `customer`.`name` IS '名称';
COMMENT ON COLUMN `customer`.`shortName` IS '略称';
COMMENT ON COLUMN `customer`.`deleteFlg` IS '删除标志';
COMMENT ON TABLE `subCategory` IS '小分类';
COMMENT ON COLUMN `subCategory`.`id` IS '小分类ID';
COMMENT ON COLUMN `subCategory`.`categoryId` IS '大分类ID';
COMMENT ON COLUMN `subCategory`.`name` IS '名称';
COMMENT ON COLUMN `subCategory`.`shortName` IS '略称';
COMMENT ON COLUMN `subCategory`.`deleteFlg` IS '删除标志';
COMMENT ON TABLE `organization` IS '组织';
COMMENT ON COLUMN `organization`.`id` IS '组织ID';
COMMENT ON COLUMN `organization`.`code` IS '组织编码';
COMMENT ON COLUMN `organization`.`name` IS '组织名';
COMMENT ON COLUMN `organization`.`parent` IS '上级组织';
COMMENT ON COLUMN `organization`.`startDate` IS '开始时间';
COMMENT ON COLUMN `organization`.`endDate` IS '结束时间';
COMMENT ON COLUMN `organization`.`deleteFlg` IS '删除标志';
COMMENT ON TABLE `project` IS '项目';
COMMENT ON COLUMN `project`.`id` IS '项目ID';
COMMENT ON COLUMN `project`.`name` IS '名称';
COMMENT ON COLUMN `project`.`shortName` IS '略称';
COMMENT ON COLUMN `project`.`customerId` IS '客户ID';
COMMENT ON COLUMN `project`.`startDate` IS '开始时间';
COMMENT ON COLUMN `project`.`endDate` IS '结束时间';
COMMENT ON COLUMN `project`.`deleteFlg` IS '删除标志';
COMMENT ON TABLE `employee` IS '员工';
COMMENT ON COLUMN `employee`.`id` IS '员工ID';
COMMENT ON COLUMN `employee`.`code` IS '员工号';
COMMENT ON COLUMN `employee`.`orgId` IS '组织ID';
COMMENT ON COLUMN `employee`.`name` IS '员工名';
COMMENT ON COLUMN `employee`.`sex` IS '性别';
COMMENT ON COLUMN `employee`.`national` IS '民族';
COMMENT ON COLUMN `employee`.`homeTown` IS '籍贯';
COMMENT ON COLUMN `employee`.`birthday` IS '出生年月日';
COMMENT ON COLUMN `employee`.`politicallandscape` IS '政治面貌';
COMMENT ON COLUMN `employee`.`maritalStatus` IS '婚姻状况';
COMMENT ON COLUMN `employee`.`degree` IS '最高学历';
COMMENT ON COLUMN `employee`.`graduationUniversity` IS '毕业院校';
COMMENT ON COLUMN `employee`.`discipline` IS '专业';
COMMENT ON COLUMN `employee`.`telephone` IS '联系电话';
COMMENT ON COLUMN `employee`.`deleteFlg` IS '删除标志';
COMMENT ON TABLE `userInProject` IS '项目人员安排';
COMMENT ON COLUMN `userInProject`.`employeeId` IS '员工ID';
COMMENT ON COLUMN `userInProject`.`projectId` IS '项目ID';
COMMENT ON COLUMN `userInProject`.`startDate` IS '开始时间';
COMMENT ON COLUMN `userInProject`.`endDate` IS '结束时间';
COMMENT ON COLUMN `userInProject`.`deleteFlg` IS '删除标志';
COMMENT ON TABLE `effort` IS 'Effort';
COMMENT ON COLUMN `effort`.`id` IS 'ID';
COMMENT ON COLUMN `effort`.`employeeId` IS '员工ID';
COMMENT ON COLUMN `effort`.`projectId` IS '项目ID';
COMMENT ON COLUMN `effort`.`customerId` IS '客户ID';
COMMENT ON COLUMN `effort`.`categoryId` IS '大分类ID';
COMMENT ON COLUMN `effort`.`subCategoryId` IS '小分类ID';
COMMENT ON COLUMN `effort`.`startDate` IS '日期';
COMMENT ON COLUMN `effort`.`duration` IS '时间';
COMMENT ON COLUMN `effort`.`detail` IS '详细';
