import axios from 'axios';
const API_BASE_URL = 'http://localhost:8081/api/matricula/v1/vehiculos';


export const obtenerListaVehiculos = async () => {
    const response = await axios.get(API_BASE_URL);
    return response.data;
};


export const obtenerRecursoPorUrl = async (url) => {
    const response = await axios.get(url);
    return response.data;
};