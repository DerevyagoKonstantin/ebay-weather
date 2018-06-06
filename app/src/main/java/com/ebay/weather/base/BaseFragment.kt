package com.ebay.weather.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein


abstract class BaseFragment : Fragment(), KodeinAware {

    private val parentKodein by closestKodein()
    override val kodein: Kodein by lazy {
        Kodein {
            extend(parentKodein)
            import(provideInjections())
        }
    }

    protected open fun provideInjections() = Kodein.Module {
    }

    abstract val viewId: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(viewId, container, false)
        init(view)
        bind(view)
        return view
    }

    protected open fun init(view: View) {
    }

    protected open fun bind(view: View) {
    }
}