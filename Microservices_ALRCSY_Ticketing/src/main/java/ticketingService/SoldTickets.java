package ticketingService;

import java.util.ArrayList;
import java.util.List;

import ticketing.Ticketing;

public class SoldTickets {

	private List<Ticket> tickets;
	
	public SoldTickets() {
		if(tickets==null) {
			tickets=new ArrayList<Ticket>();
		}
	}
	
	public void sell(int movieId, int count) {
		for(Ticket ticket:tickets) {
			if(ticket.getMovieId()==movieId) {
				ticket.addCount(count);
				return;
			}
		}
		Ticket ticket=new Ticket();
		ticket.setMovieId(movieId);
		ticket.setCount(count);
		tickets.add(ticket);
		
	}
	
	public Ticketing.GetTicketsResponse getProto(){
		List<Ticketing.Ticket> list=new ArrayList<Ticketing.Ticket>();
		for(Ticket ticket:tickets) {
			list.add(ticket.getProto());
		}
			
		return Ticketing.GetTicketsResponse.newBuilder().addAllTicket(list).build() ;
		
	}
	
	
	
	
	
}
