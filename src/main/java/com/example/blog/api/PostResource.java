package com.example.blog.api;

import com.example.blog.auth.BlogPrincipal;
import com.example.blog.core.Post;
import com.example.blog.db.PostDAO;
import io.dropwizard.auth.Auth;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Path("/posts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PostResource {
    
    private final PostDAO postDAO;

    public PostResource(PostDAO postDAO) {
        this.postDAO = postDAO;
    }

    @GET
    public List<Post> getPosts(@Auth BlogPrincipal principal) {
        return postDAO.findAll();
    }

    @GET
    @Path("/{id}")
    public Response getPostById(@PathParam("id") Long id, @Auth BlogPrincipal principal) {
        Optional<Post> post = postDAO.findById(id);
        if (post.isPresent()) {
            return Response.ok(post.get()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/published")
    public List<Post> getPublishedPosts(@Auth BlogPrincipal principal) {
        return postDAO.findPublished();
    }

    @GET
    @Path("/author/{author}")
    public List<Post> getPostsByAuthor(@PathParam("author") String author, @Auth BlogPrincipal principal) {
        return postDAO.findByAuthor(author);
    }

    @POST
    public Response createPost(@Valid Post post, @Auth BlogPrincipal principal) {
        post.setCreatedAt(Instant.now());
        post.setUpdatedAt(Instant.now());
        
        Long id = postDAO.insert(post);
        post.setId(id);
        return Response.status(Response.Status.CREATED).entity(post).build();
    }

    @PUT
    @Path("/{id}")
    public Response updatePost(@PathParam("id") Long id, @Valid Post post, @Auth BlogPrincipal principal) {
        Optional<Post> existingPost = postDAO.findById(id);
        if (!existingPost.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        post.setId(id);
        post.setUpdatedAt(Instant.now());
        
        postDAO.update(post);
        return Response.ok(post).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletePost(@PathParam("id") Long id, @Auth BlogPrincipal principal) {
        Optional<Post> post = postDAO.findById(id);
        if (!post.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        postDAO.delete(id);
        return Response.noContent().build();
    }
}