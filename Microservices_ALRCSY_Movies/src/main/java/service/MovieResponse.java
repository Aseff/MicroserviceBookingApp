package service;

import java.util.List;


public class MovieResponse {
	
	public List<Movie> movie;
	
	public MovieResponse() {
		movie=null;
	}
	public MovieResponse(List<Movie> movies) {
		this.movie=movies;
	}
}
