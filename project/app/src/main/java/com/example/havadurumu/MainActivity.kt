package com.example.havadurumu

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.net.ConnectivityManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.havadurumu.Dto.Citys
import com.example.havadurumu.databinding.ActivityMainBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.Locale


class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    private val filteredItemList = ArrayList<String>()
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val REQUEST_LOCATION_PERMISSION = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //internet baglantısı yoksa uygulamadan atar
        if(!isNetworkConnected())
            showNetworkAlertDialog()

        //konum bilgisi için değiken oluşturuldu
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        binding.progressBar3.isVisible = false
        binding.useLocate.setOnClickListener {
            if(!isNetworkConnected())
                showNetworkAlertDialog()
            else{
                getLocation()
            }
        }
        addCitytoSpinner()
        filterCity()

        binding.getirBtn.setOnClickListener{
            if(!isNetworkConnected())
                showNetworkAlertDialog()
            else{
                Citys.city = binding.spinner.selectedItem.toString()
                getWeatherPage()
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Konum izni Verildi.", Toast.LENGTH_SHORT).show()
                getLocation()
            } else {
                Toast.makeText(this, "Konum izni reddedildi.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getWeatherPage(){
        var view = Intent(applicationContext,FifeDaysWeather::class.java)
        startActivity(view)
    }

    private fun getLocation(){
        if (ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(ACCESS_FINE_LOCATION), REQUEST_LOCATION_PERMISSION)
        } else {
            whileGetLocate(true)
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                if (location != null) {
                    val latitude = location.latitude
                    val longitude = location.longitude
                    val geocoder = Geocoder(this, Locale.getDefault())
                    val addressList = geocoder.getFromLocation(latitude, longitude, 1)
                    if (!addressList.isNullOrEmpty()) {
                        val city = addressList[0].adminArea
                        Citys.city = city.lowercase()
                        whileGetLocate(false)
                        getWeatherPage()
                    }
                }
            }
        }
    }

    private fun whileGetLocate(cond:Boolean){
        binding.editTextText2.isEnabled = !cond
        binding.spinner.isEnabled = !cond
        binding.progressBar3.isVisible = cond
        binding.getirBtn.isEnabled = !cond
        binding.useLocate.isEnabled = !cond
    }

    private fun showNetworkAlertDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Uyarı")
        builder.setMessage("İnternet bağlantısı yok!")
        builder.setPositiveButton("Tamam") { dialog, _ ->
            finish()
        }
        builder.setOnCancelListener{
            finish()
        }
        val alertDialog = builder.create()
        alertDialog.show()
    }

    private fun isNetworkConnected(): Boolean {
        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    private fun filterCity(){

        binding.editTextText2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val searchText = s.toString()
                filterSpinnerItems(searchText)
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun filterSpinnerItems(searchText: String) {
        filteredItemList.clear()

        for (item in Citys.citys) {
            if (item.contains(searchText, ignoreCase = true)) {
                filteredItemList.add(item)
            }
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, filteredItemList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter
    }

    private fun addCitytoSpinner(){
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, Citys.citys)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter
    }
}