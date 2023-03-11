package com.example.retrofit.ui.fragment.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.retrofit.ActivityViewModel
import com.example.retrofit.R
import com.example.retrofit.databinding.FragmentOnBoardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardFragment : Fragment(), OnBoardListener {

    private var _binding: FragmentOnBoardBinding? = null
    private val binding get() = _binding!!

    private var adapter : OnBoardAdapter? = null
    private lateinit var viewPager: ViewPager2
    private val viewModel: ActivityViewModel by viewModels()


    private val boardModels = listOf(
        BoardModel(
            imgResId = R.drawable.ob1,
            title = "Have a good time",
            description = "You should take the time to help those\n who need you"
        ),
        BoardModel(
            imgResId = R.drawable.ob2,
            title = "Cherishing love",
            description = "It is now no possible for\n you to cherish love"
        ),
        BoardModel(
            imgResId = R.drawable.ob3,
            title = "Have a breakup?",
            description = "We have made the correction for you\n don't worry\n" +
                    " Maybe someone is waiting for you!"
        ),
        BoardModel(
            imgResId = R.drawable.ob4,
            title = "",
            description = "It's Funs and Many more"
        ),
    )


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnBoardBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        adapter = OnBoardAdapter(
            childFragmentManager,
            lifecycle,
            boardModels
        )

        viewPager = binding.viewPager
        viewPager.adapter = adapter
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onGetStartClicked() {
        viewModel.setHaveSeenOnBoarding()
        findNavController().navigateUp()

    }

    override fun getViewPager(): ViewPager2 {
        return viewPager
    }
}