package com.spearson.pawpal.presentation.map

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.play.integrity.internal.k
import com.google.gson.internal.bind.MapTypeAdapterFactory
import com.spearson.pawpal.R


class MapFragment : Fragment() {

    private var map : GoogleMap? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapView = view.findViewById<MapView>(R.id.map_view)

        mapView.onCreate(savedInstanceState)

    }




    override fun onResume() {
        super.onResume()
        val mapView = view?.findViewById<MapView>(R.id.map_view)
        mapView?.onResume()
    }

    override fun onStart() {
        super.onStart()

        val mapView = view?.findViewById<MapView>(R.id.map_view)
        mapView?.getMapAsync { map ->
            val tbilisi = LatLng(41.6938,44.8015)

            map.moveCamera(CameraUpdateFactory.newLatLng(tbilisi))
            map.moveCamera(CameraUpdateFactory.zoomTo(11F))
        }

        mapView?.onStart()
    }

    override fun onStop() {
        super.onStop()

        val mapView = view?.findViewById<MapView>(R.id.map_view)
        mapView?.onStop()
    }

    override fun onPause() {
        super.onPause()

        val mapView = view?.findViewById<MapView>(R.id.map_view)
        mapView?.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()

        val mapView = view?.findViewById<MapView>(R.id.map_view)
        mapView?.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val mapView = view?.findViewById<MapView>(R.id.map_view)
        mapView?.onSaveInstanceState(outState)
    }


}
