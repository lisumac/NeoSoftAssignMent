package com.assignment.neosoftassignment.view.bottomSheet


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import com.assignment.neosoftassignment.databinding.BottomSheetLayoutBinding
import com.assignment.neosoftassignment.onClickListner.BottomSheetSortListner
import com.assignment.neosoftassignment.onClickListner.OnClickListner
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class SortingBottomSheetDialog : BottomSheetDialogFragment(), BottomSheetSortListner {
    lateinit var binding: BottomSheetLayoutBinding
    lateinit var getHandlerData_: OnClickListner
    override fun onCreateView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetLayoutBinding.inflate(inflater, container, false)
        binding.handler = this
        onclickListner()
        return binding.root

    }

    private fun onclickListner() {
        binding.tvAscendingOrder.setOnClickListener {
            getHandlerData_.getAscendingOrder()
        }
        binding.tvDescendingOrder.setOnClickListener {
            getHandlerData_.getDescendingOrder()

        }
    }

    override fun ascendingOrder() {
        getHandlerData_.getAscendingOrder()
       // dialog?.dismiss()
        dismiss();

    }

    override fun descendingOrder() {
        getHandlerData_.getDescendingOrder()
        //dialog?.dismiss()
        dismiss();
    }
}