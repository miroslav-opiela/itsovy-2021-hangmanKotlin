package sk.itsovy.android.hangmankotlin

import java.io.Serializable

interface Game : Serializable {

    companion object {
        const val DEFAULT_ATTEMPTS_LEFT = 6
        const val UNGUESSED_CHAR = '_'
    }

    // premenne (property) vyjadruju stav
    val isWon: Boolean
    val guessedCharacters : CharSequence
    val challengeWord : String
    val attemptsLeft : Int
    // funkcie popisuju spravanie
    fun guess(character: Char) : Boolean
}