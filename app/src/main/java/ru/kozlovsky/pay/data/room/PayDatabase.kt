package ru.kozlovsky.pay.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.kozlovsky.pay.data.model.entity.CustomerEntity

@Database(
    entities = [
        CustomerEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class PayDatabase : RoomDatabase() {
    abstract val profileDao: ProfileDao
    abstract val recipientsDao: RecipientsDao
}