import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Cliente (private var cpf: String, private var nome: String, private var telefone: String,private var email: String): Parcelable{
    override fun toString(): String {
        return ("Nome: $nome")
    }
}
