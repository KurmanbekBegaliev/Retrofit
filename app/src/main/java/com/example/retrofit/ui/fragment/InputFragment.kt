package com.example.retrofit.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.retrofit.ActivityViewModel
import com.example.retrofit.ui.BundleKeys.KEY_FOR_BUNDLE
import com.example.retrofit.CalculateModel
import com.example.retrofit.R
import com.example.retrofit.databinding.FragmentInputBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class InputFragment : Fragment() {

    private var _binding: FragmentInputBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ActivityViewModel by viewModels()

//    @Inject
//    @HiltModule.StringBuilderOne
//    lateinit var str : java.lang.StringBuilder



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInputBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClick()

//        Log.d("TAG", "InputFragment: $str")
//        str.append("Good Luck")
//        Log.d("TAG", "InputFragment: $str")

    }

    private fun onClick() = with(binding) {
        binding.btnCalculate.setOnClickListener {
            viewModel.makeRequest(
                firstName = edtFirstName.text.toString(),
                secondName = edtSecondName.text.toString()
            ).observe(viewLifecycleOwner) {
                it?.let { openResult(it) }
            }
        }

    }

    private fun openResult(model: CalculateModel) {
        binding.edtFirstName.setText("")
        binding.edtSecondName.setText("")
        val bundle = Bundle()
        bundle.putSerializable(KEY_FOR_BUNDLE, model)
        findNavController().navigate(R.id.resultFragment, bundle)
    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }


}