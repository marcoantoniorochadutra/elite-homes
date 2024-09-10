package com.elitehomes.web.endpoint;

import com.elitehomes.core.auth.Authentication;
import com.elitehomes.core.auth.UserType;
import com.elitehomes.model.base.SelectableDto;
import com.elitehomes.service.ReferenceService;
import jakarta.inject.Singleton;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;

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
    @Path("/property")
    @Produces({MediaType.APPLICATION_JSON})
    public List<SelectableDto> listPropertyGroup() {
        return referenceService.listPropertyGroup();
    }

    @GET
    @Path("/type")
    @Produces({MediaType.APPLICATION_JSON})
    public List<SelectableDto> listPropertyType() {
        return referenceService.listPropertyType();
    }
}
