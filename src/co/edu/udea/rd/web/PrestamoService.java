package co.edu.udea.rd.web;

import java.util.Date;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.udea.rd.bl.PrestamoBL;
import co.edu.udea.rd.exception.MyException;


/**
* Clase que contiene los servicios para la tabla Préstamo
**/


@Component
@Path("Prestamo")
public class PrestamoService {

        /**
        * Inyección de dependencias 
        **/

	@Autowired
	PrestamoBL prestamoBL;
	
	Logger log = Logger.getLogger(this.getClass());
        
        /**
        * Método para actualizar un estado de préstamo en la base de datos. 
        **/

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("actualizarEstado")
	public String actualizarEstadoPrestamo(@QueryParam("username") String username,
			@QueryParam("idDispositivo") String idDispositivo, @QueryParam("estado") String estado) {
		try {
			prestamoBL.actualizarEstadoPrestamo(username, idDispositivo, estado);
		} catch (MyException e) {
			return e.getMessage();
		}
		return "Estado del prestamo modificado correctamente";
	}
        
        /**
        * Método para mandar una solicitud y procesarla, almacenándola en la base de datos 
        **/

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("solicitar")
	public String solicitarPrestamo(@QueryParam("idDipositivo") String idDipositivo,
			@QueryParam("username") String username, @QueryParam("fechaInicialPrestamo") Date fechaInicialPrestamo,
			@QueryParam("fechaFinalPrestamo") Date fechaFinalPrestamo) {
		try {
			prestamoBL.solicitarPrestamo(idDipositivo, username, fechaInicialPrestamo, fechaFinalPrestamo);
		} catch (MyException e) {
			return e.getMessage();
		}
		return "Prestamo solicitado correctamente";
	}
}
