package com.example.orgs.ui.dialog

import android.app.Dialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.orgs.R
import com.example.orgs.databinding.CarregarImagemDialogLayoutBinding
import com.example.orgs.extensions.tentaCarregarImagem

class LoadImageDialog(
    var url: Uri? = null,
) : DialogFragment() {

    lateinit var binding: CarregarImagemDialogLayoutBinding
    private lateinit var listener: ImageDialogListener


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding =
            CarregarImagemDialogLayoutBinding.inflate(LayoutInflater.from(requireContext()))

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
                    listener.onDialogPositiveClick(this)
                }

            setOnClickListener()

            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = context as ImageDialogListener
        } catch (e: ClassCastException) {
            // The activity doesn't implement the interface, throw exception
            throw ClassCastException((context.toString() +
                    " must implement NoticeDialogListener"))
        }
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


    interface ImageDialogListener {
        fun onDialogPositiveClick(dialog: LoadImageDialog)
    }

}
