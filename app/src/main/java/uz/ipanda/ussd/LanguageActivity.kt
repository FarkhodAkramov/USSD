package uz.ipanda.ussd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.ipanda.ussd.databinding.ActivityLanguageBinding
import uz.ipanda.ussd.databinding.ActivityMainBinding

class LanguageActivity : AppCompatActivity() {
    lateinit var binding: ActivityLanguageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {

        }
    }
}