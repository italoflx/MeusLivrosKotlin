package ufrn.br.meuslivros

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListarLivrosViewModel : ViewModel() {
    var _nome = MutableLiveData<String>("")
    var _autor = MutableLiveData<String>("")
    var _ano = MutableLiveData<Int>(0)
    var _nota = MutableLiveData<Int>(0)
}