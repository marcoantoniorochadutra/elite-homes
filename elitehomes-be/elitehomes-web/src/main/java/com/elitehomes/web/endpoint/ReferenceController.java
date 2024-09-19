package com.elitehomes.web.endpoint;

import com.elitehomes.core.auth.LoginDto;
import com.elitehomes.core.entity.base.SelectableDto;
import com.elitehomes.service.ReferenceService;
import jakarta.inject.Singleton;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Singleton
@Path("/v1/reference")
public class ReferenceController {

    private final ReferenceService referenceService;

    @Autowired
    public ReferenceController(ReferenceService referenceService) {
        this.referenceService = referenceService;
    }

    @GET
    @Path("/goal")
    @Produces({MediaType.APPLICATION_JSON})
    public List<SelectableDto> listPropertyGoal() {
        return referenceService.listPropertyGoal();
    }

    @GET
    @Path("/group")
    @Produces({MediaType.APPLICATION_JSON})
    public List<SelectableDto> listPropertyGroup() {
        return referenceService.listPropertyGroup();
    }

    @GET
    @Path("/type/{group}") // TODO CHANGE TO QUERY PARAM
    @Produces({MediaType.APPLICATION_JSON})
    public List<SelectableDto> listPropertyType(@PathParam("group") String group) {
        return referenceService.listPropertyType(group);
    }
}
