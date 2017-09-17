package com.fpa.softw.movlib.dao;

import com.fpa.softw.movlib.connection.ConnectionFactory;
import com.fpa.softw.movlib.models.Movie;
import com.fpa.softw.movlib.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {

    private Connection connection;

    public MovieDAO(){
        this.connection = ConnectionFactory.getConnection();
    }

    public boolean addMovie(Movie movie){

        String sql = "INSERT INTO movie (title, user_rating, watched_year, watched, recommends, user_id) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            preparedStatement.setString(1, movie.getTitle());
            preparedStatement.setDouble(2, movie.getUserRating());
            preparedStatement.setInt(3, movie.getWatchedYear());
            preparedStatement.setBoolean(4, movie.isWatched());
            preparedStatement.setBoolean(5, movie.isRecommends());
            preparedStatement.setInt(6, movie.getUserId());

            preparedStatement.execute();

            preparedStatement.close();

            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }

    }

    public List<Movie> listMovieByUserId(int userId){
        String sql = "SELECT * from movie WHERE user_id = " + userId;

        List<Movie> movieList = new ArrayList<>();

        try {
            ResultSet rs = this.connection.prepareStatement(sql).executeQuery();

            while (rs.next()){

                int id = rs.getInt(1);
                String title = rs.getString(2);
                double userRating = rs.getDouble(3);
                int watchedYear = rs.getInt(4);
                boolean watched = rs.getBoolean(5);
                boolean recommends = rs.getBoolean(6);


                movieList.add(new Movie(id, title, userRating, watchedYear, watched, recommends, userId));

            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movieList;
    }

    public void removeMovie(Movie movie){

    }

    public void updateMovie(int movieId){

    }

}
