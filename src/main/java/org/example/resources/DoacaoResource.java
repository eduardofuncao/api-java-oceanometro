package org.example.resources;

import org.example.entities.Doacao;
import org.example.entities.dtos.SearchDoacaoDto;
import org.example.repositories.DoacaoRepository;
import org.example.services.DoacaoService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("doacao")
public class DoacaoResource {

    public DoacaoRepository doacaoRepository;
    public DoacaoService doacaoService;

    public DoacaoResource() {
        doacaoRepository = new DoacaoRepository();
        doacaoService = new DoacaoService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(
            @QueryParam("orderby") String orderBy,
            @QueryParam("direction") String direction,
            @QueryParam("limit") int limit,
            @QueryParam("offset") int offset,
            @QueryParam("nome") String nome,
            @QueryParam("preco") double preco
    ) {
        return Response.ok(doacaoService.getAll(nome, preco,
                orderBy, direction, limit, offset)).build();
    }

    @GET
    @Path("categoria/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllByCategoria(@PathParam("id") int idCategoria) {
        return Response.ok(doacaoRepository.getAllByCategoria(idCategoria)).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") int id) {
        var doacao = doacaoRepository.get(id);
        return doacao.isPresent() ?
                Response.ok(doacao.get()).build() :
                Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("top10")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTop10() {
        return Response.ok(doacaoRepository.getTop10()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Doacao doacao) {
        try {
            doacaoService.create(doacao);
            return Response.status(Response.Status.CREATED).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, Doacao doacao) {
        try {
            doacaoService.update(id, doacao);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") int id) {
        try {
            doacaoService.delete(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}