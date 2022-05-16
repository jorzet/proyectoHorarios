package com.horarios.horariosapp.repository;

import com.horarios.horariosapp.data.*;

import java.sql.*;
import java.util.ArrayList;

// Data access object
public class Dao {
    Connection connection = null;
    CallableStatement sp=null;
    Statement stmt = null;
    ResultSet rs = null;
    String SQL = null;
    ResultSetMetaData rmeta = null;
    String resultado = null;
    public Dao(){}

    public ArrayList<Profesor> getAllTeachers() {
        connection = new Connection();
        ArrayList<Profesor> profesors = new ArrayList();
        int f = 0;
        try {
            if (connection.initConnection() != null) {
                // hace referencia a el procedimiento almacenado de la BD
                SQL = "{call getAllTeachers (?)}";
                sp = connection.conexion.prepareCall(SQL);
                sp.setEscapeProcessing(true);
                sp.setQueryTimeout(20);
                sp.registerOutParameter(1, java.sql.Types.NVARCHAR);
                rs = sp.executeQuery();
                resultado = sp.getString(1);
                if(resultado.equals("OK")){
                    while(rs.next()){
                        f = f+1;
                    }
                    if(f == 0){
                        System.out.println("Es nulo"+rs);
                    }
                    else{
                        rs.beforeFirst();
                        System.out.println("No es nulo"+rs);
                        while(rs.next()) {
                            Profesor profesor = new Profesor();
                            profesor.setProfessorId(rs.getInt(1));
                            profesor.setProfessorName(rs.getNString(2));
                            profesor.setProfesorFirstLastName(rs.getNString(3));
                            profesor.setProfesorSecondtLastName(rs.getNString(4));
                            profesors.add(profesor);
                        }
                        return profesors;
                    }
                }
                else
                    return null;

            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                connection.closeConnection();
                if(sp != null)
                    sp.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public ArrayList<GroupModule> getAllModulesAndGroups() {
        connection = new Connection();
        ArrayList<GroupModule> groupModules = new ArrayList();
        int f = 0;
        try {
            if (connection.initConnection() != null) {
                // hace referencia a el procedimiento almacenado de la BD
                SQL = "{call getAllModulesAndGroups (?)}";
                sp = connection.conexion.prepareCall(SQL);
                sp.setEscapeProcessing(true);
                sp.setQueryTimeout(20);
                sp.registerOutParameter(1, java.sql.Types.NVARCHAR);
                rs = sp.executeQuery();
                resultado = sp.getString(1);
                if(resultado.equals("OK")){
                    while(rs.next()){
                        f = f+1;
                    }
                    if(f == 0){
                        System.out.println("Es nulo"+rs);
                    }
                    else{
                        rs.beforeFirst();
                        System.out.println("No es nulo"+rs);
                        while(rs.next()) {
                            GroupModule groupModule = new GroupModule();
                            groupModule.setIdGroup(rs.getInt(1));
                            groupModule.setIdModule(rs.getInt(2));
                            groupModule.setIdTeacher(rs.getInt(3));
                            groupModules.add(groupModule);
                        }
                        return groupModules;
                    }
                }
                else
                    return null;

            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                connection.closeConnection();
                if(sp != null)
                    sp.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public ArrayList<Integer> getAllTeachersIdByModuleCode(String moduleCode) {
        connection = new Connection();
        ArrayList<Integer> teachersId = new ArrayList<>();
        int f = 0;
        try {
            if (connection.initConnection() != null) {
                SQL = "{call getAllTeachersIdByModuleCode (?,?)}";
                sp = connection.conexion.prepareCall(SQL);
                sp.setEscapeProcessing(true);
                sp.setQueryTimeout(20);
                sp.setString(1, moduleCode);
                sp.registerOutParameter(2, java.sql.Types.NVARCHAR);
                rs = sp.executeQuery();
                resultado = sp.getString(2);
                if(resultado.equals("OK")){
                    while(rs.next()){
                        f = f+1;
                    }
                    if(f == 0){
                        System.out.println("Es nulo"+rs);
                    }
                    else{
                        rs.beforeFirst();
                        System.out.println("No es nulo"+rs);
                        while(rs.next()) {
                            teachersId.add(rs.getInt(1));

                        }
                        return teachersId;
                    }
                }
                else
                    return null;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                connection.closeConnection();
                if(sp != null)
                    sp.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public ArrayList<Aula> getAllRooms() {
        connection = new Connection();
        ArrayList<Aula> rooms = new ArrayList();
        int f = 0;
        try {
            if (connection.initConnection() != null) {
                SQL = "{call getAllRooms (?)}";
                sp = connection.conexion.prepareCall(SQL);
                sp.setEscapeProcessing(true);
                sp.setQueryTimeout(20);
                sp.registerOutParameter(1, java.sql.Types.NVARCHAR);
                rs = sp.executeQuery();
                resultado = sp.getString(1);
                if(resultado.equals("OK")){
                    while(rs.next()){
                        f = f+1;
                    }
                    if(f == 0){
                        System.out.println("Es nulo"+rs);
                    }
                    else{
                        rs.beforeFirst();
                        System.out.println("No es nulo"+rs);
                        while(rs.next()) {
                            Aula room = new Aula();
                            room.setRoomId(rs.getInt(1));
                            room.setRoomNumber(rs.getNString(2));
                            room.setCapacity(rs.getInt(3));
                            rooms.add(room);
                        }
                        return rooms;
                    }
                }
                else
                    return null;

            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                connection.closeConnection();
                if(sp != null)
                    sp.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public ArrayList<Horario> getAllTimes() {
        connection = new Connection();
        ArrayList<Horario> times = new ArrayList<Horario>();
        int f = 0;
        try {
            if (connection.initConnection() != null) {
                SQL = "{call getAllTimes (?)}";
                sp = connection.conexion.prepareCall(SQL);
                sp.setEscapeProcessing(true);
                sp.setQueryTimeout(20);
                sp.registerOutParameter(1, java.sql.Types.NVARCHAR);
                rs = sp.executeQuery();
                resultado = sp.getString(1);
                if(resultado.equals("OK")){
                    while(rs.next()){
                        f = f+1;
                    }
                    if(f == 0){
                        System.out.println("Es nulo"+rs);
                    }
                    else{
                        rs.beforeFirst();
                        System.out.println("No es nulo"+rs);
                        while(rs.next()) {
                            Horario time = new Horario();

                            time.setTimeslotId(rs.getInt(1));
                            time.setDay(rs.getNString(2));
                            time.setTimeslot(rs.getNString(3));
                            times.add(time);
                        }
                        return times;
                    }
                }
                else
                    return null;

            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                connection.closeConnection();
                if(sp != null)
                    sp.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public ArrayList<Modulo> getAllModules() {
        connection = new Connection();
        ArrayList<Modulo> modules = new ArrayList();
        int f = 0;
        try {
            if (connection.initConnection() != null) {
                SQL = "{call getAllModules (?)}";
                sp = connection.conexion.prepareCall(SQL);
                sp.setEscapeProcessing(true);
                sp.setQueryTimeout(20);
                sp.registerOutParameter(1, java.sql.Types.NVARCHAR);
                rs = sp.executeQuery();
                resultado = sp.getString(1);
                if(resultado.equals("OK")){
                    while(rs.next()){
                        f = f+1;
                    }
                    if(f == 0){
                        System.out.println("Es nulo"+rs);
                    }
                    else{
                        rs.beforeFirst();
                        System.out.println("No es nulo"+rs);
                        while(rs.next()) {
                            Modulo module = new Modulo();
                            module.setModuleId(rs.getInt(1));
                            module.setModuleCode(rs.getNString(2));
                            module.setModule(rs.getNString(3));

                            modules.add(module);
                        }
                        return modules;
                    }
                }
                else
                    return null;

            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                connection.closeConnection();
                if(sp != null)
                    sp.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public ArrayList<Integer> getAllModulesByGroupId(int groupId) {
        connection = new Connection();
        ArrayList<Integer> modulesId = new ArrayList<>();
        int f = 0;
        try {
            if (connection.initConnection() != null) {
                SQL = "{call getAllModuleIdByGroupId (?,?)}";
                sp = connection.conexion.prepareCall(SQL);
                sp.setEscapeProcessing(true);
                sp.setQueryTimeout(20);
                sp.setInt(1, groupId);
                sp.registerOutParameter(2, java.sql.Types.NVARCHAR);
                rs = sp.executeQuery();
                resultado = sp.getString(2);
                if(resultado.equals("OK")){
                    while(rs.next()){
                        f = f+1;
                    }
                    if(f == 0){
                        System.out.println("Es nulo"+rs);
                    }
                    else{
                        rs.beforeFirst();
                        System.out.println("No es nulo"+rs);
                        while(rs.next()) {
                            modulesId.add(rs.getInt(1));

                        }
                        return modulesId;
                    }
                }
                else
                    return null;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                connection.closeConnection();
                if(sp != null)
                    sp.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public ArrayList<Grupo> getAllGroups() {
        connection = new Connection();
        ArrayList<Grupo> groups = new ArrayList();
        int f = 0;
        try {
            if (connection.initConnection() != null) {
                SQL = "{call getAllGroups (?)}";
                sp = connection.conexion.prepareCall(SQL);
                sp.setEscapeProcessing(true);
                sp.setQueryTimeout(20);
                sp.registerOutParameter(1, java.sql.Types.NVARCHAR);
                rs = sp.executeQuery();
                resultado = sp.getString(1);
                if(resultado.equals("OK")){
                    while(rs.next()){
                        f = f+1;
                    }
                    if(f == 0){
                        System.out.println("Es nulo"+rs);
                    }
                    else{
                        rs.beforeFirst();
                        System.out.println("No es nulo"+rs);
                        while(rs.next()) {
                            Grupo group = new Grupo();
                            group.setGroupId(rs.getInt(1));
                            group.setGroupName(rs.getNString(2));
                            group.setGroupSize(Integer.parseInt(rs.getNString(3)));
                            group.setMatutino(rs.getBoolean(4));
                            //group.setModuleIds(new int[]{rs.getInt(3)});

                            groups.add(group);
                        }
                        return groups;
                    }
                }
                else
                    return null;

            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                connection.closeConnection();
                if(sp != null)
                    sp.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public ArrayList<TimesResult> getTimesByGroupNumber(String groupNumber) {
        connection = new Connection();
        ArrayList<TimesResult> timesResults = new ArrayList<>();
        int f = 0;
        try {
            if (connection.initConnection() != null) {
                SQL = "{call getTimeByGroupNumber (?,?)}";
                sp = connection.conexion.prepareCall(SQL);
                sp.setEscapeProcessing(true);
                sp.setQueryTimeout(20);
                sp.setString(1, groupNumber);
                sp.registerOutParameter(2, java.sql.Types.NVARCHAR);
                rs = sp.executeQuery();
                resultado = sp.getString(2);
                if(resultado.equals("OK")){
                    while(rs.next()){
                        f = f+1;
                    }
                    if(f == 0){
                        System.out.println("Es nulo"+rs);
                    }
                    else{
                        rs.beforeFirst();
                        System.out.println("No es nulo"+rs);
                        while(rs.next()) {
                            TimesResult timesResult = new TimesResult();
                            timesResult.setClassNumber(rs.getString(1));
                            timesResult.setClassNumber(rs.getString(2));
                            timesResult.setClassNumber(rs.getString(3));
                            timesResult.setClassNumber(rs.getString(4));
                            timesResult.setClassNumber(rs.getString(5));
                            timesResult.setClassNumber(rs.getString(6));
                            timesResult.setClassNumber(rs.getString(7));
                            timesResult.setClassNumber(rs.getString(8));
                            timesResults.add(timesResult);
                        }
                        return timesResults;
                    }
                }
                else
                    return null;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                connection.closeConnection();
                if(sp != null)
                    sp.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }


    public String insertRoom(Aula room) {
        connection = new Connection();
        String result = "";
        try {
            if (connection.initConnection() != null) {
                SQL = "{call insertRoom (?,?,?)}";
                sp = connection.conexion.prepareCall(SQL);
                sp.setEscapeProcessing(true);
                sp.setQueryTimeout(20);
                sp.setString(1, room.getRoomNumber());
                sp.setInt(2, room.getRoomCapacity());
                sp.registerOutParameter(3, java.sql.Types.VARCHAR);
                sp.execute();
                result = sp.getString(3);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                connection.closeConnection();
                if(sp != null)
                    sp.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return result;
    }

    public String insertTime(Horario time) {
        connection = new Connection();
        String result = "";
        try {
            if (connection.initConnection() != null) {
                SQL = "{call insertTimes (?,?,?)}";
                sp = connection.conexion.prepareCall(SQL);
                sp.setEscapeProcessing(true);
                sp.setQueryTimeout(20);
                sp.setString(1, time.getDay());
                sp.setString(2, time.getTimeslot());
                sp.registerOutParameter(3, java.sql.Types.VARCHAR);
                sp.execute();
                result = sp.getString(3);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                connection.closeConnection();
                if(sp != null)
                    sp.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return result;
    }

    public String insertTeacher(Profesor teacher) {
        connection = new Connection();
        String result = "";
        try {
            if (connection.initConnection() != null) {
                SQL = "{call insertTeacher (?,?,?,?)}";
                sp = connection.conexion.prepareCall(SQL);
                sp.setEscapeProcessing(true);
                sp.setQueryTimeout(20);
                sp.setString(1, teacher.getProfessorName());
                sp.setString(2, teacher.getProfesorFirstLastName());
                sp.setString(3, teacher.getProfesorSecondtLastName());
                sp.registerOutParameter(4, java.sql.Types.VARCHAR);
                sp.execute();
                result = sp.getString(4);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                connection.closeConnection();
                if(sp != null)
                    sp.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return result;
    }

    public String insertModule(Modulo module) {
        connection = new Connection();
        String result = "";
        try {
            if (connection.initConnection() != null) {
                SQL = "{call insertModule (?,?,?,?)}";
                sp = connection.conexion.prepareCall(SQL);
                sp.setEscapeProcessing(true);
                sp.setQueryTimeout(20);
                sp.setString(1, module.getModuleCode());
                sp.setString(2, module.getModuleName());
                sp.setInt(3, module.getRandomProfessorId());
                sp.registerOutParameter(4, java.sql.Types.VARCHAR);
                sp.execute();
                result = sp.getString(4);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                connection.closeConnection();
                if(sp != null)
                    sp.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return result;
    }

    public String insertGroup(Grupo group) {
        connection = new Connection();
        String result = "";
        try {
            if (connection.initConnection() != null) {
                SQL = "{call insertGroups (?,?,?,?)}";
                sp = connection.conexion.prepareCall(SQL);
                sp.setEscapeProcessing(true);
                sp.setQueryTimeout(20);
                sp.setString(1, group.getGroupName());
                sp.setInt(2, group.getGroupSize());
                sp.setBoolean(3, group.isMatutino());
                sp.registerOutParameter(4, java.sql.Types.VARCHAR);
                sp.execute();
                result = sp.getString(4);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                connection.closeConnection();
                if(sp != null)
                    sp.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return result;
    }

    public String insertTimesGroup(TimesResult timeResult) {
        connection = new Connection();
        String result = "";
        try {
            if (connection.initConnection() != null) {
                SQL = "{call insertGroups (?,?,?,?,?,?,?,?,?)}";
                sp = connection.conexion.prepareCall(SQL);
                sp.setEscapeProcessing(true);
                sp.setQueryTimeout(20);
                sp.setString(1, timeResult.getClassNumber());
                sp.setString(2, timeResult.getGroupNumber());
                sp.setString(3, timeResult.getModuleName());
                sp.setString(4, timeResult.getModuleCode());
                sp.setString(5, timeResult.getRoomCode());
                sp.setString(6, timeResult.getTeacherName());
                sp.setString(7, timeResult.getTime());
                sp.setString(8, timeResult.getDay());
                sp.registerOutParameter(9, java.sql.Types.VARCHAR);
                sp.execute();
                result = sp.getString(9);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                connection.closeConnection();
                if(sp != null)
                    sp.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return result;
    }

    public String insertGroupsModuleTeacher(int idGroup, int idModule, int idTeacher) {
        connection = new Connection();
        String result = "";
        try {
            if (connection.initConnection() != null) {
                SQL = "{call insertGroupsModuleTeacher (?,?,?,?)}";
                sp = connection.conexion.prepareCall(SQL);
                sp.setEscapeProcessing(true);
                sp.setQueryTimeout(20);
                sp.setInt(1, idGroup);
                sp.setInt(2, idModule);
                sp.setInt(3, idTeacher);
                sp.registerOutParameter(4, java.sql.Types.VARCHAR);
                sp.execute();
                result = sp.getString(4);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                connection.closeConnection();
                if(sp != null)
                    sp.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return result;
    }
}
