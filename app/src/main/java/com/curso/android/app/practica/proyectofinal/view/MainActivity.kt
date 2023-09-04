package com.curso.android.app.practica.proyectofinal.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.curso.android.app.practica.proyectofinal.R
import com.curso.android.app.practica.proyectofinal.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val mainViewModel :  MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.comparar.observe(this){
            binding.resultado.text = getString(R.string.comparacion_nula)
            binding.resultado.setTextColor(Color.BLACK)

            if (mainViewModel.sonTextosNoNulos()){

                if (mainViewModel.compararTextos()){
                    binding.resultado.text = getString(R.string.comparacion_igual)
                    binding.resultado.setTextColor(Color.GREEN)
                } else{
                    binding.resultado.text = getString(R.string.comparacion_distinta)
                    binding.resultado.setTextColor(Color.RED)
                }
            }

        }

        binding.botoncomparar.setOnClickListener{
            mainViewModel.actualizarTextos(binding.text1.text.toString(), binding.text2.text.toString())
        }



    }




}