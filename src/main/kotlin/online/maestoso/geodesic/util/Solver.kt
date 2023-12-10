package online.maestoso.geodesic.util

import java.lang.NumberFormatException
import java.util.*
import kotlin.math.*

object Solver {
    enum class Result {
        TRUE, FALSE, ERR_EMPTY_STACK, ERR_NUM_FORMAT
    }
    fun solve(x: Int, y: Int, z: Int, i: Int, j: Int, k: Int, predicate: String): Result {
        val numstack: Stack<Double> = Stack()
        val boolstack: Stack<Boolean> = Stack()
        predicate.split(' ').forEach { n ->
            try {
                when(n) {
                    // External variables
                    "x" -> numstack.push(x.toDouble())
                    "-x" -> numstack.push(-x.toDouble())
                    "y" -> numstack.push(y.toDouble())
                    "-y" -> numstack.push(-y.toDouble())
                    "z" -> numstack.push(z.toDouble())
                    "-z" -> numstack.push(-z.toDouble())
                    "i" -> numstack.push(i.toDouble())
                    "-i" -> numstack.push(-i.toDouble())
                    "j" -> numstack.push(j.toDouble())
                    "-j" -> numstack.push(-j.toDouble())
                    "k" -> numstack.push(k.toDouble())
                    "-k" -> numstack.push(-k.toDouble())
                    // Basic arithmetic
                    "+" -> numstack.push(numstack.pop() + numstack.pop())
                    "-" -> numstack.push(numstack.pop() - numstack.pop())
                    "*" -> numstack.push(numstack.pop() * numstack.pop())
                    "/" -> numstack.push(numstack.pop() / numstack.pop())
                    "%" -> numstack.push(numstack.pop() % numstack.pop())
                    "^" -> numstack.push(Math.pow(numstack.pop(), numstack.pop()))
                    // Advanced arithmetic/trigonometric
                    "abs" -> numstack.push(abs(numstack.pop()))
                    "acos" -> numstack.push(acos(numstack.pop()))
                    "asin" -> numstack.push(asin(numstack.pop()))
                    "atan2" -> numstack.push(atan2(numstack.pop(), numstack.pop()))
                    "atan" -> numstack.push(atan(numstack.pop()))
                    "cbrt" -> numstack.push(cbrt(numstack.pop()))
                    "ceil" -> numstack.push(ceil(numstack.pop()))
                    "cos" -> numstack.push(cos(numstack.pop()))
                    "cosh" -> numstack.push(cosh(numstack.pop()))
                    "exp" -> numstack.push(exp(numstack.pop()))
                    "floor" -> numstack.push(floor(numstack.pop()))
                    "ln" -> numstack.push(ln(numstack.pop()))
                    "log" -> numstack.push(log(numstack.pop(), numstack.pop()))
                    "max" -> numstack.push(max(numstack.pop(), numstack.pop()))
                    "min" -> numstack.push(min(numstack.pop(), numstack.pop()))
                    "round" -> numstack.push(round(numstack.pop()))
                    "sin" -> numstack.push(sin(numstack.pop()))
                    "sinh" -> numstack.push(sinh(numstack.pop()))
                    "sqrt" -> numstack.push(sqrt(numstack.pop()))
                    "tan" -> numstack.push(tan(numstack.pop()))
                    "tanh" -> numstack.push(tanh(numstack.pop()))
                    // Comparison operators
                    "==" -> boolstack.push(numstack.pop() == numstack.pop())
                    "!=" -> boolstack.push(numstack.pop() != numstack.pop())
                    "~=" -> boolstack.push(abs(numstack.pop() - numstack.pop()) < 1.0)
                    ">" -> boolstack.push(numstack.pop() > numstack.pop())
                    "<" -> boolstack.push(numstack.pop() < numstack.pop())
                    ">=" -> boolstack.push(numstack.pop() >= numstack.pop())
                    "<=" -> boolstack.push(numstack.pop() <= numstack.pop())
                    "!" -> boolstack.push(!boolstack.pop())
                    "||" -> boolstack.push(boolstack.pop() || boolstack.pop())
                    "&&" -> boolstack.push(boolstack.pop() && boolstack.pop())
                    "xor" -> boolstack.push(boolstack.pop() xor boolstack.pop())
                    // Constants
                    "e" -> numstack.push(Math.E)
                    "pi" -> numstack.push(Math.PI)
                    "tau" -> numstack.push(Math.PI * 2.0)
                    "true" -> boolstack.push(true)
                    "false" -> boolstack.push(false)
                    "rb" -> boolstack.push(Random().nextBoolean())
                    "rnd" -> numstack.push(Math.random())
                    "rand" -> numstack.push(Random().nextDouble(numstack.pop(), numstack.pop()))
                    else -> numstack.push(n.toDouble())
                }
            } catch(e: EmptyStackException) {
                return Result.ERR_EMPTY_STACK
            } catch(e: NumberFormatException) {
                return Result.ERR_NUM_FORMAT
            }
        }
        return if(boolstack.pop() == true) {Result.TRUE} else {Result.FALSE}
    }
}
