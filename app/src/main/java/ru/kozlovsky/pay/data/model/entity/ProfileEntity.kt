package ru.kozlovsky.pay.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile")
data class ProfileEntity(
    @PrimaryKey
    val profileId: Long
)
