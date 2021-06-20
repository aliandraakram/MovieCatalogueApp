package com.bangkit.moviesandtvshowsapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.moviesandtvshowsapp.R
import com.bangkit.moviesandtvshowsapp.adapter.MoviesAdapter
import com.bangkit.moviesandtvshowsapp.databinding.FragmentFavMovieBinding
import com.bangkit.moviesandtvshowsapp.viewmodel.FavMovieViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class FavMovieFragment : Fragment() {
    private val viewModel: FavMovieViewModel by viewModel()
    private var _binding: FragmentFavMovieBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val favAdapter = MoviesAdapter()


            binding.progBar.visibility = View.VISIBLE
            viewModel.getFavMovie().observe(viewLifecycleOwner, {
                if (it != null) {
                    binding.rvMovies.apply {
                        layoutManager = LinearLayoutManager(context)
                        setHasFixedSize(true)
                        adapter = favAdapter
                    }
                    favAdapter.setMovie(it)
                } else {
                    binding.favListEmpty.text = resources.getString(R.string.favorite_list_empty)
                }
                binding.progBar.visibility = View.INVISIBLE
            })
        }
    }


}