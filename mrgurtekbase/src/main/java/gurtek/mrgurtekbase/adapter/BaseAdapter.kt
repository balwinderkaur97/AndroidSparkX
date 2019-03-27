package gurtek.mrgurtekbase.adapter

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import gurtek.mrgurtekbase.inflate
import kotlin.reflect.KClass

/**
 * * Created by Gurtek Singh on 1/1/2018.
 * Sachtech Solution
 * gurtek@protonmail.ch
 */
abstract class BaseAdapter<VH : RecyclerView.ViewHolder, T>(
        @LayoutRes private val view: Int,
        private val holderclass: KClass<VH>,
        protected val list: ArrayList<T> = arrayListOf()) : RecyclerView.Adapter<VH>() {

    var itemClickCallback: RecycleItemViewCallback<T>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return getviewholder(parent, view, viewType)
    }

    protected open fun getviewholder(parent: ViewGroup?, vieww: Int, viewType: Int): VH {
        val view = parent?.inflate(vieww)
        val constructor = holderclass.java.getConstructor(View::class.java)
        onViewCreated(view, viewType)
        return constructor.newInstance(view)

    }


    override fun getItemCount() = list.size


    abstract override fun onBindViewHolder(holder: VH, position: Int)
    abstract fun onViewCreated(view: View?, viewType: Int)

    fun addtoList(list: List<T>?) {
        if (list != null) {
            val previousRange = list.size

            this.list.addAll(list)

            val newRange = list.size
            notifyItemChanged(previousRange, newRange)
        }
    }

    fun addNewList(list: ArrayList<T>?) {
        if (list != null) {
            this@BaseAdapter.list.clear()
            this@BaseAdapter.list.addAll(list)
            notifyDataSetChanged()
        }
    }

    fun addtoList(item: T): Int {
        list.add(item)
        val insertedAt = list.size - 1
        notifyItemInserted(insertedAt)
        return insertedAt
    }

    fun addNewList(list: Iterable<T>?) {
        if (list != null) {
            this@BaseAdapter.list.clear()
            this@BaseAdapter.list.addAll(list)
            notifyDataSetChanged()
        }
    }

    fun addMoreItems(items: List<T>?) {
        val previousRange = list.size

        items?.let { list.addAll(items) }

        val newRange = list.size

        notifyItemRangeInserted(previousRange, newRange)


    }


    fun addMoreItemsAtStart(items: List<T>) {
        val previousRange = list.size

        list.addAll(items)

        val newRange = list.size

        notifyItemRangeInserted(previousRange, newRange)


    }


    fun appendAtStart(item: T) {
        list.add(0, item)
        notifyItemInserted(0)
    }

    fun getOriginalList(): ArrayList<T> {
        return list
    }

    fun removeItem(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    fun removeAll() {
        list.clear()
        notifyDataSetChanged()
    }


}