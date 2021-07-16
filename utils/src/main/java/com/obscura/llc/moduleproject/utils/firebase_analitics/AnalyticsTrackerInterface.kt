package com.obscura.llc.moduleproject.utils.firebase_analitics



interface AnalyticsTrackerInterface {
    fun trackEvent(event: AnalyticsEvent, additionalInfo: Map<String, String>? = null)
}
