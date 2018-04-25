package com.iesvirgendelcarmen.jdbc.teoria;

import java.util.List;

public class TestAplicacion {

	public static void main(String[] args) {
		LibroDAO manipulacionLibros = new LibroDAOImp();
		List<LibroDTO> listaTotal = manipulacionLibros.listarTodosLibros();
		
		System.out.println("Nº total de libros: " + listaTotal.size());
	}

}
