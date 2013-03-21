use exam;

ALTER TABLE `exam`.`admission` ADD COLUMN `score` DOUBLE NULL DEFAULT 0  AFTER `printFlg` ;