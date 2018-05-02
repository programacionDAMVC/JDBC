package com.iesvirgendelcarmen.jdbc.teoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
		int borrados = 0;
		// DELETE FROM libro WHERE nombre = "Terminator" AND autor = "Paquito";
		String sql = "DELETE FROM libro WHERE nombre = ? AND autor = ?;";
		try (PreparedStatement preparedStatement = conexion.prepareStatement(sql);
){
			preparedStatement.setString(1, nombreLibro);
			preparedStatement.setString(2, nombreAutor);
			borrados = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return borrados != 0;
	}

	@Override
	public boolean actualizarCategoriaLibro(LibroDTO libro, String nombreCategoria) {
		int updates = 0;
		//  UPDATE libro SET categoria='Seguridad' WHERE nombre = 'Santa Tecla';
		String sql = "UPDATE libro SET categoria = ? WHERE nombre = ?;";
		try (PreparedStatement preparedStatement = conexion.prepareStatement(sql);
){
			preparedStatement.setString(1, nombreCategoria);
			preparedStatement.setString(2, libro.getNombreLibro());
			updates = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updates != 0;
	}

	@Override
	public boolean insertarLibro(LibroDTO libro) {
		int resultado = 0;
		//  INSERT INTO libro (nombre, autor, editorial, categoria) VALUES ('1','2','3','4');
		String sql = "INSERT INTO libro (nombre, autor, editorial, categoria)"
				+ " VALUES (?,?,?,?);";
		try (PreparedStatement psStatement = conexion.prepareStatement(sql);){
			psStatement.setString(1, libro.getNombreLibro());
			psStatement.setString(2, libro.getNombreAutor());
			psStatement.setString(3, libro.getEditorial());
			psStatement.setString(4, libro.getNombreCategoria());
			resultado = psStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error introduciendo datos");
		}
		System.out.println("Insertando datos: " + (resultado == 1));
		return resultado == 1;
	}

	@Override
	public boolean insertarListaLibros(List<LibroDTO> listaLibros) {
		try {
			conexion.setAutoCommit(false);
			for (LibroDTO libroDTO : listaLibros) {
				insertarLibro(libroDTO);
			}
			conexion.commit();
			System.out.println("Insertando datos de lista correctamente ");
			conexion.setAutoCommit(true);
			return true;
		} catch (SQLException e) {
			try {
				conexion.rollback();
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} finally {
				System.out.println("No se puede insertar datos de lista");
				try {
					conexion.setAutoCommit(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				return false;
			}
		}
		
	}

}
