package activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.example.appbanwatch.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_thong_tin.*
import java.security.Permission

class ThongTin : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thong_tin)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        Actiontoolbar()
    }

    private fun Actiontoolbar() {
        setSupportActionBar(findViewById(R.id.toolthongtin))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolthongtin.setNavigationOnClickListener {
            finish()
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.isMyLocationEnabled = true

        // Add a marker in Sydney and move the camera
        val phungkhoang = LatLng(20.987114, 105.791598)
        mMap.addMarker(MarkerOptions().position(phungkhoang).title("Trà chanh bụi phố Phùng Khoang").snippet("Ngõ 67 Phùng Khoang Hà Đông Hà Nội").icon(BitmapDescriptorFactory.defaultMarker()))
        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        var camera:CameraPosition = CameraPosition.Builder().target(phungkhoang).zoom(90F).build()
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(camera))

    }
}
