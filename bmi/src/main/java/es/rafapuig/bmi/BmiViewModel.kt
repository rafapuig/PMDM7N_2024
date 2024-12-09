package es.rafapuig.bmi

import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BmiViewModel : ViewModel() {

    var weight: Double = 0.0
    var height: Double = 0.0

    //var bmi: Double = 0.0

    val bmi = MutableLiveData<Double>()


    fun computeBMI() {
        bmi.value = weight / (height / 100 * height / 100)
    }

    fun getResult(): Int {
        if(bmi.value == null) return 0
        if (bmi.value!! < 18.5) return  R.string.underweight
        if (bmi.value!! < 25) return R.string.normal
        if (bmi.value!! < 30) return R.string.overweight
        return R.string.obesity
    }

}