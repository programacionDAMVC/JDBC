package com.iesvirgendelcarmen.jdbc.teoria;

import java.util.List;

public interface LibroDAO {
	
	List<LibroDTO> listarTodosLibros();
	List<LibroDTO> listarLibrosDisponibles();
	boolean borrarLibro(String nombreLibro, String nombreAutor);
	boolean actualizarCategoriaLibro(LibroDTO libro, String nombreCategoria);
	boolean insertarLibro(LibroDTO libro);
	boolean insertarListaLibros(List<LibroDTO> listaLibros);
	
	// **********************
}
