package com.example.orgs.extensions

import android.net.Uri
import android.widget.ImageView
import coil.load
import com.example.orgs.R


fun ImageView.tentaCarregarImagem(url: Uri? = null) {
    load(url) {
        fallback(R.drawable.ic_no_image)
        error(R.drawable.ic_no_image)
        placeholder(R.drawable.ic_no_image)
    }
}
