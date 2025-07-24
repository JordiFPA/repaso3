package service;

import java.util.List;

import repository.modelo.Vehiculo;

public interface iVehiculoService {
    void guardarVehiculo(Vehiculo vehiculo);

    Vehiculo buscarVehiculoPorPlaca(String placa);

    List<Vehiculo> listarTodosVehiculos();

}
