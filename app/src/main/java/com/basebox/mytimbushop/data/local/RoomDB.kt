package com.basebox.mytimbushop.data.local

import android.content.Context
import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.basebox.mytimbushop.data.dao.CartItemDao
import com.basebox.mytimbushop.data.dao.OrderDao
import com.basebox.mytimbushop.models.CartItem
import com.basebox.mytimbushop.models.DateConverter
import com.basebox.mytimbushop.models.Orders
import com.basebox.mytimbushop.util.Converter

@Database(entities = [CartItem::class, Orders::class], version = 3, exportSchema = false)
@TypeConverters(Converter::class, DateConverter::class)
abstract class RoomDB : RoomDatabase() {
    abstract fun cartItemDao(): CartItemDao
    abstract fun orderDao(): OrderDao

    companion object {
        @Volatile
        private var INSTANCE: RoomDB? = null

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE order_history (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, orderId TEXT NOT NULL, productName TEXT NOT NULL, quantity INTEGER NOT NULL, orderDate INTEGER NOT NULL, status TEXT NOT NULL, totalPrice DOUBLE NOT NULL)")
            }
        }
        fun getDatabase(context: Context): RoomDB {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDB::class.java,
                    "product_database"
                ).addMigrations(MIGRATION_1_2).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}