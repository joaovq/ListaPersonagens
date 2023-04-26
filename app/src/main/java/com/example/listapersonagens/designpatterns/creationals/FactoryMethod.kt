package com.example.listapersonagens.designpatterns.creationals

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import androidx.annotation.DrawableRes
import com.example.listapersonagens.R
import com.example.listapersonagens.databinding.CustomDialogLayoutBinding
import com.example.listapersonagens.designpatterns.creationals.CustomDialogType.ALERT
import com.example.listapersonagens.designpatterns.creationals.CustomDialogType.CONFIRM
import com.example.listapersonagens.designpatterns.creationals.CustomDialogType.DELETE

/**
 * Utilizando o método de fábrica (factory method) pra criar dialogos que necessariamente a view não
 * precisa saber como ser criadas, assim:
 *
 *  - evitando acoplamentos na view
 *  - "S" do solid. Delegamos a responsabilidade de criar os dialogos para outra classe
 *  - facilitar a manutenção
* */

// Obs: dava pra dividir em pacotes mas optei assim para melhorar a correção

object ConfirmDialogFactory {
    fun createDialog(
        type: CustomDialogType,
        context: Context,
        action: (Dialog) -> Unit,
    ): CustomDialog =
        when (type) {
            CONFIRM -> {
                CustomDialog.ConfirmDialog(context) { dialog ->
                    action(dialog)
                }
            }
            DELETE -> {
                CustomDialog.DeleteDialog(context) { dialog ->
                    action(dialog)
                }
            }
            ALERT -> {
                CustomDialog.AlertDialog(context) { dialog ->
                    action(dialog)
                }
            }
        }
}

sealed class CustomDialog(
    @DrawableRes val icon: Int,
    val message: String,
    val description: String,
    context: Context,
    val actionYes: (Dialog) -> Unit,
) : Dialog(context) {

    val binding = CustomDialogLayoutBinding.inflate(LayoutInflater.from(context))

    data class ConfirmDialog(val contextDialog: Context, val action: (Dialog) -> Unit) :
        CustomDialog(
            R.drawable.ic_disney,
            "Deseja realmente confirmar",
            "Confirmando mesmo...",
            contextDialog,
            action,
        ) {
        init {
            setContentView(bind().root)
        }

        override fun bind(): CustomDialogLayoutBinding {
            binding.ivImage.setImageResource(icon)
            binding.tvNo.setOnClickListener {
                dismiss()
            }
            binding.tvYes.setOnClickListener {
                actionYes(this@ConfirmDialog)
            }
            binding.tvTitle.text = message
            binding.tvDescription.text = description
            return binding
        }
    }

    data class DeleteDialog(val contextDialog: Context, val action: (Dialog) -> Unit) :
        CustomDialog(
            R.drawable.ic_disney,
            "Deseja realmente deletar?",
            "Confirmando mesmo...",
            contextDialog,
            action,
        ) {
        override fun bind(): CustomDialogLayoutBinding {
            binding.ivImage.setImageResource(icon)
            binding.tvNo.setOnClickListener {
                dismiss()
            }
            binding.tvYes.setOnClickListener {
                actionYes(this@DeleteDialog)
            }
            binding.tvTitle.text = message
            binding.tvDescription.text = description
            return binding
        }
    }

    data class AlertDialog(val contextDialog: Context, val action: (Dialog) -> Unit) :
        CustomDialog(
            R.drawable.ic_disney,
            "Alertaa",
            "Perigoso fazer isso...",
            contextDialog,
            action,
        ) {
        override fun bind(): CustomDialogLayoutBinding {
            binding.ivImage.setImageResource(icon)
            binding.tvNo.setOnClickListener {
                dismiss()
            }
            binding.tvYes.setOnClickListener {
                actionYes(this@AlertDialog)
            }
            binding.tvTitle.text = message
            binding.tvDescription.text = description
            return binding
        }
    }

    abstract fun bind(): CustomDialogLayoutBinding
}

enum class CustomDialogType {
    CONFIRM,
    DELETE,
    ALERT,
}
