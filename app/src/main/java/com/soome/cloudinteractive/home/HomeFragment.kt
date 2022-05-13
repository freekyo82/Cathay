package com.soome.cloudinteractive.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.soome.cloudinteractive.R
import com.soome.cloudinteractive.databinding.HomeFragmentBinding
import com.soome.cloudinteractive.entity.UserData
import com.soome.cloudinteractive.home.HomeFragmentDirections.Companion.actionHomeFragmentToUserDetailFragment
import com.soome.cloudinteractive.launchAndRepeatWithViewLifecycle
import com.soome.cloudinteractive.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.home_fragment) {

    private val binding by viewBinding(HomeFragmentBinding::bind)
    private val viewModel by viewModels<HomeViewModel>()
    private var userListAdapter = UserListAdapter {
        findNavController().navigate(actionHomeFragmentToUserDetailFragment(it.login))
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rvView.apply {
            adapter = userListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        launchAndRepeatWithViewLifecycle {
            viewModel.userList.collect {
                userListAdapter.submitData(it)
            }
        }
    }
}