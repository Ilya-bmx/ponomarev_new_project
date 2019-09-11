package com.company.controller;

import com.company.model.*;
import com.company.model.View.Views;
import com.company.repos.AccessRepo;
import com.company.service.AdminService;
import com.company.service.AuthService;
import com.company.service.DraftService;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

/**
 * Created by ponomarev_ia on 27.08.2019.
 * Добавить JPA , попробовать добавить рест регистрацию , через БД
 */
@Path("/users")
public class FirstController {
    @Inject
    AuthService authService;
    @Inject
    AccessRepo accessRepo;
    @Inject
    AdminService adminService;
    @Inject
    DraftService draftservice;

    @JsonView(Views.Internal.class)
    @GET
    @Produces("application/json")
    public PojoUser homeGetController() throws JsonProcessingException {

        PojoUser user = new PojoUser(1, "str", "hotel");

        //Усложнённая реализация JsonView ( можно делать через аннотацию надо контроллером )
        /*SerializerFactory serializerFactory = BeanSerializerFactory.instance
                .withSerializerModifier(new MyBeanSerializerModifier());

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializerFactory(serializerFactory);

        String result = mapper
                .writerWithView(Views.Internal.class)
                .writeValueAsString(user);*/

        return user;
    }

    @POST
    //@Path("aaa")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response homePostController(String json) throws IOException, URISyntaxException {//попробовать с строки реализовать
        /*String json = "{ \"id\" : \"01\", \"name\" : \"Ilya\", \"secondName\" : \"Ponomarev\", \"status\" : \"hotel\" }";*/
        System.out.println("in a target post /users" + json);
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
        PojoUser user = mapper
                .readerWithView(Views.Internal.class)
                .forType(PojoUser.class)
                .readValue(json);
        System.out.println(user.getId() + " " + user.getName() + " " + user.getSecondName() + " " + user.getStatus());
        return Response.status(201).entity(user).build();
    }

    @GET
    @Path("/admin/{pin}")
    @Produces(MediaType.APPLICATION_XML)
    public Customer getCustomerInXML(@PathParam("pin") int pin) {
        /*Customer customer = new Customer();*/
        /*customer.setName("mkyong");
        cu stomer.setSecondName("mkyongichev");
        customer.setPin(pin);*/
        return Customer.builder().name("Iya").secondName("Ponomarev").pin(100).build();

        /*return customer;*/

    }

    @POST
    @Path("/admin/auth")
    public Response getAuthorised(AuthModel authModel) {
        System.out.println(" " + authModel.getLogin() + "--------" + authModel.getPassword());
        if (authService.getRegistrated(authModel) == 1) return Response.status(200).entity("Already exist").build();
        else return Response.status(200).build();
    }

    @GET
    @Path("/admin/auth/{login}")
    @Produces("application/json")
    public Response useAccessableService(@PathParam("login") String login) {
        //return Response.status(100).build();
        return Response.ok(100).entity(accessRepo.findAllByLogin(login)).build();
    }

    @GET
    @Path("/admin/addService")
    public Response useAdminService_AddUserAccess(@QueryParam("userlogin") String userlogin, @QueryParam("useraccess") String useraccess) {
        adminService.giveSomeRules(userlogin, useraccess);
        return Response.status(200).build();
    }

    //обработать исключения на непрохождение валидности
    @POST
    @Path("/worker/save")
    public Response saveDraft(@Valid @NotNull ApplicationDraft modelObject) {
        draftservice.saveDraft(modelObject);
        return Response.ok().build() /*вернуть респонз в которо говоорится что создали например вернуть id анкеты*/;
    }

    @POST
    @Path("/worker/delete")
    public Response deleteDraft(@Valid @NotNull ApplicationDraft modelObject) {
        String response = draftservice.deleteDraftByNumber(modelObject.getNumber());
        return Response.ok().entity(response).build();
    }

    @GET
    @Path("/worker")
    @Produces("application/json")
    public Response getWorker_AllDraft() {
        System.out.println(adminService.getDraftsByWorker().size());
        return Response.ok(100).entity(adminService.getDraftsByWorker()).build();
    }

    @POST
    @Path("/worker/service/{access}")
    public Response saveProjectData(@PathParam("access") String access, @Valid @NotNull ProjectData projectData) {
        adminService.addProjectData(access, projectData.getLogin(), projectData.getVendor_code(), projectData.getAccess());
        return Response.ok().build() /*вернуть респонз в которо говоорится что создали например вернуть id анкеты*/;
    }

    @GET
    @Path("/accept")
    public String getAccept1() {
        System.out.println("accepted1");
        return "ERROR";
    }

    @POST
    @Path("/accept")
    @Consumes("application/json")
    public void getAccept(PojoUser user) {
        System.out.println("accepted /accept" + user);
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("index")
    public Response getCreditCalculatorForm() {
        return Response.ok()
                .type("text/html")
                .entity((StreamingOutput) output -> {
                    InputStream resourceAsStream = FirstController.class.getResourceAsStream("/index.html");
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    int available = resourceAsStream.available();
                    byte[] bytes = new byte[available];
                    while (available > 0 && resourceAsStream.read(bytes) > 0) {
                        baos.write(bytes);
                    }
                    baos.writeTo(output);
                })
                .build();
    }

    @GET
    @Path("/{key}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getJQuery(@PathParam("key") String key) {
        String path;
        String type = "text/html";
        switch (key) {
            case "css":
                path = "/css/mysite.css";
                type = "text/css";
                break;
            case "js":
                path = "/js/main.js";//заменить на .css файлы
                type = "application/javascript";
                break;
            case "main":
                path = "/documents/src/assets/js/main.js";
                break;
            default:
                path = "";
        }
        return path.isEmpty() ? Response.noContent().build() : Response.ok()
                .type(type)
                .header("cache-control", "")
                .header("pragma", "")
                .entity((StreamingOutput) output -> {
                    InputStream resourceAsStream = FirstController.class.getResourceAsStream(path);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    int available = resourceAsStream.available();
                    byte[] bytes = new byte[available];
                    while (available > 0 && resourceAsStream.read(bytes) > 0) {
                        baos.write(bytes);
                    }
                    baos.writeTo(output);
                })
                .build();
    }
}
