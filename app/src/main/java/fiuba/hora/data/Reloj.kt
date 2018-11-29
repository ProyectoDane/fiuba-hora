package fiuba.hora.data

/**
 * Reloj, representa un reloj analogico con formato de 12 horas ( no 24 horas )
 * Solo posee horas y minutos
 * Puede dar la posicion de sus agujas en angulos respecto a las 3 hs
 * Puede devolver la hora en formato digital EJ 12:45
 * Puede devolver la hora en formato texto EJ Una menos cuarto
 */
class Reloj {

    var hours: Int = 12
        private set  //store hours

    var minutes: Int = 0
        private set //store minutes

    /**
     * Devuelve la posicion de la aguja de las horas en grados
     * El angulo cero corresponde a las 15 hrs
     */
    val hourToAngle: Float
        get() = if (hours == 12) 270f else hours * 30f - 90

    /**
     * Devuelve la posicion de la aguja de los minutos en grados
     * El angulo cero corresponde a los 15 min
     */
    val minToAngle: Float
        get() = if (minutes == 0) 270f else minutes * 6f - 90

    /**
     * Mappeado de horas en numero
     */
    val hoursMap: Map<Int, String> = hashMapOf(
            (0 to "doce"),
            (1 to "una"),
            (2 to "dos"),
            (3 to "tres"),
            (4 to "cuatro"),
            (5 to "cinco"),
            (6 to "seis"),
            (7 to "siete"),
            (8 to "ocho"),
            (9 to "nueve"),
            (10 to "diez"),
            (11 to "once"),
            (12 to "doce")
    )

    /**
     * Mappeado de minutos en numero a como se le dice de manera casual
     * a esa cantidad de minutos.
     */
    val minsMap: Map<Int, String> = hashMapOf(
            (0 to "en punto"),
            (5 to "y cinco"),
            (10 to "y diez"),
            (15 to "y cuarto"),
            (20 to "y veinte"),
            (25 to "y veinticinco"),
            (30 to "y media"),
            (35 to "menos veinticinco"),
            (40 to "menos veinte"),
            (45 to "menos cuarto"),
            (50 to "menos diez"),
            (55 to "menos cinco")
    )

    val copy: Reloj get() {
        val temp = Reloj()
        temp.hours = hours
        temp.minutes = minutes
        return temp
    }

    constructor(hours: Int, minutes: Int) {
        setTime(hours, minutes)
    }

    constructor() {
        setTime(12, 0)
    }

    /**
     * Metodo para setear el tiempo.
     */
    fun setTime(hours: Int, minutes: Int) {
        if (hours in 1..12)
            this.hours = hours
        if (minutes in 0..59)
            this.minutes = minutes
    }

    /**
     * Obtiene la hora que está marcando el reloj actualmente a partir del angulo de su aguja.
     */
    fun setHourFromAngle(angleHour: Float) {
        if (angleHour > 345 || angleHour <= 15) {
            hours = 3
        } else if (angleHour > 15 && angleHour <= 45) {
            hours = 2
        } else if (angleHour > 45 && angleHour <= 75) {
            hours = 1
        } else if (angleHour > 75 && angleHour <= 105) {
            hours = 12
        } else if (angleHour > 105 && angleHour <= 135) {
            hours = 11
        } else if (angleHour > 135 && angleHour <= 165) {
            hours = 10
        } else if (angleHour > 165 && angleHour <= 195) {
            hours = 9
        } else if (angleHour > 195 && angleHour <= 225) {
            hours = 8
        } else if (angleHour > 225 && angleHour <= 255) {
            hours = 7
        } else if (angleHour > 255 && angleHour <= 285) {
            hours = 6
        } else if (angleHour > 285 && angleHour <= 315) {
            hours = 5
        } else if (angleHour > 315 && angleHour <= 345) {
            hours = 4
        }
    }

    /**
     * Obtiene los minutos que está marcando el reloj actualmente a partir del angulo de su aguja.
     */
    fun setMinutesFromAngle(angleMin: Float) {
        if (angleMin > 345 || angleMin <= 15) {
            minutes = 15
        } else if (angleMin > 15 && angleMin <= 45) {
            minutes = 10
        } else if (angleMin > 45 && angleMin <= 75) {
            minutes = 5
        } else if (angleMin > 75 && angleMin <= 105) {
            minutes = 0
        } else if (angleMin > 105 && angleMin <= 135) {
            minutes = 55
        } else if (angleMin > 135 && angleMin <= 165) {
            minutes = 50
        } else if (angleMin > 165 && angleMin <= 195) {
            minutes = 45
        } else if (angleMin > 195 && angleMin <= 225) {
            minutes = 40
        } else if (angleMin > 225 && angleMin <= 255) {
            minutes = 35
        } else if (angleMin > 255 && angleMin <= 285) {
            minutes = 30
        } else if (angleMin > 285 && angleMin <= 315) {
            minutes = 25
        } else if (angleMin > 315 && angleMin <= 345) {
            minutes = 20
        }
    }

    /**
     * Metodo para imprimir el tiempo en formato hh:mm:ss
     */
    fun printTime() {
        if (hours < 10)
            print("0")
        print(hours.toString() + ":")
        if (minutes < 10)
            print("0")
        print(minutes.toString() + ":")
    }

    /**
     * Compara dos relojes, si tienen la misma hora y minutos son iguales
     */
    fun equals(otroReloj: Reloj): Boolean {
        return hours == otroReloj.hours && minutes == otroReloj.minutes
    }

    /**
     * Crea una copia de un reloj
     */
    fun makeCopy(otroReloj: Reloj) {
        hours = otroReloj.hours
        minutes = otroReloj.minutes
    }

    /**
     * Devuelve la hora en formato digital
     */
    override fun toString(): String {
        var str = ""

        if (hours < 10)
            str = "0"
        str = "$str$hours:"
        if (minutes < 10)
            str += "0"
        str = "$str$minutes"

        return str
    }

    /**
     * Devuelve la hora en formato y cuarto y media etc
     */
    fun toStringBeutify(): String {
        val minutesStr = minsMap[minutes]
        val hoursStr = hoursMap[getCorrectedHours()]
        return ("$hoursStr $minutesStr").toUpperCase()
    }

    private fun getCorrectedHours(): Int {
        val extra: Int = if (minsMap[minutes]!!.contains("menos")) 1 else 0
        return (hours + extra).rem(12)
    }
}