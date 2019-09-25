package com.company.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by ponomarev_ia on 25.09.2019.
 * Добавлен редирект
 */
@Path("/")
public class SecondController {

    @GET
    public Response yourAPIMethod() throws URISyntaxException {
        URI targetURIForRedirection = new URI("http://localhost:8080/users/index");
        return Response.temporaryRedirect(targetURIForRedirection).build();
    }
}
