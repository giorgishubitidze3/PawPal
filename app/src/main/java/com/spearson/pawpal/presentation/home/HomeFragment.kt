package com.spearson.pawpal.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.SearchView
import com.spearson.pawpal.R



class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchView = view.findViewById<android.widget.SearchView>(R.id.searchview_home)
        val iconLocation = view.findViewById<ImageView>(R.id.imageView_home_location)
        val tvLocation = view.findViewById<TextView>(R.id.tv_home_location)

        //icon and location visibility on search view focus change
        searchView.setOnQueryTextFocusChangeListener { _, hasFocus ->
            if(hasFocus){
                iconLocation.visibility = View.GONE
                tvLocation.visibility = View.GONE
            }else{
                iconLocation.visibility = View.VISIBLE
                tvLocation.visibility = View.VISIBLE
            }
        }

        //iconify searchview on back button press
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                if(!searchView.isIconified){
                    searchView.setIconified(true)
                    searchView.onActionViewCollapsed()
                    searchView.clearFocus()
                    iconLocation.visibility = View.VISIBLE
                    tvLocation.visibility = View.VISIBLE
                }else{
                    isEnabled = false
                    requireActivity().onBackPressed()
                }
            }
        })



    }
}