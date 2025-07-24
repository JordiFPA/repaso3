package controller;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import service.Mapper.VehiculoMapper;
import service.To.VehiculoTo;
import service.iVehiculoService;

@Path("/vehiculos")
public class VehiculoController {

    @Inject
    private iVehiculoService vehiculoService;

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerVehiculos(@Context UriInfo uriInfo) {
        List<VehiculoTo> vehiculos = vehiculoService.listarTodosVehiculos().stream().map(vehiculo -> {
            VehiculoTo toNuevo = VehiculoMapper.toTo(vehiculo);
            toNuevo.buildlink(uriInfo);
            return toNuevo;

        }).collect(Collectors.toList());
        return Response.status(Response.Status.OK)
                .entity(vehiculos).build();
    }

    @GET
    @Path("/{placa}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerVehiculoPorPlaca(@PathParam("placa") String placa, @Context UriInfo uriInfo) {
        VehiculoTo vehiculoTo = VehiculoMapper.toTo(this.vehiculoService.buscarVehiculoPorPlaca(placa));
        vehiculoTo.buildlink(uriInfo);
        return Response.status(Response.Status.OK)
                .entity(vehiculoTo).build();
    }

}
