package com.bangkit.moviesandtvshowsapp.favorite.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.moviesandtvshowsapp.adapter.TvShowAdapter
import com.bangkit.moviesandtvshowsapp.favorite.R
import com.bangkit.moviesandtvshowsapp.favorite.databinding.FragmentFavTvShowBinding
import com.bangkit.moviesandtvshowsapp.favorite.favTvShowModule
import com.bangkit.moviesandtvshowsapp.favorite.viewmodel.FavTvShowViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules


class FavTvShowFragment : Fragment() {
    private val viewModel: FavTvShowViewModel by viewModel()
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
        loadKoinModules(favTvShowModule)

        val favAdapter = TvShowAdapter()

        binding.rvTvShows.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = favAdapter
        }

        binding.progBar.visibility = View.VISIBLE
        viewModel.getFavTvShow().observe(viewLifecycleOwner, {
            if (it != null) {
                favAdapter.setShow(it)
            } else {
                binding.rvTvShows.visibility = View.GONE
                binding.favListEmpty.text = resources.getString(R.string.favorite_list_empty)
                binding.favListEmpty.visibility = View.VISIBLE
            }
            binding.progBar.visibility = View.INVISIBLE
        })

    }


}