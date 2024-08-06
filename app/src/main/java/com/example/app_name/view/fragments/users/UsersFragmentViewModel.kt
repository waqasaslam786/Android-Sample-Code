package com.example.app_name.view.fragments.users

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.app_name.api.SharedWebService
import com.example.app_name.extensions.wrapWithEvent
import com.example.app_name.models.userDetailModel.UserDetailResponse
import com.example.app_name.models.usersModel.AllUserResponseItem
import com.example.app_name.util.AppUtils
import com.example.app_name.util.Event
import com.example.app_name.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class UsersFragmentViewModel constructor(
    private val sharedWebService: SharedWebService,
) : BaseViewModel() {


    var allUsers: MutableLiveData<Event<List<AllUserResponseItem>>> = MutableLiveData()
    var userDetail: MutableLiveData<Event<UserDetailResponse>> = MutableLiveData()

    /**
     * hit the api
     */
    private var since = 0;

    fun getNewUsers() {
        viewModelScope.launch {

            _showHideProgressDialog.value = true.wrapWithEvent()

            sharedWebService.getAllUserList(since).run {

                onSuccess {

                    _showHideProgressDialog.value = false.wrapWithEvent()

                    allUsers.value = it.wrapWithEvent()

                    since = it.last().id

                }

                onFailure {
                    _showHideProgressDialog.value = false.wrapWithEvent()
//                    it.message?.let { it1 -> showSnackbarMessage(it1) }
                    showSnackbarMessage(AppUtils.INTERNET_CONNECTION_ERROR_MESSAGE)
                }

            }
        }
    }

    fun getUserDetailAndNavigate(userID: String) {
        viewModelScope.launch {

            _showHideProgressDialog.value = true.wrapWithEvent()

            sharedWebService.getUserDetailById(userID).run {

                onSuccess {

                    _showHideProgressDialog.value = false.wrapWithEvent()

                    userDetail.value = it.wrapWithEvent()


                }

                onFailure {
                    _showHideProgressDialog.value = false.wrapWithEvent()
                    showSnackbarMessage(AppUtils.INTERNET_CONNECTION_ERROR_MESSAGE)
                }

            }
        }

    }

}