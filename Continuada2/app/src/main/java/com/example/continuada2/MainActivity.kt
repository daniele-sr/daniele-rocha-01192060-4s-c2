package com.example.continuada2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       atualizarLista()
    }

    fun atualizarLista() {

        val layoutLista: LinearLayout = findViewById(R.id.layout_lista)
        layoutLista.removeAllViews()

        val apiCachorros = ConexaoApiCachorros.criar()

        apiCachorros.get().enqueue(object : Callback<List<Cachorro>> {

            override fun onResponse(call: Call<List<Cachorro>>, response: Response<List<Cachorro>>) {

                response.body()?.forEach {

                    val tvCachorro = TextView(baseContext)
                    tvCachorro.text = "Id: ${it.id} - Raça: ${it.raca} - PreçoMédio: ${it.precoMedio} - IndicadoCriança: ${it.indicadoCrianca}"

                    layoutLista.addView(tvCachorro)
                }
            }

            override fun onFailure(call: Call<List<Cachorro>>, t: Throwable) {
                Log.e("api", t.message!!)
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun comprar(view: View) {

        val apiCachorros = ConexaoApiCachorros.criar()

        val tv:EditText = findViewById(R.id.tv_input1)
        val tv2:EditText = findViewById(R.id.tv_input2)

        val idOne = tv.text.toString().toInt()
        val idTwo = tv2.text.toString().toInt()
        
        apiCachorros.get(idOne).enqueue(object : Callback<Cachorro> {
            override fun onResponse(call: Call<Cachorro>, response: Response<Cachorro>) {
                val cachorro = response.body()
                if (cachorro != null ) {
                    val intent = Intent(applicationContext, Tela3::class.java)
                    startActivity(intent)
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