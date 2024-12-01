package com.example.diceforshining

//import android.media.SoundPool
import android.annotation.SuppressLint
import android.media.AudioManager
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val audioManager: AudioManager = getSystemService(AUDIO_SERVICE) as AudioManager


        //本案例使用findViewById找UI
        /*
        资源ID的形式为 R.<type>.<name>
        对于View的ID， <type>为id   如 R.id.button
        */
        val rollButton: Button = findViewById(R.id.button)
        rollButton.setOnClickListener {
            //呼叫Toast.makeText() 即可建立内含文字 “Dice Rolled!” 的Toast
            Toast.makeText( this, "Dice Rolled!", Toast.LENGTH_SHORT).show()

            // 播放系统提示音
            //audioManager.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD)
            // 播放系统当前报错音
            audioManager.playSoundEffect(AudioManager.FX_KEYPRESS_INVALID)
            rollDice() //执行滚动的功能
        }

    }

    @SuppressLint("SetTextI18n")
    private fun rollDice() {
        val dice = Dice(6)
        val diceRoll = dice.roll()
        val resultTextView: TextView = findViewById(R.id.textView)
        resultTextView.text = diceRoll.toString()

        val luckyTextView: TextView = findViewById(R.id.textView2)
        val luckyNumber = 6
        if (luckyNumber == diceRoll) {
            luckyTextView.text = "Lucky Number!"
        }else{
            luckyTextView.text = ""
        }

        val diceImage: ImageView = findViewById(R.id.imageView)
/*        if (diceRoll == 1){
            diceImage.setImageResource(R.drawable.dice_1)
        }else if (diceRoll == 2){
            diceImage.setImageResource(R.drawable.dice_2)
        }*/
        //comment the if code block above since it's better to use when here
/*        when(diceRoll){
            1 -> diceImage.setImageResource(R.drawable.dice_1)
            2 -> diceImage.setImageResource(R.drawable.dice_2)
            3 -> diceImage.setImageResource(R.drawable.dice_3)
            4 -> diceImage.setImageResource(R.drawable.dice_4)
            5 -> diceImage.setImageResource(R.drawable.dice_5)
            6 -> diceImage.setImageResource(R.drawable.dice_6)
        }*/
        //But there is still another better way
        val drawableResource = when(diceRoll){
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        diceImage.setImageResource(drawableResource)

    }
}

/*
骰子蓝图 class Dice
具有滚动功能 fun roll()
 */
class Dice(val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }

}
