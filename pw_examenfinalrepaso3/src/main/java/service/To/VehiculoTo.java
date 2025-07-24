package service.To;

import java.net.URI;
import java.util.HashMap;

import controller.VehiculoController;
import jakarta.ws.rs.core.UriInfo;

public class VehiculoTo {
    private Integer id;
    private String placa;
    private String marca;
    private String modelo;
    private String color;
    private HashMap<String, String> _links = new HashMap<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public HashMap<String, String> get_links() {
        return _links;
    }

    public void set_links(HashMap<String, String> _links) {
        this._links = _links;
    }

    public void buildlink(UriInfo uriInfo) {
        URI autosTraidos = uriInfo.getBaseUriBuilder().path(VehiculoController.class)
                .path(VehiculoController.class, "obtenerVehiculoPorPlaca").build(placa);
        _links.put("vehiculo", autosTraidos.toString());
    }
}
