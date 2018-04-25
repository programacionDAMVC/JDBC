package com.iesvirgendelcarmen.jdbc.teoria;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LibroDAOImp implements LibroDAO {

	private static Connection conexion = Conexion.getConexion();
	
	@Override
	public List<LibroDTO> listarTodosLibros() {
		List<LibroDTO> listaLibros = new ArrayList<>();
		// Crear objeto Statement
		String sql = "SELECT * FROM libro;";
		try (Statement statement = conexion.createStatement();){
			// Crear objeto ResultSet
			ResultSet resulset = statement.executeQuery(sql);
			while (resulset.next()) {
				LibroDTO libro = new LibroDTO(resulset.getString(2),
						resulset.getString(3),
						resulset.getString(4),
						resulset.getString(5));
				listaLibros.add(libro);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listaLibros;
	}

	@Override
	public List<LibroDTO> listarLibrosDisponibles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean borrarLibro(String nombreLibro, String nombreAutor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean actualizarCategoriaLibro(String nombreCategoria) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertarLibro(LibroDTO libro) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertarListaLibros(List<LibroDTO> listaLibros) {
		// TODO Auto-generated method stub
		return false;
	}

}
