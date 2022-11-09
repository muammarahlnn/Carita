package com.ardnn.carita.ui.addstory

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_GET_CONTENT
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.PackageManagerCompat
import androidx.fragment.app.viewModels
import com.ardnn.carita.CaritaApplication
import com.ardnn.carita.R
import com.ardnn.carita.data.addstory.repository.source.remote.request.AddStoryRequest
import com.ardnn.carita.databinding.FragmentAddStoryBinding
import com.ardnn.carita.ui.main.MainActivity
import com.ardnn.carita.ui.util.*
import com.ardnn.carita.vo.Status
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

class AddStoryFragment : BottomSheetDialogFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var onSuccessPostStory: OnSuccessPostStory

    private val viewModel: AddStoryViewModel by viewModels {
        viewModelFactory
    }

    private var file: File? = null

    private var _binding: FragmentAddStoryBinding? = null

    private val binding get() = _binding

    private var token = ""

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as CaritaApplication).applicationComponent.inject(this)
        onSuccessPostStory = context as OnSuccessPostStory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddStoryBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            if (!isAllPermissionsGranted()) {
                ActivityCompat.requestPermissions(
                    activity as Activity,
                    REQUIRED_PERMISSIONS,
                    REQUEST_CODE_PERMISSIONS
                )
            }
            setupBundle()
            setupViewModel()
            setupActions()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun isAllPermissionsGranted(): Boolean {
        return REQUIRED_PERMISSIONS.all {
            ContextCompat.checkSelfPermission(context as Context, it) == PackageManager.PERMISSION_GRANTED
        }
    }

    private fun setupBundle() {
        arguments?.let {
            token = it.getString(EXTRA_TOKEN, "")
        }
    }

    private fun setupViewModel() {
        viewModel.addStory.observe(this) { addStoryResponse ->
            when (addStoryResponse) {
                is Status.Success -> {
                    hideLoading()
                    showToast(context as Context, "Story created successfully")
                    dismiss()
//                    (activity as MainActivity).getStories()
                    onSuccessPostStory.onSuccess()
                }

                is Status.Error -> {
                    hideLoading()
                    showToast(context as Context, addStoryResponse.message.toString())
                }

                is Status.Loading -> {
                    showLoading()
                }
            }
        }
    }

    private fun showLoading() {
        binding?.loading?.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding?.loading?.visibility = View.GONE
    }

    private fun setupActions() {
        binding?.run {
            btnClose.setOnClickListener {
                dismiss()
            }
            btnGallery.setOnClickListener {
                openGallery()
            }
            btnCamera.setOnClickListener {
                startCameraX()
            }
            btnUpload.setOnClickListener {
                uploadStory()
            }
        }
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == -1) {
            val selectedImage: Uri = result.data?.data as Uri
            val myFile = uriToFile(selectedImage, context as Context)
            file = myFile
            binding?.ivImage?.setImageURI(selectedImage)
        }
    }

    private fun openGallery() {
        val intent = Intent().apply {
            action = ACTION_GET_CONTENT
            type = "image/*"
        }
        val chooser = Intent.createChooser(intent, "Choose a picture")
        launcherIntentGallery.launch(chooser)
    }

    private val launcherActivityCameraX = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == CAMERA_X_RESULT) {
            val myFile = result.data?.getSerializableExtra(EXTRA_PICTURE) as File
            val isBackCamera = result.data?.getBooleanExtra(EXTRA_IS_BACK_CAMERA, true) as Boolean

            file = myFile

            val selectedImage = rotateBitmap(BitmapFactory.decodeFile(myFile.path), isBackCamera)
            binding?.ivImage?.setImageBitmap(selectedImage)
        }
    }

    private fun startCameraX() {
        launcherActivityCameraX.launch(Intent(activity, CameraActivity::class.java))
    }

    private fun uploadStory() {
        val caption = binding?.etCaption?.text?.toString()
        if (caption.isNullOrEmpty()) {
            showToast(context as Context, "Please fill the caption first")
            return
        }
        if (file != null) {
            val file = reduceFileImage(this@AddStoryFragment.file as File)
            val description = caption.toRequestBody("text/plain".toMediaType())
            val requestImageFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
            val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
                "photo",
                file.name,
                requestImageFile
            )
            viewModel.postStory(token, imageMultipart, description)
        } else {
            showToast(context as Context, "Please take an image first")
        }
    }

    interface OnSuccessPostStory {

        fun onSuccess()
    }

    companion object {

        const val EXTRA_TOKEN = "extra_token"
        const val EXTRA_PICTURE = "extra_picture"
        const val EXTRA_IS_BACK_CAMERA = "extra_is_back_camera"
        const val CAMERA_X_RESULT = 200
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }
}