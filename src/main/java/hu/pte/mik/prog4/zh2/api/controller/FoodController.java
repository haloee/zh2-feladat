package hu.pte.mik.prog4.zh2.api.controller;

import hu.pte.mik.prog4.zh2.entity.FoodEntity;
import hu.pte.mik.prog4.zh2.service.FoodService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;



@Path("/api/foods")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FoodController {

    private final FoodService foodService = new FoodService();

    @GET
    public List<FoodEntity> listAllFoods() {
        return foodService.listAll();
    }

    @GET
    @Path("/{id}")
    public FoodEntity getFood(@PathParam("id") Long id) {
        return foodService.findById(id);
    }

    @POST
    public Response saveFood(FoodEntity food) {
        FoodEntity saved = foodService.save(food);
        return Response.status(Response.Status.CREATED).entity(saved).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateFood(@PathParam("id") Long id, FoodEntity food) {
        food.setId(id);
        FoodEntity updated = foodService.update(food);
        return Response.ok(updated).build();
    }
}
