package com.ait.beauty;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/beauty")
public class BeautyResource {

	BeautyDAO dao = new BeautyDAO();
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<BeautyTreatment> findAll() {
		return dao.findAll();
	}
	
	@GET @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public BeautyTreatment findById(@PathParam("id") String id) {
        return dao.findById(Integer.parseInt(id));
    }
	
	@GET @Path("search/{query}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<BeautyTreatment> findByLocation(@PathParam("query") String query){
		return dao.findByLocation(query);
	}

	@POST
	@Consumes ({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces ({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public BeautyTreatment create(BeautyTreatment beautyTreatment) {
		return dao.create(beautyTreatment);
	}
	
	@PUT @Path("{id}")
	@Consumes ({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces ({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public BeautyTreatment upadate(BeautyTreatment beautyTreatment){
		dao.update(beautyTreatment);
		return beautyTreatment;
	}
	
	@DELETE @Path("{id}")
	@Produces ({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void remove(@PathParam("id") int id) {
		dao.remove(id);
	}
}
