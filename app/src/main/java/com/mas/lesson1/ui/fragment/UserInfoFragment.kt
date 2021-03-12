package com.mas.lesson1.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mas.lesson1.databinding.FragmentUserInfoBinding
import com.mas.lesson1.mvp.model.entity.GithubUser
import com.mas.lesson1.mvp.presenter.UsersInfoPresenter
import com.mas.lesson1.mvp.view.UsersInfoView
import com.mas.lesson1.ui.App
import com.mas.lesson1.ui.BackClickListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserInfoFragment : MvpAppCompatFragment(), UsersInfoView, BackClickListener {

    companion object {
        private const val USER_ARG = "user"
        fun newInstance(user: GithubUser) = UserInfoFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER_ARG,user)
            }
        }
    }

    private val presenter by moxyPresenter {
        val user = arguments?.getParcelable<GithubUser>(USER_ARG) as GithubUser
        UsersInfoPresenter(user, App.instance.router)
    }

    private var vb: FragmentUserInfoBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentUserInfoBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun setLogin(text: String) {
        vb?.userLogin?.text = text
    }


    override fun backPressed() = presenter.backClick()

}