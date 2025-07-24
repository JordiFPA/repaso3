package repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import repository.modelo.Vehiculo;

@Transactional
@ApplicationScoped
public class VehiculoRepoImpl implements iVehiculoRepo {

    @Inject
    private EntityManager em;

    @Override
    public void guardar(Vehiculo vehiculo) {

        this.em.persist(vehiculo);
    }

    @Override
    public Vehiculo buscarPorPlaca(String placa) {
        TypedQuery<Vehiculo> myQuery = this.em.createQuery("SELECT v From Vehiculo v WHERE v.placa = :placa",
                Vehiculo.class);
        myQuery.setParameter("placa", placa);
        return (Vehiculo) myQuery.getSingleResult();
    }

    @Override
    public List<Vehiculo> buscarTodos() {
        TypedQuery<Vehiculo> myQuery = this.em.createQuery("SELECT v from Vehiculo v", Vehiculo.class);
        return myQuery.getResultList();
    }

}
