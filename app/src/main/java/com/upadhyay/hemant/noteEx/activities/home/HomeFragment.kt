package com.upadhyay.hemant.noteEx.activities.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.upadhyay.hemant.R
import com.upadhyay.hemant.databinding.FragmentHomeBinding
import com.upadhyay.hemant.noteEx.Util.viewBinding
import com.upadhyay.hemant.noteEx.activities.createNotes.CreateNoteFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    private lateinit var notesAdapter: NotesAdapter

    private val viewModel by viewModels<HomeViewModel>()

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRv()
        collectNotes()

        // FAB CREATE NOTE FRAGMENT
        binding.fabCreateNoteBtn.setOnClickListener {
            replaceFragment(CreateNoteFragment.newInstance(), true)
        }

        binding.searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                viewModel.onSearchQueryChanged(p0.toString())
                return true
            }
        })
    }

    private val onClicked = object : NotesAdapter.OnItemClickListener {
        override fun onClicked(notesId: Int) {

            val fragment: Fragment
            val bundle = Bundle()
            bundle.putInt(getString(R.string.noteID), notesId)
            fragment = CreateNoteFragment.newInstance()
            fragment.arguments = bundle

            replaceFragment(fragment, true)
        }
    }

    private fun collectNotes() = viewLifecycleOwner.lifecycleScope.launchWhenStarted {
        viewModel.notes.collectLatest {
            notesAdapter.submitList(it)
        }
    }

    private fun setUpRv() = binding.apply {
        notesAdapter = NotesAdapter().apply { setOnClickListener(onClicked) }
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.adapter = notesAdapter
    }

    fun replaceFragment(fragment: Fragment, istransition: Boolean) {

        val fragmentTransition = requireActivity().supportFragmentManager.beginTransaction()

        if (istransition) {
            fragmentTransition.setCustomAnimations(
                android.R.anim.slide_out_right,
                android.R.anim.slide_in_left
            )
        }
        fragmentTransition.replace(R.id.frameLayoutFragment, fragment)
            .addToBackStack(fragment.javaClass.simpleName)
        fragmentTransition.commit()
    }
}
