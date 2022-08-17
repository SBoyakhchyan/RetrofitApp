package com.example.retrofitapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitapp.R
import com.example.retrofitapp.adapter.DisplayInfoAdapter
import com.example.retrofitapp.databinding.FragmentDisplayDataBinding
import com.example.retrofitapp.model.ArticelInfoData
import com.example.retrofitapp.model.MainViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class DisplayDataFragment : Fragment() {
    private lateinit var binding: FragmentDisplayDataBinding
    private var infoList = ArrayList<ArticelInfoData>()
    private lateinit var recyclerViewInfo: RecyclerView
    private lateinit var adapterInfo: DisplayInfoAdapter
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_display_data, container, false)
        binding = FragmentDisplayDataBinding.bind(view)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        GlobalScope.async(Dispatchers.Main) { setData() }
        initRecyclerView()
        observeLiveData()
        setRecyclerViewDivider()

    }

    //    private fun requestData() {
//        val service = getService()
//        service.getArticles("techcrunch").enqueue(object : Callback<ResponseResult> {
//            override fun onResponse(
//                call: Call<ResponseResult>,
//                response: Response<ResponseResult>
//            ) {
//                if (response.isSuccessful && response.body()?.articles?.isNotEmpty() == true) {
//                    adapterInfo.setList(response.body()?.articles!!)
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseResult>, t: Throwable) {
//                Log.d("TAG", "onResponse: ${t.message}")
//            }
//        })
//    }
    private fun observeLiveData() {
        viewModel.articlesLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                infoList = it
                Log.d("tttt", "success1")
            }
        }
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                adapterInfo.apply {
                    setList(ArrayList())
                    notifyDataSetChanged()
                }
            } else {
                Toast.makeText(requireContext(), "Error in getting list", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initRecyclerView() {
        recyclerViewInfo = binding.rvInsertData
        adapterInfo = DisplayInfoAdapter(infoList)
        recyclerViewInfo.adapter = adapterInfo
        recyclerViewInfo.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setRecyclerViewDivider() {
        val divider = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        divider.setDrawable(
            AppCompatResources.getDrawable(
                requireContext(),
                R.drawable.custom_divider
            )!!
        )
        recyclerViewInfo.addItemDecoration(divider)
    }

    private suspend fun setData() {
        withContext(Dispatchers.IO) {
            viewModel.getArticles("techcrunch")
            infoList = viewModel.articlesLiveData.value ?: return@withContext
            Log.d("ttttt", "00000 ${infoList.toString()}")
        }
    }
}