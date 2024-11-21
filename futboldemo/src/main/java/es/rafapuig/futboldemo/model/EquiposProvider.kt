package es.rafapuig.futboldemo.model

import es.rafapuig.futboldemo.R

class EquiposProvider {

    companion object {

        val REAL_MADRID = Equipo("Real Madrid C.F","Ancelotti", "Bernabeu", R.drawable.real_madrid)
        val BARCELONA = Equipo("F.C. Barcelona","Flick","Camp Nou", R.drawable.real_madrid)
        val ATL_MADRID = Equipo("Atlético de Madrid","Simeone","Metropolitano", R.drawable.real_madrid)
        val VALENCIA = Equipo("Valencia C.F.", "Baraja", "Mestalla", R.drawable.valencia)

        val EQUIPOS = listOf(REAL_MADRID, BARCELONA, ATL_MADRID, VALENCIA)
    }
}