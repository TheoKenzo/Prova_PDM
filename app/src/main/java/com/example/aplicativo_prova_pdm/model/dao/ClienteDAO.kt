import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ClienteDAO(context: Context) : SQLiteOpenHelper(context, "MeubancoIFTM", null, 1){
    private val nomeTabela = "Cliente"
    override fun onCreate(db: SQLiteDatabase){
        val col1 = "cpf"
        val col2 = "nome"
        val col3 = "telefone"
        val col4 = "email"

        val SQL_criacao =
            "CREATE TABLE IF NOT EXISTS $nomeTabela (" +
                    "${col1} TEXT PRIMARY KEY," +
                    "${col2} TEXT NOT NULL," +
                    "${col3}TEXT NOT NULL," +
                    "${col4} TEXT NOT NULL)"

        db.execSQL(SQL_criacao)
    }
    override fun onUpgrade(db: SQLiteDatabase, versaoAntiga: Int, novaVersao: Int){
        val SQL_exclusao = "DROP TABLE IF EXISTS $nomeTabela"
        db.execSQL(SQL_exclusao)
        onCreate(db)
    }
}