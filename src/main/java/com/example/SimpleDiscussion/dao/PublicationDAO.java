package com.example.SimpleDiscussion.dao;

import com.example.SimpleDiscussion.model.Publication;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PublicationDAO {
    String username="postgres";
    String pwd="postgres";
    String connUrl="jdbc:postgresql://localhost:5432/demo";

    private static final String INSERT_PUBLICATIONS_SQL = "insert into publications" + "(name, description, comments) values" +
            "(?,?,?);";
    private static final String SELECT_PUBLICATIONS_BY_ID = "select id,name,description,comments from  publications where id = ?";
    private static final String SELECT_ALL_PUBLICATIONS = "select * from publications";
    private static final String DELETE_PUBLICATIONS_SQL = "delete from publications where id = ?;";
    private static final String UPDATE_PUBLICATIONS_SQL = "update publications set name = ? , description = ?, comments = ? where id = ?;" ;

    protected Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(connUrl, username, pwd);
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return connection;
    }

    public void insertPublications(Publication publication) throws  SQLException{
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PUBLICATIONS_SQL)){
            preparedStatement.setString(1, publication.getName());
            preparedStatement.setString(2, publication.getDescription());
            preparedStatement.setString(3, publication.getComments());
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updatePublications(Publication publication) throws  SQLException{
        boolean rowUpdate;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PUBLICATIONS_SQL)){
            statement.setString(1, publication.getName());
            statement.setString(2, publication.getDescription());
            statement.setString(3, publication.getComments());
            statement.setInt(4, publication.getId());

            rowUpdate =  statement.executeUpdate() > 0;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Publication selectPublications(int id) throws  SQLException{
        Publication publication = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PUBLICATIONS_BY_ID)){
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                String name = rs.getString("name");
                String description = rs.getString("description");
                String comments = rs.getString("comments");
                publication = new Publication(id,name,description,comments);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return publication;
    }

    public List<Publication> selectAllPublications(){
        List<Publication> publications = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PUBLICATIONS_BY_ID)){
            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String comments = rs.getString("comments");
                publications.add(new Publication(id,name,description,comments));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return publications;
    }

    public boolean deletePublication(int id) throws SQLException{
        boolean rowDelete;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PUBLICATIONS_SQL)){
            statement.setInt(1,id);
            rowDelete = statement.executeUpdate() > 0;
        }
        return rowDelete;
    }
}
