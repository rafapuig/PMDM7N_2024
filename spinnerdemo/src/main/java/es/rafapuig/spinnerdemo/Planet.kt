package es.rafapuig.spinnerdemo

data class Planet(val name:String, val mass:Double) {

    override fun toString(): String {
        return name
    }
}
