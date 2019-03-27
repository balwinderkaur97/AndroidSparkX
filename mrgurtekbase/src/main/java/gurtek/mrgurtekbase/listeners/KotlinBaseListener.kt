package gurtek.mrgurtekbase.listeners

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import kotlin.reflect.KClass

/**
 * * Created by Gurtek Singh on 1/31/2018.
 * Sachtech Solution
 * gurtek@protonmail.ch
 */
interface KotlinBaseListener {
    fun openA(kClass: KClass<out AppCompatActivity>, extras: Bundle? = Bundle())
    fun navigateToFragment(java: KClass<out Fragment>, extras: Bundle? = Bundle())
    fun addFragment(fragment: KClass<out Fragment>, extras: Bundle? = Bundle(), tag: String = "")
    fun addChildFragment(childFragmentManager: FragmentManager, container: Int, kClass: KClass<out Fragment>)
    fun openAForResult(kClass: KClass<out AppCompatActivity>, bundle: Bundle, code: Int)
    fun getFragment(kClass: KClass<out Fragment>): Fragment?

}