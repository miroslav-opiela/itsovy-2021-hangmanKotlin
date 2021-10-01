package sk.itsovy.android.hangmankotlin

import android.os.SystemClock
import java.lang.StringBuilder
import kotlin.random.Random

class HangmanGame(words: Array<String>, random: Random) : Game {

    override val challengeWord : String

    override val guessedCharacters: CharSequence
        get() = uncoveredWord
    private val uncoveredWord : StringBuilder

    private val startTime : Long
    val time : Long
        get() = SystemClock.elapsedRealtime() - startTime

    override val isWon: Boolean
        get() = uncoveredWord.toString() == challengeWord

    // private set nastavi setter ako viditelny iba v tejto triede
    override var attemptsLeft = Game.DEFAULT_ATTEMPTS_LEFT
        private set

    override fun guess(character: Char): Boolean {
        var success = false
        for (i in challengeWord.indices) {
            if (challengeWord[i] == character) {
                uncoveredWord[i] = character
                success = true
            }
        }
        if (!success) {
            attemptsLeft--
        }
        return success

    }

    init {

        val index = random.nextInt(words.size)
        challengeWord = words[index]

        // vyraba sa novy objekt, slovo new sa nepise
        uncoveredWord = StringBuilder()
        for (i in challengeWord.indices) {
            uncoveredWord.append(Game.UNGUESSED_CHAR)
        }
        startTime = SystemClock.elapsedRealtime()
    }
}