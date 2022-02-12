package es.infolojo.wonkasfactory.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import es.infolojo.wonkasfactory.R
import es.infolojo.wonkasfactory.data.adapters.WonkasListAdapter
import es.infolojo.wonkasfactory.data.adapters.WonkasListAdapterAction
import es.infolojo.wonkasfactory.data.adapters.WonkasListState
import es.infolojo.wonkasfactory.data.vo.WonkaWorkerVO
import es.infolojo.wonkasfactory.databinding.FragmentWonkasListBinding
import es.infolojo.wonkasfactory.ui.viewmodel.WonkasListViewModel

class WonkasListFragment : Fragment() {

    private lateinit var binding: FragmentWonkasListBinding
    private val viewModel: WonkasListViewModel by viewModels()
    private val adapter by lazy {
        WonkasListAdapter(::adapterListener)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentWonkasListBinding.inflate(layoutInflater)
        return binding.root

        initViewModel()
    }

    private fun initViewModel() {
        viewModel.wonkasListState.observe(viewLifecycleOwner) {state ->
            when (state) {
                WonkasListState.Error -> {
                    showLoading(false)
                    TODO("Error screen is in WIP")
                }
                WonkasListState.Loading -> {
                    showLoading(true)
                }
                is WonkasListState.Render -> {
                    showLoading(false)
                    handleRender(state.wonkas)

                }
            }
        }
    }

    private fun showLoading(boolean: Boolean) {
        if (boolean) {
            binding.spinner.root.visibility = View.VISIBLE
        } else {
            binding.spinner.root.visibility = View.GONE
        }
    }

    private fun handleRender(wonkas: List<WonkaWorkerVO>) {

    }

    private fun adapterListener(actions: WonkasListAdapterAction) {
        when (actions) {
            is WonkasListAdapterAction.DetailAction -> {
                //TODO Navigation to detail
            }
        }
    }
}