package com.example.customview.Practices

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import androidx.annotation.LayoutRes
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.example.customview.R

class PageFragment : Fragment() {
    @LayoutRes
    var practiceLayoutRes = 0

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_page, container, false)
        val practiceStub: ViewStub = view.findViewById<View>(R.id.practiceStub) as ViewStub
        practiceStub.layoutResource = practiceLayoutRes
        practiceStub.inflate()
        return view
    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args: Bundle? = getArguments()
        if (args != null) {
            practiceLayoutRes = args.getInt("practiceLayoutRes")
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(
            @LayoutRes practiceLayoutRes: Int
        ): PageFragment {
            val fragment = PageFragment()
            val args = Bundle()
            args.putInt("practiceLayoutRes", practiceLayoutRes)
            fragment.arguments = args
            return fragment
        }
    }
}