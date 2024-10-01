fun interface OperacionBinaria {
    fun operar(x: Int, y: Int): Int
}

class Sumador : OperacionBinaria {
    override fun operar(x: Int, y: Int) = x + y
}

class Multiplicador : OperacionBinaria {
    override fun operar(x: Int, y: Int) = x * y
}

fun interface OperacionUnaria {
    fun operar(x: Int): Int
}


val sumar = { x: Int, y: Int -> x + y }
val multiplicar = { x: Int, y: Int -> x * y }

fun main() {
    val nombre = "Rafa Puig"
    println("Hola ${nombre.uppercase()}")

    val elevarCuadrado = { x: Int -> x * x }

    var resultado = elevarCuadrado(5)
    println(resultado)

    val mitad = { x: Int -> x / 2 }
    println(mitad(10))

    var operacion = elevarCuadrado
    resultado = operacion(8)
    println(resultado)

    operacion = mitad
    resultado = operacion(8)
    println(resultado)


    operarCon10(mitad)
    operarCon10(elevarCuadrado)
    operarCon10(operacion)

    operacion = getOperacion()
    println(operacion(50))


    operarCon10 { x -> x * 2 }
    operarCon10 { it * 2 }

    println(sumar(4, 8))
    println(multiplicar(2, 5))

    var operacionBinaria = sumar
    operacionBinaria = multiplicar
    //operacionBinaria = elevarCuadrado

    realizarOperacionBinaria(6, 8, multiplicar)
    realizarOperacionBinaria(3, 7, sumar)

    realizarOperacionBinaria(60, 6) { x, y -> x / y }

    realizarOperacionSobre40y4 { x, y -> x - y }

    var opBin: OperacionBinaria = Sumador()

    resultado = opBin.operar(5, 6)
    println(resultado)

    opBin = Multiplicador()
    resultado = opBin.operar(5, 6)
    println(resultado)

    opBin = object : OperacionBinaria {
        override fun operar(x: Int, y: Int) = x / y
    }
    resultado = opBin.operar(54, 6)
    println(resultado)

    opBin = OperacionBinaria { x, y -> x - y }
    resultado = opBin.operar(10, 6)
    println(resultado)

    opBin = OperacionBinaria(sumar)

    realizarOperacion(4, 5) { x, y -> (x + y) / 2 }

    var opUnaria = OperacionUnaria { x -> x * x }

    realizarOperacionUnariaSobre10(opUnaria)
    realizarOperacionUnariaSobre10 { x -> x / 2 }
    realizarOperacionUnariaSobre10 { x -> imprimir(x) }
    realizarOperacionUnariaSobre10 { it / 2 }
    realizarOperacionUnariaSobre10 { imprimir(it) }
}

fun imprimir(x: Int): Int {
    println("El valor de x es $x")
    return 10
}

fun operarCon10(operacion: (Int) -> Int) {
    val res = operacion(10)
    println(res)
}

fun getOperacion(): (Int) -> Int {
    return { x -> x + 1 }
}

fun realizarOperacionBinaria(op1: Int, op2: Int, operacion: (Int, Int) -> Int) {
    println(op1)
    println(op2)
    val res = operacion(op1, op2)
    println(res)
}

fun realizarOperacionSobre40y4(operacion: (Int, Int) -> Int) {
    realizarOperacionBinaria(40, 4, operacion)
}

fun realizarOperacion(x: Int, y: Int, operacion: OperacionBinaria) {
    var res = operacion.operar(x, y)
    println(res)
}

fun realizarOperacionUnariaSobre10(operacion: OperacionUnaria) {
    println(operacion.operar(10))
}