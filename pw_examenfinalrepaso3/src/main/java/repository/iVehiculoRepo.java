package repository;

import java.util.List;

import repository.modelo.Vehiculo;

public interface iVehiculoRepo {
    void guardar(Vehiculo vehiculo);

    Vehiculo buscarPorPlaca(String placa);

    List<Vehiculo> buscarTodos();
}
