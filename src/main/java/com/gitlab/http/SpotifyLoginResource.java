package com.gitlab.http;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/login")
public class SpotifyLoginResource {
    
    @ConfigProperty(name = "CLIENT_ID")
    private String CLIENT_ID;

    @ConfigProperty(name = "CLIENT_SECRET")
    private String CLIENT_SECRET;

    @ConfigProperty(name = "REDIRECT_URL")
    private String REDIRECT_URL;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response login() {
        String authorizationUrl = "https://accounts.spotify.com/authorize" +
        "?client_id=" + CLIENT_ID +
        "&response_type=code" +
        "&redirect_uri=" + REDIRECT_URL +
        "&scope=user-read-private%20user-read-email"; // Add necessary scopes
        return Response.status(302).header("Location", authorizationUrl).build();        
    }
}
