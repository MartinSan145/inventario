package com.example.inventario.servicio;

import com.example.inventario.entidad.empleado;

import java.util.List;

public interface EmpleadoServicio {

    public List <empleado> listarTodoslosEmpleados();

    public empleado guardarEmpleados(empleado empleados);

    public empleado obtenerEmpleadoPorId(Long Id);

    public empleado actualizarEmpleados(empleado empleados);

    public void eliminarClientes(Long Id);
}
