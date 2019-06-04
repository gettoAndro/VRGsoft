package com.getto.vrgsoft.data.nyt.storage

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey



@Entity(tableName = "favorite")
data class StorageFavorite(
        @PrimaryKey(autoGenerate = true) var id: Long? = null,
        var adx_keywords: String? = "",
        var subsection: String? = "",
       // val email_count: Number?,
        var count_type: String? = "",
        //  val column: Any?,
     //   val eta_id: Number?,
        var section: String? = "",
      //  val asset_id: Number?,
        var nytdsection: String? = "",
        var byline: String? = "",
        var type: String? = "",
        var title: String? = "",
        var abstract: String? = "",
        var published_date: String? = "",
        var source: String? = "",
        var updated: String? = ""
)
