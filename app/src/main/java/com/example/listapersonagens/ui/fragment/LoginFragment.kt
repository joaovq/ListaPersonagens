package com.example.listapersonagens.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.listapersonagens.databinding.FragmentLoginBinding
import com.example.listapersonagens.designpatterns.behavioral.LoginUiState
import com.example.listapersonagens.designpatterns.creationals.ConfirmDialogFactory
import com.example.listapersonagens.designpatterns.creationals.CustomDialogType
import com.example.listapersonagens.designpatterns.structurals.validateEmail
import com.example.listapersonagens.designpatterns.structurals.validatePassword
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
                ConfirmDialogFactory.createDialog(
                    CustomDialogType.CONFIRM,
                    requireContext(),
                ) { dialog ->
                    if (tietEmail.text.toString().validateEmail() && tietPassword.text.toString()
                            .validatePassword()
                    ) {
                        viewModel.signIn(
                            tietEmail.text.toString(),
                            tietPassword.text.toString(),
                        )
                        dialog.dismiss()
                    }
                }.show()
            }
        }
    }

    private fun initStateFlow() {
        lifecycleScope.launch {
            viewModel.auth.observe(viewLifecycleOwner) { state ->
                when(state) {
                    LoginUiState.LoginState.Error ->  toast(message = "Usuário não encontrado.")
                    LoginUiState.LoginState.Success -> findNavController().navigate(
                        LoginFragmentDirections.actionLoginFragmentToCharactersFragment(),
                    )
                }
            }
            viewModel.isLoading.collectLatest { isLoading ->
                if (isLoading == true) {
                    binding.pbLogin.isVisible = true
                    binding.tilEmail.isVisible = false
                    binding.tilPassword.isVisible = false
                } else {
                    binding.pbLogin.isVisible = false
                    binding.tilEmail.isVisible = true
                    binding.tilPassword.isVisible = true
                }
            }
        }
    }
}
