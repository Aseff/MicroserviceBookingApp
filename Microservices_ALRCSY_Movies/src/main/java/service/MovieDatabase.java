package service;

import javax.ws.rs.core.Response;

import movies.Movies;
import movies.Movies.MovieId;
import movies.Movies.MovieList;


public class MovieDatabase implements IMovieDatabase {
	private static MovieService movieList;
	
	public MovieDatabase() {
		if (movieList == null) {
			movieList = new MovieService();
		}
	}
	
	@Override
	public Movies.MovieList getAllMovies() {
		return movieList.getProto();
	}

	@Override
	public Response getMovieById(int id) {
		Movie movie = movieList.getMovieById(id);
		if (movie == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(movie.getProto()).build();
	}

	
	@Override
	public MovieId insertMovie(movies.Movies.Movie movie) {
		Movie m = new Movie(movie);
		ResultResponse result = movieList.addMovie(m);
		return result.getProto();
	}

	@Override
	public void deleteMovie(int id) {
		if(movieList.getMovieById(id)!=null) {
			movieList.removeMovieId(id);
		}
		
	}

	@Override
	public void updateOrInsertMovie(int id, movies.Movies.Movie movie) {
		Movie m=new Movie(movie);
		m.setId(id);
		if(movieList.getMovieById(id)==null) {
			movieList.addMovie(m);
		}
		else {
			movieList.updateMovie(m);
		}
		
	}

	@Override
	public Response getMovieIdByYearAndField(int year, String field) {
		
		if(field.equals("Title") || field.equals("Director")) {
			
			MovieQueryResponse movieQueryResponse=movieList.getMoviesByYearAndField(year, field);
			return Response.ok(movieQueryResponse.getProto()).build();
			
		}
		return Response.status(Response.Status.BAD_REQUEST).build();
	}
	
		

	


}
