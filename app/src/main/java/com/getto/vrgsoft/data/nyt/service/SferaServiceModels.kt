package com.getto.vrgsoft.data.nyt.service

import com.google.gson.annotations.SerializedName

data class CountryInfo(
        val id: Long,
        var alpha3Code: String,
        var borders: List<String>,
        var nativeName: String
)

data class Emailed(
   val status: String?,
   val copyright: String?,
   val num_results: Number?,
   val results: List<Results>?
)

data class Results(val url: String?,
                   val adx_keywords: String?,
                   val subsection: String?,
                   val email_count: Number?,
                   val count_type: String?,
                   val column: Any?,
                   val eta_id: Number?,
                   val section: String?,
                   val id: Number?,
                   val asset_id: Number?,
                   val nytdsection: String?,
                   val byline: String?,
                   val type: String?,
                   val title: String?,
                   val abstract: String?,
                   val published_date: String?,
                   val source: String?,
                   val updated: String?,
                   val media : List<Media>,
                //   val des_facet: List<String>?,
               //    val org_facet: List<String>?,
                //   val per_facet: List<String>?,
                //   val geo_facet: List<String>?,
                   val uri: String?
)
data class Media(
    val type: String?,
    val subtype: String?,
    val caption: String?,
    val copyright: String?,
    val approved_for_syndication: Number?,
    @SerializedName("media-metadata") val metadata: List<MediaMetadata>?
)

data class MediaMetadata
(
    val url: String?,
    val format: String?,
    val height: Number?,
    val width: Number?
)