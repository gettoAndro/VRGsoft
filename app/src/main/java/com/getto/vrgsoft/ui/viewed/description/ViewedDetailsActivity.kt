package com.getto.vrgsoft.ui.viewed.description

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable
import com.getto.vrgsoft.R
import com.getto.vrgsoft.data.nyt.service.Results
import com.getto.vrgsoft.ui.base.BaseActivity
import com.getto.vrgsoft.util.ImageSaver
import kotlinx.android.synthetic.main.activity_viewed_details.*
import javax.inject.Inject

class ViewedDetailsActivity : BaseActivity(), ViewedDetailsContract.View {

    @Inject
    lateinit var presenter: ViewedDetailsContract.Presenter<ViewedDetailsContract.View>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewed_details)
        activityComponent.inject(this)
        presenter.bindView(this)
        val results = intent.extras.getParcelable<Results>("viewed")
        Glide.with(this).load(results.media[0].metadata?.get(2)?.url).into(img_viewed_desc)
        title_viewed_desc.text = results.title
        date_viewed_desc.text = results.published_date
        description_viewed.text = results.abstract
        toolbar.setNavigationOnClickListener { v -> onBackPressed() }
        fav.setOnClickListener {

            if (!ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    1)
            }
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
                presenter.insertViewed(results)
                val draw = img_viewed_desc.drawable as GlideBitmapDrawable
                val bitmap = draw.bitmap
                ImageSaver(this).
                    setFileName("${results.id}.jpg").
                    setDirectoryName("images").
                    save(bitmap)
            }
        }
    }

    override fun onSuccess() {
        toast("Favorite was added success")
    }
}