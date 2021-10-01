package sk.itsovy.android.hangmankotlin

import android.os.SystemClock
import java.lang.StringBuilder
import kotlin.random.Random

class HangmanGame(words: Array<String>, random: Random) : Game {

    val word : String
    val uncoveredWord : StringBuilder
    val startTime : Long
    // typ Int pisat nemusime lebo sa priradzuje hodnota
    var attemptsLeft = Game.DEFAULT_ATTEMPTS_LEFT

    init {

        val index = random.nextInt(words.size)
        word = words[index]

        // vyraba sa novy objekt, slovo new sa nepise
        uncoveredWord = StringBuilder()
        for (i in word.indices) {
            uncoveredWord.append(Game.UNGUESSED_CHAR)
        }
        startTime = SystemClock.elapsedRealtime()
    }

    // porovnanie robi to co v jave equals
    override fun isWon() = word == uncoveredWord.toString()

    // single expression funkcia
    override fun guessedCharacters() = uncoveredWord

    override fun challengeWord() = word

    override fun attemptsLeft() = attemptsLeft

    override fun guess(character: Char): Boolean {
        var success = false
        for (i in word.indices) {
            if (word[i] == character) {
                uncoveredWord[i] = character
                success = true
            }
        }
        if (!success) {
            attemptsLeft--
        }
        return success

    }
}