package com.obscura.llc.obscuraproject.presentation.activities.location

import android.location.Address

interface LocationChooserSearchCallback {
    fun searchAddresses(addresses: List<Address>)
}

interface LocationChooserPlaceCallback {
    fun onAddressClicked(address: Address)
}