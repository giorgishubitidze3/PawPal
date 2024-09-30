package com.spearson.pawpal.presentation.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.spearson.pawpal.R
import com.spearson.pawpal.databinding.FragmentProfileBinding
import com.spearson.pawpal.domain.repository.FirebaseService
import com.spearson.pawpal.presentation.auth.signIn.SignInActivity
import com.spearson.pawpal.presentation.util.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment() {


    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentProfileBinding
    private val viewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth
        auth.currentUser?.let { viewModel.fetchUser(it.uid) }

        val navController = activity?.findNavController(R.id.fragment_container)

        binding.btnSignOut.setOnClickListener {
            auth.signOut()

            val intent = Intent(requireContext(), SignInActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }


        //observe user livedata

        viewModel.user.observe(requireActivity()) { user ->
            binding.userNameProfile.text = user?.name
            binding.userUsernameProfile.text = user?.username
        }


        view.findViewById<Button>(R.id.btn_add_post).setOnClickListener {
            if (navController != null) {
                navController.navigate(R.id.palMapFragment)
            }
        }
    }


}