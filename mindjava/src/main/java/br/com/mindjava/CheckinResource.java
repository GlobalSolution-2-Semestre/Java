package br.com.mindjava;
import br.com.mindjava.bo.CheckinBO;
import br.com.mindjava.excecoes.BusinessException;
import br.com.mindjava.excecoes.ExcecoesConexao;
import br.com.mindjava.beans.CheckinHumor;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Path("/checkins")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CheckinResource {

    @Inject
    CheckinBO bo;

    @GET
    public Response listar() throws ExcecoesConexao {
        List<CheckinHumor> lista = bo.listar();
        return Response.ok(lista).build();
    }

    @POST
    public Response inserir(CheckinHumor checkin, @Context UriInfo uri)
            throws ExcecoesConexao, BusinessException {
        bo.inserir(checkin);
        URI location = uri.getAbsolutePathBuilder().path(String.valueOf(checkin.getId())).build();
        return Response.created(location).entity(checkin).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") int id) throws ExcecoesConexao, BusinessException {
        bo.deletar(id);
        return Response.noContent().build();
    }
}
