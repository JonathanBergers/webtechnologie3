package resources;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import login.LoginService;
import model.User;
import register.CustomRestResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by falco on 25-10-15.
 */
@Path("login")
public class LoginResource extends SearchableResource<User>{

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON})
    public Response login (String loginObject){
        JsonObject jsonObject = new GsonBuilder().create().fromJson(loginObject, JsonObject.class);
        CustomRestResponse restResponse = new CustomRestResponse();

        if(jsonObject == null ||jsonObject.isJsonNull()){
            restResponse.addError("convert", "json is null");
            return Response.status(403).entity(restResponse).build();
        }

        LoginService loginService = new LoginService(jsonObject,getModel());

        restResponse = loginService.validateUserCredentials(restResponse);

        return Response.ok(restResponse).build();

    }
}
