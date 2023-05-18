package com.example.wordstory.view.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.wordstory.R
import com.example.wordstory.databinding.ActivityChapterBinding
import com.example.wordstory.model.ChaptersModel
import com.example.wordstory.model.StoriesModel
import com.example.wordstory.viewmodel.ListFragmentViewModel

class ChapterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChapterBinding
    private lateinit var viewModel: ListFragmentViewModel


    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChapterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var chaptersModel = intent.extras?.get("dungbach") as ChaptersModel
        val storiesModel = intent.extras?.get("bachdung") as StoriesModel?
        viewModel = ViewModelProvider(this)[ListFragmentViewModel::class.java]
        if (storiesModel != null) {
            viewModel.setData(storiesModel)
        }
        val mutableList = viewModel.getFragmentRecyclerViewData()

        updateContent(mutableList, chaptersModel, Action.NONE)
        binding.back.setOnClickListener {
            chaptersModel = updateContent(mutableList, chaptersModel, Action.PREVIOUS)
            val path = chaptersModel.content.replace("/assets/", "")
            getChapterContent(path, chaptersModel.name)
        }
        binding.next.setOnClickListener {
            chaptersModel = updateContent(mutableList, chaptersModel, Action.NEXT)
            val path = chaptersModel.content.replace("/assets/", "")
            getChapterContent(path, chaptersModel.name)
        }
        binding.buttonBack.setOnClickListener {
            onBackPressed()
        }

        binding.nameChapter.text = chaptersModel.name

        val path = chaptersModel.content.replace("/assets/", "")
        binding.webView.settings.javaScriptEnabled = true
        binding.buttonSetting.setOnClickListener {
            val popupMenu = PopupMenu(this, binding.buttonSetting)
            popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { menuItem ->
                val size = menuItem.title.toString()
                binding.webView.settings.defaultFontSize = size.toInt()
                true
            }
            popupMenu.show()
        }
        binding.switchButton.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                binding.webView.setBackgroundColor(resources.getColor(R.color.bg_webview))
                binding.webView.reload()
            } else {
                binding.webView.setBackgroundColor(resources.getColor(R.color.white))
                binding.webView.reload()
            }
        }
        binding.switchButton.isChecked = false
        getChapterContent(path, chaptersModel.name)
    }

    private fun updateContent(
        mutableList: MutableList<ChaptersModel>,
        chaptersModel: ChaptersModel,
        action: Action
    ): ChaptersModel {
        val last = mutableList.size - 1

        var index = -1
        for (i in mutableList.indices) {
            if (mutableList[i].id == chaptersModel.id) {
                index = i
                break
            }
        }

        when (action) {
            Action.NEXT -> {
                binding.next.visibility = View.VISIBLE
                binding.back.visibility = View.VISIBLE
                index++
                if (index == last) {
                    binding.next.visibility = View.GONE
                }
                return mutableList[index]
            }
            Action.PREVIOUS -> {
                binding.next.visibility = View.VISIBLE
                binding.back.visibility = View.VISIBLE
                index--
                if (index == 0) {
                    binding.back.visibility = View.GONE
                }
                return mutableList[index]
            }
            else -> {
                if (index == last) {
                    binding.next.visibility = View.GONE
                } else if (index == 0) {
                    binding.back.visibility = View.GONE
                }
                return mutableList[index]
            }
        }
        return mutableList[index]
    }


    fun getChapterContent(path: String, name: String) {
        binding.nameChapter.text = name

        val htmlFileContents = assets.open(path).bufferedReader().use { it.readText() }
        val cssStyles = "<style>.chapter-c{\n" +
                "    font-family:\"Palatino Linotype\",\"Arial\",\"Times New Roman\",sans-serif;\n" +
                "    line-height:180%;\n" +
                "    color:#2B2B2B;\n" +
                "    text-align:left;\n" +
                "    word-wrap:break-word\n" +
                "} </style>"
        val htmlContentWithStyles = htmlFileContents.replace("</head>", "$cssStyles</head>")
        //binding.webView.loadUrl("file:///android_asset/${path}")
        binding.webView.loadDataWithBaseURL(null, htmlContentWithStyles, "text/html", "UTF-8", null)
    }

    enum class Action {
        NEXT, PREVIOUS, NONE
    }
}