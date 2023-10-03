package ufrn.br.meuslivros.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ufrn.br.meuslivros.model.Livro

@Dao
interface LivroDao {
    @Insert
    fun inserir(livro: Livro)

    @Update
    fun atualiza(livro: Livro)

    @Delete
    fun remover(livro: Livro)

    @Query("SELECT * FROM Livro")
    fun listar(): List<Livro>
}