package service;

import movies.Movies;

public class ResultResponse {
	
	private int id;
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Movies.MovieId getProto() {
		return Movies.MovieId.newBuilder().setId(this.getId()).build();
	}
	
//	private long id;
//	private Movie movie;
//	
//	MovieDB db;
//	  
//	public long getId() {
//		return id;
//	}
//		
//	public void setId(long id) {
//
//		this.id=id;
//		
//	}
//	
//	
//	public ResultResponse() {
//		movie=null;
//	}
//	public ResultResponse(Movie movie,long id) {
//		this.movie=movie;
//		this.id=id;
//	
//	}
	
	
}
