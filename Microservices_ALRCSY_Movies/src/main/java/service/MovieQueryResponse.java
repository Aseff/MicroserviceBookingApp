package service;

import java.util.ArrayList;
import java.util.List;

import movies.Movies;

public class MovieQueryResponse {
	private List<Integer> ids;
	
	public MovieQueryResponse() {
		ids = new ArrayList<Integer>();
	}
	
	public void add(int id) {
		ids.add(new Integer(id));
	}
	
	public Movies.MovieIdList getProto() {
		return Movies.MovieIdList.newBuilder().addAllId(this.ids).build();
	}
//	private List<Long> id;
//
//	public List<Long> getId() {
//		return id;
//	}
//
//	public void setId(List<Long> id) {
//		this.id = id;
//	}
//
//	
//	
//	public MovieQueryResponse(List<Long> id) {
//		
//		this.id = id;
//	}
//	
//	public MovieQueryResponse() {
//		
//	}
}
