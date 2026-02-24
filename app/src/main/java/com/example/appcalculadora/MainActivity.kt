package com.example.appcalculadora

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvVisor: TextView
    private var numeroAtual = ""
    private var operador = ""
    private var primeiroNumero = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvVisor = findViewById(R.id.tvVisor)

        val botoesNumeros = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2,
            R.id.btn3, R.id.btn4, R.id.btn5,
            R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        )

        for (id in botoesNumeros) {
            findViewById<Button>(id).setOnClickListener {
                val botao = it as Button
                numeroAtual += botao.text
                tvVisor.text = numeroAtual
            }
        }

        findViewById<Button>(R.id.btnSoma).setOnClickListener { definirOperador("+") }
        findViewById<Button>(R.id.btnSub).setOnClickListener { definirOperador("-") }
        findViewById<Button>(R.id.btnMult).setOnClickListener { definirOperador("*") }
        findViewById<Button>(R.id.btnDiv).setOnClickListener { definirOperador("/") }

        findViewById<Button>(R.id.btnIgual).setOnClickListener {
            calcularResultado()
        }

        findViewById<Button>(R.id.btnClear).setOnClickListener {
            numeroAtual = ""
            operador = ""
            primeiroNumero = 0.0
            tvVisor.text = "0"
        }
    }

    private fun definirOperador(op: String) {
        primeiroNumero = numeroAtual.toDouble()
        operador = op
        numeroAtual = ""
    }

    private fun calcularResultado() {
        val segundoNumero = numeroAtual.toDouble()
        val resultado = when (operador) {
            "+" -> primeiroNumero + segundoNumero
            "-" -> primeiroNumero - segundoNumero
            "*" -> primeiroNumero * segundoNumero
            "/" -> primeiroNumero / segundoNumero
            else -> 0.0
        }

        tvVisor.text = resultado.toString()
        numeroAtual = resultado.toString()
    }
}