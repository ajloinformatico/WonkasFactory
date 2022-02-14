package es.infolojo.wonkasfactory.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import es.infolojo.wonkasfactory.R
import es.infolojo.wonkasfactory.data.Mappers.stringColorToResource
import es.infolojo.wonkasfactory.data.adapters.WonkaDetailState
import es.infolojo.wonkasfactory.data.vo.WonkaWorkerVO
import es.infolojo.wonkasfactory.databinding.FragmentWonkaDetailFramentBinding
import es.infolojo.wonkasfactory.ui.viewmodel.WonkaDetailViewModel

private const val ID = "ID"

@AndroidEntryPoint
class WonkaDetailFrament : Fragment() {

    private val args: WonkaDetailFramentArgs by navArgs()

    private val viewModel: WonkaDetailViewModel by viewModels()

    private lateinit var binding: FragmentWonkaDetailFramentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentWonkaDetailFramentBinding.bind(
            inflater.inflate(
                R.layout.fragment_wonka_detail_frament,
                container,
                false
            )
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

    private fun handleRender(wonka: WonkaWorkerVO) {

        var expanedText = false

        initToolbar(wonka)

        Glide.with(requireContext())
            .load(wonka.image)
            .placeholder(R.drawable.ic_baseline_camera_alt_24)
            .into(binding.mainToolbarImageView)

        binding.detailEmail.text = wonka.email

        "${binding.colorPlacheholder.text} ${wonka.color}".also { binding.colorPlacheholder.text = it }

        binding.favoriteColor.background = ContextCompat.getDrawable(
            requireContext(),
            wonka.color.stringColorToResource(
                requireContext()
            )
        )

        binding.genre.text = wonka.genre

        binding.description.text = HtmlCompat.fromHtml(
            wonka.description,
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )

        "${wonka.quota.substring(0, 300)}...".also { binding.quote.text = it }

        binding.expandQuote.setOnClickListener {
            if (expanedText.not()) {

                binding.quote.text = wonka.quota
                binding.expandQuote.text = this.getString(R.string.un_expaned_quota)
                expanedText = true

            } else {
                "${wonka.quota.substring(0, 300)}...".also { binding.quote.text = it }
                binding.expandQuote.text = this.getText(R.string.expand_quota)
                expanedText = false
            }
        }

        binding.talkBtn.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SEND)
            emailIntent.type = "text/plain"
            emailIntent.data = Uri.parse("mailto: ${wonka.email}")
            emailIntent.putExtra(Intent.EXTRA_EMAIL, "test")
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "We inform you about ...")
            startActivity(emailIntent)
        }
    }

    private fun initToolbar(wonka: WonkaWorkerVO) {
        binding.mainToolbar.title = "${wonka.firstName} ${wonka.lastName}"
        binding.mainToolbar.subtitle = wonka.profession
    }

    private fun initViewModel() {
        viewModel.wonkaDetailState.observe(viewLifecycleOwner) { state ->
            when (state) {
                WonkaDetailState.Error -> {
                    showLoading(false)
                    showError(true)
                }
                WonkaDetailState.Loading -> {
                    showLoading(true)
                    showError(false)
                }
                is WonkaDetailState.Render -> {
                    showLoading(false)
                    showError(false)
                    handleRender(state.wonka)
                }
            }
        }
        viewModel.init(args.id)
    }

    private fun showLoading(boolean: Boolean) {
        if (boolean) {
            binding.spinner.root.visibility = View.VISIBLE
        } else {
            binding.spinner.root.visibility = View.GONE
        }
    }

    private fun showError(boolean: Boolean) {
        if (boolean) {
            binding.errorScreen.root.visibility = View.VISIBLE
        } else {
            binding.errorScreen.root.visibility = View.GONE
        }
    }
}