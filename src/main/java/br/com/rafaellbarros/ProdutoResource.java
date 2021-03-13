package br.com.rafaellbarros;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {

    @GET
    public List<Produto> buscarTodosProdutos() {
        return Produto.listAll();
    }

    @GET
    @Path("{id}")
    public Produto buscarPorId(@PathParam("id") final Long  id) {
        final Optional<Produto> produtoOp = Produto.findByIdOptional(id);
        if (produtoOp.isEmpty()) {
            throw new NotFoundException();
        }
        return produtoOp.get();
    }

    @POST
    @Transactional
    public Produto cadastrar(final CadastrarProdutoDTO produtoDTO) {
        Produto produto = new Produto();
        produto.nome = produtoDTO.nome;
        produto.valor = produtoDTO.valor;
        produto.persist();
        return produto;
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Produto atualizar(@PathParam("id") final Long  id, final CadastrarProdutoDTO produtoDTO) {
        Optional<Produto> produtoOp = Produto.findByIdOptional(id);
        if (produtoOp.isEmpty()) {
            throw new NotFoundException();
        }
        Produto produto = produtoOp.get();
        produto.nome = produtoDTO.nome;
        produto.valor = produtoDTO.valor;
        produto.persist();
        return produto;
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void excluir(@PathParam("id") final Long  id) {
        Optional<Produto> produtoOp = Produto.findByIdOptional(id);
        produtoOp.ifPresentOrElse(Produto::delete, () -> {
            throw  new NotFoundException();
        });
    }
}
