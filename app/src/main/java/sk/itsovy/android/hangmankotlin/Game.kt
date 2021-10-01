package sk.itsovy.android.hangmankotlin

import java.io.Serializable

interface Game : Serializable {

    companion object {
        const val DEFAULT_ATTEMPTS_LEFT = 6
        const val UNGUESSED_CHAR = '_'
    }

    fun isWon(): Boolean
    fun guessedCharacters() : CharSequence
    fun challengeWord() : String
    fun attemptsLeft() : Int
    fun guess(character: Char) : Boolean
}