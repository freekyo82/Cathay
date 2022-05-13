package com.soome.cloudinteractive.detailed

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.soome.cloudinteractive.R
import com.soome.cloudinteractive.databinding.UserDetailFragmentBinding
import com.soome.cloudinteractive.launchAndRepeatWithViewLifecycle
import com.soome.cloudinteractive.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailFragment : Fragment(R.layout.user_detail_fragment) {

    private val binding by viewBinding(UserDetailFragmentBinding::bind)
    private val viewModel by viewModels<UserDetailedViewModel>()
    private val args by navArgs<UserDetailFragmentArgs>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getUserDetailed(args.login)

        launchAndRepeatWithViewLifecycle {
            viewModel.onUserEvent.collect {
                binding.ivAvatar.load(it.avatarUrl)
                binding.tvUserName.text = it.name
                binding.tvBlog.text = it.blog
                binding.tvLogin.text = it.login
                binding.tvLocation.text = it.location
                binding.tvStaff.isVisible = it.isAdmin
            }
        }
    }
}