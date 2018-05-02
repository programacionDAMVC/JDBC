package com.iesvirgendelcarmen.jdbc.teoria;

import java.util.ArrayList;
import java.util.List;

public class TestAplicacion {

	public static void main(String[] args) {
		List<LibroDTO> lista = new ArrayList<>();
		LibroDAO manipulacionLibros = new LibroDAOImp();
		List<LibroDTO> listaTotal = manipulacionLibros.listarTodosLibros();
		System.out.println("Nº total de libros: " + listaTotal.size());
		LibroDTO libroDTO = new LibroDTO(
				"Santa Tecla", null, null, null);
		System.out.println("Actualización: " + manipulacionLibros.
				actualizarCategoriaLibro(libroDTO, "Programacion"));
		System.out.println("Borrado: " + manipulacionLibros.
				borrarLibro("nombreLibro", "nombreAutor"));
		LibroDTO libro = new LibroDTO("nombreLibro", "nombreAutor", "editorial", "Programacion");
		lista.add(libro);
		lista.add(libro);
		lista.add(libro);
		lista.add(new LibroDTO("nombreLibro", "nombreAutor", "editorial", "Prgramacion"));
		manipulacionLibros.insertarListaLibros(lista);
		//manipulacionLibros.insertarLibro(libro);
		
	}

}
