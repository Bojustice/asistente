package com.example.prueba.model
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="invoice")
class Invoice {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    @Column (name="asistente_id")
    var asistenteId: Long? = null
    var code: String? = null
    @Column (name ="create_at")
    var createAt: String? = null
    var total: Double? = null
}