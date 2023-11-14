package com.example.customview.Practices

import android.os.Bundle
import android.view.Menu
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.customview.R
import com.google.android.material.tabs.TabLayout
import java.util.*

class AnimationMainActivity : AppCompatActivity() {
    var tabLayout: TabLayout? = null
    var pager: ViewPager? = null
    var pageModels: MutableList<PageModel> = ArrayList()
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_example)
        pager = findViewById<ViewPager>(R.id.pager)
        pager?.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                val pageModel = pageModels[position]
                return PageFragment.newInstance(
                    pageModel.practiceLayoutRes
                )
            }

            //            val count: Int
            //                get() = pageModels.size

            override fun getCount(): Int {
                return pageModels.size
            }

            override fun getPageTitle(position: Int): CharSequence {
                return getString(pageModels[position].titleRes)
            }
        }
        tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        tabLayout?.setupWithViewPager(pager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    inner class PageModel internal constructor(
        @field:StringRes @param:StringRes var titleRes: Int,
        @field:LayoutRes @param:LayoutRes var practiceLayoutRes: Int
    )

    init {
        pageModels.add(PageModel(R.string.title_draw, R.layout.practice_draw))
        pageModels.add(PageModel(R.string.title_translation, R.layout.practice_translation))
        pageModels.add(PageModel(R.string.title_rotation, R.layout.practice_rotation))
        pageModels.add(PageModel(R.string.title_scale, R.layout.practice_scale))
        pageModels.add(PageModel(R.string.title_alpha, R.layout.practice_alpha))
        pageModels.add(PageModel(R.string.title_multi_properties, R.layout.practice_multi_properties))
        pageModels.add(PageModel(R.string.title_duration, R.layout.practice_duration))
        pageModels.add(PageModel(R.string.title_interpolator, R.layout.practice_interpolator))
        pageModels.add(PageModel(R.string.title_object_animator, R.layout.practice_object_animator))
        pageModels.add(PageModel(R.string.title_layout_already,R.layout.practice_layout_already))
    }
}