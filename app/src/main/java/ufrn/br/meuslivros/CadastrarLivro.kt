package ufrn.br.meuslivros

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.room.Room
import ufrn.br.meuslivros.database.AppDatabase
import ufrn.br.meuslivros.databinding.ActivityCadastrarLivroBinding
import ufrn.br.meuslivros.model.Livro

class CadastrarLivro : AppCompatActivity() {

    private lateinit var binding:ActivityCadastrarLivroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cadastrar_livro)
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "meusLivros.sqlite"
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries().build()

        binding.buttonSalvar.setOnClickListener{
            val stringAno = binding.editTextNota.text.toString()
            var livro:Livro = Livro(binding.editTextTitulo.text.toString() , binding.editTextAutor.text.toString(), stringAno.toInt(),
                binding.ratingBar.numStars)
            db.livroDao().inserir(livro)
            val livros: List<Livro> = db.livroDao().listar()

            livros.forEach {livro ->
                println("${livro.ano} ${livro.autor} ${livro.nome} ${livro.nota}")
            }
        }

        binding.buttonCancelar.setOnClickListener{
            onBackPressed()
        }
    }
}