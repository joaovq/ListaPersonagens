package com.example.listapersonagens.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.listapersonagens.R
import com.example.listapersonagens.databinding.FragmentLoginBinding
import com.example.listapersonagens.ui.utils.extension.toast
import com.example.listapersonagens.ui.viewmodel.SignInViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<SignInViewModel>()

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
        initStateFlow()
    }

    private fun setupView() {
        with(binding) {
            btnLogin.setOnClickListener {
//                ANTES
                /**
                 *
                 * val isLogged = FirebaseAuthenticator.login(
                 tietEmail.text.toString(),
                 tietPassword.text.toString(),
                 )
                 * */

                viewModel.signIn(
                    tietEmail.text.toString(),
                    tietPassword.text.toString(),
                )
            }
        }
    }

    private fun initStateFlow() {
        lifecycleScope.launch {
            viewModel.isAuth.collectLatest { isLoggedIn ->
                isLoggedIn?.let {
                    if (it) {
                        findNavController().navigate(R.id.action_loginFragment_to_charactersFragment)
                    } else {
                        toast(message = "Usuário não encontrado.")
                    }
                }
            }
        }
    }
}
