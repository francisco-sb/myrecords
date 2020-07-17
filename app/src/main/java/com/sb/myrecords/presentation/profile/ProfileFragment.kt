package com.sb.myrecords.presentation.profile

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.sb.myrecords.R
import com.sb.myrecords.databinding.ProfileFragmentBinding
import com.sb.myrecords.di.Injectable
import com.sb.myrecords.di.injectViewModel
import com.sb.myrecords.ui.setExpandedToolbar
import com.sb.myrecords.ui.setTitle
import javax.inject.Inject

class ProfileFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: ProfileViewModel

    private val args: ProfileFragmentArgs? by navArgs()

    private lateinit var binding: ProfileFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = injectViewModel(viewModelFactory)

        binding = ProfileFragmentBinding.inflate(inflater, container, false)
        context ?: return binding.root

        setHasOptionsMenu(true)
        setTitle(getString(R.string.title_profile_fragment))
        setExpandedToolbar(false)

        subscribeUI()

        binding.changePhotoButton.setOnClickListener {
            if (allPermissionsGranted()) {
                it.findNavController().navigate(R.id.cameraFragment)
            } else {
                requestPermissions(REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)
            }
        }

        return binding.root
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults:
        IntArray
    ) {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                binding.root.findNavController().navigate(R.id.cameraFragment)
            } else {
                Snackbar.make(
                    requireView(),
                    "Permissions not granted by the user.",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_profile, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save_profile -> {
                //viewModel.update(viewModel.updatedUser)
                viewModel.updatedUser.name = binding.nameText.text!!.toString()
                viewModel.updatedUser.username = binding.usernameText.text!!.toString()
                viewModel.updatedUser.biography = binding.biographyText.text!!.toString()

                viewModel.update(viewModel.updatedUser)

                Snackbar.make(requireView(), "Profile saved!", Snackbar.LENGTH_SHORT).show()
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    //region:: PRIVATE METHODS
    private fun subscribeUI() {
        viewModel.user.observe(viewLifecycleOwner, Observer {
            binding.apply {
                viewModel.updatedUser = it

                if (!args?.photoUri.equals("none")) {
                    viewModel.updatedUser.img = args?.photoUri!!
                }

                user = viewModel.updatedUser
                executePendingBindings()
            }
        })
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        activity?.baseContext?.let {context ->
            ContextCompat.checkSelfPermission(
                context, it)
        } == PackageManager.PERMISSION_GRANTED
    }

    //endregion

    companion object {
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    }
}
