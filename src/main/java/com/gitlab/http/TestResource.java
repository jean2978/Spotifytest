package com.gitlab.http;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.gitlab.client.SpotifyAuthRestClient;
import com.gitlab.client.SpotifyRestClient;
import com.gitlab.utility.ApplicationUtility;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;

@Path("/test")
public class TestResource {

    @Inject
    UriInfo info;

    @RestClient
    SpotifyAuthRestClient authRestClient;

    @RestClient
    SpotifyRestClient client;

    private final Template page;

    @ConfigProperty(name = "REDIRECT_URL")
    private String REDIRECT_URL;

    @ConfigProperty(name = "CLIENT_ID")
    private String CLIENT_ID;

    @ConfigProperty(name = "CLIENT_SECRET")
    private String CLIENT_SECRET;

    public TestResource(Template page) {
        this.page = page;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Uni<TemplateInstance> test() {
        return authRestClient.getToken(
                "authorization_code",
                info.getQueryParameters().getFirst("code"),
                REDIRECT_URL,
                "Basic " + ApplicationUtility.encodeBase64(CLIENT_ID + ":" + CLIENT_SECRET),
                MediaType.APPLICATION_FORM_URLENCODED)
                .onItem().transformToUni(token -> {
                    // Assuming client.getUser() returns a Uni<User> or similar reactive type
                    return client.getUser("Bearer " + token.getAccess_token())
                            .onItem().transform(user -> {
                                String url = user.getImages().get(0).getUrl();
                                return page.data("image", url);
                            });
                });
    }
}
