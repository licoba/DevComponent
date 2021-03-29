package dev.core.lib.base.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * detail: Base ViewBinding ViewHolder
 * @author Ttt
 */
open class BaseVBHolder<VB : ViewBinding> : RecyclerView.ViewHolder {

    lateinit var binding: VB

    constructor(itemView: View) : super(itemView)

    constructor(
        binding: VB
    ) : super(binding.root) {
        this.binding = binding
    }
}