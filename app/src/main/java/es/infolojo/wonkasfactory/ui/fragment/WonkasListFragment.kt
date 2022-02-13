package es.infolojo.wonkasfactory.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import es.infolojo.wonkasfactory.R
import es.infolojo.wonkasfactory.data.adapters.WonkasListAdapter
import es.infolojo.wonkasfactory.data.adapters.WonkasListAdapterAction
import es.infolojo.wonkasfactory.data.adapters.WonkasListState
import es.infolojo.wonkasfactory.data.vo.WonkaWorkerVO
import es.infolojo.wonkasfactory.databinding.FragmentWonkasListBinding
import es.infolojo.wonkasfactory.ui.utis.Utils
import es.infolojo.wonkasfactory.ui.viewmodel.WonkasListViewModel

@AndroidEntryPoint
class WonkasListFragment : Fragment() {

    private lateinit var binding: FragmentWonkasListBinding
    private val viewModel: WonkasListViewModel by viewModels()
    private val adapter by lazy {
        WonkasListAdapter(::adapterListener)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWonkasListBinding.bind(inflater.inflate(R.layout.fragment_wonkas_list, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        initViewModel()
    }

    private fun initRecycler() {
        binding.recycler.adapter = this.adapter
    }

    private fun initViewModel() {
        viewModel.wonkasListState.observe(viewLifecycleOwner) {state ->
            when (state) {
                WonkasListState.Error -> {
                    showLoading(false)
                    //TODO ERROR SCREEN
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
        viewModel.init()
    }

    private fun showLoading(boolean: Boolean) {
        if (boolean) {
            binding.spinner.root.visibility = View.VISIBLE
        } else {
            binding.spinner.root.visibility = View.GONE
        }
    }

    private fun handleRender(wonkas: List<WonkaWorkerVO>) {
        adapter.submitList(wonkas)
    }

    private fun adapterListener(actions: WonkasListAdapterAction) {
        when (actions) {
            is WonkasListAdapterAction.DetailAction -> {
                Utils.showToast(requireContext(), "Navigate to detail", Toast.LENGTH_SHORT)
            }
        }
    }
}