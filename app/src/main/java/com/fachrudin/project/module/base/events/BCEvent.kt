package com.fachrudin.project.module.base.events

import android.content.Context
import android.os.Bundle

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
data class BCEvent(val context: Context, val receiver: Receiver) {
    var bundle: Bundle? = null
}