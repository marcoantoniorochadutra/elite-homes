package com.elitehomes.web.endpoint;

import com.elitehomes.core.auth.Authentication;
import com.elitehomes.core.auth.LoginDto;
import com.elitehomes.core.auth.UserType;
import com.elitehomes.core.entity.base.MessageDto;
import com.elitehomes.model.PropertyDto;
import com.elitehomes.core.model.result.PropertyResultDto;
import com.elitehomes.service.PropertyService;
import jakarta.inject.Singleton;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Singleton
@Path("/v1/property")
@Authentication(types = {UserType.MASTER})
public class PropertyController {

    private final PropertyService propertyService;

    @Autowired
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public PropertyDto create(@Context LoginDto loginDto, PropertyDto realEstateDto) {
        return propertyService.create(realEstateDto, loginDto);
    }

    @PUT
    @Path("{id}")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public PropertyDto delete(@Context LoginDto loginDto, @PathParam("id") Long id, PropertyDto realEstateDto) {
		return propertyService.update(id, realEstateDto, loginDto);
	}
    
    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public MessageDto delete(@Context LoginDto loginDto, @PathParam("id") Long id) {
		return propertyService.delete(id, loginDto);
    }


    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public PropertyDto findById(@Context LoginDto loginDto, @PathParam("id") Long id) {
        return propertyService.findById(id, loginDto);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public List<PropertyResultDto> listByCriteria(@Context LoginDto loginDto) {
        return propertyService.listByCriteria(new HashMap<>(), loginDto);
    }

    @PUT
    @Path("/{id}/photo/{fileName}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes("*/*")
    public PropertyDto addImagem(@PathParam("id") Long id,
                                 @PathParam("photoName") String photoName,
                                 @QueryParam("mainPhoto") Boolean mainPhoto,
                                 byte[] file,
                                 @Context LoginDto login) {
        log.info("Saving File {} : {}", photoName, FileUtils.byteCountToDisplaySize(file.length));
        return propertyService.savePhoto(id, photoName, file, login);
    }

    @DELETE
    @Path("/{id}/photo/{fileName}")
    @Produces({MediaType.APPLICATION_JSON})
    public PropertyDto removeAnexo(@PathParam("id") Long id,
                                   @PathParam("photoName") String photoName,
                                   @Context LoginDto login) {
        log.info("Deleting File {}", photoName);
        return propertyService.deletePhoto(id, photoName, login);
    }
}
