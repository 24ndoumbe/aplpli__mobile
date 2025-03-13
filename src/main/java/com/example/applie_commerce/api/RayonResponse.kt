package com.example.applie_commerce.api

import com.google.gson.annotations.SerializedName

class RayonResponse (
    @SerializedName("hydra:member") val rayons: List<Rayon>
)