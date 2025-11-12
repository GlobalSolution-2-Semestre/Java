package br.com.mindjava;

import br.com.mindjava.bo.AlertaBO;
import br.com.mindjava.excecoes.BusinessException;
import br.com.mindjava.excecoes.ExcecoesConexao;
import br.com.mindjava.beans.Alerta;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Path("/alertas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlertaResource {

    @Inject
    AlertaBO bo;

    @GET
    public Response listar() throws ExcecoesConexao {
        List<Alerta> lista = bo.listar();
        return Response.ok(lista).build();
    }

    @POST
    public Response inserir(Alerta alerta, @Context UriInfo uri) throws ExcecoesConexao, BusinessException {
        bo.inserir(alerta);
        URI location = uri.getAbsolutePathBuilder().path(String.valueOf(alerta.getId())).build();
        return Response.created(location).entity(alerta).build();
    }
    @DELETE

    @Path("/{id}")

    public Response deletar(@PathParam("id") int id) throws ExcecoesConexao, BusinessException {
        System.out.println("[DEBUG] Chegou no Resource com id=" + id);
        boolean deletado = bo.deletar(id);
        if (deletado) {
            System.out.println("[DEBUG] Alerta com ID " + id + " deletado com sucesso!");
            return Response.noContent().build();

        } else {
            System.out.println("[WARN] Nenhum alerta encontrado com ID " + id);
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Nenhum alerta encontrado com o ID informado.")
                    .build();

        }

    }
}

