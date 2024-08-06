package com.example.app_name.view.fragments.userDetail

import android.os.Bundle
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.app_name.R
import com.example.app_name.extensions.setupProgressDialog
import com.example.app_name.koinDI.userDetailModule
import com.example.app_name.util.MaterialDialogHelper
import com.example.app_name.view.fragments.base.MainMVVMNavigationFragment
import org.koin.android.ext.android.inject
import org.koin.core.module.Module
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_user_detail.view.*


class UserDetailFragment :
    MainMVVMNavigationFragment<UserDetailViewModel>(UserDetailViewModel::class) {

    private val navArgs: UserDetailFragmentArgs by navArgs()

    override fun getLayoutResId(): Int = R.layout.fragment_user_detail

    override fun registerModule(): Module {
        return userDetailModule
    }

    override fun inOnCreateView(mRootView: ViewGroup, savedInstanceState: Bundle?) {
        val dialogHelper by inject<MaterialDialogHelper>()
        setupProgressDialog(viewModel.showHideProgressDialog, dialogHelper)

        navArgs.userDetail?.let {
            if (it.avatar_url == null) {
                mRootView.fragment_user_detial_image_view.setImageResource(R.drawable.ic_baseline_person_24)
            } else {
                Glide.with(mRootView.fragment_user_detial_image_view).load(it.avatar_url)
                    .into(mRootView.fragment_user_detial_image_view)
            }

            mRootView.fragment_user_detail_name.text = it.name

//            mRootView.fragment_user_detail_followers.text = it.followers.toString()
//            mRootView.fragment_user_detail_following.text = it.following.toString()

            mRootView.iv_back_btn.setOnClickListener {
                findNavController().navigateUp()
            }
            mRootView.fragment_user_detail_company.text = it.company
            mRootView.fragment_user_detail_blog.text = it.blog
            mRootView.fragment_user_detail_location.text = it.location
            mRootView.fragment_user_detail_bio.text = it.bio

        }


    }


}