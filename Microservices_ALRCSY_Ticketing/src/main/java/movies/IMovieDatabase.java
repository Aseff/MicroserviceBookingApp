package movies;

import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public interface IMovieDatabase {
	
	
	@GET
	@Path("movies")
	@Produces({"application/x-protobuf", MediaType.APPLICATION_JSON })
	public Movies.MovieList getMovies();
	
	@GET
	@Path("movies/{id}")
	@Produces({"application/x-protobuf", MediaType.APPLICATION_JSON })
	public Response getMovieById(@PathParam("id") int id);
	
	
	@POST
	@Path("movies")
	@Consumes({"application/x-protobuf", MediaType.APPLICATION_JSON })
	@Produces({"application/x-protobuf", MediaType.APPLICATION_JSON })
	public Movies.MovieId insertMovie(Movies.Movie movie);
	
	@PUT
	@Path("movies/{id}")
	@Consumes({"application/x-protobuf", MediaType.APPLICATION_JSON })
	@Produces({"application/x-protobuf", MediaType.APPLICATION_JSON })
	public void updateMovie(int id,Movies.Movie movie);
	
	@DELETE
	@Path("movies/{id}")
	@Consumes({"application/x-protobuf", MediaType.APPLICATION_JSON })
	@Produces({"application/x-protobuf", MediaType.APPLICATION_JSON })
	public void deleteMovie(@PathParam("id") int id);
	
	@GET
	@Path("movies/find")
	@Consumes({"application/x-protobuf", MediaType.APPLICATION_JSON })
	@Produces({"application/x-protobuf", MediaType.APPLICATION_JSON })
	public Response searchMovie(@QueryParam("year") int year,@QueryParam("orderby") String field);
	
	
}
