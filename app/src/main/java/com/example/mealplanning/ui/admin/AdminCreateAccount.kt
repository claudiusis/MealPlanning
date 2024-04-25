package com.example.mealplanning.ui.admin
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mealplanning.R
import com.example.mealplanning.databinding.FragmentAdminCreateAccountBinding
import com.example.mealplanning.viewModels.AdminViewModel

class AdminCreateAccount : Fragment() {


    private var _binding:FragmentAdminCreateAccountBinding?=null
    private val mBinding get()=_binding!!
    private val viewModelAdmin:AdminViewModel by activityViewModels<AdminViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding= FragmentAdminCreateAccountBinding.inflate(inflater, container,false)

        mBinding.adminCreateAccountBtn.setOnClickListener {
            val role=mBinding.adminRoleET.text.toString()
            val name=mBinding.adminNameET.text.toString()
            val surName=mBinding.adminSurnameET.text.toString()
            val login=mBinding.adminLoginET.text.toString()
            val password=mBinding.adminPasswordET.text.toString()
            val account=AccountsData(null, name, role,surName,login,password)
            viewModelAdmin.createAccount(account)
            findNavController().navigate(R.id.action_adminCreateAccount_to_adminAccountsFragment)

        }

        return mBinding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}