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

    @Inject

    ColaboradorBO bo;

    @GET
    public Response listar() throws ExcecoesConexao {

        System.out.println("[DEBUG] GET /colaboradores chamado");

        List<Colaborador> lista = bo.listar();

        return Response.ok(lista).build();

    }


    @POST
    public Response inserir(Colaborador c, @Context UriInfo uri) throws ExcecoesConexao, BusinessException {

        System.out.println("[DEBUG] POST /colaboradores chamado");

        System.out.println("[DEBUG] Corpo recebido: " + c);

        if (c == null) {

            System.out.println("[ERRO] Objeto Colaborador chegou nulo no Resource!");

            throw new BusinessException("Requisição inválida: corpo JSON ausente ou mal formatado.");

        }

        bo.inserir(c);

        URI location = uri.getAbsolutePathBuilder().path(String.valueOf(c.getId())).build();

        return Response.created(location).entity(c).build();

    }


    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") int id, Colaborador c) throws ExcecoesConexao, BusinessException {

        System.out.println("[DEBUG] PUT /colaboradores/" + id + " chamado");

        c.setId(id);

        bo.atualizar(c);

        return Response.ok(c).build();

    }


    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") int id) throws ExcecoesConexao, BusinessException {
        System.out.println("[DEBUG] DELETE /colaboradores/" + id + " chamado");
        bo.deletar(id);
        return Response.noContent().build();

    }

}

