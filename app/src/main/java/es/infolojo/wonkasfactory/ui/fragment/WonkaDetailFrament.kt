package es.infolojo.wonkasfactory.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import es.infolojo.wonkasfactory.R
import es.infolojo.wonkasfactory.databinding.FragmentWonkaDetailFramentBinding

private const val ID = "ID"

@AndroidEntryPoint
class WonkaDetailFrament : Fragment() {

    private val id: String by lazy {
        arguments?.getString(ID).orEmpty()
    }

    private lateinit var binding: FragmentWonkaDetailFramentBinding

    companion object {
        fun newInstance(
            id: String,
        ) = WonkaDetailFrament().apply {
            arguments = Bundle().apply {
                putString(ID, id)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentWonkaDetailFramentBinding.bind(inflater.inflate(R.layout.fragment_wonka_detail_frament, container, false))
        return binding.root
        initViews()
        initViewModel()
    }

    private fun initViews() {
        //TODO VIEWS
    }

    private fun initViewModel() {
        //TODO VIEWMODEL STATES
    }
}