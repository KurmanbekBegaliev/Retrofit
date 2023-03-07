package com.example.retrofit.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.retrofit.ActivityViewModel
import com.example.retrofit.BundleKeys.KEY_FOR_BUNDLE
import com.example.retrofit.CalculateModel
import com.example.retrofit.R
import com.example.retrofit.databinding.FragmentInputBinding


class InputFragment : Fragment() {

    private var _binding: FragmentInputBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ActivityViewModel by viewModels()


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

//    private fun makeRequest() {
//        App.api.getPercentage(
//            firstName = binding.edtFirstName.text.toString(),
//            secondName = binding.edtSecondName.text.toString()
//        ).enqueue(
//            object: Callback<CalculateModel> {
//                override fun onResponse(
//                    call: Call<CalculateModel>,
//                    response: Response<CalculateModel>
//                ) {
//                    binding.edtFirstName.setText("")
//                    binding.edtSecondName.setText("")
//                    response.body()?.let { openResult(it) }
//                }
//
//                override fun onFailure(call: Call<CalculateModel>, t: Throwable) {
//                    Toast.makeText(requireContext(), "${t.stackTrace}", Toast.LENGTH_SHORT).show()
//                }
//
//            }
//        )
//    }

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