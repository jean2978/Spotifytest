package com.gitlab.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.gitlab.pojo.User;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;

@Path("/")
@RegisterRestClient
public interface SpotifyRestClient {
    
    @GET
    @Path("/me")
    Uni<User> getUser(@HeaderParam("Authorization") String authorization);
}
