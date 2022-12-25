package com.example.prueba.model
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank


@Entity
@Table(name="asistente")
class Asistente {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? =null
    @NotBlank(message="Obligatorio")
    var nombres: String? = null
    @Email(message="Formato incorrecto")
    @NotBlank(message="Obligatorio")
    var email: String? = null
    var institucion: String? = null
    var cargo: String? = null


}

