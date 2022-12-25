package com.example.prueba.service

import com.example.prueba.model.Invoice
import com.example.prueba.repository.InvoiceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.server.ResponseStatusException

@Service
class InvoiceService {
    @Autowired
    lateinit var invoiceRepository: InvoiceRepository

    fun list(): List<Invoice> {
        return invoiceRepository.findAll()
    }

    fun listTotalMoreThan(total:Double?): List<Invoice>? {
        return invoiceRepository.findTotalMoreThan(total)
    }

    fun save(invoice: Invoice): Invoice {
        return invoiceRepository.save(invoice)
    }

    fun update(invoice: Invoice):Invoice{
        try{
            invoiceRepository.findById(invoice.id)
                ?:throw Exception("El id ${invoice.id} en invoice no existe")
            return invoiceRepository.save(invoice)
        }
        catch (ex:Exception){
            throw  ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }


    fun updateHome(invoice: Invoice): Invoice {
        try {
            val response = invoiceRepository.findById(invoice.id)
                ?: throw Exception("El id no existe")
                response.apply {
                code = invoice.code
            }
            return invoiceRepository.save(response)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }

    }
}