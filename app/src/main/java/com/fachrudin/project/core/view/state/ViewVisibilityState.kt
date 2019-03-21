package com.fachrudin.project.core.view.state

import android.view.View
import com.fachrudin.project.core.view.ViewState

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
enum class ViewVisibilityState(val visibility: Int) : ViewState {
    VISIBLE(View.VISIBLE),
    HIDDEN(View.INVISIBLE),
    NOT_VISIBLE(View.GONE)
}
