package dev.jvitor.motivacao.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import dev.jvitor.motivacao.databinding.ActivityUserBinding
import dev.jvitor.motivacao.viewmodel.UserViewModel

class UserActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityUserBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[UserViewModel::class.java]

        //Eventos de click
        binding.btnSave.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        if (v.id == binding.btnSave.id) {
            verifyName()
        }
    }

    // Função responsável por validar o campo de nome de usuario
   private fun verifyName(){
        val name = binding.editYourName.text.toString()
            if (viewModel.verifyName(name)) {
                viewModel.saveValueName(name)
                changeForMainActivity()
            }else {
                Toast.makeText(this, "Insira seu nome.", Toast.LENGTH_SHORT).show()
            }
        }

    // Função responsável por trocar de página e finalizar a anterior.
    private fun changeForMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}