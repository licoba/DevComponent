package dev.core.lib.base.viewholder

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * detail: Base ViewDataBinding ViewHolder
 * @author Ttt
 */
class BaseVDBHolder<VDB : ViewDataBinding> : RecyclerView.ViewHolder {

    lateinit var binding: VDB

    constructor(itemView: View) : super(itemView)

    constructor(
        binding: VDB
    ) : super(binding.root) {
        this.binding = binding
    }
}