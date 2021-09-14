package com.osama.tvshowmanager.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.osama.tvshowmanager.R
import com.osama.tvshowmanager.presentation.movies.MoviesActivity
import com.osama.tvshowmanager.presentation.new_movie.AddNewMovieActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showAllMovies(view: View) {
        startActivity(Intent(this, MoviesActivity::class.java))
    }

    fun addNewMovie(view: View) {
        startActivity(Intent(this, AddNewMovieActivity::class.java))
    }
}