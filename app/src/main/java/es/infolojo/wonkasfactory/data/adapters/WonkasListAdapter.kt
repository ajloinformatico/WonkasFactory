package es.infolojo.wonkasfactory.data.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import es.infolojo.wonkasfactory.R
import es.infolojo.wonkasfactory.data.vo.WonkaWorkerVO
import es.infolojo.wonkasfactory.databinding.RowFragmentWonkasListBinding

class WonkasListAdapter(
    private val listener: (WonkasListAdapterAction) -> Unit,
): ListAdapter<WonkaWorkerVO, RecyclerView.ViewHolder>(WonkasListDiffUtil())

{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = RowFragmentWonkasListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return WonkaListHolder(binding)
    }


    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        when (viewHolder) {
            is WonkaListHolder -> {
                viewHolder.bind(
                    getItem(position) as WonkaWorkerVO,
                    listener
                )
            }
        }
    }

    inner class WonkaListHolder(private val binding: RowFragmentWonkasListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: WonkaWorkerVO,
            listener: (WonkasListAdapterAction) -> Unit
        ) {
            "${item.firstName} ${item.lastName}".also { binding.wonkaName.text = it }

            Glide.with(itemView.context)
                .load(item.image)
                .placeholder(R.drawable.ic_baseline_camera_alt_24)
                .into(binding.wonkaImage)

            binding.profession.text = item.profession
            binding.email.text = item.email
            binding.age.text = item.age
            binding.height.text = item.height

            binding.root.setOnClickListener {
                listener(
                    WonkasListAdapterAction.DetailAction(
                        item.id
                    )
                )
            }

            binding.root.setOnLongClickListener {
                listener(
                    WonkasListAdapterAction.RemoveAction(
                        item.id
                    )
                )
                true
            }

        }
    }


    class WonkasListDiffUtil : DiffUtil.ItemCallback<WonkaWorkerVO>() {
        override fun areItemsTheSame(oldItem: WonkaWorkerVO, newItem: WonkaWorkerVO): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: WonkaWorkerVO, newItem: WonkaWorkerVO): Boolean =
            oldItem == newItem

    }
}