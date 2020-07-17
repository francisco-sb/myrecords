package com.sb.myrecords.ui

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.textview.MaterialTextView
import com.sb.myrecords.MainActivity
import com.sb.myrecords.R
import com.sb.myrecords.R.drawable
import com.sb.myrecords.data.entities.User

/**
 * Created by Sb on 14/07/2020
 * com.sb.myrecords.ui
 * My Records
 */
fun Fragment.setTitle(title: String) {
    val mainActivity = (activity as MainActivity)
    val appBarLayout = mainActivity.findViewById<AppBarLayout>(R.id.app_bar)
    val collapsingToolbarLayout = mainActivity.findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)

    var isShow = true
    var scrollRange = -1
    appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
        if (scrollRange == -1){
            scrollRange = barLayout?.totalScrollRange!!
        }
        if (scrollRange + verticalOffset == 0){
            collapsingToolbarLayout.title = title
            isShow = true
        } else if (isShow){
            collapsingToolbarLayout.title = " " //careful there should a space between double quote otherwise it wont work
            isShow = false
        }
    })
}

fun Fragment.setExpandedToolbar(collapse: Boolean) {
    val mainActivity = (activity as MainActivity)
    val appBarLayout = mainActivity.findViewById<AppBarLayout>(R.id.app_bar)

    val profileContent = mainActivity.findViewById<LinearLayout>(R.id.profile_content)
    appBarLayout.setExpanded(collapse)
    profileContent.isGone = !collapse
}

fun Fragment.setToolbarData(user: User) {
    val mainActivity = (activity as MainActivity)
    val profilePicture = mainActivity.findViewById<ImageView>(R.id.profile_picture)
    val nameText = mainActivity.findViewById<MaterialTextView>(R.id.name_text)
    val usernameText = mainActivity.findViewById<MaterialTextView>(R.id.username_text)
    val biographyText = mainActivity.findViewById<MaterialTextView>(R.id.biography_text)

    Glide.with(this)
        .load(user.img)
        .placeholder(drawable.ic_profile_placeholder)
        .error(drawable.ic_profile_placeholder)
        .skipMemoryCache(true)
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .transform(CircleCrop())
        .into(profilePicture)

    nameText.text = user.name
    usernameText.text = user.username
    biographyText.text = user.biography
}

fun ProgressBar.hide() {
    visibility = View.GONE
}

fun ProgressBar.show() {
    visibility = View.VISIBLE
}