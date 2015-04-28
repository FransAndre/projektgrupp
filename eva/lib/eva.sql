BEGIN TRANSACTION;
CREATE TABLE `VMottagare` (
	`Aktivitet`	INTEGER,
	`Info`	TEXT,
	`Mottagare`	INTEGER
);
INSERT INTO `VMottagare` VALUES (1,'Trött',1);
INSERT INTO `VMottagare` VALUES (2,'Pigg',2);
CREATE TABLE `Personal` (
	`Namn`	TEXT,
	`Personnummer`	INTEGER,
	`ID`	INTEGER
);
INSERT INTO `Personal` VALUES ('Erik',8709055467,1);
INSERT INTO `Personal` VALUES ('Ulla',5408033882,2);
CREATE TABLE "Omsorgsakt" (
	`ID`	INTEGER,
	`Starttid`	INTEGER,
	`Sluttid`	INTEGER,
	`Datum`	INTEGER,
	`Ansvarig`	INTEGER
);
INSERT INTO `Omsorgsakt` VALUES (1,'14:30','14:50','06/14/15',1);
INSERT INTO `Omsorgsakt` VALUES (2,'14:30','14:50','06/11/15',2);
CREATE TABLE `Deltagare` (
	`Aktivitet`	INTEGER,
	`Mat`	INTEGER,
	`Info`	INTEGER,
	`Deltagare`	INTEGER
);
INSERT INTO `Deltagare` VALUES (1,'N','VIll ha kul',1);
INSERT INTO `Deltagare` VALUES (1,'Fisk','Trött',2);
INSERT INTO `Deltagare` VALUES (2,'Soppa',NULL,2);
CREATE TABLE "Boende" (
	`ID`	INTEGER,
	`Namn`	TEXT,
	`Personnum`	INTEGER
);
INSERT INTO `Boende` VALUES (1,'Johanna',3408055664);
INSERT INTO `Boende` VALUES (2,'Bertil',2508065448);
INSERT INTO `Boende` VALUES (3,'Christina',2407086778);
CREATE TABLE "AnhBo" (
	`anh`	INTEGER,
	`boende`	INTEGER
);
INSERT INTO `AnhBo` VALUES (1,1);
INSERT INTO `AnhBo` VALUES (2,2);
INSERT INTO `AnhBo` VALUES (2,3);
CREATE TABLE "Anh" (
	`Namn`	TEXT,
	`Tel`	INTEGER,
	`Personnum`	INTEGER,
	`ID`	INTEGER
);
INSERT INTO `Anh` VALUES ('Erik',23765632,8708044334,1);
INSERT INTO `Anh` VALUES ('Malin',346537,7806075546,2);
CREATE TABLE "Aktiviteter" (
	`ID`	INTEGER,
	`Starttid`	TEXT,
	`Sluttid`	TEXT,
	`Datum`	TEXT,
	`Kostnad`	INTEGER,
	`Ansvarig`	INTEGER,
	`AktNamn`	INTEGER
);
INSERT INTO `Aktiviteter` VALUES (1,'13:00','17:00','06/14/15',43,2,'Festen');
INSERT INTO `Aktiviteter` VALUES (2,'12:00','13:00','06/16/15',20,1,'Lunch');
COMMIT;
