package com.bangkit.moviesandtvshowsapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.moviesandtvshowsapp.adapter.TvShowAdapter
import com.bangkit.moviesandtvshowsapp.databinding.FragmentTvShowsBinding
import com.bangkit.moviesandtvshowsapp.domain.model.TvShow
import com.bangkit.moviesandtvshowsapp.viewmodel.TvShowsViewmodel
import com.bangkit.moviesandtvshowsapp.viewmodel.ViewModelFactory
import com.bangkit.moviesandtvshowsapp.vo.Status


class TvShowsFragment : Fragment() {
    private var _binding: FragmentTvShowsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvShowsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModelFactory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, viewModelFactory)[TvShowsViewmodel::class.java]
        val tvAdapter = TvShowAdapter()

        binding.rvTvShows.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = tvAdapter
        }

        viewModel.getTvShows().observe(viewLifecycleOwner, {
            if (it != null) {
                when (it.status) {
                    Status.LOADING -> binding.progBar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        binding.progBar.visibility = View.INVISIBLE
                        tvAdapter.setShow(it.data as List<TvShow>)
                    }
                    Status.ERROR -> {
                        binding.progBar.visibility = View.INVISIBLE
                        Toast.makeText(
                            requireActivity(),
                            "Tv Show list is empty",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}