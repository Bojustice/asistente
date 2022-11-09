package com.example.prueba.repository

import com.example.prueba.model.Asistente
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository

interface AsistenteRepository:JpaRepository<Asistente, Long> {
}