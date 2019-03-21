package com.fachrudin.project.core.view.state

import com.fachrudin.project.core.view.ViewState

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
enum class ViewContentState : ViewState {
    INIT,
    LOADING_CONTENT,
    CONTENT_LOADED,
    RELOADING_CONTENT
}
