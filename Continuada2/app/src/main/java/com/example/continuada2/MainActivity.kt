package com.example.continuada2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

        val layoutLista: LinearLayout = findViewById(R.id.layout_lista)

        // usando o objet para pegar a instância do objeto que invoca os EndPoints
        val apiCachorros = ConexaoApiCachorros.criar()

        // .enqueue() abre um processo ASSÍNCRONO (ou seja, paralelo, não "trava" a tela)
        apiCachorros.get().enqueue(object : Callback<List<Cachorro>> {

            // onResponse é executando se chamada for feita com sucesso
            override fun onResponse(call: Call<List<Cachorro>>, response: Response<List<Cachorro>>) {
                // response.body() -> obtém o corpo da resposta
                response.body()?.forEach {
                    // criando uma nova TextView

                    val tvCachorro = TextView(baseContext)
                    tvCachorro.text = "Id: ${it.id} - Raça: ${it.raca} - PreçoMédio: ${it.precoMedio} - IndicadoCriança: ${it.indicadoCrianca}"

                    // adicionando a nova TextView no LinearLayout
                    layoutLista.addView(tvCachorro)
                }
            }

            // onFailure é executado se houver erro na chamada
            override fun onFailure(call: Call<List<Cachorro>>, t: Throwable) {
                Log.e("api", t.message!!)
                // t.printStackTrace()
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }
        })



    }
}