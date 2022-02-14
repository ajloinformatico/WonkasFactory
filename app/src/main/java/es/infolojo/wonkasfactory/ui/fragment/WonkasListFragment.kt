package es.infolojo.wonkasfactory.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import es.infolojo.wonkasfactory.R
import es.infolojo.wonkasfactory.data.adapters.WonkasListAdapter
import es.infolojo.wonkasfactory.data.adapters.WonkasListAdapterAction
import es.infolojo.wonkasfactory.data.adapters.WonkasListState
import es.infolojo.wonkasfactory.data.bo.ORDER_STATE
import es.infolojo.wonkasfactory.data.vo.WonkaWorkerVO
import es.infolojo.wonkasfactory.databinding.FragmentWonkasListBinding
import es.infolojo.wonkasfactory.ui.utis.Utils
import es.infolojo.wonkasfactory.ui.viewmodel.WonkasListViewModel

@AndroidEntryPoint
class WonkasListFragment : Fragment() {

    private lateinit var binding: FragmentWonkasListBinding
    private lateinit var navController: NavController
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
        navController = findNavController()
        binding = FragmentWonkasListBinding.bind(
            inflater.inflate(
                R.layout.fragment_wonkas_list,
                container,
                false
            )
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        initViewModel()
        initToolbar()
    }

    private fun initRecycler() {
        binding.recycler.adapter = this.adapter
    }

    private fun initViewModel() {
        viewModel.wonkasListState.observe(viewLifecycleOwner) { state ->
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
                navController.navigate(
                    WonkasListFragmentDirections.actionWonkasListFragmentToWonkaDetailFrament(
                        actions.id
                    )
                )
            }
            is WonkasListAdapterAction.RemoveAction -> {
                showRemoveDialog(actions.id)
            }
        }
    }

    private fun initToolbar() {
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.filter_by_name -> {
                    viewModel.orderByName(ORDER_STATE.BY_NAME)
                    true
                }

                R.id.filter_by_age -> {
                    viewModel.orderByName(ORDER_STATE.BY_AGE)
                    true
                }

                R.id.filter_by_height -> {
                    viewModel.orderByName(ORDER_STATE.BY_HEIGHT)
                    true
                }

                R.id.reload -> {
                    viewModel.init()
                    true
                }

                else -> {
                    false
                }
            }
        }
    }

    fun showRemoveDialog(id: String) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())

        builder.let {
            it.setMessage("Are you sure you want to delete?")
                .setTitle("Delete worker")
                .setPositiveButton("yes") { _, _ ->
                    viewModel.deleteWonka(id)
                }
                .setNegativeButton("No") { _, _ ->
                    //no-op
                }
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

}