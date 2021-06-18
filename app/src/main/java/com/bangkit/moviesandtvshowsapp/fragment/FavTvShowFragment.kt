package com.bangkit.moviesandtvshowsapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.moviesandtvshowsapp.R
import com.bangkit.moviesandtvshowsapp.adapter.TvShowAdapter
import com.bangkit.moviesandtvshowsapp.databinding.FragmentFavTvShowBinding
import com.bangkit.moviesandtvshowsapp.viewmodel.FavTvShowViewModel
import com.bangkit.moviesandtvshowsapp.viewmodel.ViewModelFactory


class FavTvShowFragment : Fragment() {
    private var _binding: FragmentFavTvShowBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavTvShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val favAdapter = TvShowAdapter()

        binding.rvTvShows.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = favAdapter
        }

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewmodel = ViewModelProvider(this, factory)[FavTvShowViewModel::class.java]

        binding.progBar.visibility = View.VISIBLE
        viewmodel.getFavTvShow().observe(viewLifecycleOwner, {
            if (it != null){
                favAdapter.setShow(it)
            } else {
                binding.rvTvShows.visibility = View.GONE
                binding.favListEmpty.text = resources.getString(R.string.favorite_list_empty)
            }
            binding.progBar.visibility = View.INVISIBLE
        })

    }


}