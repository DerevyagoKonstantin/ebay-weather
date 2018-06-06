package com.ebay.weather.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.android.retainedKodein


abstract class BaseActivity : AppCompatActivity(), KodeinAware {

    private val parentKodein by closestKodein()
    override val kodein by retainedKodein {
        extend(parentKodein)
        import(provideInjections())
    }

    protected open fun provideInjections() = Kodein.Module {
    }

    abstract val viewId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewId)
        init()
        bind()
    }

    protected open fun init() {
    }

    protected open fun bind() {
    }
}