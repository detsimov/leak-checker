package com.detsimov.leakchecker.ui_android.features.trackdata.creator.view

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import by.kirich1409.viewbindingdelegate.viewBinding
import com.detsimov.core_ui.fragments.BaseDialogFragment
import com.detsimov.core_ui.fragments.requireListener
import com.detsimov.core_ui.fragments.toast
import com.detsimov.core_ui.view.setItemSelectedCallback
import com.detsimov.leakchecker.domain.models.TrackDataModel
import com.detsimov.leakchecker.domain.repositories.EmailRuleException
import com.detsimov.leakchecker.domain.repositories.PhoneRuleException
import com.detsimov.leakchecker.ui_android.R
import com.detsimov.leakchecker.ui_android.databinding.DialogTrackDataCreateBinding
import com.detsimov.leakchecker.ui_android.features.trackdata.creator.models.TrackDataCreateUi
import com.detsimov.leakchecker.ui_android.features.trackdata.creator.viewmodel.TrackDataCreateViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.slots.PredefinedSlots
import ru.tinkoff.decoro.watchers.MaskFormatWatcher

class TrackDataCreateDialog :
    BaseDialogFragment<TrackDataCreateViewModel>(R.layout.dialog_track_data_create) {


    interface Owner {
        fun onTrackDataCreated(trackDataCreateUi: TrackDataCreateUi)
    }

    override val viewModel: TrackDataCreateViewModel by viewModel()

    private val viewBinding by viewBinding(DialogTrackDataCreateBinding::bind)

    private val spinnerAdapter by lazy {
        ArrayAdapter(
            requireContext(),
            R.layout.spinner_item,
            TrackDataModel.TypeValue.values()
        ).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
    }

    private val owner: Owner by lazy {
        requireListener()
    }

    private val phoneWatcher =
        MaskFormatWatcher(MaskImpl.createTerminated(PredefinedSlots.RUS_PHONE_NUMBER))


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpSpinner()
        setUpButtons()

        viewModel.apply {
            closePressed.observe(viewLifecycleOwner) {
                dismiss()
            }
            onSaveTrackData.observe(viewLifecycleOwner) {
                owner.onTrackDataCreated(it)
            }
        }
    }


    private fun setUpSpinner() {
        viewBinding.apply {
            spinnerType.apply {
                adapter = spinnerAdapter
                setItemSelectedCallback { position ->
                    spinnerAdapter.getItem(position)?.let {
                        when (it) {
                            TrackDataModel.TypeValue.EMAIL -> {
                                phoneWatcher.removeFromTextView()
                                inputData.text.clear()
                                inputData.hint = "example@gmail.com"
                            }
                            TrackDataModel.TypeValue.PHONE -> {
                                toast(context.getString(R.string.track_data_create_warning_phone))
                                phoneWatcher.installOn(inputData)
                                inputData.text.clear()
                                inputData.hint = "+7 (000) 000 00"
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onShowError(error: Throwable) {
        toast(
            when (error) {
                is EmailRuleException -> getString(R.string.track_data_create_error_email)
                is PhoneRuleException -> getString(R.string.track_data_create_error_phone)
                else -> error.toString()
            }
        )
    }

    private fun setUpButtons() {
        viewBinding.apply {
            btnClose.setOnClickListener {
                viewModel.onClosePressed()
            }
            btnSend.setOnClickListener {
                viewModel.onCheckTrackData(formAnData())
            }
        }
    }

    private fun formAnData(): TrackDataCreateUi =
        viewBinding.run {
            TrackDataCreateUi(
                value = inputData.text.toString(),
                type = spinnerAdapter.getItem(spinnerType.selectedItemPosition)
                    ?: error("Can't send trackData with nullable type")
            )
        }


    companion object {

        val TAG = "TrackDataCreateDialog"

        fun create() = TrackDataCreateDialog()
    }


}

