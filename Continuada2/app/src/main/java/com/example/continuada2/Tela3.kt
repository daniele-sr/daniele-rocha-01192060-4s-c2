package com.example.continuada2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Tela3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela3)

        val apiCachorros = ConexaoApiCachorros.criar()

        val tv: EditText = findViewById(R.id.tv_input1)
        val tv2: EditText = findViewById(R.id.tv_input2)

        val idOne = tv.text.toString().toInt()
        val idTwo = tv2.text.toString().toInt()

        apiCachorros.get(idOne).enqueue(object : Callback<Cachorro> {
            override fun onResponse(call: Call<Cachorro>, response: Response<Cachorro>) {

                val tvResultado: TextView = findViewById(R.id.tv_resultado)
                val tvResultado2: TextView = findViewById(R.id.tv_resultado2)
                val tvResultado3: TextView = findViewById(R.id.tv_resultado3)
                

                val cachorro = response.body()

                if (cachorro != null ) {
                    tvResultado.text = "Raça: ${idOne}"
                    tvResultado2.text = "Raça: ${idTwo}"
                    tvResultado3.text =

                } else {
                    val intent = Intent(applicationContext, Tela2::class.java)
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<Cachorro>, t: Throwable) {
                Toast.makeText(baseContext, "Erro: ${t.message!!}", Toast.LENGTH_SHORT).show()
            }
        })


    }
}