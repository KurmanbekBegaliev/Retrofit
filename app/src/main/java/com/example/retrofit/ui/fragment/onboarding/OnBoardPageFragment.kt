package com.example.retrofit.ui.fragment.onboarding

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.retrofit.ui.BundleKeys
import com.example.retrofit.databinding.FragmentOnBoardPageBinding


class OnBoardPageFragment : Fragment() {

    private var _binding: FragmentOnBoardPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnBoardPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
    }

    private fun initViews() {

        binding.dotsIndicator.attachTo(
            (parentFragment as OnBoardListener).getViewPager()
        )

        val data = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getSerializable(BundleKeys.ARGS_KEY, BoardModel::class.java)
        } else {
            arguments?.getSerializable(BundleKeys.ARGS_KEY) as BoardModel
        }
        val isLast = arguments?.getBoolean(BundleKeys.IS_LAST_ARG)

        if (data != null) {
            binding.ivBoardPicture.setImageResource(data.imgResId)
            binding.tvBigText.text = data.title
            binding.tvSmallText.text = data.description
        }

        if (isLast == true) {
            binding.dotsIndicator.visibility = View.GONE
            binding.btnGetStarted.visibility = View.VISIBLE
        } else {
            binding.btnGetStarted.visibility = View.GONE
            binding.dotsIndicator.visibility = View.VISIBLE
        }
    }

    private fun initListeners() {
        binding.btnGetStarted.setOnClickListener {
            (parentFragment as OnBoardListener).onGetStartClicked()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

interface OnBoardListener {
    fun onGetStartClicked()

    fun getViewPager(): ViewPager2
}