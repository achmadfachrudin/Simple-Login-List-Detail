@file:JvmName("Toaster")

package com.fachrudin.project.core.common.extensions

import android.content.Context
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */

fun Fragment.showToast(@StringRes stringResource: Int) = context?.showToast(stringResource)

fun Fragment.showToast(message: String) = context?.showToast(message)

fun Context.showToast(@StringRes stringResource: Int) = showToast(getString(stringResource))

fun Context.showToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Fragment.showLongToast(@StringRes stringResource: Int) = context?.showLongToast(stringResource)

fun Fragment.showLongToast(message: String) = context?.showLongToast(message)

fun Context.showLongToast(@StringRes stringResource: Int) = showLongToast(getString(stringResource))

fun Context.showLongToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()

fun Context.showSnackbar(view: View, message: String) = Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()

fun Context.showSnackbar(view: View, @StringRes stringResource: Int) = Snackbar.make(view, stringResource, Snackbar.LENGTH_SHORT).show()

fun Fragment.showSnackbar(view: View, @StringRes stringResource: Int) = context?.showSnackbar(view, stringResource)

fun Fragment.showSnackbar(view: View, message: String) = context?.showSnackbar(view, message)

fun Context.showLongSnackbar(view: View, message: String) = Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()

fun Context.showLongSnackbar(view: View, @StringRes stringResource: Int) = Snackbar.make(view, stringResource, Snackbar.LENGTH_LONG).show()

fun Fragment.showLongSnackbar(view: View, @StringRes stringResource: Int) = context?.showLongSnackbar(view, stringResource)

fun Fragment.showLongSnackbar(view: View, message: String) = context?.showLongSnackbar(view, message)