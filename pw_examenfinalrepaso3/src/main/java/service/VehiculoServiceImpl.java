package service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import repository.iVehiculoRepo;
import repository.modelo.Vehiculo;

@Transactional
@ApplicationScoped
public class VehiculoServiceImpl implements iVehiculoService {
    @Inject
    private iVehiculoRepo vehiculoRepo;

    @Override
    public void guardarVehiculo(Vehiculo vehiculo) {
        this.vehiculoRepo.guardar(vehiculo);

    }

    @Override
    public Vehiculo buscarVehiculoPorPlaca(String placa) {
        return this.vehiculoRepo.buscarPorPlaca(placa);
    }

    @Override
    public List<Vehiculo> listarTodosVehiculos() {
        return this.vehiculoRepo.buscarTodos();
    }

}
