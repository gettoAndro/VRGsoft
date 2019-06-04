package com.getto.vrgsoft.ui.emailed.description

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import com.bumptech.glide.Glide
import com.getto.vrgsoft.R
import com.getto.vrgsoft.data.nyt.service.Results
import com.getto.vrgsoft.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_emailed_details.*
import javax.inject.Inject
import android.graphics.Bitmap
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable
import java.io.File
import java.io.FileOutputStream
import android.content.ContextWrapper
import android.content.Context
import android.util.Log
import com.getto.vrgsoft.util.ImageSaver
import java.io.IOException


class EmailedDetailsActivity : BaseActivity(), EmailedDetailsContract.View {


    @Inject
    lateinit var presenter: EmailedDetailsContract.Presenter<EmailedDetailsContract.View>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emailed_details)
        activityComponent.inject(this)
        presenter.bindView(this)
        val results = intent.extras.getParcelable<Results>("emailed")
        Glide.with(this).load(results.media[0].metadata?.get(2)?.url).into(img_emailed_desc)
        title_emailed_desc.text = results.title
        date_emailed_desc.text = results.published_date
        description_emailed.text = results.abstract
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
                presenter.insertEmailed(results)
                val draw = img_emailed_desc.drawable as GlideBitmapDrawable
                val bitmap = draw.bitmap
                ImageSaver(this).
                    setFileName("${results.id}.jpg").
                    setDirectoryName("images").
                    save(bitmap)
            }
        }
    }

    private fun saveImageInternal(results: Results){
        val cw = ContextWrapper(applicationContext)
        val draw = img_emailed_desc.drawable as GlideBitmapDrawable
        val bitmap = draw.bitmap
        // path to /data/data/yourapp/app_data/imageDir
        val directory = cw.getDir("imageDir", Context.MODE_PRIVATE)
        // Create imageDir
        val mypath = File(directory, "${results.id}.jpg")

        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(mypath)
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                fos!!.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
        Log.d("IMAGE", "internal path = " + directory.absolutePath)
    }


    private fun saveImage(results: Results){
        val draw = img_emailed_desc.drawable as GlideBitmapDrawable
        val bitmap = draw.bitmap

        var outStream: FileOutputStream? = null
        val sdCard = Environment.getExternalStorageDirectory()
        val dir = File(sdCard.absolutePath + "/")
        if (!dir.exists())
            dir.mkdirs()

        val fileName = String.format("%d.jpg",results.id)
        val outFile = File(dir, fileName)
        outStream = FileOutputStream(outFile)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream)
        outStream!!.flush()
        outStream!!.close()
    }

}