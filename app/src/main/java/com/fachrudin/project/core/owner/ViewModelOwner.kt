package com.fachrudin.project.core.owner

import com.fachrudin.project.core.base.BaseViewModel

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
interface ViewModelOwner<T : BaseViewModel> {
    val viewModel: T
}
