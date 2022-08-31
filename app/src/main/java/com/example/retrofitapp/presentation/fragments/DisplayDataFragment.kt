package com.example.retrofitapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitapp.R
import com.example.retrofitapp.presentation.adapter.DisplayInfoAdapter
import com.example.retrofitapp.presentation.adapter.SwipeToDelete
import com.example.retrofitapp.databinding.FragmentDisplayDataBinding
import com.example.retrofitapp.presentation.model.ArticelInfoData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class DisplayDataFragment : Fragment() {
    private lateinit var binding: FragmentDisplayDataBinding
    private var infoList = ArrayList<ArticelInfoData>()
    private lateinit var recyclerViewInfo: RecyclerView
    private lateinit var adapterInfo: DisplayInfoAdapter
    private val viewModel: MainViewModel by viewModel()

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
        setData()
        initRecyclerView()
        collectFlows()
        setRecyclerViewDivider()

    }


    private fun collectFlows() {
        CoroutineScope(Main).launch {
            launch {
                viewModel.articlesStateFlow.collect { list ->
                    list?.let {
                        adapterInfo.setList(it)
                    }
                }
            }
            launch {
                viewModel.showProgressStateFlow.collect {
                    binding.progressBar.isVisible = it
                }
            }
            launch {
                viewModel.errorStateFlow.collect {
                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initRecyclerView() {
        recyclerViewInfo = binding.rvInsertData
        adapterInfo = DisplayInfoAdapter(infoList)
        recyclerViewInfo.adapter = adapterInfo
        recyclerViewInfo.layoutManager = LinearLayoutManager(requireContext())
        var itemTouchHelper = ItemTouchHelper(SwipeToDelete(adapterInfo))
        itemTouchHelper.attachToRecyclerView(recyclerViewInfo)
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

    private fun setData() {
        viewModel.getArticles("techcrunch")
    }
}