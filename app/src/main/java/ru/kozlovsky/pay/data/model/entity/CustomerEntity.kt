package ru.kozlovsky.pay.data.model.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.kozlovsky.pay.data.model.dto.CustomerStatus
import ru.kozlovsky.pay.data.model.dto.ImageDto

@Entity(tableName = "customers")
data class CustomerEntity(
    @PrimaryKey(autoGenerate = false)
    val customerId: Long? = null,
    val phone: String,
    val name: String?,

    @Embedded
    val src: ImageDto? = null,
    val status: CustomerStatus,
)
