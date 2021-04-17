package com.detsimov.leakchecker.ui_android.features.settings.master

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.text.method.MovementMethod
import android.view.Gravity
import android.view.View
import android.widget.PopupMenu
import by.kirich1409.viewbindingdelegate.viewBinding
import com.detsimov.core_ui.fragments.BaseFragment
import com.detsimov.core_ui.fragments.toast
import com.detsimov.leakchecker.ui_android.BuildConfig
import com.detsimov.leakchecker.ui_android.R
import com.detsimov.leakchecker.ui_android.databinding.FragmentSettingsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsMasterFragment : BaseFragment<SettingsMasterViewModel>(R.layout.fragment_settings) {

    override val viewModel: SettingsMasterViewModel by viewModel()

    private val viewBinding by viewBinding(FragmentSettingsBinding::bind)

    private val clipBoardManager by lazy {
        requireContext().getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpConsent()
        setUpVersion()
        with(viewModel) {
            token.observe(viewLifecycleOwner) {
                handleToken(it)
            }
            copyClipData.observe(viewLifecycleOwner) {
                copyClipData(it)
            }
        }
    }

    private fun copyClipData(clipData: ClipData) {
        clipBoardManager.setPrimaryClip(clipData)
        for (item in 0 until clipData.itemCount) {
            toast("Скопировано: ${clipData.getItemAt(item).text}")
        }
    }

    private fun handleToken(token: String? = null) {
        viewBinding.apply {
            if (token != null) tvDataToken.text = token
            else tvDataToken.setText(R.string.settings_master_error_token)
            containerToken.apply {
                if (token == null) setOnLongClickListener(null)
                else setOnLongClickListener {
                    showTokenPopupMenu(it)
                    true
                }
            }
        }
    }

    private fun setUpVersion() {
        viewBinding.tvDataAbout.apply {
            text = getString(R.string.settings_master_app_info, BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE)
        }
    }

    private fun setUpConsent() {
        viewBinding.tvConsent.apply {
            text = Html.fromHtml(getString(R.string.settings_master_consent))
            movementMethod = LinkMovementMethod.getInstance()
        }
    }

    private fun showTokenPopupMenu(anchorView: View) {
        PopupMenu(context, anchorView, Gravity.END).apply {
            inflate(R.menu.menu_token)
            setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.btnCopy -> {
                        val clipData = ClipData.newPlainText(
                            "LeakChecker token",
                            viewBinding.tvDataToken.text.toString()
                        )
                        viewModel.onCopyText(clipData)
                        true
                    }
                    else -> false
                }
            }
        }.show()
    }

    companion object {

        fun create() = SettingsMasterFragment()
    }
}