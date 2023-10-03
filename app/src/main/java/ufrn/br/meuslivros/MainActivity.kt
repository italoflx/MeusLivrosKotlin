package ufrn.br.meuslivros

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.room.Room
import ufrn.br.meuslivros.database.AppDatabase
import ufrn.br.meuslivros.databinding.ActivityMainBinding
import ufrn.br.meuslivros.model.Livro

class MainActivity : AppCompatActivity() {

    private val activityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){
            result ->
        if (result.resultCode == RESULT_OK){
            if (result.data != null) {
                Toast.makeText(baseContext, result.data!!.getStringExtra("msg"), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "meusLivros.sqlite"
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries().build()

        binding  = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.cadastrar.setOnClickListener{
            val i = Intent(this, CadastrarLivro::class.java)
            activityLauncher.launch(i)
        }

        binding.listar.setOnClickListener{
            if(db.livroDao().listar().isNotEmpty()){
                val i = Intent(this, ListarLivros::class.java)
                activityLauncher.launch(i)
            }else{
                Toast.makeText(this, "Não existem livros cadastrados!!", Toast.LENGTH_SHORT).show()
        }
        }
    }
}