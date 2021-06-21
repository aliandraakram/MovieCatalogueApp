package com.bangkit.moviesandtvshowsapp.favorite.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.moviesandtvshowsapp.adapter.MoviesAdapter
import com.bangkit.moviesandtvshowsapp.favorite.R
import com.bangkit.moviesandtvshowsapp.favorite.databinding.FragmentFavMovieBinding
import com.bangkit.moviesandtvshowsapp.favorite.favMovieModule
import com.bangkit.moviesandtvshowsapp.favorite.viewmodel.FavMovieViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules


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
        loadKoinModules(favMovieModule)

        if (activity != null) {
            val favAdapter = MoviesAdapter()

            binding.rvMovies.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = favAdapter
            }

            binding.progBar.visibility = View.VISIBLE
            viewModel.getFavMovie().observe(viewLifecycleOwner, {
                if (it != null) {
                    favAdapter.setMovie(it)
                } else {
                    binding.rvMovies.visibility = View.GONE
                    binding.favListEmpty.text = resources.getString(R.string.favorite_list_empty)
                    binding.favListEmpty.visibility = View.VISIBLE
                }
                binding.progBar.visibility = View.INVISIBLE
            })
        }
    }


}