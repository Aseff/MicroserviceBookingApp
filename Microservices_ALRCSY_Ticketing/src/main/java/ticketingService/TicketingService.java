package ticketingService;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

import banking.Banking;
import banking.Banking.ChargeCardRequest;
import banking.Banking.ChargeCardResponse;
import banking.ICreditCard;
import movies.IMovieDatabase;
import movies.Movies;
import movies.Movies.Movie;
import movies.Movies.MovieId;
import movies.Movies.MovieIdList;
import movies.Movies.MovieList;
import ticketing.Ticketing;
import ticketing.Ticketing.BuyTicketsRequest;
import ticketing.Ticketing.BuyTicketsResponse;
import ticketing.Ticketing.GetMoviesRequest;
import ticketing.Ticketing.GetMoviesResponse;
import ticketing.Ticketing.GetTicketsRequest;
import ticketing.Ticketing.GetTicketsResponse;


public class TicketingService implements ITicketingService {
	private static String MoviesAddress;
	private static String BankingAddress;
	private static SoldTickets soldTickets;
	
	
	public TicketingService() {
		if(soldTickets==null) {
			soldTickets=new SoldTickets();
		}
	}
	
	
	private static String getMoviesAddress() {
		if (MoviesAddress == null) {
			MoviesAddress = System.getProperty("microservices.movies.url");
		}
		return MoviesAddress;
	}
	
	private static String getBankingAddress() {
		if (BankingAddress == null) {
			BankingAddress = System.getProperty("microservices.banking.url");
		}
		return BankingAddress;
	}

	private IMovieDatabase getMoviesBackend() {
		String backendAddress = TicketingService.getMoviesAddress();
		if (backendAddress == null)
			return null;
		try {
			// Create the Resteasy provider:
			ResteasyProviderFactory instance = ResteasyProviderFactory.getInstance();
			ResteasyClient client = new ResteasyClientBuilder().providerFactory(instance).build();

			ResteasyWebTarget target = client.target(backendAddress);
			// Get a typed interface:
			IMovieDatabase backend = target.proxy(IMovieDatabase.class);
			return backend;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	private ICreditCard getBankingBackend() {
		String backendAddress = TicketingService.getBankingAddress();
		if (backendAddress == null)
			return null;
		try {
			// Create the Resteasy provider:
			ResteasyProviderFactory instance = ResteasyProviderFactory.getInstance();
			ResteasyClient client = new ResteasyClientBuilder().providerFactory(instance).build();

			ResteasyWebTarget target = client.target(backendAddress);
			// Get a typed interface:
			ICreditCard backend = target.proxy(ICreditCard.class);
			return backend;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	@Override
	public GetMoviesResponse getMovies(GetMoviesRequest request) {
		IMovieDatabase moviesBackend = this.getMoviesBackend();
		if (moviesBackend == null) return null;
		
		Movies.MovieIdList idList = moviesBackend.searchMovie(request.getYear(), "Title").readEntity(Movies.MovieIdList.class);
		List<Integer> ids = idList.getIdList();
	
		List<Ticketing.Movie> result = new ArrayList<Ticketing.Movie>();
		
		for (Integer i: ids) {
			Movies.Movie movie = moviesBackend.getMovieById(i).readEntity(Movies.Movie.class);
			result.add(Ticketing.Movie.newBuilder().setId(i).setTitle(movie.getTitle()).build());
		}
		
		return Ticketing.GetMoviesResponse.newBuilder().addAllMovie(result).build();
	}

	@Override
	public BuyTicketsResponse buyTickets(BuyTicketsRequest request) {
		IMovieDatabase moviesBackend = this.getMoviesBackend();
		ICreditCard bankingBackend = this.getBankingBackend();
		if (moviesBackend == null || bankingBackend == null) return null;
		
		int price = request.getCount() * 10;
		Banking.ChargeCardRequest chargeAmount = Banking.ChargeCardRequest.newBuilder()
				.setCardNumber(request.getCardNumber())
				.setAmount(price)
				.build();
		Banking.ChargeCardResponse res = bankingBackend.charge(chargeAmount);
		
		if (res.getSuccess()) {
			soldTickets.sell(request.getMovieId(), request.getCount());
		}
		return Ticketing.BuyTicketsResponse.newBuilder().setSuccess(res.getSuccess()).build();
	}



	@Override
	public GetTicketsResponse getTickets(GetTicketsRequest request) {
		
		return soldTickets.getProto();
	}
	
		

}
