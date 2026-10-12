package com.example.inventario.servicio;

import com.example.inventario.entidad.empleado;
import com.example.inventario.repositorio.EmpleadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoServicioImple implements EmpleadoServicio{

    @Autowired
    private EmpleadoRepositorio empleadoRepositorio;

    @Override
    public List<empleado> listarTodoslosEmpleados() {

        return empleadoRepositorio.findAll();

    }

    @Override
    public empleado guardarEmpleados(empleado empleados) {
        return empleadoRepositorio.save(empleados);
    }

    @Override
    public empleado obtenerEmpleadoPorId(Long Id) {
        return null;
    }

    @Override
    public empleado actualizarEmpleados(empleado empleados) {
        return null;
    }

    @Override
    public void eliminarClientes(Long Id) {

    }
}
