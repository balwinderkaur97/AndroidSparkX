package gurtek.mrgurtekbase.adapter

import android.support.annotation.LayoutRes
import android.view.View

/**
 * * Created by Gurtek Singh on 1/31/2018.
 * Sachtech Solution
 * gurtek@protonmail.ch
 */
abstract class ItemsBaseAdapter<T>(@LayoutRes layout: Int) : BaseAdapter<BaseViewHolder, T>(layout, BaseViewHolder::class) {

    override fun onViewCreated(view: View?, viewType: Int) {

    }

}