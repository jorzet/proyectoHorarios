use mydb;

/* INSERTAR HORARIOS*/
CALL insertTimes('Lunes', '7:00 - 8:00', @response);
select @response;
CALL insertTimes('Lunes', '8:00 - 9:00', @response);
select @response;
CALL insertTimes('Lunes', '9:00 - 10:00', @response);
select @response;
CALL insertTimes('Lunes', '10:00 - 11:00', @response);
select @response;
CALL insertTimes('Lunes', '11:00 - 12:00', @response);
select @response;
CALL insertTimes('Lunes', '12:00 - 13:00', @response);
select @response;
CALL insertTimes('Lunes', '13:00 - 14:00', @response);
select @response;
CALL insertTimes('Lunes', '14:00 - 15:00', @response);
select @response;
CALL insertTimes('Lunes', '15:00 - 16:00', @response);
select @response;
CALL insertTimes('Lunes', '16:00 - 17:00', @response);
select @response;
CALL insertTimes('Lunes', '17:00 - 18:00', @response);
select @response;
CALL insertTimes('Lunes', '18:00 - 19:00', @response);
select @response;
CALL insertTimes('Lunes', '19:00 - 20:00', @response);
select @response;



CALL insertTimes('Martes', '7:00 - 8:00', @response);
select @response;
CALL insertTimes('Martes', '8:00 - 9:00', @response);
select @response;
CALL insertTimes('Martes', '9:00 - 10:00', @response);
select @response;
CALL insertTimes('Martes', '10:00 - 11:00', @response);
select @response;
CALL insertTimes('Martes', '11:00 - 12:00', @response);
select @response;
CALL insertTimes('Martes', '12:00 - 13:00', @response);
select @response;
CALL insertTimes('Martes', '13:00 - 14:00', @response);
select @response;
CALL insertTimes('Martes', '14:00 - 15:00', @response);
select @response;
CALL insertTimes('Martes', '15:00 - 16:00', @response);
select @response;
CALL insertTimes('Martes', '16:00 - 17:00', @response);
select @response;
CALL insertTimes('Martes', '17:00 - 18:00', @response);
select @response;
CALL insertTimes('Martes', '18:00 - 19:00', @response);
select @response;
CALL insertTimes('Martes', '19:00 - 20:00', @response);
select @response;

CALL insertTimes('Miercoles', '7:00 - 8:00', @response);
select @response;
CALL insertTimes('Miercoles', '8:00 - 9:00', @response);
select @response;
CALL insertTimes('Miercoles', '9:00 - 10:00', @response);
select @response;
CALL insertTimes('Miercoles', '10:00 - 11:00', @response);
select @response;
CALL insertTimes('Miercoles', '11:00 - 12:00', @response);
select @response;
CALL insertTimes('Miercoles', '12:00 - 13:00', @response);
select @response;
CALL insertTimes('Miercoles', '13:00 - 14:00', @response);
select @response;
CALL insertTimes('Miercoles', '14:00 - 15:00', @response);
select @response;
CALL insertTimes('Miercoles', '15:00 - 16:00', @response);
select @response;
CALL insertTimes('Miercoles', '16:00 - 17:00', @response);
select @response;
CALL insertTimes('Miercoles', '17:00 - 18:00', @response);
select @response;
CALL insertTimes('Miercoles', '18:00 - 19:00', @response);
select @response;
CALL insertTimes('Miercoles', '19:00 - 20:00', @response);
select @response;


CALL insertTimes('Jueves', '7:00 - 8:00', @response);
select @response;
CALL insertTimes('Jueves', '8:00 - 9:00', @response);
select @response;
CALL insertTimes('Jueves', '9:00 - 10:00', @response);
select @response;
CALL insertTimes('Jueves', '10:00 - 11:00', @response);
select @response;
CALL insertTimes('Jueves', '11:00 - 12:00', @response);
select @response;
CALL insertTimes('Jueves', '12:00 - 13:00', @response);
select @response;
CALL insertTimes('Jueves', '13:00 - 14:00', @response);
select @response;
CALL insertTimes('Jueves', '14:00 - 15:00', @response);
select @response;
CALL insertTimes('Jueves', '15:00 - 16:00', @response);
select @response;
CALL insertTimes('Jueves', '16:00 - 17:00', @response);
select @response;
CALL insertTimes('Jueves', '17:00 - 18:00', @response);
select @response;
CALL insertTimes('Jueves', '18:00 - 19:00', @response);
select @response;
CALL insertTimes('Jueves', '19:00 - 20:00', @response);
select @response;

CALL insertTimes('Viernes', '7:00 - 8:00', @response);
select @response;
CALL insertTimes('Viernes', '8:00 - 9:00', @response);
select @response;
CALL insertTimes('Viernes', '9:00 - 10:00', @response);
select @response;
CALL insertTimes('Viernes', '10:00 - 11:00', @response);
select @response;
CALL insertTimes('Viernes', '11:00 - 12:00', @response);
select @response;
CALL insertTimes('Viernes', '12:00 - 13:00', @response);
select @response;
CALL insertTimes('Viernes', '13:00 - 14:00', @response);
select @response;
CALL insertTimes('Viernes', '14:00 - 15:00', @response);
select @response;
CALL insertTimes('Viernes', '15:00 - 16:00', @response);
select @response;
CALL insertTimes('Viernes', '16:00 - 17:00', @response);
select @response;
CALL insertTimes('Viernes', '17:00 - 18:00', @response);
select @response;
CALL insertTimes('Viernes', '18:00 - 19:00', @response);
select @response;
CALL insertTimes('Viernes', '19:00 - 20:00', @response);
select @response;

/* INSERTAR PROFESORES*/
CALL insertTeacher('Ernesto Alonso','Solis','Galindo', @response);
select @response;
CALL insertTeacher('Luis Rodolfo','Coello','Galindo', @response);
select @response;
CALL insertTeacher('Jorge','Bautista','Lopez', @response);
select @response;
CALL insertTeacher('Jorge Manuel','Ortega','Martinez', @response);
select @response;
CALL insertTeacher('Israel','Acuña','Galvan', @response);
select @response;
CALL insertTeacher('Ingrid Berenice','Reyes','Escudero', @response);
select @response;
CALL insertTeacher('Nestor Andes','Montiel','Hernandez', @response);
select @response;
CALL insertTeacher('Juana Neiry','Luna','Morales', @response);
select @response;
CALL insertTeacher('Irene','Campos','Gonzales', @response);
select @response;
CALL insertTeacher('Ernesto','Flores','Garcia', @response);
select @response;

/* INSERTAR AULAS*/

CALL insertRoom("Aula 1", 50, @response);
CALL insertRoom("Aula 2", 30, @response);
CALL insertRoom("Aula 3", 40, @response);
CALL insertRoom("Aula 4", 30, @response);
CALL insertRoom("Aula 5", 50, @response);
CALL insertRoom("Aula 6", 40, @response);
CALL insertRoom("Aula 7", 30, @response);
CALL insertRoom("Aula 8", 40, @response);
CALL insertRoom("Aula 9", 50, @response);
CALL insertRoom("Aula 10", 30, @response);
CALL insertRoom("Aula 11", 50, @response);
CALL insertRoom("Aula 12", 30, @response);
CALL insertRoom("Aula 13", 40, @response);
CALL insertRoom("Aula 14", 30, @response);
CALL insertRoom("Aula 15", 50, @response);
CALL insertRoom("Aula 16", 40, @response);
CALL insertRoom("Aula 17", 30, @response);
CALL insertRoom("Aula 18", 40, @response);
CALL insertRoom("Aula 19", 50, @response);
CALL insertRoom("Aula 20", 30, @response);
CALL insertRoom("Aula 21", 50, @response);
CALL insertRoom("Aula 22", 30, @response);
CALL insertRoom("Aula 23", 40, @response);
CALL insertRoom("Aula 24", 30, @response);
CALL insertRoom("Aula 25", 50, @response);
CALL insertRoom("Aula 26", 40, @response);
CALL insertRoom("Aula 27", 30, @response);
CALL insertRoom("Aula 28", 40, @response);
CALL insertRoom("Aula 29", 50, @response);
CALL insertRoom("Aula 30", 30, @response);

/* INSERTAR MODULOS*/

CALL insertModule("FP1", "Fundamentos de Programacion", 1, @response);
CALL insertModule("IME1", "Interaccion Materia y Energia", 3, @response);
CALL insertModule("EA1", "Electronica Analogica", 2, @response);
CALL insertModule("EcD1", "Ecuaciones Diferenciales", 4, @response);
CALL insertModule("AC1", "Arquitectira de Computadoras", 6, @response);
CALL insertModule("MN1", "Metodos Numericos", 7, @response);
CALL insertModule("EsD1", "Estructura de Datos", 5, @response);
CALL insertModule("CE1", "Causa y Efecto. Lengua Extranjera", 8, @response);
CALL insertModule("EP1", "En otras palabras...Lengua Extranjera", 9, @response);
CALL insertModule("CDI1", "Calculo Diferencial e Integral", 4, @response);
CALL insertModule("CDI1", "Calculo Diferencial e Integral", 5, @response);
CALL insertModule("CI1", "Capacitacion en la Industria", 1, @response);
CALL insertModule("NLR1", "Normas, Leyes y Reglamentos de Ingenieria en Computacion", 3, @response);
CALL insertModule("NLR1", "Normas, Leyes y Reglamentos de Ingenieria en Computacion", 7, @response);
CALL insertModule("O41", "Optativa 4(Implementacion de Protocolos de Comunicacion en Hardware)", 2, @response);
CALL insertModule("O41", "Optativa 4(Implementacion de Protocolos de Comunicacion en Hardware)", 6, @response);
CALL insertModule("RP1", "Redes y Protocolos Industriales", 2, @response);
CALL insertModule("RP1", "Redes y Protocolos Industriales", 10, @response);
CALL insertModule("AL1", "Algebra Lineal", 10, @response);

/* INSERTAR GRUPOS*/


CALL insertGroups("Grupo 1", 10, true, @response);
CALL insertGroups("Grupo 2", 20, true, @response);
CALL insertGroups("Grupo 3", 27, true, @response);
CALL insertGroups("Grupo 4", 32, true, @response);
CALL insertGroups("Grupo 5", 18, true, @response);
CALL insertGroups("Grupo 6",28, true, @response);
CALL insertGroups("Grupo 7",18, true, @response);
CALL insertGroups("Grupo 8",30, true, @response);
CALL insertGroups("Grupo 9",10, true, @response);
CALL insertGroups("Grupo 10",14, true, @response);
CALL insertGroups("Grupo 11",18, true, @response);
CALL insertGroups("Grupo 12",22, true, @response);
CALL insertGroups("Grupo 13",26, true, @response);
CALL insertGroups("Grupo 14",19, true, @response);
CALL insertGroups("Grupo 15",29, false, @response);
CALL insertGroups("Grupo 16",31, false, @response);
CALL insertGroups("Grupo 17",35, false, @response);
CALL insertGroups("Grupo 18",25, false, @response);
CALL insertGroups("Grupo 19",19, false, @response);
CALL insertGroups("Grupo 20",21, false, @response);

/* INSERTAR GRUPOS*/
CALL insertGroupsModuleTeacher(1, 1, 1, @response);
CALL insertGroupsModuleTeacher(1, 2, 3, @response);
CALL insertGroupsModuleTeacher(1, 3, 2, @response);
CALL insertGroupsModuleTeacher(1, 4, 4, @response);

CALL insertGroupsModuleTeacher(2, 5, 6, @response);
CALL insertGroupsModuleTeacher(2, 6, 7, @response);
CALL insertGroupsModuleTeacher(2, 7, 5, @response);
CALL insertGroupsModuleTeacher(2, 8, 8, @response);

CALL insertGroupsModuleTeacher(3, 5, 6, @response);
CALL insertGroupsModuleTeacher(3, 2, 3, @response);
CALL insertGroupsModuleTeacher(3, 8, 8, @response);
CALL insertGroupsModuleTeacher(3, 9, 9, @response);

CALL insertGroupsModuleTeacher(4, 1, 1, @response);
CALL insertGroupsModuleTeacher(4, 12, 1, @response);
CALL insertGroupsModuleTeacher(4, 9, 9, @response);
CALL insertGroupsModuleTeacher(4, 15, 2, @response);

CALL insertGroupsModuleTeacher(5, 12, 1, @response);
CALL insertGroupsModuleTeacher(5, 14, 7, @response);
CALL insertGroupsModuleTeacher(5, 13, 3, @response);
CALL insertGroupsModuleTeacher(5, 9, 9, @response);

CALL insertGroupsModuleTeacher(6, 1, 1, @response);
CALL insertGroupsModuleTeacher(7, 1, 1, @response);
CALL insertGroupsModuleTeacher(8, 1, 1, @response);
CALL insertGroupsModuleTeacher(9, 1, 1, @response);
CALL insertGroupsModuleTeacher(10, 1, 1, @response);
CALL insertGroupsModuleTeacher(11, 1, 1, @response);
CALL insertGroupsModuleTeacher(12, 1, 1, @response);
CALL insertGroupsModuleTeacher(13, 1, 1, @response);
CALL insertGroupsModuleTeacher(14, 1, 1, @response);
CALL insertGroupsModuleTeacher(15, 1, 1, @response);
CALL insertGroupsModuleTeacher(16, 1, 1, @response);
CALL insertGroupsModuleTeacher(17, 1, 1, @response);
CALL insertGroupsModuleTeacher(18, 1, 1, @response);
CALL insertGroupsModuleTeacher(19, 1, 1, @response);
CALL insertGroupsModuleTeacher(20, 1, 1, @response);
