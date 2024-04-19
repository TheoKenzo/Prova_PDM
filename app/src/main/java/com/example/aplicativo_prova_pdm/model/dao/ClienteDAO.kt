import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ClienteDAO(context: Context) : SQLiteOpenHelper(context, "fabricaPamonhas", null, 1){
    private val nomeTabela = "Cliente"
    override fun onCreate(db: SQLiteDatabase){
        val SQL_criacao =
            """
                CREATE TABLE IF NOT EXISTS $nomeTabela (
                    cpf TEXT PRIMARY KEY,
                    nome TEXT NOT NULL,
                    telefone TEXT NOT NULL,
                    email TEXT NOT NULL
                )
            """.trimIndent()

        db.execSQL(SQL_criacao)
    }
    override fun onUpgrade(db: SQLiteDatabase, versaoAntiga: Int, novaVersao: Int){
        val SQL_exclusao = "DROP TABLE IF EXISTS $nomeTabela"
        db.execSQL(SQL_exclusao)
        onCreate(db)
    }
}