package com.bangkit.moviesandtvshowsapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.moviesandtvshowapp.core.domain.model.Movie
import com.bangkit.moviesandtvshowapp.core.vo.Status
import com.bangkit.moviesandtvshowsapp.adapter.MoviesAdapter
import com.bangkit.moviesandtvshowsapp.databinding.FragmentMovieBinding
import com.bangkit.moviesandtvshowsapp.viewmodel.MovieViewmodel
import org.koin.android.viewmodel.ext.android.viewModel


class MovieFragment : Fragment() {

    private val viewModel: MovieViewmodel by viewModel()
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val movieAdapter = MoviesAdapter()

            binding.rvMovies.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }


            viewModel.getMovies().observe(viewLifecycleOwner, {
                if (it != null) {
                    when (it.status) {
                        Status.LOADING -> binding.progBar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            binding.progBar.visibility = View.INVISIBLE
                            movieAdapter.setMovie(it.data as List<Movie>)
                        }
                        Status.ERROR -> {
                            binding.progBar.visibility = View.INVISIBLE
                            Toast.makeText(
                                requireActivity(),
                                "Movie list is empty",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }

            })

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}