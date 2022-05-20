use mydb;

/* CREACION DE PRODEDIMIENTOS ALMACENADOS PARA AÑADIR*/

DELIMITER $$
CREATE PROCEDURE insertTimes(IN dayname VARCHAR(45),IN times VARCHAR(45), OUT response VARCHAR(100))
BEGIN
	IF NOT EXISTS (SELECT t.idTimes FROM mydb.times as t WHERE t.times = times AND t.dayname = dayname)
	THEN
		INSERT INTO mydb.times (dayname, times)
		VALUES (dayname, times);
		SET response='Horario registrado correctamente';
	ELSE
		SET response='El horario ya se ha registrado';
	END IF;
END $$

DELIMITER $$
CREATE PROCEDURE insertTeacher(IN teacherName VARCHAR(45), IN teacherFirstLastName VARCHAR(45), IN teacherSecondLastName VARCHAR(45), OUT response VARCHAR(100))
BEGIN
  INSERT INTO mydb.teacher (teacherName, teacherFirstLastName, teacherSecondLastName)
  VALUES (teacherName, teacherFirstLastName, teacherSecondLastName);
  SET response='Profesor registrado correctamente';
END $$

DELIMITER $$
CREATE PROCEDURE insertRoom(IN roomName VARCHAR(45), IN capacity integer, OUT response VARCHAR(100))
BEGIN
	IF NOT EXISTS (SELECT r.idRoom FROM mydb.room as r WHERE r.roomName=roomName)
	THEN
		INSERT INTO mydb.room (roomName, capacity)
		VALUES (roomName, capacity);
		SET response='Aula registrada correctamente';
  ELSE 
		SET response='ya existe un aula registrada con ese nombre';
  END IF;
END $$

DELIMITER $$
CREATE PROCEDURE insertModule(IN moduleCode VARCHAR(100), IN moduleName VARCHAR(200), IN teacherId int, OUT response VARCHAR(100))
BEGIN
  INSERT INTO mydb.module (moduleCode, moduleName, Teacher_idTeacher)
  VALUES (moduleCode, moduleName, teacherId);
  SET response='Modulo registrado correctamente';
END $$

DELIMITER $$
CREATE PROCEDURE insertGroups(IN name VARCHAR(45), IN size VARCHAR(45), IN matutino boolean, OUT response VARCHAR(100))
BEGIN
  INSERT INTO mydb.group (name, size, matutino)
  VALUES (name, size, matutino);
  SET response='Grupo registrado correctamente';
END $$

DELIMITER $$
CREATE PROCEDURE insertGroupsModuleTeacher(IN idGroup INT, IN idModule INT, IN idTeacher INT, IN times INT, OUT response VARCHAR(100))
BEGIN
  INSERT INTO mydb.Group_has_Module (Group_idGroup, Module_idModule, Module_Teacher_idTeacher, times)
  VALUES (idGroup, idModule, idTeacher, times);
  SET response='Modulo y grupo registrado correctamente';
END $$

DELIMITER $$
CREATE PROCEDURE insertTimesGroup(
IN classNumber VARCHAR(45), 
IN groupNumber VARCHAR(45), 
IN moduleName VARCHAR(100), 
IN moduleCode VARCHAR(45), 
IN roomCode VARCHAR(45), 
IN teacherName VARCHAR(45), 
IN time VARCHAR(45), 
IN day VARCHAR(45), 

OUT response VARCHAR(100))
BEGIN
  INSERT INTO mydb.TimesGroup (classNumber, groupNumber, moduleName, moduleCode, roomCode, teacherName, time, day)
  VALUES (classNumber, groupNumber, moduleName, moduleCode, roomCode, teacherName, time, day);
  SET response='Horario registrado correctamente';
END $$

/* CREACION DE PRODEDIMIENTOS ALMACENADOS PARA ELIMINAR*/

/* CREACION DE PRODEDIMIENTOS ALMACENADOS PARA EDITAR*/

/* CREACION DE PRODEDIMIENTOS ALMACENADOS PARA OBTENER*/

DELIMITER $$
CREATE PROCEDURE getTeacherById(IN idTeacher integer, OUT response VARCHAR(100))
BEGIN
	IF EXISTS (SELECT t.idTeacher FROM mydb.teacher as t where t.idTeacher = idTeacher)
	THEN
		SELECT * FROM mydb.teacher as teacher where teacher.idTeacher = idTeacher;
		SET response = 'OK';
	ELSE
		SET response = 'No existe el profesor';
	END IF;
END $$

DELIMITER $$
CREATE PROCEDURE getAllTeachers(OUT response VARCHAR(100))
BEGIN
	IF EXISTS (SELECT t.idTeacher FROM mydb.teacher as t)
	THEN
		SELECT * FROM mydb.teacher as teacher where teacher.idTeacher = idTeacher;
		SET response = 'OK';
	ELSE
		SET response = 'No hay profesores en la BD';
	END IF;
END $$

DELIMITER $$
CREATE PROCEDURE getAllTeachersNames(OUT response VARCHAR(100))
BEGIN
	IF EXISTS (SELECT t.idTeacher FROM mydb.teacher as t)
	THEN
		SELECT teacher.teacherName, teacherFirstLastName, teacher.teacherSecondLastName FROM mydb.teacher as teacher;
		SET response = 'OK';
	ELSE
		SET response = 'No hay profesores en la BD';
	END IF;
END $$

DELIMITER $$
CREATE PROCEDURE getAllRooms(OUT response VARCHAR(100))
BEGIN
	IF EXISTS (SELECT r.idRoom FROM mydb.room as r)
	THEN
		SELECT * FROM mydb.room as room;
		SET response = 'OK';
	ELSE
		SET response = 'No hay aulas en la BD';
	END IF;
END $$

DELIMITER $$
CREATE PROCEDURE getAllTimes(OUT response VARCHAR(100))
BEGIN
	IF EXISTS (SELECT t.idTimes FROM mydb.times as t)
	THEN
		SELECT * FROM mydb.times as time;
		SET response = 'OK';
	ELSE
		SET response = 'No hay horarios en la BD';
	END IF;
END $$


DELIMITER $$
CREATE PROCEDURE getAllModules(OUT response VARCHAR(100))
BEGIN
	IF EXISTS (SELECT m.idModule FROM mydb.module as m)
	THEN
		SELECT * FROM mydb.module as module;
		SET response = 'OK';
	ELSE
		SET response = 'No hay modulos en la BD';
	END IF;
END $$

DELIMITER $$
CREATE PROCEDURE getAllGroups(OUT response VARCHAR(100))
BEGIN
	IF EXISTS (SELECT g.idGroup FROM mydb.group as g)
	THEN
		SELECT * FROM mydb.group;
		SET response = 'OK';
	ELSE
		SET response = 'No hay grupos en la BD';
	END IF;
END $$

DELIMITER $$
CREATE PROCEDURE getAllModulesAndGroups(OUT response VARCHAR(100))
BEGIN
	IF EXISTS (SELECT g.Group_idGroup FROM mydb.Group_has_Module as g)
	THEN
		SELECT * FROM mydb.Group_has_Module;
		SET response = 'OK';
	ELSE
		SET response = 'No hay grupos en la BD';
	END IF;
END $$


DELIMITER $$
CREATE PROCEDURE getAllModuleIdbyGroupId(IN groupId int, OUT response VARCHAR(100))
BEGIN
	IF EXISTS (SELECT ghm.Module_idModule FROM mydb.Group_has_Module as ghm WHERE ghm.Group_idGroup=groupId)
	THEN
		SELECT ghm.Module_idModule, ghm.times FROM mydb.Group_has_Module as ghm WHERE ghm.Group_idGroup=groupId;
		SET response = 'OK';
	ELSE
		SET response = 'No hay grupos relacionados al moduleId en la BD';
	END IF;
END $$

DELIMITER $$
CREATE PROCEDURE getAllTeachersIdByModuleCode(IN moduleCode VARCHAR(45), OUT response VARCHAR(100))
BEGIN
	IF EXISTS (SELECT t.idTeacher FROM mydb.module as m JOIN mydb.teacher as t WHERE m.Teacher_idTeacher=t.idTeacher AND m.moduleCode=moduleCode)
	THEN
		SELECT t.idTeacher FROM mydb.module as m JOIN mydb.teacher as t WHERE m.Teacher_idTeacher=t.idTeacher AND m.moduleCode=moduleCode;
		SET response = 'OK';
	ELSE
		SET response = 'No hay grupos relacionados al moduleId en la BD';
	END IF;
END $$

DELIMITER $$
CREATE PROCEDURE getTimeByGroupNumber(IN groupNumber VARCHAR(45), OUT response VARCHAR(100))
BEGIN
	IF EXISTS (SELECT tg.idTimesGroup FROM mydb.TimesGroup as tg WHERE tg.groupNumber=groupNumber)
	THEN
		SELECT tg.classNumber, tg.groupNumber, tg.moduleName, tg.moduleCode, tg.roomCode, tg.teacherName, tg.time, tg.day FROM mydb.TimesGroup as tg WHERE tg.groupNumber=groupNumber;
		SET response = 'OK';
	ELSE
		SET response = 'No hay horarios relacionados al moduleId en la BD';
	END IF;
END $$




/* ELIMINAR PROCEDIMIENTOS ALMACENADOS*/
##DROP PROCEDURE insertGroups;
##DROP PROCEDURE insertTimes;

/*LLAMAR PRODECIMIENTOS ALMACENADOS*/


CALL getAllTeachers(@response);
select @response
CALL getTeacherById(1, @response);
select @response
CALL getAllTeachersNames(@response);
select @response
CALL getAllRooms(@response);
select @response
CALL getAllTimes(@response);
select @response
CALL getAllModules(@response);
select @response
CALL getAllGroups(@response);
select @response
CALL getAllModuleIdByGroupId(1, @response);
select @response;
CALL getAllTeachersIdByModuleId(1, @response);
select @response

CALL getAllModuleIdbyGroupId(1, @response);
select @response

