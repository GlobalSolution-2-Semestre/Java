package br.com.mindjava;
import br.com.mindjava.bo.RelatorioBO;
import br.com.mindjava.excecoes.BusinessException;
import br.com.mindjava.excecoes.ExcecoesConexao;
import br.com.mindjava.beans.Relatorio;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.net.URI;
import java.util.List;
@Path("/relatorios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RelatorioResource {
    private RelatorioBO bo = new RelatorioBO();

    @GET
    public Response listar() throws ExcecoesConexao {
        List<Relatorio> lista = bo.listar();
        return Response.ok(lista).build();
    }

    @POST
    public Response inserir(Relatorio r, @Context UriInfo uri)
            throws ExcecoesConexao, BusinessException {
        bo.inserir(r);
        URI location = uri.getAbsolutePathBuilder().path(String.valueOf(r.getId())).build();
        return Response.created(location).entity(r).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") int id) throws ExcecoesConexao, BusinessException {
        bo.deletar(id);
        return Response.noContent().build();
    }
}