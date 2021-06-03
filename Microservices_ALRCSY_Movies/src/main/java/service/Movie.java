package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import movies.Movies;
import movies.Movies.Movie.Builder;

public class Movie {
	
	private int id;
	private boolean hasId;
	private String title;
	private Integer year;
	private String director;
	private String[] actor;
	
	
	public Movie() {
		this.hasId = false;
		this.actor = new String[] {};
	}


//	public Movie(String title, Integer year, String director, String[] actor) {
//		this.title = title;
//		this.year = year;
//		this.director = director;
//		this.actor = actor;
//	}
	
	public Movie(Movies.Movie movie) {
		this.hasId = false;
		this.setTitle(movie.getTitle());
		this.setYear(movie.getYear());
		this.setDirector(movie.getDirector());
		List<String> actors = new ArrayList<String>();
		for (int i = 0; i < movie.getActorCount(); i++) {
			String actor = movie.getActor(i);
			if (actor != "") {
				actors.add(actor);
			}
		}
		this.setActor(actors.toArray(new String[0]));
	}
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
		this.hasId = true;
	}

	public boolean hasId() {
		return this.hasId;
	}



	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public Integer getYear() {
		return year;
	}


	public void setYear(Integer year) {
		this.year = year;
	}


	public String getDirector() {
		return director;
	}


	public void setDirector(String director) {
		this.director = director;
	}


	public String[] getActor() {
		return actor;
	}


	public void setActor(String[] actor) {
		this.actor = actor;
	}


	@Override
	public String toString() {
		return "MovieDatabase [title=" + title + ", year=" + year + ", director=" + director + ", actor="
				+ Arrays.toString(actor) + "]";
	}
	
	public Movies.Movie getProto() {
		Builder movieBuilder = Movies.Movie.newBuilder();
		movieBuilder.setTitle(this.getTitle()).setYear(this.getYear()).setDirector(this.getDirector());
		for (String actor: this.actor) {
			movieBuilder.addActor(actor);
		}
		return movieBuilder.build();
	}

}
