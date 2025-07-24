<template>
  <div class="container">
    <h1>Sistema Vehicular</h1>

    <!-- Sección 1: Tabla con la lista de vehículos -->
    <div class="tabla-container">
      <h2>Listado de Vehículos</h2>
      <table>
        <thead>
          <tr>
            <th>Id</th>
            <th>Placa</th>
            <th>Marca</th>
            <th>Modelo</th>
            <th>Color</th>
            <th>Acción</th>
          </tr>
        </thead>
        <tbody>
          <!-- Mensaje mientras se cargan los datos -->
          <tr v-if="cargando">
            <td colspan="6" class="status-msg">
              Cargando lista de vehículos...
            </td>
          </tr>
          <!-- Mensaje si no hay vehículos después de cargar -->
          <tr v-else-if="!listaVehiculos.length">
            <td colspan="6" class="status-msg">No se encontraron vehículos.</td>
          </tr>
          <!-- Bucle para renderizar cada vehículo en la tabla -->
          <tr v-else v-for="vehiculo in listaVehiculos" :key="vehiculo.id">
            <td>{{ vehiculo.id }}</td>
            <td>{{ vehiculo.placa }}</td>
            <td>{{ vehiculo.marca }}</td>
            <td>{{ vehiculo.modelo }}</td>
            <td>{{ vehiculo.color }}</td>
            <td>
              <!-- El botón llama al método y le pasa el objeto 'vehiculo' completo de esta fila -->
              <button @click="visualizarDetalles(vehiculo)">Visualizar</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <hr v-if="vehiculoSeleccionado" />

    <!-- Sección 2: Formulario para mostrar los detalles del vehículo seleccionado -->
    <!-- Este bloque solo es visible si 'vehiculoSeleccionado' no es nulo -->
    <div v-if="vehiculoSeleccionado" class="detalle-container">
      <h2>Detalles del Vehículo</h2>
      <div class="detalle-form">
        <div class="form-group">
          <label>Id:</label>
          <input type="text" :value="vehiculoSeleccionado.id" readonly />
        </div>
        <div class="form-group">
          <label>Placa:</label>
          <input type="text" :value="vehiculoSeleccionado.placa" readonly />
        </div>
        <div class="form-group">
          <label>Marca:</label>
          <input type="text" :value="vehiculoSeleccionado.marca" readonly />
        </div>
        <div class="form-group">
          <label>Modelo:</label>
          <input type="text" :value="vehiculoSeleccionado.modelo" readonly />
        </div>
        <div class="form-group">
          <label>Color:</label>
          <input type="text" :value="vehiculoSeleccionado.color" readonly />
        </div>
      </div>
    </div>

    <!-- Mensaje de error general para el usuario -->
    <p v-if="errorMsg" class="error-msg">{{ errorMsg }}</p>
  </div>
</template>

<script>
// Importamos las funciones de nuestro cliente de API
import {
  obtenerListaVehiculos,
  obtenerRecursoPorUrl,
} from "../clients/InventarioClient";

export default {
  data() {
    return {
      listaVehiculos: [], // Almacena la lista para la tabla
      vehiculoSeleccionado: null, // Almacena el objeto del vehículo para el detalle
      cargando: false, // Para mostrar un mensaje de "Cargando..."
      errorMsg: "", // Para mostrar errores al usuario
    };
  },
  // 'mounted' se ejecuta automáticamente cuando el componente se carga por primera vez
  mounted() {
    this.cargarVehiculos();
  },
  methods: {
    // Método para obtener la lista inicial de vehículos
    async cargarVehiculos() {
      this.cargando = true;
      this.errorMsg = "";
      try {
        this.listaVehiculos = await obtenerListaVehiculos();
      } catch (error) {
        this.errorMsg =
          "Error al cargar la lista de vehículos desde el servidor.";
        console.error("Error en cargarVehiculos:", error);
      } finally {
        this.cargando = false;
      }
    },
    // Método que se ejecuta al hacer clic en el botón "Visualizar"
    async visualizarDetalles(vehiculo) {
      // Imprimimos en consola el objeto recibido para depurar
      console.log("Se hizo clic en Visualizar. Objeto recibido:", vehiculo);

      this.errorMsg = "";
      this.vehiculoSeleccionado = null; // Reiniciamos el detalle
      const urlDetalle = vehiculo._links.vehiculo;

      console.log("Siguiendo link HATEOAS a la URL:", urlDetalle);

      try {
        const detallesCompletos = await obtenerRecursoPorUrl(urlDetalle);

        // Guardamos el resultado en nuestro estado, lo que hará que el formulario de detalle aparezca
        this.vehiculoSeleccionado = detallesCompletos;
      } catch (error) {
        this.errorMsg =
          "No se pudieron obtener los detalles del vehículo desde el servidor.";
        console.error("Error en visualizarDetalles al llamar al API:", error);
      }
    },
  },
};
</script>

<style scoped>
.container {
  max-width: 960px;
  margin: 20px auto;
  font-family: Arial, sans-serif;
}
.tabla-container,
.detalle-container {
  border: 1px solid #ddd;
  padding: 20px;
  border-radius: 8px;
  margin-top: 20px;
  background-color: #fff;
}
table {
  width: 100%;
  border-collapse: collapse;
}
th,
td {
  border: 1px solid #ddd;
  padding: 12px;
  text-align: left;
}
th {
  background-color: #f2f2f2;
}
button {
  padding: 8px 12px;
  cursor: pointer;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
}
button:hover {
  background-color: #0056b3;
}
hr {
  margin: 40px 0;
  border: 0;
  border-top: 1px solid #eee;
}
.form-group {
  margin-bottom: 1rem;
}
.form-group label {
  display: block;
  margin-bottom: 0.5rem;
  font-weight: bold;
}
.form-group input {
  width: 100%;
  padding: 8px;
  box-sizing: border-box;
  background-color: #e9ecef;
  border: 1px solid #ccc;
  border-radius: 4px;
}
.error-msg {
  color: #dc3545;
  font-weight: bold;
  margin-top: 20px;
  padding: 10px;
  background-color: #f8d7da;
  border: 1px solid #f5c6cb;
  border-radius: 4px;
}
.status-msg {
  text-align: center;
  color: #6c757d;
  font-style: italic;
}
</style>