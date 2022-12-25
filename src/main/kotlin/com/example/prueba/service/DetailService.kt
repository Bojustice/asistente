package com.example.prueba.service

import com.example.prueba.model.Detail
import com.example.prueba.model.Product
import com.example.prueba.repository.DetailRepository
import com.example.prueba.repository.InvoiceRepository
import com.example.prueba.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.server.ResponseStatusException

@Service
class DetailService {


    @Autowired
    lateinit var detailRepository: DetailRepository

    @Autowired
    lateinit var invoiceRepository: InvoiceRepository

    @Autowired
    lateinit var productRepository: ProductRepository


    fun list(): List<Detail> {
        return detailRepository.findAll()
    }

    fun save(detail: Detail): Detail {
        try {
            val response = detailRepository.save(detail)
            val responseProduct: Product = productRepository.findById(response.productId)
            responseProduct.apply {
                stock = stock?.minus(detail.quantity!!)
            }
            productRepository.save(responseProduct)
            calculateAndUpdateTotal(response)
            return response
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun update(detail: Detail): Detail {
        try {
            detailRepository.findById(detail.id)
                ?: throw Exception("El id ${detail.id} en detail no existe")
            return detailRepository.save(detail)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }


    fun updateHome(detail: Detail): Detail {
        try{
            val response = detailRepository.findById(detail.id)
                ?: throw Exception("El id no existe")
            response.apply {
                quantity = detail.quantity
            }
            return detailRepository.save(response)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }

    }

    fun calculateAndUpdateTotal(detail: Detail) {
        val totalCalculated = detailRepository.sumTotal(detail.invoiceId)
        val invoiceResponse = invoiceRepository.findById(detail.invoiceId)
        invoiceResponse.apply {
            total = totalCalculated
        }
        invoiceRepository.save(invoiceResponse)
    }
}



