package com.example.retrofit.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.retrofit.CalculateModel
import com.example.retrofit.databinding.FragmentResultBinding


@Suppress("DEPRECATION")
class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val model = arguments?.getSerializable(InputFragment.KEY_FOR_BUNDLE) as CalculateModel

        binding.tvFirstName.text = model.firstName
        binding.tvSecondName.text = model.secondName
        val str = "${model.percentage}%"
        binding.tvResult.text = str

        binding.btnTryAgain.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}