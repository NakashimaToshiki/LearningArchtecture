package com.nakashima_hibari.learning.architecture.ch3_repository

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nakashima_hibari.learning.architecture.ch3_repository.addedittask.AddEditTaskActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            startActivity(Intent(this, AddEditTaskActivity::class.java))
        }
    }
}
