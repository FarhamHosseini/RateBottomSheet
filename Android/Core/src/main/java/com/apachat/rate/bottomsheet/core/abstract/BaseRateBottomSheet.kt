package com.apachat.rate.bottomsheet.core.abstract

import android.content.Context
import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.apachat.rate.bottomsheet.core.R
import com.apachat.rate.bottomsheet.core.RateBottomSheetManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.rate_bottom_sheet_layout.*

abstract class BaseRateBottomSheet : BottomSheetDialogFragment() {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    dialog?.setOnShowListener { dialog ->
      (dialog as? BottomSheetDialog)?.also {
        it.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
          ?.also { bottomSheetInternal ->
            BottomSheetBehavior.from(bottomSheetInternal).state =
              BottomSheetBehavior.STATE_EXPANDED
          }
      }
    }
    return inflater.inflate(R.layout.rate_bottom_sheet_layout, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    btnRateBottomSheetCancel.visibility =
      if (RateBottomSheetManager.showCloseButtonIcon) View.VISIBLE else View.GONE

    context?.also {
      btnRateBottomSheetOk.backgroundTintList =
        ColorStateList.valueOf(getThemeAccentColor(it))
    }

    btnRateBottomSheetCancel.setOnClickListener { dismiss() }
    btnRateBottomSheetNo.setOnClickListener { defaultBtnNoClickAction(it) }
    btnRateBottomSheetLater.setOnClickListener { defaultBtnLaterClickAction(it) }
  }

  protected fun defaultBtnNoClickAction(view: View) {
    RateBottomSheetManager(view.context).disableAgreeShowDialog()
    dismiss()
  }

  private fun defaultBtnLaterClickAction(view: View) {
    RateBottomSheetManager(view.context).setRemindInterval()
    dismiss()
  }

  private fun getThemeAccentColor(context: Context): Int {
    val colorAttr = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      android.R.attr.colorAccent
    } else {
      context.resources.getIdentifier("colorAccent", "attr", context.packageName)
    }
    val outValue = TypedValue()
    context.theme.resolveAttribute(colorAttr, outValue, true)
    return outValue.data
  }
}