package ufrn.br.meuslivros.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ufrn.br.meuslivros.model.Livro
import ufrn.br.meuslivros.repository.LivroDao

@Database(entities = [Livro::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun livroDao(): LivroDao
}