package es.rafapuig.recyclerviewdemo.model

import es.rafapuig.recyclerviewdemo.R

class EquiposProvider {

    companion object {
        val LEVANTE = Equipo("Levante","Pepe","Ciutat de Valencia", R.drawable.real_madrid)
        val REAL_MADRID = Equipo("Real Madrid C.F.", "Ancelotti", "Santiago Bernabeu", R.drawable.real_madrid)
        val BARCELONA = Equipo("F.C. Barcelona","Flick", "Camp Nou", R.drawable.real_madrid)
        val VALENCIA = Equipo("Valencia C.F.", "Baraja", "Mestalla",R.drawable.valencia)

        val EQUIPOS = listOf(LEVANTE, REAL_MADRID, BARCELONA,VALENCIA)
    }

}