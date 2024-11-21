package es.rafapuig.futboldemo

import es.rafapuig.futboldemo.model.Equipo
import es.rafapuig.futboldemo.model.EquiposProvider
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testEquipos() {
        EquiposProvider.EQUIPOS
            .filter { it.nombre.startsWith("A") }
            .sortedWith(Comparator.comparing<Equipo?, String?> { it.nombre }.reversed())
            .map { it.nombre }
            .forEach { println(it) }
    }
}