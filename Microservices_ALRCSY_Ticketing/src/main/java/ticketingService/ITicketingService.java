package ticketingService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ticketing.Ticketing;
import ticketing.Ticketing.BuyTicketsRequest;
import ticketing.Ticketing.BuyTicketsResponse;
import ticketing.Ticketing.GetMoviesRequest;
import ticketing.Ticketing.GetMoviesResponse;
import ticketing.Ticketing.GetTicketsRequest;
import ticketing.Ticketing.GetTicketsResponse;

@Path("/ticketing")
public interface ITicketingService {
	
	

	@POST
	@Path("GetMovies")
	@Consumes({"application/x-protobuf", MediaType.APPLICATION_JSON })
	@Produces({"application/x-protobuf", MediaType.APPLICATION_JSON })
	public Ticketing.GetMoviesResponse getMovies(Ticketing.GetMoviesRequest request);
	
	@POST
	@Path("BuyTickets")
	@Consumes({"application/x-protobuf", MediaType.APPLICATION_JSON })
	@Produces({"application/x-protobuf", MediaType.APPLICATION_JSON })
	public Ticketing.BuyTicketsResponse buyTickets(Ticketing.BuyTicketsRequest request);
	
	@POST
	@Path("GetTickets")
	@Consumes({"application/x-protobuf", MediaType.APPLICATION_JSON })
	@Produces({"application/x-protobuf", MediaType.APPLICATION_JSON })
	public Ticketing.GetTicketsResponse getTickets(Ticketing.GetTicketsRequest request);	

}
