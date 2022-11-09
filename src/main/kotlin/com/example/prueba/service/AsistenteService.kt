package com.example.prueba.service

import com.example.prueba.model.Asistente
import com.example.prueba.repository.AsistenteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AsistenteService {
    @Autowired
    lateinit var asistenteRepository: AsistenteRepository

    fun list():List<Asistente>{
        return asistenteRepository.findAll()
    }

    fun save(asistente:Asistente):Asistente{
        return asistenteRepository.save(asistente)
    }

}