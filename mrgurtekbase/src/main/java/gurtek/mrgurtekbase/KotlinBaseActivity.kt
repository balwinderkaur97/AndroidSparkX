package gurtek.mrgurtekbase

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.view.View
import gurtek.mrgurtekbase.listeners.KotlinBaseListener
import gurtek.mrgurtekbase.navigator.Navigator
import kotlin.reflect.KClass


/**
 * * Created by Gurtek Singh on 1/1/2018.
 * Sachtech Solution
 * gurtek@protonmail.ch
 */

open class KotlinBaseActivity(@IdRes private val container: Int = 0) : AppCompatActivity(), KotlinBaseListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBackStackListener()

    }

    private fun initBackStackListener() {

        with(supportFragmentManager) {
            addOnBackStackChangedListener {
                val fragment = findFragmentById(container)
                navigator.lastFragmentChanged(fragment = fragment as KotlinBaseFragment)

            }

        }

    }


    override fun getFragment(kClass: KClass<out Fragment>): Fragment? {
        val fragment = supportFragmentManager.findFragmentByTag(kClass.java.simpleName)
        return if (fragment != null) fragment as Fragment else null
    }


    override fun openAForResult(kClass: KClass<out AppCompatActivity>, bundle: Bundle, code: Int) {
        navigator.openAForResult(kClass, bundle, code)
    }


    override fun navigateToFragment(java: KClass<out Fragment>, extras: Bundle?) {
        navigateToFragment(java.java, extras, transitionView = null)
    }

    override fun addFragment(fragment: KClass<out Fragment>, extras: Bundle?, tag: String) {
        navigator.addFragment(fragment.java, tag = tag, bundle = extras)
    }


    private val manager: FragmentManager by lazy {
        supportFragmentManager
    }

    private val navigator: Navigator by lazy { Navigator(this, container) }


    fun navigateToFragment(clazz: Class<out Fragment>, bundle: Bundle? = null, transitionView: View? = null) {
        navigator.replaceFragment(clazz, bundle, transitionView)
    }

    fun switchFragment(clazz: Class<out Fragment>, bundle: Bundle? = null, transitionView: View? = null) {
        navigator.addFragment(clazz, bundle, transitionView)
    }


    override fun openA(kClass: KClass<out AppCompatActivity>, extras: Bundle?) {
        navigator.openA(kClass, extras)
    }

    override fun onBackPressed() {
        if (containsOnlyFragment()) {
            if (manager.backStackEntryCount == 1) {
                finish()
            } else {
                super.onBackPressed()
            }
        } else {
            super.onBackPressed()
        }
    }

    open fun containsOnlyFragment(): Boolean {
        return true
    }

    fun currentVisibleFragmentTag(): String? {
        return navigator.getCurrentFragmentTag()
    }

    inline fun <reified T : Fragment> getFragment(): T? {
        val fragment = supportFragmentManager.findFragmentByTag(T::class.java.simpleName)
        return if (fragment != null) fragment as T else null
    }


    override fun addChildFragment(childFragmentManager: FragmentManager, container: Int, kClass: KClass<out Fragment>) {
        navigator.addChildFragment(childFragmentManager, container, kClass)
    }

    fun bringtoFront(kClass: KClass<out KotlinBaseFragment>) {
        navigator.bringFragmentToFrontIfPresentOrCreate(kClass.java)
    }

    fun getLastAddedFragment(): KotlinBaseFragment? {
        return navigator.getLastAddedFragment()
    }

    fun <T> showDialog(clazz: Class<out KotlinBaseDialogFragment<T>>, bundle: Bundle? = Bundle()): Fragment {
        val tag = clazz.simpleName
        val ft = supportFragmentManager?.beginTransaction()
        var fragment = supportFragmentManager?.findFragmentByTag(tag)
        if (fragment != null) {
            ft?.remove(fragment)
        }
        ft?.addToBackStack(tag)

        // Create and show the dialog.
        fragment = clazz.newInstance()
        fragment.arguments = bundle
        fragment.show(ft, tag)

        return fragment
    }


}
