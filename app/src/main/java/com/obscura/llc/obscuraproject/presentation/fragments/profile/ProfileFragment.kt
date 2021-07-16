package com.obscura.llc.obscuraproject.presentation.fragments.profile

import android.os.Bundle
import android.widget.Toast
import com.obscura.llc.obscuraproject.R
import com.obscura.llc.obscuraproject.databinding.ProfileDataBinding
import com.obscura.llc.obscuraproject.presentation.base.BaseFragment
import com.obscura.llc.obscuraproject.presentation.fragments.profile.flow.ProfileFlow
import com.obscura.llc.obscuraproject.presentation.fragments.profile.flow.ProfileModel
import com.obscura.llc.obscuraproject.presentation.fragments.profile.flow.ProfileModelBinding
import com.obscura.llc.obscuraproject.presentation.navigation.fragment_navigator.model.Pages
import com.obscura.llc.moduleproject.data_source.database.entity.CategoryEntity
import com.obscura.llc.moduleproject.data_source.database.entity.LocationEntity
import com.obscura.llc.moduleproject.data_source.database.entity.UserEntity

class ProfileFragment : BaseFragment<ProfileDataBinding>(), ProfileFlow.ProfileListener {

    /**
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //ToDo get extras from bundle
        }
    }

    /**
     * @return
     */
    override fun getLayoutId(): Int = R.layout.fragment_profile

    /**
     * @param binding
     */
    override fun setupViewLogic(binding: ProfileDataBinding) {
        context?.let {
            binding.bindingModel = ProfileModelBinding(ProfileModel(getUser()), it)
            binding.clickHandler = this
        }
    }

    private fun getUser() : UserEntity {
        val user = UserEntity(509)
        user.surname = "Anton"
        user.name = "Antonov"
        user.avatarPreview = "https://home.caresolace.com/wp-content/uploads/2019/04/adult-beach-casual-736716.jpg"
        user.favoriteCategories = mutableListOf(CategoryEntity(1, "ART"), CategoryEntity(2, "Android"))
        user.location = LocationEntity(1, "Ukraine", "Dnepropetrovska", "Dnipro")
        user.occupation = "Android Developer"
        return user
    }

    companion object {
        @JvmStatic
        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }

    override fun onLogout() {
        Toast.makeText(context, "action logout in progress", Toast.LENGTH_LONG).show()
    }

    override fun openScreen(page: Pages) {
        when(page){
            Pages.EDIT_PERSONAL_DATA -> {
                Toast.makeText(context, "action open edit personal data in progress", Toast.LENGTH_LONG).show()
            }
            Pages.EDIT_PROFESSIONAL_DATA -> {
                Toast.makeText(context, "action open edit professional data in progress", Toast.LENGTH_LONG).show()
            }
            Pages.THEMES_CALENDAR -> {
                Toast.makeText(context, "action open themes calendar in progress", Toast.LENGTH_LONG).show()
            }
            Pages.CREATED_THEMES, Pages.FOLLOWED_THEMES -> {
                getBaseActivity().goToDetailListActivity(page)
            }
            Pages.APPLICATION_SETTINGS -> {
                Toast.makeText(context, "action open application settings in progress", Toast.LENGTH_LONG).show()
            }
        }
//        getBaseActivity().goToPage(PageNavigationItem(page), TransitionBundle(TransitionAnimation.ENTER_FROM_RIGHT))
    }
}
