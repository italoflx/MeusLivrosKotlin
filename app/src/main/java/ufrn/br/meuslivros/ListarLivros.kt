package ufrn.br.meuslivros

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import ufrn.br.meuslivros.database.AppDatabase
import ufrn.br.meuslivros.databinding.ActivityListarLivrosBinding
import ufrn.br.meuslivros.model.Livro

class ListarLivros : AppCompatActivity() {

    private lateinit var binding: ActivityListarLivrosBinding
    private lateinit var viewmodel:ListarLivrosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_listar_livros)
        viewmodel = ViewModelProvider(this)[(ListarLivrosViewModel::class.java)]

        viewmodel._nome.observe(this, Observer {
            binding.textView5.text = it.toString()
        })

        viewmodel._autor.observe(this, Observer {
            binding.textView6.text = it.toString()
        })

        viewmodel._ano.observe(this, Observer {
            binding.textView9.text = it.toString()
        })

        viewmodel._nota.observe(this, Observer {
            binding.textView10.text = it.toString()
        })

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "meusLivros.sqlite"
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries().build()

        var listaLivros:List<Livro> = db.livroDao().listar()
        var indiceLivroAtual = 0
        var livroAtual:Livro

        viewmodel._nome.value = listaLivros[0].nome
        viewmodel._autor.value = listaLivros[0].autor
        viewmodel._nota.value = listaLivros[0].ano
        viewmodel._ano.value = listaLivros[0].nota

        binding.buttonProximo.setOnClickListener{
            if(indiceLivroAtual+1 < listaLivros.size) {
                indiceLivroAtual += 1
                livroAtual = listaLivros[indiceLivroAtual]
                viewmodel._nome.value = livroAtual.nome
                viewmodel._autor.value = livroAtual.autor
                viewmodel._nota.value = livroAtual.nota
                viewmodel._ano.value = livroAtual.ano
            }else{
                Toast.makeText(this, "Não existe proximo", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonAnterior.setOnClickListener{
            if(indiceLivroAtual-1 >= 0) {
                indiceLivroAtual -= 1
                livroAtual = listaLivros[indiceLivroAtual]
                viewmodel._nome.value = livroAtual.nome
                viewmodel._autor.value = livroAtual.autor
                viewmodel._nota.value = livroAtual.nota
                viewmodel._ano.value = livroAtual.ano
            }else{
                Toast.makeText(this, "Não existe anterior", Toast.LENGTH_SHORT).show()
            }
        }

    }
}