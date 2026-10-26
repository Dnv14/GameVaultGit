package com.example.GameVault.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.GameVault.model.Juego;

@Repository
public interface JuegoRepository extends JpaRepository<Juego, Long> {
    List<Juego> findByTituloContainingIgnoreCase(String titulo);

    @Query("SELECT j FROM Juego j WHERE LOWER(j.descripcion) LIKE LOWER(CONCAT('%', :palabra, '%'))")
    List<Juego> buscarPorDescripcion(@Param("palabra") String palabra);

    @Query("SELECT j FROM Juego j ORDER BY j.id DESC")
    List<Juego> obtenerJuegosRecientes();


    List<Juego> findByUsuarioId(Long usuarioId);

    
    @Query("SELECT j FROM Juego j " +
            "WHERE LOWER(j.titulo) " +
            "LIKE LOWER(CONCAT('%', :palabra, '%')) " +
            "OR LOWER(j.categoria) " +
            "LIKE LOWER(CONCAT('%', :palabra, '%'))")
    List<Juego> buscarPorTituloOCategoria(@Param("palabra") String palabra);

}
