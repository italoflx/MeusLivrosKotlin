package ufrn.br.meuslivros.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Livro(
    //nome, autor, ano, nota
    var nome: String,
    var autor: String,
    var ano: Int,
    var nota: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0
}