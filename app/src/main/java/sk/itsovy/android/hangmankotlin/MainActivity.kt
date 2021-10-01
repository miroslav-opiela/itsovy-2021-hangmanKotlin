package sk.itsovy.android.hangmankotlin

import android.graphics.ColorFilter
import android.graphics.LightingColorFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var imageView: ImageView
    lateinit var textView: TextView
    lateinit var editText: EditText

    var game : Game? = null

    val gallowsIds = intArrayOf(
        R.drawable.gallows0,
        R.drawable.gallows1,
        R.drawable.gallows2,
        R.drawable.gallows3,
        R.drawable.gallows4,
        R.drawable.gallows5,
        R.drawable.gallows6
    )

    companion object {
        val WON_GAME = LightingColorFilter(android.graphics.Color.GREEN, android.graphics.Color.BLACK)
        val LOST_GAME = LightingColorFilter(android.graphics.Color.RED, android.graphics.Color.BLACK)

        const val BUNDLE_KEY = "game"
        const val BEST_TIME_KEY = "best time"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageViewGallows)
        textView = findViewById(R.id.textViewGuessedWord)
        editText = findViewById(R.id.editTextLetter)

        if (savedInstanceState == null) {
            restartGame()
        } else {
            game = savedInstanceState.getSerializable(BUNDLE_KEY) as Game
            updateText()
            updateImage()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(BUNDLE_KEY, game)
    }

    fun restartGame() {
        val words = resources.getStringArray(R.array.dictionary)
        game = HangmanGame(words, Random.Default)
        updateText()
        updateImage()
        imageView.colorFilter = null
    }

    fun onImageClick(view: View) {


    }

    fun updateText() {
        // zapuzdrenost tam je, volanie settra na text a gettra na guessedCharacters
        // ?. safe call
        //textView.text = game?.guessedCharacters ?: "no text"

        // null check - predpokladame ze null tam nebude, inak by bola vynimka
        textView.text = game!!.guessedCharacters
    }

    fun updateImage() {
        //int index = Game.DEFAULT_ATTEMPTS_LEFT - game.getAttemptsLeft();
        //        imageView.setImageResource(gallowsIds[index]);
        val index = Game.DEFAULT_ATTEMPTS_LEFT - game!!.attemptsLeft
        // imageResource nie je property v imageView, preto volame funkciu
        imageView.setImageResource(gallowsIds[index])
    }
}