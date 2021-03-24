package ru.lihogub.easycalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.util.Stack;

class MainActivity : AppCompatActivity() {
    private var inputStack  = Stack<String>()
    private var digitStack  = Stack<String>()
    private var opStack = Stack<String>()
    private var tmpStack = Stack<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun updateInputExpression(view: View) {
        findViewById<TextView>(R.id.expressionInput).text = inputStack.toArray().joinToString(separator = "")
    }

    fun onEqualButtonClick(view: View) {

    }

    fun onDigitButtonClick(view: View) {
        pushDigit(Integer.parseInt(findViewById<Button>(view.id).text.toString()))
        updateInputExpression(view)
    }

    fun onOperatorButtonClick(view: View) {
        pushOperator(findViewById<Button>(view.id).text.toString())
        updateInputExpression(view)
    }

    fun onDotButtonClick(view: View) {
        pushDot()
        updateInputExpression(view)
    }

    fun onDeleteButtonClick(view: View) {
        try {
            inputStack.pop()
        } catch (e: Exception) {}
        updateInputExpression(view)
    }

    private fun pushDigit(digit : Int) {
        inputStack.push(digit.toString())
    }

    private fun pushDot(){
        var temporaryStack = Stack<String>()
        var isError = false
        var numbersFound = 0
        while (!inputStack.empty()) {
            var s = inputStack.pop()
            temporaryStack.push(s)
            if (s.equals("+") || s.equals("-") || s.equals("/") || s.equals("*")) break
            if (s.equals(".")) {
                isError = true
                break
            }
            numbersFound++
        }
        while (!temporaryStack.empty()) inputStack.push(temporaryStack.pop())
        if (!isError && numbersFound > 0) {
            inputStack.push(".")
        }
    }

    private fun pushOperator(operator : String) {
        if (inputStack.empty()) return
        if (inputStack.peek().equals("+")
            || inputStack.peek().equals("-")
            || inputStack.peek().equals("*")
            || inputStack.peek().equals("/")
        ) {
            inputStack.pop()
            inputStack.push(operator)
            return
        }
        inputStack.push(operator);
    }
}