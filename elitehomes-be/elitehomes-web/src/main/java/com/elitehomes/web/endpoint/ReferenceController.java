package com.elitehomes.web.endpoint;

import com.elitehomes.core.auth.Authentication;
import com.elitehomes.core.auth.LoginDto;
import com.elitehomes.core.entity.base.SelectableDto;
import com.elitehomes.domain.config.TenantContext;
import com.elitehomes.domain.ref.AvailableReference;
import com.elitehomes.service.ReferenceService;
import jakarta.inject.Singleton;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;
import org.apache.commons.lang3.StringUtils;
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
    @Path("/{reference}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<SelectableDto> listReferences(
            @PathParam("reference") AvailableReference reference,
            @Context UriInfo uriInfo) {

        String additional = uriInfo.getQueryParameters().getFirst("additional");
        return referenceService.listReference(reference, additional);
    }

}
