package com.example.orgs.ui.dialog

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.orgs.R
import com.example.orgs.databinding.CarregarImagemDialogLayoutBinding
import com.example.orgs.extensions.tentaCarregarImagem

class LoadImageDialog(
    var url: Uri? = null,
    var quandoImagemCarregada: (imagem: Uri?) -> Unit
) : DialogFragment() {

    lateinit var binding: CarregarImagemDialogLayoutBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding =
            CarregarImagemDialogLayoutBinding.inflate(layoutInflater)

        url?.let {
            binding.dialogImageUrl.editText?.setText(url.toString())
            carregaImagemNaView(url)
        }

        return activity?.let {

            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setView(binding.root)
                .setNegativeButton(getString(R.string.load_image_dialog_cancel_label)) { _, _ -> }
                .setPositiveButton(getString(R.string.load_image_dialog_confirm_label)) { _, _ ->
                    quandoImagemCarregada(url)
                }

            setOnClickListener()

            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun setOnClickListener() {
        binding.dialogButtonCarregar.setOnClickListener {
            val imageUrlText = binding.dialogImageUrl.editText?.text.toString()
            if (imageUrlText.isNotEmpty()) {
                carregaImagemNaView(Uri.parse(imageUrlText))
            }
        }
    }

    private fun carregaImagemNaView(url: Uri?) {
        val imageView = binding.dialogIvProduto
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        imageView.tentaCarregarImagem(url)
        this.url = url
    }
}
