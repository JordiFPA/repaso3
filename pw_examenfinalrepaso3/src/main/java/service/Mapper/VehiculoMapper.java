package service.Mapper;

import repository.modelo.Vehiculo;
import service.To.VehiculoTo;

public class VehiculoMapper {

    public static VehiculoTo toTo(Vehiculo vehiculo) {
        VehiculoTo veTo = new VehiculoTo();
        veTo.setId(vehiculo.getId());
        veTo.setPlaca(vehiculo.getPlaca());
        veTo.setMarca(vehiculo.getMarca());
        veTo.setModelo(vehiculo.getModelo());
        veTo.setColor(vehiculo.getColor());
        return veTo;
    }

    public static  Vehiculo toEntity(VehiculoTo vehiculoTo) {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setId(vehiculoTo.getId());
        vehiculo.setPlaca(vehiculoTo.getPlaca());
        vehiculo.setMarca(vehiculoTo.getMarca());
        vehiculo.setModelo(vehiculoTo.getModelo());
        vehiculo.setColor(vehiculoTo.getColor());
        return vehiculo;
    }

}
