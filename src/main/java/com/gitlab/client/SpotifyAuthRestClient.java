package com.gitlab.client;

import java.util.Set;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.gitlab.pojo.Token;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/")
@RegisterRestClient
public interface SpotifyAuthRestClient {

    @Path("/token")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    Uni<Token> getToken(
            @QueryParam("grant_type") String grant_type,
            @QueryParam("code") String code,
            @QueryParam("redirect_uri") String redirect_uri,
            @HeaderParam("Authorization") String authorization,
            @HeaderParam("Content-Type") String content_type);
}
