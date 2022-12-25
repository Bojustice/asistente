package com.example.prueba.service

import com.example.prueba.model.Detail
import com.example.prueba.model.Product
import com.example.prueba.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.server.ResponseStatusException
import com.example.prueba.repository.DetailRepository

@Service
class ProductService {
    @Autowired
    lateinit var productRepository: ProductRepository

    fun list(): List<Product> {
        return productRepository.findAll()
    }

    fun save(product: Product): Product {
        return productRepository.save(product)
    }

    fun update(product: Product):Product{
        try{
            productRepository.findById(product.id)
                ?:throw Exception("El id ${product.id} en product no existe")
            return productRepository.save(product)
        }
        catch (ex:Exception){
            throw  ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }


    fun updateHome(product: Product): Product {
        try {
            val response = productRepository.findById(product.id)
                ?: throw Exception("El id no existe")
                response.apply {
                brand = product.brand
            }
            return productRepository.save(response)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }

    }


}