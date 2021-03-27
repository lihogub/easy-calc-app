package ru.lihogub.easycalc

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private var calc = Calc()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun updateInputExpression(view: View) {
        findViewById<TextView>(R.id.expressionInput).text = calc.getInputExpression()
        findViewById<TextView>(R.id.expressionOutput).text = calc.evalExpression(findViewById<TextView>(R.id.expressionOutput).text.toString())
    }

    fun onEqualButtonClick(view: View) {
        try {
            var result = calc.eval("2+3+")
        } catch (e: Exception) {}
    }

    fun onDigitButtonClick(view: View) {
        calc.pushDigit(Integer.parseInt(findViewById<Button>(view.id).text.toString()))
        updateInputExpression(view)
    }

    fun onOperatorButtonClick(view: View) {
        calc.pushOperator(findViewById<Button>(view.id).text.toString())
        updateInputExpression(view)
    }

    fun onDotButtonClick(view: View) {
        calc.pushDot()
        updateInputExpression(view)
    }

    fun onDeleteButtonClick(view: View) {
        calc.deleteSymbol()
        updateInputExpression(view)
    }

}