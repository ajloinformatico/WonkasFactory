package es.infolojo.wonkasfactory.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import es.infolojo.wonkasfactory.R
import es.infolojo.wonkasfactory.data.Mappers.stringColorToResource
import es.infolojo.wonkasfactory.data.adapters.WonkaDetailState
import es.infolojo.wonkasfactory.data.vo.WonkaWorkerVO
import es.infolojo.wonkasfactory.databinding.FragmentWonkaDetailFramentBinding
import es.infolojo.wonkasfactory.ui.utis.Utils
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
        binding = FragmentWonkaDetailFramentBinding.bind(inflater.inflate(R.layout.fragment_wonka_detail_frament, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

    private fun handleRender(wonka: WonkaWorkerVO) {
        Glide.with(requireContext())
            .load(wonka.image)
            .placeholder(R.drawable.ic_baseline_camera_alt_24)
            .into(binding.mainToolbarImageView)

        binding.detailEmail.text = wonka.email

        binding.favoriteColor.setBackgroundColor(
            wonka.color.stringColorToResource(
                requireContext()
            )
        )

        binding.genre.text = wonka.genre
        binding.description.text = wonka.description
        binding.quote.text = wonka.quota

        binding.talkBtn.setOnClickListener {
            Utils.showToast(requireContext(), "Open gmail intent", Toast.LENGTH_SHORT)
        }
    }

    private fun initViewModel() {
        viewModel.wonkaDetailState.observe(viewLifecycleOwner) { state ->
            when (state) {
                WonkaDetailState.Error -> {
                    showLoading(false)
                    //TODO ERROR SCREEN
                }
                WonkaDetailState.Loading -> {
                    showLoading(true)
                }
                is WonkaDetailState.Render -> {
                    showLoading(false)
                    handleRender(state.wonka)
                }
            }
        }
        viewModel.init(args)
    }

    private fun showLoading(boolean: Boolean) {
        if (boolean) {
            binding.spinner.root.visibility = View.VISIBLE
        } else {
            binding.spinner.root.visibility = View.GONE
        }
    }
}