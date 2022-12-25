package com.example.prueba.controller

import com.example.prueba.model.Asistente
import com.example.prueba.service.AsistenteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

import javax.validation.Valid

@RestController
@RequestMapping("/asistente")
class AsistenteController {
    @Autowired
    lateinit var asistenteService: AsistenteService

    @GetMapping
    fun list (asistente: Asistente, pageable: Pageable):ResponseEntity<*>{
        val response= asistenteService.list(pageable, asistente)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @PostMapping
    fun save(@RequestBody @Valid asistente:Asistente):ResponseEntity<Asistente>{
        return ResponseEntity (asistenteService.save(asistente), HttpStatus.OK)
    }

    @PutMapping
    fun update (@RequestBody asistente:Asistente): ResponseEntity<Asistente> {
        return ResponseEntity(asistenteService.update(asistente), HttpStatus.OK)
    }

    @PatchMapping
    fun updateName(@RequestBody asistente: Asistente): ResponseEntity<Asistente> {
        return ResponseEntity(asistenteService.updateName(asistente), HttpStatus.OK)
    }



}