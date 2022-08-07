package com.example.schoolapi.repository;

import com.example.schoolapi.model.Groups;
import com.example.schoolapi.model.Students;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentsRepositoryImpl extends ConnectionDB implements  StudentRepository {
    ConnectionDB connexion = new ConnectionDB();

    @Override
    public List<Students> findAll() {
        ResultSet rs;
        List<Students> students = new ArrayList<>() ;
        try (Connection con = DriverManager.getConnection(connexion.Connex())) {
            PreparedStatement pst = con.prepareStatement("SELECT * FROM students");
            rs = pst.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    @Override
    public Students add(Students s) {
        PreparedStatement stm;
        List<Students> std = new ArrayList<>() ;
        try (Connection con = DriverManager.getConnection(connexion.Connex())) {

            stm = con.prepareStatement("insert into students (name, groups)" +
                    "values (?,?)");

            stm.setString(1, s.getName());
            stm.setLong(2, s.getGroups().getId());
            std.add(s) ;
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return (Students) std;
    }

    @Override
    public String deleteById(Long id) {
        try(Connection con = DriverManager.getConnection(connexion.Connex())){
            Statement pst = con.createStatement();
            pst.executeUpdate("delete from students where id = " + id) ;
            System.out.println("TASK HAS BEEN DELETED SUCCESSFULLY");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Students updateNameById(Long id, String newName) {
        List<Students> arr = new ArrayList<>() ;
        try (Connection con = DriverManager.getConnection(connexion.Connex())) {
            Statement pst = con.createStatement();
            pst.executeUpdate("UPDATE students SET name" + "=" + newName + "where id = " + id);
            arr.add(id , newName , new Groups(Students.getGroups().getId() , Students.getGroups().getName() , Students.getGroups().getCreationDate()))
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return (Students) arr;
    }

    @Override
    public List<Students> findWhereNameLike(String query) {
        ResultSet rs;
        try (Connection con = DriverManager.getConnection(connexion.Connex())) {
            PreparedStatement pst = con.prepareStatement("SELECT name FROM students where name iLike " + query);
            rs = pst.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return (List<Students>) rs ;
    }
}
