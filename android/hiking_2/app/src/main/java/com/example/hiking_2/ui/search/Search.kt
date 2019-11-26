package com.example.hiking_2.ui.search

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.example.hiking_2.R
import org.json.JSONArray
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.search.*
import org.json.JSONObject
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Build
import android.view.View
import android.widget.SearchView
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.search.searchView
import kotlinx.android.synthetic.main.search.text_search


class Search : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search)
        println("connected to search !!!!!!!!")

        searchTag()

    }

    companion object {
        const val tagToPass = "hh"
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @SuppressLint("ResourceType")
    private fun searchTag() {

        val searchPage = Intent(this, empty_search::class.java)
        searchView.setOnClickListener {
            startActivity(searchPage)
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(s: String): Boolean {
                println("I'm changing !!!!!!!")
                return true
            }

            override fun onQueryTextSubmit(s: String): Boolean {
                println("I submitted !!!!!!!!")
                val whatItype = searchView.query.toString()
                println(whatItype)
                return true
            }
        })

        //themeName.text = "nope"
        println("connected!!!!!!!!")

        val tagPassed = intent.getStringExtra(tagToPass)

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        // val url = "https://aptproject-255903.appspot.com/search?tag=hot"
        val url = "https://aptproject-255903.appspot.com/search?tag=" + tagPassed
        val basicPhotoUrl = "https://aptproject-255903.appspot.com/photo?photoId="

        // Request a string response from the provided URL.
        val jsonGetRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener<JSONObject> { response ->


                var photoIDs = response.getJSONArray("theme_image")
                var photoID = photoIDs.getJSONObject(0)
                var photoUrl = basicPhotoUrl + photoID.getString("\$oid")


                val selected_tag = response.getString("selected_tag")
                val reports = response.getJSONArray("reports")
                val user_name = response.getJSONArray("user_name")



                var i = 0
                while (i < reports.length()) {

                    var newCard = RelativeLayout(this)
                    var current_report = reports.getJSONObject(i)

                    var reportImageView = ImageView(this)
                    var reportPhotoIDs = current_report.getJSONArray("photos")
                    var reportPhotoID = reportPhotoIDs.getJSONObject(0)
                    var reportPhotoUrl = basicPhotoUrl + reportPhotoID.getString("\$oid")
                    Picasso
                        .get()
                        .load(reportPhotoUrl)
                        .into(reportImageView)

                    var nameTextView = TextView(this)
                    nameTextView.text = "${user_name[i]}"

                    var dateTextview = TextView(this)
                    dateTextview.text = current_report.getString("date_in")

                    var descriptionTextView = TextView(this)
                    descriptionTextView.text = current_report.getString("description")

                    // generate id for each component
                    reportImageView.id = ImageView.generateViewId()
                    nameTextView.id = TextView.generateViewId()
                    dateTextview.id = TextView.generateViewId()
                    descriptionTextView.id =TextView.generateViewId()


                    var param1 = RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    param1.leftMargin = 50
                    param1.addRule(RelativeLayout.BELOW, reportImageView.id)
                    nameTextView.setLayoutParams(param1)
                    println("id is " + reportImageView.id)
                    println("id is " + nameTextView.id)

                    var param2 = RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    param2.leftMargin = 50
                    param2.topMargin = 15
                    param2.addRule(RelativeLayout.BELOW, nameTextView.id)
                    dateTextview.setLayoutParams(param2)

                    var param3 = RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    param3.leftMargin = 50
                    param3.topMargin = 15
                    param3.addRule(RelativeLayout.BELOW, dateTextview.id)
                    descriptionTextView.setLayoutParams(param3)




                    newCard.addView(reportImageView)
                    newCard.addView(nameTextView)
                    newCard.addView(dateTextview)
                    newCard.addView(descriptionTextView)
                    //newCard.addView(tagTextView)

                    reportImageView.layoutParams.width = RelativeLayout.LayoutParams.MATCH_PARENT
                    reportImageView.layoutParams.height = RelativeLayout.LayoutParams.WRAP_CONTENT
                    reportImageView.scaleType = ImageView.ScaleType.FIT_XY
                    reportImageView.adjustViewBounds = true

                    nameTextView.layoutParams.width = RelativeLayout.LayoutParams.MATCH_PARENT
                    nameTextView.layoutParams.height = RelativeLayout.LayoutParams.WRAP_CONTENT


                    nameTextView.textSize = 30F



                    //nameTextView.scaleType = ImageView.ScaleType.FIT_XY
                    //reportImageView.adjustViewBounds = true

                    //tagTextView.text = current_report.getJSONArray("tags")[0].toString()
                    var current_tags = current_report.getJSONArray("tags")
                    var idx = 0
                    while (idx < current_tags.length()) {
                        var tagTextView = TextView(this)
                        tagTextView.text = "${current_tags[idx]}"
                        newCard.addView(tagTextView)

                        tagTextView.layoutParams.width = RelativeLayout.LayoutParams.WRAP_CONTENT
                        tagTextView.layoutParams.height = RelativeLayout.LayoutParams.WRAP_CONTENT
                        tagTextView.setBackgroundColor(Color.BLACK)
                        tagTextView.setTextColor(Color.WHITE)
                        tagTextView.id = TextView.generateViewId()

                        var param4 = RelativeLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                        param4.leftMargin = 50
                        param4.topMargin = 15
                        param4.addRule(RelativeLayout.BELOW, descriptionTextView.id)
                        tagTextView.setLayoutParams(param4)

                        ++idx
                    }




                    var param: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT)
                    param.topMargin = 50
                    param.bottomMargin = 50
                    newCard.layoutParams = param
                    newCard.minimumHeight = 200





                    theme_linear_layout.addView(newCard)

                    ++i
                }


            },
            Response.ErrorListener { text_search.text = "That didn't work!" })

        // Add the request to the RequestQueue.
        queue.add(jsonGetRequest)
    }
}