package com.company.controller;

import com.company.model.*;
import com.company.model.View.Views;
import com.company.service.AuthService;
import com.company.service.DaoServices.AdminService;
import com.company.service.DraftService;
import com.company.service.Registrate;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

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
    AdminService adminService;
    @Inject
    DraftService draftservice;
    @Inject
    Registrate registrate;

    @OPTIONS
    public Response sendOptions(){
        return Response.ok().status(200).build();
    }

    @JsonView(Views.Internal.class)
    @GET
    @Produces("application/json")
    public Response homeGetController() throws JsonProcessingException {
        registrate.saveFunc();
        PojoUser user = new PojoUser(1, "Bob", "Marley", "hotel");

        //Усложнённая реализация JsonView ( можно делать через аннотацию надо контроллером )
        /*SerializerFactory serializerFactory = BeanSerializerFactory.instance
                .withSerializerModifier(new MyBeanSerializerModifier());

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializerFactory(serializerFactory);

        String result = mapper
                .writerWithView(Views.Internal.class)
                .writeValueAsString(user);*/
        return Response.ok().status(200).entity(user).build();//user;
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
    public Customer getCustomerInXML(@PathParam("pin") int pin, @AuthenticationPrincipal User user) {
        System.out.println("LOGINED USER::::.... -- " + user);
        /*Customer customer = new Customer();*/
        /*customer.setName("mkyong");
        cu stomer.setSecondName("mkyongichev");
        customer.setPin(pin);*/
        return Customer.builder().name("Iya").secondName("Ponomarev").pin(100).build();

        /*return customer;*/

    }

    //наделение правами по имени пользователя
    @POST
    @Path("/admin/give")
    public Response giveRules(AuthModel authModel) {
        System.out.println(" " + authModel.getLogin() + "--------" + authModel.getRole());
        String response = adminService.giveSomeRules(authModel.getLogin(), authModel.getRole());
        return Response.status(200).entity(response).build();
    }

    //при передаче всё равно роль указывать
    @POST
    @Path("/admin/delete")
    public Response deleteuser(AuthModel authModel) {
        System.out.println(" " + authModel.getLogin() + "--------" + authModel.getRole());
        String response = adminService.deleteUser(authModel.getLogin());
        return Response.status(200).entity(response).build();
    }

    @GET
    @Path("/admin/addService")
    public Response useAdminService_AddUserAccess(@QueryParam("userlogin") String userlogin, @QueryParam("useraccess") String useraccess) {
        adminService.giveSomeRules(userlogin, useraccess);
        return Response.status(200).build();
    }

    //обработать исключения на непрохождение валидности
    @POST
    @Consumes("application/json")
    @Path("/worker/save")
    public Response saveDraft(ApplicationDraft modelObject) {
        draftservice.saveDraft(modelObject);
        System.out.println("ENTERED TO SAVE " + modelObject.toString());
        return Response.ok().build() /*вернуть респонз в которо говоорится что создали например вернуть id анкеты*/;
    }

    @POST
    @Path("/worker/delete")
    public Response deleteDraft(ApplicationDraft modelObject) {
        String response = draftservice.deleteDraftByNumber(modelObject.getNumber());
        return Response.ok().entity(response).build();
    }

    @POST
    @Path("role")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getRoleOfPrincipal() {
        System.out.println("Role Checking DELETE IT ARRRRRRRR");
        return Response.ok().entity(authService.getPrincipalsRole() == "USER" ? "1" : "2").build();
    }

    @GET
    @Path("/worker")
    @Produces("application/json")
    public Response getWorkers_AllDraft() {
        System.out.println(adminService.getDraftsByWorker().size());
        return Response.ok(100).entity(adminService.getDraftsByWorker()).build();
    }

    @POST
    @Path("/worker/service/{access}")
    public Response saveProjectData(@PathParam("access") String access, @Valid @NotNull ProjectData projectData) {
        adminService.addProjectData(access, projectData.getLogin(), projectData.getVendor_code(), projectData.getAddress());
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
    public Response getAccept_savePojoUserDraft(PojoUser user) {
        System.out.println("accepted /accept" + user);
        draftservice.saveConvertPojoUserDraft(user);
        return Response.ok().entity(user.toString()).build();
    }

    //регистрация пользователя
    //c проверкой на валидность , аккуратней))
    @POST
    @Path("/accept1")
    @Consumes("application/json")
    public Response getAccept_saveUser(@Valid PojoUser user) {
        System.out.println("\n" + "trying to registrate : . . . " + user);
        registrate.saveUserFromRequest(new User(user.getName(), user.getPassword(), true, "USER"));
        return Response.ok().entity(user.toString()).build();
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
    @Produces(MediaType.TEXT_PLAIN) // ЕСЛИ не работает для картинки , то поменять МедиаТайп
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
            case "jpeg":
                path = "/picture/pictureForApp.jpg";
                type = "image/jpeg";
                break;
            case "wservice":
                path = "/wservice.html";
                type = "text/html";
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
