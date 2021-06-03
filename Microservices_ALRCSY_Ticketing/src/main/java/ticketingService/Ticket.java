package ticketingService;

import ticketing.Ticketing;

public class Ticket {
	
	private int movieId;
	private int count;
  
	public Ticket() {
		  
	}
  
	public Ticket(int movieId, int count) {
	
	this.movieId = movieId;
	this.count = count;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	
	public void addCount(int newTicket) {
		this.count += newTicket;
	}

	
	public Ticketing.Ticket getProto() {
		return Ticketing.Ticket.newBuilder().setMovieId(this.getMovieId()).setCount(this.getCount()).build();
	}
	  
	
  
}
