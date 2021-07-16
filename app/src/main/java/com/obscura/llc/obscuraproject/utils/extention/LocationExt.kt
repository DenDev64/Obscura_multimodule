package com.obscura.llc.obscuraproject.utils.extention

import android.location.Location
import com.google.android.gms.maps.model.LatLng

fun Location.toLatLng(): LatLng= LatLng(this.latitude, this.longitude)