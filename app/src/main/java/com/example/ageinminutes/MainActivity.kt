package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener { view ->
            clickDatePicker(view)
        }
    }

    fun clickDatePicker(view: View) {

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener {
                    view, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(this,
                    "The chosen year is $selectedYear, the month is $selectedMonth, and the day is $selectedDayOfMonth"
                    , Toast.LENGTH_LONG).show()

//              Selected Date
                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                tvSelectedDate.setText(selectedDate)

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)
                println(theDate)

//              Age in Minutes
                val selectedDateInMinutes = theDate!!.time / 60000

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateInMinutes = currentDate!!.time / 60000

                val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                tvSelectedDateInMinutes.setText(differenceInMinutes.toString())

//              Age in Days
                val selectedDateInDays = theDate!!.time / 86400000
                val currentDateInDays = currentDate!!.time /86400000
                val differenceInDays = currentDateInDays - selectedDateInDays
                tvSelectedDateInDays.setText(differenceInDays.toString())

            }, year, month, day).show()
    }
}
