package org.example;

import org.jsoup.nodes.Document;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Indexer {
    /*we  need three thing web page title, page links, page text ,, we can get these from document object*/

    static Connection connection = null;

    Indexer(Document document, String url){
        // Select important elements of object
        String title = document.title();
        String link = url;
        String text = document.text();

        // Save elements to database
        try{
            connection = DatabaseConnection.getConnection();  // setting up the database connection,, it will call the getConnection method of the DatabaseConnection class
            // inserting queries,, and values are going to be dynamic
            PreparedStatement preparedStatement = connection.prepareStatement("Insert into pages values(?, ?, ?);");
            // preparing statements
            preparedStatement.setString(1,title); // at first index(? Question mark) we want page title
            preparedStatement.setString(2,link);   // at second index we want pagelink
            preparedStatement.setString(3,text);  // at third index we want page text
            preparedStatement.executeUpdate();   // updating the query
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
}
/*Note :- so we have created an instance of connection class to run an insert query in java and executed the update */
