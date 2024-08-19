package com.elitehomes.web.endpoint;

import com.elitehomes.core.auth.Authentication;
import com.elitehomes.core.auth.LoginDto;
import com.elitehomes.core.auth.UserType;
import com.elitehomes.model.RealEstateDto;
import com.elitehomes.model.base.MessageDto;
import com.elitehomes.service.RealEstateService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.inject.Singleton;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;

@Singleton
@Path("/v1/real-estate")
@Authentication(types = {UserType.MASTER})
public class RealEstateController {

    private final RealEstateService realEstateService;

    @Autowired
    public RealEstateController(RealEstateService realEstateService) {
        this.realEstateService = realEstateService;
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public RealEstateDto create(@Context LoginDto loginDto, RealEstateDto realEstateDto) {
        return realEstateService.create(realEstateDto, loginDto);
    }

    @PUT
    @Path("{id}")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public RealEstateDto delete(@Context LoginDto loginDto, @PathParam("id") Long id, RealEstateDto realEstateDto) {
		return realEstateService.update(id, realEstateDto, loginDto);
	}
    
    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public MessageDto delete(@Context LoginDto loginDto, @PathParam("id") Long id) {
		return realEstateService.delete(id, loginDto);
    }


    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public RealEstateDto findById(@Context LoginDto loginDto, @PathParam("id") Long id) {
        return realEstateService.findById(id, loginDto);
    }
}
