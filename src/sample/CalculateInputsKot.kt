package sample

import sample.CalculateInputsKot
import java.lang.NumberFormatException
import java.util.*

class CalculateInputsKot(input: String?) {
    private var result = 0
    private fun calculate(times: Int) {
        for (i in 0 until times) {
            var firstArgs: Int
            var secondArgs: Int
            if (operands.contains('/')) {
                firstArgs = arguments[operands.indexOf('/')]
                secondArgs = arguments[operands.indexOf('/') + 1]
                result = firstArgs / secondArgs
                arguments[operands.indexOf('/')] = result
                arguments.removeAt(operands.indexOf('/') + 1)
                operands.removeAt(operands.indexOf('/'))
            } else if (operands.contains('*')) {
                firstArgs = arguments[operands.indexOf('*')]
                secondArgs = arguments[operands.indexOf('*') + 1]
                result = firstArgs * secondArgs
                arguments[operands.indexOf('*')] = result
                arguments.removeAt(operands.indexOf('*') + 1)
                operands.removeAt(operands.indexOf('*'))
            } else if (operands.firstElement() == '+') {
                firstArgs = arguments[operands.indexOf('+')]
                secondArgs = arguments[operands.indexOf('+') + 1]
                result = firstArgs + secondArgs
                arguments[operands.indexOf('+')] = result
                arguments.removeAt(operands.indexOf('+') + 1)
                operands.removeAt(operands.indexOf('+'))
            } else if (operands.firstElement() == '-') {
                firstArgs = arguments[operands.indexOf('-')]
                secondArgs = arguments[operands.indexOf('-') + 1]
                result = firstArgs - secondArgs
                arguments[operands.indexOf('-')] = result
                arguments.removeAt(operands.indexOf('-') + 1)
                operands.removeAt(operands.indexOf('-'))
            }
        }
    }

    private fun getArguments(operators: MutableList<String>, rand: Random, operator: String) {
        var operator = operator
        if (input!!.contains(operator)) {
        } else {
            operators.remove(operator)
            operator = operators[rand.nextInt(operators.size)]
            if (input!!.contains(operator)) {
            } else {
                operators.remove(operator)
                operator = operators[rand.nextInt(operators.size)]
                if (input!!.contains(operator)) {
                } else {
                    operators.remove(operator)
                    operator = operators[rand.nextInt(operators.size)]
                }
            }
        }
        getArguments(input, operator)
    }

    private fun getArguments(input: String?, operand: String) {
        var input = input
        val initialExpression = input!!.substring(0, input.indexOf(operand))
        try {
            arguments.add(initialExpression.toInt())
        } catch (e: NumberFormatException) {
            if (initialExpression.contains("-")) {
                getArguments(initialExpression, "-")
            } else if (initialExpression.contains("+")) {
                getArguments(initialExpression, "+")
            } else if (initialExpression.contains("*")) {
                getArguments(initialExpression, "*")
            } else if (initialExpression.contains("/")) {
                getArguments(initialExpression, "/")
            }
        }
        input = input.substring(input.indexOf(operand) + 1)
        operands.push(operand[0])
        try {
            input.toInt()
            arguments.add(input.toInt())
        } catch (e: NumberFormatException) {
            if (input.contains("-")) {
                getArguments(input, "-")
            } else if (input.contains("+")) {
                getArguments(input, "+")
            } else if (input.contains("*")) {
                getArguments(input, "*")
            } else if (input.contains("/")) {
                getArguments(input, "/")
            }
        }
    }

    override fun toString(): String {
        return Integer.toString(result)
    }

    companion object {
        private var input: String? = null
        private val arguments: MutableList<Int> = ArrayList()
        private val operands = Stack<Char>()
    }

    init {
        Companion.input = input
        val operators: MutableList<String> = ArrayList(Arrays.asList("+", "-", "*", "/"))
        val rand = Random()
        val operator = operators[rand.nextInt(operators.size)]
        getArguments(operators, rand, operator)
        val times = operands.size
        calculate(times)
    }
}