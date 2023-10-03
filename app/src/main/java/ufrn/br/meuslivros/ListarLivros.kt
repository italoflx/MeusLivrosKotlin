package ufrn.br.meuslivros

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.room.Room
import ufrn.br.meuslivros.database.AppDatabase
import ufrn.br.meuslivros.databinding.ActivityListarLivrosBinding

class ListarLivros : AppCompatActivity() {

    private lateinit var binding: ActivityListarLivrosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_listar_livros)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "meusLivros.sqlite"
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries().build()
    }
}