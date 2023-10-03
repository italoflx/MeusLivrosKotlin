package ufrn.br.meuslivros

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import ufrn.br.meuslivros.databinding.ActivityMainBinding

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
        binding  = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.cadastrar.setOnClickListener{
            val i = Intent(this, CadastrarLivro::class.java)
            activityLauncher.launch(i)
        }

        binding.listar.setOnClickListener{
            val i = Intent(this, ListarLivros::class.java)
            activityLauncher.launch(i)
        }
    }
}