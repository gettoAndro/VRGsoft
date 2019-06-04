package com.getto.vrgsoft.ui.favorite

import android.os.Bundle
import com.getto.vrgsoft.R
import com.getto.vrgsoft.data.nyt.service.Results
import com.getto.vrgsoft.ui.base.BaseActivity
import com.getto.vrgsoft.util.ImageSaver
import kotlinx.android.synthetic.main.activity_favorite_details.*

class FavoriteDetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_details)
        val results = intent.extras.getParcelable<Results>("favorite")
        val bitmap = ImageSaver(this).
                setFileName("${results.id}.jpg").
                setDirectoryName("images").
                load()
        img_desc.setImageBitmap(bitmap)
        title_desc.text = results.title
        date_desc.text = results.published_date
        description.text = results.abstract
        toolbar.setNavigationOnClickListener { v -> onBackPressed() }
    }
}