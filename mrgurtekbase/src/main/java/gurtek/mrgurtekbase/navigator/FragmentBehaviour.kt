package gurtek.mrgurtekbase.navigator

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction

/**
 * * Created by Gurtek Singh on 1/31/2018.
 * Sachtech Solution
 * gurtek@protonmail.ch
 */
abstract class FragmentBehaviour {
    abstract fun execute(transaction: FragmentTransaction, fragment: Fragment?, tag: String?)
}