package com.example.app_name.view.fragments.users

import android.os.Bundle
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app_name.R
import com.example.app_name.extensions.setupProgressDialog
import com.example.app_name.koinDI.usersModule
import com.example.app_name.util.MaterialDialogHelper
import com.example.app_name.view.fragments.base.MainMVVMNavigationFragment
import kotlinx.android.synthetic.main.fragment_user.view.*
import org.koin.android.ext.android.inject
import org.koin.core.module.Module


class UsersFragment :
    MainMVVMNavigationFragment<UsersFragmentViewModel>(UsersFragmentViewModel::class) {


    override fun getLayoutResId() = R.layout.fragment_user
    override fun registerModule(): Module {
        return usersModule
    }

    private lateinit var allUserAdapter: UsersAdapter

    override fun inOnCreateView(mRootView: ViewGroup, savedInstanceState: Bundle?) {
        val dialogHelper by inject<MaterialDialogHelper>()
        setupProgressDialog(viewModel.showHideProgressDialog, dialogHelper)

        allUserAdapter = UsersAdapter {
            viewModel.getUserDetailAndNavigate(it.login)
        }

        val mLayoutManager = LinearLayoutManager(requireContext())
        mRootView.fragment_home_recyclerview.let {
            it.layoutManager = mLayoutManager
            it.adapter = allUserAdapter
        }


        var loading = true
        var pastVisiblesItems: Int
        var visibleItemCount: Int
        var totalItemCount: Int

        mRootView.fragment_home_recyclerview.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) { //check for scroll down
                    visibleItemCount = mLayoutManager.getChildCount()
                    totalItemCount = mLayoutManager.getItemCount()
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition()
                    if (loading) {
                        if (visibleItemCount + pastVisiblesItems >= totalItemCount) {
                            loading = false

                            viewModel.getNewUsers();
                            // Do pagination.. i.e. fetch new data
                            loading = true
                        }
                    }
                }
            }
        })

        observe(viewModel.allUsers) {
            if (!it.consumed) it.consume()?.let { it1 ->
                allUserAdapter.submitList(it1)
            }
        }
        observe(viewModel.userDetail) {
            if (!it.consumed) it.consume()?.let { it1 ->
                findNavController().navigate(
                    UsersFragmentDirections.actionNavigationUserToNavigationUserDetail(
                        it1
                    )
                )
            }
        }

        viewModel.getNewUsers();

    }


}