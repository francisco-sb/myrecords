package com.sb.myrecords.presentation.profile

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.sb.myrecords.R
import com.sb.myrecords.databinding.ProfileFragmentBinding
import com.sb.myrecords.di.Injectable
import com.sb.myrecords.di.injectViewModel
import com.sb.myrecords.ui.setExpandedToolbar
import com.sb.myrecords.ui.setTitle
import kotlinx.android.synthetic.main.picture_bottom_sheet_layout.view.*
import javax.inject.Inject

class ProfileFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: ProfileViewModel

    private val args: ProfileFragmentArgs? by navArgs()

    private lateinit var binding: ProfileFragmentBinding
    private lateinit var bottomSheet: BottomSheetDialog

    @SuppressLint("InflateParams")
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

        bottomSheet = BottomSheetDialog(requireContext())
        val bottomView = layoutInflater.inflate(R.layout.picture_bottom_sheet_layout, null)
        bottomSheet.setContentView(bottomView)


        binding.changePhotoButton.setOnClickListener {
            bottomSheet.show()
        }

        bottomView.take_picture_option.setOnClickListener {
            if (allCameraPermissionsGranted()) {
                binding.root.findNavController().navigate(R.id.cameraFragment)
                bottomSheet.dismiss()
            } else {
                requestPermissions(REQUIRED_CAMERA, REQUEST_CODE_CAMERA)
            }
        }

        bottomView.open_gallery_option.setOnClickListener {
            if (allExternalStoragePermissionsGranted()) {
                openGallery()
                bottomSheet.dismiss()
            } else {
                requestPermissions(REQUIRED_EXTERNAL_STORAGE, REQUEST_CODE_EXTERNAL_STORAGE)
            }
        }

        return binding.root
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults:
        IntArray
    ) {
        if (requestCode == REQUEST_CODE_CAMERA) {
            if (allCameraPermissionsGranted()) {
                binding.root.findNavController().navigate(R.id.cameraFragment)
                bottomSheet.dismiss()
            } else {
                Snackbar.make(
                    requireView(),
                    "Permissions not granted by the user.",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

        if (requestCode == REQUEST_CODE_EXTERNAL_STORAGE) {
            if (allExternalStoragePermissionsGranted()) {
                openGallery()
                bottomSheet.dismiss()
            } else {
                Snackbar.make(
                    requireView(),
                    "Permissions not granted by the user.",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_EXTERNAL_STORAGE) {
            viewModel.updatedUser.img = data?.data.toString()
            binding.apply {
                user = viewModel.updatedUser
                executePendingBindings()
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

    private fun allCameraPermissionsGranted() = REQUIRED_CAMERA.all {
        activity?.baseContext?.let {context ->
            ContextCompat.checkSelfPermission(
                context, it)
        } == PackageManager.PERMISSION_GRANTED
    }

    private fun allExternalStoragePermissionsGranted() = REQUIRED_EXTERNAL_STORAGE.all {
        activity?.baseContext?.let {context ->
            ContextCompat.checkSelfPermission(
                context, it)
        } == PackageManager.PERMISSION_GRANTED
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE_EXTERNAL_STORAGE)
    }
    //endregion

    companion object {
        private const val REQUEST_CODE_CAMERA = 10
        private const val REQUEST_CODE_EXTERNAL_STORAGE = 100
        private val REQUIRED_CAMERA = arrayOf(Manifest.permission.CAMERA)
        private val REQUIRED_EXTERNAL_STORAGE = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
    }
}
