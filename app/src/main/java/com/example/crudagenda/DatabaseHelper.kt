package com.example.crudagenda

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    companion object {
        private const val DATABASE_NAME = "CadastroPessoas.db"
        private const val DATABASE_VERSION = 1

        const val TABLE_NAME = "pessoas"
        const val COL_ID = "id"
        const val COL_NOME = "nome"
        const val COL_TELEFONE = "telefone"
        const val COL_EMAIL = "email"
        const val COL_CIDADE = "cidade"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_NAME (
            $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $COL_NOME TEXT,
            $COL_TELEFONE TEXT,
            $COL_EMAIL TEXT,
            $COL_CIDADE TEXT
            )
        """.trimIndent()
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // -------OPERAÇÕES CRUD-------

    // 1. Create (Inserir)

    fun inserirPessoa(pessoa: Pessoa): Boolean {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COL_NOME, pessoa.nome)
            put(COL_TELEFONE, pessoa.telefone)
            put(COL_EMAIL, pessoa.email)
            put(COL_CIDADE, pessoa.cidade)
        }

        val resultado = db.insert(TABLE_NAME, null, values)
        return resultado != -1L
    }

    // 2. READ (Buscar por Nome)
    fun buscarPessoaPorNome(nome: String): Pessoa? {
        val db = this.readableDatabase
        val cursor = db.query(TABLE_NAME, null, "$COL_NOME=?",
            arrayOf(nome), null, null, null)

        return if (cursor != null && cursor.moveToFirst()) {
            val pessoa = Pessoa(
                id = cursor.getInt(0),
                nome = cursor.getString(1),
                telefone = cursor.getString(2),
                email = cursor.getString(3),
                cidade = cursor.getString(4)
            )
            cursor.close()
            pessoa
        } else {
            cursor?.close()
            null // Retorna nulo se não encontrar nafa de forma segura
        }
    }

    // 3. UPDATE (Atualizar)
    fun atualizarPessoa(pessoa: Pessoa): Boolean {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COL_NOME, pessoa.nome)
            put(COL_TELEFONE, pessoa.telefone)
            put(COL_EMAIL, pessoa.email)
            put(COL_CIDADE, pessoa.cidade)
        }

        val linhasAfetadas = db.update(TABLE_NAME, values, "$COL_ID=?",
            arrayOf(pessoa.id.toString()))
        return linhasAfetadas > 0

    }

    // 4. DELETE (Deletar)

    fun deletarPessoa(id: Int): Boolean {
        val db = this.writableDatabase
        val linhasAfetadas = db.delete(TABLE_NAME, "$COL_ID=?",
            arrayOf(id.toString()))
        return linhasAfetadas > 0
    }

}