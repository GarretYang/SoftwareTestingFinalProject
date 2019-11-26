package com.example.hiking_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.example.hiking_2.ui.MapsActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_report_details.*

class ReportDetailsActivity : AppCompatActivity() {

    private val TAG = "ReportDetailsActivity"

    private var photoIdsArray = arrayListOf<String>()

    private var photoIdx = 0

    private val basePhotoUrl = "https://aptproject-255903.appspot.com/photo?photoId="

    companion object {
        const val LOCATION_NAME = "NAME"
        const val LOCATION_THEME = "THEME"
        const val LOCATION_DATE = "DATE"
        const val LOCATION_REPORT_ID = "ID"
        const val LOCATION_DESC = "DESC"
        const val LOCATION_PHOTOS = "PHOTOS"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_details)
        showReportDetails()
    }


    private fun showReportDetails() {
        val reportId = intent.getStringExtra(LOCATION_REPORT_ID)
        val locationName = intent.getStringExtra(LOCATION_NAME)
        val theme = intent.getStringExtra(LOCATION_THEME)
        val date = intent.getStringExtra(LOCATION_DATE)
        val desc = intent.getStringExtra(LOCATION_DESC)
        // Photo IDs array
        photoIdsArray = intent.getStringArrayListExtra(LOCATION_PHOTOS)

        Log.i(TAG, reportId.toString())
        Log.i(TAG, locationName.toString())
        Log.i(TAG, photoIdsArray.toString())

        findViewById<TextView>(R.id.textTheme).text = theme
        findViewById<TextView>(R.id.textDate).text = date
        findViewById<TextView>(R.id.textLocationName).text = locationName
        findViewById<TextView>(R.id.textDesc).text = desc

        // Load image from remote
        val url = basePhotoUrl + photoIdsArray[photoIdx]
        Picasso.get()
            .load(url)
            .placeholder(R.drawable.common_google_signin_btn_icon_dark)
            .into(imageView)
    }

    // Transition function to Google map
    fun transitionToMap(view: View) {

        val mapIntent = Intent(this, MapsActivity::class.java)

        // Start new activity
        startActivity(mapIntent)
    }


    fun transitionToMainPage(view: View) {

        val mainPageIntent = Intent(this, MainActivity::class.java)

        // Start new activity
        startActivity(mainPageIntent)
    }


    // Change to next displayed photo
    fun nextPhoto(view: View) {
        ++photoIdx
        photoIdx %= photoIdsArray.size
        val url = basePhotoUrl + photoIdsArray[photoIdx]
        Picasso.get()
            .load(url)
            .placeholder(R.drawable.common_google_signin_btn_icon_dark)
            .into(imageView)
    }
}
