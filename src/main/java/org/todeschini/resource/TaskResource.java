package org.todeschini.resource;

import io.quarkus.runtime.StartupEvent;
import lombok.extern.slf4j.Slf4j;
import org.todeschini.entity.Task;
import org.todeschini.service.AbstractFacade;

import java.time.LocalDate;
import java.util.List;
import javax.enterprise.event.Observes;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
//import jakarta.enterprise.event.Observes;

/**
 *
 * @author Artur
 */
@Path("/tasks")
@Slf4j

public class TaskResource extends AbstractFacade<Task> {

    public TaskResource() {
        super(Task.class);
        var t = Task.builder()
                .criada(LocalDate.now())
                .status(0)
                .descricao("d1")
                .titulo("t1").build();
        super.create(t);
    }

    @POST
    @Override
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public void create(Task entity) {
        entity.setCriada(LocalDate.now());
        super.create(entity);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public void edit(@PathParam("id") Long id, Task entity) {
        var original = super.find(id);

        if (entity.getStatus() == 1 && original.getStatus() == 0) {
            original.setFinalizada(LocalDate.now());
        } else {
            original.setFinalizada(null);
        }

        original.setDescricao(entity.getDescricao());
        original.setTitulo(entity.getTitulo());
        original.setStatus(entity.getStatus());

        super.edit(original);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Task find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Task> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Task> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

}
