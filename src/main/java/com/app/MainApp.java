package com.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import com.model.Empleado;

public class MainApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int opcion = 0;
		Empleado miEmpleado;
		String menu = "";
		String nombre;
		String documento;
		String apellido;
		String direccion;
		String telefono;
		
		
		EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
		menu+="1.Registrar Empleado \n";
		menu+="2.Buscar Empleado \n";
		menu+="3.Actualizar Empleado \n";
		menu+="4.Eliminar Empleado \n";
		menu+="5.Salir \n Eliga una opcion";
		while (opcion!=5) {

			opcion=Integer.parseInt(JOptionPane.showInputDialog(menu));
			
			switch (opcion) {
			case 1:
				miEmpleado = new Empleado();
				
				documento=JOptionPane.showInputDialog("Ingrese el documento del empleado");
				miEmpleado.setDocumento(documento);
					
				nombre=JOptionPane.showInputDialog("Ingrese el nombre del empleado");
				miEmpleado.setNombre(nombre);
				
				apellido=JOptionPane.showInputDialog("Ingrese el apellido del Empleado");
				miEmpleado.setApellido(apellido);
				
				direccion=JOptionPane.showInputDialog("Ingrese la direccion del Empleado");
				miEmpleado.setDireccion(direccion);
				
				telefono=JOptionPane.showInputDialog("Ingrese el telefono del Empleado");
				miEmpleado.setTelefono(telefono);
				System.out.println(miEmpleado);
				
				Empleado consulta = entity.find(Empleado.class, documento);
				if(consulta==null){
					entity.getTransaction().begin();
					entity.persist(miEmpleado);
					entity.getTransaction().commit();
					JOptionPane.showMessageDialog(null, "Empleado Registrado");
				}else{
					JOptionPane.showMessageDialog(null, "El Empleado Ya Se Encuentra Registrado");

				}
			
				break;

			case 2:
				
				documento=JOptionPane.showInputDialog("Ingrese el documento del empleado a consultar");
				miEmpleado = new Empleado();
				miEmpleado = entity.find(Empleado.class, documento);
				if (miEmpleado != null) {
				JOptionPane.showMessageDialog(null,"\n Documento: " + miEmpleado.getDocumento()+"\n Nombre: " + miEmpleado.getNombre() +
				  "\n Apellido: " + miEmpleado.getApellido() + "\n Direccion: " + miEmpleado.getDireccion()
				 + "\n Telefono: " + miEmpleado.getTelefono());
					System.out.println(miEmpleado);
					System.out.println();
				} else {
					System.out.println();
					JOptionPane.showMessageDialog(null, "El Empleado No Se Encuentra");
					List<Empleado> listaEmpleados= new ArrayList<>();
					Query query=entity.createQuery("SELECT e FROM Empleado e");
					listaEmpleados=query.getResultList();
					for (Empleado l : listaEmpleados) {
						System.out.println(l);
					}
					
					System.out.println();
				}

				break;
			case 3:
				documento=JOptionPane.showInputDialog("Ingrese el documento del empleado a Actualizar");
				miEmpleado = new Empleado();

				miEmpleado = entity.find(Empleado.class, documento);
				if (miEmpleado != null) {
					System.out.println(miEmpleado);
					
					nombre=JOptionPane.showInputDialog("Ingrese el nombre del empleado");
					miEmpleado.setNombre(nombre);
					
					apellido=JOptionPane.showInputDialog("Ingrese el apellido del empleado");
					miEmpleado.setApellido(apellido);
					
					direccion=JOptionPane.showInputDialog("Ingrese la direccion del empleado");
					miEmpleado.setDireccion(direccion);
					
					telefono=JOptionPane.showInputDialog("Ingrese el telefono del empleado");
					miEmpleado.setDireccion(telefono);
					
					entity.getTransaction().begin();
					entity.merge(miEmpleado);
					entity.getTransaction().commit();
					JOptionPane.showMessageDialog(null, "Empleado Actualizado Exitosamente");
					System.out.println();
				} else {
					JOptionPane.showMessageDialog(null, "Empleado no encontrado");
					System.out.println();
				}
				break;
			case 4:
				documento=JOptionPane.showInputDialog("Ingrese el documento del empleado a Eliminar");
				miEmpleado = new Empleado();

				miEmpleado = entity.find(Empleado.class, documento);
				if (miEmpleado != null) {
					System.out.println(miEmpleado);
					entity.getTransaction().begin();
					entity.remove(miEmpleado);
					entity.getTransaction().commit();
					JOptionPane.showMessageDialog(null, "Empleado Eliminado Exitosamente");
				} else {
					JOptionPane.showMessageDialog(null, "Empleado No Encontrado");
				}
				break;
				
			case 5:entity.close();JPAUtil.shutdown();
			break;

			default:
				JOptionPane.showMessageDialog(null, "Opcion no Valida");
				break;
			}
		}
	}

}