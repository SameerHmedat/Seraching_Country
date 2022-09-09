package com.example.searchingapplication21_6_2022

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import java.util.*

class MainActivity : AppCompatActivity() {

    private val infoList: ArrayList<Element> = arrayListOf()
    private var myAdapter = ElementAdapter(infoList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupInfoUIList()
        setINfoList()
        changeText()
    }

    private fun setupInfoUIList(){
        rvElement.layoutManager = LinearLayoutManager(this)
        rvElement.setHasFixedSize(true)
        myAdapter.setOnItemClickListener(object : ElementAdapter.OnItemClickedListener {
            override fun onItemClick(position: Int) {
                rvElement.visibility = View.INVISIBLE
                textInputEditText.setText(myAdapter.newList[position].name)
                txtCountry.text = textInputEditText.text
                //txtCountry.text = myAdapter.newList[position].name //Error
                //Toast.makeText(this@MainActivity,"Sameer",Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun setINfoList(){
        val json: String =
            this.assets.open("Info.json").bufferedReader().use { it.readText() }
        val jsoArr = JSONArray(json)
        for (i in 0 until jsoArr.length()) {
            val jsonObj = jsoArr.getJSONObject(i)
            infoList += Element(name = jsonObj.getString("name"))

            rvElement.adapter = myAdapter
        }}

    private fun changeText(){
        textInputEditText.addTextChangedListener(object :TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
               //rvElement.visibility = View.VISIBLE
            }

            override fun afterTextChanged(s: Editable?) {
                rvElement.visibility = View.VISIBLE
                filter(s.toString())

            }

            private fun filter(text: String) {
                val filterList: ArrayList<Element> = arrayListOf()
                for (item in infoList) {
                    if (item.name.lowercase(Locale.getDefault())
                            .contains(text.lowercase(Locale.getDefault()))
                    ) {
                        filterList.add(item)
                    }

                }
                myAdapter.filterList(filterList)
            }
        })
    }
}






