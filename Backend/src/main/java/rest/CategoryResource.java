package rest;

import entities.Category;
import entities.dto.JokesDTO;
import facades.ApiFacade;
import facades.RequestFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import utils.EMF_Creator;

/**
 *
 * @author asgerhs
 */
@Path("jokeByCategory")
public class CategoryResource {
    
     private static final EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/examprep2",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    private static final ApiFacade apiFacade =  ApiFacade.getFacade();
    private static final RequestFacade requestFacade = RequestFacade.getFacade(emf);

    @Context
    private UriInfo context;
    
    
    @Path("/{Categories}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JokesDTO getJokes(@PathParam("Categories") String categories){
        String[] stringCat = categories.split(",");
        
        if(stringCat.length > 4){
            throw new WebApplicationException("Nah Bruv, 4 or less");
        }
        
        try{
            List<Category> cats = new ArrayList();
            for(String cat : stringCat){
                cats.add(requestFacade.getSingleCategory(cat));
            }
            requestFacade.createRequest(cats);
            return apiFacade.fetch("https://api.chucknorris.io/jokes/random?category=", cats);
        }catch(NoResultException ex){
            throw new WebApplicationException("Can't use that one kiddo.");
        }
    }
    
}
