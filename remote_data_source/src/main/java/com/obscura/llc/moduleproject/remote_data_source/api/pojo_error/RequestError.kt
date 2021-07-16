package com.obscura.llc.moduleproject.remote_data_source.api.pojo_error

import androidx.annotation.Keep

open class RequestError(val error: Throwable)

@Keep
class ConnectionError(error: Throwable) : RequestError(error)