package com.example.listapersonagens.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.listapersonagens.R
import com.example.listapersonagens.databinding.FragmentLoginBinding
import com.example.listapersonagens.network.authentication.FirebaseAuthenticator
import com.example.listapersonagens.ui.utils.extension.toast

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        with(binding) {
            btnLogin.setOnClickListener {
                val isLoggedIn = FirebaseAuthenticator.login(
                    tietEmail.text.toString(),
                    tietPassword.text.toString(),
                )
                if (isLoggedIn) {
                    findNavController().navigate(R.id.action_loginFragment_to_charactersFragment)
                } else {
                    toast(message = "Usuário não encontrado.")
                }
            }
        }
    }
}
