package com.example.retrofit.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.retrofit.App
import com.example.retrofit.CalculateModel
import com.example.retrofit.R
import com.example.retrofit.databinding.FragmentInputBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class InputFragment : Fragment() {

    private var _binding: FragmentInputBinding? = null
    private val binding get() = _binding!!
    companion object {
        const val KEY_FOR_BUNDLE = "bundle"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInputBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCalculate.setOnClickListener {
            makeRequest()
        }

    }

    private fun makeRequest() {
        App.api.getPercentage(
            firstName = binding.edtFirstName.text.toString(),
            secondName = binding.edtSecondName.text.toString()
        ).enqueue(
            object: Callback<CalculateModel> {
                override fun onResponse(
                    call: Call<CalculateModel>,
                    response: Response<CalculateModel>
                ) {
                    binding.edtFirstName.setText("")
                    binding.edtSecondName.setText("")
                    response.body()?.let { openResult(it) }
                }

                override fun onFailure(call: Call<CalculateModel>, t: Throwable) {
                    Toast.makeText(requireContext(), "${t.stackTrace}", Toast.LENGTH_SHORT).show()
                }

            }
        )
    }

    private fun openResult(model: CalculateModel) {
        val bundle = Bundle()
        bundle.putSerializable(KEY_FOR_BUNDLE, model)
        findNavController().navigate(R.id.resultFragment, bundle)
    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }


}