package com.example.prueba.service

import com.example.prueba.model.Asistente
import com.example.prueba.repository.AsistenteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.server.ResponseStatusException


@Service
class AsistenteService {
    @Autowired
    lateinit var asistenteRepository: AsistenteRepository

    fun list(): List<Asistente> {
        return asistenteRepository.findAll()
    }

    fun save(asistente: Asistente): Asistente {
        return asistenteRepository.save(asistente)
    }

    fun update(asistente: Asistente):Asistente{
        try{
            asistenteRepository.findById(asistente.id)
                ?:throw Exception("El id ${asistente.id} en asistente no existe")
            return asistenteRepository.save(asistente)
        }
        catch (ex:Exception){
            throw  ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }


    fun updateName(asistente: Asistente): Asistente {
        try {
            val response = asistenteRepository.findById(asistente.id)
                ?: throw Exception("El id no existe")
                response.apply {
                nombres = asistente.nombres
            }
            return asistenteRepository.save(response)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }

    }

    fun list (pageable: Pageable, asistente: Asistente): Page<Asistente> {
        val matcher = ExampleMatcher.matching()
            .withIgnoreNullValues()
            .withMatcher(("field"), ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
        return asistenteRepository.findAll(Example.of(asistente, matcher), pageable)
    }
}