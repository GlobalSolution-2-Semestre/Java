package br.com.mindjava;
import br.com.mindjava.bo.ColaboradorBO;
import br.com.mindjava.excecoes.BusinessException;
import br.com.mindjava.excecoes.ExcecoesConexao;
import br.com.mindjava.beans.Colaborador;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Path("/colaboradores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ColaboradorResource {

    private ColaboradorBO bo = new ColaboradorBO();

    @GET
    public Response listar() throws ExcecoesConexao {
        List<Colaborador> lista = bo.listar();
        return Response.ok(lista).build();
    }

    @POST
    public Response inserir(Colaborador c, @Context UriInfo uri) throws ExcecoesConexao, BusinessException {
        if (c == null) {
            throw new BusinessException("Requisição inválida: corpo JSON ausente ou mal formatado.");
        }
        bo.inserir(c);
        URI location = uri.getAbsolutePathBuilder().path(String.valueOf(c.getId())).build();
        return Response.created(location).entity(c).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") int id, Colaborador c) throws ExcecoesConexao, BusinessException {
        c.setId(id);
        bo.atualizar(c);
        return Response.ok(c).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") int id) throws ExcecoesConexao, BusinessException {
        bo.deletar(id);
        return Response.noContent().build();
    }
}