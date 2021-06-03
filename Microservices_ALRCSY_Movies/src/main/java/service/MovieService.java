package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

import movies.Movies;
import movies.Movies.MovieList;
import movies.Movies.MovieList.Builder;



public class MovieService  {

	//private static final Object lock = (Object) new Object();
	//private Map<Long,Movie> movies=MovieDB.getMovies();
	//private MovieManagement movieService=new MovieManagement();
	private List<Movie> movies;
	
	
//	public Map<Long,Movie> getInstance() {
//		return movies;
//	}
	public MovieService() {
		this.movies = new ArrayList<Movie>();
	}
	
	public Movies.MovieList getProto() {
		Builder movieListBuilder = Movies.MovieList.newBuilder();
		
		for (Movie movie: this.movies) {
			Movies.Movie m = movie.getProto();
			movieListBuilder.addMovie(m);
		}
		
		return movieListBuilder.build();
		
	}
		
	public Movie getMovieById(int id) {
		for (Movie movie: this.movies) {
			if (movie.getId() == id) {
				return movie;
			}
		}
		return null;
	}

	public ResultResponse addMovie(Movie movie) {
		if (!movie.hasId()) {
			movie.setId(this.generateMovieId());
		}
		this.movies.add(movie);
		
		ResultResponse result = new ResultResponse();
		result.setId(movie.getId());
		return result;
	}
	
	
	private int generateMovieId() {
		if (this.movies.size() == 0) {
			return 0;
		}
		
		int id = 0;
		for (Movie movie: this.movies) {
			int currentId = movie.getId();
			if (currentId >= id) {
				id = currentId + 1;
			}
		}
		return id;
	}
	
	public  void removeMovieId(int id) {
		this.movies.remove(getMovieById(id));
	}
	
	public void updateMovie(Movie newMovie) {
		int i=0;
		for(Movie movie:this.movies) {
			if(movie.getId()==newMovie.getId()) {
				this.movies.set(i, newMovie);
				break;
			}
			i++;
		}
	}
	public MovieQueryResponse getMoviesByYearAndField(int year,String field) {
		MovieQueryResponse idList=new MovieQueryResponse();
		List<Movie> listOfMovie =new ArrayList<Movie>();
		
		for(Movie movie:this.movies) {
			if(movie.getYear()==year) {
				listOfMovie.add(movie);
			}
		}
		
		switch(field) {
			case  "Title":
				Comparator<Movie> compareByTitle = (Movie o1, Movie o2) -> o1.getTitle().compareTo( o2.getTitle() );
				Collections.sort(listOfMovie, compareByTitle);
				break;
				
			case  "Director":
				Comparator<Movie> compareByDirector = (Movie o1, Movie o2) -> o1.getDirector().compareTo( o2.getDirector() );
				Collections.sort(listOfMovie, compareByDirector);	
				break;
		
		}
		

		for (Movie movie: listOfMovie) {
			idList.add(movie.getId());
		}
		
		return idList;
		
	}

	
	
	
//	public Movie getMovie(long id) {
//		return movies.get(id);
//	}
	
//	public Movie addMovie(Movie movie,long identifier) {
//		// only add one user at a time to the map
//	     synchronized (lock)
//	     {
//	      	movies.put(identifier,movie);
//
//	     }
//		return movie;
//	}
//	
//	public void updateMovie(long id,Movie movie) {
//
//		movies.put(id, movie);
//	
//	}
//	
//	public Movie deleteMovie(long id) {
//		return movies.remove(id);
//	}
//	
//	public List<Long> searchMovie(int year,String field) {
//		List<Movie> list=new ArrayList<>();
//		List<Long> idList=new ArrayList<Long>();
//	
//			for (Map.Entry<Long, Movie> entry : movies.entrySet()) {
//	            if (entry.getValue().getYear().equals(year)){
//	            	list.add(entry.getValue());
//
//	            	
//	              }
//	         }
//	            	
//	        if(field.equals("Title")) {
//	    		if (list.size() > 0) {
//	    			Comparator<Movie> compareByTitle = (Movie o1, Movie o2) -> o1.getTitle().compareTo( o2.getTitle() );
//		   				Collections.sort(list, compareByTitle);
//	    		}
//	        }
//	        else if(field.equals("Director")) {
//	   			 if (list.size() > 0) {
//	   			   Comparator<Movie> compareByDirector = (Movie o1, Movie o2) -> o1.getDirector().compareTo( o2.getDirector() );
//	   				Collections.sort(list, compareByDirector);
//	   			 }
//	   		} 
//	            	
//	            
//			for(Movie movie : list) {
//				System.out.println(movie.getTitle());
//				for (Map.Entry<Long, Movie> entry : movies.entrySet()) {
//				    Long id = entry.getKey();
//				    Movie mapMovie = entry.getValue();
//				       if(movie.equals(mapMovie)) {
//				    		idList.add(id);
//				    		
//				    	}
//				    
//				    }
//				}
//		
//		return idList;
//		
//         }

}
