package com.example.incurance_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    override fun onNothingSelected(parent: AdapterView<*>?) {
        
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        /*Toast.makeText(this, "Position = $position", Toast.LENGTH_SHORT).show()*/
        Toast.makeText(this, "Item = ${spinnerAge.getItemAtPosition(position)}", Toast.LENGTH_SHORT).show()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinnerAge.onItemSelectedListener = this

        buttonCalculate.setOnClickListener{
            calculatePremium()
        }
    }

    private fun calculatePremium() {

        var premium : Int = 0

        //position = index of an item selected by user
        val age: Int = spinnerAge.selectedItemPosition
        premium += when(age){
            0 -> 60
            1 -> 70
            2 -> 90
            3 -> 120
            else -> 150
        }

        //ID of a radioButton checked by user
        val gender : Int = radioGroupGender.checkedRadioButtonId
        //if(gender == radioButtonMale.id)
        if(gender == R.id.radioButtonMale){
            premium += when(age){
                0 -> 0
                1 -> 50
                2 -> 100
                3 -> 150
                else -> 200
            }
        }else{

        }

        //Boolean value
        val smoker : Boolean = checkBoxSmoker.isChecked
        if(smoker){
            premium += when(age){
                0 -> 0
                1 -> 100
                2 -> 150
                3 -> 200
                4 -> 250
                else -> 300
            }
        }
        textViewPremium.text = getString(R.string.premium) + "RM" + premium.toString()
    }

    fun resetInput(View: View?){
        textViewPremium.text = getString(R.string.premium)
        checkBoxSmoker.isChecked = false
        radioGroupGender.clearCheck()
        spinnerAge.setSelection(0)
    }
}
