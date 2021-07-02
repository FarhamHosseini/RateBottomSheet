package com.apachat.rate.bottomsheet.core

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import com.apachat.rate.bottomsheet.core.abstract.BaseRateBottomSheet
import kotlinx.android.synthetic.main.rate_bottom_sheet_layout.*

class AskRateBottomSheet(
  private val listener: ActionListener? = null
) : BaseRateBottomSheet() {
  interface ActionListener : RateBottomSheet.ActionListener {
    fun onDislikeClickListener() {}
  }

  companion object {
    internal fun show(manager: FragmentManager, listener: ActionListener? = null) {
      AskRateBottomSheet(listener).show(manager, "askRateBottomSheet")
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    btnRateBottomSheetLater.visibility = View.GONE
    textRateBottomSheetTitle.text = getString(R.string.rate_popup_ask_title)
    textRateBottomSheetMessage.text = getString(R.string.rate_popup_ask_message)
    btnRateBottomSheetNo.text = getString(R.string.rate_popup_ask_no)
    btnRateBottomSheetOk.text = getString(R.string.rate_popup_ask_ok)

    btnRateBottomSheetOk.setOnClickListener {
      activity?.run { RateBottomSheet.show(supportFragmentManager, listener) }
      dismiss()
    }

    btnRateBottomSheetNo.setOnClickListener {
      defaultBtnNoClickAction(it)
      listener?.onDislikeClickListener()
    }
  }
}