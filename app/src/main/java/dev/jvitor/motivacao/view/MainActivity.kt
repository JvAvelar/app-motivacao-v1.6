package dev.jvitor.motivacao.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import dev.jvitor.motivacao.R
import dev.jvitor.motivacao.databinding.ActivityMainBinding
import dev.jvitor.motivacao.infra.Constants
import dev.jvitor.motivacao.viewmodel.MainViewModel


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private var categoryId = Constants.Names.ALL_INCLUSIVE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Atribuindo valor para viewModel
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        // Eventos de click
        binding.imageAllIncluse.setOnClickListener(this)
        binding.imageHappy.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)
        binding.btnNewPhrase.setOnClickListener(this)
        binding.textviewHelloName.setOnClickListener(this)

        changeUserName()

        // Tentativa de esconder a cor de cima da tela
        supportActionBar?.hide()

        // Cor branco por padrão no primeiro icone mesmo sem clicar
        colorsDefault()

        // Nova frase gerada sempre que abrir o app
        changePhrase(categoryId)
    }

    override fun onResume() {
        super.onResume()
        changeUserName()
    }

    override fun onClick(v: View) {
        when (v.id) {
            in listOf(R.id.image_all_incluse, R.id.image_happy, R.id.image_sunny) -> {
                setColors(v.id)
                changePhrase(categoryId)
            }

            R.id.btn_new_phrase -> {
                changePhrase(categoryId)
            }

            R.id.textview_hello_name -> {
                changeForUserActivity()
            }
        }
    }
    // Função responsável por observar a troca de nome e atribuir o novo nome.
    private fun changeUserName() {
        viewModel.setName().observe(this) {
            if (it.isNotEmpty()){
                binding.textviewHelloName.text = viewModel.message(it)
            }
            else {
                changeForUserActivity()
            }
        }
    }

    // Função responsável por trocar a tela principal para a tela de inserir o nome do usuario
    private fun changeForUserActivity() {
        startActivity(Intent(this, UserActivity::class.java))
        finish()
    }

    // Função responsável por alterar as frases
    private fun changePhrase(id: Int) {
        binding.textviewPhrase.text = viewModel.getPhrases(id)
    }

    // Função responsável por alterar o primeiro icone para branco por padrão ao abrir
    private fun colorsDefault() {
        binding.imageAllIncluse.setColorFilter(getColor(R.color.white))
        binding.imageHappy.setColorFilter(getColor(R.color.black))
        binding.imageSunny.setColorFilter(getColor(R.color.black))
    }

    // Função responsável por alterar as cores dos icones ao ser clicado
    private fun setColors(id: Int) {
        binding.imageAllIncluse.setColorFilter(getColor(R.color.black))
        binding.imageHappy.setColorFilter(getColor(R.color.black))
        binding.imageSunny.setColorFilter(getColor(R.color.black))
        when (id) {
            R.id.image_all_incluse -> {
                binding.imageAllIncluse.setColorFilter(getColor(R.color.white))
                categoryId
            }

            R.id.image_happy -> {
                binding.imageHappy.setColorFilter(getColor(R.color.white))
                categoryId = Constants.Names.HAPPY
            }

            R.id.image_sunny -> {
                binding.imageSunny.setColorFilter(getColor(R.color.white))
                categoryId = Constants.Names.SUNNY
            }
        }
    }
}