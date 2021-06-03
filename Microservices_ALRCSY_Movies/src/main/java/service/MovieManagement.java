package service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import movies.Movies;


public class MovieManagement {
	private static final String String = null;
	private Map<Long, Movie> movieMap =MovieDB.getMovies();
	private long generalID=1;
	public MovieManagement() {
		
	}
	
	public Collection<Movie> getAllMovies(){
		return movieMap.values();
	}
	
	public Movie getMovie(int id){
		
		Movie movie = movieMap.get(id);
		return movie;
	}
	
	public Long addMovie(Movie movie){
		
		while(movieMap.containsKey(generalID)) {
			generalID++;
		}
		
		movieMap.put(generalID, movie);
		return generalID;
	}
	
	public void putMovie(long id,Movie movie) {
		movieMap.put(id, movie);
	}
	
	public void deleteMovie(int id) {
		movieMap.remove(id);
	}
	
	
//	
//	public List<Integer> findMovieBy(int year, String field){
//		Map<Long, Movie> movieMap2 =new HashMap<>();
//		for(Map.Entry<Long, Movie> entry: movieMap.entrySet()) {
//			String[] s=new String[entry.getValue().getActor().length];
//			List<String> actList=new ArrayList<String>();
//			for(int i=0; i<entry.getValue().getActor().length; i++) {
//				s[i]=entry.getValue().getActor()[i];
//				actList.add(entry.getValue().getActor()[i]);
//			}
//			Movie m =new Movie(entry.getValue().getTitle(),entry.getValue().getYear(),
//					   entry.getValue().getDirector(),
//					   s);
//			movieMap2.put(entry.getKey(), m);
//		}
//		if(field.equals("Director")) { 
//		//System.out.println(field);
//		//	System.out.println("inside");
//			List<Integer> list=movieMap2
//		    	      .entrySet()
//		    	      .stream()
//		    	      .filter(entry -> entry.getValue().yearForComparingGet()==year)
//		    	      .sorted(Map.Entry.<Integer, Movie>comparingByValue(Comparator.comparing(Movie::directorForComparingGet)))
//		    	      .map(Map.Entry::getKey).collect(Collectors.toList());
//			//list.forEach(System.out::println);
//			return list;
//		}
//		else if(field.equals("Title")){
//			List<Integer> list=movieMap2
//		    	      .entrySet()
//		    	      .stream()
//		    	      .filter(entry -> entry.getValue().yearForComparingGet()==year)
//		    	      .sorted(Map.Entry.<Integer, model.Movie>comparingByValue(Comparator.comparing(model.Movie::titleForComparingGet)))
//		    	      .map(Map.Entry::getKey).collect(Collectors.toList());
//			//list.forEach(System.out::println);
//			return list;
//		}
//		else {
//			return new ArrayList<Integer>();
//		}
//		
//	}
}
