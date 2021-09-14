package com.osama.tvshowmanager.presentation.new_movie

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.apollographql.apollo.api.Error
import com.apollographql.apollo.api.toInput
import com.osama.tvshowmanager.R
import com.osama.tvshowmanager.data.commun.onError
import com.osama.tvshowmanager.data.commun.onLoading
import com.osama.tvshowmanager.data.commun.onSuccess
import com.osama.tvshowmanager.databinding.ActivityAddNewMoviewBinding
import com.osama.tvshowmanager.presentation.movies.MoviesViewModel
import com.osama.tvshowmanager.utils.hide
import com.osama.tvshowmanager.utils.show
import com.osama.tvshowmanager.utils.toast
import com.tvshowmanager.server.type.CreateMovieFieldsInput
import com.tvshowmanager.server.type.CreateMovieInput
import dagger.hilt.android.AndroidEntryPoint
import java.text.DateFormatSymbols
import java.time.Month
import java.time.format.TextStyle
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS

@AndroidEntryPoint
class AddNewMovieActivity : AppCompatActivity() {
    private val viewModel: MoviesViewModel by viewModels()
    private lateinit var binding: ActivityAddNewMoviewBinding
    private lateinit var date: Date
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNewMoviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initObserver()
    }

    private fun initObserver() {
        viewModel.resultCreateMovie.observe(this) {
            it.onSuccess { createMovie ->
                binding.progressCircular.hide()
                toast("Success: movie name is" + createMovie.createMovie?.movie?.title)
                finish()
            }.onError { error ->
                binding.progressCircular.hide()
                when (error.messageResource) {
                    is Int -> toast(getString(error.messageResource))
                    is Error? -> {
                        error.messageResource?.let { errorMessage -> toast(errorMessage.message) }
                    }
                }
            }.onLoading {
                binding.progressCircular.show()
            }
        }
    }

    fun submit(view: View) {
        if (binding.etTitle.text.toString().isNotEmpty() && binding.etTitle.text.toString()
                .isNotEmpty()
        ) {
            binding.progressCircular.show()
            val fields = CreateMovieFieldsInput(
                title = binding.etTitle.text.toString(),
                releaseDate = date.toInput(),
                seasons = (binding.etSeasons.text.toString().toDouble()).toInput()
            )
            val input = CreateMovieInput(fields.toInput())

            viewModel.createMovie(input)
        } else {
            Toast.makeText(this, "Enter all fields", Toast.LENGTH_LONG).show()
        }
    }

    @SuppressLint("SetTextI18n")
    fun showDatePicker(view: View) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)


        val dpd = DatePickerDialog(
            this,
            { view, year, monthOfYear, dayOfMonth ->
                run {

                    date = Date(year, month, day)

                    // Display Selected date in textbox
                    binding.btnReleaseDate.text = "$dayOfMonth ${
                        DateFormatSymbols().months[month - 1]
                    }, $year"
                }
            },
            year,
            month,
            day
        )

        dpd.show()
    }
}