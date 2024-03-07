package ru.lonelywh1te.kotlin_calc.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.lonelywh1te.kotlin_calc.R
import ru.lonelywh1te.kotlin_calc.databinding.ActivityInfoBinding

class InfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        binding = ActivityInfoBinding.inflate(layoutInflater)
    }
}